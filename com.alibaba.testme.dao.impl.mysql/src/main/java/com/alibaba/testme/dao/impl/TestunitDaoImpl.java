package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitDao;
import com.alibaba.testme.domain.dataobject.TestunitDO;

/**
 * Testunit Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitDaoImpl extends SqlMapClientDaoSupport implements TestunitDao {

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public Integer addTestunitDO(TestunitDO testunitDO) {
        return (Integer) this.getSqlMapClientTemplate().insert("testunit.add", testunitDO);
    }

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public Integer updateTestunitDO(TestunitDO testunitDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunit.update", testunitDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunit.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitDO findById(Long id) {
        return (TestunitDO) this.getSqlMapClientTemplate().queryForObject("testunit.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitDO> findList(TestunitDO testunitDO) {
        return (List<TestunitDO>) this.getSqlMapClientTemplate().queryForList("testunit.findList",
                testunitDO);
    }

}
