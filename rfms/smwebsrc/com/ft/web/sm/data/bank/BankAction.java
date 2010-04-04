package com.ft.web.sm.data.bank;

import java.util.Date;

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
import com.ft.sm.dto.BankDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * ������Ϣҳ�������
 * 
 * 
 * @struts.action path="/bank" name="bankForm" scope="request" validate="false"
 *                parameter="act" input="sm.bank.index"
 * 
 * @struts.action-forward name="create" path="sm.bank.create"
 * 
 * @struts.action-forward name="edit" path="sm.bank.edit"
 * 
 * @struts.tiles name="sm.bank.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/bank/listBank.jsp"
 * 
 * @spring.bean id="bankAction"
 * 
 * @version 1.0
 */
public class BankAction extends BaseAction {

    // ��¼��־��Ϣ
    private static Log logger = LogFactory.getLog(BankAction.class);

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
     * ��ת������ҳ��
     * 
     * @struts.tiles name="sm.bank.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/bank/editBank.jsp"
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankForm bankForm = (BankForm) form;
        bankForm.getBank().setBankType(-1);
        return mapping.findForward("create");
    }

    /**
     * ������Ϣ
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
        BankForm bankForm = (BankForm) form;
        BankDTO bank = bankForm.getBank();
        
        String actionCode = ActionDefinition.SYS_ADD_BANK;
        if (null != bank.getBankId() && bank.getBankId().longValue() >0){
            actionCode = ActionDefinition.SYS_UPDATE_BANK;
        }
        
        //���������¼
        AppRequest appRequest = bankForm.getAppRequest(request, actionCode);
        appService.saveApp(appRequest);
        
        try {
            //��������
            if (null != bank.getBankId() && bank.getBankId().longValue() >0) {
                bankManager.updateBank(bank,appRequest);
            //��������
            }else{
                bank.setCreateDate(new Date());
                bank.setOperatorId(bankForm.getCurrentUser().getOperatorId().longValue());
                bank.setOrgId(bankForm.getCurrentUser().getOrg().getOrgId());
                bank.setLoginOrgId(OperatorSessionHelper.getLoginOrg(request).getOrgId().longValue());
                bank.setAppId(appRequest.getAppId());
                bankManager.saveBank(bank,appRequest);
            }
        }catch (Exception e) {
            // ��¼������Ϣ����ʧ����־
            logger.log(request, actionCode,""+appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        
        // ������־
        logger.log(request, actionCode, ""+appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ��ת����ʾ������Ϣҳ��
     * 
     * @struts.tiles name="sm.bank.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/bank/editBank.jsp"
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * ��ѯ
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
     * ����ѡ�е����м�¼
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
        BankForm bankForm = (BankForm) form;
        String[] selectedIds = request.getParameterValues("selectedIds");

        String actionCode = ActionDefinition.SYS_DISABLE_BANK;
        ////���������¼
        AppRequest appRequest = bankForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        
        try {
            this.bankManager.disableBanks(selectedIds,appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ����ѡ�е����м�¼
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
        BankForm bankForm = (BankForm) form;
        String[] selectedIds = request.getParameterValues("selectedIds");
       
        String actionCode = ActionDefinition.SYS_ENABLE_BANK;
        ////���������¼
        AppRequest appRequest = bankForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        
        try {
            this.bankManager.enableBanks(selectedIds,appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return mapping.getInputForward();
    }

    /**
     * ���õ�ǰ������Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward enableBank(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankForm bankForm = (BankForm) form;
        BankDTO bank = bankForm.getBank();

        String actionCode = ActionDefinition.SYS_ENABLE_BANK;
        ////���������¼
        AppRequest appRequest = bankForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        
        try {
            this.bankManager.enableBanks(new Long[] { bank.getBankId() },appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error(
                    "Excute action " + actionCode + " failed,action sequence ="
                            + appRequest.getAppId(), e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return this.getRedirectForwardAction("bank.do?act=edit&bankId="
                + bank.getBankId());
    }

    /**
     * ���õ�ǰ������Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disableBank(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BankForm bankForm = (BankForm) form;
        BankDTO bank = bankForm.getBank();

        String actionCode = ActionDefinition.SYS_DISABLE_BANK;
        ////���������¼
        AppRequest appRequest = bankForm.getAppRequest(request, actionCode);
        //appService.saveApp(appRequest);
        
        try {
            this.bankManager.disableBanks(new Long[] { bank.getBankId() },appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, ""+appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error(
                    "Excute action " + actionCode + " failed,action sequence ="
                            + appRequest.getAppId(), e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, ""+appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return this.getRedirectForwardAction("bank.do?act=edit&bankId="
                + bank.getBankId());
    }
}
