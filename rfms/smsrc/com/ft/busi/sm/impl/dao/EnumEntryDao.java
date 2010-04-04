package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Enum;
import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumEntry;

/**
 * EnumEntry ʵ�����ݷ�����
 * 
 * @spring.bean id="EnumEntryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumEntryDao extends BaseDao {

    /**
     * ���캯��
     */
    public EnumEntryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return EnumEntry.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumEntry getById(Serializable key) {
        return (EnumEntry) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumEntry loadById(Serializable key) {
        return (EnumEntry) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯö�������е�������Ŀ��������˳���������
     * 
     * @param enumId
     *                ö������ID
     * @return ö��������Ŀʵ���б�
     */
    public List findEntriesByEnum(Long enumId) {
        StringBuffer hql = new StringBuffer("from EnumEntry entry");
        hql.append(" where entry.").append(EnumEntry.PROP_ENUM_ID).append("=?");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" order by entry.").append(EnumEntry.PROP_ENTRY_ORDER);
        hql.append(" ,entry.").append(EnumEntry.PROP_ENUM_ENTRY_VALUE);

        return this.query(hql.toString(), new Object[] { enumId });
    }

    /**
     * ɾ��ö�������е�ö��������Ŀ��
     * 
     * @param enumId
     *                ö������ID��
     */
    public void deleteEntriesByEnum(Long enumId) {
        StringBuffer hql = new StringBuffer("from EnumEntry entry");
        hql.append(" where entry.").append(EnumEntry.PROP_ENUM_ID).append("=?");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");

        this.deleteFromQuery(hql.toString(), new Object[] { enumId });
    }

    /**
     * ��ѯָ��ö������������������Ŀ��
     * 
     * @param categoryCode
     *                ö�����������롣
     * @param enumCode
     *                ö�����ݴ��롣
     * @return EnumDataEntryʵ���б�
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode) {
        StringBuffer hql = new StringBuffer("select entry");
        hql.append(" from EnumEntry entry,Enum enumData,EnumCategory category");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=category.").append(EnumCategory.PROP_CATEGORY_ID);
        hql.append(" and entry.").append(EnumEntry.PROP_ENUM_ID).append(
                "=enumData.").append(Enum.PROP_ENUM_ID);
        hql.append(" and category.").append(EnumCategory.PROP_CATEGORY_CODE)
                .append("=?");
        hql.append(" and enumData.").append(Enum.PROP_ENUM_CODE).append("=?");
        
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and category.").append(EnumCategory.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(),
                new Object[] { categoryCode, enumCode });
    }

    /**
     * ��ѯָ��ö������������������Ŀ��
     * 
     * @param categoryCode
     *                ö�����������롣
     * @param enumCode
     *                ö�����ݴ��롣
     * @param linkValue
     *                ö��������Ŀ����ֵ��
     * @return
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode,
            String linkValue) {
        StringBuffer hql = new StringBuffer("select entry");
        hql.append(" from EnumEntry entry,Enum enumData,EnumCategory category");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=category.").append(EnumCategory.PROP_CATEGORY_ID);
        hql.append(" and entry.").append(EnumEntry.PROP_ENUM_ID).append(
                "=enumData.").append(Enum.PROP_ENUM_ID);
        hql.append(" and category.").append(EnumCategory.PROP_CATEGORY_CODE)
                .append("=?");
        hql.append(" and enumData.").append(Enum.PROP_ENUM_CODE).append("=?");
        hql.append(" and entry.").append(EnumEntry.PROP_ENUM_ENTRY_VALUE)
                .append("=?");
        
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and category.").append(EnumCategory.PROP_EXPIRE_DATE).append(" is null");
        return this.query(hql.toString(), new Object[] { categoryCode,
                enumCode, linkValue });
    }

    /**
     * ��ѯö���������е�������Ŀ��
     * 
     * @param groupId
     *                ö��������ID��
     * @return
     */
    public List findEntriesOfGroup(Long groupId) {
        StringBuffer hql = new StringBuffer("select entry");
        hql.append(" from EnumEntry entry,Enum enumData,EnumCategory category");
        hql.append(" where entry.").append(EnumEntry.PROP_ENUM_ID).append(
                "=enumData.").append(Enum.PROP_ENUM_ID);
        hql.append(" and enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=category.").append(EnumCategory.PROP_CATEGORY_ID);
        hql.append(" and category.").append(EnumCategory.PROP_GROUP_ID).append(
                "=?");
        
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and category.").append(EnumCategory.PROP_EXPIRE_DATE).append(" is null");
        
        hql.append(" order by entry.").append(EnumEntry.PROP_ENTRY_ORDER);
        hql.append(" ,entry.").append(EnumEntry.PROP_ENUM_ENTRY_VALUE);

        return this.query(hql.toString(), new Object[] { groupId });
    }

}