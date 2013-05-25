package com.alibaba.testme.service;

import java.util.List;

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
    public int addSystemEnvDO(SystemEnvDO systemEnvDO);

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

}
