/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-24
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

import com.alibaba.testme.common.enums.TestunitFlowCaseDetailStatusEnum;
import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseHelper;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowDetailService;

/**
 * TestunitFlow辅助类
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseHelper implements ITestunitFlowCaseHelper {

    private TestunitFlowDetailService     testunitFlowDetailService;
    private TestunitFlowCaseService       testunitFlowCaseService;
    private TestunitFlowCaseDetailService testunitFlowCaseDetailService;

    /*
     * 判断是否存在下一个节点
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#hasExecutableNode
     * (java.lang.Integer)
     */
    @Override
    public boolean hasExecutableNode(Long testunitFlowDetailId) {

        TestunitFlowDetailDO testunitFlowDetailDO = testunitFlowDetailService
                .findById(testunitFlowDetailId);
        if (testunitFlowDetailDO.getNextTestunitId() != null) {
            return true;
        }
        return false;

    }

    /*
     * 设置暂停等待用户输入参数
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#setPaused(com
     * .alibaba.testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public void setPaused(TestunitFlowCaseContext testunitFlowContext) {

        TestunitFlowCaseDO testunitFlowCaseDO = testunitFlowCaseService
                .findById(testunitFlowContext.getTestunitFlowCaseId());
        TestunitFlowCaseDetailDO testunitFlowCaseDetailDO = testunitFlowCaseDetailService
                .findById(testunitFlowContext.getTestunitFlowCaseDetailId());

        testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.PAUSED.getKey());
        testunitFlowCaseService.updateTestunitFlowCaseDO(testunitFlowCaseDO);

        testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.PAUSED.getKey());
        testunitFlowCaseDetailService.updateTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);

    }

    /*
     * 设置执行中状态
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowHelper#setRunning(com
     * .alibaba.testme.core.testunitflow.context.TestunitFlowContext)
     */
    @Override
    public void setRunning(TestunitFlowCaseContext testunitFlowContext) {

        TestunitFlowCaseDO testunitFlowCaseDO = testunitFlowCaseService
                .findById(testunitFlowContext.getTestunitFlowCaseId());
        TestunitFlowCaseDetailDO testunitFlowCaseDetailDO = testunitFlowCaseDetailService
                .findById(testunitFlowContext.getTestunitFlowCaseDetailId());

        testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.RUNNING.getKey());
        testunitFlowCaseService.updateTestunitFlowCaseDO(testunitFlowCaseDO);

        testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.RUNNING.getKey());
        testunitFlowCaseDetailService.updateTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);

    }

    public void setTestunitFlowDetailService(TestunitFlowDetailService testunitFlowDetailService) {
        this.testunitFlowDetailService = testunitFlowDetailService;
    }

    public void setTestunitFlowCaseService(TestunitFlowCaseService testunitFlowCaseService) {
        this.testunitFlowCaseService = testunitFlowCaseService;
    }

    public void setTestunitFlowCaseDetailService(TestunitFlowCaseDetailService testunitFlowCaseDetailService) {
        this.testunitFlowCaseDetailService = testunitFlowCaseDetailService;
    }

}
