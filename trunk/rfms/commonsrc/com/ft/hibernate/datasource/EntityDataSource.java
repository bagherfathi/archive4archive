package com.ft.hibernate.datasource;

import com.ft.commons.page.PageBean;

import java.io.Serializable;

import java.util.List;

public interface EntityDataSource extends Cloneable, Serializable {
    /**
     * �õ������
     *
     * @param page
     * @param paramsValues
     *
     * @return
     */
    public List getResultSet(PageBean page, ParamsValueResolver paramsValues);

    /**
     *
     * @param paramsValues
     *
     * @return
     */
    public int count(ParamsValueResolver paramsValues);
}
