/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-19
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
package com.alibaba.testme.domain.query;

/**
 * TODO Comment of TaskCreateParamQuery
 * 
 * @author lz
 */
public class TaskCreateParamQuery {
    private int    pageNo;
    private int    cellsPerPage;
    private String tag;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getCellsPerPage() {
        return cellsPerPage;
    }

    public void setCellsPerPage(int cellsPerPage) {
        this.cellsPerPage = cellsPerPage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
