package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.SystemEnvDetailDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;

/**
 * SystemEnvDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvDetailDaoImpl extends SqlMapClientDaoSupport implements SystemEnvDetailDao {

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("systemEnvDetail.add",
                systemEnvDetailDO);
    }

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public Integer updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        return (Integer) this.getSqlMapClientTemplate().update("systemEnvDetail.update",
                systemEnvDetailDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteSystemEnvDetailDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("systemEnvDetail.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemEnvDetailDO findById(Long id) {
        return (SystemEnvDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "systemEnvDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO) {
        return (List<SystemEnvDetailDO>) this.getSqlMapClientTemplate().queryForList(
                "systemEnvDetail.findList", systemEnvDetailDO);
    }

}
