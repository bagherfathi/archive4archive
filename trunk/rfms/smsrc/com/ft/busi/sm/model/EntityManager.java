package com.ft.busi.sm.model;

import java.io.Serializable;
import java.util.List;

import com.ft.commons.page.PageBean;

/**
 * ʵ�����ӿڡ�
 * 
 * @version 1.0
 */
public interface EntityManager {
    /**
     * ����ָ����һ��ID��ѯʵ�塣
     * 
     * @param ids
     *                ʵ��ID���顣
     * @param typeClass
     *                ʵ���ࡣ
     * @return
     */
    public List loadByIds(Class typeClass, Serializable[] ids) throws Exception;

    /**
     * ����ָ��ID��ѯʵ�塣
     * 
     * @param id
     *                ʵ��ID��
     * @param typeClass
     *                ʵ���ࡣ
     * @return
     */
    public Object getEntity(Class typeClass, Serializable id) throws Exception;

    /**
     * ���ݲ�ѯ����ҳ��ѯ��
     * 
     * @param typeClass
     *                Ҫ��ѯ��ʵ���ࡣ
     * @param hql
     *                ��ѯ���HQL��
     * @param params
     *                ������
     * @param page
     *                ��ҳ��Ϣ��
     * @return
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) throws Exception;
}
