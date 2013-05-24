package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitDO;

/**
 * Testunit Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitService {

    /**
     * @param testunitDO
     * @return
     */
    public int addTestunitDO(TestunitDO testunitDO);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitDO(TestunitDO testunitDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitDO> findList(TestunitDO testunitDO);

}
