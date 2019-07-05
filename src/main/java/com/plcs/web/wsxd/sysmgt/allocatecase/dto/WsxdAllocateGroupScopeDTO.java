package com.plcs.web.wsxd.sysmgt.allocatecase.dto;

/**
 * @ProjectName plcs-web
 * @ClassName WsxdAllocateGroupScopeDTO
 * @Description TODO
 * @Author AndyHuang
 * @DATE 2019/6/18 18:30
 */
public class WsxdAllocateGroupScopeDTO {
    private String id;                  // 分案处理组案件范围id
    private String groupName;		    // 处理组名
    private String groupId;		        // 处理组id
    private String appOrg;		        // 机构 01:网商 02:玖康 03:分蛋 04:嘉禾 05: 机构一商润贷 app:app
    private String appOrgName;		    // 机构名
    private String departmentId;		// 事业部
    private String departmentName;		// 事业部名
    private String hasCommonPool;       // 是否含公共池,0:是1:否 2:全部

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAppOrg() {
        return appOrg;
    }

    public void setAppOrg(String appOrg) {
        this.appOrg = appOrg;
    }

    public String getAppOrgName() {
        return appOrgName;
    }

    public void setAppOrgName(String appOrgName) {
        this.appOrgName = appOrgName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHasCommonPool() {
        return hasCommonPool;
    }

    public void setHasCommonPool(String hasCommonPool) {
        this.hasCommonPool = hasCommonPool;
    }

}
