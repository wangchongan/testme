package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemEnvDO;

/**
 * SystemEnv Dao Interface
 * 
 * @author xiaopenzi
 */
public interface SystemEnvDao {

    /**
     * @param systemEnvDO
     * @return
     */
    public int addSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param systemEnvDO
     * @return
     */
    public int updateSystemEnvDO(SystemEnvDO systemEnvDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemEnvDO(Integer id);

    /**
     * @param id
     * @return
     */
    public SystemEnvDO findById(Integer id);

    /**
     * @param systemEnvDO
     * @return
     */
    public List<SystemEnvDO> findList(SystemEnvDO systemEnvDO);

}
