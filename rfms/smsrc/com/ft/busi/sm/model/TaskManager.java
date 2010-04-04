package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.dto.TaskTriggerHisDTO;
import com.ft.sm.dto.TaskTriggerLogDTO;

/**
 * �������ʵ�����ӿڡ�
 * 
 */
public interface TaskManager extends EntityManager {
    /**
     * ����һ��Job��
     * 
     * @param jobDefine
     */
    public Long addJob(TaskJobDTO jobDefine) throws Exception;

    /**
     * ����ĳ����������й���
     * 
     * @param taskTrigger
     */
    public Long addTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * �޸�jobDefine ����Ϣ��
     * 
     * @param jobDefine
     */
    public void updateJob(TaskJobDTO jobDefine) throws Exception;

    /**
     * �޸����й���.
     * 
     * @param taskTrigger
     */
    public void updateTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * ɾ��һ������.
     * 
     * @param jobDefine
     */
    public void deleteTask(TaskJobDTO jobDefine) throws Exception;

    /**
     * ɾ��ĳ����������й���.
     * 
     * @param taskTrigger
     * 
     */
    public void deleteTrigger(TaskTriggerDTO taskTrigger) throws Exception;

    /**
     * ����jobId ��ѯ����.
     * 
     * @param id
     * @return
     */
    public TaskJobDTO findJobById(Long id) throws Exception;

    /**
     * ����id ��ѯ���й���.
     * 
     * @param id
     * @return
     */
    public TaskTriggerDTO findTriggerById(Long id) throws Exception;

    /**
     * ����Job��Id ��ѯTrigger.
     * 
     * @param id
     * @return
     */
    public List findTriggerByTaskID(Long id) throws Exception;

    /**
     * ��ȡ���е�JOB.
     * 
     * @return
     */
    public List selectAllTasks() throws Exception;

    /**
     * ��ȡ���е���Ч��triggers.
     * 
     * @return
     */
    public List selectAllTaskTrigger() throws Exception;

    /**
     * �����������
     * 
     * @param jobId
     *                ����id
     * @return
     */
    public List findJobParamDefines(Long jobId) throws Exception;

    /**
     * �������й���Ĳ���
     * 
     * @param triggerId
     *                ����ID
     * @return
     */
    public List findTaskJobParam(Long triggerId) throws Exception;

    /**
     * ���¹���Ĳ���
     * 
     * @param params
     *                ��������
     */
    public void updateTriggerParams(List params) throws Exception;
    
    /**
     * ��������
     * @param triggerHisDTO
     * @throws Exception
     */
    public void saveOrUpdateTriggerHis(TaskTriggerHisDTO triggerHisDTO)  throws Exception;

    /**
     * ���ݹ���Id��ѯ������ʷ
     * @param triggerId
     * @return  TaskTriggerHis����
     */
    public List findTriggerHistorybyTriggerId(Long triggerId) throws Exception ;

    /**
     *  ��������Id��ѯ����  ���������ִ�д����������ִ��ʱ�䡣idΪ�����ѯ�������õĹ���
     * @param jobId
     */
    public List findTriggerWithHistoryByTaskID(Long jobId) throws Exception ;

    /**��ѯ����
     * @param jobName  ��������
     * @param cronType  ��������
     * @param triggerStatus  ����״̬
     * @return
     */
    public List searchTrigger(String jobName, Long cronType, Long triggerStatus) throws Exception ;

    /**
     * ������߸������
     * @param category
     */
    public Long saveOrUpdateCategory(TaskCategoryDTO category) throws Exception ;
    
    /**
     * ɾ���������
     * @param category
     */
    public void deleteCategory(TaskCategoryDTO category) throws Exception ;

    /**
     * ���������������
     */
    public List findAllCategorys() throws Exception ;
    
    /**
     * ����������������ͣ��־
     * @throws Exception
     */
    public void saveTaskTriggerLog(TaskTriggerLogDTO triggerLogDTO,AppRequest appRequest) throws Exception ;

    /**
     * ����AppId����ȡ����ִ�мƻ���־
     * @param appId
     * @return
     * @throws Exception
     */
    public TaskTriggerLogDTO getTaskTriggerLogDTOByAppId(Long appId) throws Exception;
}
