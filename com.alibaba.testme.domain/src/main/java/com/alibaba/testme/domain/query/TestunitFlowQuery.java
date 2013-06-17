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
package com.alibaba.testme.domain.query;

import com.alibaba.testme.common.constants.CommonConstants;

/**
 * 测试流程查询类
 * 
 * @author xiaopenzi
 */
public class TestunitFlowQuery {
    /**
     * 页码
     */
    private int    pageIndex;
    /**
     * 每页显示条数
     */
    private int    sizePerPage;
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
    private String testunitFLowName;
    /**
     * 系统ID
     */
    private Long   systemId;
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

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex == 0 ? CommonConstants.PAGE_INDEX : pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the sizePerPage
     */
    public int getSizePerPage() {
        return sizePerPage == 0 ? CommonConstants.SIZE_PERPAGE : sizePerPage;
    }

    /**
     * @param sizePerPage the sizePerPage to set
     */
    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

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
     * @return the testunitFLowName
     */
    public String getTestunitFLowName() {
        return testunitFLowName;
    }

    /**
     * @param testunitFLowName the testunitFLowName to set
     */
    public void setTestunitFLowName(String testunitFLowName) {
        this.testunitFLowName = testunitFLowName;
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

}
