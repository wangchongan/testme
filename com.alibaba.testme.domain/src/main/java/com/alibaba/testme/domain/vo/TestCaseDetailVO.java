/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-17
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

import com.alibaba.testme.common.enums.TestunitFlowCaseDetailStatusEnum;

/**
 * 测试任务单元明细
 * 
 * @author lz
 */
public class TestCaseDetailVO {
    private Long   id;
    private String testunitName;
    private String inParam;
    private String outParam;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestunitName() {
        return testunitName;
    }

    public void setTestunitName(String testunitName) {
        this.testunitName = testunitName;
    }

    public String getInParam() {
        return inParam;
    }

    public void setInParam(String inParam) {
        this.inParam = inParam;
    }

    public String getOutParam() {
        return outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusStr() {
        if (this.status != null) {
            return TestunitFlowCaseDetailStatusEnum.getName(this.status);
        }
        return "未知";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
