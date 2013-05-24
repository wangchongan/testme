package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitDO;

/**
 * Testunit Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitDao {

    /**
     * @param testunitDO
     * @return
     */
    public int addTestunitDO(TestunitDO testunitDO);

    /**
     * @param testunitDO
     * @return
     */
    public int updateTestunitDO(TestunitDO testunitDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitDO findById(Integer id);

    /**
     * @param testunitDO
     * @return
     */
    public List<TestunitDO> findList(TestunitDO testunitDO);

}
