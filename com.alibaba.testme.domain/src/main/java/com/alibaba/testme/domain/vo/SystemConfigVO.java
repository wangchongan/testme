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
package com.alibaba.testme.domain.vo;

import com.alibaba.testme.common.enums.ConfigTypeEnum;

/**
 * 系统配置信息VO
 * 
 * @author xiaopenzi
 */
public class SystemConfigVO {
    /**
     * 配置详情ID
     */
    private Long   systemEnvDetailId;
    /**
     * 配置ID
     */
    private Long   systemEnvId;
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 所属系统
     */
    private Long   systemId;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 配置类型
     */
    private String configType;
    /**
     * 参数名称
     */
    private String propKey;
    /**
     * 参数值
     */
    private String propValue;
    /**
     * 用户ID
     */
    private Long   userId;
    /**
     * 备注：参数中文名
     */
    private String remark;

    /**
     * @return the systemEnvDetailId
     */
    public Long getSystemEnvDetailId() {
        return systemEnvDetailId;
    }

    /**
     * @param systemEnvDetailId the systemEnvDetailId to set
     */
    public void setSystemEnvDetailId(Long systemEnvDetailId) {
        this.systemEnvDetailId = systemEnvDetailId;
    }

    /**
     * @return the systemEnvId
     */
    public Long getSystemEnvId() {
        return systemEnvId;
    }

    /**
     * @param systemEnvId the systemEnvId to set
     */
    public void setSystemEnvId(Long systemEnvId) {
        this.systemEnvId = systemEnvId;
    }

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
     * @return the systemName
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName the systemName to set
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @return the configType
     */
    public String getConfigType() {
        if (configType != null) {
            return ConfigTypeEnum.getValue(configType);
        }
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

    /**
     * @return the propValue
     */
    public String getPropValue() {
        return propValue;
    }

    /**
     * @param propValue the propValue to set
     */
    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
