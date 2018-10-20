package com.lottery.common.constant;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.common.constant
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/10/31 22:25
 */
public class BusinessCode {

    public static final String REQUEST_ERROR_CODE = "99999";
    public static final String REQUEST_ERROR_MSG = "";

    public static class Company {

        public static final String PARAM_CHECK_ERROR_CODE = "10000";

        public static final String NOT_FOUND = "10001";

        public static final String COMPANY_NOT_EXIT_CODE="10001";
        public static final String COMPANY_NOT_EXIT_MSG="公司信息不存在";
    }

    public static class Project {

        public static final String PARAM_CHECK_ERROR_CODE = "10100";

        public static final String NOT_FOUND = "10101";

        public static final String NOT_ALLOW_DELETE = "10102";
    }

    public static class Community {

        public static final String PARAM_CHECK_ERROR_CODE = "10200";

        public static final String NOT_FOUND = "10201";

        public static final String NOT_ALLOW_DELETE = "10202";

        public static final String INFOMAINTION_EXISTS = "10203";
    }

    public static class Common {

        public static final String NOT_FOUND = "10301";
        public static final String FILE_NOT_FOUND = "10302";
    }

    public class AuthorityHouse {

        public static final String NOT_FOUND = "10401";

        public static final String PARAM_CHECK_ERROR_CODE = "10402";

        public static final String NOT_CERTIFIED = "10403";

        public static final String HAS_CERTIFIED = "10404";

        public static final String HOUSE_NOT_EXIT_CODE="10405";
        public static final String HOUSE_NOT_EXIT_MSG="当前用户没有认证房屋信息";

        public static final String NOT_ALLOW_DELETE = "10406";
    }

    public class Repair {

        public static final String NOT_FOUND = "10501";

        public static final String PARAM_CHECK_ERROR = "10502";
    }

    public static class Export {

        public static final String NO_RECORD_FIND_CODE = "10601";
    }

    public class Excel {

        public static final String LOAD_ERROR = "10701";
    }

    public class User {

        public static final String USER_PROJECT_NOT_FOUND_CODE = "10501";

        public static final String USER_PROJECT_NOT_FOUND_MSG = "用户关联的项目信息不存在";

        public static final String USER_PROJECT_INFO_ERROR_CODE = "10502";

        public static final String USER_PROJECT_INFO_ERROR_MSG = "当前用户存在多个项目信息";
    }

    public class Notification {

        public static final String NOTIFICATION_NOT_FOUND_CODE = "10601";
        public static final String NOTIFICATION_TIME_FORMAT_ERROR_CODE = "10602";
        public static final String NOTIFICATION_TIME_FORMAT_ERROR_MSG = "通知时间格式错误";
        public static final String NOTIFICATION_NOT_FOUND_MSG = "通知信息没有找到";

    }

    public class FeesOrder {

        public static final String FEES_ORDER_NOT_FOUND_CODE = "10701";

        public static final String FEES_ORDER_NOT_FOUND_MSG = "该缴费单没有找到";

        public static final String FEES_ORDER_CREATE_STATUS_ERROR_CODE = "10702";

        public static final String FEES_ORDER_CREATE_STATUS_ERROR_MSG = "该缴费单生成状态为失败";

        public static final String FEES_ORDER_CAN_NOT_EDIT_CODE = "10703";

        public static final String FEES_ORDER_CAN_NOT_EDIT_MSG = "该缴费单不可编辑";

        public static final String FEES_ORDER_CREATE_ERROR_CODE = "10704";

        public static final String FEES_ORDER_CAN_NOT_DELETE_CODE = "10705";

        public static final String FEES_ORDER_CAN_NOT_DELETE_MSG = "已缴费的订单不可删除";

        public static final String FEES_TYPE_NOT_FOUND_CODE = "10706";

        public static final String FEES_TYPE_NOT_FOUND_MSG = "费用类型没有找到";

        public static final String FEES_TYPE_CAN_NOT_DELETE_CODE = "10707";

