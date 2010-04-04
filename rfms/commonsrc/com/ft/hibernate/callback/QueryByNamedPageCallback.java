package com.ft.hibernate.callback;

import com.ft.commons.page.PageBean;


/**
 * ���ݲ�ѯ���ƺ�PageBean������в�ѯ
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
