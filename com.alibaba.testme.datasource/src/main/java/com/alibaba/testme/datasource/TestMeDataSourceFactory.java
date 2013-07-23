/**
 * Project: com.alibaba.testme.datasource
 * 
 * File Created at 2013-7-22
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * testMe数据源工厂
 * 
 * @author lz
 */
public class TestMeDataSourceFactory {
    private static final TestMeDataSourceFactory testMeDataSourceFactory = new TestMeDataSourceFactory();

    private TestMeDataSourceFactory() {
    }

    public static TestMeDataSourceFactory getInstance() {
        return testMeDataSourceFactory;
    }

    public DataSource createDataSource(String driverClassName, String url, String username,
                                    String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    public void closeDataSource(DataSource ds) {
        BasicDataSource bds = (BasicDataSource) ds;
        try {
            bds.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
