package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;

/**
 * 权限委托管理接口。
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/commissionManager" id="commissionManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface CommissionManager extends EntityManager {
    /**
     * 更新委托记录。
     * 
     * @param addCommission
     *            增加的委托记录。
     * @param removedCommission
     *            删除的委托记录。
     */
    public void updateCommissions(List addCommission, List removedCommission)
            throws Exception;

    /**
     * 保存委托记录
     * 
     * @param consigner
     *            委托人
     * @param consignee
     *            被委托人
     * @param orgId
     *            委托可访问组织
     * @param validTime
     *            生效时间
     * @param expireTime
     *            失效时间
     * @param resourceIds
     *            委托的功能权限ID
     */
    public void saveCommissions(OperatorDTO consigner, OperatorDTO consignee,
            Long orgId, Date validTime, Date expireTime, Long[] resourceIds)
            throws Exception;

    /**
     * 查询委托记录。
     * 
     * @param consignerId
     *            委托人ID。
     * @param consigneeId
     *            委托对象ID，若为空时查询所有委托对象的记录。
     * @param orgId
     *            访问组织ID，若为空时查询所有访问组织的委托记录。
     * @param type
     *            权限类型。
     * @return ConsignPermitDTO列表。
     */
    public List findCommissions(Long consignerId, Long consigneeId, Long orgId,
            int type) throws Exception;

    /**
     * 查询操作员被委托的有效的可访问功能权限。
     * 
     * @param consigneeId
     *            委托对象标识。
     * @param orgId
     *            访问组织ID。
     * @param validTime
     *            查询时间。
     * @return Resource实体列表。
     */
    public List findValidResources(Long consigneeId, Long orgId, Date validTime)
            throws Exception;

    /**
     * 按照条件查询操作员委托记录。
     * 
     * @param consignerId
     *            委托人ID。
     * @param consigneeId
     *            委托对象Id。
     */
    public List findCommissions(Long consigneId, Long consigneeId)
            throws Exception;

    /**
     * 按照条件查询操作员委托记录。
     * 
     * @param consignerId
     *            委托人ID。
     * @param consigneeName
     *            委托对象名称。
     * @param resourceName
     *            委托权限名称。
     * @param startTime
     *            开始时间。
     * @param endTime
     *            结束时间。
     * @return
     */
    public List findCommissions(Long consignerId, String consigneeName,
            String resourceName, Date startTime, Date endTime) throws Exception;

    /**
     * 回收委托的权限。
     * 
     * @param commissionIds
     *            委托权限的ID数组。
     */
    public void removeCommission(java.io.Serializable[] commissionIds)
            throws Exception;
   
    /**
     * 回去已过期的委托权限。
     * @throws Exception
     */
    public void removeExpiredCommission() throws Exception; 

    /**
     * 
     * @param consignerId 委托者ID
     * @param consigneeId 委托对象ID
     * @throws Exception
     */
    public void removeCommission(Long consignerId, Long consigneeId)
            throws Exception;

    /**
     * 更新委托信息。
     * 
     * @param cp
     *            委托权限实体。
     */
    public void updateCommission(ConsignPermitDTO cp) throws Exception;
}
