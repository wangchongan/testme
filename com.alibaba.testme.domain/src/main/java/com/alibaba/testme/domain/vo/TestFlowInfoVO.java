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

import com.alibaba.testme.domain.dataobject.SystemEnvDO;

/**
 * 测试流程VO
 * 
 * @author lz
 */
public class TestFlowInfoVO {
    private Long              testunitFlowCaseId;

    private Long              testunitFlowId;
    private String            testunitFlowName;
    private String            testunitFlowTag;
    private Long              systemId;
    private String            systemName;
    private String            userName;
    private String            wangwang;
    private String            remark;
    private String            isActive;

    private TestunitInfoVO    testunitInfoVO;
    private List<SystemEnvDO> systemEnvs;

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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public Long getSystemId() {
        return systemId;
    }

    public List<SystemEnvDO> getSystemEnvs() {
        return systemEnvs;
    }

    public void setSystemEnvs(List<SystemEnvDO> systemEnvs) {
        this.systemEnvs = systemEnvs;
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

    public TestunitInfoVO getTestunitInfoVO() {
        return testunitInfoVO;
    }

    public void setTestunitInfoVO(TestunitInfoVO testunitInfoVO) {
        this.testunitInfoVO = testunitInfoVO;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
