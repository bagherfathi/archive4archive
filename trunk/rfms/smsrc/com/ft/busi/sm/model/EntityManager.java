package com.ft.busi.sm.model;

import java.io.Serializable;
import java.util.List;

import com.ft.commons.page.PageBean;

/**
 * 实体管理接口。
 * 
 * @version 1.0
 */
public interface EntityManager {
    /**
     * 根据指定的一组ID查询实体。
     * 
     * @param ids
     *                实体ID数组。
     * @param typeClass
     *                实体类。
     * @return
     */
    public List loadByIds(Class typeClass, Serializable[] ids) throws Exception;

    /**
     * 根据指定ID查询实体。
     * 
     * @param id
     *                实体ID。
     * @param typeClass
     *                实体类。
     * @return
     */
    public Object getEntity(Class typeClass, Serializable id) throws Exception;

    /**
     * 根据查询语句分页查询。
     * 
     * @param typeClass
     *                要查询的实体类。
     * @param hql
     *                查询语句HQL。
     * @param params
     *                参数。
     * @param page
     *                分页信息。
     * @return
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) throws Exception;
}
