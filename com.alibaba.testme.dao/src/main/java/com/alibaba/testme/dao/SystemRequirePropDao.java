package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;

/**
 * SystemRequireProp Dao Interface
 * 
 * @author xiaopenzi
 */
public interface SystemRequirePropDao {

    /**
     * @param systemRequirePropDO
     * @return
     */
    public Integer addSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO);

    /**
     * @param systemRequirePropDO
     * @return
     */
    public Integer updateSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteSystemRequirePropDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemRequirePropDO findById(Long id);

    /**
     * @param systemRequirePropDO
     * @return
     */
    public List<SystemRequirePropDO> findList(SystemRequirePropDO systemRequirePropDO);

}
