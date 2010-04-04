package com.ft.hibernate.datasource;

import com.ft.commons.page.PageBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  µÃÂ≤È—Ø
 *
 */
public class EntityQueryDataSource implements Serializable, EntityDataSource {
    private static final long serialVersionUID = 4783963386837692096L;
    private String queryString;
    private Map params = new HashMap();
    private List fetchModes = new ArrayList();

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /*
     *  (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public Object clone() {
        EntityQueryDataSource result = new EntityQueryDataSource();
        result.setQueryString(this.queryString);
        result.fetchModes.addAll(this.fetchModes);
        result.params.putAll(this.params);

        return result;
    }

    public List getFetchModes() {
        return fetchModes;
    }

    public void setFetchModes(List fetchModes) {
        this.fetchModes = fetchModes;
    }

    public List getResultSet(PageBean page, ParamsValueResolver paramsValues) {
        return null;
    }

    public int count(ParamsValueResolver paramsValues) {
        // TODO Auto-generated method stub
        return 0;
    }
}
