package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitFlowCaseDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;
import com.alibaba.testme.service.TestunitFlowCaseDetailService;

/**
 * TestunitFlowCaseDetail Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseDetailServiceImpl implements TestunitFlowCaseDetailService {

    private TestunitFlowCaseDetailDao testunitFlowCaseDetailDao;

    public void setTestunitFlowCaseDetailDao(TestunitFlowCaseDetailDao testunitFlowCaseDetailDao) {
        this.testunitFlowCaseDetailDao = testunitFlowCaseDetailDao;
    }

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public Integer addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        if (testunitFlowCaseDetailDO == null) {
            return 0;
        }
        return testunitFlowCaseDetailDao.addTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);
    }

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        if (testunitFlowCaseDetailDO == null) {
            return 0;
        }
        return testunitFlowCaseDetailDao.updateTestunitFlowCaseDetailDO(testunitFlowCaseDetailDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowCaseDetailDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitFlowCaseDetailDao.deleteTestunitFlowCaseDetailDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowCaseDetailDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitFlowCaseDetailDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitFlowCaseDetailDO> findList(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO) {
        return testunitFlowCaseDetailDao.findList(testunitFlowCaseDetailDO);
    }

}
