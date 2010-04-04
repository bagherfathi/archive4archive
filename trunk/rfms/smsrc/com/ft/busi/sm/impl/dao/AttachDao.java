package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Attach;

/**
 * Attach 实体数据访问类
 * 
 * @spring.bean id="AttachDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AttachDao extends BaseDao {

    /**
     * 构造函数
     */
    public AttachDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Attach.class;
    }

    /**
     * 根据ID查找对象
     */
    public Attach getById(Serializable key) {
        return (Attach) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public Attach loadById(Serializable key) {
        return (Attach) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询共享信息的附件信息。
     * 
     * @param infoId
     *                共享信息ID。
     * @return
     */
    public List findAttachsByInfoId(Long infoId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_INFO_ID).append("=?");

        return this.query(hql.toString(), new Object[] { infoId });
    }

    /**
     * 删除附件信息。
     * 
     * @param attachId
     *                附件信息ID。
     */
    public void deleteAttachById(Long attachId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_ATTACH_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { attachId });
    }

    /**
     * 删除共享信息的附件信息。
     * 
     * @param infoId
     */
    public void deleteAttachByInfoId(Long infoId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_INFO_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { infoId });

    }
}