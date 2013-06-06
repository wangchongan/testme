/**
 * Project: lp4pl.supports
 * 
 * File Created at 2013-2-19
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

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类型
 * 
 * @author xiaopenzi
 */
public enum ConfigTypeEnum {
    //系统必要参数
    SYSTEM_REQUIRED("systemRequired", "系统必要参数"),
    //自定义参数
    USER_CUSTOM("userCustom", "用户自定义参数");

    private String key;
    private String value;

    private ConfigTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        for (ConfigTypeEnum obj : ConfigTypeEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj.getValue();
            }
        }

        return null;
    }

    public static String getKey(String value) {
        for (ConfigTypeEnum obj : ConfigTypeEnum.values()) {
            if (obj.getValue().equals(value)) {
                return obj.getKey();
            }
        }
        return null;
    }

    public static ConfigTypeEnum parse(String key) {
        for (ConfigTypeEnum obj : ConfigTypeEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj;
            }
        }
        return null;
    }

    public static List<ConfigTypeEnum> getList() {
        List<ConfigTypeEnum> configTypeEnumList = new ArrayList<ConfigTypeEnum>();
        for (ConfigTypeEnum obj : ConfigTypeEnum.values()) {
            configTypeEnumList.add(obj);
        }

        return configTypeEnumList;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
