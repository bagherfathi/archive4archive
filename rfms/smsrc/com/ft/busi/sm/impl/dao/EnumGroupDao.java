package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.EnumGroup;

/**
 * EnumGroup ʵ�����ݷ�����
 * 
 * @spring.bean id="EnumGroupDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumGroupDao extends BaseDao {

    /**
     * ���캯��
     */
    public EnumGroupDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return EnumGroup.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumGroup getById(Serializable key) {
        return (EnumGroup) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public EnumGroup loadById(Serializable key) {
        return (EnumGroup) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        StringBuffer hql = new StringBuffer("from EnumGroup enumGroup where enumGroup.").append(EnumGroup.PROP_EXPIRE_DATE).append(" is null");
        return this.query(hql.toString());
    }
    
    public java.util.List findAllOrderByName(){
        StringBuffer hql = new StringBuffer("from EnumGroup enumGroup where enumGroup.").append(EnumGroup.PROP_EXPIRE_DATE).append(" is null");
        hql.append(" order by enumGroup.").append(EnumGroup.PROP_GROUP_NAME);
        return this.query(hql.toString());
    }

    /**
     * ɾ��EnumDataGroupʵ��
     * 
     * @param enumGroupId
     *                ϵͳ������ID
     */
    public void deleteEnumDateGroup(Long enumGroupId) {
        StringBuffer hql = new StringBuffer("from EnumGroup enumGroup");
        hql.append(" where enumGroup.").append(EnumGroup.PROP_GROUP_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { enumGroupId });
    }
}