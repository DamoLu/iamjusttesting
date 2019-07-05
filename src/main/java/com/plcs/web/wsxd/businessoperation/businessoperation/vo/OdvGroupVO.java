package com.plcs.web.wsxd.businessoperation.businessoperation.vo;

import java.util.List;

public class OdvGroupVO {
    private String odvGroup;
    private String odvGroupName;

    private List<OdvOV> odvOVList;

    public String getOdvGroup() {
        return odvGroup;
    }

    public void setOdvGroup(String odvGroup) {
        this.odvGroup = odvGroup;
    }

    public String getOdvGroupName() {
        return odvGroupName;
    }

    public void setOdvGroupName(String odvGroupName) {
        this.odvGroupName = odvGroupName;
    }

    public List<OdvOV> getOdvOVList() {
        return odvOVList;
    }

    public void setOdvOVList(List<OdvOV> odvOVList) {
        this.odvOVList = odvOVList;
    }
}
