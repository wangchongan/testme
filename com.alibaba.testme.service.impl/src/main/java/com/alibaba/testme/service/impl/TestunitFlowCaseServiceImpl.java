package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.dao.TestunitFlowCaseDao;
import com.alibaba.testme.dao.TestunitFlowCaseDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.query.TestunitFlowCaseQuery;
import com.alibaba.testme.domain.vo.TestCaseDetailVO;
import com.alibaba.testme.domain.vo.TestCaseVO;
import com.alibaba.testme.domain.vo.TestunitFlowCaseVO;
import com.alibaba.testme.service.TestunitFlowCaseService;

/**
 * TestunitFlowCase Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowCaseServiceImpl implements TestunitFlowCaseService {

    private TestunitFlowCaseDao       testunitFlowCaseDao;
    private TestunitFlowCaseDetailDao testunitFlowCaseDetailDao;

    public void setTestunitFlowCaseDao(TestunitFlowCaseDao testunitFlowCaseDao) {
        this.testunitFlowCaseDao = testunitFlowCaseDao;
    }

    public void setTestunitFlowCaseDetailDao(TestunitFlowCaseDetailDao testunitFlowCaseDetailDao) {
        this.testunitFlowCaseDetailDao = testunitFlowCaseDetailDao;
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public Long addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        if (testunitFlowCaseDO == null) {
            return null;
        }
        return testunitFlowCaseDao.addTestunitFlowCaseDO(testunitFlowCaseDO);
    }

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    @Override
    public int updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO) {
        if (testunitFlowCaseDO == null) {
            return 0;
        }
        return testunitFlowCaseDao.updateTestunitFlowCaseDO(testunitFlowCaseDO);

    }

    /**
     * @param id
     */
    @Override
    public void deleteTestunitFlowCaseDO(Long id) {
        if (id == null || id == 0L) {
            throw new IllegalArgumentException("input param is null");
        }
        int rowCount = 0;
        rowCount = testunitFlowCaseDao.deleteTestunitFlowCaseDO(id);

        if (rowCount != 1) {
            throw new RuntimeException("delete testunitFlowCase fail, TestunitFlowCaseId: " + id);
        }

        rowCount = this.testunitFlowCaseDetailDao.deleteTestunitFlowCaseDetailDO(id);
        if (rowCount < 1) {
            throw new RuntimeException("delete testunitFlowCaseDetail fail, TestunitFlowCaseId: "
                    + id);
        }
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

    @Override
    public Page<TestunitFlowCaseVO> queryPage(TestunitFlowCaseQuery testunitFlowCaseQuery) {
        return testunitFlowCaseDao.queryPage(testunitFlowCaseQuery);
    }

    @Override
    public TestCaseVO queryTestunitFlowCaseDetail(Long id) {
        TestCaseVO testCaseVO = testunitFlowCaseDao.queryTestunitFlowCase(id);
        if (testCaseVO != null) {
            List<TestCaseDetailVO> testCaseDetailList = this.testunitFlowCaseDetailDao
                    .queryTestCaseDetailList(id);
            testCaseVO.setTestCaseDetailList(testCaseDetailList);
        }
        return testCaseVO;
    }

}
