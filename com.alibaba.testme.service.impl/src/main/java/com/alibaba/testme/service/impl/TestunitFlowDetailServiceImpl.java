package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitFlowDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.service.TestunitFlowDetailService;

/**
 * TestunitFlowDetail Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDetailServiceImpl implements TestunitFlowDetailService {

    private TestunitFlowDetailDao testunitFlowDetailDao;

    public void setTestunitFlowDetailDao(TestunitFlowDetailDao testunitFlowDetailDao) {
        this.testunitFlowDetailDao = testunitFlowDetailDao;
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public Integer addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        if (testunitFlowDetailDO == null) {
            return 0;
        }
        return testunitFlowDetailDao.addTestunitFlowDetailDO(testunitFlowDetailDO);
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public Integer updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        if (testunitFlowDetailDO == null) {
            return 0;
        }
        return testunitFlowDetailDao.updateTestunitFlowDetailDO(testunitFlowDetailDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteTestunitFlowDetailDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitFlowDetailDao.deleteTestunitFlowDetailDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowDetailDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitFlowDetailDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitFlowDetailDO> findList(TestunitFlowDetailDO testunitFlowDetailDO) {
        return testunitFlowDetailDao.findList(testunitFlowDetailDO);
    }

}
