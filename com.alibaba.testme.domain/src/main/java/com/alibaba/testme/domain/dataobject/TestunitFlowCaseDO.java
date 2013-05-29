package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * TestunitFlowCaseDO
 */
public class TestunitFlowCaseDO {

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
     * testunitFlowId
     */
    private Long   testunitFlowId;

    /**
     * status
     */
    private String status;

    /**
     * gmtStart
     */
    private Date   gmtStart;

    /**
     * gmtEnd
     */
    private Date   gmtEnd;

    /**
     * userId
     */
    private Long   userId;

    /**
     * systemEnvId
     */
    private Long   systemEnvId;

    /**
     * gmtNextRetry
     */
    private Date   gmtNextRetry;

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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtStart() {
        return this.gmtStart;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Date getGmtEnd() {
        return this.gmtEnd;
    }

    public void setSystemEnvId(Long systemEnvId) {
        this.systemEnvId = systemEnvId;
    }

    public Long getSystemEnvId() {
        return this.systemEnvId;
    }

    public void setGmtNextRetry(Date gmtNextRetry) {
        this.gmtNextRetry = gmtNextRetry;
    }

    public Date getGmtNextRetry() {
        return this.gmtNextRetry;
    }

    public Long getTestunitFlowId() {
        return testunitFlowId;
    }

    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
