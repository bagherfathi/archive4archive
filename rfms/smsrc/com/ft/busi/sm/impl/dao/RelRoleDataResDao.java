
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelRoleDataRes;

/**
 * RelRoleDataRes ʵ�����ݷ�����
 * 
 * @spring.bean id="RelRoleDataResDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleDataResDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelRoleDataResDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelRoleDataRes.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleDataRes getById(Serializable key) {
        return (RelRoleDataRes) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleDataRes loadById(Serializable key) {
        return (RelRoleDataRes) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ����ɫӵ�е�ҵ��Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @return
     */
    public List findRelRoleDataResByRoleId(Long roleId) {
        StringBuffer hql = new StringBuffer("from RelRoleDataRes rrd");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=?");
        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * ɾ����ɫӵ�е�ҵ��Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @param entryId
     *                ҵ��Ȩ����ĿID��
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