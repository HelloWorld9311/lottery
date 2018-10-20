package com.lottery.common.constant;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.common.constant
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/10/31 22:25
 */
public class BusinessConstant {


    public static class Compnany {

        /**
         * 国营
         */
        public static final int PROPERTY_NATIONALIZED = 10;

        /**
         * 民营
         */
        public static final int PROPERTY_PRIVATE = 20;

        /**
         * 合资
         */
        public static final int PROPERTY_JOINT_VENTURE = 30;

        /**
         * 属性转换方法
         *
         * @param type 待转换类型
         * @return 字符串类型常量
         */
        public static String transferProperty(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case PROPERTY_NATIONALIZED:
                    s = "国营";
                    break;
                case PROPERTY_PRIVATE:
                    s = "民营";
                    break;
                case PROPERTY_JOINT_VENTURE:
                    s = "合资";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /**
         * 国营
         */
        public static final int QUALIFICATION_ONE = 10;

        /**
         * 民营
         */
        public static final int QUALIFICATION_TWO = 20;

        /**
         * 合资
         */
        public static final int QUALIFICATION_THREE = 30;

        /**
         * 属性转换方法
         *
         * @param type 待转换类型
         * @return 字符串类型常量
         */
        public static String transferQualification(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case QUALIFICATION_ONE:
                    s = "一级";
                    break;
                case QUALIFICATION_TWO:
                    s = "二级";
                    break;
                case QUALIFICATION_THREE:
                    s = "三级";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }
    }

    public static class AuthorityHouse {

        /**
         * 业主
         */
        public static final int AUTHORITY_TYPE_OWNER = 10;

        /**
         * 租户
         */
        public static final int AUTHORITY_TYPE_TENANTRY = 20;

        /**
         * 业主家属
         */
        public static final int AUTHORITY_TYPE_RELATION = 30;

        public static Integer transferAuthorityHouseTypeDesc(String desc){
            if( "业主".equals(desc) ){
                return AUTHORITY_TYPE_OWNER;
            }
            if( "租户".equals(desc) ){
                return AUTHORITY_TYPE_TENANTRY;
            }
            if( "家属".equals(desc) ){
                return AUTHORITY_TYPE_RELATION;
            }
            return Integer.valueOf(0);
        }

