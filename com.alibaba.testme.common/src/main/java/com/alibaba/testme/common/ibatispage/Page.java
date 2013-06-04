/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huangjun Aug 5, 2011 5:35:44 PM
 */
public class Page<E> {

    public static final String  INDEX                 = "query_page_index";
    public static final String  SIZE                  = "query_page_size";

    public static final String  START_ROW             = "startRow";
    public static final String  END_ROW               = "endRow";

    /**
     * 缺省每页的显示记录数为20
     */
    public static final Integer DEFAULT_SIZE_PER_PAGE = 20;

    /**
     * 当前的页码
     */
    private Integer             pageIndex             = 1;
    /**
     * 总的页码
     */
    private Integer             pageSize              = 0;

    /**
     * 每页显示的记录数
     */
    private Integer             sizePerpage           = DEFAULT_SIZE_PER_PAGE;

    /**
     * 记录总数
     */
    private Integer             recordCount           = 0;

    /**
     * 数据内容
     */
    private List<E>             datas;

    private boolean             hasNext;

    private boolean             hasPre;

    /**
     * @param pageIndex
     * @param datas
     */
    public Page() {
    }

    public Page(Integer pageIndex, int recordCount) {
        this(pageIndex, DEFAULT_SIZE_PER_PAGE, recordCount, new ArrayList<E>());
    }

    /**
     * @param pageIndex
     * @param pageSize
     * @param datas
     */
    public Page(Integer pageIndex, int recordCount, List<E> datas) {
        this(pageIndex, DEFAULT_SIZE_PER_PAGE, recordCount, datas);
    }

    /**
     * @param pageIndex
     * @param sizePerpage
     * @param datas
     */
    public Page(Integer pageIndex, Integer sizePerpage, int recordCount) {
        this(pageIndex, sizePerpage, recordCount, new ArrayList<E>());
    }

    /**
     * @param pageIndex
     * @param sizePerpage
     * @param datas
     */
    public Page(Integer pageIndex, Integer sizePerpage, int recordCount, List<E> datas) {
        if (pageIndex != null) {
            this.pageIndex = pageIndex;
        }
        if (sizePerpage != null) {
            this.sizePerpage = sizePerpage;
        }

        this.recordCount = recordCount;

        this.setDatas(datas);

        this.pageSize = this.recordCount / this.sizePerpage;
        if (this.recordCount % this.sizePerpage != 0) {
            this.pageSize++;
        }

        int pre = this.pageIndex - 1;
        this.hasPre = pre > 0;
        int next = this.pageIndex + 1;
        this.hasNext = !(next > this.pageSize);
    }

    /**
     * @return the pageIndex
     */
    public Integer getPageIndex() {
        if (pageIndex == -1) {
            return this.pageSize;
        }
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the sizePerpage
     */
    public Integer getSizePerpage() {
        return sizePerpage;
    }

    /**
     * @param sizePerpage the sizePerpage to set
     */
    public void setSizePerpage(Integer sizePerpage) {
        this.sizePerpage = sizePerpage;
    }

    /**
     * @return the datas
     */
    public List<E> getDatas() {
        return datas;
    }

    /**
     * @param datas the datas to set
     */
    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    /**
     * @return the recordCount
     */
    public Integer getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount the recordCount to set
     */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * 是否有下一页
     * 
     * @return
     */
    public boolean isHasNext() {
        return this.hasNext;
    }

    /**
     * 是否有上一页
     * 
     * @return
     */
    public boolean isHasPre() {
        return this.hasPre;
    }

    /**
     * @param parameter
     */
    @SuppressWarnings("rawtypes")
    public static Map constructPageInfo(PageDialect pageDialect, Object parameter) {
        Map pm = (Map) parameter;
        Object index = pm.get(Page.INDEX);
        if (index == null) {
            index = 1;
        }
        Object size = pm.get(Page.SIZE);
        if (size == null) {
            size = Page.DEFAULT_SIZE_PER_PAGE;
        }

        int intIndex = ((Integer) index) - 1;
        int intSize = (Integer) size;

        pageDialect.translatePageArguments(intIndex, intSize, pm);

        return pm;
    }

}
