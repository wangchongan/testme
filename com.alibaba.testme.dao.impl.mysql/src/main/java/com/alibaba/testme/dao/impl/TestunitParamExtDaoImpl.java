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
    public Integer addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        return (Integer) this.getSqlMapClientTemplate().insert("testunitParamExt.add",
                testunitParamExtDO);
    }

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public Integer updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunitParamExt.update",
                testunitParamExtDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitParamExtDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunitParamExt.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitParamExtDO findById(Long id) {
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
