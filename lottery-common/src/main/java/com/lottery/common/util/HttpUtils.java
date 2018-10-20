package com.lottery.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

public class HttpUtils {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    public static int readTimeout = 30000;
    public static int connectionTimeout = 30000;
    public static String defaultEncoding = "UTF-8";
    public static Integer POST_TYPE_FORM = 1;
    public static Integer POST_TYPE_JSON = 2;

    /**
     * 发送get请求
     * 自动对参数中的字符串类型进行编码
     */
    public static String httpGet(String urlStr, Map<String, Object> param,
                                 String paramEncoding) {
        if (paramEncoding == null || "".equals(paramEncoding.trim())) {
            paramEncoding = defaultEncoding;
        }
        try {
            StringBuffer paramStr = new StringBuffer();
            if (param != null && param.size() > 0) {
                for (String key : param.keySet()) {
                    Object value = param.get(key);
                    if (value == null) {
                        value = "";
                    }
                    if (value instanceof String) {
                        value = URLEncoder.encode(value.toString(), paramEncoding);
                    }
                    paramStr.append("&").append(key).append("=").append(value);
                }
            }
            return httpGet(urlStr, paramStr.length() > 1 ? paramStr.substring(1) : null, paramEncoding, null, null);
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        }
        return null;
    }

    /**
     * 发送get请求
     */
    public static String httpGet(String urlStr, String param,
                                 String paramEncoding, String resultEncoding, Integer conTimeout) {
        if (paramEncoding == null || "".equals(paramEncoding.trim())) {
            paramEncoding = defaultEncoding;
        }
        if (resultEncoding == null || "".equals(resultEncoding.trim())) {
            resultEncoding = defaultEncoding;
        }
        HttpURLConnection con = null;
        BufferedReader reader = null;
        try {
            // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
            if (param != null && !"".equals(param.trim())) {
                if (urlStr.contains("?")) {
                    urlStr += "&" + param;
                } else {
                    urlStr += "?" + param;
                }
            }
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            if (conTimeout == null) {
                con.setConnectTimeout(connectionTimeout);
            } else {
                con.setConnectTimeout(conTimeout.intValue() * 1000);
            }
            con.setReadTimeout(readTimeout);
            con.connect();
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), resultEncoding));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                logger.error("GET:" + urlStr, e);
            }
            try {
                con.disconnect();
            } catch (Exception e) {
                logger.error("GET:" + urlStr, e);
            }
        }
        return null;
    }


    /**
     * 发送post请求
     */
    public static String httpPost(String urlStr, Map<String, Object> param,
                                  String paramEncoding) {
        if (paramEncoding == null || "".equals(paramEncoding.trim())) {
            paramEncoding = defaultEncoding;
        }
        try {
            StringBuffer paramStr = new StringBuffer();
            if (param != null && param.size() > 0) {
                for (String key : param.keySet()) {
                    Object value = param.get(key);
                    if (value == null) {
                        value = "";
                    }
                    paramStr.append("&").append(key).append("=").append(value);
                }
            }
            return httpPost(urlStr, paramStr.length() > 1 ? paramStr.substring(1) : null, POST_TYPE_FORM, paramEncoding, null, null);
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        }
        return null;
    }

    /**
     * 发送post请求
     */
    public static String httpPost(String urlStr, String params) {
        return httpPost(urlStr, params, HttpUtils.POST_TYPE_JSON, "UTF-8", null, null);
    }

    /**
     * 发送post请求
     */
    public static String httpPost(String urlStr, String params,
                                  String paramEncoding, String resultEncoding, Integer conTimeout) {
        return httpPost(urlStr, params, POST_TYPE_FORM, paramEncoding, resultEncoding, conTimeout);
    }

    /**
     * 发送post请求
     */
    public static String httpPost(String urlStr, String params, Integer type,
                                  String paramEncoding, String resultEncoding, Integer conTimeout) {
        HttpURLConnection urlConn = null;
        BufferedReader in = null;
        try {
            if (paramEncoding == null || paramEncoding.trim().equals("")) {
                paramEncoding = defaultEncoding;
            }
            if (resultEncoding == null || resultEncoding.trim().equals("")) {
                resultEncoding = defaultEncoding;
            }
            StringBuffer result = new StringBuffer();
            URL destURL = new URL(urlStr);
            urlConn = (HttpURLConnection) destURL.openConnection();
            if (POST_TYPE_FORM.equals(type)) {
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + paramEncoding.toLowerCase());
            } else if (POST_TYPE_JSON.equals(type)) {
                urlConn.setRequestProperty("Content-Type", "application/json; charset=" + paramEncoding.toLowerCase());
            }
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("POST");
            if (conTimeout == null) {
                urlConn.setConnectTimeout(connectionTimeout);
            } else {
                urlConn.setConnectTimeout(conTimeout.intValue() * 1000);
            }


            urlConn.setReadTimeout(readTimeout);
            OutputStream os = urlConn.getOutputStream();
            if (params != null) {
                os.write(params.getBytes(paramEncoding));
            }
            in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream(), resultEncoding));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line + "\n");
            }
            return result.toString();
        } catch (Exception e) {
//            ExceptionUtils.log(logger,e);
            logger.error("POST:" + urlStr, e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("POST:" + urlStr, e);
            }
            try {
                urlConn.disconnect();
            } catch (Exception e) {
                logger.error("POST:" + urlStr, e);
            }
        }
        return null;
    }
    /*public static String httpPostByYunZhiXun(String urlStr, Map<String,Object> param , String time) {
    	HttpURLConnection urlConn = null;
    	BufferedReader in = null;
    	try {
    		JSON obj = (JSON) JSONObject.toJSON(param);
    		String   params=obj.toString();
//              String time=DateUtils.formatDateToSecond2(new Date(System.currentTimeMillis()));
   	        String sigParameter= EncryptUtil.encryptMD5(SystemConfig.YUNZHIXUN_ACCOUNTSID+ SystemConfig.YUNZHIXUN_AUTHTOKEN+time).toUpperCase();
    		StringBuffer result = new StringBuffer();
    		URL destURL = new URL(urlStr);
    		urlConn = (HttpURLConnection) destURL.openConnection();
    		urlConn.setRequestProperty("SoftVersion",  SystemConfig.YUNZHIXUN_VERSION);
    		urlConn.setRequestProperty("accountSid", SystemConfig.YUNZHIXUN_ACCOUNTSID);
    		urlConn.setRequestProperty("SigParameter", sigParameter);
    		urlConn.setRequestProperty("Accept", "application/json;");
    		urlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
    		urlConn.setRequestProperty("Authorization", new String(Base64.encodeBase64((SystemConfig.YUNZHIXUN_ACCOUNTSID+":"+time).getBytes())));
    		urlConn.setDoOutput(true);
    		urlConn.setDoInput(true);
    		urlConn.setAllowUserInteraction(false);
    		urlConn.setUseCaches(false);
    		urlConn.setRequestMethod("POST");
    		urlConn.setConnectTimeout(connectionTimeout);
    		
    		
    		urlConn.setReadTimeout(readTimeout);
    		OutputStream os = urlConn.getOutputStream();
    		if (params != null) {
    			os.write(params.getBytes("UTF-8"));
    		}
    		in = new BufferedReader(new InputStreamReader(
    				urlConn.getInputStream(), "UTF-8"));
    		String line;
    		while ((line = in.readLine()) != null) {
    			result.append(line + "\n");
    		}
    		return result.toString();
    	} catch (Exception e) {
//            ExceptionUtils.log(logger,e);
    		logger.error("POST:" + urlStr, e);
    	} finally {
    		try {
    			in.close();
    		} catch (IOException e) {
    			logger.error("POST:" + urlStr, e);
    		}
    		try {
    			urlConn.disconnect();
    		} catch (Exception e) {
    			logger.error("POST:" + urlStr, e);
    		}
    	}
    	return null;
    }*/

    /**
     * 下载文件，并重命名（不改变后缀）
     *
     * @param urlStr
     * @param fileName
     */
    public static void downloadFile(String urlStr, String fileName) {
        HttpURLConnection con = null;
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            String disposition = con.getHeaderField("Content-Disposition");
            String suffix = ".unknow";
            if (disposition != null && disposition.contains("filename")) {
                suffix = disposition.replaceAll("\"", "").substring(disposition.lastIndexOf(".") - 1);
            }
            input = new BufferedInputStream(con.getInputStream());
            output = new BufferedOutputStream(new FileOutputStream(fileName + suffix));
            int len = 3096;
            byte[] buffer = new byte[len];
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                logger.error("GET:" + urlStr, e);
            }
            try {
                output.close();
            } catch (Exception e) {
                logger.error("GET:" + urlStr, e);
            }
            try {
                con.disconnect();
            } catch (Exception e) {
                logger.error("GET:" + urlStr, e);
            }
        }
    }

    /**
     * 下载文件，并重命名（不改变后缀）
     *
     * @param urlStr
     */
    public static byte[] downloadFile(String urlStr) {
        HttpURLConnection con = null;
        BufferedInputStream input = null;
        ByteArrayOutputStream output = null;
        byte[] bytes = null;
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            String contentType = con.getContentType();
            logger.debug("contentType >>"+contentType);
            input = new BufferedInputStream(con.getInputStream());
            output = new ByteArrayOutputStream();
            int len = 4096;
            byte[] buffer = new byte[len];
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            bytes = output.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return bytes;
    }

    /**
     * 下载文件,返回二进制流和contentType
     *
     * @param urlStr
     */
    public static Map<String ,Object> downloadFileFromWeChat(String urlStr) {
        HttpURLConnection con = null;
        BufferedInputStream input = null;
        ByteArrayOutputStream output = null;
        byte[] bytes = null;
        Map<String,Object> result = new TreeMap<>();
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.connect();

            String contentType = con.getContentType();
            logger.debug("contentType >>"+contentType);

            input = new BufferedInputStream(con.getInputStream());
            output = new ByteArrayOutputStream();
            int len = 4096;
            byte[] buffer = new byte[len];
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            logger.debug("response >>"+output.toString());

            result.put("contentType",contentType);
            bytes = output.toByteArray();
            if(StringUtils.isNotBlank(contentType) && contentType.startsWith("image")){
//            if("image/jpeg".equals(contentType)){
                result.put("bytes",bytes);
                String disposition = con.getHeaderField("Content-Disposition");
                String filename = "";
                if (disposition != null && disposition.contains("filename")) {
                    filename = disposition.replaceAll("\"", "").substring(disposition.lastIndexOf("filename="));
                }
                result.put("filename",filename);

            }else if("application/json".equals(contentType) || "text/plain".equals(contentType)){
                JSONObject jsonObject = JSONObject.parseObject(output.toString());
                //TODO 两种情况,一种是视频文件,key为video_url,value为对应的值
                //一种是错误消息
                result.putAll(jsonObject);

            }else{
                //TODO 其他类型待扩展
            }

            return result;
        } catch (Exception e) {
            logger.error("GET:" + urlStr, e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return result;
    }


    public static String postByToken(Map<String, String> parameter, String url, String token) throws IOException {
        Set<Entry<String, String>> set = parameter.entrySet();

        Iterator<Entry<String, String>> iterator = set.iterator();
        List<String> list = new ArrayList<String>();

        String digest = "";

        while (iterator.hasNext()) {
            Entry<String, String> data = iterator.next();
            list.add(data.getKey() + "=" + data.getValue());
        }
        Collections.sort(list);
        NameValuePair[] param = new NameValuePair[(list.size()) + 1];
        for (int i = 0; i < list.size(); i++) {
            String[] temp = list.get(i).split("=");
            param[i] = new NameValuePair(temp[0], temp[1]);
            digest += list.get(i) + "&";
        }

        digest += token;
        System.out.println("new digest sort" + digest);
        String salt = Md5Utils.encryptMD5(digest,"UTF-8");
        param[(list.size())] = new NameValuePair("salt", salt);
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("apt-version", "1.0");
        post.setRequestHeader("client", "mrzjClient");
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");
        post.setRequestBody(param);
        post.releaseConnection();
        int statusCode = client.executeMethod(post);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + post.getStatusLine());
        }
        // 读取内容
        byte[] responseBody = post.getResponseBody();
        // 处理内容
        return (new String(responseBody, "utf-8"));
    }

    public static String getByToken(Map<String, String> parameter, String url, String token) throws IOException {
        Set<Entry<String, String>> set = parameter.entrySet();

        Iterator<Entry<String, String>> iterator = set.iterator();
        List<String> list = new ArrayList<String>();

        String digest = "";

        while (iterator.hasNext()) {
            Entry<String, String> data = iterator.next();
            list.add(data.getKey() + "=" + data.getValue());
        }
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            digest += list.get(i) + "&";
        }
        String path = digest;

        digest += token;
        String salt = Md5Utils.encryptMD5(digest,"UTF-8");

        path += "salt=" + salt;
        GetMethod method = new GetMethod(URIUtil.encodeQuery(url + "?" + path));
        method.setRequestHeader("apt-version", "1.0");
        method.setRequestHeader("client", "mrzjClient");
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");
        HttpClient client = new HttpClient();
        int statusCode = client.executeMethod(method);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + method.getStatusLine());
        }
        // 读取内容
        byte[] responseBody = method.getResponseBody();
        // 处理内容
        return (new String(responseBody, "utf-8"));
    }


}
