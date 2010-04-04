package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.dto.TaskTriggerHisDTO;
import com.ft.sm.dto.TaskTriggerLogDTO;

/**
 * 任务相关实体管理接口。
 * 
 */
public interface TaskManager extends EntityManager {
    /**
     * 增加一个Job。
     * 
     * @param jobDefine
     */
    public Long addJob(TaskJobDTO jobDefine) throws Exception;

    /**
     * 增加某个任务的运行规则。
     * 
     * @param taskTrigger
     */
    public Long addTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * 修改jobDefine 的信息。
     * 
     * @param jobDefine
     */
    public void updateJob(TaskJobDTO jobDefine) throws Exception;

    /**
     * 修改运行规则.
     * 
     * @param taskTrigger
     */
    public void updateTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * 删除一个任务.
     * 
     * @param jobDefine
     */
    public void deleteTask(TaskJobDTO jobDefine) throws Exception;

    /**
     * 删除某个任务的运行规则.
     * 
     * @param taskTrigger
     * 
     */
    public void deleteTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * 根据jobId 查询任务.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) throws Exception;

    /**
     * 根据id 查询运行规则.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) throws Exception;

    /**
     * 根据Job的Id 查询Trigger.
     * 
     * @param id
     * @return
     */
    public List findTriggerByTaskID(Long id) throws Exception;

    /**
     * 获取所有的JOB.
     * 
     * @return
     */
    public List selectAllTasks() throws Exception;

    /**
     * 获取所有的有效的triggers.
     * 
     * @return
     */
    public List selectAllTaskTrigger() throws Exception;

    /**
     * 查找任务参数
     * 
     * @param jobId
     *                任务id
     * @return
     */
    public List findJobParamDefines(Long jobId) throws Exception;

    /**
     * 查找运行规则的参数
     * 
     * @param triggerId
     *                规则ID
     * @return
     */
    public List findTaskJobParam(Long triggerId) throws Exception;

    /**
     * 更新规则的参数
     * 
     * @param params
     *                参数集合
     */
    public void updateTriggerParams(List params) throws Exception;
    
    /**
     * 保存或更新
     * @param triggerHisDTO
     * @throws Exception
     */
    public void saveOrUpdateTriggerHis(TaskTriggerHisDTO triggerHisDTO)  throws Exception;

    /**
     * 根据规则Id查询规则历史
     * @param triggerId
     * @return  TaskTriggerHis集合
     */
    public List findTriggerHistorybyTriggerId(Long triggerId) throws Exception ;

    /**
     *  根据任务Id查询规则  包括规则的执行次数，和最后执行时间。id为空则查询所有启用的规则
     * @param jobId
     */
    public List findTriggerWithHistoryByTaskID(Long jobId) throws Exception ;

    /**查询规则
     * @param jobName  任务名称
     * @param cronType  规则类型
     * @param triggerStatus  规则状态
     * @return
     */
    public List searchTrigger(String jobName, Long cronType, Long triggerStatus) throws Exception ;

    /**
     * 保存或者更新类别
     * @param category
     */
    public Long saveOrUpdateCategory(TaskCategoryDTO category) throws Exception ;
    
    /**
     * 删除任务类别
     * @param category
     */
    public void deleteCategory(TaskCategoryDTO category) throws Exception ;

    /**
     * 查找所有任务类别
     */
    public List findAllCategorys() throws Exception ;
    
    /**
     * 保存任务启动，暂停日志
     * @throws Exception
     */
    public void saveTaskTriggerLog(TaskTriggerLogDTO triggerLogDTO,AppRequest appRequest) throws Exception ;

    /**
     * 根据AppId来获取任务执行计划日志
     * @param appId
     * @return
     * @throws Exception
     */
    public TaskTriggerLogDTO getTaskTriggerLogDTOByAppId(Long appId) throws Exception;
}
