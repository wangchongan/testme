/**
 * Project: com.alibaba.testme.common
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
package com.alibaba.testme.common.enums;

/**
 * TODO Comment of TestunitFlowCaseDetailEnum
 * 
 * @author chongan.wangca
 */
public enum TestunitFlowCaseDetailStatusEnum {

    SUCCESS("SUCCESS", "成功执行而完成"),
    FAIL("FAIL", "因异常执行失败而完成"),
    RETRY("RETRY", "稍后重试"),
    PAUSED("PAUSED", "需要录入参数而暂停"),
    RUNNING("RUNNING", "执行中"),
    WAITING("WAITING", "未执行");

    private String key;
    private String name;

    private TestunitFlowCaseDetailStatusEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (TestunitFlowCaseDetailStatusEnum obj : TestunitFlowCaseDetailStatusEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj.getName();
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

}
