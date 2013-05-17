/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.page;

/**
 * 类Pager.java的实现描述：TODO 类实现描述
 * 
 * @author chongan.wangca 2012-4-9 下午11:27:39
 */
public class Pager {

    private Long currPage;
    private Long pageSize;
    private Long pageCount;
    private Long prePage;
    private Long nextPage;
    private Long currLine;
    private Long totalLine;

    public Long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getPrePage() {
        return prePage;
    }

    public void setPrePage(Long prePage) {
        this.prePage = prePage;
    }

    public Long getNextPage() {
        return nextPage;
    }

    public void setNextPage(Long nextPage) {
        this.nextPage = nextPage;
    }

    public Long getCurrLine() {
        return currLine;
    }

    public void setCurrLine(Long currLine) {
        this.currLine = currLine;
    }

    public Long getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(Long totalLine) {
        this.totalLine = totalLine;
    }

}
