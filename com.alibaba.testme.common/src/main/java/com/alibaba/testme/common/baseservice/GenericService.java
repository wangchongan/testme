/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.baseservice;

import java.util.List;
import java.util.Map;

import com.alibaba.testme.common.page.PageControler;

/**
 * 泛型Service
 * 
 * @author chongan.wangca
 */
public interface GenericService<T, TQ> {

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
