
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
 * 任务调度实现类。
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
    /** 日志. */
    private static final Log logger = LogFactory.getLog(SchedulerManager.class);

    /** 任务调度的工厂类. */
    private MonitoredSchedulerFactoryBean schedulerFactory;

    /** 任务的数据库管理类. */
    private TaskManager taskManager;

    /** 任务调度的实例. */
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
     * 启动暂停的任务.
     * 
     * @param taskTrigger
     */
    public void runTrigger(TaskTriggerDTO triggerDTO) {
        String jobName = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId();
        String groupName = Scheduler.DEFAULT_GROUP;

        try {
            // 根据jobName ,groupName再次启动该任务
            this.scheduler.resumeJob(jobName, groupName);
            // 修改trigger 的状态,并存入数据库
            triggerDTO.setStatus(TaskTriggerDTO.TRIGGER_STATUS_RUN);
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * 启动一个修改后的任务.
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
     * 启动一个重新设定运行规则的任务.
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
                // 根据jobName ,groupName 获取在 scheduler 中的 jobDetail

                jobDetail = scheduler.getJobDetail(name, groupName);
                if (jobDetail != null) {
                    jobDetail.getJobDataMap().put(JobProxy.JOB_TASK_NAME,
                            triggerDTO);

                    // 获取新的cronTrigger
                    CronTriggerBean cronTrigger = getCronTriggerBean(jobDetail,
                            triggerDTO.getTaskTrigger());

                    cronTrigger.setStartTime(new Date());
                    // 根据job 的 name, group 的name,和trigger 等参数来确定,需要启动的任务
                    scheduler.rescheduleJob(name, groupName, cronTrigger);

                    if (triggerDTO.getStatus().longValue() == TaskTriggerDTO.TRIGGER_STATUS_PAUSE) {
                        pauseTrigger(triggerDTO);
                    }
                }
            }
        } catch (SchedulerException e) {
            logger.info("重新设定任务的运行规则时出错");
            logger.error(e);
        }
    }

    /**
     * 根据jobDetail、taskTrigger 构建出CronTriggerBean对象.
     * 
     * @param jobDetail
     * @param taskTrigger
     * @return
     */
    public CronTriggerBean getCronTriggerBean(JobDetail jobDetail,
            TaskTrigger taskTrigger) {
        // 根据jobDefine 的ID，和trigger 的 ID 配置成唯一的 JobName
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
     * 启动某个任务.
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
     * 启用规则
     * 
     * @param triggerDTO
     */
    public void startTrigger(TaskTriggerDTO triggerDTO) {
        triggerDTO.setNeedStartup(TaskTriggerDTO.TRIGGER_START);
        CronTriggerBean cronTrigger = this.createCronTrigger(triggerDTO);
        cronTrigger.setStartTime(new Date());
        
        try {
            // 加入规则队列中,并启动
            scheduler.scheduleJob(cronTrigger.getJobDetail(), cronTrigger);
            this.taskManager.updateTrigger(triggerDTO);
        } catch (SchedulerException e) {
            logger.error(e);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * 禁用规则
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
     * 暂停某个任务.
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
     * 启动所有的调度.
     * 
     */
    @SuppressWarnings("unchecked")
	public void runAll() {
        try {
            List taskTriggers = null;
            List allTriggers = taskManager.selectAllTaskTrigger();
            taskTriggers = this.getCronTriggers(allTriggers);

            // 将List 集合转化成所需要的数组
            int size = taskTriggers.size();
            Trigger[] triggers = (Trigger[]) taskTriggers
                    .toArray(new Trigger[size]);
            schedulerFactory.setTriggers(triggers);
            scheduler.start();

            // 将所有的trigger 都放入队列中.并且启动.然后再根据trigger 的状态来判断,是否需要暂停
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
     * 构建出Trigger 集合.
     * 
     * @return
     */
    public List getCronTriggers(List taskTriggers) {
        List<CronTriggerBean> trigger = new ArrayList<CronTriggerBean>();

        for (Iterator iter = taskTriggers.iterator(); iter.hasNext();) {
            TaskTriggerDTO taskTrigger = (TaskTriggerDTO) iter.next();
            logger.info("taskTrigger id:" + taskTrigger.getTriggerId());

            CronTriggerBean cronTrigger = createCronTrigger(taskTrigger);

            // 判断规则是否过期;
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
     * 创建出CronTriggerBean对象.
     * 
     * @param triggerDTO
     * @param cronTrigger
     */
    @SuppressWarnings("unchecked")
	public CronTriggerBean createCronTrigger(TaskTriggerDTO triggerDTO) {
        String name = getJobName(triggerDTO.getTaskTrigger())
                + triggerDTO.getTriggerId().longValue();

        // 根据 name 、Group 、 jobClass 创建JobDetail
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
     * 创建Scheduler 对象，并将JobDetail 和 Trigger 放入队列.
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
     * 判断该任务是否存在于列队之中.
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
     * 获取已经启动的任务的信息.
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
     * 得到Job 实例的名字.
     * 
     * @param taskTrigger
     * @return
     */
    public String getJobName(TaskTrigger trigger) {
        return trigger.getJobId() + "";
    }

    /**
     * 获取正在运行的任务的信息.
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
     * 获取任务运行时的信息.
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
     * 根据job的id 号，来查询Job.
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
     * 根据trigger 的id 号，来查询Trigger.
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
     * 得到所有的trigger.
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
