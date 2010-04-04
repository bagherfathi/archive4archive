package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.RelAfficheOrg;

/**
 * Affiche ʵ�����ݷ�����
 * 
 * 
 * @spring.bean id="AfficheDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AfficheDao extends BaseDao {

    /**
     * ���캯��
     */
    public AfficheDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Affiche.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Affiche getById(Serializable key) {
        return (Affiche) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Affiche loadById(Serializable key) {
        return (Affiche) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ������ʵ�塣
     * 
     * @param afficheId
     *                ����ʵ��ID��
     */
    public void deleteAfficheById(Long afficheId) {
        StringBuffer hql = new StringBuffer("from Affiche affiche");
        hql.append(" where affiche.").append(Affiche.PROP_AFFICHE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { afficheId });
    }
    
    /**
     * ɾ������������֯��
     * @param afficheId  �����ʶ��
     */
    public void deleteRelAfficheOrgs(Long afficheId){
        StringBuffer hql = new StringBuffer("from RelAfficheOrg rao");
        hql.append(" where rao.").append(RelAfficheOrg.PROP_AFFICHE_ID).append("=?");
        this.deleteFromQuery(hql.toString(),new Object[]{afficheId});
    }
    
    /**
     * ��ѯ����������֯
     * @param afficheId   �����ʶ
     * @return
     */
    public List findRelAfficheOrgs(Long afficheId){
        StringBuffer hql = new StringBuffer("from RelAfficheOrg rao");
        hql.append(" where rao.").append(RelAfficheOrg.PROP_AFFICHE_ID).append("=?");
        
        return this.query(hql.toString(),new Object[]{afficheId});
    }
}