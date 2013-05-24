package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
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
    public int addTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        if (testunitFlowDO == null) {
            return 0;
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
    public int deleteTestunitFlowDO(Integer id) {
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
    public TestunitFlowDO findById(Integer id) {
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

}
