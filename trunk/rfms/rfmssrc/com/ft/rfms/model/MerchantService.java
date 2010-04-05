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
	 * 根据商户标识获取门店
	 * 
	 * @param merchantId
	 * @return
	 * @throws Exception
	 */
	public List findBranchByMerchantId(Long merchantId) throws Exception;

	/**
	 * 保存或者更新商户和门店
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
	 * 根据审核状态获取角色标识
	 * 
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public Long[] findRoleIdsByAuditStatus(Long auditStatus) throws Exception;

	/**
	 * 获取下步控制流程
	 * 
	 * @param curStatus
	 * @return
	 * @throws Exception
	 */
	public List findNextCtrlByStatusBefore(Long curStatus) throws Exception;

	/**
	 * 获取下步审批的角色
	 * 
	 * @param curStatus
	 * @return
	 * @throws Exception
	 */
	public List findNextCtrlRoleIdsByStatusBefore(Long curStatus)
			throws Exception;

	/**
	 * 根据门店获取pos信息
	 * 
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List findPosByBranchId(Long branchId) throws Exception;

	public List findCommisionStepByMerchantId(Long merchantId) throws Exception;

	/**
	 * 审核商户
	 * 
	 * @param merchantId
	 * @param merchantAudit
	 */
	public void saveAuditMerchant(RfmsMerchant merchant,
			RfmsMerchantAudit merchantAudit, List<RfmsMerchantPos> pos,
			AppRequest appRequest) throws Exception;

	/**
	 * 获取POS编号
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
	 * 获取下一个操作员
	 * 
	 * @param merchantId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public Operator findOperatorByMerchantIdAndAuditStatus(Long merchantId,
			Long auditStatus) throws Exception;

	/**
	 * 对商户充值
	 * 
	 * @param merchantId
	 * @param payment
	 * @param appRquest
	 * @throws Exception
	 */
	public void savePayment(Long merchantId, RfmsMerchantPayment payment,
			AppRequest appRquest) throws Exception;
}
