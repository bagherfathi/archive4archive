package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.RelAfficheOrg;

/**
 * Affiche 实体数据访问类
 * 
 * 
 * @spring.bean id="AfficheDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AfficheDao extends BaseDao {

    /**
     * 构造函数
     */
    public AfficheDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Affiche.class;
    }

    /**
     * 根据ID查找对象
     */
    public Affiche getById(Serializable key) {
        return (Affiche) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public Affiche loadById(Serializable key) {
        return (Affiche) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除公告实体。
     * 
     * @param afficheId
     *                公告实体ID。
     */
    public void deleteAfficheById(Long afficheId) {
        StringBuffer hql = new StringBuffer("from Affiche affiche");
        hql.append(" where affiche.").append(Affiche.PROP_AFFICHE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { afficheId });
    }
    
    /**
     * 删除公告适用组织。
     * @param afficheId  公告标识。
     */
    public void deleteRelAfficheOrgs(Long afficheId){
        StringBuffer hql = new StringBuffer("from RelAfficheOrg rao");
        hql.append(" where rao.").append(RelAfficheOrg.PROP_AFFICHE_ID).append("=?");
        this.deleteFromQuery(hql.toString(),new Object[]{afficheId});
    }
    
    /**
     * 查询公告适用组织
     * @param afficheId   公告标识
     * @return
     */
    public List findRelAfficheOrgs(Long afficheId){
        StringBuffer hql = new StringBuffer("from RelAfficheOrg rao");
        hql.append(" where rao.").append(RelAfficheOrg.PROP_AFFICHE_ID).append("=?");
        
        return this.query(hql.toString(),new Object[]{afficheId});
    }
}