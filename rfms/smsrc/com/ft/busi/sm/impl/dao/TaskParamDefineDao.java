
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskParamDefine;

/**
 * TaskParamDefine ʵ�����ݷ�����
 * 
 * @spring.bean id="TaskParamDefineDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskParamDefineDao extends BaseDao {

    /**
     * ���캯��
     */
    public TaskParamDefineDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return TaskParamDefine.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskParamDefine getById(Serializable key) {
        return (TaskParamDefine) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskParamDefine loadById(Serializable key) {
        return (TaskParamDefine) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * ��������IDɾ����������
     * @param taskId
     */
    public void deleteTaskParamDefineByTaskId(Long taskId){
        StringBuffer hql = new StringBuffer();
        hql.append(" from TaskParamDefine tpd ");
        hql.append(" where tpd.").append(TaskParamDefine.PROP_JOB_ID).append("=?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{taskId});
    }
}