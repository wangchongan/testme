package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemDO;

/**
 * System Service Interface
 * 
 * @author xiaopenzi
 */
public interface SystemService {

    /**
     * @param systemDO
     * @return
     */
    public int addSystemDO(SystemDO systemDO);

    /**
     * @param DO
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
     * @param id
     * @return
     */
    public List<SystemDO> findList(SystemDO systemDO);

}
