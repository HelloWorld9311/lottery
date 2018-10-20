package com.lottery.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author zhangboqing
 * @date 2017/11/14
 */
public class MathUtils {


    private static DecimalFormat format = new DecimalFormat("0.00");


    /**
     * 保留小数点后两位
     * @param decimal
     * @return
     */
    public static String formatDecimalToString(BigDecimal decimal) {

        if (Objects.isNull(decimal)) {
            return null;
        }
        return format.format(decimal);
    }

}
