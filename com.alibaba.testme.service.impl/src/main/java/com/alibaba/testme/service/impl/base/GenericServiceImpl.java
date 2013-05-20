/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.service.impl.base;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.testme.common.basedao.GenericDao;
import com.alibaba.testme.common.baseservice.GenericService;
import com.alibaba.testme.common.page.PageControler;

/**
 * @author chongan.wangca
 */
public abstract class GenericServiceImpl<T, TQ> implements GenericService<T, TQ> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericServiceImpl.class);

    private GenericDao<T, TQ>   genericDao;

    /*
     * @see com.taobaiji.base.service.GenericService#add(java.lang.Object)
     */
    @Override
    public Long add(T entity) {
        return genericDao.add(entity);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#deleteById(java.lang.Long)
     */
    @Override
    public boolean deleteById(Long id) {
        return genericDao.deleteById(id);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#update(java.lang.Object)
     */
    @Override
    public boolean update(T entity) {
        return genericDao.update(entity);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#findById(java.lang.Long)
     */
    @Override
    public T findById(Long id) {
        return genericDao.findById(id);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#findList(java.lang.Object)
     */
    @Override
    public List<T> findList(TQ query) {
        return genericDao.findList(query);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#find(java.lang.Object)
     */
    @Override
    public T find(TQ query) {
        return genericDao.find(query);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(TQ query) {
        return genericDao.findCount(query);
    }

    /*
     * @see
     * com.taobaiji.base.service.GenericService#findByPage(java.lang.Object,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public PageControler<T> findByPage(TQ query, Long page, Long pageSize) {
        return genericDao.findByPage(query, page, pageSize);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(TQ query, String countSqlId) {
        return genericDao.findCount(query, countSqlId);
    }

    /*
     * @see
     * com.taobaiji.base.service.GenericService#findByPage(java.lang.Object,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public PageControler<T> findByPage(TQ query, String sqlId, String countSqlId, Long page,
                                       Long pageSize) {
        return genericDao.findByPage(query, sqlId, countSqlId, page, pageSize);
    }

    /*
     * @see com.taobaiji.base.service.GenericService#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(Map<String, Object> queryMap, String countSqlId) {
        return genericDao.findCount(queryMap, countSqlId);
    }

    /*
     * @see
     * com.taobaiji.base.service.GenericService#findByPage(java.lang.Object,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public PageControler<T> findByPage(Map<String, Object> queryMap, String sqlId,
                                       String countSqlId, Long page, Long pageSize) {
        return genericDao.findByPage(queryMap, sqlId, countSqlId, page, pageSize);
    }

    public GenericDao<T, TQ> getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao<T, TQ> genericDao) {
        this.genericDao = genericDao;
    }

}
