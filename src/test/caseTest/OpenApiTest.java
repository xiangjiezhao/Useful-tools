package caseTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 平台签名方式对接测试
 *
 * @author zhaoxiangjie
 * @version 1.0
 * @date 2023/10/11
 */
@SpringBootTest
public class OpenApiTest {

    private final String ClientId = "hJfXHDCPkTeXNKNi";

    private final String SecureKey = "RcSxBGDdwpFTGjmHjYMKTbWa";

//        private final String PlatformAddress = "http://10.86.3.21";
    private final String PlatformAddress = "http://localhost:8630";

    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void test1() {
        String url = "/api/v1/device/_query";


        StringBuilder param = new StringBuilder();
//
//        //将参数key按ascii排序
//        SortedMap<String, Object> params = new TreeMap<>();
//        params.put("pageSize", "20");
//        params.put("pageIndex", "0");
////        System.out.println(params);
//
//        //数据拼接
        long timestamp = System.currentTimeMillis();
//
//        params.forEach((key, value) -> {
//            param.append(key + "=" + value + "&");
//        });
//        param.deleteCharAt(param.length() - 1);
        param.append(timestamp);
        param.append(SecureKey);
////        System.out.println(param);
//
//        //md5加密
        String xSign = DigestUtils.md5DigestAsHex(param.toString().getBytes());
//
//        //发送请求
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Client-Id", ClientId);
//        headers.add("X-Timestamp", String.valueOf(timestamp));
//        headers.add("X-Sign", xSign);
//        headers.add("Content-Type", "application/json");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.exchange(PlatformAddress + url, HttpMethod.POST, entity, String.class);






        //v2
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Client-Id", ClientId);
        httpHeaders.add("X-Timestamp", String.valueOf(timestamp));
        httpHeaders.add("X-Sign", xSign);
        httpHeaders.add("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageIndex",0);
        jsonObject.put("pageSize",20);

        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<>(jsonObject, httpHeaders);

        String result = restTemplate.postForObject(PlatformAddress + url, jsonObjectHttpEntity, String.class);





        System.out.println(result);
    }

    @Test
    public void test2(){

        String url = PlatformAddress + "/api/v1/device/_query";

        HttpRequest request = new SimpleHttpRequest(url, httpClient);

        //String body = "{\"pageSize\":25,\"pageIndex\":0,\"where\":\"productId is 1236859833832701952\"}";
        String body = "{\"pageSize\":25,\"pageIndex\":0,\"terms\":[{\"column\":\"productId\",\"value\":\"1236859833832701952\"}]}";
        request.headers(HeaderUtils.createHeadersOfJsonString(body));
        request.requestBody(body);

        try {
            Response response = request.post();
            System.out.println(JSON.parse(response.asBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }



}
