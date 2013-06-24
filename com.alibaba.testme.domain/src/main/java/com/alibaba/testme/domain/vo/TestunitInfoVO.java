/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-21
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
 * TODO Comment of TestunitInfoVO
 * 
 * @author lz
 */
public class TestunitInfoVO {

    private Long                      testunitId;
    private String                    testunitName;
    private String                    testunitCode;
    private String                    testunitTag;
    private String                    userName;

    private List<TestunitParamInfoVO> testunitParamInfoVOs;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TestunitParamInfoVO> getTestunitParamInfoVOs() {
        return testunitParamInfoVOs;
    }

    public void setTestunitParamInfoVOs(List<TestunitParamInfoVO> testunitParamInfoVOs) {
        this.testunitParamInfoVOs = testunitParamInfoVOs;
    }
}
