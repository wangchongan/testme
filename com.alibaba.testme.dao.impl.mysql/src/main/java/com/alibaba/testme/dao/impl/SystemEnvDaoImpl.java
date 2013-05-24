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
    public int addSystemEnvDO(SystemEnvDO systemEnvDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("systemEnv.add",
                systemEnvDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public int updateSystemEnvDO(SystemEnvDO systemEnvDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("systemEnv.update",
                systemEnvDO);
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
    public int deleteSystemEnvDO(Integer id) {
        Integer result = (Integer) this.getSqlMapClientTemplate()
                .delete("systemEnv.deleteById", id);
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
    public SystemEnvDO findById(Integer id) {
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
