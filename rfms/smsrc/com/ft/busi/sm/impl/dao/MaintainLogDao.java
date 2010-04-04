package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.MaintainLog;

/**
 * MaintainLog 实体数据访问类
 * 
 * @spring.bean id="MaintainLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class MaintainLogDao extends BaseDao {

    /**
     * 构造函数
     */
    public MaintainLogDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return MaintainLog.class;
    }

    /**
     * 根据ID查找对象
     */
    public MaintainLog getById(Serializable key) {
        return (MaintainLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public MaintainLog loadById(Serializable key) {
        return (MaintainLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据ID删除维护日志。
     * 
     * @param logId
     *                维护日志ID。
     */
    public void deleteLogById(Long logId) {
        StringBuffer hql = new StringBuffer("from MaintainLog log");
        hql.append(" where log.").append(MaintainLog.PROP_LOG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { logId });
    }
}