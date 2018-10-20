package com.lottery.security.intercepter;

import com.lottery.common.exception.BusinessException;
import com.lottery.common.util.RedisUtil;
import com.lottery.security.annotation.Resubmit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
public class ResubmitInterceptor extends HandlerInterceptorAdapter{

    private RedisUtil redisUtil;

    public ResubmitInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 目的是要得到请求方法, 获得方法签名, 防止因为网络延迟重复提交
            HandlerMethod method = (HandlerMethod) handler;
            // 是否需要拦截标志
            boolean flag = false;
            long second = 0;
            Resubmit methodAnnotation = method.getMethodAnnotation(Resubmit.class);
            if (null == methodAnnotation) {
                Resubmit classAnnotation = method.getBeanType().getAnnotation(Resubmit.class);
                if (null != classAnnotation) {
                    // 如果类上注解不为空, 则需要拦截
                    flag = true;
                    second = classAnnotation.value();
                }
            } else {
                flag = true;
                second = methodAnnotation.value();
            }

            if (flag) {
                return handle(request, method, second);
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束之后删除Key
        if (handler instanceof HandlerMethod) {
            // 目的是要得到请求方法, 获得方法签名, 防止因为网络延迟重复提交
            HandlerMethod method = (HandlerMethod) handler;
            String key = getKey(request, method);
            redisUtil.remove(key);
        }
    }

    /**
     *
     * @param request
     * @param method
     * @param second
     * @return
     */
    private boolean handle(HttpServletRequest request, HandlerMethod method, long second) throws BusinessException {
        String key = getKey(request, method);
        if (redisUtil.exists(key)) {
            throw new BusinessException("9999", "请不要短时间内重复提交请求");
        }
        redisUtil.set(key, key, second);
        return true;
    }

    private String getKey(HttpServletRequest request, HandlerMethod method) {
        String requestURI = request.getRequestURI();
//        String classPackageName = method.getBeanType().getName();
//        String methodName = method.getMethod().getName();
        Map<String, String[]> parameterMap = request.getParameterMap();
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (null != parameterMap) {
            // 对参数进行排序处理
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                treeMap.put(entry.getKey(), Arrays.toString(entry.getValue()));
            }
        }
        StringBuilder sb = new StringBuilder();
        String key = sb.append(requestURI).append(treeMap).toString();
        return key;
    }
}
