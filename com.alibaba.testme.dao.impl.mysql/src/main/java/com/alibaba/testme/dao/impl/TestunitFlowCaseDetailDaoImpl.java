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
    public int addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert(
                "testunitFlowCaseDetail.add", testunitFlowCaseDetailDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public int updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update(
                "testunitFlowCaseDetail.update", testunitFlowCaseDetailDO);
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
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "testunitFlowCaseDetail.deleteById", id);
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
