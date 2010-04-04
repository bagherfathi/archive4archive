
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelRoleOrg;

/**
 * RelRoleOrg ʵ�����ݷ�����
 * 
 * @spring.bean id="RelRoleOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleOrgDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelRoleOrgDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelRoleOrg.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleOrg getById(Serializable key) {
        return (RelRoleOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleOrg loadById(Serializable key) {
        return (RelRoleOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ���ҽ�ɫ����Ӧ��֯
     * 
     * @param roleId
     * @return ��֯ʵ���б�
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
     * ɾ����ɫ����Ӧ��֯
     * 
     * @param roleId
     * @param orgId
     *            ����Ϊ��
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