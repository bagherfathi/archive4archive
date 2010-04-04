package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelGroupRole;

/**
 * RelGroupRole 实体数据访问类
 * 
 * @spring.bean id="RelGroupRoleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelGroupRoleDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelGroupRoleDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelGroupRole.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelGroupRole getById(Serializable key) {
        return (RelGroupRole) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelGroupRole loadById(Serializable key) {
        return (RelGroupRole) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除操作员组分配的角色。
     * 
     * @param groupId
     *                操作员组ID。
     * @param orgId
     *                可访问组织ID。
     * @param roleId
     *                角色ID。
     */
    public void deleteRelGroupRole(Long groupId, Long orgId, Long roleId) {
        StringBuffer hql = new StringBuffer("from RelGroupRole rgr");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ROLE_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { groupId, orgId,
                roleId });

    }
    
    /**
     * 删除操作员组分配角色。
     * @param groupId   操作员组Id
     * @param orgId     操作员组可访问组织Id
     */
    public void deleteRelGroupRole(Long groupId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelGroupRole rgr");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { groupId, orgId});
    }
}