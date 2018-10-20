package com.lottery.common.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.Assert;

/**
 * @Prject: bqmart-sunshine
 * @Package: com.bqmart.common.util
 * @Description: JodaTime时间工具类, 使用注意, 格式必须严格符合 不允许多一个空格, 少一个空格的现象, 除了格式验证方法
 * 其它均没有做异常处理, 注意时间格式错误的异常处理
 * @author: wuyujia
 * @Date: 2017/8/8 00:21
 */
public class JodaTimeUtils {

    /**
     * 日期转时间戳
     * @param date String 类型时间格式
     * @param fmt 格式化参数
     * @return 返回long时间戳 精确到秒
     */
    public static long toTimeStamp(String date, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        return dateTime.getMillis() / 1000L;
    }


    /**
     * 日期转时间戳
     * @param date  String 类型时间格式
     * @param sourceFmt 来源格式
     * @param targetFmt 目标格式
     * @return
     */
    public static long toTimeStamp(String date, DateFormat sourceFmt, DateFormat targetFmt) {
        if (StringUtils.isBlank(date)) {
            return 0L;
        }

        String parse = parse(date, sourceFmt, targetFmt);
        return toTimeStamp(parse,targetFmt);
    }

    /**
     * 日期转时间戳
     * @param date String 类型时间格式
     * @param fmt 格式化参数
     * @return 返回整型时间戳
     */
    public static int toIntTimeStamp(String date, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        return (int) (dateTime.getMillis() / 1000L);
    }

