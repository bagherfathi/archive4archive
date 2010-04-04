
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskCategory;

/**
 * TaskCategory ʵ�����ݷ�����
 * 
 * @spring.bean id="TaskCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskCategoryDao extends BaseDao {

    /**
     * ���캯��
     */
    public TaskCategoryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return TaskCategory.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskCategory getById(Serializable key) {
        return (TaskCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskCategory loadById(Serializable key) {
        return (TaskCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}