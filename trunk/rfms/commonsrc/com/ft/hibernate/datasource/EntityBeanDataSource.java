package com.ft.hibernate.datasource;

import com.ft.commons.page.PageBean;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 *  µÃÂBean
 *
 * @author Coffee
 */
public class EntityBeanDataSource extends EntityAbstractDataSource {
    private static final long serialVersionUID = 1305557790243308307L;
    private Class beanClass;
    private String aliasName;
    private String orderFields;

    public List getResultSet(PageBean page, ParamsValueResolver paramsValues) {
        Object[] paramValues = new Object[0];
        StringBuffer buffer = new StringBuffer(" from ");
        this.getFromString(buffer);

        if (paramsValues != null) {
            List aParams = paramsValues.getParamValues(this.params);
            this.getConditionString(buffer, aParams);
            paramValues = getParamValues(aParams);
        }
        
        if(null == page){
            return this.queryHelper.query(buffer.toString(), paramValues);
        }else{
            return queryHelper.query(buffer.toString(), paramValues, page);
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Object clone() {
        EntityBeanDataSource result = new EntityBeanDataSource();
        result.setBeanClass(this.beanClass);
        result.fetchModes.addAll(this.fetchModes);
        result.params.addAll(this.params);
        result.setQueryHelper(this.getQueryHelper());

        return result;
    }

    public void getConditionString(StringBuffer sql, List params) {
        sql.append(" where 1=1 ");

        String alias = this.aliasName;

        for (Iterator iter = params.iterator(); iter.hasNext();) {
            EntityParam element = (EntityParam) iter.next();
            sql.append(" and ");

            if (element.getType().equals(Date.class)) {
                sql.append(alias).append(".").append(element.getName());
                sql.append(" between ? and ? ");
            } else {
                sql.append(alias).append(".").append(element.getName());
                sql.append(" ");
                sql.append(element.getExpr());
                sql.append(" ? ");
            }
        }

        if (this.orderFields != null) {
            sql.append(" ").append(" order by ").append(this.aliasName)
               .append(".").append(this.orderFields);
        }
    }

    public void getFromString(StringBuffer sql) {
        sql.append(this.beanClass.getName()).append(" ").append(
            this.aliasName).append(" ");
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /*
     *  (non-Javadoc)
     * @see com.ft.hibernate.datasource.EntityDataSource
     * #count(com.ft.hibernate.datasource.ParamsValueResolver)
     */
    public int count(ParamsValueResolver paramsValues) {
        Object[] paramValues = new Object[0];
        StringBuffer buffer = new StringBuffer(" from ");
        this.getFromString(buffer);

        if (paramsValues != null) {
            List aParams = paramsValues.getParamValues(this.params);
            this.getConditionString(buffer, aParams);
            paramValues = getParamValues(aParams);
        }

        return queryHelper.count(buffer.toString(), paramValues);
    }

    public String getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(String orderFileds) {
        this.orderFields = orderFileds;
    }
}
