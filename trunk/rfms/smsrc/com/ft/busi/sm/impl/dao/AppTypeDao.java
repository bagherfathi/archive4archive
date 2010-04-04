package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.AppType;
import com.ft.utils.DBUtils;

/**
 * AppType 实体数据访问类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appTypeDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AppTypeDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public AppTypeDao() {
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return AppType.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public AppType getById(Serializable key) {
		return (AppType) this.getHibernateTemplate().get(getReferenceClass(),
				key);
	}

	/**
	 * 根据ID查找对象
	 */
	public AppType loadById(Serializable key) {
		return (AppType) this.getHibernateTemplate().load(getReferenceClass(),
				key);
	}

	/**
	 * 根据受理类型查找对象
	 * 
	 * @param code
	 * @return
	 */
	public AppType findByAppAction(String appAction) {
		StringBuffer hql = new StringBuffer(
				"from AppType apt where apt.appAction =?");
		
		List result = this.query(hql.toString(), new Object[] { appAction });
		return (null != result && result.size() > 0) ? (AppType) result.get(0)
				: null;
	}
	
	/**
	 * 根据受理类型类别查找受理类型
	 * @param category
	 * @return 受理类型列表，不存在为空
	 */
	public List findByCategory(long category){
		StringBuffer hql = new StringBuffer(
		"from AppType apt ");
		if(category>0)
		{
			hql.append("where apt.category =? ");
			return this.query(hql.toString(), new Object[] { new Long(category) });
			
		}else
			return this.query(hql.toString());
			
		
	}
	/**
	 *根据受理类型标识查找受理类型
	 * @param appTypeIds 受理类型标识列表
	 * @return AppType
	 */
	public List findByAppTypeIds(Long[] appTypeIds){
		StringBuffer hql = new StringBuffer(
		"from AppType apt where apt.appTypeId in (").append(
				DBUtils.getArrayAsString(appTypeIds,",")).append(")");
		return this.query(hql.toString());
	}

	/**
	 * 查找所有对象
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

}