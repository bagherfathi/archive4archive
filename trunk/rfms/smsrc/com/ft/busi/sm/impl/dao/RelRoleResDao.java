
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelRoleRes;

/**
 * RelRoleRes ʵ�����ݷ�����
 * 
 * @spring.bean id="RelRoleResDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelRoleResDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelRoleResDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelRoleRes.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleRes getById(Serializable key) {
        return (RelRoleRes) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelRoleRes loadById(Serializable key) {
        return (RelRoleRes) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ����ɫ����Ĺ���Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @param long1
     *                ����Ȩ��ID��
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
     * ��ѯ��ɫӵ�еĹ���Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @return
     */
    public List findRelRoleResByRoleId(Long roleId) {
        StringBuffer hql = new StringBuffer("from RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        return this.query(hql.toString(), new Object[] { roleId });
    }
    
    /**
     *  ɾ����ɫ��������й���Ȩ�ޡ�
     * @param roleId    ��ɫID��
     */
    public void deleteRelRoleRes(Long roleId){
        StringBuffer hql = new StringBuffer("from RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{roleId});
    }
}