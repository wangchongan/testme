package com.alibaba.testme.dao.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TaskCreateParamQuery;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestFlowInfoVO;
import com.alibaba.testme.domain.vo.TestunitFlowVO;

/**
 * TestunitFlow Dao Implement
 * 
 * @author xiaopenzi
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestunitFlowDaoImpl extends PageSqlMapClientDaoSupport implements TestunitFlowDao {

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
        Integer result = this.getSqlMapClientTemplate().update("testunitFlow.update",
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
        Integer result = this.getSqlMapClientTemplate().delete("testunitFlow.deleteById", id);
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
    @Override
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO) {
        return this.getSqlMapClientTemplate().queryForList("testunitFlow.findList", testunitFlowDO);
    }

    @Override
    public Page<TestunitFlowVO> queryPage(Integer index, Integer sizePerPage,
                                          TestunitFlowQuery testunitFlowQuery) {
        return page(index, sizePerPage, testunitFlowQuery, "testunitFlow.getCount",
                "testunitFlow.pageList");
    }

    @Override
    public TestunitFlowVO queryById(Long testunitFlowId) {
        return (TestunitFlowVO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlow.queryById", testunitFlowId);
    }

    @Override
    public Page<TestFlowInfoVO> getTestFlowInfos(TaskCreateParamQuery taskCreateParamQuery) {
        return page(taskCreateParamQuery.getPageNo(), taskCreateParamQuery.getCellsPerPage(),
                taskCreateParamQuery, "testFlowInfoVO.getCount", "testFlowInfoVO.pageList");
    }
}
