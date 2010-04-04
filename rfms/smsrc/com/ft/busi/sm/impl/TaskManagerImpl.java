package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.busi.dto.AppDTO;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.sm.impl.dao.TaskCategoryDao;
import com.ft.busi.sm.impl.dao.TaskJobDao;
import com.ft.busi.sm.impl.dao.TaskJobParamDao;
import com.ft.busi.sm.impl.dao.TaskParamDefineDao;
import com.ft.busi.sm.impl.dao.TaskTriggerDao;
import com.ft.busi.sm.impl.dao.TaskTriggerHisDao;
import com.ft.busi.sm.impl.dao.TaskTriggerLogDao;
import com.ft.busi.sm.model.TaskManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskJobParamDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.dto.TaskTriggerHisDTO;
import com.ft.sm.dto.TaskTriggerLogDTO;
import com.ft.sm.entity.TaskCategory;
import com.ft.sm.entity.TaskJob;
import com.ft.sm.entity.TaskParamDefine;
import com.ft.sm.entity.TaskTrigger;

/**
 * 任务相关实体管理实现类。
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="taskManager" parent="transactionProxyFactoryBean"
 *                  target="taskManagerImpl"
 * 
 * @spring.bean id="taskManagerImpl"
 */
public class TaskManagerImpl implements TaskManager {

    private TaskJobDao taskJobDao;

    private TaskTriggerDao taskTriggerDao;

    private TaskTriggerHisDao taskTriggerHisDao;

    private TaskJobParamDao taskJobParamDao;

    private TaskParamDefineDao taskParamDefineDao;

    private TaskCategoryDao taskCategoryDao;
    
    private TaskTriggerLogDao taskTriggerLogDao;
    
    @SuppressWarnings("unused")
	private BusiAppService appService;

