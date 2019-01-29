package com.wy.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: Http请求处理工具类
 *
 * @author wangyuan
 * Date: Created at 2019-01-29 14:50
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 调用Veritrans的https接口时，设置使用TLSv1.2协议
     */
    private static SSLContext SSL_CONTEXT_FOR_TLSV1_2;

    /**
     * 设置Apache HttpClient默认使用的SSLConnectionSocketFactory
     */
    protected static SSLConnectionSocketFactory HTTP_DEFAULT_SSL_SOCKETFACTORY;

    static {
        try {
            SSL_CONTEXT_FOR_TLSV1_2 = SSLContext.getInstance("TLSv1.2");
            SSL_CONTEXT_FOR_TLSV1_2.init(null, null, new SecureRandom());
            HTTP_DEFAULT_SSL_SOCKETFACTORY = new SSLConnectionSocketFactory(SSL_CONTEXT_FOR_TLSV1_2,
                    new String[]{"TLSv1.1", "TLSv1.2"},
                    null,
                    null);
        } catch (Exception ex) {
            LOGGER.error("init TLSv1.2 SSLContext error, detail:", ex);
            throw new RuntimeException(ex);
        }

    }


    /**
     * 以x-www-form-urlencoded的格式请求数据
     *
     * @param url      目标网站url地址
     * @param paramMap post请求参数
     * @return http响应字符串
     */
    public static String postWithXwwwFormUrlEncoded(String url, Map<String, Object> paramMap) {
        List<NameValuePair> formParams = assembleNameValuePairList(paramMap);
        UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(requestEntity);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(HTTP_DEFAULT_SSL_SOCKETFACTORY).build();
        CloseableHttpResponse response;
        String httpResultStr = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            LOGGER.info("#CloseableHttpClient postWithXwwwFormUrlEncoded request params:", paramMap, ", statusLine:",
                    response.getStatusLine(), ", http response:", response);

            if (responseEntity != null) {
                httpResultStr = EntityUtils.toString(responseEntity);
                LOGGER.info("#CloseableHttpClient postWithXwwwFormUrlEncoded request params:", paramMap,
                        ", response:", httpResultStr);
            }
        } catch (Exception ex) {
            LOGGER.error("#CloseableHttpClient postWithXwwwFormUrlEncoded exception, url:", url, ", detail:", ex);
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException ioEx) {
                    LOGGER.error("#CloseableHttpClient close exception, url:", url, ", detail:", ioEx);
                }
            }
        }

        return httpResultStr;
    }


    /**
     * 将Map对象转换成x-www-form-urlencoded请求需要的格式
     *
     * @param paramMap 参数map
     * @return http请求参数体
     */
    private static List<NameValuePair> assembleNameValuePairList(Map<String, Object> paramMap) {
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        if (CollectionUtils.isEmpty(paramMap)) {
            return nameValuePairList;
        }

        for (Map.Entry entry : paramMap.entrySet()) {
            String entryValue = "";
            if (!StringUtils.isEmpty(entry.getValue())) {
                entryValue = entry.getValue().toString();
            }

            NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey().toString(), entryValue);
            nameValuePairList.add(nameValuePair);
        }

        return nameValuePairList;
    }

}
