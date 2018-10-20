package com.lottery.security;

import com.lottery.common.exception.BusinessException;

/**
 * @Prject: lottery
 * @Package: cn.lottery.security.annotation
 * @Description: TODO
 * @Date: 2018-10-20
 */
public interface ApiSecurityService {

    /**
     * 根据用户ID, 查询用户拥有的资源数据, 将资源数据整合成List<String>封装到ApiSecurity中
     * @param userId
     * @see ApiSecurity
     * @return
     */
    ApiSecurity findUserAccessSources(Integer userId) throws BusinessException;
}
