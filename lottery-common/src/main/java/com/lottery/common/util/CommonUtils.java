package com.lottery.common.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @北京联合倍全电子商务有限公司
 * @author: simazilin  @Email: fgihxq@163.com
 * @Date: 2017-11-10 11:34
 * @Project: 阳光物业V1.0
 * @Description:
 */
public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    private static final String DOT = ",";

    /**
     * @return List转string ","分割
     */
    public static String integerListToStringByDot(List<Integer> list) {
        if (list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer ob : list) {
            sb.append(ob).append(DOT);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * string 转 list
     *
     * @param stringDot string
     * @return list
     */
    public static List<Integer> stringToIntegerListByDOT(String stringDot) {
        String[] projectIds = stringDot.split(DOT);
        List<Integer> list = new ArrayList<>();
        for (String projectId : projectIds) {
            list.add(Integer.valueOf(projectId));
        }
        return list;
    }


    /**
     * 截取字符串，多余用... 处理
     *
     * @param param 输入参数
     * @param num   截取大小
     * @return 返回参数
     */
    public static String stringSub(String param, Integer num) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(param)) {
            if (param.length() > num) {
                return stringBuilder.append(param.substring(0, num - 1)).append("...").toString();
            } else {
                return param;
            }
        } else {
            return "";
        }
    }

    /**
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws Exception exception
     * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert1(String str) throws Exception {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(
                        sb,
                        "[["
                                + java.net.URLEncoder.encode(matcher.group(1),
                                "UTF-8") + "]]");
            } catch (Exception e) {
                logger.error("emojiConvert error");
                throw e;
            }
        }
        matcher.appendTail(sb);
        logger.debug("emojiConvert " + str + " to " + sb.toString()
                + ", len：" + sb.length());
        return sb.toString();
    }

    /**
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException exception
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     */
    public static String emojiRecovery2(String str) throws Exception {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (Exception e) {
                logger.error("emojiRecovery error", e);
                throw e;
            }
        }
        matcher.appendTail(sb);
        logger.debug("emojiRecovery " + str + " to " + sb.toString());
        return sb.toString();
    }


}
