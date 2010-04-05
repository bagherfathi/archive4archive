/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;
import java.util.Map;

import com.ft.busi.dto.AppRequest;
import com.ft.common.busi.BaseService;
import com.ft.rfms.entity.RfmsCommisionStep;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantAudit;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPayment;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.sm.entity.Operator;

/**
 * @author soler
 * 
 */
public interface MerchantService extends BaseService {

	/**
	 * �����̻���ʶ��ȡ�ŵ�
	 * 
	 * @param merchantId
	 * @return
	 * @throws Exception
	 */
	public List findBranchByMerchantId(Long merchantId) throws Exception;

	/**
	 * ������߸����̻����ŵ�
	 * 
	 * @param merchant
	 * @param branchs
	 * @param nextOperatorIds
	 * @param appRequest
	 * @throws Exception
	 */
	public void saveOrUpdateMerchant(RfmsMerchant merchant,
			List<RfmsMerchantBranch> branchs,
			Map<String, RfmsCommisionStep> steps, Long[] nextOperatorIds,
			AppRequest appRequest) throws Exception;

	/**
	 * �������״̬��ȡ��ɫ��ʶ
	 * 
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public Long[] findRoleIdsByAuditStatus(Long auditStatus) throws Exception;

	/**
	 * ��ȡ�²���������
	 * 
	 * @param curStatus
	 * @return
	 * @throws Exception
	 */
	public List findNextCtrlByStatusBefore(Long curStatus) throws Exception;

	/**
	 * ��ȡ�²������Ľ�ɫ
	 * 
	 * @param curStatus
	 * @return
	 * @throws Exception
	 */
	public List findNextCtrlRoleIdsByStatusBefore(Long curStatus)
			throws Exception;

	/**
	 * �����ŵ��ȡpos��Ϣ
	 * 
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List findPosByBranchId(Long branchId) throws Exception;

	public List findCommisionStepByMerchantId(Long merchantId) throws Exception;

	/**
	 * ����̻�
	 * 
	 * @param merchantId
	 * @param merchantAudit
	 */
	public void saveAuditMerchant(RfmsMerchant merchant,
			RfmsMerchantAudit merchantAudit, List<RfmsMerchantPos> pos,
			AppRequest appRequest) throws Exception;

	/**
	 * ��ȡPOS���
	 * 
	 * @param merchantId
	 * @return
	 * @throws Exception
	 */
	public String getMerchantPosSysCode(Long branchId) throws Exception;

	public String getTicketSysCode(String ticketSysCode) throws Exception;

	public List findMerchantsByOperatorAndAuditStatus(Long operatorId,
			Long auditStatus) throws Exception;

	/**
	 * ��ȡ��һ������Ա
	 * 
	 * @param merchantId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public Operator findOperatorByMerchantIdAndAuditStatus(Long merchantId,
			Long auditStatus) throws Exception;

	/**
	 * ���̻���ֵ
	 * 
	 * @param merchantId
	 * @param payment
	 * @param appRquest
	 * @throws Exception
	 */
	public void savePayment(Long merchantId, RfmsMerchantPayment payment,
			AppRequest appRquest) throws Exception;
}
