package com.lottery.common.util;


import cn.bqmart.common.config.CommonRemoteResult;
import cn.bqmart.common.constant.BusinessCode;
import cn.bqmart.common.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RemoteServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(RemoteServiceUtil.class);


    @Autowired
    private RestTemplate restTemplate;

    //private RestTemplate restTemplate =new RestTemplate();

    private HttpEntity<String> getHttpEntity(Object obj) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return new HttpEntity<>(JSONUtil.objToStr(obj), headers);
    }

    /**
     * pos请求
     *
     * @param url
     * @param param
     * @return
     */
    public String post(String url, Object param) {
        logger.info("调用第三方post服务请求URL。{}", url);
        logger.info("调用第三方post服务请求参数。{}", JSONUtil.objToStr(param));
        HttpEntity<String> formEntity = getHttpEntity(param);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        logger.debug("调用第三方post服务返回结果。{}", result);
        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public String get(String url, Object param) {
        logger.info("调用第三方get服务请求URL。{}", url);
        logger.info("调用第三方get服务请求参数。{}", JSONUtil.objToStr(param));
        String result = restTemplate.getForObject(Helper.urlParams(url, param), String.class);
        logger.info("调用第三方get服务返回结果。{}", result);
        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public String getPhp(String url, Object param) throws BusinessException {
        logger.info("调用第三方get服务请求URL。{}", url);
        logger.info("调用第三方get服务请求参数。{}", JSONUtil.objToStr(param));
        String result = restTemplate.getForObject(Helper.urlParams(url, param), String.class);
        logger.info("调用第三方get服务返回结果。{}", result);
        JSONObject jsonObj = JSONObject.parseObject(result);
        if (null != jsonObj) {
            String code ="0";
            CommonRemoteResult commonRemoteResult = jsonObj.getObject("error", CommonRemoteResult.class);
            if (code.equals(commonRemoteResult.getCode())) {
                String data = JSONUtil.objToStr(jsonObj.get("result"), true);
                if (data.equals("{}") || data.equals("[]")) {
                    return null;
                }
                return data;
            }
            throw new BusinessException(commonRemoteResult.getCode(), commonRemoteResult.getMessage());
        }
        throw new BusinessException(BusinessCode.REQUEST_ERROR_CODE, "请求失败");
    }

}
