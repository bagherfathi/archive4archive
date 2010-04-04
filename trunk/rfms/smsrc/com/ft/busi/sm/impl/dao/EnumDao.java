package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Enum;
import com.ft.sm.entity.EnumCategory;

/**
 * Enum 实体数据访问类
 * 
 * @spring.bean id="EnumDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumDao extends BaseDao {

    /**
     * 构造函数
     */
    public EnumDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Enum.class;
    }

    /**
     * 根据ID查找对象
     */
    public Enum getById(Serializable key) {
        return (Enum) this.getHibernateTemplate().get(getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public Enum loadById(Serializable key) {
        return (Enum) this.getHibernateTemplate()
                .load(getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询枚举数据类别中的枚举数据。
     * 
     * @param categoryId
     *                枚举数据类别ID。
     * @return ，枚举数据实体列表。
     */
    public List findEnumDatasByCategory(Long categoryId) {
        StringBuffer hql = new StringBuffer("from Enum enumData");
        hql.append(" where enumData.").append(Enum.PROP_CATEGORY_ID).append(
                "=?");
        hql.append(" and enumData.").append(Enum.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { categoryId });
    }

    /**
     * 查询枚举数据类别中的枚举数据。
     * 
     * @param categoryCode
     *                枚举数据类别代码。
     * @return 枚举数据实体列表。
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
     * 查询枚举数据组中的枚举数据。
     * 
     * @param groupId
     *                枚举数据组ID。
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