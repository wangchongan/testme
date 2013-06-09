package com.alibaba.testme.dao.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestunitFlowVO;

/**
 * TestunitFlow Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDaoImpl extends PageSqlMapClientDaoSupport<TestunitFlowVO> implements
        TestunitFlowDao {

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

    @Override
    public Page<TestunitFlowVO> queryPage(Integer index, Integer sizePerPage,
                                          TestunitFlowQuery testunitFlowQuery) {
        return page(index, sizePerPage, testunitFlowQuery, "testunitFlow.getCount",
                "testunitFlow.pageList");
    }
}