        public static String transferAuthorityHouseType(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case AUTHORITY_TYPE_OWNER:
                    s = "业主";
                    break;
                case AUTHORITY_TYPE_TENANTRY:
                    s = "租户";
                    break;
                case AUTHORITY_TYPE_RELATION:
                    s = "家属";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /**
         * 待审核状态
         */
        public static final Integer STATUS_PENDING_REVIEW = 10;

        /**
         * 审核通过
         */
        public static final Integer STATUS_PASSED = 20;

        /**
         * 审核不通过
         */
        public static final Integer STATUS_NOT_PASSED = 30;

        public static String transferAuthorityHouseStatus(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 10:
                    s = "待审核";
                    break;
                case 20:
                    s = "审核通过";
                    break;
                case 30:
                    s = "审核不通过";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        public static String transferWebAuthorityHouseStatus(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 10:
                    s = "审核中";
                    break;
                case 20:
                    s = "审核通过";
                    break;
                case 30:
                    s = "审核未通过";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }
    }

    public static class HouseRepair {

        /**
         * 紧急程度, 紧急
         */
        public static final int REPAIR_LEVEL_URGENT = 10;

        /**
         * 紧急程度, 一般
         */
        public static final int REPAIR_LEVEL_NORMAL = 20;

        public static String transferHouseRepairLevel(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case REPAIR_LEVEL_URGENT:
                    s = "紧急";
                    break;
                case REPAIR_LEVEL_NORMAL:
                    s = "一般";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /**
         * 维修类型, 公共
         */
        public static final int REPAIR_TYPE_COMMON = 10;

        /**
         * 维修类型, 私人
         */
        public static final int REPAIR_TYPE_PRIVATE = 20;

        public static String transferHouseRepairType(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case REPAIR_TYPE_COMMON:
                    s = "公共区域报修";
                    break;
                case REPAIR_TYPE_PRIVATE:
                    s = "私人报修";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /**
         * 待确认
         */
        public static final int STATUS_WAIT_CONFIRM = 0;

        /**
         * 已结单
         */
        public static final int STATUS_RECEIVE_ORDER = 10;

        /**
         * 维修中
         */
        public static final int STATUS_REPAIRING = 20;

        /**
         * 已完成
         */
        public static final int STATUS_COMPLETE = 30;

        /**
         * 已评价 (已完成)
         */
        public static final int STATUS_BEEN_EVALUATED = 40;

        /**
         * 已关闭
         */
        public static final int STATUS_CLOSED = 50;

        /**
         * 已支付
         */
        public static final int STATUS_PAYED = 60;

        public static String transferHouseRepairStatus(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case STATUS_WAIT_CONFIRM:
                    s = "待确认";
                    break;
                case STATUS_RECEIVE_ORDER:
                    s = "已接单";
                    break;
                case STATUS_REPAIRING:
                    s = "维修中";
                    break;
                case STATUS_COMPLETE:
                case STATUS_BEEN_EVALUATED:
                    s = "已完成";
                    break;
                case STATUS_CLOSED:
                    s = "已关闭";
                    break;
                case STATUS_PAYED:
                    s = "已支付";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }
    }

    public static class Rating {

        public static String transferRatingStar(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 1:
                    s = "一星";
                    break;
                case 2:
                    s = "二星";
                    break;
                case 3:
                    s = "三星";
                    break;
                case 4:
                    s = "四星";
                    break;
                case 5:
                    s = "五星";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }
    }


    /**
     * 费用相关
     */
    public static class FeesOrder {

        /**
         * APP类型
         */
        public static final String ANDROID="android";
        public static final String IOS="ios";

        /**
         * APP标识
         */
        public static final String YG_APP="yg_app";
        public static final String APP="app";
        public static final String MINI_PRO = "minipro";

        /**
         * 支付类型：1.微信 2.支付宝
         */
        public static final Integer PAY_CHANNEL_WECHAT = 1;
        public static final Integer PAY_CHANNEL_ALIPAY = 2;

        public static String transferPayChannel(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 1:
                    s = "微信支付";
                    break;
                case 2:
                    s = "支付宝支付";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /** 支付用户端类型 */
        public static final String DEVICE_APP = "app";
        public static final String DEVICE_PC = "pc";
        public static final String DEVICE_WECHAT = "wechat";
        public static final String DEVICE_MINI_PRO = "minipro";


        /** 缴费方式 */
        //未支付
        public static final Integer PAY_TYPE_NO = 0;
        //在线支付
        public static final Integer PAY_TYPE_ONLINE = 1;
        //线下支付
        public static final Integer PAY_TYPE_OFFLINE = 2;

        public static String transferPayType(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 1:
                    s = "在线支付";
                    break;
                case 2:
                    s = "线下支付";
                    break;
                default:
                    s = "-";
                    break;
            }
            return s;
        }

        /**
         * 缴费状态
         */
        //未交费
        public static final Integer fees_status_no = 0;
        //已缴费
        public static final Integer fees_status_yes = 1;

        public static String transferFeesStatus(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 0:
                    s = "未缴费";
                    break;
                case 1:
                    s = "已缴费";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }

        /**
         * 创建状态
         */
        //成功
        public static final Integer create_status_yes = 0;
        //失败
        public static final Integer create_status_no = 1;

        public static String transferCreateStatus(Integer type) {
            if (type == null) {
                return "";
            }
            String s;
            switch (type) {
                case 0:
                    s = "成功";
                    break;
                case 1:
                    s = "失败";
                    break;
                default:
                    s = "";
                    break;
            }
            return s;
        }
    }

    public static class Complaint {
        /**
         * 1待处理;2已经处理;10已结束
         */
        public static final Integer STATUS_PENDING = 1;
        public static final Integer STATUS_DEAL = 2;
        public static final Integer STATUS_FINISH = 10;

        public static String transferStatus(Integer status) {
            if (status == null) {
                return "";
            }

            String statusDesc;
            switch (status) {
                case 1:
                    statusDesc = "待处理";
                    break;
                case 2:
                    statusDesc = "已处理";
                    break;
                case 10:
                    statusDesc = "已结束";
                    break;
                default:
                    statusDesc = "";
                    break;
            }
            return statusDesc;
        }

        /**
         * 1用户2物业人员
         */
        public static final Integer USER_TYPE = 1;
        public static final Integer COMPANY_DEAL = 2;

        public static String transferUserType(Integer type) {
            if (type == null) {
                return "";
            }

            String userType;
            switch (type) {
                case 1:
                    userType = "我";
                    break;
                case 2:
                    userType = "物业回复";
                    break;
                default:
                    userType = "";
                    break;
            }
            return userType;
        }
    }

    public static class Role {
        /**
         * 1: 为禁用状态2: 启用状态
         */
        public static final Integer STATUS_DISABLE = 1;
        public static final Integer STATUS_ENABLED = 2;
        public static String transferStatus(Integer status) {

            if (status == null) {
                return "";
            }
            String statusDesc;
            switch (status) {
                case 1:
                    statusDesc = "禁用";
                    break;
                case 2:
                statusDesc = "启用";
                break;
                default:
                    statusDesc = "";
            }
            return statusDesc;
        }

        /**
         * 1 经理；2客服；3 维修工；4 项目管理员；5 城市经理
         */
        public static final Integer ROLE_MANAGER=1;
        public static final Integer ROLE_SERVICE=2;
        public static final Integer ROLE_WORKER=3;
        public static final Integer ROLE_PROJECT_MANAGER=4;
        public static final Integer ROLE_CITY_MANAGER=5;
    }


    public static class Member {
        public static final String PASSWORD = "123456";

        public static final Integer CREATOR = 99999999;
    }
    /**
     * 社区信息
     */
    public static class Information {

        /**
         * 2017年11月23日 产品说不拼接号楼了
         */
        public static final String FLOOR_ID_STR = "号楼";
//        public static final String FLOOR_ID_STR = " ";

        /**
         * 2017年11月23日 产品说 单元拼接为 #
         * 2018年3月18日 上峰命令 单元拼接汉字 "单元"
         */
//        public static final String UNIT_ID_STR = "#";
        public static final String UNIT_ID_STR = "单元";
        public static final String UNIT_ID_STR_NEW = "层";

        public static final String HOUSE_ID_STR = "房间";

        public static String transferFloorId(String str) {
            if (null == str || str.isEmpty()) {
                return "";
            }
            return str + FLOOR_ID_STR;
        }

        public static String transferUnitId(String str, int communityType) {
            if (null == str || str.isEmpty()) {
                return "" + UNIT_ID_STR;
            }
            if (communityType == 10) {
                // 住宅
                return str + UNIT_ID_STR;
            } else if (communityType == 20) {
                return str + UNIT_ID_STR_NEW;
            }
            return str + UNIT_ID_STR;
        }
    }

    public static class Coupon {
        //优惠券状态 10:可用 20:已过期 30:已使用 40:已停用
        public static final int STATUS_CANUSE = 10;
        public static final int STATUS_PASSED = 20;
        public static final int STATUS_USEED = 30;
        public static final int STATUS_STOP = 40;

        public static String transferStatus(Integer status) {

            if (status == null) {
                return "";
            }
            String statusDesc;
            switch (status) {
                case STATUS_CANUSE:
                    statusDesc = "可用";
                    break;
                case STATUS_PASSED:
                    statusDesc = "已过期";
                    break;
                case STATUS_USEED:
                    statusDesc = "已使用";
                    break;
                case STATUS_STOP:
                    statusDesc = "已停用";
                    break;
                default:
                    statusDesc = "";
            }
            return statusDesc;
        }

        public static final int SOURCE_FROM_USER = 1;
        public static final int SOURCE_FROM_ADMIN = 3;
    }

    public enum Order{

        TYPE_PROPERTY_FEE(10,"物业费"),

        TYPE_MAINTENANCE_COST(20,"维修费"),

        ;

        Order(Integer type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        private Integer type;
        private String desc;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
