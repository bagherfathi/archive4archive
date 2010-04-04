package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.sm.impl.dao.ConsignPermitDao;
import com.ft.busi.sm.model.CommissionManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.PermitLogDTO;
import com.ft.sm.entity.ConsignPermit;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.PermitLog;
import com.ft.sm.entity.Resource;

/**
 * 权限委托管理实现类
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="commissionManager" parent="transactionProxyFactoryBean"
 *                  target="commissionManagerImpl"
 * 
 * @spring.bean id="commissionManagerImpl"
 */
public class CommissionManagerImpl implements CommissionManager {
    private ConsignPermitDao consignPermitDao;

    /**
     * @spring.property ref = "ConsignPermitDao"
     * @param consignPermitDao
     *            the consignPermitDao to set
     */
    public void setConsignPermitDao(ConsignPermitDao consignPermitDao) {
        this.consignPermitDao = consignPermitDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#updateCommissions(com.huashu.boss.sm.entity.ConsignPrivilege[],
     *      com.huashu.boss.sm.entity.ConsignPrivilege[])
     */
    @SuppressWarnings("unchecked")
	public void updateCommissions(List addCommission, List removedCommission) {
        List addConsignPermits = EntityDTOConverter
                .converDTO2Entity(addCommission);
        List removeConsignPermits = EntityDTOConverter
                .converDTO2Entity(removedCommission);

        List histories = this.createHistory(addConsignPermits,
                PermitLogDTO.OPERATION_TYPE_ADD);
        List removedHistories = this.createHistory(removeConsignPermits,
                PermitLogDTO.OPERATION_TYPE_REMOVE);

        histories.addAll(removedHistories);
        this.consignPermitDao.batchSave(histories);

        this.consignPermitDao.batchSave(addConsignPermits);
        this.consignPermitDao.batchDelete(removeConsignPermits);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#saveCommissions(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO, java.lang.Long, java.util.Date,
     *      java.util.Date, java.lang.Long[])
     */
    public void saveCommissions(OperatorDTO consigner, OperatorDTO consignee,
            Long orgId, Date validTime, Date expireTime, Long[] resourceIds)
            throws Exception {
        // 查找出已存在的委托记录，进行更新
        List commissions = this.consignPermitDao.findCommissions(consigner
                .getOperatorId(), consignee.getOperatorId(), orgId,
                ConsignPermitDTO.RESOURCE_TYPE);
        List updateCommissions = findUpdateCommission(consigner, consignee,
                orgId, commissions, resourceIds, validTime, expireTime);
        this.consignPermitDao.batchUpdate(updateCommissions);

        List addCommissions = this.findAddCommission(consigner, consignee,
                orgId, commissions, resourceIds, validTime, expireTime);
        this.consignPermitDao.batchSave(addCommissions);
    }

    /**
     * 创建委托记录
     * 
     * @param consigner
     * @param consignee
     * @param orgId
     * @param commissions
     * @param resourceIds
     * @param validTime
     * @param expireTime
     * @return
     */
    private List findAddCommission(OperatorDTO consigner,
            OperatorDTO consignee, Long orgId, List commissions,
            Long[] resourceIds, Date validTime, Date expireTime) {
        List<ConsignPermit> addCommissions = new ArrayList<ConsignPermit>();

        for (int i = 0; i < resourceIds.length; i++) {
            Long resourceId = resourceIds[i];
            boolean flag = false;
            for (Iterator iterator = commissions.iterator(); iterator.hasNext();) {
                ConsignPermit object = (ConsignPermit) iterator.next();
                if (object.getResourceId() == resourceId.longValue()) {
                    flag = true;
                    break;
                }
            }

            // 新建委托记录
            if (!flag) {
                ConsignPermit newConsign = new ConsignPermit();
                newConsign
                        .setConsignerId(consigner.getOperatorId().longValue());
                newConsign.setConsignerOrgId(consigner.getOrg().getOrgId());
                newConsign
                        .setConsigneeId(consignee.getOperatorId().longValue());
                newConsign.setConsigneeOrgId(consignee.getOrg().getOrgId());
                newConsign.setOrgId(orgId.longValue());
                newConsign.setExpireTime(expireTime);
                newConsign.setValidTime(validTime);
                newConsign.setResourceId(resourceId.longValue());
                newConsign.setResourceType(ConsignPermitDTO.RESOURCE_TYPE);
                newConsign.setConsignTime(new Date());
                addCommissions.add(newConsign);
            }
        }

        return addCommissions;
    }

