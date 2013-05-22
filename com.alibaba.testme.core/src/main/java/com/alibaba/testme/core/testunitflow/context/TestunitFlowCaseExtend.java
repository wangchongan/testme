/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-22
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
package com.alibaba.testme.core.testunitflow.context;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;

/**
 * TODO Comment of TestunitFlowCaseExtend
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseExtend extends TestunitFlowCaseDO {

    private List<TestunitFlowCaseDetailDO> testunitFlowCaseDetailDOList;

    public List<TestunitFlowCaseDetailDO> getTestunitFlowCaseDetailDOList() {
        return testunitFlowCaseDetailDOList;
    }

    public void setTestunitFlowCaseDetailDOList(List<TestunitFlowCaseDetailDO> testunitFlowCaseDetailDOList) {
        this.testunitFlowCaseDetailDOList = testunitFlowCaseDetailDOList;
    }

}
