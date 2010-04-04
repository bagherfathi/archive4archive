
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
 * RelOperRole ʵ�����ݷ�����
 * 
 * @spring.bean id="RelOperRoleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperRoleDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelOperRoleDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelOperRole.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperRole getById(Serializable key) {
        return (RelOperRole) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperRole loadById(Serializable key) {
        return (RelOperRole) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ������Աӵ�еĽ�ɫ��
     * 
     * @param operatorId
     *            ����ԱID��
     * @param roleId
     *            ��ɫID��
     * @param orgId
     *            �ɷ�����֯ID��
     * @param icludeChildren 
     *            �Ƿ��������֯
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
     * ɾ������Ա�ڶ�Ӧ��֯�µ�Ȩ�ޡ�
     * 
     * @param operatorId
     *            ����ԱID��
     * @param orgId
     *            ��֯����ID��
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
     * ��ѯ����Ա����֯��ϵ��
     * 
     * @param operatorId
     *            ����ԱID��
     * @param roleId
     *            ��ɫID��
     * @param orgId
     *            ��֯����ID��
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
     * ��ѯ����Ա�󶨵Ľ�ɫ��
     * 
     * @param operatorId
     *            ����ԱID��
     * @param roleType
     *            ��ɫ���͡�
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
     * ���ݽ�ɫ��ʶ����֯��ʶ��ѯ�ڸ���֯�¸ý�ɫ������Ա��
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