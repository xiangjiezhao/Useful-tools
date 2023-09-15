
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zhaoxiangjie
 * @version 1.0
 * @date 2023/8/24
 */
public class SocketListenerUtil {
    public static void main(String[] args) {
        int port = 8080; // 监听的端口号

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("等待连接...");

            while (true) {
                Socket socket = serverSocket.accept(); // 等待新的客户端连接

                // 创建线程处理每个客户端请求
                Thread clientThread = new Thread(() -> handleClientRequest(socket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket socket) {
        try {
            System.out.println("接受新连接，客户端地址：" + socket.getInetAddress());

            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println("接收到消息：" + message);
            }

            // 处理完请求后关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
