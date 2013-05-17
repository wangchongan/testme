/**
 * Project: taobaiji File Created at 2012-3-11 $Id$ Copyright 1999-2100 Alibaba.com Corporation Limited. All rights
 * reserved. This software is the confidential and proprietary information of Alibaba Company.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.page;

import java.util.List;

/**
 * 分页控件
 * 
 * @author chongan.wangca
 */
public class PageControler<T> {

    private Long    currPage;
    private Long    pageSize = 20L;
    private Long    pageCount;
    private Long    prePage;
    private Long    nextPage;
    private Long    currLine;
    private Long    totalLine;
    private List<T> resultList;

    public PageControler<T> pageFormat(Long page, Long toLine, Long pageSize) {
        PageControler<T> pc = new PageControler<T>();
        // 从数据库读取总行数
        Long totalLine = toLine;

        // 计算总页数
        Long pageCount = 0L;
        if (totalLine.longValue() > 0) {

            if (totalLine < pageSize) {
                pageCount = 1L;
            } else {
                pageCount = totalLine / pageSize;
                if (totalLine % pageSize != 0) {
                    pageCount++;
                }
            }
            // pageCount = totalLine < pageSize ? 1 : Math.round(Math.ceil((totalLine / pageSize))) + 1;

            // 修正页码
            if (page.longValue() < 1) page = 1L;
            if (page.longValue() > pageCount.longValue()) page = pageCount;

            // 计算已经翻过的行数的最后一行行码，因为在数据库，是从该行码开始读数据的。
            Long currLine = (page - 1) * pageSize;
            Long prePage = page - 1;
            Long nextPage = page + 1;

            pc.setCurrPage(page);
            pc.setPrePage(prePage);
            pc.setNextPage(nextPage);
            pc.setPageSize(pageSize);
            pc.setPageCount(pageCount);
            pc.setCurrLine(currLine);
            pc.setTotalLine(totalLine);
        } else {
            pc.setCurrPage(0L);
            pc.setPrePage(0L);
            pc.setNextPage(0L);
            pc.setPageSize(pageSize);
            pc.setPageCount(0L);
            pc.setCurrLine(0L);
            pc.setTotalLine(0L);
        }
        return pc;
    }

    public PageControler<T> pageFormat(Long page, Long toLine) {

        PageControler<T> pc = new PageControler<T>();
        // 从数据库读取总行数
        Long totalLine = toLine;
        // 计算总页数
        Long pageCount = 0L;

        if (totalLine.longValue() > 0) {
            pageCount = totalLine < pageSize ? 1 : Math.round(Math.ceil((totalLine / pageSize))) + 1;

            // 修正页码
            if (page.longValue() < 1) page = 1L;
            if (page.longValue() > pageCount.longValue()) page = pageCount;

            // 计算已经翻过的行数的最后一行行码，因为在数据库，是从该行码开始读数据的。
            Long currLine = (page - 1) * pageSize;
            Long prePage = page - 1;
            Long nextPage = page + 1;

            pc.setCurrPage(page);
            pc.setPrePage(prePage);
            pc.setNextPage(nextPage);
            pc.setPageSize(pageSize);
            pc.setPageCount(pageCount);
            pc.setCurrLine(currLine);
            pc.setTotalLine(totalLine);
        } else {
            pc.setCurrPage(0L);
            pc.setPrePage(0L);
            pc.setNextPage(0L);
            pc.setPageSize(pageSize);
            pc.setPageCount(0L);
            pc.setCurrLine(0L);
            pc.setTotalLine(0L);
        }
        return pc;
    }

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

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Long getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(Long totalLine) {
        this.totalLine = totalLine;
    }

    public Pager getPager() {
        Pager pager = new Pager();
        pager.setCurrLine(this.currLine);
        pager.setCurrPage(this.currPage);
        pager.setNextPage(this.nextPage);
        pager.setPageCount(this.pageCount);
        pager.setPageSize(this.pageSize);
        pager.setPrePage(this.prePage);
        pager.setTotalLine(this.totalLine);
        return pager;
    }

}
