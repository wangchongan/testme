package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitDao;
import com.alibaba.testme.domain.dataobject.TestunitDO;

/**
 * Testunit Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitDaoImpl extends SqlMapClientDaoSupport implements TestunitDao {

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public int addTestunitDO(TestunitDO testunitDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate()
                .insert("testunit.add", testunitDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param testunitDO
     * @return
     */
    @Override
    public int updateTestunitDO(TestunitDO testunitDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("testunit.update",
                testunitDO);
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
    public int deleteTestunitDO(Integer id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete("testunit.deleteById", id);
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
    public TestunitDO findById(Integer id) {
        return (TestunitDO) this.getSqlMapClientTemplate().queryForObject("testunit.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitDO> findList(TestunitDO testunitDO) {
        return (List<TestunitDO>) this.getSqlMapClientTemplate().queryForList("testunit.findList",
                testunitDO);
    }

}
