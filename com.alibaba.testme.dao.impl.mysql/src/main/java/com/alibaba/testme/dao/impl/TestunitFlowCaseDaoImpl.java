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
    public int addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("testunitFlowCase.add",
                testunitFlowCaseDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public int updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("testunitFlowCase.update",
                testunitFlowCaseDO);
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
    public int deleteTestunitFlowCaseDO(Integer id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "testunitFlowCase.deleteById", id);
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
    public TestunitFlowCaseDO findById(Integer id) {
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
