package com.ft.web.sm.priv.commission;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.CommissionManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.ResourceRepository;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 委托权限管理页面控制类.
 * 
 */

public class CommisionManagerAction extends BaseAction {
    private static Log logger = LogFactory.getLog(CommisionManagerAction.class);

    private CommissionManager commissionManager;

    private ResourceRepository resourceRepository;

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return searchCommissions(mapping, form, request, response);
    }

    /**
     * 查询委托记录
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchCommissions(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CommisionManagerForm cfForm = (CommisionManagerForm) form;

        String searchConsigneeName = cfForm.getSearchConsigneeName();
        String searchResourceName = cfForm.getSearchResourceName();
        Date startTime = cfForm.getStartDate();
        Date endTime = cfForm.getEndDate();

        OperatorDTO consigner = cfForm.getCurrentUser();

        List result = this.commissionManager.findCommissions(consigner
                .getOperatorId(), searchConsigneeName, searchResourceName,
                startTime, endTime);

        request.setAttribute("commissionList", result);
        return mapping.getInputForward();

    }

    /**
     * 回收权限
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reclaim(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_DELETE_COMMISION;
        String[] selectedIds = request.getParameterValues("selectedIds");
        StringBuffer strBuffer = new StringBuffer("");
        for (int i = 0; i < selectedIds.length; i++) {
            strBuffer.append(selectedIds[i] + ",");
        }
        try {
            commissionManager.removeCommission(selectedIds);
        } catch (Exception e) {
            // 记录回收失败日志
            logger.log(request, actionCode, strBuffer.toString(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + strBuffer.toString(), e);
            throw e;
        }

        // 记录操作日志
        logger.log(request, actionCode, strBuffer.toString(),
                ActionDefinition.ACTION_SUCCESS);
        // return getRedirectForwardAction("/sm/consign.do");
        return searchCommissions(mapping, form, request, response);
    }

    /**
     * 更新委托信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateCommission(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String actionCode = ActionDefinition.SYS_UPDATE_COMMISION;
        CommisionManagerForm cfForm = (CommisionManagerForm) form;
        ConsignPermitDTO cp = cfForm.getConsignPrivilege();
        try {
            this.commissionManager.updateCommission(cp);
        } catch (Exception e) {
            // 记录更新失败日志
            logger.log(request, actionCode, cp.getConsignId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + cp.getConsignId() + "", e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, cp.getConsignId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return searchCommissions(mapping, form, request, response);
    }


    public ActionForward editCommission(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommisionManagerForm cfForm = (CommisionManagerForm) form;
        ConsignPermitDTO cp = cfForm.getConsignPrivilege();
        Long resourceId = new Long(cp.getResourceId());
        ResourceDTO resource = this.resourceRepository.getResource(resourceId);
        cfForm.setResourceName(resource.getTitle());
        return mapping.findForward("edit");
    }

    /**
     * @spring.property ref="commissionManager"
     * @param commissionManager
     *                The commissionManager to set.
     */
    public void setCommissionManager(CommissionManager commissionManager) {
        this.commissionManager = commissionManager;
    }

    /**
     * @spring.property ref = "resourceRepository"
     * 
     * @param resourceRepository
     */
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
}
