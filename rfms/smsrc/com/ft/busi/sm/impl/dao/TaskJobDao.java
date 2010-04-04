
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskJob;

/**
 * TaskJob ʵ�����ݷ�����
 * 
 * @spring.bean id="TaskJobDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskJobDao extends BaseDao {

    /**
     * ���캯��
     */
    public TaskJobDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return TaskJob.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskJob getById(Serializable key) {
        return (TaskJob) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskJob loadById(Serializable key) {
        return (TaskJob) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ���������������
     * 
     * @param jobId
     *                ����ID
     * @return TaskParamDefineʵ���б�
     */
    public List findJobParamDefines(Long jobId) {
        String hql = "from TaskParamDefine param where param.jobId = ?";
        return this.query(hql, new Object[] { jobId });
    }

    /**
     * ��������Ĺ���
     * 
     * @param id
     *                ����id
     * @return TaskTriggerDTOʵ���б�
     */
    public List findTriggerByTaskID(Long id) {
        StringBuffer hql = new StringBuffer("select new ");
        hql.append(" com.ft.sm.dto.TaskTriggerDTO(trigger,job)");
        hql.append(" from TaskTrigger trigger,TaskJob job");
        hql.append(" where trigger.jobId = job.jobId");
        hql.append(" and job.jobId = ?");
        return this.query(hql.toString(), new Object[] { id });
    }

    /**
     * ���ҹ������
     * 
     * @param triggerId
     *                ����id
     * @return TaskJobParamDTOʵ���б�
     */
    public List findTaskJobParam(Long triggerId) {
        StringBuffer hql = new StringBuffer("select new ");
        hql
                .append(" com.ft.sm.dto.TaskJobParamDTO(param,paramDefine)");
        hql.append(" from TaskJobParam param,TaskParamDefine paramDefine ");
        hql.append(" where param.triggerId = ?");
        hql.append(" and param.paramDefineid = paramDefine.paramDefineid");
        return this.query(hql.toString(), new Object[] { triggerId });
    }
}