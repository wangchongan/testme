package com.alibaba.testme.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitParamDao;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.domain.vo.TestunitParamInfoVO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * TestunitParam Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitParamDaoImpl extends SqlMapClientDaoSupport implements TestunitParamDao {
    private static final Logger logger = LoggerFactory.getLogger(TestunitParamDaoImpl.class);

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public Long addTestunitParamDO(TestunitParamDO testunitParamDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitParam.add", testunitParamDO);
    }

    /**
     * @param testunitParamDO
     * @return
     */
    @Override
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunitParam.update",
                testunitParamDO);
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
    public int deleteTestunitParamDO(Long id) {
        Integer result = this.getSqlMapClientTemplate().delete("testunitParam.deleteById", id);
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
    public TestunitParamDO findById(Long id) {
        return (TestunitParamDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitParam.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO) {
        return this.getSqlMapClientTemplate().queryForList("testunitParam.findList",
                testunitParamDO);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void batchSaveTestunitParamDO(final List<TestunitParamDO> testunitParamDOList) {
        try {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

                @Override
                public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                    executor.startBatch(); // 通知开始批量
                    int batch = 1;
                    for (TestunitParamDO testunitParamDO : testunitParamDOList) {
                        executor.delete("testunitParam.add", testunitParamDO);
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
        Integer result = this.getSqlMapClientTemplate().delete("testunitParam.deleteByTestunitId",
                testunitId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitParamInfoVO> getTestunitParamInfos(Long testunitId) {
        return this.getSqlMapClientTemplate().queryForList(
                "testunitParamInfo.getTestunitParamInfos", testunitId);
    }

}
