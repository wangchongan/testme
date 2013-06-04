/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangjun Aug 5, 2011 6:46:14 PM
 */
@SuppressWarnings("rawtypes")
public class ParamMap extends HashMap {
    private static final long serialVersionUID = 2183452622984973515L;

    @SuppressWarnings("unchecked")
    public ParamMap put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public void addAll(Map parameters) {
        this.putAll(parameters);
    }

    public Map toMap() {
        return this;
    }

    /**
     * 根据分页参数创建ParamMap对象，使用缺省的每页20条记录
     * 
     * @param index 当前页码
     * @return
     */
    public static ParamMap create(Integer index) {
        return create(index, null);
    }

    /**
     * 根据分页参数创建ParamMap对象
     * 
     * @param index 当前页码
     * @param size 每页显示的记录数
     * @return
     */
    public static ParamMap create(Integer index, Integer size) {
        ParamMap param = new ParamMap();
        if (index != null) {
            param.put(Page.INDEX, index);
        }

        if (size != null) {
            param.put(Page.SIZE, size);
        } else {
            param.put(Page.SIZE, Page.DEFAULT_SIZE_PER_PAGE);
        }
        return param;
    }

    public static ParamMap create() {
        return new ParamMap();
    }

    public static Map createEmptyMap() {
        return create();
    }
}
