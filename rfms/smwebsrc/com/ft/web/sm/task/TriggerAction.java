package com.ft.web.sm.task;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.impl.EntityDTOConverter;
import com.ft.busi.sm.model.SchedulerManager;
import com.ft.busi.sm.model.TaskManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.task.corn.CronEntityFactory;
import com.ft.common.task.corn.DailyCronEntity;
import com.ft.common.task.corn.FixedCronEntity;
import com.ft.common.task.corn.HourlyCronEntity;
import com.ft.common.task.corn.MonthlyCronEntity;
import com.ft.common.task.corn.OneTimeCronEntity;
import com.ft.common.task.corn.WeeklyCronEntity;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskJobParamDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskJobParam;
import com.ft.sm.entity.TaskParamDefine;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 计划管理Action类
 * 
 * 
 * @spring.bean id="triggerAction"
 * 
 * @struts.action name="taskForm" path="/trigger" scope="request"
 *                input="sm.task.trigger.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.task.trigger.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/task/triggerIndex.jsp"
 * 
 * 
 * @struts.action-forward name="taskCron" path="sm.task.trigger"
 * 
 * @struts.action-forward name="triggerAdd" path="sm.trigger.add"
 * 
 * @struts.action-forward name="triggerEdit" path="sm.trigger.edit"
 * 
 * @struts.action-forward name="triggerHis" path="sm.trigger.history"
 * 
 */
public class TriggerAction extends BaseAction {

    private static final Log logger = LogFactory.getLog(TaskAction.class);

    private TaskManager taskManager;

    private SchedulerManager schedulerManager;

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.trigger.history" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/triggerHistory.jsp"
     * 
     */
    public ActionForward history(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        List triggerHis = taskManager.findTriggerHistorybyTriggerId(taskForm
                .getTaskTrigger().getTriggerId());
        request.setAttribute("triggerHis", triggerHis);
        return mapping.findForward("triggerHis");
    }

    /**
     * @struts.tiles name="sm.task.trigger" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/taskCron.jsp"
     * 
     */
    public ActionForward trigger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;

        // List triggers =
        // taskManager.findTriggerByTaskID(taskForm.getJobDefine()
        // .getJobId());
        List triggers = taskManager.findTriggerWithHistoryByTaskID(taskForm
                .getJobDefine().getJobId());

        request.setAttribute("triggers", triggers);

