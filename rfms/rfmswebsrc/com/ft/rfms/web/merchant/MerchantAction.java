/**
 * 
 */
package com.ft.rfms.web.merchant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.rfms.entity.RfmsCommisionStep;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantAudit;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPayment;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.model.MerchantService;
import com.ft.rfms.web.RfmsActionDefinition;
import com.ft.singleTable.web.BaseSimpleAction;
import com.ft.sm.dto.OperatorDTO;
import com.ft.struts.ActionMessagesHelper;
import com.ft.utils.MoneyFormat;

/**
 * @author soler
 * 
 */

public class MerchantAction extends BaseSimpleAction {

	private MerchantService merchantService;

	private static long incPosId = -1;

	private Long roleId;

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param appService
	 *            the appService to set
	 */
	public void setAppService(BusiAppService appService) {
		this.appService = appService;
	}

	/**
	 * @param merchantService
	 *            the merchantService to set
	 */
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	private List<LabelValueBean> getOperators(Long curStatus) {
		if (curStatus == null || curStatus.longValue() == 0) {
			curStatus = new Long(1);
		}
		List<LabelValueBean> collection = new ArrayList<LabelValueBean>();
		try {
			Long[] roleIds = merchantService
					.findRoleIdsByAuditStatus(curStatus);
			OperatorManager operatorManager = (OperatorManager) SpringBeanUtils
					.getBean("operatorManager");
			List<Long> opIds = new ArrayList<Long>();
			if (roleIds != null) {
				for (int i = 0; i < roleIds.length; i++) {
					List list = operatorManager.findOperatorOfRole(roleIds[i]);
					if (list != null && !list.isEmpty()) {
						for (int x = 0; x < list.size(); x++) {
							OperatorDTO opdto = (OperatorDTO) list.get(x);
							if (!opIds.contains(opdto.getOperatorId())) {
								collection.add(new LabelValueBean(opdto
										.getOperator().getOpName(), opdto
										.getOperatorId().longValue()
										+ ""));
								opIds.add(opdto.getOperatorId());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collection;
	}

	private List<LabelValueBean> getNextAuditOperators(Long curStatus) {
		if (curStatus == null && curStatus.longValue() == 0) {
			curStatus = new Long(1);
		}
		List<LabelValueBean> collection = new ArrayList<LabelValueBean>();
		try {
			List roleIds = merchantService
					.findNextCtrlRoleIdsByStatusBefore(curStatus);
			OperatorManager operatorManager = (OperatorManager) SpringBeanUtils
					.getBean("operatorManager");
			List<Long> opIds = new ArrayList<Long>();
			for (int i = 0; i < roleIds.size(); i++) {
				List list = operatorManager.findOperatorOfRole((Long) roleIds
						.get(i));
				if (list != null && !list.isEmpty()) {
					for (int x = 0; x < list.size(); x++) {
						OperatorDTO opdto = (OperatorDTO) list.get(x);
						if (!opIds.contains(opdto.getOperatorId())) {
							collection.add(new LabelValueBean(opdto
									.getOperator().getOpName(), opdto
									.getOperatorId().longValue()
									+ ""));
							opIds.add(opdto.getOperatorId());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#create(org.apache.struts.action
	 * .ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		arg2.getSession().removeAttribute("merchantForm");
		arg2.getSession().setAttribute("branchs", new ArrayList());
		MerchantForm aform = (MerchantForm) arg1;
		aform.reset(arg0, arg2);
		aform.setNextAuditOperators(this.getOperators(new Long(1)));
		arg2.getSession().setAttribute("merchantForm", aform);
		aform.setUsers(getUsers());
		return arg0.findForward("edit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#delete(org.apache.struts.action
	 * .ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward delete(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return super.delete(arg0, arg1, arg2, arg3);
	}

	public ActionForward toSearch(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return arg0.findForward("searchIndex");
	}

	public ActionForward view(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		this.edit(arg0, arg1, arg2, arg3);
		return arg0.findForward("view");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#edit(org.apache.struts.action
	 * .ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward edit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		arg2.getSession().removeAttribute("merchantForm");
		arg2.getSession().setAttribute("branchs", new ArrayList());
		arg2.getSession().setAttribute("stepMap", null);
		arg2.getSession().setAttribute("merchantSteps", null);
		super.edit(arg0, arg1, arg2, arg3);

		MerchantForm aform = (MerchantForm) arg1;
		aform.setStepId(new Long(0));
		aform.setBranchId(new Long(0));
		Long auditStatus = new Long(0);
		if (aform.getId() != null && aform.getId().longValue() > 0) {
			RfmsMerchant merchant = (RfmsMerchant) aform.getBaseEntity();
			arg2.getSession().setAttribute(
					"branchs",
					this.merchantService.findBranchByMerchantId(merchant
							.getMerchantId()));
			aform.setBaseEntity(merchant);
			auditStatus = merchant.getAuditStatus();
		}
		aform.setNextAuditOperators(this.getOperators(auditStatus));
		arg2.getSession().setAttribute("merchantForm", aform);
		aform.setUsers(getUsers());
		this.viewStep(arg0, arg1, arg2, arg3);
		return arg0.findForward("edit");
	}

	private List<LabelValueBean> getUsers() throws Exception {
		List<LabelValueBean> collection = new ArrayList<LabelValueBean>();
		OperatorManager operatorManager = (OperatorManager) SpringBeanUtils
				.getBean("operatorManager");
		List list = operatorManager.findOperatorOfRole(this.roleId);
		if (list != null) {
			List<Long> opIds = new ArrayList<Long>();
			for (int i = 0; i < list.size(); i++) {
				OperatorDTO opdto = (OperatorDTO) list.get(i);
				if (!opIds.contains(opdto.getOperatorId())) {
					collection.add(new LabelValueBean(opdto.getOperator()
							.getOpName(), opdto.getOperatorId().longValue()
							+ ""));
					opIds.add(opdto.getOperatorId());
				}
			}
		}
		return collection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#save(org.apache.struts.action
	 * .ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward save(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		ActionErrors errors = arg1.validate(arg0, arg2);

		MerchantForm aform = (MerchantForm) arg1;
		RfmsMerchant merchant = (RfmsMerchant) aform.getBaseEntity();
		// merchant.setStatus("1");
		List<RfmsMerchantBranch> branchs = (List) arg2.getSession()
				.getAttribute("branchs");

		Map<String, RfmsCommisionStep> steps = (Map) arg2.getSession()
				.getAttribute("stepMap");
		if (merchant.getBranchNum() != branchs.size()) {
			errors.add("notSameSize", new ActionMessage(
					"msg.show.merchant.branchSizeEbranchNum"));
			ActionMessagesHelper.saveRequestMessage(arg2,
					"msg.show.merchant.branchSizeEbranchNum");
		}
		// if (merchant.getCommisionStep().longValue() == 1
		// && (steps == null || steps.size() == 0)) {
		// errors.add("setSteps", new ActionMessage(
		// "msg.show.merchant.setStep"));
		// ActionMessagesHelper.saveRequestMessage(arg2,
		// "msg.show.merchant.setStep");
		// }
		// 结算方式校验
		// Long settlePeriod = merchant.getSettlePeriod();
		// Long settleDate = merchant.getSettleDate();
		// if (settlePeriod.longValue() == 2 || settlePeriod.longValue() == 3) {
		// // 周结算
		// if (settleDate.longValue() < 1 || settleDate.longValue() > 7) {
		// //
		// errors.add("settle", new ActionMessage(
		// "msg.show.merchant.settle_period"));
		// ActionMessagesHelper.saveRequestMessage(arg2,
		// "msg.show.merchant.settle_period", new Object[] {
		// "周结,二周结", "1", "7" });
		// }
		//
		// } else if (settlePeriod.longValue() == 3) {
		// if (settleDate.longValue() < 1 || settleDate.longValue() > 28) {
		// //
		// errors.add("settle1", new ActionMessage(
		// "msg.show.merchant.settle_period"));
		// ActionMessagesHelper.saveRequestMessage(arg2,
		// "msg.show.merchant.settle_period", new Object[] { "月结",
		// "1", "28" });
		// }
		// }

		// 阶梯佣金设置必须从0开始
		// if (merchant.getCommisionStep().longValue() == 1) {
		// Iterator it = steps.keySet().iterator();
		// boolean hasZeroValue = false;
		// while (it.hasNext()) {
		// RfmsCommisionStep s = steps.get(it.next());
		// if (s.getMinCharge().longValue() == 0) {
		// hasZeroValue = true;
		// merchant.setCommisionCharge(s.getCommisionCharge());
		// break;
		// }
		// }
		// if (!hasZeroValue) {
		// errors.add("settle1", new ActionMessage(
		// "msg.show.merchant.setpCommSet"));
		// ActionMessagesHelper.saveRequestMessage(arg2,
		// "msg.show.merchant.setpCommSet");
		// }
		// }
		Long[] nextOperatorIds = aform.getNetxtOperatorIds();
		String strsubflag = arg2.getParameter("subflag");
		if (strsubflag == null) {
			strsubflag = "0";
		}
		Long subFlag = new Long(strsubflag);
		if (merchant.getAuditStatus() == 1 || subFlag.longValue() == 1) {
			if (nextOperatorIds == null || nextOperatorIds.length <= 0) {
				merchant.setAuditStatus(new Long(0));
				errors.add("settle1", new ActionMessage(
						"msg.show.merchant.selectNext"));
				ActionMessagesHelper.saveRequestMessage(arg2,
						"msg.show.merchant.selectNext");
			}
		}
		if (errors != null && errors.size() > 0) {
			merchant.setAuditStatus(new Long(0));
			return arg0.findForward("edit");
		}
		AppRequest appRequest = aform.getAppRequest(arg2,
				RfmsActionDefinition.RFMS_MERCHANT_ADD);

		merchant.setAuditStatus(subFlag);
		this.merchantService.saveOrUpdateMerchant(merchant, branchs, steps,
				nextOperatorIds, appRequest);
		// arg2.getSession().setAttribute("branchs", new
		// HashSet<RfmsMerchantBranch>());
		String isSearchEdit = arg2.getParameter("isSearchEdit");
		if (isSearchEdit != null && "1".equalsIgnoreCase(isSearchEdit)) {
			return arg0.findForward("searchIndex");
		}
		return arg0.getInputForward();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#search(org.apache.struts.action
	 * .ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward search(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		// 1.获取操作员的角色
		// 2.获取改角色可以处理的商户列表

		return super.search(arg0, arg1, arg2, arg3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ft.singleTable.web.BaseSimpleAction#unspecified(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		super.unspecified(arg0, arg1, arg2, arg3);
		MerchantForm aform = (MerchantForm) arg1;
		aform.setUsers(getUsers());
		arg2.setAttribute("searchObj.operatorId", aform.getCurrentUser()
				.getOperatorId());
		arg2.setAttribute("searchObj.auditStatus", "0");
		return arg0.getInputForward();
	}

	/**
	 * 门店列表
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward listMerchantBranch(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest request,
			HttpServletResponse arg3) throws Exception {
		String merchantId = request.getParameter("merchantId");
		List branchs = this.merchantService.findBranchByMerchantId(new Long(
				merchantId));
		MerchantForm aform = (MerchantForm) arg1;
		aform.setMerchantId(new Long(merchantId));
		request.setAttribute("branchs", branchs);
		return arg0.findForward("listBranch");
	}

	/**
	 * 编辑门店
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward editMerchantBranch(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest request,
			HttpServletResponse arg3) throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		Long branchId = aform.getBranchId();
		RfmsMerchantBranch branch = null;
		if (branchId != null && branchId.longValue() != 0) {
			branch = (RfmsMerchantBranch) this.merchantService.getObjectById(
					RfmsMerchantBranch.class, branchId);
		} else {
			branch = new RfmsMerchantBranch();
		}

		aform.setBranch(branch);

		return arg0.findForward("editBranch");
	}

	/**
	 * 保存门店
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward saveMerchantBranch(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest request,
			HttpServletResponse arg3) throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		RfmsMerchantBranch branch = aform.getBranch();
		if (branch.getMerchantBranchId() == null) {
			branch.setMerchantBranchId(new Long(0));
		}
		List list = (List) request.getSession().getAttribute("branchs");
		if (list == null) {
			list = new ArrayList<RfmsMerchantBranch>();
			list.add(branch);
		} else {
			for (int i = 0; i < list.size(); i++) {
				RfmsMerchantBranch abranch = (RfmsMerchantBranch) list.get(i);
				if (abranch.getId() != null
						&& abranch.getId().longValue() > 0
						&& abranch.getId().longValue() == branch.getId()
								.longValue()) {
					list.remove(abranch);
				}
			}
			list.add(branch);
		}

		request.getSession().setAttribute("branchs", list);
		arg3.getOutputStream().write(
				"<script>window.returnValue='true';self.close();</script>"
						.getBytes());
		return null;
	}

	public ActionForward refreshEdit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		return arg0.findForward("edit");
	}

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward audit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		String auditStatusStr = request.getParameter("auditStatus");
		if (auditStatusStr == null) {
			auditStatusStr = "0";
		}
		request.getSession().setAttribute("auditStatus",
				request.getParameter("auditStatus"));
		MerchantForm aform = (MerchantForm) arg1;
		List merchants = this.merchantService
				.findMerchantsByOperatorAndAuditStatus(aform.getCurrentUser()
						.getOperatorId(), new Long(auditStatusStr));
		request.setAttribute("merchants", merchants);

		return arg0.findForward("audit");
	}

	/**
	 * 审批详细页面
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward auditView(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		request.getSession().removeAttribute("merchantForm");
		request.getSession().setAttribute("branchs", new ArrayList());
		super.edit(arg0, arg1, request, arg3);

		MerchantForm aform = (MerchantForm) arg1;
		if (aform.getId() != null && aform.getId().longValue() > 0) {
			RfmsMerchant merchant = (RfmsMerchant) aform.getBaseEntity();
			// 检查改用户的是否有审核权限，如果没有返回，以免恶意审核
			if (false) {
				// TODO
				arg0.findForward("audit");
			}

			List branchs = this.merchantService.findBranchByMerchantId(merchant
					.getMerchantId());
			request.getSession().setAttribute("branchs", branchs);
			if (branchs != null && !branchs.isEmpty()) {
				for (int i = 0; i < branchs.size(); i++) {
					RfmsMerchantBranch branch = (RfmsMerchantBranch) branchs
							.get(i);
					request.getSession().setAttribute(
							"posMap" + branch.getMerchantBranchId(),
							new HashMap<String, RfmsMerchantPos>());
				}
			}

			aform.setBaseEntity(merchant);
			request.getSession().setAttribute("merchantForm", aform);
			// 取得当前状态 审核的用户信息
			if (merchant.getAuditStatus().longValue() == 1
					&& (merchant.getDiscountRate() != null && merchant
							.getDiscountRate().floatValue() > 0.0)) {
				// 取财务审核的操作员，如果用户折扣已经设定，则不需要设定用户折扣，审核后状态为待财务审核
				aform.setNextAuditOperators(this
						.getNextAuditOperators(new Long(2)));
			} else {
				aform.setNextAuditOperators(this.getNextAuditOperators(merchant
						.getAuditStatus()));
			}

		}

		return arg0.findForward("auditView");
	}

	/**
	 * 查看，编辑pos记录
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewPos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		Long branchId = aform.getBranchId();
		Map<String, RfmsMerchantPos> posmap = (Map) request.getSession()
				.getAttribute("posMap" + branchId);

		if (posmap == null) {
			List list = this.merchantService.findPosByBranchId(branchId);
			posmap = new HashMap<String, RfmsMerchantPos>();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					RfmsMerchantPos pos = (RfmsMerchantPos) list.get(i);
					posmap.put(String.valueOf(pos.getMerchantPosId()), pos);
				}
			}
			request.getSession().setAttribute("posMap" + branchId, posmap);
		}
		Long posId = aform.getPosId();
		if (posId != null && posId.longValue() > 0) {
			RfmsMerchantPos pos = (RfmsMerchantPos) this.merchantService
					.getObjectById(RfmsMerchantPos.class, posId);
			aform.setPos(pos);
		} else {
			RfmsMerchantPos pos = new RfmsMerchantPos();
			pos.setSysPosCode(this.merchantService
					.getMerchantPosSysCode(branchId));
			aform.setPos(pos);
		}
		request.getSession().setAttribute("merchantPoss", posmap.values());
		request.getSession().setAttribute("merchantForm", aform);
		return arg0.findForward("posIndex");
	}

	public ActionForward showPos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		Long branchId = aform.getBranchId();
		request.getSession().setAttribute("posMap" + branchId, null);
		this.viewPos(arg0, arg1, request, arg3);
		return arg0.findForward("posShow");
	}

	public ActionForward editPos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		request.getSession().setAttribute("merchantPoss", new ArrayList());
		MerchantForm aform = (MerchantForm) arg1;
		Long branchId = aform.getBranchId();
		Long posId = aform.getPosId();
		Map<String, RfmsMerchantPos> posmap = (Map) request.getSession()
				.getAttribute("posMap" + branchId);
		aform.setPos(posmap.get(String.valueOf(posId)));
		// posmap.remove(String.valueOf(posId));
		Collection<RfmsMerchantPos> poss = posmap.values();
		request.getSession().setAttribute("merchantPoss", poss);
		request.getSession().setAttribute("merchantForm", aform);
		return arg0.findForward("posIndex");

	}

	/**
	 * 添加pos记录
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward savePos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		RfmsMerchantPos pos = aform.getPos();
		Long branchId = aform.getBranchId();
		Map<String, RfmsMerchantPos> posmap = (Map) request.getSession()
				.getAttribute("posMap" + branchId);
		this.merchantService.save(pos);
		if (pos.getMerchantPosId() != null
				&& pos.getMerchantPosId().longValue() != 0) {
			posmap.put(String.valueOf(pos.getMerchantPosId()), pos);
		} else {
			// 附加一个ID，负数，保存的是否校正
			pos.setMerchantPosId(new Long(incPosId--));
			posmap.put(String.valueOf(pos.getMerchantPosId()), pos);
		}
		RfmsMerchantPos pos1 = new RfmsMerchantPos();
		PropertyUtils.copyProperties(pos1, pos);
		RfmsMerchantPos xpos = new RfmsMerchantPos();
		xpos
				.setSysPosCode(this.merchantService
						.getMerchantPosSysCode(branchId));
		aform.setPos(xpos);
		request.getSession().setAttribute("merchantPoss", posmap.values());

		return arg0.findForward("posIndex");
	}

	public ActionForward deletePos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		RfmsMerchantPos pos = aform.getPos();
		String posId = request.getParameter("posId");
		this.merchantService.delObject(new Long(posId), "RfmsMerchantPos");
		Long branchId = aform.getBranchId();
		Map<String, RfmsMerchantPos> posmap = (Map) request.getSession()
				.getAttribute("posMap" + branchId);
		posmap.remove(posId);
		request.getSession().setAttribute("merchantPoss", posmap.values());
		return arg0.findForward("posIndex");
	}

	@SuppressWarnings("unchecked")
	public ActionForward saveAudit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		MerchantForm aform = (MerchantForm) arg1;
		boolean returnflag = false;
		String auditResult = request.getParameter("auditResult");
		if (auditResult == null) {
			auditResult = "0";// 审核不通过
		}
		String returnTo = request.getParameter("returnTo");
		if (returnTo == null) {
			returnTo = "-1";
		} else {
			returnflag = true;
		}
		RfmsMerchant merchant = (RfmsMerchant) aform.getBaseEntity();

		String auditRemark = request.getParameter("auditRemark");
		Long[] nextopIds = aform.getNetxtOperatorIds();
		if ((nextopIds == null || nextopIds.length <= 0)
				&& merchant.getAuditStatus().longValue() != 7
				&& !returnTo.equals("-1")) {
			ActionMessagesHelper.saveRequestMessage(request,
					"msg.show.merchant.selectNext");
			return this.auditView(arg0, arg1, request, arg3);
		}
		if (merchant.getAuditStatus() != null
				&& merchant.getAuditStatus().longValue() == 2) { // 设定用户折扣，检查用户折扣是否设定
			if (merchant.getDiscountRate() == null
					|| merchant.getDiscountRate().floatValue() <= 0.0f) {
				ActionMessagesHelper.saveRequestMessage(request,
						"msg.show.merchant.inputDiscount");
				return this.auditView(arg0, arg1, request, arg3);
			}
		}

		RfmsMerchantAudit audit = new RfmsMerchantAudit();
		audit.setAuditRemark(auditRemark);
		audit.setAuditTime(new Date());
		if (merchant.getAuditStatus().longValue() == 7) {
			audit.setFinished(new Long(1));
		} else {
			audit.setFinished(new Long(0));
		}
		audit.setFlowCtrlId(new Long(0));
		audit.setMerchantId(aform.getId());
		audit.setNextOperatorId(arrayToString(nextopIds));
		audit.setOperatorId(aform.getCurrentUser().getOperatorId());
		audit.setReturnto(new Long(returnTo));
		audit.setAuditResult(new Long(auditResult));
		List<RfmsMerchantPos> poss = new ArrayList();

		if (merchant.getAuditStatus() == 6) { // 是分配数据才处理pos信息
			List branchs = this.merchantService.findBranchByMerchantId(merchant
					.getMerchantId());

			for (int n = 0; n < branchs.size(); n++) {
				int flag = 0;
				RfmsMerchantBranch branch = (RfmsMerchantBranch) branchs.get(n);
				Map<String, RfmsMerchantPos> posmap = (Map) request
						.getSession().getAttribute("posMap" + branch.getId());
				if (posmap != null) {
					Collection<RfmsMerchantPos> colle = posmap.values();
					for (Iterator i = colle.iterator(); i.hasNext();) {
						RfmsMerchantPos apos = (RfmsMerchantPos) i.next();
						if (apos.getMerchantPosId() != null
								&& apos.getMerchantPosId().longValue() < 0) {
							apos.setMerchantPosId(null);
						}
						poss.add(apos);
					}
				}
			}
		}
		// incPosId=new Long(-1);
		this.merchantService.saveAuditMerchant(merchant, audit, poss, aform
				.getAppRequest(request,
						RfmsActionDefinition.RFMS_MERCHANT_AUDIT));
		String auditStatus = (String) request.getSession().getAttribute(
				"auditStatus");
		return this
				.getRedirectForwardAction("merchant.do?act=audit&auditStatus="
						+ auditStatus);
	}

	private String arrayToString(Object[] arr) {
		StringBuffer result = new StringBuffer();
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				if (result.length() == 0) {
					result.append(String.valueOf(arr[i]));
				} else {
					result.append(",").append(String.valueOf(arr[i]));
				}
			}
		}
		return result.toString();
	}

	/**
	 * 查看，编辑pos记录
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewStep(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		Long merchantId = aform.getId();
		Map<String, RfmsCommisionStep> stepmap = (Map) request.getSession()
				.getAttribute("stepMap");

		if (stepmap == null) {
			List list = this.merchantService
					.findCommisionStepByMerchantId(merchantId);
			stepmap = new HashMap<String, RfmsCommisionStep>();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					RfmsCommisionStep step = (RfmsCommisionStep) list.get(i);
					stepmap
							.put(String.valueOf(step.getCommisionStepId()),
									step);
				}
			}
			request.getSession().setAttribute("stepMap", stepmap);
		}
		Long stepId = aform.getStepId();
		if (stepId != null && stepId.longValue() > 0) {
			RfmsCommisionStep step = (RfmsCommisionStep) this.merchantService
					.getObjectById(RfmsCommisionStep.class, stepId);
			aform.setStep(step);
		} else {
			RfmsCommisionStep step = new RfmsCommisionStep();
			aform.setStep(step);
		}
		request.getSession().setAttribute("merchantSteps", stepmap.values());
		request.getSession().setAttribute("merchantForm", aform);
		return arg0.findForward("stepIndex");
	}

	public ActionForward editStep(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		request.getSession().setAttribute("merchantSteps", new ArrayList());
		MerchantForm aform = (MerchantForm) arg1;
		Long stepId = aform.getStepId();
		Map<String, RfmsCommisionStep> stepmap = (Map) request.getSession()
				.getAttribute("stepMap");
		aform.setStep(stepmap.get(String.valueOf(stepId)));
		// posmap.remove(String.valueOf(posId));
		Collection<RfmsCommisionStep> poss = stepmap.values();
		request.getSession().setAttribute("merchantSteps", poss);
		request.getSession().setAttribute("merchantForm", aform);
		return arg0.findForward("stepIndex");

	}

	/**
	 * 添加pos记录
	 * 
	 * @param arg0
	 * @param arg1
	 * @param request
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward saveStep(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		RfmsCommisionStep step = aform.getStep();
		Map<String, RfmsCommisionStep> posmap = (Map) request.getSession()
				.getAttribute("stepMap");
		if (step.getCommisionStepId() != null
				&& step.getCommisionStepId().longValue() != 0) {
			posmap.put(String.valueOf(step.getCommisionStepId()), step);
		} else {
			// 附加一个ID，负数，保存的是否校正
			step.setCommisionStepId(new Long(incPosId--));
			posmap.put(String.valueOf(step.getCommisionStepId()), step);
		}
		RfmsCommisionStep step1 = new RfmsCommisionStep();
		PropertyUtils.copyProperties(step1, step);
		aform.setStep(new RfmsCommisionStep());
		request.getSession().setAttribute("merchantSteps", posmap.values());

		return arg0.findForward("stepIndex");
	}

	public ActionForward searchMerchant(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		return arg0.findForward("searchIndex");
	}

	public ActionForward toPayment(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		return arg0.findForward("payment");
	}

	public ActionForward payment(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		MerchantForm aform = (MerchantForm) arg1;
		String strAmount = aform.getStrAmount();
		String notes = request.getParameter("notes");
		RfmsMerchantPayment payment = new RfmsMerchantPayment();
		payment.setAmount(MoneyFormat.yuan2Fen(strAmount));
		payment.setNotes(notes);
		payment.setMetchantId(aform.getMerchantId());
		payment.setPaymentDate(new Date());
		payment.setPaymentType(new Long(1));
		payment.setOperatorId(aform.getCurrentUser().getOperatorId());
		AppRequest appRequest = aform.getAppRequest(request,
				RfmsActionDefinition.RFMS_MERCHANT_ADD);
		this.merchantService.savePayment(aform.getMerchantId(), payment,
				appRequest);

		return arg0.findForward("searchIndex");
	}
}
