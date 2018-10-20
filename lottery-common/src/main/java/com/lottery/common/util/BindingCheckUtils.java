package com.lottery.common.util;

import cn.bqmart.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
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
public class BindingCheckUtils {

    private boolean ifCheckFlag = true;

    private final String CODE = "9999";

    public BindingCheckUtils() {

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
//        this.log(obj);
        // 如果关闭检查, 就跳过检查
        if (!ifCheckFlag) {
            return;
        }
        if (br != null) {
            if (br.hasFieldErrors()) {
                throw new BusinessException(this.CODE, br.getFieldError().getDefaultMessage());
            }
        }
//        try {
//            paramValid(request, obj, clazz);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            throw new BusinessException(this.CODE, "参数加密错误");
//        }
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

            if (value instanceof String) {
                String stringValue = value.toString();
                // 判断是否包含中文, 如果包含中文, 则需要进行编码
                if (StringValidUtils.containChinese(stringValue)) {
                    //如果有中文参数,需要urlencode来协调一致
                    try {
                        stringValue = URLEncoder.encode(stringValue, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        throw new BusinessException(this.CODE, "编码错误");
                    }
                    //这里分情况 先判断传递的是否有空格 包含空格的话 将+号转换一次 下面的代码 主要处理js与java encode不一样的情况
                    if (String.valueOf(value).contains(" ")) {
                        stringValue = stringValue.replaceAll("\\+", "%20");
                    }
                    if (StringUtils.isNotBlank(stringValue)) {
                        stringValue = stringValue.replaceAll("%21", "!");
                        stringValue = stringValue.replaceAll("%27", "'");
                        stringValue = stringValue.replaceAll("%28", "(");
                        stringValue = stringValue.replaceAll("%29", ")");
                        stringValue = stringValue.replaceAll("%7E", "~");
                    }
                }
                value = stringValue;
            }
            builder.append(stringFieldEntry.getKey()).append(equal).append(value).append(and);
        }
        builder.append(token);
        System.out.println(builder.toString());
        // 进行MD5
        String newSalt = Md5Utils.md5(builder.toString());
        return newSalt.equalsIgnoreCase(salt);
    }

    /**
     * 请求日志打印
     *
     * @param params
     */
    private void log(Object params) {
        LogUtils.requestLog(params);
    }
}
