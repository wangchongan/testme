package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemDO;

/**
 * System Dao Interface
 * 
 * @author xiaopenzi
 */
public interface SystemDao {

    /**
     * @param systemDO
     * @return
     */
    public Integer addSystemDO(SystemDO systemDO);

    /**
     * @param systemDO
     * @return
     */
    public Integer updateSystemDO(SystemDO systemDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteSystemDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemDO findById(Long id);

    /**
     * @param systemDO
     * @return
     */
    public List<SystemDO> findList(SystemDO systemDO);

}
