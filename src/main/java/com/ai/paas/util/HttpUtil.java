package com.ai.paas.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.Constant;
import com.ai.paas.GeneralRuntimeException;

public class HttpUtil {
    private HttpUtil() {
        // 禁止私有化
    }

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 默认连接超时1分钟
     */
    private static final int CNN_TIME_OUT = 60000;
    /**
     * 默认请求超时时间3分钟
     */
    private static final int CNN_REQUEST_TIME_OUT = 180000;
    /**
     * 默认套接字超时时间5分钟
     */
    private static final int SOCKET_TIME_OUT = 300000;

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;

    static {

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(CNN_TIME_OUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(SOCKET_TIME_OUT);
        configBuilder.setCookieSpec(CookieSpecs.STANDARD_STRICT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(CNN_REQUEST_TIME_OUT);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送post请求
     * 
     * @param urlAddr           地址
     * @param param             参数对象
     * @param readCharset       返回流编码格式
     * @param cnnTimeOut        连接超时时间
     * @param cnnRequestTimeOut 请求超时时间
     * @param socketTimeOut     套接字超时时间
     * @return
     */
    public static String doPost(String urlAddr, StringEntity param, Map<String, String> header, String readCharset,
            int cnnTimeOut, int cnnRequestTimeOut, int socketTimeOut) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setConnectTimeout(cnnTimeOut).setConnectionRequestTimeout(cnnRequestTimeOut)
                    .setSocketTimeout(socketTimeOut).build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpPost httpPost = new HttpPost(urlAddr);
            httpPost.setEntity(param);
            if (null != header) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, readCharset);
            } else {
                throw new GeneralRuntimeException("" + response.getStatusLine().getStatusCode(),
                        response.getStatusLine().toString());
            }
        } catch (Exception e) {
            throw new GeneralRuntimeException("", e);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * post请求
     * 
     * @param urlAddr
     * @param param
     * @return
     */
    public static String doPost(String urlAddr, StringEntity param) {
        return doPost(urlAddr, param, null, Constant.CHARSET_UTF8, CNN_TIME_OUT, CNN_REQUEST_TIME_OUT, SOCKET_TIME_OUT);
    }

    public static String doPost(String urlAddr, Map<String, String> param, String paraCharset, String sendCharset,
            String readCharset, String contentType, int cnnTimeOut, int cnnRequestTimeOut, int socketTimeOut) {
        try {
            List<NameValuePair> pairList = new ArrayList<>(param.size());
            for (Map.Entry<String, String> entry : param.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                pairList.add(pair);
            }

            StringEntity entity = new UrlEncodedFormEntity(pairList, paraCharset);
            entity.setContentType(contentType);
            entity.setContentEncoding(sendCharset);
            return doPost(urlAddr, entity, null, readCharset, cnnTimeOut, cnnRequestTimeOut, socketTimeOut);
        } catch (UnsupportedEncodingException t) {
            throw new GeneralRuntimeException("", t);
        }
    }

    public static String doPost(String urlAddr, Map<String, String> param, String paraCharset, String sendCharset,
            String readCharset, String contentType, int cnnTimeOut) {
        return doPost(urlAddr, param, paraCharset, sendCharset, readCharset, contentType, cnnTimeOut, cnnTimeOut,
                cnnTimeOut);
    }

    public static String doPost(String urlAddr, Map<String, String> param, int cnnTimeOut) {
        return doPost(urlAddr, param, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8,
                Constant.CHARSET_UTF8, cnnTimeOut);

    }

    public static String doPost(String urlAddr, Map<String, String> param) {
        //
        return doPost(urlAddr, param, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8,
                Constant.CHARSET_UTF8, CNN_TIME_OUT, CNN_REQUEST_TIME_OUT, SOCKET_TIME_OUT);
    }

    public static String doGet(String urlAddr, Map<String, String> param, Map<String, String> header,
            String paraCharset, String sendCharset, String readCharset, int cnnTimeOut, int cnnRequestTimeOut,
            int socketTimeOut) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setConnectTimeout(cnnTimeOut).setConnectionRequestTimeout(cnnRequestTimeOut)
                    .setSocketTimeout(socketTimeOut).build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            boolean hasQM = urlAddr.indexOf('?') >= 0;
            StringBuilder sb = new StringBuilder();
            if (null != param) {
                int i = 0;
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    if (i == 0 && !hasQM) {
                        sb.append("?");
                    } else
                        sb.append("&");
                    sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), paraCharset));
                    i++;
                }
            }
            urlAddr += sb.toString();
            HttpGet httpGet = new HttpGet(urlAddr);
            if (null != header) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, readCharset);
            } else {
                throw new GeneralRuntimeException("" + response.getStatusLine().getStatusCode(),
                        response.getStatusLine().toString());
            }
        } catch (Exception e) {
            throw new GeneralRuntimeException("", e);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    public static String doGet(String urlAddr, Map<String, String> param, Map<String, String> header, int cnnTimeOut,
            int cnnRequestTimeOut, int socketTimeOut) {
        return doGet(urlAddr, param, header, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8,
                cnnTimeOut, cnnRequestTimeOut, socketTimeOut);
    }

    public static String doGet(String urlAddr, Map<String, String> param, Map<String, String> header) {
        return doGet(urlAddr, param, header, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8,
                CNN_TIME_OUT, CNN_REQUEST_TIME_OUT, SOCKET_TIME_OUT);
    }

    public static String doGet(String urlAddr, Map<String, String> param) {
        return doGet(urlAddr, param, null, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8, Constant.CHARSET_UTF8,
                CNN_TIME_OUT, CNN_REQUEST_TIME_OUT, SOCKET_TIME_OUT);
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     * 
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String urlAddr, Map<String, Object> param) {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", createSSLConnSocketFactory())
                .build();
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(urlAddr);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(param.size());
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Constant.CHARSET_UTF8));
            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, Constant.CHARSET_UTF8);
            } else {
                throw new GeneralRuntimeException("http status code: " + response.getStatusLine().getStatusCode(),
                        response.getStatusLine().toString());
            }
        } catch (Exception e) {
            throw new GeneralRuntimeException("", e);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    public static String doGetSSL(String urlAddr, Map<String, String> param) {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", createSSLConnSocketFactory())
                .build();
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;

        try {
            boolean hasQM = urlAddr.indexOf('?') >= 0;
            StringBuilder sb = new StringBuilder();
            if (null != param) {
                int i = 0;
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    if (i == 0 && !hasQM) {
                        sb.append("?");
                    } else
                        sb.append("&");
                    sb.append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue(), Constant.CHARSET_UTF8));
                    i++;
                }
            }
            urlAddr += sb.toString();
            httpGet = new HttpGet(urlAddr);
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, Constant.CHARSET_UTF8);
            } else {
                throw new GeneralRuntimeException("" + response.getStatusLine().getStatusCode(),
                        response.getStatusLine().toString());
            }
        } catch (Exception e) {
            throw new GeneralRuntimeException("", e);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * 创建SSL安全连接
     * 
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            TrustManager manager = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    if (chain == null) {
                        throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
                    }

                    if (chain.length <= 0) {
                        throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                    }

                    if (!(null != authType && ( authType.equalsIgnoreCase("RSA") || authType.contains("RSA")))) {
                        log.error("checkServerTrusted: AuthType is not RSA,{}", authType);
                        throw new CertificateException("checkServerTrusted: AuthType is not RSA, it is "+authType);
                    }
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[] {};
                }
            };
            SSLContext sslcontext = SSLContext.getInstance("TLSv1.2");
            sslcontext.init(null, new TrustManager[] { manager }, null);
            sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2", "SSLv3" }, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        } catch (GeneralSecurityException e) {
            log.error("error create ssl connection!", e);
        }
        return sslsf;
    }

    public static void main(String[] args) {

    }

}
