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
package com.alibaba.testme.core.testunit.impl;

import com.alibaba.testme.client.testunit.ITestunitHandler;
import com.alibaba.testme.client.testunit.context.TestunitContext;
import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;
import com.alibaba.testme.core.bundle.exception.BundleManagerException;
import com.alibaba.testme.core.bundle.manager.TestMeBundleManager;
import com.alibaba.testme.core.testunit.ITestunitClient;

/**
 * 测试单元调用器
 * 
 * @author chongan.wangca
 */
public class TestunitClient implements ITestunitClient {

    private TestMeBundleManager testMeBundleManager;

    @Override
    public TestunitResult invoke(TestunitContext testunitContext) {
        //获取Testunit并且执行调用动作
        TestunitResult result = new TestunitResult();
        try {
            String className = testunitContext.getClassQualifiedName();
            ITestunitHandler testunitHandler = testMeBundleManager.getService(
                    ITestunitHandler.class, className);
            if (testunitHandler == null) {
                throw new BundleManagerException("serviceName : " + className
                        + " is not found, process could not continue");
            }
            return testunitHandler.deal(testunitContext);
        } catch (Exception e) {
            result.addErrorMsg(e.getMessage());
        }

        result.setStatus(TestunitResultStatus.FAIL);

        return result;
    }

    public void setTestMeBundleManager(TestMeBundleManager testMeBundleManager) {
        this.testMeBundleManager = testMeBundleManager;
    }
}
