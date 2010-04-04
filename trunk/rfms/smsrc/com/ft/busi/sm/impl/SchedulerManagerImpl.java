
package com.ft.busi.sm.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.ft.busi.sm.impl.task.JobProxy;
import com.ft.busi.sm.impl.task.MonitoredSchedulerFactoryBean;
import com.ft.busi.sm.impl.task.SchedulerMonitor;
import com.ft.busi.sm.impl.task.SchedulerMonitor.TriggerStatus;
import com.ft.busi.sm.model.SchedulerManager;
import com.ft.busi.sm.model.TaskManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskTrigger;

/**
 * �������ʵ���ࡣ
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.aop-bean id="schedulerManager" parent="transactionProxyFactoryBean"
 *                  target="schedulerManagerImpl"
 * 
 * @spring.bean id="schedulerManagerImpl" init-method="runAll"
 */
public class SchedulerManagerImpl implements SchedulerManager{
    /** ��־. */
    private static final Log logger = LogFactory.getLog(SchedulerManager.class);

    /** ������ȵĹ�����. */
    private MonitoredSchedulerFactoryBean schedulerFactory;

    /** ��������ݿ������. */
    private TaskManager taskManager;

    /** ������ȵ�ʵ��. */
    private Scheduler scheduler;

    public SchedulerManagerImpl() {
        schedulerFactory = new MonitoredSchedulerFactoryBean();
        try {
            scheduler = schedulerFactory.createScheduler(
                    new StdSchedulerFactory(), "scheduler");
        } catch (SchedulerException e) {
            logger.error(e);
        }
    }

