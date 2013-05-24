package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitParamExtDao;
import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;
import com.alibaba.testme.service.TestunitParamExtService;

/**
 * TestunitParamExt Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamExtServiceImpl implements TestunitParamExtService {

    private TestunitParamExtDao testunitParamExtDao;

    public void setTestunitParamExtDao(TestunitParamExtDao testunitParamExtDao) {
        this.testunitParamExtDao = testunitParamExtDao;
    }

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public int addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        if (testunitParamExtDO == null) {
            return 0;
        }
        return testunitParamExtDao.addTestunitParamExtDO(testunitParamExtDO);
    }

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        if (testunitParamExtDO == null) {
            return 0;
        }
        return testunitParamExtDao.updateTestunitParamExtDO(testunitParamExtDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitParamExtDO(Integer id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitParamExtDao.deleteTestunitParamExtDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitParamExtDO findById(Integer id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitParamExtDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO) {
        return testunitParamExtDao.findList(testunitParamExtDO);
    }

}
