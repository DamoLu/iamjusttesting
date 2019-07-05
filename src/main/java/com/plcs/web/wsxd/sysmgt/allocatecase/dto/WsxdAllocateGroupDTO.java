package com.plcs.web.wsxd.sysmgt.allocatecase.dto;

import java.util.List;

/**
 * @ProjectName plcs-web
 * @ClassName WsxdAllocateGroupDTO
 * @Description TODO
 * @Author AndyHuang
 * @DATE 2019/6/18 17:57
 */
public class WsxdAllocateGroupDTO {
    private String id;                  // 处理组id
    private String groupName;		    // 处理组名
    private String odvs;		        // 处理人员组
    private String odvsName;		    // 处理人员组（姓名）
    private Integer minOverdueDay;		// 逾期范围下限
    private Integer maxOverdueDay;		// 逾期范围上限
    private String status;		        // 当前状态 1-启用 0-禁用
    private String remarks;

    private List<WsxdAllocateGroupScopeDTO> wsxdAllocateGroupScopeList; //处理组范围列表

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

    public String getOdvs() {
        return odvs;
    }

    public void setOdvs(String odvs) {
        this.odvs = odvs;
    }

    public String getOdvsName() {
        return odvsName;
    }

    public void setOdvsName(String odvsName) {
        this.odvsName = odvsName;
    }

    public Integer getMinOverdueDay() {
        return minOverdueDay;
    }

    public void setMinOverdueDay(Integer minOverdueDay) {
        this.minOverdueDay = minOverdueDay;
    }

    public Integer getMaxOverdueDay() {
        return maxOverdueDay;
    }

    public void setMaxOverdueDay(Integer maxOverdueDay) {
        this.maxOverdueDay = maxOverdueDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<WsxdAllocateGroupScopeDTO> getWsxdAllocateGroupScopeList() {
        return wsxdAllocateGroupScopeList;
    }

    public void setWsxdAllocateGroupScopeList(List<WsxdAllocateGroupScopeDTO> wsxdAllocateGroupScopeList) {
        this.wsxdAllocateGroupScopeList = wsxdAllocateGroupScopeList;
    }
}
