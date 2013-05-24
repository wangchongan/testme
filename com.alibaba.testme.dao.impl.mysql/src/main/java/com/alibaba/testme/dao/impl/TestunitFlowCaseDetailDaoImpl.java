package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowCaseDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;

/**
 * TestunitFlowCaseDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseDetailDaoImpl extends SqlMapClientDaoSupport implements
        TestunitFlowCaseDetailDao {

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public Integer addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        return (Integer) this.getSqlMapClientTemplate().insert("testunitFlowCaseDetail.add",
                testunitFlowCaseDetailDO);
    }

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        return (Integer) this.getSqlMapClientTemplate().update("testunitFlowCaseDetail.update",
                testunitFlowCaseDetailDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowCaseDetailDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("testunitFlowCaseDetail.deleteById",
                id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowCaseDetailDO findById(Long id) {
        return (TestunitFlowCaseDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlowCaseDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitFlowCaseDetailDO> findList(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        return (List<TestunitFlowCaseDetailDO>) this.getSqlMapClientTemplate().queryForList(
                "testunitFlowCaseDetail.findList", testunitFlowCaseDetailDO);
    }

}
