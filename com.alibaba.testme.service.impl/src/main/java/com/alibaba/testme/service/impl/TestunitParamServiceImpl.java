package com.alibaba.testme.service.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.alibaba.testme.dao.TestunitParamDao;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.domain.vo.TestunitParamExtInfoVO;
import com.alibaba.testme.domain.vo.TestunitParamInfoVO;
import com.alibaba.testme.service.TestunitParamExtService;
import com.alibaba.testme.service.TestunitParamService;

/**
 * TestunitParam Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamServiceImpl implements TestunitParamService {

    private TestunitParamDao        testunitParamDao;

    private TestunitParamExtService testunitParamExtService;

    public void setTestunitParamDao(TestunitParamDao testunitParamDao) {
        this.testunitParamDao = testunitParamDao;
    }

    public void setTestunitParamExtService(TestunitParamExtService testunitParamExtService) {
        this.testunitParamExtService = testunitParamExtService;
    }

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public Long addTestunitParamDO(TestunitParamDO testunitParamDO) {
        if (testunitParamDO == null) {
            return null;
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
    public int deleteTestunitParamDO(Long id) {
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
    public TestunitParamDO findById(Long id) {
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

    @Override
    public void batchSaveTestunitParamDO(List<TestunitParamDO> testunitParamDOList) {
        if (testunitParamDOList == null || testunitParamDOList.size() == 0) {
            return;
        }
        testunitParamDao.batchSaveTestunitParamDO(testunitParamDOList);
    }

    @Override
    public int deleteByTestunitId(Long testunitId) {
        if (testunitId == null || testunitId <= 0L) {
            return 0;
        }
        return testunitParamDao.deleteByTestunitId(testunitId);
    }

    @Override
    public List<TestunitParamInfoVO> getTestunitParamInfos(Long testunitId) {
        List<TestunitParamInfoVO> testunitParamInfos = this.testunitParamDao
                .getTestunitParamInfos(testunitId);
        if (!CollectionUtils.isEmpty(testunitParamInfos)) {
            for (TestunitParamInfoVO testunitParamInfoVO : testunitParamInfos) {
                List<TestunitParamExtInfoVO> testunitParamExtInfos = this.testunitParamExtService
                        .getTestunitParamExtInfos(testunitParamInfoVO.getTestunitParamId());
                testunitParamInfoVO.setTestunitParamExtInfoVOs(testunitParamExtInfos);
            }
        }
        return testunitParamInfos;
    }

}
