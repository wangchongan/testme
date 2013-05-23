package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemEnvDO;

/**
 * SystemEnv Dao Interface
 * @author xiaopenzi
 */
public interface SystemEnvDao {

    /**
     * @param systemEnvDO
     * @return
     */
    public Long addSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param systemEnvDO
     * @return
     */
    public Integer updateSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteSystemEnvDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemEnvDO findById(Long id);

    /**
     * @param systemEnvDO
     * @return
     */
    public List<SystemEnvDO> findList(SystemEnvDO systemEnvDO);

}
