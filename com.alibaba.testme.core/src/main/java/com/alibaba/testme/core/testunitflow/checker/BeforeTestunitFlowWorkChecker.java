/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-22
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
package com.alibaba.testme.core.testunitflow.checker;

import com.alibaba.testme.common.enums.TestunitFlowStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;

/**
 * 进入核心处理器前置校验
 * 
 * @author chongan.wangca
 */
public class BeforeTestunitFlowWorkChecker implements BaseChecker<TestunitFlowContext> {

    private BaseChecker<String> testunitFlowCaseStatusChecker;

    /**
     * 校验是否满足执行测试单元的需求
     * 
     * @param testunitFlowContext
     * @return
     */
    public CheckResult check(TestunitFlowContext testunitFlowContext) {

        CheckResult checkResult = new CheckResult();

        //1.校验测试实例状态是否满足可运行条件
        String status = testunitFlowContext.getTestunitFlowCaseStatus();
        CheckResult statusCheckResult = testunitFlowCaseStatusChecker.check(status);

        if (statusCheckResult.getResult() == CheckResultEnum.FAIL) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addAllErrorMsgs(statusCheckResult.getErrorMsgsList());
            return checkResult;
        }

        //2.如果测试实例状态处于暂停状态，则判断是否有传入参数
        if (TestunitFlowStatusEnum.PAUSED.getKey().equals(status)
                && !testunitFlowContext.getInputParams().hasFromUserParams()) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addErrorMsg("测试实例处于等待用户录入参数状态，请务必录入参数再提交执行。");
            return checkResult;
        }

        return checkResult;
    }

    public void setTestunitFlowCaseStatusChecker(BaseChecker<String> testunitFlowCaseStatusChecker) {
        this.testunitFlowCaseStatusChecker = testunitFlowCaseStatusChecker;
    }

}
