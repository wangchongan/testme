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

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.core.testunitflow.ITestunitFlowContextBuilder;
import com.alibaba.testme.core.testunitflow.context.InputParams;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowDetailService;
import com.alibaba.testme.service.TestunitFlowService;
import com.alibaba.testme.service.TestunitParamService;
import com.alibaba.testme.service.TestunitService;

/**
 * TODO Comment of TestunitFlowContextBuilder
 * 
 * @author chongan.wangca
 */
public class TestunitFlowContextBuilder implements ITestunitFlowContextBuilder {

    private TestunitFlowService           testunitFlowService;
    private TestunitFlowCaseService       testunitFlowCaseService;
    private TestunitService               testunitService;
    private TestunitParamService          testunitParamService;
    private TestunitFlowCaseDetailService testunitFlowCaseDetailService;
    private TestunitFlowDetailService     testunitFlowDetailService;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.ITestunitFlowContextBuilder#build
     * (java.lang.Integer, java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public TestunitFlowContext build(Long testunitFlowCaseId, Map<String, String> userInputParamsMap) {

        //测试实例
        TestunitFlowCaseDO testunitFlowCaseDO = testunitFlowCaseService
                .findById(testunitFlowCaseId);

        //当前最后一个测试实例节点
        TestunitFlowCaseDetailDO testunitFlowCaseDetailDO = testunitFlowCaseDetailService
                .findLast(testunitFlowCaseId);

        //测试流程节点
        TestunitFlowDetailDO testunitFlowDetailDO = testunitFlowDetailService
                .findById(testunitFlowCaseDetailDO.getTestunitFlowDetailId());

        //测试单元
        TestunitDO testunitDO = testunitService.findById(testunitFlowDetailDO.getTestunitId());

        //测试单元所需参数
        TestunitParamDO testunitParamQuery = new TestunitParamDO();
        testunitParamQuery.setTestunitId(testunitDO.getId());
        List<TestunitParamDO> testunitParamDOList = testunitParamService
                .findList(testunitParamQuery);

        //设置属性
        TestunitFlowContext context = new TestunitFlowContext();
        context.setTestunitFlowCaseId(testunitFlowCaseId);
        context.setUserId(Long.valueOf(testunitFlowCaseDO.getUserId()));
        context.setTestunitClassQualifiedName(testunitDO.getClassQualifiedName());
        context.setTestunitFlowCaseDetailId(testunitFlowCaseDetailDO.getId());
        context.setTestunitFlowCaseDetailStatus(testunitFlowCaseDetailDO.getStatus());
        context.setTestunitFlowCaseStatus(testunitFlowCaseDO.getStatus());

        //设置来自上一节点的输入参数
        InputParams inputParams = new InputParams();
        if (testunitFlowCaseDetailDO.getInParam() != null) {
            Map<String, String> lastInParamsMap = (Map<String, String>) JSON
                    .parse(testunitFlowCaseDetailDO.getInParam());
            inputParams.setFromLastTestunitParamsMap(lastInParamsMap);
        }
        //设置来自用户的输入参数
        inputParams.setFromUserParamsMap(userInputParamsMap);
        context.setInputParams(inputParams);

        //        context.setTestunitDefParamsManager(testunitDefParamsManager)
        //        context.setTestunitDefParamsManager(testunitDefParamsManager)
        return null;
    }
}
