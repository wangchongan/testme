package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * SystemEnvDO
 */
public class SystemEnvDO {

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
     * userId
     */
    private Long   userId;

    /**
     * systemId
     */
    private Long   systemId;

    /**
     * configName
     */
    private String configName;

    /**
     * isDefault
     */
    private String isDefault;

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

}
