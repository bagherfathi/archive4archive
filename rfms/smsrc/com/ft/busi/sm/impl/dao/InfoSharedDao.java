package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.InfoShared;

/**
 * InfoShared 实体数据访问类
 * 
 * @spring.bean id="InfoSharedDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class InfoSharedDao extends BaseDao {

    /**
     * 构造函数
     */
    public InfoSharedDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return InfoShared.class;
    }

    /**
     * 根据ID查找对象
     */
    public InfoShared getById(Serializable key) {
        return (InfoShared) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public InfoShared loadById(Serializable key) {
        return (InfoShared) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除共享信息。
     * 
     * @param infoId
     *                共享信息ID。
     */
    public void deleteInfoById(Long infoId) {
        StringBuffer hql = new StringBuffer("from InfoShared info");
        hql.append(" where info.").append(InfoShared.PROP_INFO_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { infoId });

    }

    /**
     * 查询信息分类中信息总数。
     * 
     * @param categoryId
     * @return
     */
    public Integer findInfoNumberOfCategory(Long categoryId) {
        StringBuffer hql = new StringBuffer("select count(info.infoId)");
        hql.append(" from InfoShared info");
        hql.append(" where info.").append(InfoShared.PROP_CATEGORY_ID).append(
                "=?");

        List result = this.query(hql.toString(), new Object[] { categoryId });

        return result.size() > 0 ? (Integer) result.get(0) : new Integer(0);
    }
}