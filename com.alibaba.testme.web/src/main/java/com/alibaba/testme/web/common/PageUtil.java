/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-6-4
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
package com.alibaba.testme.web.common;

import org.springframework.stereotype.Component;

import com.alibaba.testme.common.ibatispage.Page;

/**
 * 分页工具类
 * 
 * @author lz
 */
@Component
public class PageUtil {

    private static final int PAGE_NO_LIST_SIZE = 7;

    /**
     * 生成页面分页组件
     * 
     * @param pageBean
     * @return
     */
    public static String genPageToolbar(Page<?> page) {
        return genPageToolbar(page, null, PAGE_NO_LIST_SIZE);
    }

    /**
     * @param page
     * @param formName
     * @return
     */
    public static String genPageToolbar(Page<?> page, String formName) {
        return genPageToolbar(page, formName, PAGE_NO_LIST_SIZE);
    }

    /**
     * @param pageBean
     * @param formName
     * @param pageNoListSize
     * @return
     */
    public static String genPageToolbar(Page<?> page, String formName, int pageNoListSize) {
        StringBuffer buf = new StringBuffer();
        long recordCount = page.getRecordCount(); // 总记录数
        int sizePerpage = page.getSizePerpage(); // 一页有多少条记录
        int pageCount = page.getPageSize(); // 总的页数
        int pageIndex = page.getPageIndex();// 当前页码

        int minPageCount = pageCount > 0 ? 1 : 0;
        int prePage = pageIndex - 1;
        int nextPage = pageIndex + 1;

        if (pageNoListSize == 0) {
            pageNoListSize = PAGE_NO_LIST_SIZE;
        }

        buf.append("<div class=\"r3\">");
        buf.append("<ul class=\"pagination\">");
        if (pageCount > 0) {
            buf.append("<li class=\"count\">共").append(recordCount).append("条记录,&nbsp; ")
                    .append(pageCount).append("页，");
            buf.append("当前是第 ").append(pageIndex).append(" 页");

            buf.append("到<input type='text' name='jumpto' size='3' title='指定页码'> 页");
            buf.append("<button type='submit' onclick=\"doSearch(document.");
            buf.append(formName).append(".jumpto.value) \">确定</button>");

            buf.append("</li>");
            if (prePage > 0) {
                buf.append("<li class=\"start\"><a href=# onclick=doPageSearch('").append(1)
                        .append("')>首页</a></li>");
                buf.append("<li class=\"previous-off\"><a href=# onclick=doPageSearch('")
                        .append(prePage).append("')>前一页</a></li>");
            } else {
                buf.append("<li class=\"start\">首页&nbsp;</li>");
                buf.append("<li class=\"previous-off\">前一页&nbsp;</li>");
            }

            int step = pageNoListSize / 2 + pageNoListSize % 2;
            int start = 0;
            int end = 0;
            int dura1 = pageIndex - step;
            if (dura1 <= 0) {
                start = 1;
                if (pageNoListSize < pageCount) {
                    end = pageNoListSize;
                } else {
                    end = pageCount;
                }
            } else {
                end = pageIndex + step - pageNoListSize % 2;
                if (end >= pageCount) {
                    end = pageCount;
                    start = pageCount - pageNoListSize + 1;
                } else {
                    start = dura1 + 1;
                }
            }
            for (int i = start; i <= end; i++) {
                if (i == pageIndex) {
                    buf.append("<li class=\"active\">").append(i).append("</li>");
                } else {
                    buf.append("<li><a href=\"#\" <a href=# onclick=\"doPageSearch('").append(i)
                            .append("');\">").append(i).append("</a></li>");
                }
            }

            if (nextPage <= pageCount) {
                buf.append("<li class=\"next\"><a href=# onclick=\"doPageSearch('")
                        .append(nextPage).append("');\">&nbsp;下一页</a></li>");
                buf.append("<li class=\"next\"><a href=# onclick=\"doPageSearch('")
                        .append(pageCount).append("');\">末页</a></li>");
            } else {
                buf.append("<li class=\"next\">&nbsp;下一页</li>");
                buf.append("<li class=\"next\">&nbsp;末页</li>");
            }
        } else {
            buf.append("<li class=\"count\">未找到记录</li>");
        }
        buf.append("</ul></div>");
        buf.append("\n<input type=\"hidden\" name=\"sizePerPage\" value=\"")
                .append(sizePerpage).append("\"/>\n");
        buf.append("<input type=\"hidden\" name=\"pageIndex\" value=\"1\"/>\n");
        buf.append("<script type=\"text/javascript\"> \n");
        if (formName == null || formName.trim().length() == 0) {
            formName = "forms[0]";
        }
        buf.append("function doPageSearch(pageIndex) { \n");
        buf.append("if(''==pageIndex || pageIndex<=0 || pageIndex>").append(pageCount).append("){");
        buf.append("alert(\"请输入正确的页码，页码最小为").append(minPageCount).append("，最大为").append(pageCount)
                .append("\"); \n return;\n}");
        buf.append(" document.").append(formName)
                .append("[\"pageIndex\"].value=pageIndex;\n");
        buf.append(" try{ loading(); } catch(e){};");
        buf.append(" document.").append(formName).append(".submit(); \n");
        buf.append("}\n");
        buf.append("</script>\n");

        return buf.toString();
    }

}
