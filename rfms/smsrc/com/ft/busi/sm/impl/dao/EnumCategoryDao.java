package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.EnumCategory;

/**
 * EnumCategory ʵ�����ݷ�����
 * 
 * @spring.bean id="EnumCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumCategoryDao extends BaseDao {

    /**
     * ���캯��
     */
    public EnumCategoryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return EnumCategory.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumCategory getById(Serializable key) {
        return (EnumCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumCategory loadById(Serializable key) {
        return (EnumCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ��ö�������ʵ�塣
     * 
     * @param categoryId
     */
    public void deleteEnumCategory(Long categoryId) {
        StringBuffer hql = new StringBuffer("from EnumCategory enumCategory");
        hql.append(" where enumCategory.")
                .append(EnumCategory.PROP_CATEGORY_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { categoryId });
    }

    /**
     * @param groupId
     * @return
     */
    public List findEnumCategoriesOfGroup(Long groupId) {
        StringBuffer hql = new StringBuffer("from EnumCategory enumCategory");
        hql.append(" where enumCategory.").append(EnumCategory.PROP_GROUP_ID)
                .append("=?");
        hql.append(" and enumCategory.").append(EnumCategory.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { groupId });
    }
    
    public List findEnumCategoriesOfGroupOrderByName(Long groupId) {
        StringBuffer hql = new StringBuffer("from EnumCategory enumCategory");
        hql.append(" where enumCategory.").append(EnumCategory.PROP_GROUP_ID)
                .append("=?");
        hql.append(" and enumCategory.").append(EnumCategory.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" order by enumCategory.").append(EnumCategory.PROP_CATEGORY_NAME);
        
        return this.query(hql.toString(), new Object[] { groupId });
    }
}