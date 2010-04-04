package com.ft.busi.sm.model;

import java.util.List;

import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskTrigger;

/**
 * 任务调度接口。
 * 
 * @ejb.client jndiName="ejb/sm/schedulerManager" id="schedulerManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface SchedulerManager {
    /**
     * 启动暂停的任务.
     * 
     * @param taskTrigger
     */
    public void runTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 启动一个修改后的任务.
     * 
     * @param jobDTO
     */
    public void reschedulerTask(TaskJobDTO jobDTO) throws Exception;
    
    /**
     * 启动一个重新设定运行规则的任务.
     * 
     * @param trigger
     */
    public void reschedulerTask(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 根据jobDetail、taskTrigger 构建出CronTriggerBean对象.
     * 
     * @param jobDetail
     * @param taskTrigger
     * @return
     */
    public CronTriggerBean getCronTriggerBean(JobDetail jobDetail,
            TaskTrigger taskTrigger) throws Exception;
    /**
     * 启动某个任务.
     * 
     * @param triggerDTO
     */
    public void startTask(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 启用规则
     * 
     * @param triggerDTO
     */
    public void startTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 禁用规则
     * 
     * @param triggerDTO
     */
    public void stopTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 暂停某个任务.
     * 
     * @param taskTrigger
     */
    public void pauseTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 构建出Trigger 集合.
     * 
     * @return
     */
    public List getCronTriggers(List taskTriggers) throws Exception;
    
    /**
     * 创建出CronTriggerBean对象.
     * 
     * @param triggerDTO
     * @param cronTrigger
     */
    public CronTriggerBean createCronTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * 创建Scheduler 对象，并将JobDetail 和 Trigger 放入队列.
     * 
     * @param scheduler
     * @param triggers
     */
    public void createScheduleJob(List taskTriggers) throws Exception;
    
    /**
     * 判断该任务是否存在于列队之中.
     * 
     * @param taskTrigger
     * @return
     */
    public boolean checkExist(TaskTrigger taskTrigger) throws Exception;
    
    /**
     * 获取已经启动的任务的信息.
     * 
     * @return
     */
    public List getAllTriggers() throws Exception;
    
    /**
     * 查询运行规则。
     * @param jobName
     * @param cronType
     * @param triggerStatus
     * @return
     * @throws Exception
     */
    public List searchTriggers(String jobName,Long cronType,Long triggerStatus) throws Exception;
    
    /**
     * 得到Job 实例的名字.
     * 
     * @param taskTrigger
     * @return
     */
    public String getJobName(TaskTrigger trigger) throws Exception ;
    
    /**
     * 获取正在运行的任务的信息.
     * 
     * @return
     */
    public List getRunTriggers() throws Exception;
    
    /**
     * 获取任务运行时的信息.
     * 
     * @param taskTriggers
     * @param triggersStatus
     * @return
     */
    public List getTriggers(List taskTriggers, List triggersStatus) throws Exception;
    
    /**
     * 根据job的id 号，来查询Job.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) throws Exception;

    /**
     * 根据trigger 的id 号，来查询Trigger.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) throws Exception;

    /**
     * 得到所有的trigger.
     * 
     * @return
     */
    public List selectAllTriggers() throws Exception;
 }
