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
 * ConsignPermit ʵ�����ݷ�����
 * 
 * @spring.bean id="ConsignPermitDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ConsignPermitDao extends BaseDao {

    /**
     * ���캯��
     */
    public ConsignPermitDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return ConsignPermit.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public ConsignPermit getById(Serializable key) {
        return (ConsignPermit) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public ConsignPermit loadById(Serializable key) {
        return (ConsignPermit) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯί�м�¼
     * 
     * @param consignerId
     *            ί����ID
     * @param consigneeId
     *            ί�ж���ID����Ϊ��ʱ��ѯ����ί�ж���ļ�¼
     * @param orgId
     *            ������֯ID����Ϊ��ʱ��ѯ���з�����֯��ί�м�¼
     * @param type
     *            Ȩ������
     * @return ConsignPrivilegeʵ���б�
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
     * ��ѯ����Ա��ί�е���Ч�Ŀɷ��ʹ���Ȩ��
     * 
     * @param opId
     *            ����ԱID
     * @param orgId
     *            ��֯ID
     * @return ����Ȩ��ʵ���б�
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
     * ����ί��Ȩ�޵�ID��ѯί��Ȩ��
     * 
     * @param ids
     *            ί��Ȩ��ID����
     * @return
     */
    public List findConsignPrivilegesByIds(java.io.Serializable[] ids) {
        return this.loadByIds(ConsignPermit.class, ids);
    }

    /**
     * ����ί���ߵ�ID��ί�ж����Id��ѯί��Ȩ��
     * 
     * @param ids
     *            ί��Ȩ��ID����
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
     * ɾ���ѹ��ڵ�ί�м�¼��
     */
    public void removeExpiredConsignPermit(){
        StringBuffer hql = new StringBuffer("from ConsignPermit cp");
        hql.append(" where cp.").append(ConsignPermit.PROP_EXPIRE_TIME).append("< ?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{new Date()});
    }

    /**
     * ��ѯ����Ա��ί�е���Ч�Ŀɷ��ʹ���Ȩ��
     * 
     * @param opId
     *            ����ԱID
     * @param orgId
     *            ��֯ID
     * @return ����Ȩ��ʵ���б�
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
     * ��ѯ����Ա��ί�е���Ч�Ŀɷ��ʹ���Ȩ��
     * 
     * @param opId
     *            ����ԱID
     * @param orgId
     *            ��֯ID
     * @return ����Ȩ��ʵ���б�
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