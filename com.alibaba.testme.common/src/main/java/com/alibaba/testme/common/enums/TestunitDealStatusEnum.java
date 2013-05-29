/**
 * Project: com.alibaba.testme.common
 * 
 * File Created at 2013-5-21
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
 * TestunitFlowCaseDetail执行的结果<br/>
 * 即每个节点执行的结果，作为每次请求返回的结果
 * 
 * @author chongan.wangca
 */
public enum TestunitDealStatusEnum {

    SUCCESS("SUCCESS", "执行成功"),
    FAIL("FAIL", "执行失败"),
    RETRY("RETRY", "稍后重试"),
    PAUSED("PAUSED", "需要录入参数而暂停");

    private String key;
    private String name;

    private TestunitDealStatusEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (TestunitDealStatusEnum obj : TestunitDealStatusEnum.values()) {
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
