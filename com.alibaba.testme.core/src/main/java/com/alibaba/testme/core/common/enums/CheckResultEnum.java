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
package com.alibaba.testme.core.common.enums;

/**
 * TODO Comment of CheckResultEnum
 * 
 * @author chongan.wangca
 */
public enum CheckResultEnum {

    SUCCESS("SUCCESS", "校验成功"),
    FAIL("FAIL", "校验失败");

    private String key;
    private String name;

    private CheckResultEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    static public String getName(String key) {
        for (CheckResultEnum obj : CheckResultEnum.values()) {
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
