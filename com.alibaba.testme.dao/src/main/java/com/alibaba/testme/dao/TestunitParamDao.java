package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TestunitParam Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamDao {

    /**
     * @param testunitParamDO
     * @return
     */
    public int addTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param testunitParamDO
     * @return
     */
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitParamDO findById(Integer id);

    /**
     * @param testunitParamDO
     * @return
     */
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO);

}
