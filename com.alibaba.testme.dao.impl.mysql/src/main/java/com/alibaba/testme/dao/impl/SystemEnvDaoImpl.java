package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.SystemEnvDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;

/**
 * SystemEnv Dao Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvDaoImpl extends SqlMapClientDaoSupport implements SystemEnvDao {

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public Long addSystemEnvDO(SystemEnvDO systemEnvDO) {
        return (Long) this.getSqlMapClientTemplate().insert("systemEnv.add", systemEnvDO);
    }

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public Integer updateSystemEnvDO(SystemEnvDO systemEnvDO) {
        return (Integer) this.getSqlMapClientTemplate().update("systemEnv.update", systemEnvDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteSystemEnvDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("systemEnv.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemEnvDO findById(Long id) {
        return (SystemEnvDO) this.getSqlMapClientTemplate()
                .queryForObject("systemEnv.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemEnvDO> findList(SystemEnvDO systemEnvDO) {
        return (List<SystemEnvDO>) this.getSqlMapClientTemplate().queryForList(
                "systemEnv.findList", systemEnvDO);
    }

}
