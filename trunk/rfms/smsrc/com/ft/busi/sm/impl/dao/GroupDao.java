package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Resource;

/**
 * Group 实体数据访问类
 * 
 * @spring.bean id="GroupDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class GroupDao extends BaseDao {

    /**
     * 构造函数
     */
    public GroupDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Group.class;
    }

    /**
     * 根据ID查找对象
     */
    public Group getById(Serializable key) {
        return (Group) this.getHibernateTemplate()
                .get(getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public Group loadById(Serializable key) {
        return (Group) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询操作员可分配操作员组
     * 
     * @param operatorId
     *            操作员ID
     * @param orgIds
     *            操作员的可访问组织ID
     * @return 操作员组实体列表
     */
    public List findAvailableGroupsByOperator(Long operatorId, Long orgId) {
        // modified by libf,2006/12/27
        // 查询操作员可分配操作员组时按照操作员所属分公司
        /*
         * StringBuffer hql = new StringBuffer("select group from Group group");
         * hql.append(" where group.").append(Group.PROP_GROUP_ID).append( " not
         * in ("); hql.append(" select
         * rog.").append(RelOperGroup.PROP_GROUP_ID).append( " from RelOperGroup
         * rog"); hql.append(" where
         * rog.").append(RelOperGroup.PROP_OPERATOR_ID).append( "=?");
         * hql.append(")");
         * 
         * List result = this.query(hql.toString(), new Object[] { operatorId
         * }); for (Iterator iterator = result.iterator(); iterator.hasNext();) {
         * Group group = (Group) iterator.next();
         * if(!ArrayUtils.contains(orgIds, new Long(group.getOwnerOrgId()))){
         * iterator.remove(); } } return result;
         */
        StringBuffer hql = new StringBuffer("select group");
        hql.append(" from Group group");
        hql.append(" where group.").append(Group.PROP_GROUP_ID).append(
                " not in ");
        hql.append(" (select rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" from RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(")");

        hql.append(" and group.").append(Group.PROP_OWNER_ORG_ID).append(" in ");
        hql.append(" ( select org.orgId from Organization org where org.").append(
                Organization.PROP_ORG_PATH).append(" like '%#' || ? || '#%' )");
        hql.append(" and group.").append(Group.PROP_EXPIRE_DATE).append(
                " is null");

        return this.query(hql.toString(), new Object[] { operatorId, orgId });
        // end modified
    }

    /**
     * 查询操作员已分配操作员组
     * 
     * @param operatorId
     *            操作员ID
     * @return 操作员组实体列表
     */
    public List findGroupsByOperator(Long operatorId) {
        StringBuffer hql = new StringBuffer(
                "select group from Group group,RelOperGroup rog");
        hql.append(" where group.").append(Group.PROP_GROUP_ID).append("=rog.")
                .append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        return this.query(hql.toString(), new Object[] { operatorId });
    }

    public List findGroupsByOperator(Long operatorId, Long orgId) {
        StringBuffer hql = new StringBuffer(
                "select group from Group group,RelOperGroup rog");
        hql.append(" where group.").append(Group.PROP_GROUP_ID).append("=rog.")
                .append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and group.").append(Group.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" and group.").append(Group.PROP_OWNER_ORG_ID)
                .append(" in ");
        hql.append(" ( select org.orgId from Organization org where org.").append(
                Organization.PROP_ORG_PATH).append(" like '%#' || ? || '#%' )");
        return this.query(hql.toString(), new Object[] { operatorId ,orgId});
    }

    /**
     * 查询和功能权限相关的操作员组
     * 
     * @param resourceId
     *            功能权限ID
     * @return 操作员组实体列表
     */
    public List findGroupsByResource(Long orgId, Long resourceId) {
        StringBuffer hql = new StringBuffer("select distinct gr");
        hql
                .append(" from Group gr,Resource res,RelRoleRes rrr,RelGroupRole rgr");
        hql.append(" where rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=gr.").append(Group.PROP_GROUP_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_ROLE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append("=?");
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like ").append("rrr.").append(RelRoleRes.PROP_RESOURCE_PATH)
                .append(" || '%'");

        return this.query(hql.toString(), new Object[] { orgId, resourceId });
    }

    /**
     * 查询操作员所在操作员组
     * 
     * @param operatorId
     *            操作员ID
     * @return 操作员组实体列表
     */
    public List findGroupByOperator(Long operatorId) {
        StringBuffer hql = new StringBuffer(
                "select group from Group group,RelOperGroup rog");
        hql.append(" where group.").append(Group.PROP_GROUP_ID).append("=rog.")
                .append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");

        return this.query(hql.toString(), new Object[] { operatorId });
    }

    /**
     * 查询操作员组
     * 
     * @param opId
     *            当前操作员
     * @param orgId
     *            组织ID 可以为null
     * @param groupName
     *            组名 可以为null
     * @param opLoginName
     *            操作员登陆名 可以为null
     * @return
     */
    public List searchGroup(Long orgId, String groupName, boolean includeAll) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append(" from Group group,Organization org ");
        hql.append(" where group.").append(Group.PROP_OWNER_ORG_ID);
        hql.append(" = ").append(" org.").append(Organization.PROP_ORG_ID);
        hql.append(" and group.").append(Group.PROP_EXPIRE_DATE).append(
                " is null");
        if (!includeAll) {
            hql.append(" and org.").append(Organization.PROP_ORG_ID).append(
                    "=?");
            params.add(orgId);
        } else {
            hql.append(" and org.").append(Organization.PROP_ORG_PATH).append(
                    " like '%#' || ? || '#%'");
            params.add(orgId);
        }
        if (StringUtils.isNotEmpty(groupName)) {
            hql.append(" and group.").append(Group.PROP_GROUP_NAME);
            hql.append(" like ?");
            params.add("%" + groupName + "%");
        }
        return this.query(hql.toString(), params.toArray());
    }
}