    /**
     * 增加一个Job.
     * 
     * @param jobDefine
     */
    public Long addJob(TaskJobDTO jobDTO) {
        this.taskJobDao.batchDelete(this.taskJobDao.findJobParamDefines(jobDTO
                .getJobId()));
        List params = jobDTO.getParamDefines();
        this.taskJobDao.saveOrUpdate(jobDTO.getTaskJob());
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            TaskParamDefine element = (TaskParamDefine) iter.next();
            element.setJobId(jobDTO.getJobId().longValue());
        }
        this.taskJobDao.batchSave(params);
        return jobDTO.getJobId();
    }

    /**
     * 增加某个任务的运行规则.
     * 
     * @param taskTrigger
     */
    public Long addTrigger(TaskTriggerDTO triggerDTO) {
        List params = triggerDTO.getParams();
        TaskTrigger trigger = triggerDTO.getTaskTrigger();
        this.taskTriggerDao.saveOrUpdate(trigger);
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            TaskJobParamDTO element = (TaskJobParamDTO) iter.next();
            element.setTaskTriggerId(new Long(trigger.getTriggerId()));
        }
        this.taskJobDao.batchSave(EntityDTOConverter.converDTO2Entity(params));
        return new Long(trigger.getTriggerId());
    }

    /**
     * 修改jobDefine 的信息.
     * 
     * @param jobDefine
     */
    public void updateJob(TaskJobDTO taskJobDTO) {
        this.taskJobDao.batchDelete(this.taskJobDao
                .findJobParamDefines(taskJobDTO.getJobId()));
        List params = taskJobDTO.getParamDefines();
        this.taskJobDao.saveOrUpdate(taskJobDTO.getTaskJob());
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            TaskParamDefine element = (TaskParamDefine) iter.next();
            element.setJobId(taskJobDTO.getJobId().longValue());
        }
        this.taskJobDao.batchSave(params);
    }

    /**
     * 修改运行规则.
     * 
     * @param taskTrigger
     */
    public void updateTrigger(TaskTriggerDTO triggerDTO) {
        TaskTrigger trigger = triggerDTO.getTaskTrigger();
        this.taskJobDao.saveOrUpdate(trigger);
    }

    /**
     * 更新规则参数
     */
    public void updateTriggerParams(List params) {
        this.taskJobDao.batchSave(params);
    }

    /**
     * 删除一个任务.
     * 
     * @param jobDefine
     */
    public void deleteTask(TaskJobDTO jobDTO) {
        TaskJob taskJob = jobDTO.getTaskJob();
        taskParamDefineDao.deleteTaskParamDefineByTaskId(jobDTO.getJobId());
        this.taskJobDao.delete(taskJob);
    }

    /**
     * 删除某个任务的运行规则.
     * 
     * @param taskTrigger
     * 
     */
    public void deleteTrigger(TaskTriggerDTO triggerDTO) {
        TaskTrigger taskTrigger = triggerDTO.getTaskTrigger();
        taskJobParamDao
                .deleteTaskJobParamByTriggerId(triggerDTO.getTriggerId());
        this.taskTriggerDao.delete(taskTrigger);
    }

    /**
     * 根据jobId 查询任务.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) {
        TaskJob job = this.taskJobDao.getById(id);
        return new TaskJobDTO(job);
    }

    /**
     * 根据id 查询运行规则.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) {
        return this.taskTriggerDao.findTriggerById(id);
    }

    /**
     * 根据Job的Id 查询Trigger.
     * 
     * @param id
     * @return
     */
    public List findTriggerByTaskID(Long id) {
        return this.taskJobDao.findTriggerByTaskID(id);
    }

    public List findTriggerWithHistoryByTaskID(Long id) {
        List allTrigger = new ArrayList();
        List triggerWithHis = new ArrayList();
        if (id != null) {
            allTrigger = this.taskJobDao.findTriggerByTaskID(id);
            triggerWithHis = this.taskTriggerHisDao
                    .findTriggerHistorybyTaskId(id);
        } else {
            allTrigger = this.taskTriggerDao.findAll();
            triggerWithHis = this.taskTriggerHisDao
                    .findTriggerHistorybyTaskId(null);
        }
        for (Iterator iter = allTrigger.iterator(); iter.hasNext();) {
            TaskTriggerDTO element = (TaskTriggerDTO) iter.next();
            for (Iterator iterator = triggerWithHis.iterator(); iterator
                    .hasNext();) {
                TaskTriggerDTO trigger = (TaskTriggerDTO) iterator.next();
                if (element.getTriggerId().equals(trigger.getTriggerId())) {
                    element.setRunCount(trigger.getRunCount());
                    element.setLastRunTime(trigger.getLastRunTime());
                }
            }
        }
        return allTrigger;
    }

    /**
     * 获取所有的JOB.
     * 
     * @return
     */
    public List selectAllTasks() {
        return this.taskJobDao.findAll();
    }

    /**
     * 获取所有的有效的triggers.
     * 
     * @return
     */
    public List selectAllTaskTrigger() {
        return this.taskTriggerDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#findJobParamDefines(java.lang.Long)
     */
    public List findJobParamDefines(Long jobId) {
        return this.taskJobDao.findJobParamDefines(jobId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#findTaskJobParam(java.lang.Long)
     */
    public List findTaskJobParam(Long triggerId) {
        return this.taskJobDao.findTaskJobParam(triggerId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#saveOrUpdateTriggerHis(com.huashu.boss.sm.dto.TaskTriggerHisDTO)
     */

    public void saveOrUpdateTriggerHis(TaskTriggerHisDTO triggerHisDTO)
            throws Exception {
        this.taskTriggerHisDao.saveOrUpdate(triggerHisDTO.getTaskTrigger());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#findTriggerHistorybyTriggerId(java.lang.Long)
     */
    public List findTriggerHistorybyTriggerId(Long triggerId) {
        return taskTriggerHisDao.findTriggerHistorybyTriggerId(triggerId);
    }

    /**
     * @spring.property ref="TaskJobDao"
     * @param taskJobDao
     *            the taskJobDao to set
     */
    public void setTaskJobDao(TaskJobDao taskJobDao) {
        this.taskJobDao = taskJobDao;
    }

    /**
     * @spring.property ref="TaskTriggerDao"
     * @param taskTriggerDao
     *            the taskTriggerDao to set
     */
    public void setTaskTriggerDao(TaskTriggerDao taskTriggerDao) {
        this.taskTriggerDao = taskTriggerDao;
    }

    /**
     * @spring.property ref="TaskTriggerHisDao"
     * @param triggerHisDao
     *            the triggerHisDao to set
     */
    public void setTaskTriggerHisDao(TaskTriggerHisDao taskTriggerHisDao) {
        this.taskTriggerHisDao = taskTriggerHisDao;
    }

    /**
     * @spring.property ref="TaskJobParamDao"
     * @param taskJobParamDao
     *            the taskJobParamDao to set
     */
    public void setTaskJobParamDao(TaskJobParamDao taskJobParamDao) {
        this.taskJobParamDao = taskJobParamDao;
    }
    
    /**
     * @spring.property ref="TaskTriggerLogDao"
     * @param taskJobParamDao
     *            the taskJobParamDao to set
     */
    public void setTaskTriggerLogDao(TaskTriggerLogDao taskTriggerLogDao) {
        this.taskTriggerLogDao = taskTriggerLogDao;
    }

    /**
     * @spring.property ref="TaskParamDefineDao"
     * @param taskParamDefineDao
     *            the taskParamDefineDao to set
     */
    public void setTaskParamDefineDao(TaskParamDefineDao taskParamDefineDao) {
        this.taskParamDefineDao = taskParamDefineDao;
    }

    /**
     * @spring.property ref="TaskCategoryDao"
     * @param taskParamDefineDao
     *            the taskParamDefineDao to set
     */
    public void setTaskCategoryDao(TaskCategoryDao taskCategoryDao) {
        this.taskCategoryDao = taskCategoryDao;
    }
    
    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(TaskJobDTO.class)) {
            return new TaskJobDTO(this.taskJobDao.getById(id));
        }
        if (typeClass.equals(TaskTriggerDTO.class)) {
            return this.taskTriggerDao.findTriggerById(id);
        }
        if (typeClass.equals(TaskCategoryDTO.class)) {
            return new TaskCategoryDTO(this.taskCategoryDao.loadById(id));
        }
        return null;
    }

    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        if (typeClass.equals(TaskJob.class)) {
            List result = this.taskJobDao.query(hql, params);
            return EntityDTOConverter.converTaskJob2DTO(result);
        }
        if (typeClass.equals(TaskCategory.class)) {
            List result = this.taskCategoryDao.query(hql, params);
            return EntityDTOConverter.converTaskCategory2DTO(result);
        }

        return null;
    }

    public List loadByIds(Class typeClass, Serializable[] ids) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#searchTrigger(java.lang.String,
     *      java.lang.Long, java.lang.Long)
     */
    public List searchTrigger(String jobName, Long cronType, Long triggerStatus) {
        List triggers = taskTriggerDao.searchTrigger(jobName, cronType,
                triggerStatus);
        List triggerWithHis = this.taskTriggerHisDao
                .findTriggerHistorybyTaskId(null);
        for (Iterator iter = triggers.iterator(); iter.hasNext();) {
            TaskTriggerDTO element = (TaskTriggerDTO) iter.next();
            for (Iterator iterator = triggerWithHis.iterator(); iterator
                    .hasNext();) {
                TaskTriggerDTO trigger = (TaskTriggerDTO) iterator.next();
                if (element.getTriggerId().equals(trigger.getTriggerId())) {
                    element.setRunCount(trigger.getRunCount());
                    element.setLastRunTime(trigger.getLastRunTime());
                }
            }
        }
        return triggers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#saveOrUpdateCategory(com.huashu.boss.sm.dto.TaskCategoryDTO)
     */
    public Long saveOrUpdateCategory(TaskCategoryDTO category) {
        this.taskCategoryDao.saveOrUpdate(category.getCategory());
        return category.getCategoryId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#deleteCategory(com.huashu.boss.sm.dto.TaskCategoryDTO)
     */
    public void deleteCategory(TaskCategoryDTO category) {
        this.taskCategoryDao.delete(category.getCategory());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TaskManager#findAllCategorys()
     */
    public List findAllCategorys() throws Exception {
        return EntityDTOConverter.converTaskCategory2DTO(this.taskCategoryDao
                .findAll());
    }
    
    public void saveTaskTriggerLog(TaskTriggerLogDTO triggerLogDTO,AppRequest appRequest) throws Exception {
        if (null == triggerLogDTO) {
            throw new IllegalArgumentException();
        }
        BusiAppService appService = (BusiAppService)SpringBeanUtils.getBean("busiAppService");
        AppDTO app = appService.saveApp(appRequest);
        triggerLogDTO.getTriggerLog().setAppId(app.getApp().getAppId());
        this.taskTriggerLogDao.save(triggerLogDTO.getTriggerLog());
    }

    public TaskTriggerLogDTO getTaskTriggerLogDTOByAppId(Long appId) throws Exception{
        return this.taskTriggerLogDao.getTaskTriggerLogDTOByAppId(appId);
    }
}
