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
    public int addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("testunitFlowDetail.add",
                testunitFlowDetailDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public int updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update(
                "testunitFlowDetail.update", testunitFlowDetailDO);
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
    public int deleteTestunitFlowDetailDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "testunitFlowDetail.deleteById", id);
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
