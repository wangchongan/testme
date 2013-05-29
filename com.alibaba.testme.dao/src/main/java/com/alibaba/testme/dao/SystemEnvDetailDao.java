package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;

/**
 * SystemEnvDetail Dao Interface
 * 
 * @author xiaopenzi
 */
public interface SystemEnvDetailDao {

    /**
     * @param systemEnvDetailDO
     * @return
     */
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * @param systemEnvDetailDO
     * @return
     */
    public int updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemEnvDetailDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemEnvDetailDO findById(Long id);

    /**
     * @param systemEnvDetailDO
     * @return
     */
    public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO);

}
