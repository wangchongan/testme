package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;

/**
 * TestunitParamExt Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamExtDao {

    /**
     * @param testunitParamExtDO
     * @return
     */
    public Long addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param testunitParamExtDOList
     * @return
     */
    public void batchSaveTestunitParamExtDO(List<TestunitParamExtDO> testunitParamExtDOList);

    /**
     * @param testunitParamExtDO
     * @return
     */
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamExtDO(Long id);

    /**
     * 根据测试单元ID删除参数配置项信息
     * 
     * @param testunitId
     * @return
     */
    public int deleteByTestunitId(Long testunitId);

    /**
     * @param id
     * @return
     */
    public TestunitParamExtDO findById(Long id);

    /**
     * @param testunitParamExtDO
     * @return
     */
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO);

}
