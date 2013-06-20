package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.dao.TestunitDao;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitVO;
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
    public Long addTestunitDO(TestunitDO testunitDO) {
        if (testunitDO == null) {
            return null;
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
    public int deleteTestunitDO(Long id) {
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
    public TestunitDO findById(Long id) {
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

    @Override
    public Page<TestunitVO> queryPage(Integer index, Integer sizePerPage,
                                      TestunitQuery testunitQuery) {
        if (index == 0 || sizePerPage == 0 || testunitQuery == null) {
            return null;
        }
        return testunitDao.queryPage(index, sizePerPage, testunitQuery);
    }

    @Override
    public List<TestunitDO> findByIdList(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return null;
        }
        return testunitDao.findByIdList(idList);
    }

    @Override
    public List<TestunitDO> findByTestunitFlowId(Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            return null;
        }
        return testunitDao.findByTestunitFlowId(testunitFlowId);
    }

    @Override
    public TestunitVO findTestunitVOById(Long testunitId) {
        if (testunitId == null || testunitId <= 0L) {
            return null;
        }

        return testunitDao.findTestunitVOById(testunitId);
    }

}
