package com.alibaba.testme.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitParamExtDao;
import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;
import com.alibaba.testme.domain.vo.TestunitParamExtInfoVO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * TestunitParamExt Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamExtDaoImpl extends SqlMapClientDaoSupport implements TestunitParamExtDao {
    private static final Logger logger = LoggerFactory.getLogger(TestunitParamExtDaoImpl.class);

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public Long addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitParamExt.add",
                testunitParamExtDO);
    }

    /**
     * @param testunitParamExtDO
     * @return
     */
    @Override
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunitParamExt.update",
                testunitParamExtDO);
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
    public int deleteTestunitParamExtDO(Long id) {
        Integer result = this.getSqlMapClientTemplate().delete("testunitParamExt.deleteById", id);
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
    public TestunitParamExtDO findById(Long id) {
        return (TestunitParamExtDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitParamExt.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO) {
        return this.getSqlMapClientTemplate().queryForList("testunitParamExt.findList",
                testunitParamExtDO);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void batchSaveTestunitParamExtDO(final List<TestunitParamExtDO> testunitParamExtDOList) {
        try {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

                @Override
                public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                    executor.startBatch(); // 通知开始批量
                    int batch = 1;
                    for (TestunitParamExtDO testunitParamExtDO : testunitParamExtDOList) {
                        executor.delete("testunitParamExt.add", testunitParamExtDO);
                        if (batch % 100 == 0) { // 注意：executeBatch()会将inBatch属性置为false，当下一次调用delete的时候会直接执行
                            executor.executeBatch();
                            executor.startBatch(); // 因此，这里需要再start一次
                        }
                        batch++;
                    }
                    executor.executeBatch();
                    return null;
                }
            });

        } catch (Exception e) {
            logger.error("批处理出现异常", e);
        }
    }

    @Override
    public int deleteByTestunitId(Long testunitId) {
        Integer result = this.getSqlMapClientTemplate().delete(
                "testunitParamExt.deleteByTestunitId", testunitId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamExtInfoVO> getTestunitParamExtInfos(Long testunitParamId) {
        return this.getSqlMapClientTemplate().queryForList(
                "testunitParamExtInfo.getTestunitParamExtInfos", testunitParamId);
    }

}
