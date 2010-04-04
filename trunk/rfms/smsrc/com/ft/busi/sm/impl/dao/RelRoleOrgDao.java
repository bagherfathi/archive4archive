
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelRoleOrg;

/**
 * RelRoleOrg 实体数据访问类
 * 
 * @spring.bean id="RelRoleOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleOrgDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelRoleOrgDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelRoleOrg.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleOrg getById(Serializable key) {
        return (RelRoleOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelRoleOrg loadById(Serializable key) {
        return (RelRoleOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查找角色的适应组织
     * 
     * @param roleId
     * @return 组织实体列表
     */

    public List findOrgsOfRole(Long roleId) {
        StringBuffer hql = new StringBuffer();
        // hql.append(" from RelRoleOrg ");
        // return this.query(hql.toString());
        hql.append(" select org from RelRoleOrg rro,Organization org");
        hql.append(" where rro.");
        hql.append(RelRoleOrg.PROP_ROLE_ID).append("= ? ");
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append(" = ");
        hql.append(" rro.").append(RelRoleOrg.PROP_ORG_ID);
        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * 删除角色的适应组织
     * 
     * @param roleId
     * @param orgId
     *            可以为空
     */
    public void deleteRelRoleOrg(Long roleId, Long orgId) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from RelRoleOrg rro");
        hql.append(" where rro.").append(RelRoleOrg.PROP_ROLE_ID).append("=?");
        params.add(roleId);
        if (orgId != null && orgId.longValue() != 0) {
            hql.append(" and rro.").append(RelRoleOrg.PROP_ORG_ID).append("=?");
            params.add(orgId);
        }
        this.deleteFromQuery(hql.toString(), params.toArray());
    }
}