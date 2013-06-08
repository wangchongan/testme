package com.alibaba.testme.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.SystemEnvDetailDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * SystemEnvDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvDetailDaoImpl extends PageSqlMapClientDaoSupport<SystemConfigVO> implements
        SystemEnvDetailDao {
    private static final Logger logger = LoggerFactory.getLogger(SystemEnvDetailDaoImpl.class);

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("systemEnvDetail.add",
                systemEnvDetailDO);

    }

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public int updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("systemEnvDetail.update",
                systemEnvDetailDO);
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
    public int deleteSystemEnvDetailDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.deleteById", id);
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
    public SystemEnvDetailDO findById(Long id) {
        return (SystemEnvDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "systemEnvDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO) {
        return (List<SystemEnvDetailDO>) this.getSqlMapClientTemplate().queryForList(
                "systemEnvDetail.findList", systemEnvDetailDO);
    }

    @Override
    public Page<SystemConfigVO> queryPage(Integer index, Integer sizePerPage,
                                          SystemConfigQuery systemConfigQuery) {
        return (Page<SystemConfigVO>) this.page(index, sizePerPage, systemConfigQuery,
                "systemEnvDetail.getCount", "systemEnvDetail.pageList");
    }

    @Override
    public int delSystemEnvDetailDOByIds(List<Long> idList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idList", idList);
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.delSystemEnvDetailDOByIds", paramMap);
        if (result == null) {
            return 0;
        }

        return result;
    }

    @Override
    public int deleteByEnvId(Long systemEnvId, String configType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("systemEnvId", systemEnvId);
        paramMap.put("configType", configType);
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.deleteByEnvId", paramMap);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public int updatePropValue(Long systemEnvDetailId, String propValue, String modifier) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", systemEnvDetailId);
        paramMap.put("propValue", propValue);
        paramMap.put("modifier", modifier);
        Integer result = (Integer) this.getSqlMapClientTemplate().update(
                "systemEnvDetail.updatePropValue", paramMap);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SystemConfigVO> findByConditions(SystemConfigQuery systemConfigQuery) {
        return (List<SystemConfigVO>) this.getSqlMapClientTemplate().queryForList(
                "systemEnvDetail.findByConditions", systemConfigQuery);
    }

    /**
     * 批量保存
     * 
     * @param systemEnvDetailDOList
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void batchSaveEnvDetail(final List<SystemEnvDetailDO> systemEnvDetailDOList) {
        try {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

                public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                    executor.startBatch(); // 通知开始批量
                    int batch = 1;
                    for (SystemEnvDetailDO systemEnvDetailDO : systemEnvDetailDOList) {
                        executor.delete("systemEnvDetail.add", systemEnvDetailDO);
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

}
