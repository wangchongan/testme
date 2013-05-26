/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-21
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

import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflowcase.dto.TestRequestDTO;

/**
 * TODO Comment of TestRequestChecker
 * 
 * @author chongan.wangca
 */
public class TestRequestChecker implements BaseChecker<TestRequestDTO> {

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.common.interfaces.BaseChecker#check(java.lang
     * .Object)
     */
    @Override
    public CheckResult check(TestRequestDTO obj) {

        CheckResult checkResult = new CheckResult();

        if (obj.getTestunitFlowCaseId() == null && obj.getTestunitFlowId() == null) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addErrorMsg("TestunitFlowCaseId和TestunitFlowId不能全为空");
            return checkResult;
        }

        if (obj.getTestunitFlowId() != null && obj.getTestunitFlowCaseId() != null) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addErrorMsg("TestunitFlowCaseId和TestunitFlowId不能全非空");
            return checkResult;
        }

        return checkResult;

    }

}
