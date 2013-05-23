package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;

/**
 * TestunitFlowDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDetailDaoImpl extends SqlMapClientDaoSupport implements
        TestunitFlowDetailDao {

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public Long addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitFlowDetail.add",
                testunitFlowDetailDO);
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunitFlowDetail.update",
                testunitFlowDetailDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowDetailDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunitFlowDetail.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowDetailDO findById(Long id) {
        return (TestunitFlowDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlowDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitFlowDetailDO> findList(TestunitFlowDetailDO testunitFlowDetailDO) {
        return (List<TestunitFlowDetailDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitFlowDetail.findList", testunitFlowDetailDO);
    }

}
