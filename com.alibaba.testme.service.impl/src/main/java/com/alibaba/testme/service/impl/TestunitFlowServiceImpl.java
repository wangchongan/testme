package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TaskCreateParamQuery;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestFlowParamVO;
import com.alibaba.testme.domain.vo.TestunitFlowVO;
import com.alibaba.testme.service.TestunitFlowService;

/**
 * TestunitFlow Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowServiceImpl implements TestunitFlowService {

    private TestunitFlowDao testunitFlowDao;

    public void setTestunitFlowDao(TestunitFlowDao testunitFlowDao) {
        this.testunitFlowDao = testunitFlowDao;
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public Long addTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        if (testunitFlowDO == null) {
            return null;
        }
        return testunitFlowDao.addTestunitFlowDO(testunitFlowDO);
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public int updateTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        if (testunitFlowDO == null) {
            return 0;
        }
        return testunitFlowDao.updateTestunitFlowDO(testunitFlowDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitFlowDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitFlowDao.deleteTestunitFlowDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitFlowDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO) {
        return testunitFlowDao.findList(testunitFlowDO);
    }

    @Override
    public Page<TestunitFlowVO> queryPage(Integer index, Integer sizePerPage,
                                          TestunitFlowQuery testunitFlowQuery) {
        if (index == 0 || sizePerPage == 0 || testunitFlowQuery == null) {
            return null;
        }
        return testunitFlowDao.queryPage(index, sizePerPage, testunitFlowQuery);
    }

    @Override
    public TestunitFlowVO queryById(Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            return null;
        }
        return testunitFlowDao.queryById(testunitFlowId);
    }

    @Override
    public List<TestFlowParamVO> getTaskCreateParam(TaskCreateParamQuery taskCreateParamQuery) {
        //        testunitFlowDao.queryById(testunitFlowId);
        return null;
    }

}
