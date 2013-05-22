/**
 * Project: com.alibaba.testme.common
 * 
 * File Created at 2013-5-22
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
 * 测试实例的状态枚举
 * 
 * @author chongan.wangca
 */
public enum TestunitFlowStatusEnum {

    SUCCESS("SUCCESS", "成功执行而完成"),
    FAIL("FAIL", "因异常执行失败而完成"),
    RETRY("RETRY", "稍后重试"),
    PAUSED("PAUSED", "需要录入参数而暂停"),
    RUNNING("RUNNING", "执行中"),
    WAITING("WAITING", "未执行"),
    STOP("STOP", "中止执行而完成");

    private String key;
    private String name;

    private TestunitFlowStatusEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (TestunitFlowStatusEnum obj : TestunitFlowStatusEnum.values()) {
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
