package com.ft.busi.sm.impl.task;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.ft.busi.sm.model.TaskManager;
import com.ft.busi.sm.task.TaskJob;
import com.ft.busi.sm.task.TaskJobException;
import com.ft.sm.dto.TaskJobParamDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.dto.TaskTriggerHisDTO;
import com.ft.sm.entity.TaskParamDefine;
import com.ft.spring.web.SpringContextUtils;

/**
 * Job ����ʱ�Ĵ�����.
 * 
 */
public class JobProxy implements StatefulJob {
    /** ��־. */
	
    private static final Log logger = LogFactory.getLog(JobProxy.class);

    /** trigger Ĭ�Ϸ��뵽�����е�����. */
    public static final String JOB_TASK_NAME = "taskTrigger";

    /**
     * Job ��ִ��.
     */
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        TaskTriggerDTO taskTrigger = (TaskTriggerDTO) context.getJobDetail()
                .getJobDataMap().get(JOB_TASK_NAME);
        TaskManager taskManager = (TaskManager) SpringContextUtils
                .getBean("taskManager");

        logger.info("Begin execute task,trigger id "
                + taskTrigger.getTriggerId());

        TaskTriggerHisDTO triggerHisDTO = new TaskTriggerHisDTO();
        triggerHisDTO.setStartTime(new Date());
        triggerHisDTO.setTaskId(taskTrigger.getJobId());
        triggerHisDTO.setTriggerId(taskTrigger.getTriggerId().longValue());
        triggerHisDTO.setTaskName(taskTrigger.getTaskJob().getJobName());
        triggerHisDTO.setClassName(taskTrigger.getTaskJob().getJobClassname());
        triggerHisDTO.setTriggerDesc(taskTrigger.getDescription());

        Object[] objs = { new Long(0), new String(), new Boolean(true),
                new Date() };
        try {
            taskManager.saveOrUpdateTriggerHis(triggerHisDTO);
            TaskJob taskJob = createTaskJob(taskTrigger);
            TaskJobContextImpl impl = new TaskJobContextImpl(context);
            List params = taskTrigger.getParams();
            /*
             * ����JOB �еĲ���
             */
            for (Iterator iter = params.iterator(); iter.hasNext();) {
                TaskJobParamDTO element = (TaskJobParamDTO) iter.next();
                TaskParamDefine define = element.getParamDefine();

                // ���ݲ��������ͣ���ֵת�������Ӧ����
                Object obj = ConvertUtils.convert(element.getValue(),
                        objs[Integer.parseInt(define.getParamType() + "") - 1]
                                .getClass());

                logger.info(obj);
                impl.setParameter(element.getParamDefine().getParamName(), obj);
            }
            try {
                taskJob.execute(impl); // ���о����JOB
            } catch (TaskJobException jobException) {
                logger.error("Execute task failed,trigger id "
                        + taskTrigger.getTriggerId(), jobException);
            } finally {
                // �ж������Ƿ�Ϊֻ����һ��
                if (taskTrigger.getCronType() == 4) {
                    taskTrigger
                            .setNeedStartup(TaskTriggerDTO.TRIGGER_MISS_FIRE); // ��������ΪʧЧ
                    taskManager.updateTrigger(taskTrigger);
                }
                triggerHisDTO.setEndTime(new Date());
                taskManager.saveOrUpdateTriggerHis(triggerHisDTO);
            }

            logger.info("Execute task success,trigger id "
                    + taskTrigger.getTriggerId());
        } catch (Exception e) {
            logger.error("Execute task failed,trigger id "
                    + taskTrigger.getTriggerId(), e);
        }
    }

    /**
     * ���������Job.
     * 
     * @param taskTrigger
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public TaskJob createTaskJob(TaskTriggerDTO taskTrigger)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        TaskJob taskJob = null;
        String className = taskTrigger.getTaskJob().getJobClassname();
        Class jobClass = Class.forName(className); // ���ַ�����ת����Class
        Object obj = jobClass.newInstance(); // ����ʵ��

        if (obj instanceof TaskJob) { // �жϸö�����TaskJob����Ϊͬһ����
            taskJob = (TaskJob) obj;
        }

        return taskJob;
    }
}
