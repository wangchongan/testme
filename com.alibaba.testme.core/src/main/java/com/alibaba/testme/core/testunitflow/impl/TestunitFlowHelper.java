/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-24
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
package com.alibaba.testme.core.testunitflow.impl;

import java.util.Map;

import com.alibaba.testme.core.testunitflow.ITestunitFlowHelper;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;

/**
 * TODO Comment of TestunitFlowHelper
 * 
 * @author chongan.wangca
 */
public class TestunitFlowHelper implements ITestunitFlowHelper {

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#hasExecutableNode
     * (java.lang.Integer)
     */
    @Override
    public boolean hasExecutableNode(Long testunitFlowCaseId) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#
     * setTestunitFlowPaused
     * (com.alibaba.testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public void setTestunitFlowPaused(TestunitFlowContext testunitFlowContext) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#
     * buildTestunitFlowContext(java.lang.Integer, java.util.Map)
     */
    @Override
    public TestunitFlowContext buildTestunitFlowContext(Integer testunitFlowContext,
                                                        Map<String, String> inputParamsMap) {
        // TODO Auto-generated method stub
        return null;
    }

}
