package com.ft.web.sm.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.SchedulerManager;
import com.ft.busi.sm.model.TaskManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.dto.TaskTriggerLogDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 任务监控Action类
 * 
 * 
 * @spring.bean id="schedulerAction"
 * 
 * @struts.tiles name="sm.scheduler.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/task/trigger.jsp"
 * 
 * @struts.action name="taskForm" path="/scheduler" scope="request"
 *                input="sm.scheduler.index" parameter="act" validate="false"
 * 
 */
public class SchedulerAction extends BaseAction {
    /** 日志 . */
    private static final Log logger = LogFactory.getLog(SchedulerAction.class);

    private SchedulerManager schedulerManager;

    private TaskManager taskManager;
    
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        this.getAllTrigger(request);
        return mapping.getInputForward();
    }

    /**
     * 启动被暂停的任务.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        TaskTriggerDTO trigger = taskForm.getTaskTrigger();
        try {
            this.schedulerManager.runTrigger(trigger);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_RUN_TRIGGER, trigger
                    .getJobId()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_RUN_TRIGGER
                    + " failed,action sequence = ", ex);
            
            
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_RUN_TRIGGER, trigger
                .getJobId()
                + "", ActionDefinition.ACTION_SUCCESS);
        
        TaskTriggerLogDTO triggerLogDTO = new TaskTriggerLogDTO();
        OperatorDTO opDTO = OperatorSessionHelper.getLoginOp(request);
        triggerLogDTO.getTriggerLog().setOperatorId(opDTO.getOperator().getOperatorId());
        triggerLogDTO.getTriggerLog().setTriggerStart(TaskTriggerDTO.TRIGGER_START);
        triggerLogDTO.getTriggerLog().setOperDate(new java.util.Date());
        triggerLogDTO.getTriggerLog().setTriggerId(trigger.getTaskTrigger().getTriggerId());
        triggerLogDTO.getTriggerLog().setActionResult(TaskTriggerLogDTO.ACTION_SUCCESS);
        AppRequest appRequest = this.getAppRequest(request);
        appRequest.setAppAction(ActionDefinition.SYS_RUN_TRIGGER);
        this.taskManager.saveTaskTriggerLog(triggerLogDTO,appRequest);
        
        return this.getRedirectForwardAction("/sm/scheduler.do");
    }

    /**
     * 暂停规则.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward pause(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        TaskTriggerDTO triggerDTO = taskForm.getTaskTrigger();
        try {
            this.schedulerManager.pauseTrigger(triggerDTO);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_PAUSE_TRIGGER, triggerDTO
                    .getTriggerId().toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_PAUSE_TRIGGER
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_PAUSE_TRIGGER, triggerDTO
                .getTriggerId().toString(), ActionDefinition.ACTION_SUCCESS);

        //保存日志信息
        TaskTriggerLogDTO triggerLogDTO = new TaskTriggerLogDTO();
        OperatorDTO opDTO = OperatorSessionHelper.getLoginOp(request);
        triggerLogDTO.getTriggerLog().setOperatorId(opDTO.getOperator().getOperatorId());
        triggerLogDTO.getTriggerLog().setTriggerStart(TaskTriggerDTO.TRIGGER_STOP);
        triggerLogDTO.getTriggerLog().setOperDate(new java.util.Date());
        triggerLogDTO.getTriggerLog().setTriggerId(triggerDTO.getTaskTrigger().getTriggerId());
        triggerLogDTO.getTriggerLog().setActionResult(TaskTriggerLogDTO.ACTION_SUCCESS);
        AppRequest appRequest = this.getAppRequest(request);
        appRequest.setAppAction(ActionDefinition.SYS_PAUSE_TRIGGER);
        this.taskManager.saveTaskTriggerLog(triggerLogDTO,appRequest);
        
        this.getAllTrigger(request);
        return this.getRedirectForwardAction("/sm/scheduler.do");
    }

    /**
     * 禁用规则
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward stop(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        TaskTriggerDTO trigger = taskForm.getTaskTrigger();
        trigger.setNeedStartup(TaskTriggerDTO.TRIGGER_STOP);
        try {
            schedulerManager.stopTrigger(trigger);

        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_STOP_TRIGGER, trigger
                    .getTriggerId().toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_STOP_TRIGGER
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_STOP_TRIGGER, trigger
                .getTriggerId().toString(), ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("/sm/trigger.do?act=trigger&jobId="
                + trigger.getJobId());
    }

    /**
     * 启用规则
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward start(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        TaskTriggerDTO trigger = taskForm.getTaskTrigger();
        trigger.setNeedStartup(TaskTriggerDTO.TRIGGER_START);
        try {
            schedulerManager.startTrigger(trigger);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_START_TRIGGER, trigger
                    .getTriggerId().toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_START_TRIGGER
                    + " failed,action sequence = ", ex);
            throw ex;
        }

        logger.log(request, ActionDefinition.SYS_START_TRIGGER, trigger
                .getTriggerId().toString(), ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("/sm/trigger.do?act=trigger&jobId="
                + trigger.getJobId());
    }

    /**
     * 任务查询.
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
        TaskForm taskForm = (TaskForm) form;
        List allTriggers =  schedulerManager.searchTriggers(taskForm.getJobName(), new Long(
                taskForm.getCronType()), new Long(taskForm.getTriggerStatus()));
        request.setAttribute("allTriggers", allTriggers);
        return mapping.getInputForward();
    }

    /**
     * 得到任务的运行状态，并把值传到页面中.
     * 
     * @param request
     */
    public void getAllTrigger(HttpServletRequest request) throws Exception{
        List allTriggers = this.schedulerManager.getAllTriggers();
        request.setAttribute("allTriggers", allTriggers);
    }
    
    public AppRequest getAppRequest(HttpServletRequest request) {
        AppRequest appRequest = new AppRequest();
        OperatorDTO currentUser = OperatorSessionHelper.getLoginOp(request);
        appRequest.setOperatorId(currentUser.getOperatorId().longValue());
        appRequest.setOrgId(currentUser.getOrg().getOrgId());
        appRequest.setLoginOrgId(OperatorSessionHelper.getLoginOrg(request)
                .getOrgId().longValue());
        appRequest.setAppAction("");
        return appRequest;
    }
    
    /**
     * @spring.property ref="schedulerManager"
     * @param schedulerManager
     *            The schedulerManager to set.
     */
    public void setSchedulerManager(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }
    
    /**
     * @spring.property ref="taskManager"
     * @param taskManager
     */
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
}
