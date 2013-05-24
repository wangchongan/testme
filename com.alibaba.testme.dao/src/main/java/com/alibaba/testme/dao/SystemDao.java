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
    public int addSystemDO(SystemDO systemDO);

    /**
     * @param systemDO
     * @return
     */
    public int updateSystemDO(SystemDO systemDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemDO(Integer id);

    /**
     * @param id
     * @return
     */
    public SystemDO findById(Integer id);

    /**
     * @param systemDO
     * @return
     */
    public List<SystemDO> findList(SystemDO systemDO);

}