    /**
     * ������ͣ������.
     * 
     * @param taskTrigger
     */
    public void runTrigger(TaskTriggerDTO triggerDTO) {
        String jobName = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId();
        String groupName = Scheduler.DEFAULT_GROUP;

        try {
            // ����jobName ,groupName�ٴ�����������
            this.scheduler.resumeJob(jobName, groupName);
            // �޸�trigger ��״̬,���������ݿ�
            triggerDTO.setStatus(TaskTriggerDTO.TRIGGER_STATUS_RUN);
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * ����һ���޸ĺ������.
     * 
     * @param jobDTO
     */
    public void reschedulerTask(TaskJobDTO jobDTO) {
        List triggers = null;
        try {
            triggers = taskManager.findTriggerByTaskID(jobDTO.getJobId());
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }

        for (Iterator iter = triggers.iterator(); iter.hasNext();) {
            TaskTriggerDTO trigger = (TaskTriggerDTO) iter.next();
            reschedulerTask(trigger);

            if (trigger.getStatus().longValue() == TaskTriggerDTO.TRIGGER_STATUS_PAUSE) {
                pauseTrigger(trigger);
            }
        }
    }

    /**
     * ����һ�������趨���й��������.
     * 
     * @param trigger
     */
    public void reschedulerTask(TaskTriggerDTO triggerDTO) {
        String name = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId();

        JobDetail jobDetail = null;
        String groupName = Scheduler.DEFAULT_GROUP;

        try {
            if (triggerDTO.getNeedStartup() == TaskTriggerDTO.TRIGGER_START) {
                // ����jobName ,groupName ��ȡ�� scheduler �е� jobDetail

                jobDetail = scheduler.getJobDetail(name, groupName);
                if (jobDetail != null) {
                    jobDetail.getJobDataMap().put(JobProxy.JOB_TASK_NAME,
                            triggerDTO);

                    // ��ȡ�µ�cronTrigger
                    CronTriggerBean cronTrigger = getCronTriggerBean(jobDetail,
                            triggerDTO.getTaskTrigger());

                    cronTrigger.setStartTime(new Date());
                    // ����job �� name, group ��name,��trigger �Ȳ�����ȷ��,��Ҫ����������
                    scheduler.rescheduleJob(name, groupName, cronTrigger);

                    if (triggerDTO.getStatus().longValue() == TaskTriggerDTO.TRIGGER_STATUS_PAUSE) {
                        pauseTrigger(triggerDTO);
                    }
                }
            }
        } catch (SchedulerException e) {
            logger.info("�����趨��������й���ʱ����");
            logger.error(e);
        }
    }

    /**
     * ����jobDetail��taskTrigger ������CronTriggerBean����.
     * 
     * @param jobDetail
     * @param taskTrigger
     * @return
     */
    public CronTriggerBean getCronTriggerBean(JobDetail jobDetail,
            TaskTrigger taskTrigger) {
        // ����jobDefine ��ID����trigger �� ID ���ó�Ψһ�� JobName
        String name = getJobName(taskTrigger) + taskTrigger.getTriggerId();

        CronTriggerBean cronTrigger = new CronTriggerBean();
        cronTrigger.setJobDetail(jobDetail);
        cronTrigger.setJobName(name);
        cronTrigger.setJobGroup(jobDetail.getGroup());
        cronTrigger.setName(name);
        cronTrigger.setGroup(jobDetail.getGroup());
        cronTrigger.setVolatility(true);

        try {
            cronTrigger.setBeanName(name);
            cronTrigger.setCronExpression(taskTrigger.getTriggerCron());
        } catch (ParseException e) {
            logger.error(e);
        }

        return cronTrigger;
    }

    /**
     * ����ĳ������.
     * 
     * @param triggerDTO
     */
    public void startTask(TaskTriggerDTO triggerDTO) {
        CronTriggerBean cronTrigger = this.createCronTrigger(triggerDTO);

        cronTrigger.setStartTime(new Date());

        try {
            scheduler.scheduleJob(cronTrigger.getJobDetail(), cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        if (triggerDTO.getNeedStartup() == 0) {
            try {
                scheduler.pauseTrigger(cronTrigger.getName(), cronTrigger
                        .getGroup());
            } catch (SchedulerException e) {
                logger.error(e);
            }
        }
    }

    /**
     * ���ù���
     * 
     * @param triggerDTO
     */
    public void startTrigger(TaskTriggerDTO triggerDTO) {
        triggerDTO.setNeedStartup(TaskTriggerDTO.TRIGGER_START);
        CronTriggerBean cronTrigger = this.createCronTrigger(triggerDTO);
        cronTrigger.setStartTime(new Date());
        
        try {
            // ������������,������
            scheduler.scheduleJob(cronTrigger.getJobDetail(), cronTrigger);
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * ���ù���
     * 
     * @param triggerDTO
     */
    public void stopTrigger(TaskTriggerDTO triggerDTO) {
        triggerDTO.setNeedStartup(TaskTriggerDTO.TRIGGER_STOP);
        CronTriggerBean cronTrigger = this.createCronTrigger(triggerDTO);
        try {
            scheduler.deleteJob(cronTrigger.getName(), cronTrigger.getGroup());
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * ��ͣĳ������.
     * 
     * @param taskTrigger
     */
    public void pauseTrigger(TaskTriggerDTO triggerDTO) {
        String pauseTaskName = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId();
        String pauseGroupName = Scheduler.DEFAULT_GROUP;

        try {
            scheduler.pauseTrigger(pauseTaskName, pauseGroupName);
            triggerDTO.setStatus(TaskTriggerDTO.TRIGGER_STATUS_PAUSE);
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * �������еĵ���.
     * 
     */
    @SuppressWarnings("unchecked")
	public void runAll() {
        try {
            List taskTriggers = null;
            List allTriggers = taskManager.selectAllTaskTrigger();
            taskTriggers = this.getCronTriggers(allTriggers);

            // ��List ����ת��������Ҫ������
            int size = taskTriggers.size();
            Trigger[] triggers = (Trigger[]) taskTriggers
                    .toArray(new Trigger[size]);
            schedulerFactory.setTriggers(triggers);
            scheduler.start();

            // �����е�trigger �����������.��������.Ȼ���ٸ���trigger ��״̬���ж�,�Ƿ���Ҫ��ͣ
            createScheduleJob(taskTriggers);

            for (Iterator iter = allTriggers.iterator(); iter.hasNext();) {
                TaskTriggerDTO taskTrigger = (TaskTriggerDTO) iter.next();

                if (taskTrigger.getStatus().longValue() == 1) {
                    pauseTrigger(taskTrigger);
                }
            }
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * ������Trigger ����.
     * 
     * @return
     */
    public List getCronTriggers(List taskTriggers) {
        List<CronTriggerBean> trigger = new ArrayList<CronTriggerBean>();

        for (Iterator iter = taskTriggers.iterator(); iter.hasNext();) {
            TaskTriggerDTO taskTrigger = (TaskTriggerDTO) iter.next();
            logger.info("taskTrigger id:" + taskTrigger.getTriggerId());

            CronTriggerBean cronTrigger = createCronTrigger(taskTrigger);

            // �жϹ����Ƿ����;
            if (cronTrigger.getFireTimeAfter(new Date()) != null) {
                trigger.add(cronTrigger);
            } else {
                taskTrigger.setNeedStartup(TaskTriggerDTO.TRIGGER_MISS_FIRE);
                try {
                    taskManager.updateTrigger(taskTrigger);
                } catch (Exception e) {
                    throw new CommonRuntimeException("", e);
                }
            }
        }

        return trigger;
    }

    /**
     * ������CronTriggerBean����.
     * 
     * @param triggerDTO
     * @param cronTrigger
     */
    @SuppressWarnings("unchecked")
	public CronTriggerBean createCronTrigger(TaskTriggerDTO triggerDTO) {
        String name = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId().longValue();

        // ���� name ��Group �� jobClass ����JobDetail
        JobDetail jobDetail = new JobDetail(name, Scheduler.DEFAULT_GROUP,
                JobProxy.class);

        List params = null;
        try {
            params = taskManager.findTaskJobParam(triggerDTO.getTriggerId());
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
        triggerDTO.getParams().addAll(params);
        jobDetail.getJobDataMap().put(JobProxy.JOB_TASK_NAME, triggerDTO);

        return getCronTriggerBean(jobDetail, triggerDTO.getTaskTrigger());
    }

    /**
     * ����Scheduler ���󣬲���JobDetail �� Trigger �������.
     * 
     * @param scheduler
     * @param triggers
     */
    public void createScheduleJob(List taskTriggers) {
        for (Iterator iter = taskTriggers.iterator(); iter.hasNext();) {
            CronTriggerBean temp = (CronTriggerBean) iter.next();
            JobDetail jobDetail = temp.getJobDetail();
            temp.setStartTime(new Date());

            try {
                scheduler.scheduleJob(jobDetail, temp);
            } catch (SchedulerException e) {
                logger.error(e);
            }
        }
    }

    /**
     * �жϸ������Ƿ�������ж�֮��.
     * 
     * @param taskTrigger
     * @return
     */
    public boolean checkExist(TaskTrigger taskTrigger) {
        String name = getJobName(taskTrigger) + taskTrigger.getTriggerId();

        try {
            int status = scheduler.getTriggerState(name,
                    Scheduler.DEFAULT_GROUP);

            if (status != 5) {
                return true;
            }
        } catch (SchedulerException e) {
            logger.error(e);
        }

        return false;
    }

    /**
     * ��ȡ�Ѿ��������������Ϣ.
     * 
     * @return
     */
    public List getAllTriggers() {
        List triggerStatus = null;
        List triggers = new ArrayList();
        SchedulerMonitor monitor = schedulerFactory.getSchedulerMonitor();
        try {
            triggers = this.taskManager.findTriggerWithHistoryByTaskID(null);
            triggerStatus = monitor.getTriggerStatuses();
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception e1) {
            logger.error(e1);
        }
        return getTriggers(triggers, triggerStatus);
    }
    
    public List searchTriggers(String jobName,Long cronType,Long triggerStatus) {
        List runningTriggers = null;
        List triggers = new ArrayList();
        SchedulerMonitor monitor = schedulerFactory.getSchedulerMonitor();
        try {
            triggers = this.taskManager.searchTrigger(jobName,cronType,triggerStatus);
            runningTriggers = monitor.getTriggerStatuses();
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception e1) {
            logger.error(e1);
        }
        return getTriggers(triggers, runningTriggers);
    }

    /**
     * �õ�Job ʵ��������.
     * 
     * @param taskTrigger
     * @return
     */
    public String getJobName(TaskTrigger trigger) {
        return trigger.getJobId() + "";
    }

    /**
     * ��ȡ�������е��������Ϣ.
     * 
     * @return
     */
    public List getRunTriggers() {
        List taskTriggers = selectAllTriggers();
        List triggerStatus = null;

        SchedulerMonitor monitor = schedulerFactory.getSchedulerMonitor();

        try {
            triggerStatus = monitor.getRunTriggers();
        } catch (SchedulerException e) {
            logger.error(e);
        }

        return getTriggers(taskTriggers, triggerStatus);
    }

    /**
     * ��ȡ��������ʱ����Ϣ.
     * 
     * @param taskTriggers
     * @param triggersStatus
     * @return
     */
    public List getTriggers(List taskTriggers, List triggersStatus) {
        List<TaskTriggerDTO> result = new ArrayList<TaskTriggerDTO>();

        if(triggersStatus == null || triggersStatus.isEmpty()){
            return taskTriggers;
        }
        for (Iterator iter = taskTriggers.iterator(); iter.hasNext();) {
            TaskTriggerDTO taskTrigger = (TaskTriggerDTO) iter.next();

            for (Iterator its = triggersStatus.iterator(); its.hasNext();) {
                TriggerStatus element = (TriggerStatus) its.next();
                String name = getJobName(taskTrigger.getTaskTrigger())
                        + taskTrigger.getTriggerId();

                if (name.equals(element.getTaskName())) {
                    // taskTrigger.setRunStatus(element.getRunStatus());
                    result.add(taskTrigger);
                }
            }
        }

        return result;
    }

    /**
     * ����job��id �ţ�����ѯJob.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) {
        try {
            return taskManager.findJobById(id);
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    /**
     * ����trigger ��id �ţ�����ѯTrigger.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) {
        try {
            return taskManager.findTriggerById(id);
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    /**
     * �õ����е�trigger.
     * 
     * @return
     */
    public List selectAllTriggers() {
        try {
            return taskManager.selectAllTaskTrigger();
        } catch (Exception e) {
            throw new CommonRuntimeException("", e);
        }
    }

    /**
     * @return Returns the scheduler.
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * @param scheduler
     *            The scheduler to set.
     */
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
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
     * @return Returns the schedulerFactory.
     */
    public MonitoredSchedulerFactoryBean getSchedulerFactory() {
        return schedulerFactory;
    }

    /**
     * @param schedulerFactory
     *            The schedulerFactory to set.
     */
    public void setSchedulerFactory(
            MonitoredSchedulerFactoryBean schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }
}
