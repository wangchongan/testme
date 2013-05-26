/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-23
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
package com.alibaba.testme.core.testunitflowcase.context;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 输入参数
 * 
 * @author chongan.wangca
 */
public class InputParams {

    //来自上一测试单元输出的参数
    private Map<String, String> fromLastTestunitParamsMap;

    //来自用户输入的参数
    private Map<String, String> fromUserParamsMap;

    //来自历次测试单元节点输出的参数
    private Map<String, String> fromHistoryParamsMap;

    /**
     * 获取来自上一节点及用户输入的参数的JSON串
     * 
     * @return
     */
    public String getFromLastAndUserParamsJSON() {
        if (fromLastTestunitParamsMap == null && fromUserParamsMap == null) {
            return null;
        }
        if (fromLastTestunitParamsMap != null) {
            fromLastTestunitParamsMap.putAll(fromUserParamsMap);
            return JSON.toJSONString(fromLastTestunitParamsMap);
        } else {
            fromUserParamsMap.putAll(fromLastTestunitParamsMap);
            return JSON.toJSONString(fromUserParamsMap);
        }
    }

    /**
     * 判断是否存在指定的参数
     * 
     * @param paramName
     * @return
     */
    public boolean hasParam(String paramName) {
        if (fromLastTestunitParamsMap != null && fromLastTestunitParamsMap.containsKey(paramName)) {
            return true;
        }
        if (fromUserParamsMap != null && fromUserParamsMap.containsKey(paramName)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否存在来自用户输入的参数
     * 
     * @return true - 存在, false - 不存在
     */
    public boolean hasFromUserParams() {
        if (fromUserParamsMap != null && fromUserParamsMap.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有的入参
     * 
     * @return
     */
    public Map<String, String> getAllParams() {

        if (fromLastTestunitParamsMap == null && fromUserParamsMap == null
                && fromHistoryParamsMap == null) {
            return null;
        }
        Map<String, String> allParamsMap = new HashMap<String, String>();

        //先设置第一个节点的输入参数以及上上个及之前所有节点的输出参数
        if (fromHistoryParamsMap != null) {
            allParamsMap.putAll(fromHistoryParamsMap);
        }

        //再设置上个测试单元输出的参数
        if (fromLastTestunitParamsMap != null) {
            allParamsMap.putAll(fromLastTestunitParamsMap);
        }

        /**
         * 再调用用户自定义输入的参数，用户设置的参数优先级高于上个测试单元输出的参数<br/>
         * 即可能存在用户自定义输入的参数覆盖上个测试单元输出的参数的情况
         */
        if (fromUserParamsMap != null) {
            allParamsMap.putAll(fromUserParamsMap);
        }

        return allParamsMap;
    }

    public Map<String, String> getFromLastTestunitParamsMap() {
        return fromLastTestunitParamsMap;
    }

    public void setFromLastTestunitParamsMap(Map<String, String> fromLastTestunitParamsMap) {
        this.fromLastTestunitParamsMap = fromLastTestunitParamsMap;
    }

    public Map<String, String> getFromUserParamsMap() {
        return fromUserParamsMap;
    }

    public void setFromUserParamsMap(Map<String, String> fromUserParamsMap) {
        this.fromUserParamsMap = fromUserParamsMap;
    }

    public Map<String, String> getFromHistoryParamsMap() {
        return fromHistoryParamsMap;
    }

    public void setFromHistoryParamsMap(Map<String, String> fromHistoryParamsMap) {
        this.fromHistoryParamsMap = fromHistoryParamsMap;
    }

}
