package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;

/**
 * SystemRequireProp Service Interface
 * 
 * @author xiaopenzi
 */
public interface SystemRequirePropService {

    /**
     * @param systemRequirePropDO
     * @return
     */
    public int addSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO);

    /**
     * @param DO
     * @return
     */
    public int updateSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemRequirePropDO(Integer id);

    /**
     * @param id
     * @return
     */
    public SystemRequirePropDO findById(Integer id);

    /**
     * @param id
     * @return
     */
    public List<SystemRequirePropDO> findList(SystemRequirePropDO systemRequirePropDO);

}