    /**
     * 时间戳转时间
     * @param timestamp 整型 时间戳
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String timestampToString(Integer timestamp, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = new DateTime(timestamp.longValue() * 1000L);
        return dateTime.toString(formatter);
    }

    /**
     * 时间戳转时间
     * @param timestamp 整型 时间戳
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String timestampToString(Long timestamp, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = new DateTime(timestamp * 1000L);
        return dateTime.toString(formatter);
    }

    /**
     * 日期格式校验
     * 一致返回true, 不一致返回false
     * 格式严格校验, 多一个空格都不行 注意使用
     * @param date String 类型时间格式
     * @param fmt 格式化参数
     * @return 返回参数校验结果
     */
    public static boolean formatCheck(String date, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        try {
            formatter.parseDateTime(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 解析日期为对应格式
     * @param date  String 类型时间格式
     * @param sourceFmt 来源格式
     * @param targetFmt 目标格式
     * @return 返回字符串时间
     */
    public static String parse(String date, DateFormat sourceFmt, DateFormat targetFmt) {
        DateTimeFormatter source = getFormatter(sourceFmt);
        DateTime dateTime = source.parseDateTime(date);
        DateTimeFormatter target = getFormatter(targetFmt);
        return dateTime.toString(target);
    }

    /**
     * 加上天数
     * @param date String 类型时间格式
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String plusDays(String date, int days, DateFormat fmt) {
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        DateTime plusDays = dateTime.plusDays(days);
        return plusDays.toString(formatter);
    }

    /**
     * 获取昨天的日期
     * @param date String 类型时间格式
     * @param fmt 格式化参数
     * @see DateFormat
     * @return 返回字符串时间
     */
    public static String yesterday(String date, DateFormat fmt) {
        Assert.notNull(date, "日期不能为空");
        Assert.notNull(fmt, "格式化参数不能为空");
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        // 变成昨天
        DateTime yesterday = dateTime.plusDays(-1);
        return yesterday.toString(formatter);
    }

    /**
     * 直接获取昨天的日期
     * @return String类型字符串的时间
     */
    public static String yesterday() {
        String date = DateTime.now().toString(getFormatter(DateFormat.DATE_FORMAT));
        return yesterday(date);
    }

    /**
     * 根据目标日期获取目标昨天的日志
     * @param date String 类型时间
     * @return 返回字符串时间
     */
    public static String yesterday(String date) {
        return yesterday(date, DateFormat.DATE_FORMAT);
    }

    /**
     * 获取带格式的昨天的日期
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String yesterday(DateFormat fmt) {
        String date = DateTime.now().toString(getFormatter(fmt));
        return yesterday(date, fmt);
    }

    /**
     * 获取这个星期的第一天
     * @param date String 类型的日期格式
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String lastWeekFirstDay(String date, DateFormat fmt) {
        Assert.notNull(date, "日期不能为空");
        Assert.notNull(fmt, "格式化参数不能为空");
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        DateTime lastWeek = dateTime.plusWeeks(-1);
        int dayOfWeek = lastWeek.getDayOfWeek();
        DateTime lastWeekFirstDay = lastWeek.plusDays(1 - dayOfWeek);
        return lastWeekFirstDay.toString(formatter);
    }

    /**
     * 获取上周周一的第一天
     * @param date String 类型的日期格式
     * @return 返回字符串时间
     */
    public static String lastWeekFirstDay(String date) {
        return lastWeekFirstDay(date, DateFormat.DATE_FORMAT);
    }

    /**
     * 获取上周的第一天
     * @return 返回字符串时间
     */
    public static String lastWeekFirstDay() {
        DateTimeFormatter formatter = getFormatter(DateFormat.DATE_FORMAT);
        String now = DateTime.now().toString(formatter);
        return lastWeekFirstDay(now);
    }

    /**
     * 获取上周的最后一天
     * @param date String 类型的日期格式
     * @param fmt 格式化参数
     * @return 返回字符串时间
     */
    public static String lastWeekLastDay(String date, DateFormat fmt) {
        Assert.notNull(date, "日期不能为空");
        Assert.notNull(fmt, "格式化参数不能为空");
        DateTimeFormatter formatter = getFormatter(fmt);
        DateTime dateTime = formatter.parseDateTime(date);
        DateTime lastWeek = dateTime.plusWeeks(-1);
        int dayOfWeek = lastWeek.getDayOfWeek();
        DateTime lastWeekLastDay = lastWeek.plusDays(7 - dayOfWeek);
        return lastWeekLastDay.toString(formatter);
    }

    /**
     * 获取上周的最后一天
     * @param date String 类型的日期格式
     * @return 返回字符串时间
     */
    public static String lastWeekLastDay(String date) {
        return lastWeekFirstDay(date, DateFormat.DATE_FORMAT);
    }

    /**
     * 获取上周的最后一天
     * @return 返回字符串时间
     */
    public static String lastWeekLastDay() {
        DateTimeFormatter formatter = getFormatter(DateFormat.DATE_FORMAT);
        String now = DateTime.now().toString(formatter);
        return lastWeekLastDay(now);
    }

    /**
     * 获取格式解析器
     * @param fmt 格式化参数
     * @see DateFormat
     * @return 返回字符串时间
     */
    private static DateTimeFormatter getFormatter(DateFormat fmt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(fmt.getFmt());
        return dateTimeFormatter;
    }

    public static void main(String[] args) {
//        Pattern compile = Pattern.compile("[\\\\.0-9]+");
//        Matcher matcher = compile.matcher("[\"促销商品8.1折\"]");
//        if (matcher.find()) {
//            String group = matcher.group();
//            System.out.println(group);
//        }

//        System.out.println(timestampToString(longTimestamp(),DateFormat.DATETIME_FORMAT));
        System.out.println(JodaTimeUtils.toTimeStamp(null, JodaTimeUtils.DateFormat.DATETIME_FORMAT, JodaTimeUtils.DateFormat.DATE_FORMAT));

    }

    /**
     * 时间戳
     * @return int类型 精确到秒
     */
    public static Integer intTimestamp() {
        long l = System.currentTimeMillis() / 1000;
        return (int) l;
    }

    /**
     * 时间戳
     * @return int类型 精确到秒
     */
    public static Long longTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }


    /**
     * 日期格式枚举类
     */
    public enum DateFormat {


        /**
         * 格式化为 yyyyMMddHHmmss
         */
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

        /**
         * 格式化为 yyyy-MM
         */
        YYYY_MM_FORMAT("yyyy-MM"),

        /**
         * 格式化为 yyyy-MM-dd
         */
        DATE_FORMAT("yyyy-MM-dd"),

        /**
         * 格式化为 yyyy-MM-dd HH:mm:ss
         */
        DATETIME_FORMAT("yyyy-MM-dd HH:mm:ss"),

        /**
         * 格式化为 yyyy-MM-dd HH:mm
         */
        YYYY_MM_DD_HH_MM_FORMAT("yyyy-MM-dd HH:mm");

        private String fmt;

        DateFormat(String fmt) {
            this.fmt = fmt;
        }

        public String getFmt() {
            return fmt;
        }
    }
}
