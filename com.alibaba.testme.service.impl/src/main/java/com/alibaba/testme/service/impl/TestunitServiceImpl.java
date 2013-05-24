package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitDao;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.service.TestunitService;

/**
 * Testunit Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitServiceImpl implements TestunitService {

    private TestunitDao testunitDao;

    public void setTestunitDao(TestunitDao testunitDao) {
        this.testunitDao = testunitDao;
    }

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public int addTestunitDO(TestunitDO testunitDO) {
        if (testunitDO == null) {
            return 0;
        }
        return testunitDao.addTestunitDO(testunitDO);
    }

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public int updateTestunitDO(TestunitDO testunitDO) {
        if (testunitDO == null) {
            return 0;
        }
        return testunitDao.updateTestunitDO(testunitDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitDO(Integer id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitDao.deleteTestunitDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitDO findById(Integer id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitDO> findList(TestunitDO testunitDO) {
        return testunitDao.findList(testunitDO);
    }

}
