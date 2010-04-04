package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Enum;
import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumEntry;

/**
 * EnumEntry 实体数据访问类
 * 
 * @spring.bean id="EnumEntryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumEntryDao extends BaseDao {

    /**
     * 构造函数
     */
    public EnumEntryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return EnumEntry.class;
    }

    /**
     * 根据ID查找对象
     */
    public EnumEntry getById(Serializable key) {
        return (EnumEntry) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public EnumEntry loadById(Serializable key) {
        return (EnumEntry) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询枚举数据中的数据条目并按照其顺序进行排序。
     * 
     * @param enumId
     *                枚举数据ID
     * @return 枚举数据条目实体列表
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
     * 删除枚举数据中的枚举数据条目。
     * 
     * @param enumId
     *                枚举数据ID。
     */
    public void deleteEntriesByEnum(Long enumId) {
        StringBuffer hql = new StringBuffer("from EnumEntry entry");
        hql.append(" where entry.").append(EnumEntry.PROP_ENUM_ID).append("=?");
        hql.append(" and entry.").append(EnumEntry.PROP_EXPIRE_DATE).append(" is null");

        this.deleteFromQuery(hql.toString(), new Object[] { enumId });
    }

    /**
     * 查询指定枚举数据中所有数据条目。
     * 
     * @param categoryCode
     *                枚举数据类别代码。
     * @param enumCode
     *                枚举数据代码。
     * @return EnumDataEntry实体列表。
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
     * 查询指定枚举数据中所有数据条目。
     * 
     * @param categoryCode
     *                枚举数据类别代码。
     * @param enumCode
     *                枚举数据代码。
     * @param linkValue
     *                枚举数据条目关联值。
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
     * 查询枚举数据组中的数据条目。
     * 
     * @param groupId
     *                枚举数据组ID。
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