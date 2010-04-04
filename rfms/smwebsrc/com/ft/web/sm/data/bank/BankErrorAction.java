package com.ft.web.sm.data.bank;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.BankManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.BankErrorDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * 托收错误编码
 * 
 * @struts.action path="/bankError" name="bankErrorForm" scope="request"
 *                validate="false" parameter="act" input="sm.bankError.index"
 * 
 * @struts.tiles name="sm.bankError.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/bank/listBankError.jsp"
 * 
 * @struts.action-forward name="edit" path="sm.bankerror.edit"
 * 
 * @struts.action-forward name="create" path="sm.bankerror.edit"
 * 
 * @spring.bean id="bankErrorAction"
 * 
 * @version 1.0
 */
public class BankErrorAction extends BaseAction {

    // 记录日志信息
    private static Log logger = LogFactory.getLog(BankErrorAction.class);

    private BankManager bankManager;

    /**
     * @spring.property ref="bankManager"
     * 
     * @param bankManager
     */
    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 提交页面查询，跳转到查询结果页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 跳转到创建页面
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("create");
    }

    /**
     * 跳转到编辑页面
     * 
     * @struts.tiles name="sm.bankerror.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/bank/editBankError.jsp"
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 保存银行托收错误编码信息
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
        BankErrorForm bankErrorForm = (BankErrorForm) form;
        BankErrorDTO bankError = bankErrorForm.getBankError();
        
        // 同一银行中，托收错误代码不能重复
        List results = this.bankManager.findBankErrorsByBank(bankError.getBankId());
        for (Iterator iter = results.iterator(); iter.hasNext();) {
            BankErrorDTO element = (BankErrorDTO) iter.next();
            if (element.getErrorCode().equals(bankError.getErrorCode())) {
                if (bankError.getErrorId() == null
                        || bankError.getErrorId().longValue() != element
                                .getErrorId().longValue()) {
                    ActionMessagesHelper.saveRequestMessage(request,
                            "errors.bankerror.code.exist");
                    return mapping.findForward("edit");
                }
            }
        }

        String actionCode = ActionDefinition.SYS_ADD_BANKERROR;
        
        if (null != bankError.getErrorId()
                && bankError.getErrorId().longValue() != 0) {
            actionCode = ActionDefinition.SYS_UPDATE_BANKERROR;
        }

        ////保存受理记录
        AppRequest appRequest = bankErrorForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        try {
            if (null != bankError.getErrorId()
                    && bankError.getErrorId().longValue() != 0) {
                this.bankManager.updateBankError(bankError,appRequest);
            } else {
                bankError.setCreateDate(new Date());
                bankError.setOperatorId(bankErrorForm.getCurrentUser()
                        .getOperatorId().longValue());
                bankError.setOrgId(bankErrorForm.getCurrentUser().getOrg()
                        .getOrgId());
                bankError.setLoginOrgId(OperatorSessionHelper.getLoginOrg(
                        request).getOrgId().longValue());
                
                this.bankManager.saveBankError(bankError,appRequest);
            }
        } catch (Exception e) {
            // 记录保存银行错误代码失败日志
            logger.log(request, actionCode,""+appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }

        // 操作日志
        logger.log(request, actionCode, ""+appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);

        return mapping.getInputForward();
    }

    /**
     * 新增托收信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.getInputForward();
    }

    /**
     * 禁用选中的错误代码信息，跳转到错误代码列表页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankErrorForm bankErrorForm = (BankErrorForm) form;
        String[] selectedIds = request.getParameterValues("selectedIds");
        
        String actionCode = ActionDefinition.SYS_DISABLE_BANKERROR;
        ////保存受理记录
        AppRequest appRequest = bankErrorForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        try {
            this.bankManager.disableBankErrors(selectedIds,appRequest);
        } catch (Exception e) {
            // 记录删除银行错误代码失败日志
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 启用银行托收错误代码
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward enable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankErrorForm bankErrorForm = (BankErrorForm) form;
        String[] selectedIds = request.getParameterValues("selectedIds");
        String actionCode = ActionDefinition.SYS_ENABLE_BANKERROR;
        ////保存受理记录
        AppRequest appRequest = bankErrorForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        try {
            this.bankManager.enableBankErrors(selectedIds, appRequest);
        } catch (Exception e) {
            // 记录删除银行错误代码失败日志
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 启用当前银行托收错误代码
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward enableBankError(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BankErrorForm bankErrorForm = (BankErrorForm) form;
        BankErrorDTO bankError = bankErrorForm.getBankError();
        String actionCode = ActionDefinition.SYS_ENABLE_BANKERROR;
        ////保存受理记录
        AppRequest appRequest = bankErrorForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        
        try {
            this.bankManager.enableBankErrors(new Long[] { bankError
                    .getErrorId() },appRequest);
        } catch (Exception e) {
            // 记录删除银行错误代码失败日志
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, ""+appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("bankError.do?act=edit&bankErrorId="
                        + bankError.getErrorId());
    }

    /**
     * 禁用当前银行托收错误代码
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disableBankError(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BankErrorForm bankErrorForm = (BankErrorForm) form;
        BankErrorDTO bankError = bankErrorForm.getBankError();
        String actionCode = ActionDefinition.SYS_DISABLE_BANKERROR;
        ////保存受理记录
        AppRequest appRequest = bankErrorForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        try {
            this.bankManager.disableBankErrors(new Long[] { bankError
                    .getErrorId() },appRequest);
        } catch (Exception e) {
            // 记录删除银行错误代码失败日志
            logger.log(request, actionCode, ""+ appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, ""+appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("bankError.do?act=edit&bankErrorId="
                        + bankError.getErrorId());
    }
}
