package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitFlowCaseDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.service.TestunitFlowCaseService;

/**
 * TestunitFlowCase Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseServiceImpl implements TestunitFlowCaseService {

    private TestunitFlowCaseDao testunitFlowCaseDao;

    public void setTestunitFlowCaseDao(TestunitFlowCaseDao testunitFlowCaseDao) {
        this.testunitFlowCaseDao = testunitFlowCaseDao;
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public Integer addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        if (testunitFlowCaseDO == null) {
            return 0;
        }
        return testunitFlowCaseDao.addTestunitFlowCaseDO(testunitFlowCaseDO);
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        if (testunitFlowCaseDO == null) {
            return 0;
        }
        return testunitFlowCaseDao.updateTestunitFlowCaseDO(testunitFlowCaseDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowCaseDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitFlowCaseDao.deleteTestunitFlowCaseDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowCaseDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitFlowCaseDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitFlowCaseDO> findList(TestunitFlowCaseDO testunitFlowCaseDO) {
        return testunitFlowCaseDao.findList(testunitFlowCaseDO);
    }

}
