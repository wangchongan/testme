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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;
import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.common.enums.TestunitFlowCaseDetailStatusEnum;
import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseAfterProcessor;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowDetailService;

/**
 * 生成后续测试单元实例节点信息
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseAfterProcessor implements ITestunitFlowCaseAfterProcessor {

    private static final Logger           LOGGER = LoggerFactory
                                                         .getLogger(TestunitFlowCaseAfterProcessor.class);

    private TestunitFlowCaseService       testunitFlowCaseService;
    private TestunitFlowCaseDetailService testunitFlowCaseDetailService;
    private TestunitFlowDetailService     testunitFlowDetailService;

    /*
     * 处理主入口
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowRouter#doRoute(com.
     * alibaba.testme.core.testunitflow.context.TestunitFlowContext,
     * com.alibaba.testme.client.testunit.dto.TestunitResult)
     */
    @Override
    public void doProcess(TestunitFlowCaseContext testunitFlowCaseContext,
                          TestunitResult testunitResult) {

        /******************** 以下进行数据实体查询操作 ********************/

        Long testunitFlowCaseId = testunitFlowCaseContext.getTestunitFlowCaseId();

        //测试实例
        TestunitFlowCaseDO testunitFlowCaseDO = testunitFlowCaseService
                .findById(testunitFlowCaseId);

        //当前最后一个测试实例节点
        TestunitFlowCaseDetailDO testunitFlowCaseDetailDO = testunitFlowCaseDetailService
                .findLast(testunitFlowCaseId);

        //测试流程节点
        TestunitFlowDetailDO testunitFlowDetailDO = testunitFlowDetailService
                .findById(testunitFlowCaseDetailDO.getTestunitFlowDetailId());

        /******************** 以下进行数据更新操作 ********************/

        //设置测试单元流程开始执行时间
        if (testunitFlowCaseDO.getGmtStart() == null) {
            testunitFlowCaseDO.setGmtStart(testunitFlowCaseContext.getGmtStart());
        }

        //设置测试单元流程实例节点
        testunitFlowCaseDetailDO.setGmtStart(testunitFlowCaseContext.getGmtStart());
        testunitFlowCaseDetailDO.setGmtLastRun(new Date());
        testunitFlowCaseDetailDO.setInParam(testunitFlowCaseContext.getInputParams()
                .getFromLastAndUserParamsJSON());
        Map<String, Object> outputParamsMap = testunitResult.getOutputParamsMap();
        String outputParamsMapJSON = JSON.toJSONString(outputParamsMap);
        if (outputParamsMap != null) {
            testunitFlowCaseDetailDO.setOutParam(outputParamsMapJSON);
        }

        //设置状态信息
        setStatusByResult(testunitResult, testunitFlowCaseId, testunitFlowCaseDO,
                testunitFlowCaseDetailDO, testunitFlowDetailDO, outputParamsMapJSON);

        //执行更新操作
        testunitFlowCaseService.updateTestunitFlowCaseDO(testunitFlowCaseDO);
        testunitFlowCaseDetailService.updateTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);

        //新增下一个测试单元实例节点
        if (TestunitResultStatus.SUCCESS == testunitResult.getStatus()) {
            newInsertNextTestunitFlowCaseDetail(testunitFlowCaseId, testunitFlowDetailDO,
                    outputParamsMapJSON);
        }

    }

    /**
     * 设置状态
     * 
     * @param testunitResult
     * @param testunitFlowCaseId
     * @param testunitFlowCaseDO
     * @param testunitFlowCaseDetailDO
     * @param testunitFlowDetailDO
     * @param outputParamsMapJSON
     */
    private void setStatusByResult(TestunitResult testunitResult, Long testunitFlowCaseId,
                                   TestunitFlowCaseDO testunitFlowCaseDO,
                                   TestunitFlowCaseDetailDO testunitFlowCaseDetailDO,
                                   TestunitFlowDetailDO testunitFlowDetailDO,
                                   String outputParamsMapJSON) {
        //如果上次的执行结果是运行成功
        if (TestunitResultStatus.SUCCESS == testunitResult.getStatus()) {

            //更新状态，并且如果无下个节点，则结束
            if (testunitFlowDetailDO.getNextTestunitId() == null) {
                testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.ALL_OVER.getKey());
                testunitFlowCaseDO.setGmtEnd(new Date());
            } else {
                testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.SUB_OVER.getKey());
            }

            //只有执行成功该节点才认为执行结束
            testunitFlowCaseDetailDO.setGmtEnd(new Date());
            testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.SUCCESS.getKey());

        } else if (TestunitResultStatus.FAIL == testunitResult.getStatus()) {

            testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.SUB_OVER.getKey());
            testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.FAIL.getKey());

        } else if (TestunitResultStatus.RETRY == testunitResult.getStatus()) {

            testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.RETRY.getKey());
            testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.RETRY.getKey());

        } else {
            LOGGER.error("illegal execute result status:" + testunitResult.getStatus()
                    + ", testunitFlowCaseDetailId=" + testunitFlowCaseDetailDO.getId());
            throw new RuntimeException("不合法的执行结果状态:" + testunitResult.getStatus()
                    + ". testunitFlowCaseDetailId=" + testunitFlowCaseDetailDO.getId());
        }
    }

    /**
     * 新增下一个测试单元实例节点
     * 
     * @param testunitFlowCaseId
     * @param testunitFlowDetailDO
     * @param outputParamsMapJSON
     */
    private void newInsertNextTestunitFlowCaseDetail(Long testunitFlowCaseId,
                                                     TestunitFlowDetailDO testunitFlowDetailDO,
                                                     String outputParamsMapJSON) {
        TestunitFlowCaseDetailDO newNextTestunitFlowCaseDetailDO = new TestunitFlowCaseDetailDO();
        newNextTestunitFlowCaseDetailDO.setCreator(CommonConstants.CREATOR);
        newNextTestunitFlowCaseDetailDO.setModifier(CommonConstants.MODIFIER);
        newNextTestunitFlowCaseDetailDO.setInParam(outputParamsMapJSON);
        newNextTestunitFlowCaseDetailDO
                .setStatus(TestunitFlowCaseDetailStatusEnum.WAITING.getKey());
        newNextTestunitFlowCaseDetailDO.setTestunitFlowCaseId(testunitFlowCaseId);
        TestunitFlowDetailDO query = new TestunitFlowDetailDO();
        query.setTestunitId(testunitFlowDetailDO.getNextTestunitId());
        query.setTestunitFlowId(testunitFlowDetailDO.getTestunitFlowId());
        query.setPreTestunitId(testunitFlowDetailDO.getTestunitId());
        List<TestunitFlowDetailDO> detailList = testunitFlowDetailService.findList(query);
        if (detailList != null && detailList.size() > 0) {
            newNextTestunitFlowCaseDetailDO.setTestunitFlowDetailId(detailList.get(0).getId());
        }

        testunitFlowCaseDetailService.addTestunitFlowCaseDetailDO(newNextTestunitFlowCaseDetailDO);
    }

    public void setTestunitFlowCaseService(TestunitFlowCaseService testunitFlowCaseService) {
        this.testunitFlowCaseService = testunitFlowCaseService;
    }

    public void setTestunitFlowCaseDetailService(TestunitFlowCaseDetailService testunitFlowCaseDetailService) {
        this.testunitFlowCaseDetailService = testunitFlowCaseDetailService;
    }

    public void setTestunitFlowDetailService(TestunitFlowDetailService testunitFlowDetailService) {
        this.testunitFlowDetailService = testunitFlowDetailService;
    }

}
