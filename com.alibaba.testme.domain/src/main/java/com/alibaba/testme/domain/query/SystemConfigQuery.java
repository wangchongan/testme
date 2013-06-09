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

import com.alibaba.testme.common.constants.CommonConstants;

/**
 * 系统配置信息查询类
 * 
 * @author xiaopenzi
 */
public class SystemConfigQuery {
    /**
     * 页码
     */
    private int    pageIndex;
    /**
     * 每页显示条数
     */
    private int    sizePerPage;
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
     * 用户ID
     */
    private Long   userId;
    /**
     * 系统环境ID
     */
    private Long   systemEnvId;

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex == 0 ? CommonConstants.PAGE_INDEX : pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the sizePerPage
     */
    public int getSizePerPage() {
        return sizePerPage == 0 ? CommonConstants.SIZE_PERPAGE : sizePerPage;
    }

    /**
     * @param sizePerPage the sizePerPage to set
     */
    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
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

}
