package com.ft.common;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.sm.model.EntityManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.commons.page.PageBean;
import com.ft.hibernate.datasource.EntityAbstractDataSource;
import com.ft.hibernate.datasource.EntityParam;
import com.ft.hibernate.datasource.ParamsValueResolver;

/**
 * 实现统一分页查询实体的类。
 * 
 * @version 1.0
 */
public class EntityBeanDataSource extends EntityAbstractDataSource {
    private static final long serialVersionUID = -1574251477265686979L;

    private Class beanClass;

    private String aliasName;

    private String orderFields;

    private EntityManager entityManager;
    
    private String whereSql;

    public List getResultSet(PageBean page, ParamsValueResolver paramsValues) {
        Object[] paramValues = new Object[0];

        StringBuffer buffer = new StringBuffer();

        // 对于操作日志特殊处理原来继承关系
        if (this.beanClass.getName().equals(
                "com.ft.sm.entity.PerformanceLog")) {
            buffer
                    .append("select plog from PerformanceLog plog,OperatorLog log ");
            this.aliasName = "log";
        } else {
            buffer.append(" from ");
            this.getFromString(buffer);
        }

        if (paramsValues != null) {
            List aParams = paramsValues.getParamValues(this.params);
            this.getConditionString(buffer, aParams);
            paramValues = getParamValues(aParams);
        }

        /*
         * if(null == page){ return this.queryHelper.query(buffer.toString(),
         * paramValues); }else{ return queryHelper.query(buffer.toString(),
         * paramValues, page); }
         */
        try {
            return this.entityManager.getResultSet(beanClass,
                    buffer.toString(), paramValues, page);
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    @SuppressWarnings("unchecked")
	public Object clone() {
        EntityBeanDataSource result = new EntityBeanDataSource();
        result.setBeanClass(this.beanClass);
        result.fetchModes.addAll(this.fetchModes);
        result.params.addAll(this.params);
        result.setQueryHelper(this.getQueryHelper());

        return result;
    }

    public void getConditionString(StringBuffer sql, List params) {
        // 对于操作日志的特殊处理
        if (this.beanClass.getName().equals(
                "com.ft.sm.entity.PerformanceLog")) {
            sql.append(" where plog.logId=log.logId ");
        } else {
            sql.append(" where 1=1 ");
        }
        
        if(this.whereSql != null){
            sql.append(" and ").append(whereSql);
        }

        String alias = this.aliasName;

        for (Iterator iter = params.iterator(); iter.hasNext();) {
            EntityParam element = (EntityParam) iter.next();
            if(element.getType()==java.lang.Long.class && new Long(String.valueOf(element.getValue())).longValue()==-1){
        		continue;
        	}
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
            sql.append(" ").append(" order by ").append(this.aliasName).append(
                    ".").append(this.orderFields);
        }
    }

    public void getFromString(StringBuffer sql) {
        sql.append(this.beanClass.getName()).append(" ").append(this.aliasName)
                .append(" ");
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.hibernate.datasource.EntityDataSource
     *      #count(com.ft.hibernate.datasource.ParamsValueResolver)
     */
    public int count(ParamsValueResolver paramsValues) {
        Object[] paramValues = new Object[0];
        StringBuffer buffer = new StringBuffer();

        // 对于操作日志特殊处理原来继承关系
        if (this.beanClass.getName().equals(
                "com.ft.sm.entity.PerformanceLog")) {
            buffer
                    .append("select plog from PerformanceLog plog,OperatorLog log ");
            this.aliasName = "log";
        } else {
            buffer.append(" from ");
            this.getFromString(buffer);
        }

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

    /**
     * @return the whereSql
     */
    public String getWhereSql() {
        return whereSql;
    }

    /**
     * @param whereSql the whereSql to set
     */
    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    /**
     * @param entityManager
     *                the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
