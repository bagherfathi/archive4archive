package com.ft.web.sm.data.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.BankManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RelBankOrgDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 银行绑定组织页面逻辑类。
 * 
 * @struts.action path="/bankOrg" name="bankOrgForm" scope="request"
 *                validate="false" parameter="act" input="sm.bank.org.index"
 * 
 * @struts.action-forward name="create" path="sm.bank.org.create"
 * @struts.action-forward name="edit" path="sm.bank.org.edit"
 * 
 * @struts.tiles name="sm.bank.org.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/bank/listBankOrg.jsp"
 * 
 * @spring.bean id="bankOrgAction"
 * @version 1.0
 */
public class BankOrgAction extends BaseAction {
    // 记录日志信息
    private static Log logger = LogFactory.getLog(BankOrgAction.class);

    private BankManager bankManager;

    /**
     * @spring.property ref="bankManager"
     * 
     * @param bankManager
     */
    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    /**
     * 跳转到绑定列表页面。
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankOrgForm bankOrgForm = (BankOrgForm) form;
        OrganizationDTO[] accessCompanies = OperatorSessionHelper
                .getAccessCompaniesOfLoginOrg(request);
        List existBankOrgs = bankManager.findBankOrgsOfBank(bankOrgForm
                .getBank().getBankId());
        List<RelBankOrgDTO> result = new ArrayList<RelBankOrgDTO>();
        for (int i = 0; i < existBankOrgs.size(); i++) {
            RelBankOrgDTO relBankOrg = (RelBankOrgDTO) existBankOrgs.get(i);
            boolean flag = false;
            for (int j = 0; j < accessCompanies.length; j++) {
                if (relBankOrg.getOrg().getOrgId() == accessCompanies[j]
                        .getOrgId().longValue()
                        && accessCompanies[j].getStatus() == OrganizationDTO.STATUS_NORMAL) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                result.add(relBankOrg);
            }
        }
        request.setAttribute("bankOrgs", result);
        return mapping.getInputForward();
    }

    /**
     * 跳转到创建绑定页面
     * 
     * @struts.tiles name="sm.bank.org.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/bank/editBankOrg.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addRelBankOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankOrgForm bankOrgForm = (BankOrgForm) form;

        // 查询当前操作员可访问分公司
        OrganizationDTO[] accessCompanies = OperatorSessionHelper
                .getAccessCompaniesOfLoginOrg(request);

        List existBankOrgs = bankManager.findBankOrgsOfBank(bankOrgForm
                .getBank().getBankId());
        List<LabelValueBean> result = new ArrayList<LabelValueBean>();

        for (int index = 0; index < accessCompanies.length; index++) {
            OrganizationDTO org = accessCompanies[index];
            boolean flag = false;
            for (int i = 0; i < existBankOrgs.size(); i++) {
                RelBankOrgDTO relBankOrg = (RelBankOrgDTO) existBankOrgs.get(i);

                if (relBankOrg.getOrg().getOrgId() == org.getOrgId()
                        .longValue() && org.getStatus() == OrganizationDTO.STATUS_NORMAL) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                LabelValueBean labelValueBean = new LabelValueBean();
                labelValueBean.setLabel(org.getName());
                labelValueBean.setValue("" + org.getOrgId());
                result.add(labelValueBean);
            }
        }

        bankOrgForm.getRelBankOrg().getRelBankOrg().setOnlineStatus(-1);
        bankOrgForm.setAccessOrgs(result);
        return mapping.findForward("create");
    }

    /**
     * 保存或更新银行绑定组织。
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankOrgForm bankOrgForm = (BankOrgForm) form;
        RelBankOrgDTO relBankOrg = bankOrgForm.getRelBankOrg();

        String actionCode = ActionDefinition.SYS_ADD_BANKORG;

        if (relBankOrg.getRelBankOrg().getRelId() > 0) {
            actionCode = ActionDefinition.SYS_UPDATE_BANKORG;
        }

        // //保存受理记录
        AppRequest appRequest = bankOrgForm.getAppRequest(request, actionCode);
        // appService.saveApp(appRequest);

        try {
            // 更新银行绑定组织
            if (relBankOrg.getRelBankOrg().getRelId() > 0) {
                bankManager.updateBankOrg(relBankOrg, appRequest);

                // 新增银行绑定组织
            } else {
                relBankOrg.setCreateDate(new Date());
                relBankOrg.setOperatorId(bankOrgForm.getCurrentUser()
                        .getOperatorId().longValue());
                relBankOrg.setOrgId(bankOrgForm.getCurrentUser().getOrg()
                        .getOrgId());
                relBankOrg.setLoginOrgId(OperatorSessionHelper.getLoginOrg(
                        request).getOrgId().longValue());
                bankManager.saveBankOrg(relBankOrg, appRequest);
            }
        } catch (Exception e) {
            // 记录保存失败日志
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }

        // 操作日志
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("bankOrg.do?bankId="
                + bankOrgForm.getBank().getBankId());
    }

    public ActionForward deleteRelBankOrg(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BankOrgForm bankOrgForm = (BankOrgForm) form;
        String[] selectedIds = request.getParameterValues("selectedIds");

        String actionCode = ActionDefinition.SYS_DELETE_BANKORG;
        // //保存受理记录
        AppRequest appRequest = bankOrgForm.getAppRequest(request, actionCode);
        // appService.saveApp(appRequest);
        try {
            this.bankManager.deleteBankOrg(selectedIds, appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("bankOrg.do?bankId="
                + bankOrgForm.getBank().getBankId());
    }

    /**
     * 跳转到修改绑定页面
     * 
     * @struts.tiles name="sm.bank.org.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/bank/editBankOrg.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }
}
