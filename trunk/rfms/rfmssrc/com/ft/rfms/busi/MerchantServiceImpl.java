/**
 * 
 */
package com.ft.rfms.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.service.BusiCodeGenerateService;
import com.ft.common.busi.BaseServiceImpl;
import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.rfms.entity.RfmsCommisionStep;
import com.ft.rfms.entity.RfmsFlowCtrl;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantAudit;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPayment;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.entity.dao.RfmsCommisionStepDAO;
import com.ft.rfms.entity.dao.RfmsFlowCtrlDAO;
import com.ft.rfms.entity.dao.RfmsMerchantAuditDAO;
import com.ft.rfms.entity.dao.RfmsMerchantBranchDAO;
import com.ft.rfms.entity.dao.RfmsMerchantDAO;
import com.ft.rfms.entity.dao.RfmsMerchantPosDAO;
import com.ft.rfms.model.MerchantService;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Region;

/**
 * @author soler
 * 
 */
public class MerchantServiceImpl extends BaseServiceImpl implements
		MerchantService, BusiBaseService {

	private RfmsMerchantPosDAO merchantPosDAO;

	private RfmsMerchantDAO merchantDAO;

	private RfmsMerchantBranchDAO merchantBranchDAO;

	private BusiAppService appService;

	private RfmsFlowCtrlDAO flowCtrlDAO;

	private RfmsMerchantAuditDAO merchantAuditDAO;

	private RfmsCommisionStepDAO stepDAO;
	
	
	/**
	 * @param stepDAO the stepDAO to set
	 */
	public void setStepDAO(RfmsCommisionStepDAO stepDAO) {
		this.stepDAO = stepDAO;
	}

	/**
	 * @param merchantAuditDAO
	 *            the merchantAuditDAO to set
	 */
	public void setMerchantAuditDAO(RfmsMerchantAuditDAO merchantAuditDAO) {
		this.merchantAuditDAO = merchantAuditDAO;
		super.setBaseDao(merchantAuditDAO);
	}

	/**
	 * @param flowCtrlDAO
	 *            the flowCtrlDAO to set
	 */
	public void setFlowCtrlDAO(RfmsFlowCtrlDAO flowCtrlDAO) {
		this.flowCtrlDAO = flowCtrlDAO;
	}

	/**
	 * @param appService
	 *            the appService to set
	 */
	public void setAppService(BusiAppService appService) {
		this.appService = appService;
	}

	/**
	 * @param merchantBranchDAO
	 *            the merchantBranchDAO to set
	 */
	public void setMerchantBranchDAO(RfmsMerchantBranchDAO merchantBranchDAO) {
		this.merchantBranchDAO = merchantBranchDAO;
	}

	/**
	 * @param merchantDAO
	 *            the merchantDAO to set
	 */
	public void setMerchantDAO(RfmsMerchantDAO merchantDAO) {
		this.merchantDAO = merchantDAO;
		this.baseDao = merchantDAO;
	}

	/**
	 * @param merchantPosDAO
	 *            the merchantPosDAO to set
	 */
	public void setMerchantPosDAO(RfmsMerchantPosDAO merchantPosDAO) {
		this.merchantPosDAO = merchantPosDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.MerchantService#findBranchByMerchantId(java.lang.Long)
	 */
	public List findBranchByMerchantId(Long merchantId) throws Exception {
		// TODO Auto-generated method stub
		return this.merchantBranchDAO.findByMerchantId(merchantId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.MerchantService#saveOrUpdateMerchant(com.ft.rfms.entity.RfmsMerchant)
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdateMerchant(RfmsMerchant merchant,
			List<RfmsMerchantBranch> branchs,Map<String,RfmsCommisionStep> steps, Long[] nextOperatorIds,
			AppRequest appRequest) throws Exception {
		// 保存商户信息
		if(merchant.getSysMerchantCode()==null || merchant.getSysMerchantCode().length()==0){
			merchant.setSysMerchantCode(this.getMerchantSysCode(merchant.getRegionId()));
		}
		merchant = (RfmsMerchant) this.saveAndSetHistoryObject(merchant,
				appRequest);
		
		this.merchantDAO.saveOrUpdate(merchant);
		// 保存门店信息
		List<RfmsMerchantBranch> list = new ArrayList<RfmsMerchantBranch>();
		for (int i = 0; i < branchs.size(); i++) {
			RfmsMerchantBranch abranch = branchs.get(i);
			if(abranch.getSysMerchantCode()==null || abranch.getSysMerchantCode().length()==0){
				abranch.setSysMerchantCode(this.getMerchantBranchSysCode(merchant.getSysMerchantCode()));
			}
			abranch.setMerchantId(merchant.getMerchantId());
			list.add(abranch);
		}
		list = (List<RfmsMerchantBranch>) this.saveAndSetHistoryObject(list,
				appRequest);
		this.merchantBranchDAO.batchUpdate(list);
        //保存阶梯佣金之前先删除原有该商户的阶梯佣金
		if(merchant.getCommisionStep().longValue()==1){
			Iterator it=steps.keySet().iterator();
			List stepList=new ArrayList();
			while(it.hasNext()){
				RfmsCommisionStep s=steps.get(it.next());
				if(s.getCommisionStepId()!=null && s.getCommisionStepId().longValue()<0){
					s.setCommisionStepId(null);
				}
				stepList.add(s);
			}
			this.stepDAO.batchUpdate(stepList);
		}else{//当非阶梯设置佣金的是否 向佣金表插入一条记录，最少0，最大9999999999
			this.stepDAO.deleteFromQuery("from RfmsCommisionStep where" +
					" merchantId="+merchant.getMerchantId(), null);
			RfmsCommisionStep s=new RfmsCommisionStep();
			s.setCommisionCharge(merchant.getCommisionCharge());
			s.setMaxCharge(new Long("9999999999"));
			s.setMinCharge(new Long(0));
			s.setMerchantId(merchant.getMerchantId());
			this.stepDAO.saveOrUpdate(s);
		}
		// 初始化审批流程
		if (nextOperatorIds != null && nextOperatorIds.length > 0) {
			String ids = FindByIdsCallback.joinKeys(nextOperatorIds);
			ids=ids.substring(1,ids.length()-1);
			List audits = this.merchantAuditDAO.findByMerchantIdAndFlowId(
					merchant.getMerchantId(), new Long(0));
			RfmsMerchantAudit audit = null;
			if (audits != null && !audits.isEmpty()) {
				audit = (RfmsMerchantAudit) audits.get(0);
			} else {
				audit = new RfmsMerchantAudit();
				audit.setOperatorId(merchant.getOperatorId());
				audit.setAuditTime(new Date());
				audit.setFinished(new Long(2));
				audit.setFlowCtrlId(new Long(0));
				audit.setMerchantId(merchant.getMerchantId());
				audit.setAuditRemark("商户新增");
			}
			audit.setNextOperatorId(ids);
			this.merchantAuditDAO.saveOrUpdate(audit);
		}
	}

	private String getMerchantSysCode(Long regionId){
       BusiCodeGenerateService codeGenerateService=(BusiCodeGenerateService)SpringBeanUtils.getBean("busiCodeGenerateService");
        Region region=(Region)this.merchantDAO.getObjectById(Region.class, regionId);
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("regionCode", "" + region.getRegionCode());
		return codeGenerateService.generateBusiCode("RFMS_MERCHANT_SYS_CODE", paramMap);
		
	}
	private String getMerchantBranchSysCode(String merchantSysCode){
	       BusiCodeGenerateService codeGenerateService=(BusiCodeGenerateService)SpringBeanUtils.getBean("busiCodeGenerateService");
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			paramMap.put("merchantSysCode", merchantSysCode);
			return codeGenerateService.generateBusiCode("RFMS_MERCHANT_BRANCH_SYS_CODE", paramMap);
	}
	
	public String getTicketSysCode(String ticketSysCode){
	       BusiCodeGenerateService codeGenerateService=(BusiCodeGenerateService)SpringBeanUtils.getBean("busiCodeGenerateService");
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			paramMap.put("ticketSysCode", ticketSysCode);
			return codeGenerateService.generateBusiCode("RFMS_TICKET_SYS_CODE", paramMap);
	}
	
	public String getMerchantPosSysCode(Long branchId){
	       BusiCodeGenerateService codeGenerateService=(BusiCodeGenerateService)SpringBeanUtils.getBean("busiCodeGenerateService");
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			Long merchantId=this.merchantBranchDAO.getById(branchId).getMerchantId();
			RfmsMerchant merchant=this.merchantDAO.getById(merchantId);
			Region region=(Region)this.merchantDAO.getObjectById(Region.class, merchant.getRegionId());
			String yscode=region.getYscode();
			paramMap.put("yscode", "" + yscode);
			return codeGenerateService.generateBusiCode("RFMS_MERCHANT_POS_SYS_CODE", paramMap);
	}
	
	private Object saveAndSetHistoryObject(Object obj, AppRequest appRequest)
			throws Exception {
		this.appService.saveApp(appRequest);
		Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
				obj, this);

		return returnObj;
	}

	public Long[] findRoleIdsByAuditStatus(Long auditStatus) {
		List list = this.flowCtrlDAO.findByAuditStatusBefore(auditStatus);
		if (list != null && !list.isEmpty()) {
			Long[] roleIds = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				RfmsFlowCtrl ctrl = (RfmsFlowCtrl) list.get(i);
				roleIds[i] = ctrl.getRoleId();
			}
			return roleIds;
		}
		return null;
	}

	public List findNextCtrlByStatusBefore(Long curStatus) throws Exception {
		return this.flowCtrlDAO.findNextCtrlByStatusBefore(curStatus);
	}

	public List findNextCtrlRoleIdsByStatusBefore(Long curStatus)
			throws Exception {
		return this.flowCtrlDAO.findNextCtrlRoleIdsByStatusBefore(curStatus);
	}

	/**
	 * 审核商户
	 * 
	 * @param merchantId
	 * @param merchantAudit
	 */
	public void saveAuditMerchant(RfmsMerchant merchant,
			RfmsMerchantAudit merchantAudit, List<RfmsMerchantPos> pos,
			AppRequest appRequest) throws Exception {
		RfmsMerchant newmerchant = this.merchantDAO.getById(merchant
				.getMerchantId());
		long beforeStatus=newmerchant.getAuditStatus().longValue();
		if(beforeStatus==7){
			merchantAudit.setFinished(new Long(1));
		}
		boolean isReturnTo = false;
		if (merchantAudit.getReturnto() != null
				&& merchantAudit.getReturnto().longValue() > -1) {
			// 回滚
			isReturnTo = true;
			newmerchant.setAuditStatus(merchantAudit.getReturnto());
			// 保存状态和审核记录
			this.merchantAuditDAO.save(merchantAudit);
			return;
		}
		
		//auditStatus==1 auditStatus==2 auditStatus==3
		List<Long> st=new ArrayList<Long>();
		st.add(new Long(1));
		st.add(new Long(2));
		st.add(new Long(3));
		if(!st.contains(newmerchant.getAuditStatus()) ){
			merchantAudit.setAuditResult(new Long(1));
		}
		if (merchantAudit.getAuditResult() == null
				|| merchantAudit.getAuditResult().longValue() <= 0) { // 审核不通过
			if (newmerchant.getAuditStatus().longValue() == 3) {
				// 财务审核不通过
				newmerchant.setAuditStatus(new Long(4));
			} else {
				// 回滚到前一状态
			}
		} else {// 通过
			if (newmerchant.getAuditStatus().longValue() == 1) {
				// 待审核状态下，如果用户折扣不是空，这审核后状态为待财务审核3.如果用户折扣是空，这为设置用户折扣状态2
				if (newmerchant.getDiscountRate() == null
						|| newmerchant.getDiscountRate().floatValue() <= 0.0) {
					newmerchant.setAuditStatus(new Long(2));
				} else {
					newmerchant.setAuditStatus(new Long(3));
				}
			} else {
				if (newmerchant.getAuditStatus() == 2) { // 设定用户折扣
					newmerchant.setDiscountRate(merchant.getDiscountRate());
					if (merchant.getDiscountRemark() != null) {
						newmerchant.setDiscountRemark(merchant
								.getDiscountRemark());
					}
				}
				if (newmerchant.getAuditStatus() == 6) {// 分配数据
					// 保存pos记录
					
					this.merchantPosDAO.batchSave(pos);
				}
				// 根据当前状态获取下一个审核状态
				List ctrls = this.flowCtrlDAO
						.findByAuditStatusBefore(newmerchant
								.getAuditStatus());
				Long nextStatus = merchant.getAuditStatus();
				if (ctrls != null && !ctrls.isEmpty()) {
					RfmsFlowCtrl flowCtrl = (RfmsFlowCtrl) ctrls.get(0);
					nextStatus = flowCtrl.getStatusAfter();
				}
				newmerchant.setAuditStatus(nextStatus);
			}
		}
		// 设置商户审核状态
		
		Object obj = this.saveAndSetHistoryObject(newmerchant, appRequest);
		this.merchantDAO.update(obj);
        if(newmerchant.getAuditStatus()==8){
			List list=this.merchantBranchDAO.findByMerchantId(newmerchant.getMerchantId());
			if(list!=null){
				for(int i=0;i<list.size();i++){
					RfmsMerchantBranch branch=(RfmsMerchantBranch)list.get(i);
					branch.setSysJionState("1");
				}
			}
			this.merchantBranchDAO.batchUpdate(list);
		}
		// 保存商户和审核信息
		//if(beforeStatus==1 || beforeStatus==2 || beforeStatus==3)
		  this.merchantAuditDAO.save(merchantAudit);
	}

	public List findPosByBranchId(Long branchId) throws Exception {
		return this.merchantPosDAO.findPosByBranchId(branchId);
	}
	
	public List findMerchantsByOperatorAndAuditStatus(Long nextOperatorId,Long auditStatus)throws Exception{
		return this.merchantDAO.findMerchantsByOperatorAndAuditStatus(nextOperatorId, auditStatus);
	}
	public List findCommisionStepByMerchantId(Long merchantId)throws Exception{
		return this.stepDAO.findByMerchantId(merchantId);
	}
	
	/**
	 * 获取下一个操作员
	 * @param merchantId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public Operator findOperatorByMerchantIdAndAuditStatus(Long merchantId,Long auditStatus)throws Exception{
		RfmsMerchantAudit audit=this.merchantAuditDAO.findCurOperator(merchantId);
		if(audit.getNextOperatorId()!=null && audit.getNextOperatorId().length()>0){
		 return (Operator)this.baseDao.getObjectById(Operator.class, new Long(audit.getNextOperatorId()));
		}
		return null;
	}
	
	/**
	 * 对商户充值
	 * @param merchantId
	 * @param payment
	 * @param appRquest
	 * @throws Exception
	 */
	public void savePayment(Long merchantId,RfmsMerchantPayment payment,AppRequest appRequest) throws Exception{
		RfmsMerchant merchant=this.merchantDAO.getById(merchantId);
		//设置金额
		this.appService.saveApp(appRequest);
		if(merchant.getAmount()==null){
			merchant.setAmount(new Long(0));
		}
		merchant.setAmount(merchant.getAmount()+payment.getAmount());
		merchant = (RfmsMerchant) this.saveAndSetHistoryObject(merchant,
				appRequest);
		this.merchantDAO.saveOrUpdate(merchant);
		this.baseDao.save(payment);
		
	}
}
