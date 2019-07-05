package com.plcs.web.common.constant;

/**
 * 分案处理组表常量
 */
public class AllocateGroupConstants extends CommonConstants {
    public final static String STATUS_YES = "1";    // 当前状态 启用
    public final static String STATUS_NO = "0";     // 当前状态 禁用

    /**
     * 获取分案处理组信息出现异常
     */
    public final static String GET_GROUP_FAILED ="-1";

    /**
     * 获取角色信息出现异常
     */
    public final static  String GET_ROLE_FAILED ="-2";

    /**
     * 获取角色列表信息出现异常
     */
    public final static String GET_USER_BY_ROLE_FAILED ="-3";

    /**
     * 获取案件范围列表出现异常
     */
    public final static String GET_CASESCOPE_FAILED = "-4";

    /**
     * 获取启动的处理组出现异常
     */
    public final static String GET_ENABLEDGROUP_FAILED = "-5";

    /**
     * 逾期范围重叠
     */
    public final static String OVERDUE_DUPLICATE = "-6";

    /**
     * 处理人员出现在其它已启动处理组
     */
    public final static String ODVS_IS_EXISTED = "-7";

    /**
     * 处理组新增失败
     */
    public final static String INSERT_GROUP_FAILED = "-8";

    /**
     * 更新处理组信息失败
     */
    public final static String UPDATE_GROUP_FAILED = "-9";

    /**
     * 更新处理组
     */
    public final static String UPDATE_GROUP_STATUS_FAILED = "-10";

}
