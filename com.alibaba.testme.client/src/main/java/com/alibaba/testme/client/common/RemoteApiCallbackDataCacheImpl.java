/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-7-12
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
package com.alibaba.testme.client.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Comment of RemoteCallbackQueue
 * 
 * @author lz
 */
public class RemoteApiCallbackDataCacheImpl implements RemoteApiCallbackDataCache {

    private Map<String, Object> callbackDatas;

    public void init() {
        callbackDatas = new ConcurrentHashMap<String, Object>();
    }

    @Override
    public boolean isExists(String key) {
        return callbackDatas.containsKey(key);
    }

    @Override
    public Object put(String key, Object value) {
        return callbackDatas.put(key, value);
    }

    @Override
    public Object remove(String key) {
        return callbackDatas.remove(key);
    }

    @Override
    public Object get(String key) {
        return callbackDatas.get(key);
    }
}
