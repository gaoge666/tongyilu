package com.example.tongyilu.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author xwolf
 * @since 1.8
 **/
public class RestUtil {
    /**
     * get support https RestTemplate
     *
     * @return
     * @throws Exception
     */
    private static RestTemplate sslResttemplate() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    /**
     * https get 请求
     *
     * @param url   请求的url
     * @param map   参数
     * @param clazz 返回结果类型
     * @param <T>
     * @return
     */
    public static <T> T gethttps(String url, Map<String, Object> map, Class<T> clazz) {
        RestTemplate restTemplate = null;
        try {
            restTemplate = sslResttemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz, map);
        return responseEntity.getBody();
    }

    /**
     * get 请求7
     *
     * @param url   请求的url
     * @param map   参数
     * @param clazz 返回结果类型
     * @param <T>
     * @return
     */
    public static <T> T get(String url, Map<String, Object> map, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz, map);
        return responseEntity.getBody();
    }

    /**
     * <code>https://www.programcreek.com/java-api-examples/index.php?class=org.springframework.web.client.RestTemplate&method=postForEntity</code>
     *
     * @param url   请求的结果
     * @param map   请求参数
     * @param clazz 返回结果类型
     * @param <T>
     * @return
     */
    public static <T> T posthttps(String url, MultiValueMap<String, Object> map, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, map, clazz);
        return responseEntity.getBody();
    }

    /**
     * <code>https://www.programcreek.com/java-api-examples/index.php?class=org.springframework.web.client.RestTemplate&method=postForEntity</code>
     *
     * @param url   请求的结果
     * @param map   请求参数
     * @param clazz 返回结果类型
     * @param <T>
     * @return
     */
    public static <T> T post(String url, MultiValueMap<String, Object> map, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, map, clazz);
        return responseEntity.getBody();
    }

    /**
     * 上傳附件
     *
     * @param url      上傳url
     * @param filePath 本地文件路徑
     * @return
     */
    public static String uploadFile(String url, String filePath) {
        RestTemplate template = new RestTemplate();
        try (ByteArrayOutputStream fileBytes = new ByteArrayOutputStream()) {
            IOUtils.copy(new FileInputStream(filePath), fileBytes);
            ResponseEntity<String> entity = template.postForEntity(url, fileBytes.toByteArray(), String.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 各種請求
     * <code>https://www.programcreek.com/java-api-examples/index.php?class=org.springframework.web.client.RestTemplate&method=exchange</code>
     * <example>
     * HttpHeaders headers = new HttpHeaders();
     * headers.add("access","token");
     * MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
     * map.add("username","rwrwer");
     * HttpEntity<MultiValueMap<String,Object>> entity = new HttpEntity<>(map,headers);
     * String content = RestUtil.exchange(url, HttpMethod.POST,entity,String.class);
     * </example>
     *
     * @param url
     * @param httpMethod
     * @param httpEntity
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T exchange(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, clazz);
        return responseEntity.getBody();
    }
}
