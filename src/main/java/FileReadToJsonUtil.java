import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 监听文件目录，自动根据新增数据文件，批量解析生成json，并通过mq回传导入系统
 *
 * @author zhaoxiangjie
 * @version 1.0
 * @date 2023/9/15
 */
public class FileReadToJsonUtil {

    private static String path = "C:\\Users\\PLUG\\Desktop\\file\\";

    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            Integer count = 0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                if (count > 3) {
                    result.append(System.lineSeparator() + s);
                }
                count++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        //监听目录下生成新文件
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                WatchKey key;

                try {
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    Paths.get(path).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                    while (true) {
                        File file = new File(path);//path为监听文件夹
                        File[] files = file.listFiles();
                        System.out.println("等待新文件加载！");
                        key = watchService.take();//没有文件增加时，阻塞在这里
                        for (WatchEvent<?> event : key.pollEvents()) {
                            String fileName = path + event.context();
                            System.out.println("增加文件的文件夹路径" + fileName);

                            //获取最新文件
                            File file1;
                            if (files.length > 0) {
                                file1 = files[files.length - 1];
                            } else {
                                file1 = new File(fileName);
                            }

//                            System.out.println(file1.getName());//根据后缀判断
                            //String txt2String = txt2String(new File("C:\\Users\\PLUG\\Desktop\\file\\2023-09-12_DayTime.ALL"));
//                            String txt2String = txt2String(new File(path + file1.getName()));
                            String txt2String = txt2String(file1);
                            String[] lines = txt2String.split("\r\n");
                            JSONArray jsonArray = new JSONArray();
                            jsonArray.addAll(Arrays.asList(lines));
                            jsonArray.remove(0);

                            JSONArray result = new JSONArray();
                            String[] keys = new String[]{"NAME", "孔位置度 1", "孔位置度 2", "孔位置度 3", "孔位置度 4", "螺纹通规 1", "螺纹通规 2", "螺纹通规 3", "螺纹通规 4", "IN测密封径", "IN测大外径", "OUT测密封径", "法兰位置", "法兰位置跳动", "接触径", "OK/NG", "Time", "Operator", "2D Code", "Temper."};

                            for (int i = 0; i < jsonArray.size(); i++) {

                                String s0 = (String) jsonArray.get(i);
                                String[] split = s0.split("\\s+");

                                JSONObject object = new JSONObject();
                                for (int i1 = 0; i1 < keys.length; i1++) {
                                    if (i1 < split.length - 1) {
                                        object.put(keys[i1], split[i1 + 1]);
                                    }
                                    //没有字段就不要发，防止字段类型不匹配报错
                                    //   else {
                                    //     object.put(keys[i1],"");
                                    //        }
                                }
                                result.add(object);
                            }
//                            System.out.println(result);

                            //mq
                            String subTopic = "/PUSH";
                            String pubTopic = "/PUSH";
//                            String content = "Hello World";
                            String content = result.get(0).toString();
                            int qos = 2;
                            String broker = "tcp://10.86.3.21:1883";
                            String clientId = "emqx_test";
                            MemoryPersistence persistence = new MemoryPersistence();

                            try {
                                MqttClient client = new MqttClient(broker, clientId, persistence);

                                // MQTT 连接选项
                                MqttConnectOptions connOpts = new MqttConnectOptions();
                                connOpts.setUserName("admin");
                                connOpts.setPassword("admin".toCharArray());
                                // 保留会话
                                connOpts.setCleanSession(true);

                                // 设置回调
//                                client.setCallback(new PushCallback());

                                // 建立连接
                                System.out.println("Connecting to broker: " + broker);
                                client.connect(connOpts);

                                System.out.println("Connected");
                                System.out.println("Publishing message: " + content);

                                // 订阅
                                client.subscribe(subTopic);

                                // 消息发布所需参数
                                MqttMessage message = new MqttMessage(content.getBytes());
                                message.setQos(qos);
                                client.publish(pubTopic, message);
                                System.out.println("Message published");

                                client.disconnect();
                                System.out.println("Disconnected");
                                client.close();
                                System.exit(0);
                            } catch (MqttException me) {
                                System.out.println("reason " + me.getReasonCode());
                                System.out.println("msg " + me.getMessage());
                                System.out.println("loc " + me.getLocalizedMessage());
                                System.out.println("cause " + me.getCause());
                                System.out.println("excep " + me);
                                me.printStackTrace();
                            }
                        }
                        if (!key.reset()) {
                            break; //中断循环
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 2000, 3000);//第一个数字2000表示，2000ms以后开启定时器,第二个数字3000，表示3000ms后运行一次run
    }
}
