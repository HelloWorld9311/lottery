package com.lottery.common.constant;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.common.constant
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/12/4 16:47
 */
public class RedisConstant {

    public static class ExpireTime{
        /**
         * 一分钟
         */
        public static final long MINUTES_ONE = 60L;
        /**
         * 五分钟
         */
        public static final long MINUTES_FIVE = 5 * 60L;
        /**
         * 十分钟
         */
        public static final long MINUTES_TEN = 10 * 60L;
        /**
         * 一小时
         */
        public static final long HOURS_ONE = 60 * 60L;
        /**
         * 一天
         */
        public static final long DAYS_ONE = 24 * 60 * 60L;
        /**
         * 一周
         */
        public static final long WEEKS_ONE = 7 * 60 * 60L;
        /**
         * 一月
         */
        public static final long MONTHS_ONE = 30 * 24 * 60 * 60L;
        /**
         * 一年
         */
        public static final long YEARS_ONE = 365 * 24 * 60 * 60L;
    }

    public static class CommunityInformation {

        /**
         * 获取社区资料上传的key
         * @param communityId
         * @return
         */
        public static final String getUploadKey(Integer communityId) {
            return "SUNSHINE_COMMUNITY_INFORMATION_UPLOAD_KEY_" + communityId;
        }

        /**用于延迟存储用户认证资料**/
        public static final String getUploadCacheKey(Integer communityId) {
            return "SUNSHINE_COMMUNITY_INFORMATION_UPLOAD_CACHE_KEY_" + communityId;
        }
    }

    /** 缓存key标识 */
    public static class Member {
        /**
         * 用户登录緩存
         */
        public static final String LOGIN_IDENTIFY = "SUNSHINE_LOGIN_INFO";
        public static final String BACKEND_LOGIN_IDENTIFY = "BACKEND_LOGIN_INFO";
        /**
         * 用戶角色緩存
         */
        public static final String REDIS_ROLE = "SUNSHINE_REDIS_ROLE";


        /**C端用户手机验证码缓存**/
        public static final String REDIS_AUTHCODE = "USER_AUTHCODE";
    }
}
