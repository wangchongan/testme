package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestunitFlowVO;

/**
 * TestunitFlow Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowDao {

    /**
     * @param testunitFlowDO
     * @return
     */
    public Long addTestunitFlowDO(TestunitFlowDO testunitFlowDO);

    /**
     * @param testunitFlowDO
     * @return
     */
    public int updateTestunitFlowDO(TestunitFlowDO testunitFlowDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowDO findById(Long id);

    /**
     * @param testunitFlowDO
     * @return
     */
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO);

    /**
     * 分页查询测试流程信息
     * 
     * @param index
     * @param sizePerPage
     * @param testunitFlowQuery
     * @return
     */
    public Page<TestunitFlowVO> queryPage(Integer index, Integer sizePerPage,
                                          TestunitFlowQuery testunitFlowQuery);

    /**
     * 根据ID查询测试流程
     * 
     * @param testunitFlowId
     * @return
     */
    public TestunitFlowVO queryById(Long testunitFlowId);

}
