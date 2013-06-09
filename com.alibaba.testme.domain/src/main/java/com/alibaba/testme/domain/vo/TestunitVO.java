/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-9
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.domain.vo;

/**
 * 测试单元展示VO
 * 
 * @author xiaopenzi
 */
public class TestunitVO {
    /**
     * 测试单元ID
     */
    private Long   testunitId;
    /**
     * 测试单元名称
     */
    private String testunitName;
    /**
     * 测试单元编码
     */
    private String testunitCode;
    /**
     * 系统ID
     */
    private Long   systemId;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 测试单元标签
     */
    private String testunitTag;
    /**
     * 工作空间名称
     */
    private String workSpaceName;
    /**
     * 工作空间ID
     */
    private String workSpaceId;
    /**
     * 测试单元所在类路径
     */
    private String classQualifiedName;

    /**
     * 测试单元创建者用户
     */
    private Long   userId;

    /**
     * @return the testunitId
     */
    public Long getTestunitId() {
        return testunitId;
    }

    /**
     * @param testUnitId the testUnitId to set
     */
    public void setTestunitId(Long testunitId) {
        this.testunitId = testunitId;
    }

    /**
     * @return the testunitName
     */
    public String getTestunitName() {
        return testunitName;
    }

    /**
     * @param testunitName the testunitName to set
     */
    public void setTestunitName(String testunitName) {
        this.testunitName = testunitName;
    }

    /**
     * @return the testunitCode
     */
    public String getTestunitCode() {
        return testunitCode;
    }

    /**
     * @param testUnitCode the testUnitCode to set
     */
    public void setTestunitCode(String testunitCode) {
        this.testunitCode = testunitCode;
    }

    /**
     * @return the systemId
     */
    public Long getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the systemName
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName the systemName to set
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @return the testUnitTag
     */
    public String getTestunitTag() {
        return testunitTag;
    }

    /**
     * @param testUnitTag the testUnitTag to set
     */
    public void setTestunitTag(String testunitTag) {
        this.testunitTag = testunitTag;
    }

    /**
     * @return the workSpaceName
     */
    public String getWorkSpaceName() {
        return workSpaceName;
    }

    /**
     * @param workSpaceName the workSpaceName to set
     */
    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
    }

    /**
     * @return the workSpaceId
     */
    public String getWorkSpaceId() {
        return workSpaceId;
    }

    /**
     * @param workSpaceId the workSpaceId to set
     */
    public void setWorkSpaceId(String workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    /**
     * @return the classQualifiedName
     */
    public String getClassQualifiedName() {
        return classQualifiedName;
    }

    /**
     * @param classQualifiedName the classQualifiedName to set
     */
    public void setClassQualifiedName(String classQualifiedName) {
        this.classQualifiedName = classQualifiedName;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
