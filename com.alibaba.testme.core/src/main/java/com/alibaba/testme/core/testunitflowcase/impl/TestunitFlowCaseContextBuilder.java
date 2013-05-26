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
package com.alibaba.testme.core.testunitflowcase.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseContextBuilder;
import com.alibaba.testme.core.testunitflowcase.context.InputParams;
import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowDetailService;
import com.alibaba.testme.service.TestunitParamService;
import com.alibaba.testme.service.TestunitService;

/**
 * 构造测试流程上下文环境信息
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseContextBuilder implements ITestunitFlowCaseContextBuilder {

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
    public TestunitFlowCaseContext build(Long testunitFlowCaseId,
                                         Map<String, String> userInputParamsMap) {

        /******************* 获取数据实体信息 *******************/
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

        /****************** 设置属性 *******************/
        //设置属性
        TestunitFlowCaseContext context = new TestunitFlowCaseContext();
        context.setTestunitFlowCaseId(testunitFlowCaseId);
        context.setUserId(Long.valueOf(testunitFlowCaseDO.getUserId()));
        context.setTestunitClassQualifiedName(testunitDO.getClassQualifiedName());
        context.setTestunitFlowCaseDetailId(testunitFlowCaseDetailDO.getId());
        context.setTestunitFlowCaseDetailStatus(testunitFlowCaseDetailDO.getStatus());
        context.setTestunitFlowCaseStatus(testunitFlowCaseDO.getStatus());
        context.setGmtStart(new Date());
        context.setSystemEnvId(testunitFlowCaseDO.getSystemEnvId());

        //设置来自上一节点的输入参数
        InputParams inputParams = new InputParams();
        if (testunitFlowCaseDetailDO.getInParam() != null) {
            Map<String, String> lastInParamsMap = (Map<String, String>) JSON
                    .parse(testunitFlowCaseDetailDO.getInParam());
            inputParams.setFromLastTestunitParamsMap(lastInParamsMap);
        }
        //设置来自用户的输入参数
        inputParams.setFromUserParamsMap(userInputParamsMap);

        //设置历史的参数
        Map<String, String> historyParamsMap = buildHistoryParamsMap(testunitFlowCaseId,
                testunitFlowCaseDetailDO);
        inputParams.setFromHistoryParamsMap(historyParamsMap);

        context.setInputParams(inputParams);

        //添加必需的参数定义信息
        context.getTestunitDefParamsManager().addParams(testunitParamDOList);

        return context;
    }

    /**
     * 构建历史节点的参数
     * 
     * @param testunitFlowCaseId
     * @param testunitFlowCaseDetailDO
     * @param historyParamsMap
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> buildHistoryParamsMap(Long testunitFlowCaseId,
                                                      TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        Map<String, String> historyParamsMap = new HashMap<String, String>();
        TestunitFlowCaseDetailDO testunitFlowCaseDetailQuery = new TestunitFlowCaseDetailDO();
        testunitFlowCaseDetailQuery.setTestunitFlowCaseId(testunitFlowCaseId);
        List<TestunitFlowCaseDetailDO> testunitFlowCaseDetailDOList = testunitFlowCaseDetailService
                .findList(testunitFlowCaseDetailQuery);
        for (TestunitFlowCaseDetailDO detailDO : testunitFlowCaseDetailDOList) {
            if (detailDO.getId().longValue() != testunitFlowCaseDetailDO.getId().longValue()) {
                historyParamsMap.putAll(JSON.parseObject(detailDO.getInParam(), Map.class));
            }
        }
        return historyParamsMap;
    }

    public void setTestunitFlowCaseService(TestunitFlowCaseService testunitFlowCaseService) {
        this.testunitFlowCaseService = testunitFlowCaseService;
    }

    public void setTestunitService(TestunitService testunitService) {
        this.testunitService = testunitService;
    }

    public void setTestunitParamService(TestunitParamService testunitParamService) {
        this.testunitParamService = testunitParamService;
    }

    public void setTestunitFlowCaseDetailService(TestunitFlowCaseDetailService testunitFlowCaseDetailService) {
        this.testunitFlowCaseDetailService = testunitFlowCaseDetailService;
    }

    public void setTestunitFlowDetailService(TestunitFlowDetailService testunitFlowDetailService) {
        this.testunitFlowDetailService = testunitFlowDetailService;
    }

}
