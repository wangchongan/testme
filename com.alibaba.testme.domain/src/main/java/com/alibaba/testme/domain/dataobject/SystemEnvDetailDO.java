package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * SystemEnvDetailDO
 */
public class SystemEnvDetailDO {

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
     * systemId
     */
    private Long   systemId;

    /**
     * propKey
     */
    private String propKey;

    /**
     * propValue
     */
    private String propValue;

    /**
     * remark
     */
    private String remark;

    /**
     * configType
     */
    private String configType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropKey() {
        return this.propKey;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public String getPropValue() {
        return this.propValue;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigType() {
        return this.configType;
    }

}
