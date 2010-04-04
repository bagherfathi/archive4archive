package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.EnumGroup;

/**
 * EnumGroup 实体数据访问类
 * 
 * @spring.bean id="EnumGroupDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class EnumGroupDao extends BaseDao {

    /**
     * 构造函数
     */
    public EnumGroupDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return EnumGroup.class;
    }

    /**
     * 根据ID查找对象
     */
    public EnumGroup getById(Serializable key) {
        return (EnumGroup) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public EnumGroup loadById(Serializable key) {
        return (EnumGroup) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
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
     * 删除EnumDataGroup实体
     * 
     * @param enumGroupId
     *                系统数据组ID
     */
    public void deleteEnumDateGroup(Long enumGroupId) {
        StringBuffer hql = new StringBuffer("from EnumGroup enumGroup");
        hql.append(" where enumGroup.").append(EnumGroup.PROP_GROUP_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { enumGroupId });
    }
}