
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOrgRegion;

/**
 * RelOrgRegion 实体数据访问类
 * 
 * @spring.bean id="RelOrgRegionDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOrgRegionDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelOrgRegionDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelOrgRegion.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelOrgRegion getById(Serializable key) {
        return (RelOrgRegion) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelOrgRegion loadById(Serializable key) {
        return (RelOrgRegion) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询组织可访问区域关系
     * 
     * @param orgId
     *                组织ID
     * @param regionId
     *                区域ID
     * @return 组织可访问区域关系
     */
    public void deleteRelOrgRegion(Long orgId, Long regionId) {
        StringBuffer hql = new StringBuffer("from RelOrgRegion ror");
        hql.append(" where ror.").append(RelOrgRegion.PROP_ORG_ID).append("=?");
        hql.append(" and ror.").append(RelOrgRegion.PROP_REGION_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { orgId, regionId });
    }
}