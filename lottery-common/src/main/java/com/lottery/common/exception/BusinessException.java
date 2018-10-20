package com.lottery.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @Prject: lottery
 * @Package: cn.lottery.common.exception
 * @Description: TODO
 * @Date: 2018/10/20 22:10
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1831133144759855850L;
    private String code;

    private String message;

    private String param;

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public BusinessException(String code, String message, String param) {
        this.code = code;
        this.message = message;
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        if(StringUtils.isNotBlank(param)){
            StringBuilder sb = new StringBuilder();
            return sb.append(message).append(param).toString();
        }else {
            return message;
        }
    }

}