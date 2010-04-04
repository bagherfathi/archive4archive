package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelGroupOrg;

/**
 * RelGroupOrg 实体数据访问类
 * 
 * @spring.bean id="RelGroupOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelGroupOrgDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelGroupOrgDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelGroupOrg.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelGroupOrg getById(Serializable key) {
        return (RelGroupOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelGroupOrg loadById(Serializable key) {
        return (RelGroupOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * @param groupId
     * @param long1
     */
    public void deleteRelGroupOrg(Long groupId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelGroupOrg rgo");
        hql.append(" where rgo.").append(RelGroupOrg.PROP_GROUP_ID)
                .append("=?");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_ORG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { groupId, orgId });
    }
}