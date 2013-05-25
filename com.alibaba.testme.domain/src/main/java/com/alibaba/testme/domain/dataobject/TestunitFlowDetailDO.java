package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * TestunitFlowDetailDO
 */
public class TestunitFlowDetailDO {

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
     * testunitId
     */
    private Long   testunitId;

    /**
     * preTestunitId
     */
    private Long   preTestunitId;

    /**
     * nextTestunitId
     */
    private Long   nextTestunitId;

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

    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

    public Long getTestunitFlowId() {
        return this.testunitFlowId;
    }

    public void setTestunitId(Long testunitId) {
        this.testunitId = testunitId;
    }

    public Long getTestunitId() {
        return this.testunitId;
    }

    public void setPreTestunitId(Long preTestunitId) {
        this.preTestunitId = preTestunitId;
    }

    public Long getPreTestunitId() {
        return this.preTestunitId;
    }

    public void setNextTestunitId(Long nextTestunitId) {
        this.nextTestunitId = nextTestunitId;
    }

    public Long getNextTestunitId() {
        return this.nextTestunitId;
    }

}
