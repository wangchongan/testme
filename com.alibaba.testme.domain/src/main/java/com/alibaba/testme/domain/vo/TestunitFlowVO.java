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
 * 测试流程展示VO
 * 
 * @author xiaopenzi
 */
public class TestunitFlowVO {
    /**
     * 测试流程ID
     */
    private Long   testunitFlowId;
    /**
     * 测试流程编码
     */
    private String testunitFlowCode;
    /**
     * 测试流程名称
     */
    private String testunitFlowName;
    /**
     * 系统ID
     */
    private Long   systemId;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 测试流程标签
     */
    private String testunitFlowTag;
    /**
     * 是否需要系统配置参数
     */
    private String envConfigRequired;
    /**
     * 是否可用
     */
    private String isActive;

    private Long   userId;

    /**
     * @return the testunitFlowId
     */
    public Long getTestunitFlowId() {
        return testunitFlowId;
    }

    /**
     * @param testunitFlowId the testunitFlowId to set
     */
    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

    /**
     * @return the testunitFlowCode
     */
    public String getTestunitFlowCode() {
        return testunitFlowCode;
    }

    /**
     * @param testunitFlowCode the testunitFlowCode to set
     */
    public void setTestunitFlowCode(String testunitFlowCode) {
        this.testunitFlowCode = testunitFlowCode;
    }

    /**
     * @return the testunitFlowName
     */
    public String getTestunitFlowName() {
        return testunitFlowName;
    }

    /**
     * @param testunitFlowName the testunitFlowName to set
     */
    public void setTestunitFlowName(String testunitFlowName) {
        this.testunitFlowName = testunitFlowName;
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
     * @return the testunitFlowTag
     */
    public String getTestunitFlowTag() {
        return testunitFlowTag;
    }

    /**
     * @param testunitFlowTag the testunitFlowTag to set
     */
    public void setTestunitFlowTag(String testunitFlowTag) {
        this.testunitFlowTag = testunitFlowTag;
    }

    /**
     * @return the envConfigRequired
     */
    public String getEnvConfigRequired() {
        return envConfigRequired;
    }

    /**
     * @param envConfigRequired the envConfigRequired to set
     */
    public void setEnvConfigRequired(String envConfigRequired) {
        this.envConfigRequired = envConfigRequired;
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
