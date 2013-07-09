/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-26
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

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.common.enums.TestunitFlowCaseDetailStatusEnum;
import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseGenerator;
import com.alibaba.testme.core.testunitflowcase.dto.TestRequestDTO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowDetailService;

/**
 * 首次执行时，测试单元实例数据初始化生成器
 * 
 * @author chongan.wangca
 */
public class TestunitFlowCaseGenerator implements ITestunitFlowCaseGenerator {

    private TestunitFlowDetailService     testunitFlowDetailService;
    private TestunitFlowCaseService       testunitFlowCaseService;
    private TestunitFlowCaseDetailService testunitFlowCaseDetailService;

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseGenerator#generate
     * (com.alibaba.testme.core.testunitflowcase.dto.TestRequestDTO)
     */
    @Override
    public Long generate(TestRequestDTO testRequestDTO) {

        //获取第一个测试单元节点
        Long firstTestunitFlowDetailId = testunitFlowDetailService
                .findFirstTestunitFlowDetailId(testRequestDTO.getTestunitFlowId());

        /******************** 新建TestunitFlowCase ********************/
        TestunitFlowCaseDO testunitFlowCaseDO = new TestunitFlowCaseDO();
        testunitFlowCaseDO.setCreator(CommonConstants.CREATOR);
        testunitFlowCaseDO.setModifier(CommonConstants.MODIFIER);
        testunitFlowCaseDO.setStatus(TestunitFlowCaseStatusEnum.WAITING.getKey());
        testunitFlowCaseDO.setSystemId(testRequestDTO.getSystemId());
        testunitFlowCaseDO.setSystemEnvId(testRequestDTO.getSystemEnvId());
        testunitFlowCaseDO.setTestunitFlowId(testRequestDTO.getTestunitFlowId());
        testunitFlowCaseDO.setUserId(testRequestDTO.getUserId());
        Long testunitFlowCaseId = testunitFlowCaseService.addTestunitFlowCaseDO(testunitFlowCaseDO);

        /******************** 新建TestunitFlowCaseDetail ********************/
        TestunitFlowCaseDetailDO testunitFlowCaseDetailDO = new TestunitFlowCaseDetailDO();
        testunitFlowCaseDetailDO.setCreator(CommonConstants.CREATOR);
        testunitFlowCaseDetailDO.setModifier(CommonConstants.MODIFIER);
        if (testRequestDTO.getUserInputParamsMap() != null) {
            String inputParamsMapJSON = JSON.toJSONString(testRequestDTO.getUserInputParamsMap());
            testunitFlowCaseDetailDO.setInParam(inputParamsMapJSON);
        }
        testunitFlowCaseDetailDO.setStatus(TestunitFlowCaseDetailStatusEnum.WAITING.getKey());
        testunitFlowCaseDetailDO.setTestunitFlowCaseId(testunitFlowCaseId);
        testunitFlowCaseDetailDO.setTestunitFlowDetailId(firstTestunitFlowDetailId);
        testunitFlowCaseDetailService.addTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);

        return testunitFlowCaseId;

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
