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

import java.util.Map;

import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseContextBuilder;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseGenerator;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseHandler;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseHelper;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseWorker;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.core.testunitflowcase.dto.TestRequestDTO;
import com.alibaba.testme.core.testunitflowcase.dto.TestunitFlowCaseResult;

/**
 * 单向流程TestunitFlow处理总入口
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseHandler implements ITestunitFlowCaseHandler {

    private BaseChecker<TestRequestDTO>     testRequestChecker;

    private ITestunitFlowCaseContextBuilder testunitFlowCaseContextBuilder;

    private ITestunitFlowCaseWorker         testunitFlowCaseWorker;

    private ITestunitFlowCaseHelper         testunitFlowCaseHelper;

    private ITestunitFlowCaseGenerator      testunitFlowCaseGenerator;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.TestunitFlowHandler#deal(com.alibaba
     * .testme.core.testunitflow.dto.TestRequestDTO)
     */
    @Override
    public TestunitFlowCaseResult deal(TestRequestDTO testRequestDTO) {

        TestunitFlowCaseResult allTestunitFlowCaseResult = new TestunitFlowCaseResult();
        Long testunitFlowCaseId = testRequestDTO.getTestunitFlowCaseId();
        TestunitFlowCaseContext testunitFlowCaseContext = null;
        Map<String, String> userInputParamsMap = testRequestDTO.getUserInputParamsMap();
        TestunitFlowCaseResult stageTestunitFlowCaseResult = null;

        //对输入参数进行校验,不合法则立即返回
        CheckResult testRequestCheckResult = testRequestChecker.check(testRequestDTO);
        if (testRequestCheckResult.getResult() == CheckResultEnum.FAIL) {
            allTestunitFlowCaseResult.setStatus(TestunitDealStatusEnum.FAIL);
            allTestunitFlowCaseResult.addAllErrorMsgs(testRequestCheckResult.getErrorMsgsList());
            return allTestunitFlowCaseResult;
        }

        //如果是首次执行，则需要初始化测试流程实例
        if (testunitFlowCaseId == null) {
            testunitFlowCaseId = testunitFlowCaseGenerator.generate(testRequestDTO);
        }

        /**
         * 依次处理每个测试实例节点
         */
        do {

            stageTestunitFlowCaseResult = new TestunitFlowCaseResult();

            /**
             * 1.构建context
             */
            testunitFlowCaseContext = testunitFlowCaseContextBuilder.build(testunitFlowCaseId,
                    userInputParamsMap);

            /**
             * 2.设置执行中状态
             */
            testunitFlowCaseHelper.setRunning(testunitFlowCaseContext);

            /**
             * 3.交由核心处理器进行处理
             */
            stageTestunitFlowCaseResult = testunitFlowCaseWorker.doWork(testunitFlowCaseContext);

            /**
             * 4.汇总结果
             */
            stage2All(stageTestunitFlowCaseResult, allTestunitFlowCaseResult);

            /**
             * 5.清理动作<br/>
             * 由于下次运行时已无用户输入参数，所以该参数需要清空
             */
            userInputParamsMap = null;

        } while (testunitFlowCaseHelper.hasExecutableNode(testunitFlowCaseContext
                .getTestunitFlowDetailId())
                && stageTestunitFlowCaseResult.getStatus() == TestunitDealStatusEnum.SUCCESS);

        allTestunitFlowCaseResult.setStatus(stageTestunitFlowCaseResult.getStatus());
        return allTestunitFlowCaseResult;
    }

    /**
     * 将阶段节点的结果添加至总结果集中
     * 
     * @param stage
     * @param all
     */
    public void stage2All(TestunitFlowCaseResult stageResult, TestunitFlowCaseResult allResult) {
        allResult.addAllMsgs(stageResult.getMsgsList());
        allResult.addAllErrorMsgs(stageResult.getErrorMsgsList());
        allResult.setAbsentParamsList(stageResult.getAbsentParamsList());
    }
}
