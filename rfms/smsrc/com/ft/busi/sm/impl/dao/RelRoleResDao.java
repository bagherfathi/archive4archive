
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelRoleRes;

/**
 * RelRoleRes 实体数据访问类
 * 
 * @spring.bean id="RelRoleResDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleResDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelRoleResDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelRoleRes.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleRes getById(Serializable key) {
        return (RelRoleRes) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleRes loadById(Serializable key) {
        return (RelRoleRes) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除角色分配的功能权限。
     * 
     * @param roleId
     *                角色ID。
     * @param long1
     *                功能权限ID。
     */
    public void deleteRelRoleRes(Long roleId, Long resourceId) {
        StringBuffer hql = new StringBuffer("from RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        hql.append(" and rrr.").append(RelRoleRes.PROP_RESOURCE_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(),
                new Object[] { roleId, resourceId });
    }

    /**
     * 查询角色拥有的功能权限。
     * 
     * @param roleId
     *                角色ID。
     * @return
     */
    public List findRelRoleResByRoleId(Long roleId) {
        StringBuffer hql = new StringBuffer("from RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        return this.query(hql.toString(), new Object[] { roleId });
    }
    
    /**
     *  删除角色分配的所有功能权限。
     * @param roleId    角色ID。
     */
    public void deleteRelRoleRes(Long roleId){
        StringBuffer hql = new StringBuffer("from RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{roleId});
    }
}