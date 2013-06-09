/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-7
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

import java.io.Serializable;

import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;

/**
 * TODO Comment of TestunitFlowCaseVO
 * @author lz
 *
 */
public class TestunitFlowCaseVO implements Serializable{
    
    private static final long serialVersionUID = -8305127753865398723L;
    
    private Long id;
    private String testunitFlowName;
    private String systemName;
    private Long invokeTimes;
    private String gmtStart;
    private String gmtEnd;
    private String systemEnvName;
    private TestunitFlowCaseStatusEnum status;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTestunitFlowName() {
        return testunitFlowName;
    }
    public void setTestunitFlowName(String testunitFlowName) {
        this.testunitFlowName = testunitFlowName;
    }
    public String getSystemName() {
        return systemName;
    }
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    public Long getInvokeTimes() {
        return invokeTimes;
    }
    public void setInvokeTimes(Long invokeTimes) {
        this.invokeTimes = invokeTimes;
    }
    public String getGmtStart() {
        return gmtStart;
    }
    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }
    public String getGmtEnd() {
        return gmtEnd;
    }
    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }
    public String getSystemEnvName() {
        return systemEnvName;
    }
    public void setSystemEnvName(String systemEnvName) {
        this.systemEnvName = systemEnvName;
    }
    public TestunitFlowCaseStatusEnum getStatus() {
        return status;
    }
    public void setStatus(TestunitFlowCaseStatusEnum status) {
        this.status = status;
    }
    
    
    
    
}
