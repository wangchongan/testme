package com.alibaba.testme.domain.dataobject;

import java.util.Date;

/**
 * TestunitDO
 */
public class TestunitDO {

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
     * workSpaceId
     */
    private Long   workSpaceId;

    /**
     * classQualifiedName
     */
    private String classQualifiedName;

    /**
     * version
     */
    private String version;

    /**
     * tag
     */
    private String tag;

    /**
     * userId
     */
    private Long   userId;

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

    public void setWorkSpaceId(Long workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public Long getWorkSpaceId() {
        return this.workSpaceId;
    }

    public void setClassQualifiedName(String classQualifiedName) {
        this.classQualifiedName = classQualifiedName;
    }

    public String getClassQualifiedName() {
        return this.classQualifiedName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

}
