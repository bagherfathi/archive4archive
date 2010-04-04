package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.EnumCategory;

/**
 * EnumCategory 实体数据访问类
 * 
 * @spring.bean id="EnumCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumCategoryDao extends BaseDao {

    /**
     * 构造函数
     */
    public EnumCategoryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return EnumCategory.class;
    }

    /**
     * 根据ID查找对象
     */
    public EnumCategory getById(Serializable key) {
        return (EnumCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public EnumCategory loadById(Serializable key) {
        return (EnumCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除枚举数类别实体。
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