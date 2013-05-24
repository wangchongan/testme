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
package com.alibaba.testme.core.testunitflow.dto;

import java.util.List;

import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;
import com.alibaba.testme.common.basedto.BaseResult;
import com.alibaba.testme.common.enums.TestunitDealStatusEnum;
import com.alibaba.testme.core.testunitflow.context.TestunitDefParam;

/**
 * TODO Comment of TestunitFlowResultDTO
 * 
 * @author chongan.wangca
 */
public class TestunitFlowResult extends BaseResult {

    //测试单元实例ID
    private Integer                testunitFlowCaseId;

    //测试单元执行结果状态
    private TestunitDealStatusEnum status;

    //缺少的必录参数
    private List<TestunitDefParam> absentParamsList;

    public TestunitDealStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TestunitDealStatusEnum status) {
        this.status = status;
    }

    public List<TestunitDefParam> getAbsentParamsList() {
        return absentParamsList;
    }

    public void setAbsentParamsList(List<TestunitDefParam> absentParamsList) {
        this.absentParamsList = absentParamsList;
    }

    public Integer getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Integer testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    /**
     * 根据Testunit执行结果的状态映射到TestunitFlow的状态
     * 
     * @param status
     */
    public void statusMapping(TestunitResultStatus status) {

        if (status == TestunitResultStatus.SUCCESS) {
            this.status = TestunitDealStatusEnum.SUCCESS;
            return;
        }
        if (status == TestunitResultStatus.FAIL) {
            this.status = TestunitDealStatusEnum.FAIL;
            return;
        }
        if (status == TestunitResultStatus.RETRY) {
            this.status = TestunitDealStatusEnum.RETRY;
            return;
        }

    }
}
