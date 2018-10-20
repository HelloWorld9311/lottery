package com.lottery.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 时间戳转换为时间字符串
     * @param timeMills
     * @param pattern
     * @return
     */
    public static String parseDate(Long timeMills, String pattern) {
        Date date = new Date(timeMills);
        return DateFormatUtils.format(date, pattern);
    }

}
