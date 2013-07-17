package com.alibaba.testme.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.TestunitDao;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitVO;

/**
 * Testunit Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitDaoImpl extends PageSqlMapClientDaoSupport<TestunitVO> implements TestunitDao {

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public Long addTestunitDO(TestunitDO testunitDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunit.add", testunitDO);
    }

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public int updateTestunitDO(TestunitDO testunitDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunit.update", testunitDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteTestunitDO(Long id) {
        Integer result = this.getSqlMapClientTemplate().delete("testunit.deleteById", id);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TestunitDO findById(Long id) {
        return (TestunitDO) this.getSqlMapClientTemplate().queryForObject("testunit.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitDO> findList(TestunitDO testunitDO) {
        return this.getSqlMapClientTemplate().queryForList("testunit.findList", testunitDO);
    }

    @Override
    public Page<TestunitVO> queryPage(Integer index, Integer sizePerPage,
                                      TestunitQuery testunitQuery) {
        return this.page(index, sizePerPage, testunitQuery, "testunit.getCount",
                "testunit.pageList");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitDO> findByIdList(List<Long> idList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idList", idList);
        return this.getSqlMapClientTemplate().queryForList("testunit.findByIdList", paramMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitDO> findByTestunitFlowId(Long testunitFlowId) {
        return this.getSqlMapClientTemplate().queryForList("testunit.findByTestunitFlowId",
                testunitFlowId);
    }

    @Override
    public TestunitVO findTestunitVOById(Long testunitId) {
        return (TestunitVO) this.getSqlMapClientTemplate().queryForObject(
                "testunit.findTestunitVOById", testunitId);
    }

    @Override
    public int setBundleInformationNull(Long testunitId, String modifier) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("testunitId", testunitId);
        paramMap.put("modifier", modifier);
        Integer result = this.getSqlMapClientTemplate().update("testunit.setBundleInformationNull",
                paramMap);
        if (result == null) {
            return 0;
        }

        return result;
    }

    @Override
    public int updateBundleInformation(Long testunitId, String symbolicName, String bundleVersion,
                                       String bundleFileName, String classQualifiedName,
                                       String modifier) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("testunitId", testunitId);
        paramMap.put("symbolicName", symbolicName);
        paramMap.put("bundleVersion", bundleVersion);
        paramMap.put("bundleFileName", bundleFileName);
        paramMap.put("classQualifiedName", classQualifiedName);
        paramMap.put("modifier", modifier);

        Integer result = this.getSqlMapClientTemplate().update("testunit.updateBundleInformation",
                paramMap);
        if (result == null) {
            return 0;
        }

        return result;
    }

}
