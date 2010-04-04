package com.ft.hibernate.datasource;

import com.ft.commons.page.PageBean;

import java.util.List;


/**
 * Hibernate ≤È—Ø
 *
 */
public class HibernateQueryDataSource extends EntityAbstractDataSource {
    private static final long serialVersionUID = 1L;
    private String queryString;

    /*
     *  (non-Javadoc)
     * @see com.ft.hibernate.datasource.EntityDataSource#getResultSet(
     * com.ft.commons.page.PageBean,
     * com.ft.hibernate.datasource.ParamsValueResolver)
     */
    public List getResultSet(PageBean page, ParamsValueResolver paramsValues) {
        List aParams = paramsValues.getParamValues(this.params);
        Object[] paramValues = getParamValues(aParams);

        return this.getQueryHelper().query(queryString, paramValues);
    }

    /*
     *  (non-Javadoc)
     * @see com.ft.hibernate.datasource.EntityDataSource#count(
     * com.ft.hibernate.datasource.ParamsValueResolver)
     */
    public int count(ParamsValueResolver paramsValues) {
        List aParams = paramsValues.getParamValues(this.params);
        Object[] paramValues = getParamValues(aParams);

        return this.getQueryHelper().count(queryString, paramValues);
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
