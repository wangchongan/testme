package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowCaseDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.domain.vo.TestCaseDetailVO;

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
    public Long addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitFlowCaseDetail.add",
                testunitFlowCaseDetailDO);
    }

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public int updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunitFlowCaseDetail.update",
                testunitFlowCaseDetailDO);
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
    public int deleteTestunitFlowCaseDetailDO(Long id) {
        return this.getSqlMapClientTemplate().delete(
                "testunitFlowCaseDetail.deleteByTestunitFlowCaseId", id);
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
        return this.getSqlMapClientTemplate().queryForList("testunitFlowCaseDetail.findList",
                testunitFlowCaseDetailDO);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.dao.TestunitFlowCaseDetailDao#findLast(java.lang.Integer
     * )
     */
    @Override
    public TestunitFlowCaseDetailDO findLast(Long testunitFlowCaseId) {
        return (TestunitFlowCaseDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlowCaseDetail.findLast", testunitFlowCaseId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestCaseDetailVO> queryTestCaseDetailList(Long id) {
        return this.getSqlMapClientTemplate().queryForList(
                "testunitFlowCaseDetail.queryTestCaseDetailList", id);
    }

}
