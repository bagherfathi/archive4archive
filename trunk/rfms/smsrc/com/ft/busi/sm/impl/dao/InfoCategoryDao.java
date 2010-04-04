package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.InfoCategory;

/**
 * InfoCategory ʵ�����ݷ�����
 * 
 * @spring.bean id="InfoCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class InfoCategoryDao extends BaseDao {

    /**
     * ���캯��
     */
    public InfoCategoryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return InfoCategory.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public InfoCategory getById(Serializable key) {
        return (InfoCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public InfoCategory loadById(Serializable key) {
        return (InfoCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ����Ϣ����ʵ�塣
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     */
    public void deleteCategoryById(Long categoryId) {
        StringBuffer hql = new StringBuffer("from InfoCategory category");
        hql.append(" where category.").append(InfoCategory.PROP_CATEGORY_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { categoryId });

    }
}