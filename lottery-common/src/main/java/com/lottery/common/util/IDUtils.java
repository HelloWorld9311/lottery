package com.lottery.common.util;

/**
 * @Prject: springboot
 * @Package: util
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/8/25 16:29
 */
public class IDUtils {

    /**
     * 判断是否是身份证号
     * @param idNumber
     * @return
     */
    public static boolean isIdNumber(String idNumber) {
        if (!hasValue(idNumber)) {
            // 身份证为空
            return false;
        }
        if (!newIdNumber(idNumber)) {
            // 位数不对或者为15位老号
            return false;
        }
        if (!matchModel(idNumber)) {
            // 不是正确的身份证
            return false;
        }
//        Assert.isTrue(hasValue(idNumber), "身份证为空");
//        Assert.isTrue(newIdNumber(idNumber), "位数不对或者为15位老号");
//        Assert.isTrue(matchModel(idNumber), "错误");
        return true;
    }

    /**
     * 是否匹配身份证模型
     * @param idNumber
     * @return
     */
    private static boolean matchModel(String idNumber) {
        char[] chars = idNumber.substring(0, idNumber.length() - 1).toCharArray();
        int sum =
                Character.getNumericValue(chars[0]) * 7 +
                Character.getNumericValue(chars[1]) * 9 +
                Character.getNumericValue(chars[2]) * 10 +
                Character.getNumericValue(chars[3]) * 5 +
                Character.getNumericValue(chars[4]) * 8 +
                Character.getNumericValue(chars[5]) * 4 +
                Character.getNumericValue(chars[6]) * 2 +
                Character.getNumericValue(chars[7]) * 1 +
                Character.getNumericValue(chars[8]) * 6 +
                Character.getNumericValue(chars[9]) * 3 +
                Character.getNumericValue(chars[10]) * 7 +
                Character.getNumericValue(chars[11]) * 9 +
                Character.getNumericValue(chars[12]) * 10 +
                Character.getNumericValue(chars[13]) * 5 +
                Character.getNumericValue(chars[14]) * 8 +
                Character.getNumericValue(chars[15]) * 4 +
                Character.getNumericValue(chars[16]) * 2;
        int endIndex = sum % 11;
        char[] ends = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        char end = ends[endIndex];
        return idNumber.toCharArray()[chars.length] == end;
    }

    /**
     * 判断是否是新身份证ID, 18位, 如果是15校验不通过
     * @param idNumber
     * @return
     */
    private static boolean newIdNumber(String idNumber) {
        return idNumber.length() == 15 ? false : idNumber.length() == 18 ? true : false;
    }

    /**
     * 是否有值
     * @param idNumber
     * @return
     */
    private static boolean hasValue(String idNumber) {
        return !(idNumber == null || idNumber.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println(isIdNumber("510302199102070019"));
    }
}
