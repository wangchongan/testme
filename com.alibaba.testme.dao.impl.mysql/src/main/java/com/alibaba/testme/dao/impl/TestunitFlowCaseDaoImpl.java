package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowCaseDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;

/**
 * TestunitFlowCase Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseDaoImpl extends SqlMapClientDaoSupport implements TestunitFlowCaseDao {

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public Long addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitFlowCase.add",
                testunitFlowCaseDO);
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunitFlowCase.update",
                testunitFlowCaseDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowCaseDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunitFlowCase.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowCaseDO findById(Long id) {
        return (TestunitFlowCaseDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlowCase.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitFlowCaseDO> findList(TestunitFlowCaseDO testunitFlowCaseDO) {
        return (List<TestunitFlowCaseDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitFlowCase.findList", testunitFlowCaseDO);
    }

}
