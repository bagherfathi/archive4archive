package com.ft.web.sm.task;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.busi.sm.model.SchedulerManager;
import com.ft.busi.sm.model.TaskManager;
import com.ft.common.enumdata.EnumRepository;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskParamDefine;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.web.sm.priv.op.OperatorAction;

/**
 * �������Action��
 * 
 * 
 * @spring.bean id="taskAction"
 * 
 * @struts.tiles name="sm.task.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/task/task.jsp"
 * @struts.tiles-put name="title" value="�������"
 * 
 * @struts.action name="taskForm" path="/task" scope="request"
 *                input="sm.task.index" parameter="act" validate="false"
 * 
 * @struts.action-forward name="taskEdit" path="sm.task.edit"
 * 
 * @struts.action-forward name="taskAdd" path="sm.task.add"
 * 
 * 
 */
public class TaskAction extends BaseAction {

    private static Log logger = LogFactory.getLog(OperatorAction.class);

    /** ��������ݿ������. */
    private TaskManager taskManager;

    /** ����ĵ��ȹ�����. */
    private SchedulerManager schedulerManager;

    /**
     * ��������Ϣ���浽���ݿ���.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Enumeration paramenum = request.getParameterNames();

        TaskForm taskForm = (TaskForm) form;
        TaskJobDTO jobDTO = taskForm.getJobDefine();
        String actionCode = "";
        Long id = jobDTO.getJobId();
        try {
            // �ж�task�Ƿ���id��ȷ���Ƿ�Ϊ������
            if (jobDTO.getJobId().longValue() == 0) {
                actionCode = ActionDefinition.SYS_ADD_TASK;
                jobDTO.setCreateTime(new Date());
                this.addParameter(paramenum, taskForm, request); // ���ò���
                id = this.taskManager.addJob(jobDTO); // �޸����ݿ��е�����,���������

            } else {
                actionCode = ActionDefinition.SYS_UPDATE_TASK;
                this.addParameter(paramenum, taskForm, request); // ���ò���
                this.taskManager.updateJob(jobDTO);
                this.schedulerManager.reschedulerTask(jobDTO); // �����������޸ĺ������
            }
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_TASK, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_ADD_TASK
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, actionCode, id.toString(),
                ActionDefinition.ACTION_SUCCESS);

        return mapping.getInputForward();
    }

    /**
     * ɾ��һ������.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        TaskJobDTO taskJob = taskForm.getJobDefine();

        /*
         * ɾ������֮ǰ���жϸ������Ƿ���������������ˣ���Ӧ����ֹͣ����
         */
        List triggers = taskManager.findTriggerByTaskID(taskJob.getJobId());
        try {
            if (triggers.size() != 0) {
                for (Iterator iter = triggers.iterator(); iter.hasNext();) {
                    TaskTriggerDTO triggerDTO = (TaskTriggerDTO) iter.next();

                    if (TaskTriggerDTO.TRIGGER_STATUS_RUN == triggerDTO
                            .getStatus().longValue()) { // ���trigger
                        // ��״̬Ϊ������,����ֹͣ
                        this.schedulerManager.pauseTrigger(triggerDTO);
                    }
                    // �����ݿ���������ɾ��taskTrigger
                    //this.taskManager.deleteTrigger(triggerDTO);
                }
            }
              //ɾ��jobDefine
            this.taskManager.deleteTask(taskJob);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_TASK, taskJob.getJobId().toString(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DELETE_TASK
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DELETE_TASK, taskJob.getJobId().toString(),
                ActionDefinition.ACTION_SUCCESS);


        return mapping.getInputForward();
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List categorys = this.taskManager.findAllCategorys();
        request.setAttribute("categorys", categorys);
        EnumRepository er=(EnumRepository)SpringBeanUtils.getBean("enumRepository");
        request.setAttribute("enum",er.getElement("param_type@SM_TASK_JOB"));
        return mapping.getInputForward();
    }

    /**
     * 
     * @struts.tiles name="sm.task.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/taskAdd.jsp"
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List categorys = this.taskManager.findAllCategorys();
        request.setAttribute("categorys", categorys);
    	EnumRepository er=(EnumRepository)SpringBeanUtils.getBean("enumRepository");
        request.setAttribute("enum",er.getElement("param_type@SM_TASK_JOB"));
        return mapping.findForward("taskAdd");
    }

    /**
     * 
     * @struts.tiles name="sm.task.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/taskAdd.jsp"
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        List params = taskManager.findJobParamDefines(taskForm.getJobDefine()
                .getJobId());
        taskForm.getJobDefine().getParamDefines().addAll(params);
        List categorys = this.taskManager.findAllCategorys();
        request.setAttribute("categorys", categorys);
        EnumRepository er=(EnumRepository)SpringBeanUtils.getBean("enumRepository");
        request.setAttribute("enum",er.getElement("param_type@SM_TASK_JOB"));
        return mapping.findForward("taskEdit");
    }

    /**
     * ���Ӳ���.
     * 
     * @param paramenum
     * @param taskForm
     * @param request
     */
    @SuppressWarnings("unchecked")
	private void addParameter(Enumeration paramenum, TaskForm taskForm,
            HttpServletRequest request) {
        while (paramenum.hasMoreElements()) {
            String paramName = (String) paramenum.nextElement();

            if (paramName.startsWith("paramaterNode")) {
                String params = paramName.substring("paramaterNode[".length());

                int splitLen = "paramaterNode[".length();
                int len = params.indexOf("[");
                int les = "[desc]".length();

                // get the name between paramaterNode[ [desc
                String name = paramName.substring(splitLen, splitLen + len);
                String desc = paramName.substring(splitLen + len + les,
                        paramName.length() - 1);

                log.info(name + " - " + request.getParameter(paramName));

                // ����ȡ���Ĳ��������õ� TaskJobParamDefine ������
                TaskParamDefine jobParamDefine = new TaskParamDefine();
                jobParamDefine.setParamName(name);
                jobParamDefine.setParamDesc(desc);

                long type = Integer.parseInt(request.getParameter(paramName));
                jobParamDefine.setParamType(type);
                taskForm.getJobDefine().getParamDefines().add(jobParamDefine);
            }
        }
    }

    /**
     * @spring.property ref="taskManager"
     * @param taskManager
     *            The taskManager to set.
     */
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * @spring.property ref="schedulerManager"
     * @param schedulerManager
     *            The schedulerManager to set.
     */
    public void setSchedulerManager(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }
}