        public static final String FEES_TYPE_CAN_NOT_DELETE_MSG = "该缴费类型关联有缴费单，不能删除！";

        public static final String FEES_ORIGINAL_TOTAL_FEES_ERROR_CODE = "10708";

        public static final String FEES_ORIGINAL_TOTAL_FEES_ERROR_MSG = "优惠前金额不能等于或小于实际缴费金额。";


        public static final String FEES_TYPE_CAN_NOT_EDIT_CODE = "10709";

        public static final String FEES_TYPE_CAN_NOT_EDIT_MSG = "该缴费类型关联有缴费单，不能编辑！";

        public static final String FEES_TYPE_CAN_NOT_SAME_CODE = "10710";

        public static final String FEES_TYPE_CAN_NOT_SAME_MSG = "该费用类型已存在";
    }

    public static class Rating {

        public static final String NOT_FOUND = "10801";
    }

    public class Complaint {

        public static final String COMPLAINT_NOT_FOUND_CODE = "10801";
        public static final String COMPLAINT_DESC_NOT_FOUND_CODE = "10802";
        public static final String COMPLAINT_FINISH_CODE = "10803";
    }

    public class Member {

        public static final String MOB_PHONE_IS_EXIT_CODE = "10901";

        public static final String USERNAME_OR_PASSWORD_ERROR_CODE = "10902";

        public static final String USER_NAME_IS_EXIT_CODE = "10903";


        public static final String MOB_PHONE_IS_NULL_CODE = "10904";
        public static final String MOB_PHONE_IS_NULL_MSG = "登陆用户手机号不能为空";


        public static final String OLD_PASSWORD_ERROR_CODE = "10905";
        public static final String OLD_PASSWORD_ERROR_MSG = "原始密码错误，请重新输入";

        public static final String MOB_PHONE_ERROR_CODE = "10906";

        public static final String USER_NAME_ERROR_CODE = "10907";

        public static final String USER_IS_NULL_CODE = "10908";

    }

    public class Pay {
        public static final String WECHAT_PAY_FAILED_CODE = "11001";
        public static final String WECHAT_PAY_FAILED_MSG = "微信支付失败";
        public static final String ERROR_PAY_TYPE_CODE = "11002";
        public static final String ERROR_PAY_TYPE_MSG = "请选择正确的支付方式";
        public static final String NO_OPEN_PAY_CODE = "11003";
        public static final String NO_OPEN_PAY_MSG = "暂未开发支付功能";
        public static final String ALIPAY_TIMEOUT_CODE = "11004";
        public static final String ALIPAY_TIMEOUT_MSG = "支付宝支付超时";

        public static final String ORDER_ALREADY_PAY_CODE = "11005";
        public static final String ORDER_ALREADY_PAY_MSG = "该缴费单已支付";

        public static final String ORDER_APP_TYPE_ERROR_CODE = "11006";

        public static final String ORDER_APP_ERROR_CODE = "11007";
    }

    public class Coupon {
        public static final String COUPON_TYPE_ERROR = "11401";
        public static final String COUPON_NOT_FOUND = "11402";
        public static final String COUPON_DESC_TOO_LONG = "11403";
        public static final String COUPON_OVER_TIME = "11404";
        public static final String COUPON_BELOW_LIMIT = "11405";
        public static final String COUPON_HAS_USED = "11406";
    }

    public class Role {
        public static final String ROLE_STATUS_DISABLE_CODE = "11101";

        public static final String USER_ROLE_EXIT_CODE = "11102";

        public static final String USER_ROLE_NOT_EXIT_CODE = "11103";
    }

    public class Login {
        public static final String LOGIN_FAIL_CODE = "11201";
        public static final String USER_ACCESS_ERROR_CODE = "11202";

        /**C端用户登陆异常**/
        public static final String USER_LOGIN_ERROR_CODE = "11203";
        public static final String USER_AUTHCODE_SEND_ERROR_CODE = "11204";
    }

    public class CommunityInformation {
        public static final String UPLOADING = "11301";
    }
}
