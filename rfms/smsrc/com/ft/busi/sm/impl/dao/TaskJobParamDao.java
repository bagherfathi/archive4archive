
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskJobParam;

/**
 * TaskJobParam ʵ�����ݷ�����
 * 
 * @spring.bean id="TaskJobParamDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskJobParamDao extends BaseDao {

    /**
     * ���캯��
     */
    public TaskJobParamDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return TaskJobParam.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskJobParam getById(Serializable key) {
        return (TaskJobParam) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TaskJobParam loadById(Serializable key) {
        return (TaskJobParam) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * ���ݹ���IDɾ���������
     * @param triggerId
     */
    public void deleteTaskJobParamByTriggerId(Long triggerId){
        StringBuffer hql =  new StringBuffer();
        hql.append(" from TaskJobParam tjp ");
        hql.append(" where tjp.").append(TaskJobParam.PROP_TRIGGER_ID).append(" = ?");
        this.deleteFromQuery(hql.toString(), new Object[]{triggerId});
    }
    
}