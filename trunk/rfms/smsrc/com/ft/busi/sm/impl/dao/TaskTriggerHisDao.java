
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskTriggerHis;

/**
 * TaskTriggerHis ʵ�����ݷ�����
 * 
 * @spring.bean id="TaskTriggerHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskTriggerHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public TaskTriggerHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return TaskTriggerHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskTriggerHis getById(Serializable key) {
        return (TaskTriggerHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskTriggerHis loadById(Serializable key) {
        return (TaskTriggerHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ��ʷ��¼
     * 
     * @param triggerId
     * @return
     */
    public List findTriggerHistorybyTriggerId(Long triggerId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TaskTriggerHis tth ");
        hql.append(" where tth.").append(TaskTriggerHis.PROP_TRIGGER_ID);
        hql.append(" = ?");
        return this.query(hql.toString(), new Object[] { triggerId });
    }

    /**
     * ��������Id��ѯÿ�������ִ�д����������ִ��ʱ�� ��jobIdΪ����ͳ�����й���
     * 
     * @param jobId
     *            jobId
     * @return �������鼯�� [����ID,ִ�д���,���ִ��ʱ��]
     */
    public List findTriggerHistorybyTaskId(Long jobId) {
        StringBuffer hql = new StringBuffer();
        List<Long> param = new ArrayList<Long>();
        
        hql.append(" select new com.ft.sm.dto.TaskTriggerDTO");
        hql.append("(tth.triggerId,count(tth.hisId),max(tth.endTime))");
        hql.append(" from TaskTriggerHis tth");
        if (jobId != null) {
            hql.append(" where tth.").append(TaskTriggerHis.PROP_TASK_ID)
                    .append(" =? ");
            param.add(jobId);
        }
        hql.append(" group by tth.").append(TaskTriggerHis.PROP_TRIGGER_ID);
        List result = this.query(hql.toString(), param.toArray());
        return result;
    }
}