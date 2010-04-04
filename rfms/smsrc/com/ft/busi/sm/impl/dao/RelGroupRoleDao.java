package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelGroupRole;

/**
 * RelGroupRole ʵ�����ݷ�����
 * 
 * @spring.bean id="RelGroupRoleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelGroupRoleDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelGroupRoleDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelGroupRole.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelGroupRole getById(Serializable key) {
        return (RelGroupRole) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelGroupRole loadById(Serializable key) {
        return (RelGroupRole) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ������Ա�����Ľ�ɫ��
     * 
     * @param groupId
     *                ����Ա��ID��
     * @param orgId
     *                �ɷ�����֯ID��
     * @param roleId
     *                ��ɫID��
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
     * ɾ������Ա������ɫ��
     * @param groupId   ����Ա��Id
     * @param orgId     ����Ա��ɷ�����֯Id
     */
    public void deleteRelGroupRole(Long groupId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelGroupRole rgr");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { groupId, orgId});
    }
}