package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * TestunitFlowDO
 */
public class TestunitFlowDO {

    /**
     * id
     */
    private Long   id;

    /**
     * gmtCreate
     */
    private Date   gmtCreate;

    /**
     * creator
     */
    private String creator;

    /**
     * gmtModified
     */
    private Date   gmtModified;

    /**
     * modifier
     */
    private String modifier;

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

    /**
     * systemId
     */
    private String systemId;

    /**
     * tag
     */
    private String tag;

    /**
     * userId
     */
    private Long   userId;

    /**
     * picUrl
     */
    private String picUrl;

    /**
     * times
     */
    private Long   times;

    /**
     * envConfigRequired
     */
    private String envConfigRequired;

    /**
     * 是否可用
     */
    private String isActive;

    /**
     * remark
     */
    private String remark;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return this.gmtModified;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Long getTimes() {
        return this.times;
    }

    public void setEnvConfigRequired(String envConfigRequired) {
        this.envConfigRequired = envConfigRequired;
    }

    public String getEnvConfigRequired() {
        return this.envConfigRequired;
    }

    /**
     * @return the isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

}
