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
    public Integer addSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO);

    /**
     * @param DO
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
     * @param id
     * @return
     */
    public List<SystemRequirePropDO> findList(SystemRequirePropDO systemRequirePropDO);

}
