package com.ft.hibernate.callback;

import com.ft.commons.page.PageBean;


/**
 * 根据查询名称和PageBean对象进行查询
 *
 */
public class QueryByNamedPageCallback extends QueryByNamedCallback {
    PageBean pageBean;

    public QueryByNamedPageCallback(
        String string, Object[] params, PageBean pageBean) {
        super(
            string, params, pageBean.getCurrentPageFirstRecord(),
            pageBean.getCurrentPageSize());
    }
}
