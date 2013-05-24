package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitParamExtDao;
import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;

/**
 * TestunitParamExt Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamExtDaoImpl extends SqlMapClientDaoSupport implements TestunitParamExtDao {

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public int addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("testunitParamExt.add",
                testunitParamExtDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("testunitParamExt.update",
                testunitParamExtDO);
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
    public int deleteTestunitParamExtDO(Integer id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "testunitParamExt.deleteById", id);
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
    public TestunitParamExtDO findById(Integer id) {
        return (TestunitParamExtDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitParamExt.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO) {
        return (List<TestunitParamExtDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitParamExt.findList", testunitParamExtDO);
    }

}
