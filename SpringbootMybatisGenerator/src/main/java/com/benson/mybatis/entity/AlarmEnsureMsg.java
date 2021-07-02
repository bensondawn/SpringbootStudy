package com.benson.mybatis.entity;

public class AlarmEnsureMsg {
    private Integer id;

    private String areaRespMsg;

    private String workshopRespMsg;

    private String sectionRespMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaRespMsg() {
        return areaRespMsg;
    }

    public void setAreaRespMsg(String areaRespMsg) {
        this.areaRespMsg = areaRespMsg;
    }

    public String getWorkshopRespMsg() {
        return workshopRespMsg;
    }

    public void setWorkshopRespMsg(String workshopRespMsg) {
        this.workshopRespMsg = workshopRespMsg;
    }

    public String getSectionRespMsg() {
        return sectionRespMsg;
    }

    public void setSectionRespMsg(String sectionRespMsg) {
        this.sectionRespMsg = sectionRespMsg;
    }
}