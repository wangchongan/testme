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
package com.alibaba.testme.core.testunitflowcase.checker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;

/**
 * 校验测试实例的状态是否满足可执行条件
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseStatusChecker implements BaseChecker<String> {

    private static final Logger LOGGER = LoggerFactory
                                               .getLogger(TestunitFlowCaseStatusChecker.class);

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.common.interfaces.BaseChecker#check(java.lang
     * .Object)
     */
    @Override
    public CheckResult check(String obj) {
        String status = obj;
        CheckResult checkResult = new CheckResult();
        if (status == null || TestunitFlowCaseStatusEnum.ALL_OVER.getKey().equals(status)
                || TestunitFlowCaseStatusEnum.RUNNING.getKey().equals(status)) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addErrorMsg("该测试实例状态(" + status + ")不满足可执行的条件。");
            return checkResult;
        }
        return checkResult;
    }

}
