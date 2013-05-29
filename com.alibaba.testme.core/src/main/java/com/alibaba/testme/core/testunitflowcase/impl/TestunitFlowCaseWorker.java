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

import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseAfterProcessor;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseHelper;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseWorker;
import com.alibaba.testme.core.testunitflowcase.ITestunitRequester;
import com.alibaba.testme.core.testunitflowcase.checker.BeforeTestunitFlowCaseWorkChecker;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.core.testunitflowcase.dto.ParamsMatchCheckResult;
import com.alibaba.testme.core.testunitflowcase.dto.TestunitFlowCaseResult;

/**
 * @author chongan.wangca
 */
public class TestunitFlowCaseWorker implements ITestunitFlowCaseWorker {

    private BeforeTestunitFlowCaseWorkChecker beforeTestunitFlowCaseWorkChecker;

    private ITestunitRequester                testunitRequester;

    private ITestunitFlowCaseAfterProcessor   testunitFlowCaseAfterProcessor;

    private ITestunitFlowCaseHelper           testunitFlowCaseHelper;

    /**
     * 单次测试流程节点执行入口<br/>
     */
    @Override
    public TestunitFlowCaseResult doWork(TestunitFlowCaseContext testunitFlowCaseContext) {

        TestunitFlowCaseResult result = new TestunitFlowCaseResult();

        //1.前置校验
        CheckResult checkResult = beforeTestunitFlowCaseWorkChecker.check(testunitFlowCaseContext);
        if (checkResult.getResult() == CheckResultEnum.FAIL) {
            result.setStatus(TestunitDealStatusEnum.FAIL);
            result.addAllErrorMsgs(checkResult.getErrorMsgsList());
            return result;
        }

        //2.校验是否缺少必录参数
        ParamsMatchCheckResult paramsMatchCheckResult = testunitFlowCaseContext
                .getTestunitDefParamsManager().isRequiredParamsMatch(
                        testunitFlowCaseContext.getInputParams());
        //如果缺少必录参数，则直接返回让前台用户输入参数
        if (!paramsMatchCheckResult.isMatch()) {
            testunitFlowCaseHelper.setPaused(testunitFlowCaseContext);
            result.setStatus(TestunitDealStatusEnum.PAUSED);
            result.setAbsentParamsList(paramsMatchCheckResult.getAbsentParamsList());
            return result;
        }

        //3.执行调用Testunit
        TestunitResult testunitResult = testunitRequester.doRequest(testunitFlowCaseContext);

        //4.后置善后处理
        testunitFlowCaseAfterProcessor.doProcess(testunitFlowCaseContext, testunitResult);

        //5.状态设置及日志设置返回
        result.statusMapping(testunitResult.getStatus());
        result.addAllMsgs(testunitResult.getMsgsList());
        result.addAllErrorMsgs(testunitResult.getErrorMsgsList());

        return result;
    }

    public void setBeforeTestunitFlowCaseWorkChecker(BeforeTestunitFlowCaseWorkChecker beforeTestunitFlowCaseWorkChecker) {
        this.beforeTestunitFlowCaseWorkChecker = beforeTestunitFlowCaseWorkChecker;
    }

    public void setTestunitRequester(ITestunitRequester testunitRequester) {
        this.testunitRequester = testunitRequester;
    }

    public void setTestunitFlowCaseAfterProcessor(ITestunitFlowCaseAfterProcessor testunitFlowCaseAfterProcessor) {
        this.testunitFlowCaseAfterProcessor = testunitFlowCaseAfterProcessor;
    }

    public void setTestunitFlowCaseHelper(ITestunitFlowCaseHelper testunitFlowCaseHelper) {
        this.testunitFlowCaseHelper = testunitFlowCaseHelper;
    }

}
