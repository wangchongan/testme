/**
 * Project: lplsc.common.shared
 * 
 * File Created at 2012-11-26下午3:14:09
 * $Id$
 * 
 * Copyright 1999-2012 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

import java.util.List;

/**
 * @author zhengxing 2012-11-26下午3:14:09
 */
public abstract class BasePageItemCursor<T> implements PageItemCursor<T> {

    /**   **/
    private static final int DEFAULT_PAGE_SIZE = 1000;

    private int              pageSize          = DEFAULT_PAGE_SIZE;

    private int              totalCount        = -1;

    private int              currentPage;

    private int              totalPage         = 0;

    /**
     * @author zhengxing 2012-11-26下午3:14:28
     */
    @Override
    public List<T> nextPage() {
        List<T> list = getNextPageList();
        currentPage++;
        return list;
    }

    /**
     * @author zhengxing 2012-11-26下午3:30:46
     * @return
     */
    protected abstract List<T> getNextPageList();

    public int getNextPageRowStart() {
        return currentPage * pageSize;
    }

    /**
     * @author zhengxing 2012-11-26下午3:14:28
     */
    @Override
    public boolean hasNext() {

        if (totalCount < 0) {
            getCount();
        }

        return currentPage < totalPage;
    }

    /**
     * @author zhengxing 2012-11-26下午3:14:28
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;

    }

    /**
     * @author zhengxing 2012-11-26下午3:14:28
     */
    @Override
    public int getCount() {
        if (totalCount < 0) {
            totalCount = getTotalCount();
            if (totalCount > 0) {
                totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
                        : (int) (totalCount / pageSize) + 1;
            }
        }
        return totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    protected abstract int getTotalCount();

    public int getPageSize() {
        return pageSize;
    }

}
