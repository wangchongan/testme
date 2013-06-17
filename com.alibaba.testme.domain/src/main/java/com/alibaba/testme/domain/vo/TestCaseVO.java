/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-14
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
 * 测试任务详情
 * 
 * @author lz
 */
public class TestCaseVO {
    private Long                   id;
    private String                 testunitFlowCaseName; // 测试单元流程名称
    private String                 gmtStart;            // 开始执行时间
    private String                 gmtEnd;              // 任务结束时间
    private String                 status;              // 任务执行状态

    private String                 systemName;          // 所属系统
    private String                 configName;          // 配置名称

    private String                 userName;            // 作者
    private String                 remark;              // 测试单元流程描述

    private List<TestCaseDetailVO> testCaseDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTestunitFlowCaseName() {
        return testunitFlowCaseName;
    }

    public void setTestunitFlowCaseName(String testunitFlowCaseName) {
        this.testunitFlowCaseName = testunitFlowCaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TestCaseDetailVO> getTestCaseDetailList() {
        return testCaseDetailList;
    }

    public void setTestCaseDetailList(List<TestCaseDetailVO> testCaseDetailList) {
        this.testCaseDetailList = testCaseDetailList;
    }

}
