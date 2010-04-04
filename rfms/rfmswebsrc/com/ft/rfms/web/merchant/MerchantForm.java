/**
 * 
 */
package com.ft.rfms.web.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsCommisionStep;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.singleTable.web.BaseSimpleForm;

/**
 * @author soler
 *
 */
public class MerchantForm extends BaseSimpleForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6579964038651737320L;

	private RfmsMerchantBranch branch;
	
	private RfmsMerchantPos pos;
	
	private Long branchId;
	
	private Long posId;
	
	private Long merchantId;
	
	private Long returnTo;
	
	private List nextAuditOperators=new ArrayList();
	
	private Long[] netxtOperatorIds=new Long[0];
	
	private List users=new ArrayList();
	
	private RfmsCommisionStep step;
	
	private Long stepId;
	
	private Long auditStatus;
	
	/**
	 * @return the auditStatus
	 */
	public Long getAuditStatus() {
		return auditStatus;
	}


	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(Long auditStatus) {
		this.auditStatus = auditStatus;
	}


	/**
	 * @return the step
	 */
	public RfmsCommisionStep getStep() {
		return step;
	}


	/**
	 * @param step the step to set
	 */
	public void setStep(RfmsCommisionStep step) {
		this.step = step;
	}


	/**
	 * @return the stepId
	 */
	public Long getStepId() {
		return stepId;
	}


	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}


	/**
	 * @return the users
	 */
	public List getUsers() {
		return users;
	}


	/**
	 * @param users the users to set
	 */
	public void setUsers(List users) {
		this.users = users;
	}


	/**
	 * @return the netxtOperatorIds
	 */
	public Long[] getNetxtOperatorIds() {
		return netxtOperatorIds;
	}


	/**
	 * @param netxtOperatorIds the netxtOperatorIds to set
	 */
	public void setNetxtOperatorIds(Long[] netxtOperatorIds) {
		this.netxtOperatorIds = netxtOperatorIds;
	}


	/**
	 * @return the nextAuditOperators
	 */
	public List getNextAuditOperators() {
		return nextAuditOperators;
	}


	/**
	 * @param nextAuditOperators the nextAuditOperators to set
	 */
	public void setNextAuditOperators(List nextAuditOperators) {
		this.nextAuditOperators = nextAuditOperators;
	}


	/**
	 * @return the returnTo
	 */
	public Long getReturnTo() {
		return returnTo;
	}


	/**
	 * @param returnTo the returnTo to set
	 */
	public void setReturnTo(Long returnTo) {
		this.returnTo = returnTo;
	}


	/**
	 * @return the merchantId
	 */
	public Long getMerchantId() {
		return merchantId;
	}


	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}


	/**
	 * @return the branch
	 */
	public RfmsMerchantBranch getBranch() {
		return branch;
	}


	/**
	 * @param branch the branch to set
	 */
	public void setBranch(RfmsMerchantBranch branch) {
		this.branch = branch;
	}


	/**
	 * @return the branchId
	 */
	public Long getBranchId() {
		return branchId;
	}


	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}


	/**
	 * @return the pos
	 */
	public RfmsMerchantPos getPos() {
		return pos;
	}


	/**
	 * @param pos the pos to set
	 */
	public void setPos(RfmsMerchantPos pos) {
		this.pos = pos;
	}


	/**
	 * @return the posId
	 */
	public Long getPosId() {
		return posId;
	}


	/**
	 * @param posId the posId to set
	 */
	public void setPosId(Long posId) {
		this.posId = posId;
	}


	/* (non-Javadoc)
	 * @see com.ft.singleTable.web.BaseSimpleForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		this.setBaseEntity(new RfmsMerchant());
		this.setSearchObj(new RfmsMerchant());
	}

 

}