        return mapping.findForward("taskCron");
    }

    /**
     * 
     * @struts.tiles name="sm.trigger.add" extends="main.layout"
     * 
     * @struts.tiles-put name="body" value="/sm/task/triggerEdit.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward newTrigger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        List params = taskManager.findJobParamDefines(taskForm.getJobDefine()
                .getJobId());
        taskForm.getJobDefine().getParamDefines().addAll(params);
        return mapping.findForward("triggerAdd");
    }

    /**
     * 
     * @struts.tiles name="sm.trigger.edit" extends ="main.layout"
     * 
     * @struts.tiles-put name="body" value="/sm/task/triggerEdit.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward editTrigger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TaskForm taskForm = (TaskForm) form;
        // String param = request.getParameter("triggerId");
        // int id = Integer.parseInt(param);
        // TaskTrigger trigger = schedulerManager.findTriggerById(new Long(id));
        TaskTriggerDTO triggerDTO = taskForm.getTaskTrigger();
        List params = taskManager.findTaskJobParam(triggerDTO.getTriggerId());
        triggerDTO.getParams().addAll(params);
        TaskJobDTO jobDTO = new TaskJobDTO(triggerDTO.getTaskJob());
        List jobParams = taskManager.findJobParamDefines(jobDTO.getJobId());
        jobDTO.getParamDefines().addAll(jobParams);
        taskForm.setJobDefine(jobDTO);
        taskForm.setCronType(triggerDTO.getCronType());
        this.parseCronString(taskForm, request); // 解析运行规则
        return mapping.findForward("triggerEdit");
    }

    /**
     * 删除一个运行规则.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward delTrigger(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // String id = request.getParameter("triggerId");
        // TaskTrigger trigger = this.schedulerManager.findTriggerById(new Long(
        // Integer.parseInt(id)));
        TaskForm taskForm = (TaskForm) form;
        TaskTriggerDTO triggerDTO = taskForm.getTaskTrigger();
        taskForm.setJobDefine(new TaskJobDTO(triggerDTO.getTaskJob()));
        try {
            if (TaskTriggerDTO.TRIGGER_STATUS_RUN == triggerDTO.getStatus()
                    .longValue()) {
                this.schedulerManager.pauseTrigger(triggerDTO);
            }
            this.taskManager.deleteTrigger(triggerDTO);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_TRIGGER, triggerDTO
                    .getTriggerId().toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DELETE_TRIGGER
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DELETE_TRIGGER, triggerDTO
                .getTriggerId().toString(), ActionDefinition.ACTION_SUCCESS);
        return getRedirectForwardAction("trigger.do?act=trigger&jobId="
                + taskForm.getJobDefine().getJobId().longValue());
    }

    /**
     * 保存运行规律.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward saveCron(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Enumeration paramenum = request.getParameterNames();
        TaskForm taskForm = (TaskForm) form;

        // 获取运行规则
        this.configCronEntity(taskForm, request);

        TaskTriggerDTO triggerDTO = taskForm.getTaskTrigger();
        triggerDTO.setCronType(taskForm.getCronType());
        triggerDTO.setCronString(taskForm.getCronEntity().getCronString());

        String appearValue = taskForm.getCronEntity().ConfApperValue(
                triggerDTO.getCronString());

        triggerDTO.setDescription(appearValue);
        triggerDTO.setTaskJob(taskForm.getJobDefine().getTaskJob());
        // triggerDTO.set

        if (triggerDTO.getNeedStartup() == TaskTriggerDTO.TRIGGER_START) {
            triggerDTO.setStatus(TaskTriggerDTO.TRIGGER_STATUS_RUN);
        } else {
            triggerDTO.setNeedStartup(TaskTriggerDTO.TRIGGER_STOP);
            triggerDTO.setStatus(TaskTriggerDTO.TRIGGER_STATUS_PAUSE);
        }

        String actionCode = "";

        try {

            /*
             * 根据trigger的Id号来判断，该对象是否为新的对象
             */
            if (triggerDTO.getTriggerId().longValue() == 0) {
                this.addInstanceValue(paramenum, taskForm, request);
                Long triggerId = taskManager.addTrigger(triggerDTO);
                if (triggerDTO.getTriggerId() == null
                        || triggerDTO.getTriggerId().longValue() == 0) {
                    triggerDTO.setTriggerId(triggerId);
                }
                logger.info("taskForm cronType :" + taskForm.getCronType());
                if (triggerDTO.getStatus().longValue() == TaskTriggerDTO.TRIGGER_STATUS_RUN) {
                    this.schedulerManager.startTask(triggerDTO);
                }
                actionCode = ActionDefinition.SYS_ADD_TRIGGER;
            } else {
                actionCode = ActionDefinition.SYS_UPDATE_TRIGGER;
                // 获取所定义的参数被修改后的值,并更新到数据库中
                this.updateInstanceValue(paramenum, taskForm, request);
                taskManager.updateTrigger(triggerDTO);
                taskManager.updateTriggerParams(EntityDTOConverter
                        .converDTO2Entity(triggerDTO.getParams()));
                /*
                 * 判断该trigger所对应的任务,是否存在于队列中 如果存在则重新启动,反之则重新配置,然后再启动
                 */

                if (triggerDTO.getStatus().longValue() == TaskTriggerDTO.TRIGGER_STATUS_RUN) {
                    if (this.schedulerManager.checkExist(triggerDTO
                            .getTaskTrigger())) {
                        this.schedulerManager.reschedulerTask(triggerDTO);
                    } else {
                        this.schedulerManager.startTask(triggerDTO);
                    }
                }else{
                    if (this.schedulerManager.checkExist(triggerDTO
                            .getTaskTrigger())) {
                        this.schedulerManager.pauseTrigger(triggerDTO);
                    }
                }

            }

        } catch (Exception ex) {
            logger.log(request, actionCode, triggerDTO.getTriggerId()
                    .toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, actionCode, triggerDTO.getTriggerId().toString(),
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("trigger.do?act=trigger&jobId="
                + taskForm.getJobDefine().getJobId());
    }

    /**
     * 根据运行规则解析成相应的对象 并将该对象的属性值,传给页面.
     * 
     * @param taskForm
     * @param request
     */
    private void parseCronString(TaskForm taskForm, HttpServletRequest request) {
        long cronType = taskForm.getCronType();
        String cronString = taskForm.getTaskTrigger().getCronString();

        switch (Integer.parseInt(cronType + "")) {
        case 1:

            DailyCronEntity daily = (DailyCronEntity) CronEntityFactory
                    .createCoreEntity(1);
            daily.ConfApperValue(cronString);

            request.setAttribute("dailydayOrWeekPeriod", new Long(daily
                    .getDailydayOrWeekPeriod()));

            request.setAttribute("dailyrunDayTime", daily.getDailyrunDayTime());

            break;

        case 2:

            WeeklyCronEntity week = (WeeklyCronEntity) CronEntityFactory
                    .createCoreEntity(2);
            week.ConfApperValue(cronString);

            request.setAttribute("weeklydayOrWeekPeriod", week
                    .getWeeklydayOrWeekPeriod());

            request
                    .setAttribute("weeklyrunDayTime", week
                            .getWeeklyrunDayTime());

            break;

        case 3:

            MonthlyCronEntity month = (MonthlyCronEntity) CronEntityFactory
                    .createCoreEntity(3);
            month.ConfApperValue(cronString);

            request.setAttribute("monthlydayOrWeekPeriod", month
                    .getMonthlydayOrWeekPeriod());

            request.setAttribute("monthlymonthsEnum", month
                    .getMonthlymonthsEnum());

            request.setAttribute("monthlyrunDayTime", month
                    .getMonthlyrunDayTime());

            break;

        case 4:

            OneTimeCronEntity once = (OneTimeCronEntity) CronEntityFactory
                    .createCoreEntity(4);
            once.ConfApperValue(cronString);
            request.setAttribute("onetimerunDayTime", once
                    .getOnetimerunDayTime());

            break;

        case 5:

            FixedCronEntity fixd = (FixedCronEntity) CronEntityFactory
                    .createCoreEntity(5);
            fixd.ConfApperValue(cronString);

            request.setAttribute("fixedrateperiod", new Long(fixd
                    .getFixedrateperiod()));

            break;

        case 6:

            HourlyCronEntity hour = (HourlyCronEntity) CronEntityFactory
                    .createCoreEntity(6);
            hour.ConfApperValue(cronString);

            request.setAttribute("hourlyRunTime", new Long(hour
                    .getHourlyrateperiod()));

            break;
        }
    }

    /**
     * 配置运行规律. 根据页面上传过来的属性值,配置成相应的对象
     * 
     * @param taskForm
     * @param request
     */
    private void configCronEntity(TaskForm taskForm, HttpServletRequest request) {
        long cronType = taskForm.getCronType();

        switch (Integer.parseInt(cronType + "")) {
        case 1:

            DailyCronEntity daily = (DailyCronEntity) CronEntityFactory
                    .createCoreEntity(1);
            String dailydayOrWeekPeriod = request
                    .getParameter("dailydayOrWeekPeriod");
            String dailyrunDayTime = request.getParameter("dailyrunDayTime");
            daily.setDailydayOrWeekPeriod(Integer
                    .parseInt(dailydayOrWeekPeriod));
            daily.setDailyrunDayTime(dailyrunDayTime);
            taskForm.setCronEntity(daily);

            break;

        case 2:

            String[] weeklydayOrWeekPeriod = request
                    .getParameterValues("weeklydayOrWeekPeriod");
            String weeklyrunDayTime = request.getParameter("weeklyrunDayTime");
            WeeklyCronEntity week = (WeeklyCronEntity) CronEntityFactory
                    .createCoreEntity(2);

            week.setWeeklydayOrWeekPeriod(weeklydayOrWeekPeriod);
            week.setWeeklyrunDayTime(weeklyrunDayTime);
            taskForm.setCronEntity(week);

            break;

        case 3:

            String[] monthlydayOrWeekPeriod = request
                    .getParameterValues("monthlydayOrWeekPeriod");
            String[] monthlymonthsEnum = request
                    .getParameterValues("monthlymonthsEnum");
            String monthlyrunDayTime = request
                    .getParameter("monthlyrunDayTime");
            MonthlyCronEntity month = (MonthlyCronEntity) CronEntityFactory
                    .createCoreEntity(3);

            month.setMonthlydayOrWeekPeriod(monthlydayOrWeekPeriod);
            month.setMonthlymonthsEnum(monthlymonthsEnum);
            month.setMonthlyrunDayTime(monthlyrunDayTime);
            taskForm.setCronEntity(month);

            break;

        case 4:

            String onetimerunDayTime = request
                    .getParameter("onetimerunDayTime");
            OneTimeCronEntity once = (OneTimeCronEntity) CronEntityFactory
                    .createCoreEntity(4);

            once.setOnetimerunDayTime(onetimerunDayTime);
            taskForm.setCronEntity(once);

            break;

        case 5:

            String fixedrateperiod = request.getParameter("fixedrateperiod");
            FixedCronEntity fixd = (FixedCronEntity) CronEntityFactory
                    .createCoreEntity(5);

            fixd.setFixedrateperiod(Integer.parseInt(fixedrateperiod));
            taskForm.setCronEntity(fixd);

            break;
        case 6:

            String hourlyRunTime = request.getParameter("hourlyRunTime");
            HourlyCronEntity hourly = (HourlyCronEntity) CronEntityFactory
                    .createCoreEntity(6);

            hourly.setHourlyrateperiod(Integer.parseInt(hourlyRunTime));
            taskForm.setCronEntity(hourly);

            break;
        }
    }

    /**
     * 根据页面发过来的请求，得到页面上所有的请求参数， 再根据参数的值，判断出哪些是所需要的参数列表中的value值.
     * 
     * @param paramenum
     * @param taskForm
     * @param request
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	private void addInstanceValue(Enumeration paramenum, TaskForm taskForm,
            HttpServletRequest request) throws Exception {
        // Set paramDefines = taskForm.getJobDefine().getParamDefines();
        // Set jobParams = new HashSet();
        List paramDefines = taskManager.findJobParamDefines(taskForm
                .getJobDefine().getJobId());

        while (paramenum.hasMoreElements()) {
            String paramName = (String) paramenum.nextElement();

            if (paramName.startsWith("InstanceValue_")) {
                String params = paramName.substring("InstanceValue_".length());
                String value = request.getParameter(paramName).trim();
                if (StringUtils.isNotEmpty(value)) {
                    int id = Integer.parseInt(params);

                    for (Iterator iter = paramDefines.iterator(); iter
                            .hasNext();) {
                        TaskParamDefine element = (TaskParamDefine) iter.next();

                        if (element.getParamDefineid() == id) {
                            TaskJobParam jobParam = new TaskJobParam();
                            jobParam.setParamValue(value);
                            jobParam.setParamDefineid(element
                                    .getParamDefineid());
                            jobParam.setTriggerId(taskForm.getTaskTrigger()
                                    .getTriggerId().longValue());

                            TaskJobParamDTO paramDTO = new TaskJobParamDTO(
                                    jobParam, element);
                            taskForm.getTaskTrigger().getParams().add(paramDTO);
                        }
                    }
                }

            }
        }
    }

    /**
     * 修改参数列表中的value 值.
     * 
     * @param paramenum
     * @param taskForm
     * @param request
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	private void updateInstanceValue(Enumeration paramenum, TaskForm taskForm,
            HttpServletRequest request) throws Exception {
        // Set paramDefines = taskForm.getTaskTrigger().getParams();
        List paramDefines = taskManager.findTaskJobParam(taskForm
                .getTaskTrigger().getTriggerId());
        if (paramDefines.isEmpty()) {
            this.addInstanceValue(paramenum, taskForm, request);
        } else {
            while (paramenum.hasMoreElements()) {
                String paramName = (String) paramenum.nextElement();
                if (paramName.startsWith("InstanceValue_")) {
                    String params = paramName.substring("InstanceValue_"
                            .length());
                    String value = request.getParameter(paramName).trim();
                    if (StringUtils.isNotEmpty(value)) {
                        int id = Integer.parseInt(params);

                        for (Iterator iter = paramDefines.iterator(); iter
                                .hasNext();) {
                            TaskJobParamDTO element = (TaskJobParamDTO) iter
                                    .next();
                            if (element.getParamId().longValue() == id) {
                                element.setValue(value);
                            }
                            taskForm.getTaskTrigger().getParams().add(element);
                        }
                    }
                }
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
