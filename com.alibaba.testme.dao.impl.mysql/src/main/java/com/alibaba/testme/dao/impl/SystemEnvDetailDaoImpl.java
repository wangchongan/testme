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
    public int addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("systemEnvDetail.add",
                systemEnvDetailDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public int updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("systemEnvDetail.update",
                systemEnvDetailDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteSystemEnvDetailDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.deleteById", id);
        if (result == null) {
            return 0;
        }
        return result;
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
