package com.alibaba.testme.client.common;

public interface RemoteApiCallbackDataCache {

    public boolean isExists(String key);

    public Object get(String key);

    public Object put(String key, Object value);

    public Object remove(String key);

}
