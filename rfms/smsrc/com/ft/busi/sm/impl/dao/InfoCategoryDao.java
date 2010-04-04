package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.InfoCategory;

/**
 * InfoCategory 实体数据访问类
 * 
 * @spring.bean id="InfoCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class InfoCategoryDao extends BaseDao {

    /**
     * 构造函数
     */
    public InfoCategoryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return InfoCategory.class;
    }

    /**
     * 根据ID查找对象
     */
    public InfoCategory getById(Serializable key) {
        return (InfoCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public InfoCategory loadById(Serializable key) {
        return (InfoCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除信息分类实体。
     * 
     * @param categoryId
     *                信息分类ID。
     */
    public void deleteCategoryById(Long categoryId) {
        StringBuffer hql = new StringBuffer("from InfoCategory category");
        hql.append(" where category.").append(InfoCategory.PROP_CATEGORY_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { categoryId });

    }
}