/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-19
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
package com.alibaba.testme.domain.vo;

import java.util.List;

/**
 * 任务建立输入参数VO
 * 
 * @author lz
 */
public class TestFlowParamVO {
    private Long                      testunitFlowCaseId;

    private Long                      testunitFlowId;
    private String                    testunitFlowName;
    private String                    testunitFlowTag;

    private Long                      systemEnvId;

    private String                    userName;
    private String                    wangwang;

    // 当前测试单元
    private Long                      testunitId;
    private String                    testunitName;
    private String                    testunitCode;
    private String                    testunitTag;

    // 当前测试单元入参
    private List<TestFlowUnitParamVO> testFlowUnitParamVOs;

    public Long getTestunitFlowId() {
        return testunitFlowId;
    }

    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

    public Long getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Long testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    public Long getSystemEnvId() {
        return systemEnvId;
    }

    public void setSystemEnvId(Long systemEnvId) {
        this.systemEnvId = systemEnvId;
    }

    public Long getTestunitId() {
        return testunitId;
    }

    public void setTestunitId(Long testunitId) {
        this.testunitId = testunitId;
    }

    public String getTestunitName() {
        return testunitName;
    }

    public void setTestunitName(String testunitName) {
        this.testunitName = testunitName;
    }

    public String getTestunitCode() {
        return testunitCode;
    }

    public void setTestunitCode(String testunitCode) {
        this.testunitCode = testunitCode;
    }

    public String getTestunitTag() {
        return testunitTag;
    }

    public void setTestunitTag(String testunitTag) {
        this.testunitTag = testunitTag;
    }

    public List<TestFlowUnitParamVO> getTestFlowUnitParamVOs() {
        return testFlowUnitParamVOs;
    }

    public void setTestFlowUnitParamVOs(List<TestFlowUnitParamVO> testFlowUnitParamVOs) {
        this.testFlowUnitParamVOs = testFlowUnitParamVOs;
    }

    public String getTestunitFlowName() {
        return testunitFlowName;
    }

    public void setTestunitFlowName(String testunitFlowName) {
        this.testunitFlowName = testunitFlowName;
    }

    public String getTestunitFlowTag() {
        return testunitFlowTag;
    }

    public String[] getTestunitFlowTags() {
        String[] result = new String[0];
        if (testunitFlowTag != null && testunitFlowTag.length() > 0) {
            result = this.testunitFlowTag.split(",");
        }
        return result;
    }

    public void setTestunitFlowTag(String testunitFlowTag) {
        this.testunitFlowTag = testunitFlowTag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang;
    }

}
