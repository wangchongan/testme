package com.alibaba.testme.common.basedao;

import java.util.List;
import java.util.Map;

import com.alibaba.testme.common.page.PageControler;


/**
 * 泛型DAO
 * 
 * @author chongan.wangca
 */
public interface GenericDao<T, TQ> {

    /**
     * 新增记录
     * 
     * @param entity
     * @return
     */
    public Long add(T entity);

    /**
     * 根据ID删除记录
     * 
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

    /**
     * 更新记录
     * 
     * @param entity
     * @return
     */
    public boolean update(T entity);

    /**
     * 根据ID查询记录
     * 
     * @param id
     * @return
     */
    public T findById(Long id);

    /**
     * 根据查询条件查询记录
     * 
     * @param query
     * @return
     */
    public List<T> findList(TQ query);

    /**
     * 根据查询条件查询一条记录
     * 
     * @param query
     * @return
     */
    public T find(TQ query);

    /**
     * 根据查询条件查询总条数
     * 
     * @param query
     * @return
     */
    public Long findCount(TQ query);

    /**
     * 分页查询
     * 
     * @param query
     * @param page
     * @return
     */
    public PageControler<T> findByPage(TQ query, Long page, Long pageSize);

    /**
     * 根据查询条件查询总条数
     * 
     * @param query
     * @return
     */
    public Long findCount(TQ query, String countSqlId);

    /**
     * 分页查询
     * 
     * @param query
     * @param page
     * @return
     */
    public PageControler<T> findByPage(TQ query, String sqlId, String countSqlId, Long page, Long pageSize);

    /**
     * 根据查询条件查询总条数
     * 
     * @param query
     * @return
     */
    public Long findCount(Map<String, Object> queryMap, String countSqlId);

    /**
     * 分页查询
     * 
     * @param query
     * @param page
     * @return
     */
    public PageControler<T> findByPage(Map<String, Object> queryMap, String sqlId, String countSqlId, Long page,
                                       Long pageSize);

}
