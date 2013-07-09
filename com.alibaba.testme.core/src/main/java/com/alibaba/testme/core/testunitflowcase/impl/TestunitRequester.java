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
package com.alibaba.testme.core.testunitflowcase.impl;

import com.alibaba.testme.client.testunit.context.TestunitContext;
import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.core.testunit.ITestunitClient;
import com.alibaba.testme.core.testunitflowcase.ITestunitRequester;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.service.SystemEnvService;

/**
 * 测试单元调用请求器
 * 
 * @author chongan.wangca
 */
public class TestunitRequester implements ITestunitRequester {

    private ITestunitClient  testunitClient;

    private SystemEnvService systemEnvService;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitRequestManager#doRequest
     * (com.alibaba.testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public TestunitResult doRequest(TestunitFlowCaseContext testunitFlowContext) {

        TestunitContext testunitContext = new TestunitContext();
        testunitContext.setClassQualifiedName(testunitFlowContext.getTestunitClassQualifiedName());
        testunitContext.setSystemEnvId(testunitFlowContext.getSystemEnvId());
        testunitContext.setTestunitFlowCaseDetailId(testunitFlowContext
                .getTestunitFlowCaseDetailId());
        testunitContext.setTestunitFlowCaseId(testunitFlowContext.getTestunitFlowCaseId());

        //设置输入参数和环境变量参数
        testunitContext.setInputParamsMap(testunitFlowContext.getInputParams()
                .getFromLastAndUserParamsMap());
        testunitContext.setHistoryParamsMap(testunitFlowContext.getInputParams()
                .getFromHistoryParamsMap());
        testunitContext.setSystemEnvParamsMap(systemEnvService
                .buildSystemEnvParamsMap(testunitContext.getSystemEnvId()));

        //执行调用Testunit
        return testunitClient.invoke(testunitContext);
    }

    public void setTestunitClient(ITestunitClient testunitClient) {
        this.testunitClient = testunitClient;
    }

    public void setSystemEnvService(SystemEnvService systemEnvService) {
        this.systemEnvService = systemEnvService;
    }

}
