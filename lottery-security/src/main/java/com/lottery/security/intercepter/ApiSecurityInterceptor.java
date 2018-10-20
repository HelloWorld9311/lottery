package com.lottery.security.intercepter;

import com.lottery.common.constant.BusinessCode;
import com.lottery.common.constant.RedisConstant;
import com.lottery.common.exception.BusinessException;
import com.lottery.common.util.RedisUtil;
import com.lottery.security.ApiSecurity;
import com.lottery.security.ApiSecurityService;
import com.lottery.security.annotation.AccessSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
public class ApiSecurityInterceptor extends HandlerInterceptorAdapter {

    private ApiSecurityService apiSecurityService;

    private RedisUtil redisUtil;

    /**
     * 拦截器构造方法, 如果需要引入权限工具, 需要实现该类才能创建对象
     *
     * @param apiSecurityService
     */
    public ApiSecurityInterceptor(ApiSecurityService apiSecurityService, RedisUtil redisUtil) {
        this.apiSecurityService = apiSecurityService;
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 获取用户ID, 这个地方的key, 可以根据实际情况修改
        String uid = request.getParameter("userId");

        if (uid == null || uid.isEmpty()) {
            // 如果没有, 拦截
            throw new BusinessException(BusinessCode.Login.LOGIN_FAIL_CODE, "登录失效, 请重新登录");
        }
        Integer userId = null;
        try {
            userId = Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            throw new BusinessException(BusinessCode.Login.LOGIN_FAIL_CODE, "登录失效, 请重新登录");
        }
        //用户登陆校验
        Object object = redisUtil.get(RedisConstant.Member.LOGIN_IDENTIFY + userId + request.getSession().getId());
        if (Objects.isNull(object)) {
            throw new BusinessException(BusinessCode.Login.LOGIN_FAIL_CODE, "登录失效, 请重新登录");
        }
        // 权限校验接口
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            List<String> accessSources = new LinkedList<>();
            // 查询类上配置的资源名
            List<String> classTypeAnnotation = findClassTypeAnnotation(handlerMethod);
            accessSources.addAll(classTypeAnnotation);
            // 查询方法上配置的资源名
            List<String> methodTypeAnnotation = findMethodTypeAnnotation(handlerMethod);
            accessSources.addAll(methodTypeAnnotation);

            ApiSecurity userAccessSources = apiSecurityService.findUserAccessSources(userId);

            if (!userAccessSources.isAccess(accessSources)) {
                throw new BusinessException(BusinessCode.Login.USER_ACCESS_ERROR_CODE, "用户访问权限受限");
            }
        }
        return true;
    }

    /**
     * 查询方法上的资源名称
     *
     * @param handlerMethod
     * @return
     */
    private List<String> findMethodTypeAnnotation(HandlerMethod handlerMethod) {
        AccessSource methodAnnotation = handlerMethod.getMethodAnnotation(AccessSource.class);
        if (methodAnnotation != null) {
            String[] value = methodAnnotation.value();
            if (value != null && value.length != 0) {
                return Arrays.asList(value);
            }
        }
        return Collections.emptyList();
    }

    /**
     * 查询类名上设置的资源名称
     *
     * @param handlerMethod
     */
    private List<String> findClassTypeAnnotation(HandlerMethod handlerMethod) {
        AccessSource classAnnotation = handlerMethod.getBeanType().getAnnotation(AccessSource.class);
        if (classAnnotation != null) {
            String[] value = classAnnotation.value();
            if (value != null && value.length != 0) {
                return Arrays.asList(value);
            }
        }
        return Collections.emptyList();
    }
}
