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
    public Long addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        if (testunitFlowDetailDO == null) {
            return null;
        }
        return testunitFlowDetailDao.addTestunitFlowDetailDO(testunitFlowDetailDO);
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public int updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
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
    public int deleteTestunitFlowDetailDO(Long id) {
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

    /*
     * (non-Javadoc)
     * @see com.alibaba.testme.service.TestunitFlowDetailService#
     * findFirstTestunitFlowDetailId(java.lang.Long)
     */
    @Override
    public Long findFirstTestunitFlowDetailId(Long testunitFlowId) {
        TestunitFlowDetailDO testunitFlowDetailQuery = new TestunitFlowDetailDO();
        testunitFlowDetailQuery.setTestunitFlowId(testunitFlowId);
        List<TestunitFlowDetailDO> testunitFlowDetailDOList = findList(testunitFlowDetailQuery);
        for (TestunitFlowDetailDO testunitFlowDetailDO : testunitFlowDetailDOList) {
            if (testunitFlowDetailDO.getPreTestunitFlowDetailId() != null) {
                return testunitFlowDetailDO.getPreTestunitFlowDetailId();
            }
        }
        return null;
    }

}
