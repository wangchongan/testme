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
 * 表单控件类型枚举类
 * 
 * @author xiaopenzi
 */
public enum FormCtrlTypeEnum {
    //下拉菜单
    SELECT_TYPE("selectType", "下拉菜单"),
    //文本框
    TEXT_TYPE("textType", "文本框");

    private String key;
    private String value;

    private FormCtrlTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        for (FormCtrlTypeEnum obj : FormCtrlTypeEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj.getValue();
            }
        }

        return null;
    }

    public static String getKey(String value) {
        for (FormCtrlTypeEnum obj : FormCtrlTypeEnum.values()) {
            if (obj.getValue().equals(value)) {
                return obj.getKey();
            }
        }
        return null;
    }

    public static FormCtrlTypeEnum parse(String key) {
        for (FormCtrlTypeEnum obj : FormCtrlTypeEnum.values()) {
            if (obj.getKey().equals(key)) {
                return obj;
            }
        }
        return null;
    }

    public static List<FormCtrlTypeEnum> getList() {
        List<FormCtrlTypeEnum> configTypeEnumList = new ArrayList<FormCtrlTypeEnum>();
        for (FormCtrlTypeEnum obj : FormCtrlTypeEnum.values()) {
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
