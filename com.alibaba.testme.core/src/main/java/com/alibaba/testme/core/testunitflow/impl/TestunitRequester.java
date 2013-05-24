/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-20
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

import com.alibaba.testme.client.testunit.dto.TestunitContext;
import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.core.testunit.ITestunitClient;
import com.alibaba.testme.core.testunitflow.ITestunitRequester;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;

/**
 * 测试单元调用请求器
 * 
 * @author chongan.wangca
 */
public class TestunitRequester implements ITestunitRequester {

    private ITestunitClient testunitClient;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitRequestManager#doRequest
     * (com.alibaba.testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public TestunitResult doRequest(TestunitFlowContext testunitFlowContext) {
        TestunitContext testunitContext = new TestunitContext();
        testunitContext.setClassQualifiedName(testunitFlowContext.getTestunitClassQualifiedName());
        testunitContext.setInputParamsMap(testunitFlowContext.getInputParams().getAllParams());
        return testunitClient.invoke(testunitContext);
    }

    public void setTestunitClient(ITestunitClient testunitClient) {
        this.testunitClient = testunitClient;
    }

}
