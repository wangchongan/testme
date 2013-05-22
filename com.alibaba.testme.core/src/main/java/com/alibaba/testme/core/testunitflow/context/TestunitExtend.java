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

import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TODO Comment of TestunitExtend
 * 
 * @author chongan.wangca
 */
public class TestunitExtend extends TestunitDO {

    private List<TestunitParamDO> testunitParamDOList;

    public List<TestunitParamDO> getTestunitParamDOList() {
        return testunitParamDOList;
    }

    public void setTestunitParamDOList(List<TestunitParamDO> testunitParamDOList) {
        this.testunitParamDOList = testunitParamDOList;
    }

}
