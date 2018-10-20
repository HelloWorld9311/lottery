package web.intercepter;

import com.lottery.common.util.LogUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright © 2017北京联合倍全电子商务有限公司. All rights reserved.
 *
 * @Prject: ad_system
 * @Package: com.bqmart.interceptor
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/7/10 18:30
 * @version: V1.0
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            request.setAttribute(LogUtils.HANDLER_METHOD, handler);
        }
        LogUtils.setRequest(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
