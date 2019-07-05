package com.plcs.web.common.constant;

/**
 * 催收数据表共用常量
 */
public class CommonConstants {
    public final static String SPLIT_SEQ_STR = "\\|";   // 通用分隔符
    public final static String DEL_FLAG_YES = "1";    // 删除标记 0:正常
    public final static String DEL_FLAG_NO = "0";     // 删除标记 1：删除
    public final static String APP_ORG_01 = "01";     // 进件机构 网商
    public final static String APP_ORG_02 = "02";     // 进件机构 玖康
    public final static String APP_ORG_03 = "03";     // 进件机构 分蛋
    public final static String APP_ORG_04 = "04";     // 进件机构 嘉禾
    public final static String APP_ORG_APP = "app";   // 进件机构 app
    public final static String DATA_SOURCE_SC = "0";   // 数据来源 数仓导入
    public final static String DATA_SOURCE_RG = "1";   // 数据来源 人工新建
    public final static String HAS_COMMON_POOL_YES = "0";  // 是否含公共池 是
    public final static String HAS_COMMON_POOL_NO = "1";   // 是否含公共池 否
}
