package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOperGroup;

/**
 * RelOperGroup ʵ�����ݷ�����
 * 
 * @spring.bean id="RelOperGroupDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperGroupDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelOperGroupDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelOperGroup.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperGroup getById(Serializable key) {
        return (RelOperGroup) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperGroup loadById(Serializable key) {
        return (RelOperGroup) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ������Ա�Ĳ���Ա�顣
     * 
     * @param operatorId
     *                ����ԱID��
     * @param groupId
     *                ����Ա��ID��
     */
    public void deleteRelOperGroup(Long operatorId, Long groupId) {
        StringBuffer hql = new StringBuffer("from RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append("=?");

        this.deleteFromQuery(hql.toString(),
                new Object[] { operatorId, groupId });

    }
    /**
     * ��ѯ����Ա����Ĺ�ϵ
     * @param operatorId
     * @param groupId
     */

	public RelOperGroup findRelOperGroup(Long operatorId, Long groupId) {
		StringBuffer hql = new StringBuffer("from RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append("=?");
        
        return (RelOperGroup) this.getSingle(hql.toString(), new Object[]{operatorId,groupId});
		
	}
}