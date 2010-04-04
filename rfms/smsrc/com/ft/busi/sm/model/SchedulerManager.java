package com.ft.busi.sm.model;

import java.util.List;

import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskTrigger;

/**
 * ������Ƚӿڡ�
 * 
 * @ejb.client jndiName="ejb/sm/schedulerManager" id="schedulerManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface SchedulerManager {
    /**
     * ������ͣ������.
     * 
     * @param taskTrigger
     */
    public void runTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ����һ���޸ĺ������.
     * 
     * @param jobDTO
     */
    public void reschedulerTask(TaskJobDTO jobDTO) throws Exception;
    
    /**
     * ����һ�������趨���й��������.
     * 
     * @param trigger
     */
    public void reschedulerTask(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ����jobDetail��taskTrigger ������CronTriggerBean����.
     * 
     * @param jobDetail
     * @param taskTrigger
     * @return
     */
    public CronTriggerBean getCronTriggerBean(JobDetail jobDetail,
            TaskTrigger taskTrigger) throws Exception;
    /**
     * ����ĳ������.
     * 
     * @param triggerDTO
     */
    public void startTask(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ���ù���
     * 
     * @param triggerDTO
     */
    public void startTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ���ù���
     * 
     * @param triggerDTO
     */
    public void stopTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ��ͣĳ������.
     * 
     * @param taskTrigger
     */
    public void pauseTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ������Trigger ����.
     * 
     * @return
     */
    public List getCronTriggers(List taskTriggers) throws Exception;
    
    /**
     * ������CronTriggerBean����.
     * 
     * @param triggerDTO
     * @param cronTrigger
     */
    public CronTriggerBean createCronTrigger(TaskTriggerDTO triggerDTO) throws Exception;
    
    /**
     * ����Scheduler ���󣬲���JobDetail �� Trigger �������.
     * 
     * @param scheduler
     * @param triggers
     */
    public void createScheduleJob(List taskTriggers) throws Exception;
    
    /**
     * �жϸ������Ƿ�������ж�֮��.
     * 
     * @param taskTrigger
     * @return
     */
    public boolean checkExist(TaskTrigger taskTrigger) throws Exception;
    
    /**
     * ��ȡ�Ѿ��������������Ϣ.
     * 
     * @return
     */
    public List getAllTriggers() throws Exception;
    
    /**
     * ��ѯ���й���
     * @param jobName
     * @param cronType
     * @param triggerStatus
     * @return
     * @throws Exception
     */
    public List searchTriggers(String jobName,Long cronType,Long triggerStatus) throws Exception;
    
    /**
     * �õ�Job ʵ��������.
     * 
     * @param taskTrigger
     * @return
     */
    public String getJobName(TaskTrigger trigger) throws Exception ;
    
    /**
     * ��ȡ�������е��������Ϣ.
     * 
     * @return
     */
    public List getRunTriggers() throws Exception;
    
    /**
     * ��ȡ��������ʱ����Ϣ.
     * 
     * @param taskTriggers
     * @param triggersStatus
     * @return
     */
    public List getTriggers(List taskTriggers, List triggersStatus) throws Exception;
    
    /**
     * ����job��id �ţ�����ѯJob.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) throws Exception;

    /**
     * ����trigger ��id �ţ�����ѯTrigger.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) throws Exception;

    /**
     * �õ����е�trigger.
     * 
     * @return
     */
    public List selectAllTriggers() throws Exception;
 }
