package com.lottery.common.util;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangjin on 16/10/28.
 */
public class SqlProviderUtils {

    private static final Logger logger = LoggerFactory.getLogger(SqlProviderUtils.class);
    /**
     * 分页统一返回结果
     */

    public static String page(SQL sql, Integer page, Integer pageSize) {
        String sqlResult = sql.toString();
        // 返回结果
        if (null == page || null == pageSize) {
            logger.debug("通过Provider拼接好的SQL-->{}", sqlResult);
            return sqlResult;
        } else {
            logger.debug("通过Provider拼接好的SQL-->{}", sqlResult + "\nLIMIT #{page},#{pageSize} ");
            return sqlResult + "\nLIMIT #{page},#{pageSize} ";
        }
    }


    public static String getNextPageSql(SQL sql, Integer page, Integer pageSize) {
        String sqlResult = sql.toString();
        // 返回结果
        if (null == page || null == pageSize) {
            logger.debug("通过Provider拼接好的SQL-->{}",sqlResult);
            return sqlResult;
        } else {
            logger.debug("通过Provider拼接好的SQL-->{}",sqlResult + "\nLIMIT " + page + "," + pageSize);
            Integer start = (page - 1 ) * pageSize;
            Integer nextSize = pageSize + 1;
            return sqlResult + "\nLIMIT "+ start + "," + nextSize;
        }
    }


    //这个方法 只是 查询交易记录的需求用到 其他的需求暂时用不到
    public static String getSpecialPageSql(SQL sql, Integer page, Integer pageSize) {
        String sqlResult = sql.toString();
        // 返回结果
        if (null == page || null == pageSize) {
            logger.debug("通过Provider拼接好的SQL-->{}",sqlResult);
            return sqlResult;
        } else if(page==1){
            Integer start = (page - 1 ) * pageSize;
            Integer nextSize = pageSize + 1;
            logger.debug("通过Provider拼接好的SQL-->{}",sqlResult + "\nLIMIT " + start + "," + nextSize);
            return sqlResult + "\nLIMIT "+ start + "," + nextSize;
        }else{
            Integer start = (page - 1 ) * pageSize - 1;//多查一个往前  主要是 判断一下 是否添加日期类型的数据
            Integer nextSize = pageSize + 2;//往后多查一个 位置前移一个 所以 多加一个就是2
            logger.debug("通过Provider拼接好的SQL-->{}",sqlResult + "\nLIMIT " + start + "," + nextSize);
            return sqlResult + "\nLIMIT "+ start + "," + nextSize;


        }
    }
}

