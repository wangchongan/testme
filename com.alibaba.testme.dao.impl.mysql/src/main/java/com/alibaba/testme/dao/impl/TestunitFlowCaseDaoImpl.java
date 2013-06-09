package com.alibaba.testme.dao.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.TestunitFlowCaseDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.query.TestunitFlowCaseQuery;
import com.alibaba.testme.domain.vo.TestunitFlowCaseVO;

/**
 * TestunitFlowCase Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseDaoImpl extends PageSqlMapClientDaoSupport<TestunitFlowCaseVO>
        implements TestunitFlowCaseDao {

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
    public int updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunitFlowCase.update",
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
    public int deleteTestunitFlowCaseDO(Long id) {
        Integer result = this.getSqlMapClientTemplate().delete("testunitFlowCase.deleteById", id);
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
        return this.getSqlMapClientTemplate().queryForList("testunitFlowCase.findList",
                testunitFlowCaseDO);
    }

    @Override
    public Page<TestunitFlowCaseVO> queryPage(TestunitFlowCaseQuery testunitFlowCaseQuery) {
        return this.page(testunitFlowCaseQuery.getPageIndex(),
                testunitFlowCaseQuery.getSizePerPage(), testunitFlowCaseQuery,
                "testunitFlowCase.getCount", "testunitFlowCase.pageList");
    }

}
