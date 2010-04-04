package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.sm.entity.ConfigParam;

/**
 * ConfigParam 实体数据访问类
 * 
 * @spring.bean id="ConfigParamDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ConfigParamDao extends BaseDao {

    /**
     * 构造函数
     */
    public ConfigParamDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return ConfigParam.class;
    }

    /**
     * 根据ID查找对象
     */
    public ConfigParam getById(Serializable key) {
        return (ConfigParam) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public ConfigParam loadById(Serializable key) {
        return (ConfigParam) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据类型查找所有对象
     */
    public List findAllParams(long type) {
        String hql = "from ConfigParam param where param.configType = ?";
        return this.query(hql, new Object[] { new Long(type) });

    }

    /**
     * 根据父ID查找参数
     * 
     * @param parentId
     * @return ConfigParam 实体列表
     */
    public List findParamsByParentId(Long parentId) {
        StringBuffer hql = new StringBuffer("from ConfigParam param ");
        hql.append(" where param.parentId = ? and param.configType = ");
        hql.append(ConfigParamDTO.PARAM_TYPE_VALUE);
        return this.query(hql.toString(), new Object[] { parentId });
    }

    /**
     * 查找根节点参数
     */
    public ConfigParam findRootParamNode() {
        String hql = "from ConfigParam param where param.configId = param.parentId";
        return (ConfigParam) this.getSingle(hql, null);
    }

    /**
     * 根据代码查找参数
     * 
     * @param configCode
     * @return
     */
    public ConfigParam findConfigParamByCode(String configCode) {
        String hql = "from ConfigParam param where param.configCode = ?";
        return (ConfigParam) this.getSingle(hql, new Object[] { configCode });
    }
}