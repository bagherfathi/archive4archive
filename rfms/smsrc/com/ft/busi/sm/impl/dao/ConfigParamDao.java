package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.sm.entity.ConfigParam;

/**
 * ConfigParam ʵ�����ݷ�����
 * 
 * @spring.bean id="ConfigParamDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ConfigParamDao extends BaseDao {

    /**
     * ���캯��
     */
    public ConfigParamDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return ConfigParam.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public ConfigParam getById(Serializable key) {
        return (ConfigParam) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public ConfigParam loadById(Serializable key) {
        return (ConfigParam) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * �������Ͳ������ж���
     */
    public List findAllParams(long type) {
        String hql = "from ConfigParam param where param.configType = ?";
        return this.query(hql, new Object[] { new Long(type) });

    }

    /**
     * ���ݸ�ID���Ҳ���
     * 
     * @param parentId
     * @return ConfigParam ʵ���б�
     */
    public List findParamsByParentId(Long parentId) {
        StringBuffer hql = new StringBuffer("from ConfigParam param ");
        hql.append(" where param.parentId = ? and param.configType = ");
        hql.append(ConfigParamDTO.PARAM_TYPE_VALUE);
        return this.query(hql.toString(), new Object[] { parentId });
    }

    /**
     * ���Ҹ��ڵ����
     */
    public ConfigParam findRootParamNode() {
        String hql = "from ConfigParam param where param.configId = param.parentId";
        return (ConfigParam) this.getSingle(hql, null);
    }

    /**
     * ���ݴ�����Ҳ���
     * 
     * @param configCode
     * @return
     */
    public ConfigParam findConfigParamByCode(String configCode) {
        String hql = "from ConfigParam param where param.configCode = ?";
        return (ConfigParam) this.getSingle(hql, new Object[] { configCode });
    }
}