package com.lottery.common.util;


import java.util.Random;

/**
 * @author zhangboqing
 * @date 2017/11/8
 */
public class CodeUtils {

    /** 获取费用订单号 FC-*/
    public static String getFeesOrderCode() {
        StringBuffer sb = new StringBuffer();
        sb.append("FC-").append(JodaTimeUtils.timestampToString(System.currentTimeMillis()/1000,JodaTimeUtils.DateFormat.YYYYMMDDHHMMSS)).append(getRandomFour());
        return sb.toString();
    }

    /**
     * 生成报事报修支付订单号
     * @return
     */
    public static String getRepairOrderCode() {
        StringBuffer buff = new StringBuffer();
        buff.append("RP-").append(JodaTimeUtils.timestampToString(System.currentTimeMillis()/1000,JodaTimeUtils.DateFormat.YYYYMMDDHHMMSS)).append(getRandomFour());
        return buff.toString();
    }


    /**
     * 获取4位随机数
     * @return
     */
    private static String getRandomFour() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        sb.append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10));
        return sb.toString();
    }
}
