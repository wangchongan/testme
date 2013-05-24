package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.SystemDao;
import com.alibaba.testme.domain.dataobject.SystemDO;

/**
 * System Dao Implement
 * 
 * @author xiaopenzi
 */
public class SystemDaoImpl extends SqlMapClientDaoSupport implements SystemDao {

    /**
     * @param systemDO
     * @return
     */
    @Override
    public Integer addSystemDO(SystemDO systemDO) {
        return (Integer) this.getSqlMapClientTemplate().insert("system.add", systemDO);
    }

    /**
     * @param systemDO
     * @return
     */
    @Override
    public Integer updateSystemDO(SystemDO systemDO) {
        return (Integer) this.getSqlMapClientTemplate().update("system.update", systemDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteSystemDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("system.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemDO findById(Long id) {
        return (SystemDO) this.getSqlMapClientTemplate().queryForObject("system.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemDO> findList(SystemDO systemDO) {
        return (List<SystemDO>) this.getSqlMapClientTemplate().queryForList("system.findList",
                systemDO);
    }

}
