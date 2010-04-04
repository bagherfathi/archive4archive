package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;

/**
 * Ȩ��ί�й���ӿڡ�
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/commissionManager" id="commissionManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface CommissionManager extends EntityManager {
    /**
     * ����ί�м�¼��
     * 
     * @param addCommission
     *            ���ӵ�ί�м�¼��
     * @param removedCommission
     *            ɾ����ί�м�¼��
     */
    public void updateCommissions(List addCommission, List removedCommission)
            throws Exception;

    /**
     * ����ί�м�¼
     * 
     * @param consigner
     *            ί����
     * @param consignee
     *            ��ί����
     * @param orgId
     *            ί�пɷ�����֯
     * @param validTime
     *            ��Чʱ��
     * @param expireTime
     *            ʧЧʱ��
     * @param resourceIds
     *            ί�еĹ���Ȩ��ID
     */
    public void saveCommissions(OperatorDTO consigner, OperatorDTO consignee,
            Long orgId, Date validTime, Date expireTime, Long[] resourceIds)
            throws Exception;

    /**
     * ��ѯί�м�¼��
     * 
     * @param consignerId
     *            ί����ID��
     * @param consigneeId
     *            ί�ж���ID����Ϊ��ʱ��ѯ����ί�ж���ļ�¼��
     * @param orgId
     *            ������֯ID����Ϊ��ʱ��ѯ���з�����֯��ί�м�¼��
     * @param type
     *            Ȩ�����͡�
     * @return ConsignPermitDTO�б�
     */
    public List findCommissions(Long consignerId, Long consigneeId, Long orgId,
            int type) throws Exception;

    /**
     * ��ѯ����Ա��ί�е���Ч�Ŀɷ��ʹ���Ȩ�ޡ�
     * 
     * @param consigneeId
     *            ί�ж����ʶ��
     * @param orgId
     *            ������֯ID��
     * @param validTime
     *            ��ѯʱ�䡣
     * @return Resourceʵ���б�
     */
    public List findValidResources(Long consigneeId, Long orgId, Date validTime)
            throws Exception;

    /**
     * ����������ѯ����Աί�м�¼��
     * 
     * @param consignerId
     *            ί����ID��
     * @param consigneeId
     *            ί�ж���Id��
     */
    public List findCommissions(Long consigneId, Long consigneeId)
            throws Exception;

    /**
     * ����������ѯ����Աί�м�¼��
     * 
     * @param consignerId
     *            ί����ID��
     * @param consigneeName
     *            ί�ж������ơ�
     * @param resourceName
     *            ί��Ȩ�����ơ�
     * @param startTime
     *            ��ʼʱ�䡣
     * @param endTime
     *            ����ʱ�䡣
     * @return
     */
    public List findCommissions(Long consignerId, String consigneeName,
            String resourceName, Date startTime, Date endTime) throws Exception;

    /**
     * ����ί�е�Ȩ�ޡ�
     * 
     * @param commissionIds
     *            ί��Ȩ�޵�ID���顣
     */
    public void removeCommission(java.io.Serializable[] commissionIds)
            throws Exception;
   
    /**
     * ��ȥ�ѹ��ڵ�ί��Ȩ�ޡ�
     * @throws Exception
     */
    public void removeExpiredCommission() throws Exception; 

    /**
     * 
     * @param consignerId ί����ID
     * @param consigneeId ί�ж���ID
     * @throws Exception
     */
    public void removeCommission(Long consignerId, Long consigneeId)
            throws Exception;

    /**
     * ����ί����Ϣ��
     * 
     * @param cp
     *            ί��Ȩ��ʵ�塣
     */
    public void updateCommission(ConsignPermitDTO cp) throws Exception;
}
