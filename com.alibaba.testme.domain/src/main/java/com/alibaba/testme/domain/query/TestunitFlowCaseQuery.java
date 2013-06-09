/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-7
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

import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;

/**
 * 任务查询
 * 
 * @author lz
 */
public class TestunitFlowCaseQuery {

    private int                        pageIndex;
    private int                        sizePerPage;

    private Long                       systemId;
    private Long                       testunitFlowId;
    private Long                       systemEnvId;
    private TestunitFlowCaseStatusEnum testunitFlowCaseStatusEnum;

    public TestunitFlowCaseQuery() {
        super();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public Long getTestunitFlowId() {
        return testunitFlowId;
    }

    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

    public Long getSystemEnvId() {
        return systemEnvId;
    }

    public void setSystemEnvId(Long systemEnvId) {
        this.systemEnvId = systemEnvId;
    }

    public TestunitFlowCaseStatusEnum getTestunitFlowCaseStatusEnum() {
        return testunitFlowCaseStatusEnum;
    }

    public void setTestunitFlowCaseStatusEnum(TestunitFlowCaseStatusEnum testunitFlowCaseStatusEnum) {
        this.testunitFlowCaseStatusEnum = testunitFlowCaseStatusEnum;
    }

}
