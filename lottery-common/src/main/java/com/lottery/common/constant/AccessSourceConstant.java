package com.lottery.common.constant;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.common.constant
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/11/2 20:27
 */
public class AccessSourceConstant {

    /**
     * Root用户具有权限
     */
    public static class Root {

        /**
         * 公司信息保存权限
         */
        public static final String COMPANY_SAVE = "ROOT_COMPANY_SAVE";

        /**
         * 公司信息获取权限
         */
        public static final String COMPANY_GET = "ROOT_COMPANY_GET";

        /**
         * 公司信息更新权限
         */
        public static final String COMPANY_UPDATE = "ROOT_COMPANY_UPDATE";

        /**
         * 公司信息删除权限
         */
        public static final String COMPANY_DELETE = "ROOT_COMPANY_DELETE";

        /**
         * 项目信息保存权限
         */
        public static final String PROJECT_SAVE = "ROOT_PROJECT_SAVE";

        /**
         * 项目信息获取权限
         */
        public static final String PROJECT_GET = "ROOT_PROJECT_GET";

        /**
         * 项目信息更新权限
         */
        public static final String PROJECT_UPDATE = "ROOT_PROJECT_UPDATE";

        /**
         * 项目信息删除权限
         */
        public static final String PROJECT_DELETE = "ROOT_PROJECT_DELETE";
    }

    public static class Company {

    }

    /**
     * 社区相关接口权限
     */
    public static class Community {

        /**
         * 后台首页分页查询社区列表信息
         */
        public static final String BACKEND_COMMUNITY_LIST_PAGE = "BACKEND_COMMUNITY_LIST_PAGE";

//        /**
//         * 后台首页社区图片信息上传
//         */
//        public static final String BACKEND_COMMUNITY_PIC_SAVE = "BACKEND_COMMUNITY_PIC_SAVE";

        /**
         * 后台社区信息修改权限
         */
        public static final String BACKEND_COMMUNITY_OPERATE = "BACKEND_COMMUNITY_OPERATE";
        /**
         * 业主资料维护
         */
        public static final String BACKEND_COMMUNITY_INFORMATION = "BACKEND_COMMUNITY_INFORMATION";
    }

    /**
     * 住户报修权限常量
     */
    public static class Repair {

        /**
         * 房屋维修列表查看权限
         */
        public static final String BACKEND_REPAIR_LIST = "BACKEND_REPAIR_LIST";

        /**
         * 后台房屋维修数据修改权限
         */
        public static final String BACKEND_REPAIR_OPERATE = "BACKEND_REPAIR_OPERATE";

        /**
         * 房屋维修详情权限
         */
        public static final String BACKEND_REPAIR_DETAIL = "BACKEND_REPAIR_DETAIL";
    }

    /**
     * 房屋认证权限
     */
    public static class HouseAuthority {

        /**
         * 后台房屋认证列表查看权限
         */
        public static final String BACKEND_HOUSE_AUTHORITY_LIST = "BACKEND_HOUSE_AUTHORITY_LIST";

        /**
         * 后台房屋认证审核操作权限
         */
        public static final String BACKEND_HOUSE_AUTHORITY_OPERATE = "BACKEND_HOUSE_AUTHORITY_OPERATE";

        /**
         * 后台房屋认证删除权限
         */
        public static final String BACKEND_HOUSE_AUTHORITY_DELETE = "BACKEND_HOUSE_AUTHORITY_DELETE";

        /**
         * 后台房屋认证详情查看权限
         */
        public static final String BACKEND_HOUSE_AUTHORITY_DETAIL = "BACKEND_HOUSE_AUTHORITY_DETAIL";

    }

    /**
     * 物业通知权限
     */
    public static class Notification {
        /** 通知列表 */
        public static final String BACKEND_NOTIFICATION_LIST = "BACKEND_NOTIFICATION_LIST";
        /** 通知保存/编辑 */
        public static final String BACKEND_NOTIFICATION_OPERATE = "BACKEND_NOTIFICATION_OPERATE";
        /** 通知详情 */
        public static final String BACKEND_NOTIFICATION_DETAIL = "BACKEND_NOTIFICATION_DETAIL";
        /** 通知删除 */
        public static final String BACKEND_NOTIFICATION_DELETE = "BACKEND_NOTIFICATION_DELETE";
    }


    /**
     * 住户缴费
     */
    public static class Fees {
        /** 缴费订单列表 */
        public static final String BACKEND_FEES_ORDER_LIST = "BACKEND_FEES_ORDER_LIST";
        /** 缴费订单添加/编辑/删除 */
        public static final String BACKEND_FEES_ORDER_OPERATE = "BACKEND_FEES_ORDER_OPERATE";
        /** 缴费订单确认缴费 */
        public static final String BACKEND_FEES_ORDER_CONFIRM = "BACKEND_FEES_ORDER_CONFIRM";
        /** 缴费类型配置 */
        public static final String BACKEND_FEES_TYPE_OPERATE = "BACKEND_FEES_TYPE_OPERATE";
    }

    /**
     * 用户投诉权限
     */
    public static class Complaint {
        /**
         * 列表查询权限
         */
        public static final String BACKEND_COMPLAINT_LIST = "BACKEND_COMPLAINT_LIST";
        /**
         * 投诉详情查询权限
         */
        public static final String BACKEND_COMPLAINT_DETAIL = "BACKEND_COMPLAINT_DETAIL";
        /**
         * 投诉回复/结束权限
         */
        public static final String BACKEND_COMPLAINT_OPERATE = "BACKEND_COMPLAINT_OPERATE";
        /**
         * 删除投诉权限
         */
        public static final String BACKEND_COMPLAINT_DELETE = "BACKEND_COMPLAINT_DELETE";
    }

    /**
     *账户设置权限
     */
    public static class Account {
        /**
         *账户列表查询权限
         */
        public static final String BACKEND_ACCOUNT_LIST = "BACKEND_ACCOUNT_LIST";
        /**
         *新增/启用/禁用/重置密码/编辑账户权限
         */
        public static final String BACKEND_ACCOUNT_OPERATE = "BACKEND_ACCOUNT_OPERATE";

    }

    /**
     * 优惠券相关权限
     */
    public static class Coupon {
        /**
         * 优惠券列表查询权限
         */
        public static final String BACKEND_COUPON_LIST = "BACKEND_COUPON_LIST";

        /**
         * 发放优惠券权限
         */
        public static final String BACKEND_COUPON_ADD = "BACKEND_COUPON_ADD";

        /**
         * 删除优惠券权限
         */
        public static final String BACKEND_COUPON_DELETE = "BACKEND_COUPON_DELETE";
    }
}
