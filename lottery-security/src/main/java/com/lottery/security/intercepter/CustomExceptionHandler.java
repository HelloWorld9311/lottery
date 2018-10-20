package com.lottery.security.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
@Configuration
public class CustomExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new MappingJackson2JsonView());
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            mav.addObject("code", businessException.getCode());
            mav.addObject("message", businessException.getMessage());
        } else {
            logger.error("异常错误：",e);
            mav.addObject("code", e.getCause());
            mav.addObject("message", e.getMessage());
        }
        e.printStackTrace();
        jsonp(httpServletRequest, httpServletResponse);
        return mav;
    }

    /**
     * 异常
     * @param httpServletRequest
     * @param httpServletResponse
     */
    protected void jsonp(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String origin = httpServletRequest.getHeader("Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACES");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    }
}
