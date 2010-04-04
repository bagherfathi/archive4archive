package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.entity.ConsignPermit;
import com.ft.sm.entity.Resource;

/**
 * ConsignPermit 实体数据访问类
 * 
 * @spring.bean id="ConsignPermitDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ConsignPermitDao extends BaseDao {

    /**
     * 构造函数
     */
    public ConsignPermitDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return ConsignPermit.class;
    }

    /**
     * 根据ID查找对象
     */
    public ConsignPermit getById(Serializable key) {
        return (ConsignPermit) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public ConsignPermit loadById(Serializable key) {
        return (ConsignPermit) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询委托记录
     * 
     * @param consignerId
     *            委托人ID
     * @param consigneeId
     *            委托对象ID，若为空时查询所有委托对象的记录
     * @param orgId
     *            访问组织ID，若为空时查询所有访问组织的委托记录
     * @param type
     *            权限类型
     * @return ConsignPrivilege实体列表
     */
    public List findCommissions(Long consignerId, Long consigneeId, Long orgId,
            int type) {
        List<Object> params = new ArrayList<Object>();

        StringBuffer query = new StringBuffer("from ConsignPermit cp where 1=1");

        if (consignerId != null) {
            query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNER_ID)
                    .append("= ?");
            params.add(consignerId);
        }

        if (consigneeId != null) {
            query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                    .append("= ?");
            params.add(consigneeId);
        }

        if (orgId != null) {
            query.append(" and cp.").append(ConsignPermit.PROP_ORG_ID).append(
                    "= ?");
            params.add(orgId);
        }

        query.append(" and cp.").append(ConsignPermit.PROP_RESOURCE_TYPE)
                .append("= ?");
        params.add(new Long(type));

        return this.query(query.toString(), params.toArray(new Object[0]));
    }

    /**
     * 查询操作员被委托的有效的可访问功能权限
     * 
     * @param opId
     *            操作员ID
     * @param orgId
     *            组织ID
     * @return 功能权限实体列表
     */
    public List findValidResources(Long opId, Long orgId, Date time,
            long resourceType) {
        StringBuffer query = new StringBuffer(
                "select resource from Resource resource,ConsignPermit consign where 1=1");
        query.append(" and resource.").append(Resource.PROP_RESOURCE_ID)
                .append("= consign.").append(ConsignPermit.PROP_RESOURCE_ID);
        query.append(" and consign.").append(ConsignPermit.PROP_RESOURCE_TYPE)
                .append("= ?");
        query.append(" and consign.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("= ?");
        query.append(" and consign.").append(ConsignPermit.PROP_ORG_ID).append(
                "= ?");
        query.append(" and consign.").append(ConsignPermit.PROP_VALID_TIME)
                .append("<= ?");
        query.append(" and consign.").append(ConsignPermit.PROP_EXPIRE_TIME)
                .append(">= ?");

        Object[] params = new Object[] { new Long(resourceType), opId, orgId,
                time, time };

        return this.query(query.toString(), params);
    }

    /**
     * 根据委托权限的ID查询委托权限
     * 
     * @param ids
     *            委托权限ID数组
     * @return
     */
    public List findConsignPrivilegesByIds(java.io.Serializable[] ids) {
        return this.loadByIds(ConsignPermit.class, ids);
    }

    /**
     * 根据委托者的ID和委托对象的Id查询委托权限
     * 
     * @param ids
     *            委托权限ID数组
     * @return
     */
    public List findConsignPermit(Long consignerId, Long consigneeId) {
        StringBuffer query = new StringBuffer("from ConsignPermit cp ");
        query.append(" where cp.").append(ConsignPermit.PROP_CONSIGNER_ID)
                .append("= ?");
        query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("= ?");
        return this.query(query.toString(),new Object[]{consignerId,consigneeId});
    }
    
    /**
     * 删除已过期的委托记录。
     */
    public void removeExpiredConsignPermit(){
        StringBuffer hql = new StringBuffer("from ConsignPermit cp");
        hql.append(" where cp.").append(ConsignPermit.PROP_EXPIRE_TIME).append("< ?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{new Date()});
    }

    /**
     * 查询操作员被委托的有效的可访问功能权限
     * 
     * @param opId
     *            操作员ID
     * @param orgId
     *            组织ID
     * @return 功能权限实体列表
     */
    public List findValidResources(Long opId, Long orgId, Date time) {
        StringBuffer hql = new StringBuffer("select res");
        hql.append(" from Resource res,ConsignPermit consign");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID).append(
                "=consign.").append(ConsignPermit.PROP_RESOURCE_ID);
        hql.append(" and consign.").append(ConsignPermit.PROP_RESOURCE_TYPE)
                .append("=?");
        hql.append(" and consign.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("=?");
        hql.append(" and consign.").append(ConsignPermit.PROP_ORG_ID).append(
                "=?");
        hql.append(" and consign.").append(ConsignPermit.PROP_VALID_TIME)
                .append("<=?");
        hql.append(" and consign.").append(ConsignPermit.PROP_EXPIRE_TIME)
                .append(">=?");

        Object[] params = new Object[] {
                new Long(ConsignPermitDTO.RESOURCE_TYPE), opId, orgId, time,
                time };
        return this.query(hql.toString(), params);
    }
    
    /**
     * 查询操作员被委托的有效的可访问功能权限
     * 
     * @param opId
     *            操作员ID
     * @param orgId
     *            组织ID
     * @return 功能权限实体列表
     */
    public List findValidResources(Long opId, Long[] orgIds, Date time) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select res");
        hql.append(" from Resource res,ConsignPermit consign");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID).append(
                "=consign.").append(ConsignPermit.PROP_RESOURCE_ID);
        hql.append(" and consign.").append(ConsignPermit.PROP_RESOURCE_TYPE)
                .append("=?");
        params.add(new Long(ConsignPermitDTO.RESOURCE_TYPE));
        hql.append(" and consign.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("=?");
        params.add(opId);
        hql.append(" and consign.").append(ConsignPermit.PROP_VALID_TIME)
                .append("<=?");
        params.add(time);
        hql.append(" and consign.").append(ConsignPermit.PROP_EXPIRE_TIME)
                .append(">=?");
        params.add(time);
        
        hql.append(" and consign.").append(ConsignPermit.PROP_ORG_ID).append(
        " in(-1");
        
        for (int i = 0; i < orgIds.length; i++) {
            hql.append(",?");
            params.add(orgIds[i]);
        }
        hql.append(")");
        
        return this.query(hql.toString(), params.toArray(new Object[0]));
    }
}