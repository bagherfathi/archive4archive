package com.ft.web.sm.maintain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.MaintainManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.MaintainPlanDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 系统维护Action类
 * 
 * @spring.bean id="maintainPlanAction"
 * 
 * @struts.action name="maintainPlanForm" path="/maintainPlan" scope="request"
 *                input="sm.maintainPlan.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.maintainPlan.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/maintain/listPlan.jsp"
 * 
 * @struts.action-forward name="create" path="sm.maintain.plan.create"
 * 
 * @struts.action-forward name="edit" path="sm.maintain.plan.edit"
 * 
 * @version 1.0
 */
public class MaintainPlanAction extends BaseAction {

    private static Log logger = LogFactory.getLog(MaintainPlanAction.class);

    private MaintainManager maintainManager;

    /**
     * @spring.property ref="maintainManager"
     * @param maintainManager
     *                The maintainManager to set.
     */
    public void setMaintainManager(MaintainManager maintainManager) {
        this.maintainManager = maintainManager;
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 创建新的系统维护计划
     * 
     * @struts.tiles name="sm.maintain.plan.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/maintain/addPlan.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getMethod().equals("GET")) {
            // 跳转到新建系统维护计划页面
            return mapping.findForward("create");
        }
        // 操作编码：新增系统维护计划
        String actionCode = ActionDefinition.SYS_ADD_MAINTAIN_PLAN;
        MaintainPlanForm maintainPlanForm = (MaintainPlanForm) form;
        MaintainPlanDTO maintainPlan = maintainPlanForm.getMaintainPlan();
        try {
            // 新建系统维护计划
            maintainManager.saveMaintainPlan(maintainPlan);
        } catch (Exception e) {
            // 记录新建系统维护计划失败日志
            logger.log(request, actionCode, maintainPlan.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainPlan.getName(), e);
            throw e;
        }
        // 记录新增成功的操作日志
        logger.log(request, actionCode, maintainPlan.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 跳转到编辑页面
     * 
     * @struts.tiles name="sm.maintain.plan.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/maintain/editPlan.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 编辑系统维护计划信息
     * 
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
        // 操作编码：更新系统维护计划
        String actionCode = ActionDefinition.SYS_UPDATE_MAINTAIN_PLAN;
        MaintainPlanForm maintainPlanForm = (MaintainPlanForm) form;
        MaintainPlanDTO maintainPlan = maintainPlanForm.getMaintainPlan();
        try {
            // 更新系统维护计划信息
            maintainManager.updateMaintainPlan(maintainPlan);
        } catch (Exception e) {
            // 记录更新系统维护计划失败日志
            logger.log(request, actionCode, maintainPlan.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainPlan.getName(), e);
            throw e;
        }
        // 记录更新成功的操作日志
        logger.log(request, actionCode, maintainPlan.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 删除系统维护计划信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 操作编码：删除系统维护计划
        String actionCode = ActionDefinition.SYS_DELETE_MAINTAIN_PLAN;
        MaintainPlanForm maintainPlanForm = (MaintainPlanForm) form;
        MaintainPlanDTO maintainPlan = maintainPlanForm.getMaintainPlan();
        try {
            // 删除系统维护计划
            maintainManager.deleteMaintainPlan(maintainPlan.getPlanId());
        } catch (Exception e) {
            // 记录删除系统维护计划失败日志
            logger.log(request, actionCode, maintainPlan.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainPlan.getName(), e);
            throw e;
        }
        // 记录删除成功的操作日志
        logger.log(request, actionCode, maintainPlan.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }
}