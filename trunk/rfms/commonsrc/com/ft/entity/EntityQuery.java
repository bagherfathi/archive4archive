package com.ft.entity;

import com.ft.commons.page.PageBean;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;


public interface EntityQuery {
    /**
     * ���ݶ������ͺ�ID��ѯһ������
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
     * ��ѯ��һ��HSQL��ֵ
     *
     * @param queryString
     * @param params
     *
     * @return
     */
    public abstract int count(String queryString, final Object[] params);

    /**
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     *
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     *
     * @return ���ڷ���true
     */
    public abstract boolean exist(
        final Class clazz, String attribute, Object value);

    /**
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     *
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     *
     * @return ���ڷ���true
     */
    public abstract boolean exist(
        final Class clazz, String[] attribute, Object[] value);

    /**
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     *
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     *
     * @return ���ڷ���true
     */
    public abstract boolean existByOr(
        final Class clazz, String attribute, Object value);

    /**
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     *
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     *
     * @return ���ڷ���true
     */
    public abstract boolean existByOr(
        final Class clazz, String[] attribute, Object[] value);

    /**
     * ����ID������ѯID
     *
     * @param clazz
     * @param keys
     *
     * @return
     */
    public abstract List loadByIds(
        final Class clazz, final Serializable[] keys);

    /**
     * ����ĳ���ض����Բ�ѯһ������ID
     *
     * @param name ��������
     * @param attribute Ҫ�󷵻ص�����
     * @param value
     *
     * @return
     */
    public abstract Object getEntityByIdentityAttribute(
        Class clazz, String attribute, Object value);

    /**
     * ��ѯ���ж���
     *
     * @param aClass
     *
     * @return
     */
    public abstract List loadAll(Class aClass);

    /**
     * ��ҳ��ѯ����
     *
     * @param queryStr ��ѯ���
     * @param params
     * @param pageBean
     *
     * @return
     */
    public abstract List query(
        final String queryStr, final Object[] params, final PageBean pageBean);

    /**
     * �����ѯ
     *
     * @param queryStr
     * @param params
     * @param begin ��ʼ��¼��
     * @param length ��ѯ����
     *
     * @return
     */
    public abstract List query(
        final String queryStr, final Object[] params, final int begin,
        final int length);

    /**
     * ����ID�õ�һ�������ڵ���֮ǰҪ��֤�����¼�Ѿ����ڷ�����׳��쳣
     *
     * @param entityClass
     * @param key
     *
     * @return
     */
    public abstract Object load(Class entityClass, Serializable key);

    /**
     * ��ѯ
     *
     * @param string
     *
     * @return
     */
    public abstract List query(String queryStr);

    /**
     * ��ѯ
     *
     * @param string
     * @param username
     *
     * @return
     */
    public abstract List query(String queryStr, Object[] params);

    /**
     * ����Ԥ����ѯ
     */
    public abstract List findByNamedQuery(String queryName);

    /**
     * ����Ԥ����ѯ
     */
    public abstract List findByNamedQuery(String queryName, Object[] params);

    /**
     * ����Ԥ����ѯ
     */
    public abstract List queryByNamed(
        final String queryString, final Object[] params, final int size);

    /**
     * ����Ԥ����ѯ
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
     * �����ѯ
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
