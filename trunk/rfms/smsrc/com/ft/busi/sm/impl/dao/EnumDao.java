package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Enum;
import com.ft.sm.entity.EnumCategory;

/**
 * Enum ʵ�����ݷ�����
 * 
 * @spring.bean id="EnumDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumDao extends BaseDao {

    /**
     * ���캯��
     */
    public EnumDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Enum.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Enum getById(Serializable key) {
        return (Enum) this.getHibernateTemplate().get(getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Enum loadById(Serializable key) {
        return (Enum) this.getHibernateTemplate()
                .load(getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯö����������е�ö�����ݡ�
     * 
     * @param categoryId
     *                ö���������ID��
     * @return ��ö������ʵ���б�
     */
    public List findEnumDatasByCategory(Long categoryId) {
        StringBuffer hql = new StringBuffer("from Enum enumData");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=?");
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { categoryId });
    }

    /**
     * ��ѯö����������е�ö�����ݡ�
     * 
     * @param categoryCode
     *                ö�����������롣
     * @return ö������ʵ���б�
     */
    public List findEnumDatasByCategory(String categoryCode) {
        StringBuffer hql = new StringBuffer(
                "from Enum enumData,EnumCategory enumCategory");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=enumCategory.").append(EnumCategory.PROP_CATEGORY_ID);
        hql.append(" and EnumCategory.")
                .append(EnumCategory.PROP_CATEGORY_CODE).append("=?");
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { categoryCode });
    }

    /**
     * ��ѯö���������е�ö�����ݡ�
     * 
     * @param groupId
     *                ö��������ID��
     * @return
     */
    public List findEnumsOfGroup(Long groupId) {
        StringBuffer hql = new StringBuffer("select enumData");
        hql.append(" from Enum enumData,EnumCategory category");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=category.").append(EnumCategory.PROP_CATEGORY_ID);
        hql.append(" and category.").append(EnumCategory.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { groupId });
    }
}