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
package com.alibaba.testme.core.testunitflow.checker;

import com.alibaba.testme.core.common.dto.CheckResult;
import com.alibaba.testme.core.common.enums.CheckResultEnum;
import com.alibaba.testme.core.common.interfaces.BaseChecker;
import com.alibaba.testme.core.testunitflow.dto.impl.DefaultTestRequestDTO;

/**
 * TODO Comment of TestRequestChecker
 * 
 * @author chongan.wangca
 */
public class TestRequestChecker implements BaseChecker<DefaultTestRequestDTO> {

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.common.interfaces.BaseChecker#check(java.lang
     * .Object)
     */
    @Override
    public CheckResult check(DefaultTestRequestDTO obj) {
        CheckResult checkResult = new CheckResult();
        if (obj.getTestunitFlowCaseId() == null || obj.getTestunitFlowCaseId().intValue() <= 0) {
            checkResult.setResult(CheckResultEnum.FAIL);
            checkResult.addErrorMsg("TestunitFlowCaseId²»ÄÜÎª¿Õ");
            return checkResult;
        }
        return checkResult;
    }

}
