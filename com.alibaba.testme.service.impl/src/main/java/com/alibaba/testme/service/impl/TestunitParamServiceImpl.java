package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitParamDao;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.service.TestunitParamService;

/**
 * TestunitParam Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamServiceImpl implements TestunitParamService {

    private TestunitParamDao testunitParamDao;

    public void setTestunitParamDao(TestunitParamDao testunitParamDao) {
        this.testunitParamDao = testunitParamDao;
    }

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public int addTestunitParamDO(TestunitParamDO testunitParamDO) {
        if (testunitParamDO == null) {
            return 0;
        }
        return testunitParamDao.addTestunitParamDO(testunitParamDO);
    }

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO) {
        if (testunitParamDO == null) {
            return 0;
        }
        return testunitParamDao.updateTestunitParamDO(testunitParamDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitParamDO(Integer id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitParamDao.deleteTestunitParamDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitParamDO findById(Integer id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitParamDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO) {
        return testunitParamDao.findList(testunitParamDO);
    }

}
