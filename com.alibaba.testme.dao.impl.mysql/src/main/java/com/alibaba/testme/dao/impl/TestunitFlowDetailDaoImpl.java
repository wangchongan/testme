package com.alibaba.testme.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.TestunitFlowDetailDao;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.domain.vo.TestunitInfoVO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * TestunitFlowDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class TestunitFlowDetailDaoImpl extends SqlMapClientDaoSupport implements
        TestunitFlowDetailDao {
    private static final Logger logger = LoggerFactory.getLogger(TestunitFlowDetailDaoImpl.class);

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public Long addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("testunitFlowDetail.add",
                testunitFlowDetailDO);
    }

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    @Override
    public int updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO) {
        Integer result = this.getSqlMapClientTemplate().update("testunitFlowDetail.update",
                testunitFlowDetailDO);
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
    public int deleteTestunitFlowDetailDO(Long id) {
        Integer result = this.getSqlMapClientTemplate().delete("testunitFlowDetail.deleteById", id);
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
    public TestunitFlowDetailDO findById(Long id) {
        return (TestunitFlowDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "testunitFlowDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TestunitFlowDetailDO> findList(TestunitFlowDetailDO testunitFlowDetailDO) {
        return this.getSqlMapClientTemplate().queryForList("testunitFlowDetail.findList",
                testunitFlowDetailDO);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void batchSaveTestunitFlowDetail(final List<TestunitFlowDetailDO> testunitFlowDetailDOList) {
        try {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

                @Override
                public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                    executor.startBatch(); // 通知开始批量
                    int batch = 1;
                    for (TestunitFlowDetailDO testunitFlowDetailDO : testunitFlowDetailDOList) {
                        executor.insert("testunitFlowDetail.add", testunitFlowDetailDO);
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
    public int deleteByTestunitFlowId(Long testunitFlowId) {
        Integer result = this.getSqlMapClientTemplate().delete(
                "testunitFlowDetail.deleteByTestunitFlowId", testunitFlowId);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public TestunitInfoVO getFirstTestunitInfo(Long testunitFlowId) {
        return (TestunitInfoVO) this.getSqlMapClientTemplate().queryForObject(
                "testunitInfoVO.findByTestunitFlowId", testunitFlowId);
    }

}
