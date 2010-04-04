
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleOrg;
import com.ft.sm.entity.Role;

/**
 * Role ʵ�����ݷ�����
 * 
 * @spring.bean id="RoleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RoleDao extends BaseDao {
    /**
     * ���캯��
     */
    public RoleDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Role.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Role getById(Serializable key) {
        return (Role) this.getHibernateTemplate().get(getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Role loadById(Serializable key) {
        return (Role) this.getHibernateTemplate()
                .load(getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ����Ա��ӵ�еĽ�ɫ
     * 
     * @param groupId
     *                ����Ա��ID
     * @param orgId
     *                Ϊnullʱ��ѯ����Ա������н�ɫ
     * @return
     */
    public List findRoleOfGroup(Long groupId, Long orgId) {
        StringBuffer query = new StringBuffer(
                "select role from RelGroupRole rel,Role role where 1=1");
        query.append(" and rel.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        query.append(" and rel.").append(RelGroupRole.PROP_ROLE_ID).append("=")
                .append("role.").append(Role.PROP_ROLE_ID);

        if (orgId == null || orgId.longValue() <= 0) {
            return this.query(query.toString(), new Object[] { groupId });
        } else {
            query.append(" and rel.").append(RelGroupRole.PROP_ORG_ID).append(
                    "= ?");
            return this
                    .query(query.toString(), new Object[] { groupId, orgId });
        }
    }

    /**
     * ��ѯ����Ա��ɷ���Ĺ��ܽ�ɫ��
     * 
     * @param groupId
     *                ����Ա��ID��
     * @param orgId
     *                ��֯ID��
     * @return ��ɫʵ���б�
     */
    public List findAvailableRoleOfGroup(Long groupId, Long orgId, long roleType) {
        StringBuffer hql = new StringBuffer("select role from Role role");
        hql.append(" where role.").append(Role.PROP_ROLE_ID).append(" not in ");
        hql.append("(");

        hql.append("select rgr.").append(RelGroupRole.PROP_ROLE_ID).append(
                " from RelGroupRole rgr");
        hql.append(" where 1=1");

        List<Object> params = new ArrayList<Object>();
        if (orgId != null) {
            hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append(
                    "=?");
            params.add(orgId);
        }

        if (groupId != null) {
            hql.append(" and rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                    "=?");
            params.add(groupId);
        }

        hql.append(")");

        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        params.add(new Long(roleType));

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }

    /**
     * ��ѯ����Ա�ɷ���Ľ�ɫ
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @param roleType
     *                ��ɫ����
     * @return
     */
    public List findAvailableRolesOfOperator(Long opId, Long orgId,
            long roleType) {
        StringBuffer hql = new StringBuffer("select role from Role role");
        hql.append(" where role.").append(Role.PROP_ROLE_ID).append(" not in ");
        hql.append("(");

        hql.append("select ror.").append(RelOperRole.PROP_ROLE_ID).append(
                " from RelOperRole ror");
        hql.append(" where 1=1");

        List<Object> params = new ArrayList<Object>();
        if (orgId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID)
                    .append("=?");
            params.add(orgId);
        }

        if (opId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID)
                    .append("=?");
            params.add(opId);
        }

        hql.append(")");

        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        params.add(new Long(roleType));

        hql.append(" order by role.").append(Role.PROP_ROLE_NAME);

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }

    /**
     * ��ѯ����Ա�ڿɷ�����֯�н�ɫ
     * 
     * @param operatorId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @param roleType
     *                ��ɫ���� ��ɫ����
     * @return
     */
    public List findRolesOfOperator(Long operatorId, Long orgId, long roleType) {
        StringBuffer hql = new StringBuffer(
                "select role from Role role,RelOperRole ror");
        hql.append(" where role.").append(Role.PROP_ROLE_ID).append("=ror.")
                .append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");

        List<Object> params = new ArrayList<Object>();
        params.add(operatorId);
        params.add(new Long(roleType));

        if (orgId != null) {
            hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID)
                    .append("=?");
            params.add(orgId);
        }

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }

    /**
     * @param roleType
     * @return
     */
    public List findRoleByType(Long roleType) {
        StringBuffer hql = new StringBuffer("from Role role");
        hql.append(" where role.").append(Role.PROP_ROLE_TYPE).append("=?");

        return this.query(hql.toString(), new Object[] { roleType });
    }

    /**
     * ���ݽ�ɫ�����ҽ�ɫ��ģ����ѯ
     * 
     * @param orgId
     *                ������֯Id
     * @param roleName
     *                ��ɫ��
     * @param roleType
     *                ��ɫ����
     * @return RoleDTO
     */
    public List findRoleByRoleName(String roleName, long roleType) {
        List<Long> param = new ArrayList<Long>();
        StringBuffer hql = new StringBuffer();
        hql.append(" select role from Role role ");
        hql.append(" where role.").append(Role.PROP_ROLE_TYPE).append("=?");
        param.add(new Long(roleType));
        if (StringUtils.isNotEmpty(roleName)) {
            hql.append(" and role.").append(Role.PROP_ROLE_NAME).append(
                    " like '%");
            hql.append(roleName).append("%'");
        }
        hql.append(" and role.").append(Role.PROP_EXPIRE_DATE).append(
                " is null");

        List roles = this.query(hql.toString(), param.toArray());
        return roles;
    }
    
    /**
     * ���ݽ�ɫ���Ʋ�ѯ��ɫ����ȷ��ѯ
     * @param roleName
     * @param roleType
     * @return
     */
    public Role findRoleByName(String roleName,long roleType){
        StringBuffer hql = new StringBuffer("from Role role");
        hql.append(" where role.").append(Role.PROP_ROLE_NAME).append("=?");
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        hql.append(" and role.").append(Role.PROP_EXPIRE_DATE).append(" is null");
        
        return (Role)this.getSingle(hql.toString(), new Object[]{roleName,new Long(roleType)});
    }

    /**
     * ������Ӧ��֯���ҽ�ɫ
     * 
     * @param orgId
     * @param roleType
     * @return Role����
     */
    public List findRoleByAdaptOrgId(Long orgId, long roleType) {
        List result = new ArrayList();
        if (orgId == null || orgId.longValue() == 0) {
            return result;
        }
        StringBuffer hql = new StringBuffer();
        hql.append(" select distinct role from Role role,RelRoleOrg rro ");
        hql.append(" where role.").append(Role.PROP_ROLE_ID);
        hql.append(" = ").append(" rro.").append(RelRoleOrg.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append(" = ?");
        hql.append(" and rro.").append(RelRoleOrg.PROP_ORG_ID).append("=?");
        hql.append(" and role.").append(Role.PROP_EXPIRE_DATE).append(
                " is null");

        result = this.query(hql.toString(), new Object[] { new Long(roleType),
                orgId });
        return result;
    }

}