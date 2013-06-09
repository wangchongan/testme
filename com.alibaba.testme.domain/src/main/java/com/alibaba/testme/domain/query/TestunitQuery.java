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
 * 测试单元查询类
 * 
 * @author xiaopenzi
 */
public class TestunitQuery {
    /**
     * 页码
     */
    private int    pageIndex;
    /**
     * 每页显示条数
     */
    private int    sizePerPage;
    /**
     * 测试单元名称
     */
    private String testunitName;
    /**
     * 测试单元编码
     */
    private String testunitCode;
    /**
     * 所属系统
     */
    private String systemId;
    /**
     * 测试单元标签
     */
    private String testunitTag;
    /**
     * 所属工作空间
     */
    private Long   workSpaceId;

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
     * @return the testunitName
     */
    public String getTestunitName() {
        return testunitName;
    }

    /**
     * @param testunitName the testunitName to set
     */
    public void setTestunitName(String testUnitName) {
        this.testunitName = testUnitName;
    }

    /**
     * @return the testunitCode
     */
    public String getTestunitCode() {
        return testunitCode;
    }

    /**
     * @param testunitCode the testunitCode to set
     */
    public void setTestunitCode(String testunitCode) {
        this.testunitCode = testunitCode;
    }

    /**
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the testunitTag
     */
    public String getTestunitTag() {
        return testunitTag;
    }

    /**
     * @param testunitTag the testunitTag to set
     */
    public void setTestunitTag(String testunitTag) {
        this.testunitTag = testunitTag;
    }

    /**
     * @return the workSpaceId
     */
    public Long getWorkSpaceId() {
        return workSpaceId;
    }

    /**
     * @param workSpaceId the workSpaceId to set
     */
    public void setWorkSpaceId(Long workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

}
