package com.ft.entity;

import com.ft.commons.page.PageBean;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;


public interface EntityQuery {
    /**
     * 根据对象类型和ID查询一个对象
     *
     * @param entityClass
     * @param id
     *
     * @return
     *
     * @throws DataAccessException
     */
    public abstract Object get(Class entityClass, Serializable id);

    
    /**
     * 查询对一个HSQL的值
     *
     * @param queryString
     * @param params
     *
     * @return
     */
    public abstract int count(String queryString, final Object[] params);

    /**
     * 根据某个属性查询是否存在这样的记录
     *
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     *
     * @return 存在返回true
     */
    public abstract boolean exist(
        final Class clazz, String attribute, Object value);

    /**
     * 根据某个属性查询是否存在这样的记录
     *
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     *
     * @return 存在返回true
     */
    public abstract boolean exist(
        final Class clazz, String[] attribute, Object[] value);

    /**
     * 根据某个属性查询是否存在这样的记录
     *
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     *
     * @return 存在返回true
     */
    public abstract boolean existByOr(
        final Class clazz, String attribute, Object value);

    /**
     * 根据某个属性查询是否存在这样的记录
     *
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     *
     * @return 存在返回true
     */
    public abstract boolean existByOr(
        final Class clazz, String[] attribute, Object[] value);

    /**
     * 根据ID表名查询ID
     *
     * @param clazz
     * @param keys
     *
     * @return
     */
    public abstract List loadByIds(
        final Class clazz, final Serializable[] keys);

    /**
     * 根据某个特定属性查询一个对象ID
     *
     * @param name 对象名称
     * @param attribute 要求返回的属性
     * @param value
     *
     * @return
     */
    public abstract Object getEntityByIdentityAttribute(
        Class clazz, String attribute, Object value);

    /**
     * 查询所有对象
     *
     * @param aClass
     *
     * @return
     */
    public abstract List loadAll(Class aClass);

    /**
     * 分页查询对象
     *
     * @param queryStr 查询语句
     * @param params
     * @param pageBean
     *
     * @return
     */
    public abstract List query(
        final String queryStr, final Object[] params, final PageBean pageBean);

    /**
     * 分面查询
     *
     * @param queryStr
     * @param params
     * @param begin 起始记录数
     * @param length 查询个数
     *
     * @return
     */
    public abstract List query(
        final String queryStr, final Object[] params, final int begin,
        final int length);

    /**
     * 根据ID得到一个对象，在调用之前要保证这个记录已经存在否则会抛出异常
     *
     * @param entityClass
     * @param key
     *
     * @return
     */
    public abstract Object load(Class entityClass, Serializable key);

    /**
     * 查询
     *
     * @param string
     *
     * @return
     */
    public abstract List query(String queryStr);

    /**
     * 查询
     *
     * @param string
     * @param username
     *
     * @return
     */
    public abstract List query(String queryStr, Object[] params);

    /**
     * 根据预定查询
     */
    public abstract List findByNamedQuery(String queryName);

    /**
     * 根据预定查询
     */
    public abstract List findByNamedQuery(String queryName, Object[] params);

    /**
     * 根据预定查询
     */
    public abstract List queryByNamed(
        final String queryString, final Object[] params, final int size);

    /**
     * 根据预定查询
     */
    public abstract List queryByNamed(
        final String named, final Object[] params, PageBean pageBean);

    /**
     *
     * @param clazz
     * @param objects
     * @param pageBean
     */
    public abstract List query(Class clazz, PageBean pageBean);

    public abstract List query(String string, Object[] objects, int maxSize);

    /**
     * 分组查询
     *
     * @param attribute
     * @param clazz
     *
     * @return
     */
    public abstract List countGroup(Class clazz, String attribute);

    public abstract Iterator iterate(String src);

    public abstract List findAttribute(Class entityClass, String[] strings);

    public abstract List queryByNotInIds(Class class1, Long[] ids);

    public abstract Object getSingle(String string, Object[] objects);

    public abstract Object getEntityIdByIdentityAttribute(
        Class entityClass, String keyAttribute, String attribute, Object obj);
}
