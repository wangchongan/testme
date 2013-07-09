package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.TestunitFlowDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.domain.vo.TestunitInfoVO;
import com.alibaba.testme.domain.vo.TestunitParamInfoVO;
import com.alibaba.testme.service.TestunitFlowDetailService;
import com.alibaba.testme.service.TestunitParamService;

/**
 * TestunitFlowDetail Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDetailServiceImpl implements TestunitFlowDetailService {

    private TestunitFlowDetailDao testunitFlowDetailDao;
    private TestunitParamService  testunitParamService;

    public void setTestunitFlowDetailDao(TestunitFlowDetailDao testunitFlowDetailDao) {
        this.testunitFlowDetailDao = testunitFlowDetailDao;
    }

    public void setTestunitParamService(TestunitParamService testunitParamService) {
        this.testunitParamService = testunitParamService;
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

    @Override
    public Long findFirstTestunitFlowDetailId(Long testunitFlowId) {
        TestunitFlowDetailDO testunitFlowDetailQuery = new TestunitFlowDetailDO();
        testunitFlowDetailQuery.setTestunitFlowId(testunitFlowId);
        List<TestunitFlowDetailDO> testunitFlowDetailDOList = findList(testunitFlowDetailQuery);

        for (TestunitFlowDetailDO testunitFlowDetailDO : testunitFlowDetailDOList) {
            if (testunitFlowDetailDO.getPreTestunitId() == null
                    || testunitFlowDetailDO.getPreTestunitId() == 0L) {
                return testunitFlowDetailDO.getId();
            }
        }
        return null;
    }

    @Override
    public void batchSaveTestunitFlowDetail(List<TestunitFlowDetailDO> testunitFlowDetailDOList) {
        if (testunitFlowDetailDOList == null || testunitFlowDetailDOList.size() == 0) {
            return;
        }
        testunitFlowDetailDao.batchSaveTestunitFlowDetail(testunitFlowDetailDOList);
    }

    @Override
    public int deleteByTestunitFlowId(Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            return 0;
        }
        return testunitFlowDetailDao.deleteByTestunitFlowId(testunitFlowId);
    }

    @Override
    public TestunitInfoVO getFirstTestunitInfo(Long testunitFlowId) {
        TestunitInfoVO testunitInfoVO = testunitFlowDetailDao.getFirstTestunitInfo(testunitFlowId);
        if (testunitInfoVO != null) {
            List<TestunitParamInfoVO> testunitParamInfoVOs = testunitParamService
                    .getTestunitParamInfos(testunitInfoVO.getTestunitId());
            testunitInfoVO.setTestunitParamInfoVOs(testunitParamInfoVOs);
        }
        return testunitInfoVO;
    }

}
