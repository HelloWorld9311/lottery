package com.lottery.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2017北京联合倍全电子商务有限公司. All rights reserved.
 *
 * @Prject: ad_system
 * @Package: com.bqmart.common.util
 * @Description: 日志记录工具类, 能够按照模板将日志格式化输出, 能够规范日志格式
 * @author: wuyujia
 * @Date: 2017/7/19 16:06
 * @version: V1.0
 */
@SuppressWarnings("all")
public class LogUtils {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static final String HANDLER_METHOD = "HANDLER_METHOD";

    /**
     * templates
     */
    private static final String requestLogTemplate = "RequestLog: session->[{}];request->[{}];url->[{}];method->[{}];params->[{}];";

    private static final String resultLogTemplate = "ResultLog: session->[{}];request->[{}];url->[{}];method->[{}];results->[{}];";

    private static final String businessLogTemplate = "BusinessLog: session->[{}];request->[{}];class->[{}];currentMethod->[{}];message->[{}];params->[{}];results->[{}];";

    private static final String businessLogForThreadTemplate = "BusinessLog: thread->[{}];class->[{}];currentMethod->[{}];message->[{}];params->[{}];results->[{}];";

    private static final String businessExceptionLogTemplate = "BusinessExceptionLog: session->[{}];request->[{}];url->[{}];method->[{}];class->[{}];currentMethod->[{}];params->[{}]";

    private static final String exceptionLogTemplate = "ExceptionLog: session->[{}];request->[{}];url->[{}];method->[{}];message->[{}];";

    private static final String stepLogTemplate = "StepLog: session->[{}];request->[{}];url->[{}];class->[{}];current_method->[{}];params->[{}];";

    private static final String stepLogForThreadTemplate = "StepLog: thread->[{}];class->[{}];current_method->[{}];params->[{}];";

    private static final String loginLogTemplate = "LoginLog: session->[{}];request->[{}];url->[{}];method->[{}];UserInfo->[{}]";

    private static final String errorTemplate = "Error: {}";

    /**
     * requestThread
     */
    private static ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();

    /**
     * 设置请求, 放入threadLocal
     *
     * @param request
     */
    public static void setRequest(HttpServletRequest request) {
        requestThread.set(request);
    }

    /**
     * 打印请求日志
     *
     * @param params
     */
    public static void requestLog(Object params) {
        try {
            HttpServletRequest request = getRequest();
            HandlerMethod handler = getHandler();
            String method = null;
            if (handler != null) {
                method = String.join(".", handler.getBean().getClass().getName(), handler.getMethod().getName());
            }
            logger.info(requestLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), method, params);
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 打印响应结果日志
     *
     * @param results
     */
    public static void resultLog(Object results) {
        try {
            HttpServletRequest request = getRequest();
            HandlerMethod handler = getHandler();
            String method = null;
            if (handler != null) {
                method = String.join(".", handler.getBean().getClass().getName(), handler.getMethod().getName());
            }
            logger.info(resultLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), method, results);
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 打印业务日志
     *
     * @param params
     * @param result
     */
    public static void businessLog(Class clazz, String currentMethod, String message, Object params, Object results) {
        try {
            HttpServletRequest request = getRequest();
            if (request != null) {
                // 如果请求不为null, 则为http请求步骤搜集
//                HandlerMethod handler = getHandler();
//                String method;
//                if (handler != null) {
//                    StringBuffer sb = new StringBuffer();
//                    sb.append(handler.getBean().getClass().getName()).append(".").append(handler.getMethod().getName());
//                    method = handler.getBean().getClass().getName() + "." + handler.getMethod().getName();
//                    method = sb.toString();
//                }
                logger.info(businessLogTemplate, request.getSession().getId(), request, clazz.getName(), currentMethod, message, params, results);
            } else {
                // 如果request为null, 则为定时任务等其他日志, 记录线程记录
                Thread thread = Thread.currentThread();
                logger.info(businessLogForThreadTemplate, thread.getName(), clazz.getName(), currentMethod, message, params, results);
            }
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 操作步骤日志
     *
     * @param clazz
     * @param current_method
     * @param message
     * @param params
     */
    public static void stepLog(Class clazz, String current_method, Object params) {
        try {
            HttpServletRequest request = getRequest();
            if (request != null) {
                // 如果请求不为null, 则为http请求步骤搜集
//                HandlerMethod handler = getHandler();
//                String method = null;
//                if (handler != null) {
//                    method = handler.getBean().getClass().getName() + "." + handler.getMethod().getName();
//                }
                logger.info(stepLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), clazz.getName(), current_method, params);
            } else {
                // 如果request为null, 则为定时任务等其他日志, 记录线程记录
                Thread thread = Thread.currentThread();
                logger.info(stepLogForThreadTemplate, thread.getName(), clazz.getName(), current_method, params);
            }
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 用户登录日志
     *
     * @param userInfo
     */
    public static void loginLog(Object userInfo) {
        try {
            HttpServletRequest request = getRequest();
            HandlerMethod handler = getHandler();
            String method = null;
            if (handler != null) {
                method = String.join(".", handler.getBean().getClass().getName(), handler.getMethod().getName());
            }
            logger.info(loginLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), method, userInfo);
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 业务异常日志
     *
     * @param clazz
     * @param current_method
     * @param message
     * @param params
     */
    public static void businessExceptionLog(Class clazz, String current_method, String message, Object params) {
        try {
            HttpServletRequest request = getRequest();
            HandlerMethod handler = getHandler();
            String method = null;
            if (handler != null) {
                method = String.join(".", handler.getBean().getClass().getName(), handler.getMethod().getName());
            }
            logger.error(businessExceptionLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), method, clazz.getName(), current_method, message, params);
        } catch (Exception e) {
            exceptionLog(e.getMessage());
        }
    }

    /**
     * 打印异常日志
     *
     * @param message
     */
    public static void exceptionLog(String message) {
        try {
            HttpServletRequest request = getRequest();
            HandlerMethod handler = getHandler();
            String method = null;
            if (handler != null) {
                method = String.join(".", handler.getBean().getClass().getName(), handler.getMethod().getName());
            }
            logger.error(exceptionLogTemplate, request.getSession().getId(), request, request.getRequestURL().toString(), method, message);
        } catch (Exception e) {
            errorLog(e);
        }
    }

    /**
     * 打印任何日志追踪
     *
     * @param throwable
     */
    public static void errorLog(Throwable throwable) {
        logger.error(errorTemplate, throwable);
    }


    private static HttpServletRequest getRequest() {
        HttpServletRequest request = requestThread.get();
        return request;
    }


    private static HandlerMethod getHandler() {
        HttpServletRequest request = getRequest();
        Object method = request.getAttribute(HANDLER_METHOD);
        if (method != null) {
            return (HandlerMethod) method;
        }
        return null;
    }
}
