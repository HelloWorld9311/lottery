package com.lottery.common.util;

import com.bruce.tool.common.util.EmojiRegexUtils;
import com.bruce.tool.common.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.common.util
 * @Description: String类型的校验工具
 * @author: wuyujia
 * @Date: 2017/10/31 12:06
 */
public class StringValidUtils {

    /**
     * 匹配中文的正则表达式
     */
    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    /**
     * 非法字符正则校验
     */
//    private static final Pattern LEGAL_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5|\\w|\\d|_|.|%|@|!|！|\\\\|-|+|,|\\s|，|。|：|“|”|?|？]+$");
    private static final Pattern LEGAL_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5\\w\\d_.%@!！\\\\|\\-,/,\\[\\]\\s，。:：“”?？]+$");

    /**
     * 纯数字校验
     */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    /**
     * 用户名正则表达式
     */
    private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,20}$");

    /**
     * 手机号正则表达式
     */
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^1[345789]\\d{9}$");

    /**
     * 字母数字正则表达式
     */
    private static final Pattern ALPHA_NUM = Pattern.compile("^[a-zA-Z0-9]+$");

    /**
     * 空串校验
     */
    private static final Pattern EMPTY = Pattern.compile("^\\s*$");

    /**
     * 是否包含中文的校验
     * @param s 需要校验的字符串
     * @return true 包含, false 不包含
     */
    public static boolean containChinese(String s) {
        if (s != null && !s.isEmpty()) {
            Matcher matcher = CHINESE_PATTERN.matcher(s);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含中文, 且长度小于length
     * @param s 需要校验的字符串
     * @param length 需要判断的长度
     * @return true 包含中文, 且长度小于length, false 不包含中文, 或长度大于length
     */
    public static boolean containChinese(String s, int length) {
        if (containChinese(s)) {
            return s.length() <= length;
        }
        return false;
    }

    /**
     * 字符串长度限制
     * @param s 待检验的字符串
     * @param length 限制长度 <=
     * @return false 未超出, true 超出
     */
    public static boolean overLengthLimit(String s, int length) {
        if (s != null) {
            return s.length() > length;
        }
        return false;
    }

    /**
     * 不包含中文
     * @param s 需要校验的字符串
     * @return true 不包含, false 包含
     */
    public static boolean notContainChinese(String s) {
        return !containChinese(s);
    }

    /**
     * 不包含非法字符校验 (除 中文, 英文, 数字, _ 以外的都属于非法字符)
     * @param s 需要校验的字符串
     * @return false 包含非法字符,
     */
    public static boolean notContainIllegalParameter(String s) {
        if (s == null || s.isEmpty()) {
            // 如果为空, 判断不包含非法字符
            return true;
        }
        Matcher matcher = LEGAL_PATTERN.matcher(s);
        if (matcher.matches()) {
            // 如果匹配上合法字符, 则不包含非法字符
            return true;
        }
        // 如果没匹配上, 则为非法字符
        return false;
    }

    /**
     * 只有整数的校验方法
     * @param s 待校验的字符串
     * @return
     * true 只有数字
     * false 不含有数字, 为空, 或者含有其他字符
     */
    public static boolean onlyIntegerNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        Matcher matcher = NUMBER_PATTERN.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 手机号校验
     * @param s
     * @return
     * true 是合法手机号
     * false 不是手机号或者为空
     */
    public static boolean isPhoneNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isUserName(String un){
        if(StringUtils.isBlank(un)){
            return false;
        }
        Matcher matcher = USER_NAME_PATTERN.matcher(un);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    /**
     * 仅仅包含字母数字的校验
     * @param s
     */
    public static boolean onlyAlphaNum (String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        Matcher matcher = ALPHA_NUM.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    /**
     * 空判断
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s) {
            return true;
        }
        if (EMPTY.matcher(s).matches()) {
            return true;
        }
        return false;
    }

    public static String urlDecode(String param){
        if (StringUtils.isNotBlank(param)){
            String result = URLDecoder.decode(param);
            if( StringUtils.isNotBlank(result) ){
                return result;
            }
        }
        return param;
    }

    public static String urlEncode(String param){
        if (StringUtils.isNotBlank(param)){
            String result = URLEncoder.encode(param);
            if( StringUtils.isNotBlank(result) ){
                return result;
            }
        }
        return param;
    }

    public static String urlEncodeOneByOne(String param){
        String[] list = param.split("");
        StringBuilder result = new StringBuilder();
        for ( String item : list ) {
            if( containChinese(item) ){
                result.append(urlEncode(item));
            }else{
                result.append(item);
            }
        }
        return result.toString();
    }

    public static boolean containsEmoji(String param){
        String regex = EmojiRegexUtils.getFullEmojiRegex();
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(param);

        return matcher.find();
    }



    public static void main(String[] args) {
        // ...
    }

}
