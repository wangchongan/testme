/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.dao.impl.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.common.basedao.GenericDao;
import com.alibaba.testme.common.page.PageControler;

/**
 * 泛型DAO抽象类
 * 
 * @author chongan.wangca
 */
public abstract class GenericDaoImpl<T, TQ> extends SqlMapClientDaoSupport implements GenericDao<T, TQ> {

    /* 命名空间 */
    public String NS = getNameSpace();

    public abstract String getNameSpace();

    /*
     * @see com.taobaiji.base.dao.GenericDao#add(java.lang.Object)
     */
    @Override
    public Long add(T entity) {
        if (entity == null) {
            return null;
        }
        return (Long) getSqlMapClientTemplate().insert(NS + ".add", entity);
    }

    /*
     * @see com.taobaiji.base.dao.GenericDao#deleteById(java.lang.Long)
     */
    @Override
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }
        return getSqlMapClientTemplate().delete(NS + ".deleteById", id) == 1 ? true : false;
    }

    /*
     * @see com.taobaiji.base.dao.GenericDao#update(java.lang.Object)
     */
    @Override
    public boolean update(T entity) {
        if (entity == null) {
            return false;
        }
        return getSqlMapClientTemplate().update(NS + ".update", entity) == 1 ? true : false;
    }

    /*
     * @see com.taobaiji.base.dao.GenericDao#findById(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T findById(Long id) {
        if (id == null) {
            return null;
        }
        return (T) getSqlMapClientTemplate().queryForObject(NS + ".findById", id);
    }

    /*
     * @see com.taobaiji.base.dao.GenericDao#findList(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findList(TQ query) {
        if (query == null) {
            return null;
        }
        return (List<T>) getSqlMapClientTemplate().queryForList(NS + ".findList", query);
    }

    /*
     * @see com.taobaiji.base.dao.GenericDao#find(java.lang.Object)
     */
    @Override
    public T find(TQ query) {

        if (query == null) {
            return null;
        }

        List<T> list = findList(query);
        if (list == null) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() == 0) {
            return null;
        } else {
            throw new RuntimeException("Find by query has more than one result. result count:" + list.size());
        }
    }

    /*
     * 查询总数
     * @see com.taobaiji.base.dao.GenericDao#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(TQ query) {

        if (query == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        String name = query.getClass().getName().substring(query.getClass().getName().lastIndexOf(".") + 1);
        map.put(name, query);
        // 标记是处理计算总数，如果有该标记，则在计算总是时候不需要加上排序环节
        map.put("isDealCount", "1");
        return (Long) getSqlMapClientTemplate().queryForObject(NS + ".findCount", map);
    }

    /*
     * 分页查询
     * @see com.taobaiji.base.dao.GenericDao#findByPage(java.lang.Object, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageControler<T> findByPage(TQ query, Long page, Long pageSize) {

        if (query == null) {
            return null;
        }

        if (page == null || page < 1L) {
            page = 1L;
        }

        // 如果页数为空，则默认为20条
        if (pageSize == null) {
            pageSize = 20L;
        }

        long count = findCount(query);
        PageControler<T> pageControler = new PageControler<T>().pageFormat(page, count, pageSize);

        if (pageControler.getTotalLine() == 0) {
            return pageControler;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        // 查询对象使用类名字做为MAP的KEY
        String name = query.getClass().getName().substring(query.getClass().getName().lastIndexOf(".") + 1);
        map.put(name, query);
        map.put("pageControler", pageControler);
        map.put("isDealCount", "0");
        List<T> entityList = getSqlMapClientTemplate().queryForList(NS + ".findByPage", map);
        pageControler.setResultList(entityList);
        return pageControler;
    }

    /*
     * 查询总数
     * @see com.taobaiji.base.dao.GenericDao#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(TQ query, String countSqlId) {

        if (query == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        String name = query.getClass().getName().substring(query.getClass().getName().lastIndexOf(".") + 1);
        map.put(name, query);
        // 标记是处理计算总数，如果有该标记，则在计算总是时候不需要加上排序环节
        map.put("isDealCount", "1");
        return (Long) getSqlMapClientTemplate().queryForObject(NS + "." + countSqlId, map);
    }

    /*
     * 分页查询
     * @see com.taobaiji.base.dao.GenericDao#findByPage(java.lang.Object, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageControler<T> findByPage(TQ query, String sqlId, String countSqlId, Long page, Long pageSize) {

        if (query == null) {
            return null;
        }

        if (page == null || page < 1L) {
            page = 1L;
        }

        // 如果页数为空，则默认为20条
        if (pageSize == null) {
            pageSize = 20L;
        }

        long count = findCount(query, countSqlId);
        PageControler<T> pageControler = new PageControler<T>().pageFormat(page, count, pageSize);

        if (pageControler.getTotalLine() == 0) {
            return pageControler;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        // 查询对象使用类名字做为MAP的KEY
        String name = query.getClass().getName().substring(query.getClass().getName().lastIndexOf(".") + 1);
        map.put(name, query);
        map.put("pageControler", pageControler);
        map.put("isDealCount", "0");
        List<T> entityList = getSqlMapClientTemplate().queryForList(NS + "." + sqlId, map);
        pageControler.setResultList(entityList);
        return pageControler;
    }

    /*
     * 查询总数
     * @see com.taobaiji.base.dao.GenericDao#findCount(java.lang.Object)
     */
    @Override
    public Long findCount(Map<String, Object> queryMap, String countSqlId) {

        if (queryMap == null) {
            return null;
        }
        // 标记是处理计算总数，如果有该标记，则在计算总是时候不需要加上排序环节
        queryMap.put("isDealCount", "1");
        return (Long) getSqlMapClientTemplate().queryForObject(NS + "." + countSqlId, queryMap);
    }

    /*
     * 分页查询
     * @see com.taobaiji.base.dao.GenericDao#findByPage(java.lang.Object, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageControler<T> findByPage(Map<String, Object> queryMap, String sqlId, String countSqlId, Long page,
                                       Long pageSize) {

        if (queryMap == null) {
            return null;
        }

        if (page == null || page < 1L) {
            page = 1L;
        }

        // 如果页数为空，则默认为20条
        if (pageSize == null) {
            pageSize = 20L;
        }

        long count = findCount(queryMap, countSqlId);
        PageControler<T> pageControler = new PageControler<T>().pageFormat(page, count, pageSize);

        if (pageControler.getTotalLine() == 0) {
            return pageControler;
        }

        // 查询对象使用类名字做为MAP的KEY
        queryMap.put("pageControler", pageControler);
        queryMap.put("isDealCount", "0");
        List<T> entityList = getSqlMapClientTemplate().queryForList(NS + "." + sqlId, queryMap);
        pageControler.setResultList(entityList);
        return pageControler;
    }

}
