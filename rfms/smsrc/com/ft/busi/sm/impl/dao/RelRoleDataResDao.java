
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelRoleDataRes;

/**
 * RelRoleDataRes 实体数据访问类
 * 
 * @spring.bean id="RelRoleDataResDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleDataResDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelRoleDataResDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelRoleDataRes.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleDataRes getById(Serializable key) {
        return (RelRoleDataRes) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleDataRes loadById(Serializable key) {
        return (RelRoleDataRes) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除角色拥有的业务权限。
     * 
     * @param roleId
     *                角色ID。
     * @return
     */
    public List findRelRoleDataResByRoleId(Long roleId) {
        StringBuffer hql = new StringBuffer("from RelRoleDataRes rrd");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=?");
        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * 删除角色拥有的业务权限。
     * 
     * @param roleId
     *                角色ID。
     * @param entryId
     *                业务权限条目ID。
     */
    public void deleteRelRoleDataRes(Long roleId, Long entryId) {
        StringBuffer hql = new StringBuffer("from RelRoleDataRes rrd");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=?");
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ENTRY_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { roleId, entryId });
        // TODO Auto-generated method stub

    }
}