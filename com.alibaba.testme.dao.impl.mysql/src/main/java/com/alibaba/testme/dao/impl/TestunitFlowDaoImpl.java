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
    public int addTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("testunitFlow.add",
                testunitFlowDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public int updateTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("testunitFlow.update",
                testunitFlowDO);
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
    public int deleteTestunitFlowDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete("testunitFlow.deleteById",
                id);
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
