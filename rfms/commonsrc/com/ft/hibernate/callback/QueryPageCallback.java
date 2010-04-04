package com.ft.hibernate.callback;

import com.ft.commons.page.PageBean;


public class QueryPageCallback extends QueryCallback {
    public QueryPageCallback(
        String queryString, Object[] params, PageBean pageBean) {
        super(
            queryString, params, pageBean.getCurrentPageFirstRecord(),
            pageBean.getCurrentPageSize());
    }
}