    /**
     * 查找需要更新的委托记录。
     * 
     * @param consigner
     * @param consignee
     * @param commissions
     * @param orgId
     * @param resourceIds
     * @return
     */
    private List findUpdateCommission(OperatorDTO consigner,
            OperatorDTO consignee, Long orgId, List commissions,
            Long[] resourceIds, Date validTime, Date expireTime) {
        List<ConsignPermit> updateCommissions = new ArrayList<ConsignPermit>();
        for (Iterator iterator = commissions.iterator(); iterator.hasNext();) {
            ConsignPermit object = (ConsignPermit) iterator.next();
            boolean flag = false;
            for (int i = 0; i < resourceIds.length; i++) {
                if (resourceIds[i].longValue() == object.getResourceId()) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                object.setExpireTime(expireTime);
                object.setValidTime(validTime);
                updateCommissions.add(object);
            }
        }

        return updateCommissions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#findCommissions(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    public List findCommissions(Long consignerId, Long consigneeId, Long orgId,
            int type) {
        List entityList = this.consignPermitDao.findCommissions(consignerId,
                consigneeId, orgId, type);

        return EntityDTOConverter.converConsignPermit2DTO(entityList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#findValidResource(java.lang.Long,
     *      java.lang.Long, java.util.Date)
     */
    public List findValidResources(Long consigneeId, Long orgId, Date validTime) {
        List entityList = this.consignPermitDao.findValidResources(consigneeId,
                orgId, validTime, ConsignPermitDTO.RESOURCE_TYPE);

        return EntityDTOConverter.converResource2DTO(entityList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#findCommissions(java.lang.Long,
     *      java.lang.String, java.lang.String, java.util.Date, java.util.Date)
     */
    public List findCommissions(Long consignerId, String consigneeName,
            String resourceName, Date startTime, Date endTime) {
        return this.queryCommissions(consignerId, consigneeName, resourceName,
                startTime, endTime);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#removeCommission(com.huashu.boss.sm.entity.ConsignPrivilege)
     */
    public void removeCommission(java.io.Serializable[] commissionIds) {
        List deletedList = this.consignPermitDao
                .findConsignPrivilegesByIds(commissionIds);
        this.consignPermitDao.batchDelete(deletedList);

        List removedHistories = this.createHistory(deletedList,
                PermitLogDTO.OPERATION_TYPE_REMOVE);
        this.consignPermitDao.batchSave(removedHistories);
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.CommissionManager#removeExpiredCommission()
     */
    public void removeExpiredCommission(){
        this.consignPermitDao.removeExpiredConsignPermit();
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.CommissionManager#removeCommission(java.lang.Long, java.lang.Long)
     */
    public void removeCommission(Long consignerId , Long consigneeId) {
        List deletedList = this.consignPermitDao.findConsignPermit(consignerId, consigneeId);
        this.consignPermitDao.batchDelete(deletedList);
        List removedHistories = this.createHistory(deletedList,
                PermitLogDTO.OPERATION_TYPE_REMOVE);
        this.consignPermitDao.batchSave(removedHistories);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.CommissionManager#updateCommission(com.huashu.boss.sm.entity.ConsignPrivilege)
     */
    public void updateCommission(ConsignPermitDTO cp) {
        if (null != cp) {
            ConsignPermit editOb = this.consignPermitDao.getById(new Long(cp
                    .getConsignId()));
            if (null != cp.getValidTime() && null != cp.getExpireTime()) {
                // 生效时间和失效时间都进行修改则执行
                editOb.setValidTime(cp.getValidTime());
                editOb.setExpireTime(cp.getExpireTime());
                this.consignPermitDao.update(editOb);
            } else if (null == cp.getValidTime() && null != cp.getExpireTime()) {
                // 失效时间修改了则执行
                editOb.setExpireTime(cp.getExpireTime());
                this.consignPermitDao.update(editOb);
            }
        }
    }

    /**
     * 生成授权记录实体。
     * 
     * @param consignPermitDTOs
     *            委托权限DTO列表。
     * @param operation
     *            操作类型。
     * @return 授权记录PermitLog实体。
     */
    private List createHistory(List consignPermitDTOs, long operation) {
        List<PermitLog> result = new ArrayList<PermitLog>();
        for (Iterator iter = consignPermitDTOs.iterator(); iter.hasNext();) {
            ConsignPermit consignPermit = (ConsignPermit) iter.next();
            PermitLogDTO history = new PermitLogDTO();
            history.setRecordTime(new Date());
            history.setGrantorId(consignPermit.getConsignerId());
            history.setGrantorOrgId(consignPermit.getConsignerOrgId());
            history.setGranteeId(new Long(consignPermit.getConsigneeId()));
            history.setGranteeType(PermitLogDTO.GRANTEE_TYPE_OP);
            history.setOrgId(consignPermit.getOrgId());
            history.setOpType(operation);
            history.setPermitType(consignPermit.getResourceType());
            history.setResourceId(consignPermit.getResourceId());

            result.add(history.getPermitLog());
        }

        return result;
    }

   /**
    * 查询委托记录
     * 
     * @param consignerId
     *            委托人ID
     * @param consigneeId
     *            委托对象ID
    */
    public List findCommissions(Long consignerId, Long consigneeId) {
        StringBuffer query = new StringBuffer(
                "select new com.huashu.boss.sm.dto.ConsignPermitDTO(");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_ID).append(",");
        query.append("op.").append(Operator.PROP_OP_NAME).append(",");
        query.append("resource.").append(Resource.PROP_RESOURCE_TITLE).append(
                ",");
        query.append("resource.").append(Resource.PROP_RESOURCE_PATH).append(
                ",");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_VALID_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_EXPIRE_TIME).append(",");
        query.append("org.").append(Organization.PROP_ORG_NAME);
        query.append(")");

        query.append(" from ConsignPermit cp,Operator op,Resource resource,Organization org");
        query.append(" where cp.").append(ConsignPermit.PROP_CONSIGNER_ID)
                .append("=?");
        query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("= ?");
        query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNEE_ID)
        .append("= op.").append(Operator.PROP_OPERATOR_ID);
        query.append(" and cp.").append(ConsignPermit.PROP_RESOURCE_ID).append(
                "=").append("resource.").append(Resource.PROP_RESOURCE_ID);
        query.append(" and cp.").append(ConsignPermit.PROP_ORG_ID).append(
        "=").append("org.").append(Organization.PROP_ORG_ID);

        List result = this.consignPermitDao.query(query.toString(),
                new Object[] { consignerId, consigneeId });

        return result;

    }

    /**
     * 查询委托记录
     * 
     * @param consignerId
     *            委托人ID
     * @param consigneeName
     *            委托对象名称
     * @param resourceName
     *            权限标题
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 委托权限记录列表
     */
    private List queryCommissions(Long consignerId, String consigneeName,
            String resourceName, Date startTime, Date endTime) {
        StringBuffer query = new StringBuffer(
                "select new com.huashu.boss.sm.dto.ConsignPermitDTO(");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_ID).append(",");
        query.append("op.").append(Operator.PROP_OP_NAME).append(",");
        query.append("resource.").append(Resource.PROP_RESOURCE_TITLE).append(
                ",");
        query.append("resource.").append(Resource.PROP_RESOURCE_PATH).append(
                ",");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_VALID_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_EXPIRE_TIME);
        query.append(")");

        query.append(" from ConsignPermit cp,Operator op,Resource resource");
        query.append(" where cp.").append(ConsignPermit.PROP_CONSIGNER_ID)
                .append("=?");
        query.append(" and cp.").append(ConsignPermit.PROP_CONSIGNEE_ID)
                .append("=").append("op.").append(Operator.PROP_OPERATOR_ID);
        query.append(" and cp.").append(ConsignPermit.PROP_RESOURCE_TYPE)
                .append("=").append(ConsignPermitDTO.RESOURCE_TYPE);
        query.append(" and cp.").append(ConsignPermit.PROP_RESOURCE_ID).append(
                "=").append("resource.").append(Resource.PROP_RESOURCE_ID);

        List<Object> params = new ArrayList<Object>();
        params.add(consignerId);

        if (consigneeName != null && consigneeName.length() > 0) {
            query.append(" and op.").append(Operator.PROP_OP_NAME).append(
                    " like ?");
            params.add("%" + consigneeName + "%");
        }

        if (resourceName != null && resourceName.length() > 0) {
            query.append(" and resource.").append(Resource.PROP_RESOURCE_TITLE)
                    .append(" like ?");
            params.add("%" + resourceName + "%");
        }

        if (startTime != null) {
            query.append(" and cp.").append(ConsignPermit.PROP_CONSIGN_TIME)
                    .append(">= ?");
            params.add(startTime);
        }

        if (endTime != null) {
            query.append(" and cp.").append(ConsignPermit.PROP_CONSIGN_TIME)
                    .append("<= ?");
            params.add(endTime);
        }

        query.append(" order by cp.").append(ConsignPermit.PROP_CONSIGN_TIME);

        List result = this.consignPermitDao.query(query.toString(), params
                .toArray(new Object[0]));

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        StringBuffer query = new StringBuffer(
                "select new com.huashu.boss.sm.dto.ConsignPermitDTO(");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_ID).append(",");
        query.append("op.").append(Operator.PROP_OP_NAME).append(",");
        query.append("resource.").append(Resource.PROP_RESOURCE_TITLE).append(
                ",");
        query.append("resource.").append(Resource.PROP_RESOURCE_PATH).append(
                ",");
        query.append("cp.").append(ConsignPermit.PROP_CONSIGN_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_VALID_TIME).append(",");
        query.append("cp.").append(ConsignPermit.PROP_EXPIRE_TIME);
        query.append(")");

        query.append(" from ConsignPermit cp,Operator op,Resource resource");
        query.append(" where cp.").append(ConsignPermit.PROP_CONSIGN_ID)
                .append("=?");
        query.append(" and op.").append(Operator.PROP_OPERATOR_ID);
        query.append(" = cp.").append(ConsignPermit.PROP_CONSIGNEE_ID);
        query.append(" and resource.").append(Resource.PROP_RESOURCE_ID);
        query.append(" = cp.").append(ConsignPermit.PROP_RESOURCE_ID);

        List result = this.consignPermitDao.query(query.toString(),
                new Object[] { id });

        return result.size() > 0 ? result.get(0) : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.consignPermitDao.loadByIds(ConsignPermit.class, ids);

        return EntityDTOConverter.converConsignPermit2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {

        List result = this.consignPermitDao.query(hql, params, page);
        return EntityDTOConverter.converConsignPermit2DTO(result);
    }

}
