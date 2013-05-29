package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitParamDao;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TestunitParam Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamDaoImpl extends SqlMapClientDaoSupport implements TestunitParamDao {

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public Long addTestunitParamDO(TestunitParamDO testunitParamDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitParam.add", testunitParamDO);
    }

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("testunitParam.update",
                testunitParamDO);
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
    public int deleteTestunitParamDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "testunitParam.deleteById", id);
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
    public TestunitParamDO findById(Long id) {
        return (TestunitParamDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitParam.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO) {
        return (List<TestunitParamDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitParam.findList", testunitParamDO);
    }

}
