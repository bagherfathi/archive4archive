
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.Role;

/**
 * RelOperRole 实体数据访问类
 * 
 * @spring.bean id="RelOperRoleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperRoleDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelOperRoleDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelOperRole.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelOperRole getById(Serializable key) {
        return (RelOperRole) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelOperRole loadById(Serializable key) {
        return (RelOperRole) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除操作员拥有的角色。
     * 
     * @param operatorId
     *            操作员ID。
     * @param roleId
     *            角色ID。
     * @param orgId
     *            可访问组织ID。
     * @param icludeChildren 
     *            是否包括子组织
     */
    public void deleteRelOperRole(Long operatorId, Long roleId, Long orgId,
            boolean includeChildren) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from RelOperRole ror where 1=1 ");
        if (operatorId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID)
                    .append("=?");
            params.add(operatorId);
        }
        if (roleId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID).append(
                    "=?");
            params.add(roleId);
        }
        if (orgId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID);
            if (!includeChildren) {
                hql.append("=?");
                params.add(orgId);
            } else {
                hql.append(" in ( select org.orgId from ");
                hql.append(" Organization org where org.").append(
                        Organization.PROP_ORG_PATH);
                hql.append(" like '%#' || ").append(orgId.longValue()).append(
                        " || '#%' )");
            }
        }
        this.deleteFromQuery(hql.toString(), params.toArray());
    }

    /**
     * 删除操作员在对应组织下的权限。
     * 
     * @param operatorId
     *            操作员ID。
     * @param orgId
     *            组织机构ID。
     */
    // public void deleteRelOperRole(Long operatorId, Long orgId) {
    // StringBuffer hql = new StringBuffer("from RelOperRole ror");
    // hql.append(" where ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
    // "=?");
    // hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append("=?");
    //
    // this
    // .deleteFromQuery(hql.toString(), new Object[] { operatorId,
    // orgId });
    // }
    /**
     * 查询操作员绑定组织关系。
     * 
     * @param operatorId
     *            操作员ID。
     * @param roleId
     *            角色ID。
     * @param orgId
     *            组织机构ID。
     * @return
     */
    public RelOperRole findRelOperRole(Long operatorId, Long roleId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelOperRole ror");
        hql.append(" where ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID).append("=?");
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append("=?");

        return (RelOperRole) this.getSingle(hql.toString(), new Object[] {
                operatorId, roleId, orgId });
    }

    /**
     * 查询操作员绑定的角色。
     * 
     * @param operatorId
     *            操作员ID。
     * @param roleType
     *            角色类型。
     * @return
     */
    public List findRelOperRoleOfOp(Long operatorId, Long roleType) {
        StringBuffer hql = new StringBuffer("select ror from RelOperRole ror ");
        if (roleType != null) {
            hql.append(" , Role role ");
        }
        hql.append(" where ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        List<Object> params = new ArrayList<Object>();
        params.add(operatorId);
        if (roleType != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID).append(
                    "=role.").append(Role.PROP_ROLE_ID);
            hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
            params.add(roleType);
        }
        return this.query(hql.toString(), params.toArray());
    }
    
    /**
     * 根据角色标识和组织标识查询在该组织下该角色的所有员工
     * @param roleId
     * @param orgId
     * @return
     */
    public List findOperatorsOfRole(Long roleId,Long orgId){
    	StringBuffer hql=new StringBuffer();
    	hql.append("select op from RelOperRole ror,Operator op where op.");
    	hql.append(Operator.PROP_OPERATOR_ID).append("=ror.").append(RelOperRole.PROP_OPERATOR_ID);
    	hql.append(" and rof.").append(RelOperRole.PROP_ROLE_ID).append("=?");
    	if(orgId!=null && orgId.longValue()>0)
    	hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append("=").append(orgId);
    	hql.append(" and op.").append(Operator.PROP_EXPIRE_TIME).append(" is null");
    	return this.query(hql.toString(),new Long[]{roleId});
    }
}