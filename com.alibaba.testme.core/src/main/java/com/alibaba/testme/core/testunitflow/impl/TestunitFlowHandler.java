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

import java.util.Map;

import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflow.ITestunitFlowContextBuilder;
import com.alibaba.testme.core.testunitflow.ITestunitFlowHandler;
import com.alibaba.testme.core.testunitflow.ITestunitFlowHelper;
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

    private ITestunitFlowHelper         testunitFlowHelper;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.TestunitFlowHandler#deal(com.alibaba
     * .testme.core.testunitflow.dto.TestRequestDTO)
     */
    @Override
    public TestunitFlowResult deal(TestRequestDTO testRequestDTO) {

        TestunitFlowResult allTestunitFlowResult = new TestunitFlowResult();

        Long testunitFlowCaseId = testRequestDTO.getTestunitFlowCaseId();
        Map<String, String> userInputParamsMap = testRequestDTO.getUserInputParamsMap();
        TestunitFlowResult stageTestunitFlowResult = null;

        //对输入参数进行校验,不合法则立即返回
        CheckResult testRequestCheckResult = testRequestChecker.check(testRequestDTO);
        if (testRequestCheckResult.getResult() == CheckResultEnum.FAIL) {
            allTestunitFlowResult.setStatus(TestunitDealStatusEnum.FAIL);
            allTestunitFlowResult.addAllErrorMsgs(testRequestCheckResult.getErrorMsgsList());
            return allTestunitFlowResult;
        }

        /**
         * 依次处理每个测试实例节点
         */
        do {

            stageTestunitFlowResult = new TestunitFlowResult();

            /**
             * 1.构建context
             */
            TestunitFlowContext testunitFlowContext = defaultTestunitFlowContextBuilder.build(
                    testunitFlowCaseId, userInputParamsMap);

            /**
             * 2.交由核心处理器进行处理
             */
            stageTestunitFlowResult = defaultTestunitFlowWorker.doWork(testunitFlowContext);

            /**
             * 3.汇总结果
             */
            stage2All(stageTestunitFlowResult, allTestunitFlowResult);

            /**
             * 4.清理动作<br/>
             * 由于下次运行时已无用户输入参数，所以该参数需要清空
             */
            userInputParamsMap = null;

        } while (testunitFlowHelper.hasExecutableNode(testunitFlowCaseId)
                && stageTestunitFlowResult.getStatus() == TestunitDealStatusEnum.SUCCESS);

        allTestunitFlowResult.setStatus(stageTestunitFlowResult.getStatus());
        return allTestunitFlowResult;
    }

    /**
     * 将阶段节点的结果添加至总结果集中
     * 
     * @param stage
     * @param all
     */
    public void stage2All(TestunitFlowResult stageResult, TestunitFlowResult allResult) {
        allResult.addAllMsgs(stageResult.getMsgsList());
        allResult.addAllErrorMsgs(stageResult.getErrorMsgsList());
        allResult.setAbsentParamsList(stageResult.getAbsentParamsList());
    }
}
