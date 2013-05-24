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
import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;
import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.testunitflow.ITestunitFlowWorker;
import com.alibaba.testme.core.testunitflow.ITestunitRequester;
import com.alibaba.testme.core.testunitflow.checker.BeforeTestunitFlowWorkChecker;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;
import com.alibaba.testme.core.testunitflow.dto.TestunitFlowResult;

/**
 * @author chongan.wangca
 */
public class TestunitFlowWorker implements ITestunitFlowWorker {

    private BeforeTestunitFlowWorkChecker beforeTestunitFlowWorkChecker;

    private ITestunitRequester            testunitRequester;

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

        TestunitResult testunitResult = testunitRequester.doRequest(testunitFlowContext);
        if (testunitResult.getStatus() == TestunitResultStatus.SUCCESS) {

        }
        return null;
    }

    public void setBeforeTestunitFlowWorkChecker(BeforeTestunitFlowWorkChecker beforeTestunitFlowWorkChecker) {
        this.beforeTestunitFlowWorkChecker = beforeTestunitFlowWorkChecker;
    }

}
