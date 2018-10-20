package com.lottery.security;

import java.util.List;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
public class ApiSecurity {

    private List<String> accessSources;

    /**
     * 构造方法, 需要传入该角色拥有的资源权限
     *
     * @param accessSources
     */
    public ApiSecurity(List<String> accessSources) {
        this.accessSources = accessSources;
    }

    /**
     * 判断是否有权限进入
     * @param accessSource 由拦截器从方法或者类上获取的拦截资源
     * @return
     *  true: 允许访问
     *  false: 不允许访问
     */
    public boolean isAccess(String accessSource) {
        if (accessSource == null || accessSource.isEmpty()) {
            // 如果没有传入资源限制, 则表示不拦截, 可以进入
            return true;
        }
        // 如果传入允许权限不为空, 则进行权限校验
        if (this.accessSources != null && !this.accessSources.isEmpty()) {
            // 校验当前权限集合是否包含待访问资源
            return this.accessSources.contains(accessSource);
        }
        return false;
    }

    /**
     * 判断是否同时具备多个资源的访问权限
     * @param accessSources 多个资源权限的可变参
     * @return
     *  true: 允许访问
     *  false: 不允许访问
     */
    public boolean isAccess(List<String> accessSources) {
        if (accessSources == null || accessSources.size() == 0) {
            // 如果传入为空, 或者数量为0, 则表示不拦截
            return true;
        }
        boolean result = true;
        for (String accessSource : accessSources) {
            boolean access = this.isAccess(accessSource);
            // 如果存在一个不允许访问, 则终止循环, 返回不允许访问
            if (!access) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判断是否同时具备多个资源的访问权限
     * @param accessSources 多个资源权限的可变参
     * @return
     *  true: 允许访问
     *  false: 不允许访问
     */
    public boolean isAccess(String ... accessSources) {
        if (accessSources == null || accessSources.length == 0) {
            // 如果传入为空, 或者数量为0, 则表示不拦截
            return true;
        }
        boolean result = true;
        for (String accessSource : accessSources) {
            boolean access = this.isAccess(accessSource);
            // 如果存在一个不允许访问, 则终止循环, 返回不允许访问
            if (!access) {
                result = false;
                break;
            }
        }
        return result;
    }
}
