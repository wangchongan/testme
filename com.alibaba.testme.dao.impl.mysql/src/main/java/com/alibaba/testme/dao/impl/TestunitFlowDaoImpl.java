package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;

/**
 * TestunitFlow Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDaoImpl extends SqlMapClientDaoSupport implements TestunitFlowDao {

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public Long addTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitFlow.add", testunitFlowDO);
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunitFlow.update",
                testunitFlowDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunitFlow.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowDO findById(Long id) {
        return (TestunitFlowDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlow.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO) {
        return (List<TestunitFlowDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitFlow.findList", testunitFlowDO);
    }

}
