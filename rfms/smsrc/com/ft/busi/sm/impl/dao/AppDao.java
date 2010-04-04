package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.commons.page.PageBean;
import com.ft.hibernate.callback.FindByNotInIdsCallback;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.App;

/**
 * App 实体数据访问类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AppDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public AppDao() {
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return App.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public App getById(Serializable key) {
		return (App) this.getHibernateTemplate().get(getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public App loadById(Serializable key) {
		return (App) this.getHibernateTemplate().load(getReferenceClass(), key);
	}

	/**
	 * 根据受理编号查找受理信息
	 * 
	 * @param appCode
	 *            受理编号
	 * @return App 不存在返回为空
	 */
	public App findByAppCode(String appCode) {
		List result = this.query(
				"from App where appCode = ?", new Object[] { appCode });

		if (result == null || result.isEmpty()) {
			return null;
		} else {
			return (App) result.get(0);
		}
	}
	
	public long getMaxAppId(){
		StringBuffer hql=new StringBuffer("select max(appId) as maxid from App");
		List result=this.query(hql.toString());
		return ((Long)result.get(0)).longValue();
	}
	
	public long filterLatestAppId(Long[] appIds){
		StringBuffer hql=new StringBuffer("select ap.appId  from App ap")
		.append(" where ap.appAction like 'CS%'")
		.append(" and ap.appId in ")
		.append(FindByNotInIdsCallback.joinKeys(appIds))
		.append(" order by ap.appTime desc ");
		List result=this.query(hql.toString());
		return ((Long)result.get(0)).longValue();
	}
	
	/**
	 * 根据传入的翻页器pageBean查询后返回结果集
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return 根据翻页器pageBean返回的指定查询结果集
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr) {
		return this.getResultByPageBean(pageBean, queryStr, new Object[0]);
	}
	
	/**
	 * 根据传入的翻页器pageBean查询后返回查询结果集
	 * 
	 * @param pagetBean
	 *            翻页器
	 * @param queryStr
	 *            带查询参数的HSQL语句
	 * @param paramObjs
	 *            查询参数数组
	 * @return 根据翻页器pageBean返回的指定查询结果集
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr,
			Object[] paramObjs) {
		List result = new ArrayList();
		if (pageBean != null) {
			if (pageBean.getRecordCount() <= 0L) {
				int countRec = this.count(queryStr, paramObjs);
				pageBean.setRecordCount(new Long(countRec).longValue());
			}
			if (pageBean.getRecordCount() > 0L) {
				result = query(queryStr, paramObjs, pageBean);
			}
		} else {
			result = query(queryStr, paramObjs);
		}
		return result;

	}

}