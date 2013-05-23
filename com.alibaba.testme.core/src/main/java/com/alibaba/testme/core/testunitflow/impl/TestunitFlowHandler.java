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

import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflow.ITestunitFlowContextBuilder;
import com.alibaba.testme.core.testunitflow.ITestunitFlowHandler;
import com.alibaba.testme.core.testunitflow.ITestunitFlowWorker;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;
import com.alibaba.testme.core.testunitflow.dto.TestRequestDTO;
import com.alibaba.testme.core.testunitflow.dto.TestunitFlowResult;

/**
 * 单向流程TestunitFlow处理总入口
 * 
 * @author chongan.wangca
 */
public class TestunitFlowHandler implements ITestunitFlowHandler {

    private BaseChecker<TestRequestDTO> testRequestChecker;

    private ITestunitFlowContextBuilder defaultTestunitFlowContextBuilder;

    private ITestunitFlowWorker         defaultTestunitFlowWorker;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.TestunitFlowHandler#deal(com.alibaba
     * .testme.core.testunitflow.dto.TestRequestDTO)
     */
    @Override
    public TestunitFlowResult deal(TestRequestDTO testRequestDTO) {

        TestunitFlowResult testunitFlowResult = new TestunitFlowResult();

        //1、对输入参数进行校验
        CheckResult testRequestCheckResult = testRequestChecker.check(testRequestDTO);
        if (testRequestCheckResult.getResult() == CheckResultEnum.FAIL) {
            testunitFlowResult.addAllErrorMsgs(testRequestCheckResult.getErrorMsgsList());
            return testunitFlowResult;
        }

        //2、构建context
        TestunitFlowContext testunitFlowContext = defaultTestunitFlowContextBuilder
                .build(testRequestDTO);

        //3、交由核心处理器进行处理
        testunitFlowResult = defaultTestunitFlowWorker.doWork(testunitFlowContext);

        return testunitFlowResult;
    }
}
