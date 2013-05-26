package com.alibaba.testme.service;

import java.util.List;
import java.util.Map;

import com.alibaba.testme.domain.dataobject.SystemEnvDO;

/**
 * SystemEnv Service Interface
 * 
 * @author xiaopenzi
 */
public interface SystemEnvService {

    /**
     * @param systemEnvDO
     * @return
     */
    public Long addSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param DO
     * @return
     */
    public int updateSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemEnvDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemEnvDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<SystemEnvDO> findList(SystemEnvDO systemEnvDO);

    /**
     * 构建环境变量参数，如有占位符的会做占位符替换操作
     * 
     * @param systemEnvId
     * @return
     */
    public Map<String, String> buildSystemEnvParamsMap(Long systemEnvId);

}
