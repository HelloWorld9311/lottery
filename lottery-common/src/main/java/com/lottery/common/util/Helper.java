package com.lottery.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.*;


public class Helper {

    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    /**
     * 对象转map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            Class superClaz = obj.getClass();
            List<Field> fieldList = new ArrayList(Arrays.asList(superClaz.getDeclaredFields()));
            while ((superClaz = superClaz.getSuperclass()) != null) {
                fieldList.addAll(Arrays.asList(superClaz.getDeclaredFields()));
            }
            for (Field field : fieldList) {
                field.setAccessible(true);

                Object target = field.get(obj);
                String name = field.getName();
                if (null == target) {
                    continue;
                } else if ("serialVersionUID".equalsIgnoreCase(name)) {
                    continue;
                }


                map.put(field.getName(), field.get(obj));
            }
        } catch (Throwable e) {
            logger.error("真实异常打印:{}", e);
        }
        return map;
    }

    /**
     * 对象转MultiValueMap
     *
     * @param obj
     * @return
     */
    public static MultiValueMap<String, Object> objectToMultiValueMap(Object obj) {
        if (obj == null) {
            return null;
        }

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        try {
            Class superClaz = obj.getClass();
            List<Field> fieldList = new ArrayList(Arrays.asList(superClaz.getDeclaredFields()));
            while ((superClaz = superClaz.getSuperclass()) != null) {
                fieldList.addAll(Arrays.asList(superClaz.getDeclaredFields()));
            }
            for (Field field : fieldList) {
                field.setAccessible(true);

                Object target = field.get(obj);
                String name = field.getName();
                if (null == target) {
                    continue;
                } else if ("serialVersionUID".equalsIgnoreCase(name)) {
                    continue;
                }


                multiValueMap.add(field.getName(), field.get(obj));
            }
        } catch (Throwable e) {
            logger.error("真实异常打印:{}", e);
        }
        return multiValueMap;
    }

    /**
     * 拼装get的url参数
     *
     * @param url
     * @param obj
     * @return
     */
    public static String urlParams(String url, Object obj) {
        Map<String, Object> params = objectToMap(obj);
        if (null == params || params.isEmpty()) {
            return url;
        }

        url = url.concat("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().equals("")) {
                url = url.concat(entry.getKey()).concat("=").concat(entry.getValue().toString()).concat("&");
            }
        }
        if (url.endsWith("&") || url.endsWith("?")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;

    }

}