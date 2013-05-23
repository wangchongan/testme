package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TestunitParam Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamService {

    /**
     * @param testunitParamDO
     * @return
     */
    public Long addTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param DO
     * @return
     */
    public Integer updateTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitParamDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitParamDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO);

}
