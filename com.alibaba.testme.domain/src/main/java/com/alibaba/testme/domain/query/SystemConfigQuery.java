/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-4
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
package com.alibaba.testme.domain.query;

/**
 * 系统配置信息查询类
 * 
 * @author xiaopenzi
 */
public class SystemConfigQuery {
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 所属系统
     */
    private Long   systemId;
    /**
     * 配置类型
     */
    private String configType;
    /**
     * 参数名称
     */
    private String propKey;

    /**
     * @return the configName
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * @param configName the configName to set
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * @return the systemId
     */
    public Long getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the configType
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * @param configType the configType to set
     */
    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * @return the propKey
     */
    public String getPropKey() {
        return propKey;
    }

    /**
     * @param propKey the propKey to set
     */
    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

}
