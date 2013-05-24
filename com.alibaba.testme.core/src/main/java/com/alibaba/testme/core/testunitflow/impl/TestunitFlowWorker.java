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

import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.testunitflow.ITestunitFlowAfterProcessor;
import com.alibaba.testme.core.testunitflow.ITestunitFlowHelper;
import com.alibaba.testme.core.testunitflow.ITestunitFlowWorker;
import com.alibaba.testme.core.testunitflow.ITestunitRequester;
import com.alibaba.testme.core.testunitflow.checker.BeforeTestunitFlowWorkChecker;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;
import com.alibaba.testme.core.testunitflow.dto.ParamsMatchCheckResult;
import com.alibaba.testme.core.testunitflow.dto.TestunitFlowResult;

/**
 * @author chongan.wangca
 */
public class TestunitFlowWorker implements ITestunitFlowWorker {

    private BeforeTestunitFlowWorkChecker beforeTestunitFlowWorkChecker;

    private ITestunitRequester            testunitRequester;

    private ITestunitFlowAfterProcessor   testunitFlowAfterProcessor;

    private ITestunitFlowHelper           testunitFlowHelper;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.TestunitFlowWorker#doWork(com.alibaba
     * .testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public TestunitFlowResult doWork(TestunitFlowContext testunitFlowContext) {

        TestunitFlowResult result = new TestunitFlowResult();

        //1.前置校验
        CheckResult checkResult = beforeTestunitFlowWorkChecker.check(testunitFlowContext);
        if (checkResult.getResult() == CheckResultEnum.FAIL) {
            result.setStatus(TestunitDealStatusEnum.FAIL);
            result.addAllErrorMsgs(checkResult.getErrorMsgsList());
            return result;
        }

        //2.校验是否缺少必录参数
        ParamsMatchCheckResult paramsMatchCheckResult = testunitFlowContext
                .getTestunitDefParamsManager().isRequiredParamsMatch(
                        testunitFlowContext.getInputParams());
        //如果缺少必录参数，则直接返回让前台用户输入参数
        if (!paramsMatchCheckResult.isMatch()) {
            testunitFlowHelper.setTestunitFlowPaused(testunitFlowContext);
            result.setStatus(TestunitDealStatusEnum.PAUSED);
            result.setAbsentParamsList(paramsMatchCheckResult.getAbsentParamsList());
            return result;
        }

        //3.执行调用Testunit
        TestunitResult testunitResult = testunitRequester.doRequest(testunitFlowContext);

        //4.后置善后处理
        testunitFlowAfterProcessor.doProcess(testunitFlowContext, testunitResult);

        //5.状态设置及日志设置返回
        result.statusMapping(testunitResult.getStatus());
        result.addAllMsgs(testunitResult.getMsgsList());
        result.addAllErrorMsgs(testunitResult.getErrorMsgsList());

        return result;
    }

    public void setBeforeTestunitFlowWorkChecker(BeforeTestunitFlowWorkChecker beforeTestunitFlowWorkChecker) {
        this.beforeTestunitFlowWorkChecker = beforeTestunitFlowWorkChecker;
    }

}
