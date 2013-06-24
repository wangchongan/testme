package com.alibaba.testme.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.dao.TestunitFlowDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TaskCreateParamQuery;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestFlowInfoVO;
import com.alibaba.testme.domain.vo.TestunitFlowVO;
import com.alibaba.testme.domain.vo.TestunitInfoVO;
import com.alibaba.testme.service.TestunitFlowDetailService;
import com.alibaba.testme.service.TestunitFlowService;

/**
 * TestunitFlow Service Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowServiceImpl implements TestunitFlowService {

    private TestunitFlowDao           testunitFlowDao;
    private TestunitFlowDetailService testunitFlowDetailService;

    public void setTestunitFlowDao(TestunitFlowDao testunitFlowDao) {
        this.testunitFlowDao = testunitFlowDao;
    }

    public void setTestunitFlowDetailService(TestunitFlowDetailService testunitFlowDetailService) {
        this.testunitFlowDetailService = testunitFlowDetailService;
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public Long addTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        if (testunitFlowDO == null) {
            return null;
        }
        return testunitFlowDao.addTestunitFlowDO(testunitFlowDO);
    }

    /**
     * @param testunitFlowDO
     * @return
     */
    @Override
    public int updateTestunitFlowDO(TestunitFlowDO testunitFlowDO) {
        if (testunitFlowDO == null) {
            return 0;
        }
        return testunitFlowDao.updateTestunitFlowDO(testunitFlowDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitFlowDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return testunitFlowDao.deleteTestunitFlowDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitFlowDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return testunitFlowDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO) {
        return testunitFlowDao.findList(testunitFlowDO);
    }

    @Override
    public Page<TestunitFlowVO> queryPage(Integer index, Integer sizePerPage,
                                          TestunitFlowQuery testunitFlowQuery) {
        if (index == 0 || sizePerPage == 0 || testunitFlowQuery == null) {
            return null;
        }
        return testunitFlowDao.queryPage(index, sizePerPage, testunitFlowQuery);
    }

    @Override
    public TestunitFlowVO queryById(Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            return null;
        }
        return testunitFlowDao.queryById(testunitFlowId);
    }

    @Override
    public List<TestFlowInfoVO> getTestFlowInfos(TaskCreateParamQuery taskCreateParamQuery) {
        Page<TestFlowInfoVO> page = testunitFlowDao.getTestFlowInfos(taskCreateParamQuery);
        List<TestFlowInfoVO> testFlowInfoVOList = page.getDatas();
        if (CollectionUtils.isEmpty(testFlowInfoVOList)) {
            return Collections.emptyList();
        }

        for (TestFlowInfoVO testFlowInfoVO : testFlowInfoVOList) {
            TestunitInfoVO testunitInfoVO = testunitFlowDetailService
                    .getFirstTestunitInfo(testFlowInfoVO.getTestunitFlowId());
            testFlowInfoVO.setTestunitInfoVO(testunitInfoVO);
        }

        return testFlowInfoVOList;
    }

}
