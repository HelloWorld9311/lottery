package com.lottery.common.util;

import cn.bqmart.common.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright © 2017北京联合倍全电子商务有限公司. All rights reserved.
 *
 * @Prject: ad_system
 * @Package: com.bqmart.common.util
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/7/19 15:58
 * @version: V1.0
 */
@Component
@SuppressWarnings("all")
public class ValidateUtils {
    private static final Logger logger = LoggerFactory.getLogger(ValidateUtils.class);
    private boolean ifCheckFlag = true;

    private final String CODE = "9999";

    public ValidateUtils() {

    }

    /**
     * 参数错误检查
     *
     * @param request
     * @param obj
     * @param clazz
     * @param br
     * @param <T>
     */
    public <T> void check(HttpServletRequest request, Object obj, Class<T> clazz, BindingResult br) throws BusinessException {
        // 日志还是要打印滴....
        logger.info("RequestLog: url->[{}];params->[{}];", request.getRequestURL().toString(), JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue));
        // 如果关闭检查, 就跳过检查
        if (!ifCheckFlag) {
            return;
        }
        if (br != null) {
            if (br.hasFieldErrors()) {
                throw new BusinessException(this.CODE, br.getFieldError().getDefaultMessage());
            }
        }
        try {
            paramValid(request, obj, clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException(this.CODE, "参数加密错误");
        }
    }

    /**
     * 参数校验
     *
     * @param obj
     * @param clazz
     * @param <T>
     */
    public <T> void paramValid(HttpServletRequest request, Object obj, Class<T> clazz) throws IllegalAccessException, BusinessException {
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            // 没有参数 应该不会出现这样的问题
            return;
        }
        TreeMap<String, Field> sortedFields = new TreeMap<>();
        for (Field field : fields) {
            String serialVersionUID="serialVersionUID";
            if(field.getName().equals(serialVersionUID)){
                continue;
            }
            field.setAccessible(true);
            sortedFields.put(field.getName(), field);
        }
        boolean b = validSalt(obj, sortedFields);
        if (!b) {
            throw new BusinessException(this.CODE, "参数校验失败");
        }
    }

    private boolean validSalt(Object obj, TreeMap<String, Field> sortedFields) throws IllegalAccessException, BusinessException {
        StringBuilder builder = new StringBuilder();
        String salt = null;
        String osalt = null;
        // token 可能需要改成动态获取, 策略待定
        String token = "6qvlkh6khz";
        String equal = "=";
        String and = "&";
        for (Map.Entry<String, Field> stringFieldEntry : sortedFields.entrySet()) {
            Object value = stringFieldEntry.getValue().get(obj);
            // 拼接字符串参数
            if (value == null) {
                // 为空参数不参与加密
                continue;
            }

            if (stringFieldEntry.getKey().equalsIgnoreCase("salt")) {
                // 加密校验本身不参与加密
                salt = value.toString();
                continue;
            }

            if (stringFieldEntry.getKey().equalsIgnoreCase("osalt")) {
                // 加密校验本身不参与加密
                osalt = value.toString();
                continue;
            }

            if (value instanceof String) {
                String stringValue = value.toString();
                if( StringValidUtils.containsEmoji(stringValue) ){
                    throw new BusinessException(this.CODE, "文本输入框不支持moji表情");
                }
                if( !StringValidUtils.notContainIllegalParameter(stringValue) ){
                    throw new BusinessException(this.CODE, "文本输入框只支持部分特殊字符(包含:_.%@!！\\-/,[] ，。:：“”?？)");
                }
                // 判断是否包含中文, 如果包含中文, 则需要进行编码
                if (StringValidUtils.containChinese(stringValue)) {
                    stringValue = StringValidUtils.urlEncodeOneByOne(stringValue);
                }
                value = stringValue;
            }
            builder.append(stringFieldEntry.getKey()).append(equal).append(value).append(and);
        }
//        builder.append(token);
//        logger.info("before MD5 : " + builder);
//        // 进行MD5
//        String newSalt = Md5Utils.md5(builder.toString());
//        logger.info("after MD5 : " + newSalt);
//        logger.info("from web MD5 : " + salt);
//        return newSalt.equalsIgnoreCase(salt);
        return true;
    }
}
