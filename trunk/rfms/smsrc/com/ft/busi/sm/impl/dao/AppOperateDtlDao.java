package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.AppOperateDtl;

/**
 * AppOperateDtl 受理记录操作明细 实体数据访问类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appOperateDtlDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AppOperateDtlDao extends BaseDao {
   
	public final static String AppOperateDtlQueryStr=" from AppOperateDtl appOperateDtl ";
	public final static String AppOperateDtlPrefixStr="appOperateDtl";
	/**
	 * 构造函数
	 */
	public AppOperateDtlDao() {
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return AppOperateDtl.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public AppOperateDtl getById(Serializable key) {
		return (AppOperateDtl) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public AppOperateDtl loadById(Serializable key) {
		return (AppOperateDtl) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * 查找所有对象
	 */
	public List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}
	/**根据受理标识，返回受理记录操作明细 -集合
	 * @param appId 受理标识
	 * @return java.util.List 受理记录操作明细 AppOperateDtl -集合
	 * */
	public List findAppOperateDtlsByAppId(long appId){
		StringBuffer queryStr=new StringBuffer(AppOperateDtlDao.AppOperateDtlQueryStr);
		queryStr.append(" where appOperateDtl.appId=? ");
	   return this.query(queryStr.toString(),new Object[]{new Long(appId)} );		
	}
	/**根据对象标识主键,受理标识,返回受理标识之后的受理记录操作明细 -集合
	 * @param className -  对象
	 * @param objectId - 对象标识主键
	 * @param appId    - 受理标识
	 * @return java.util.List 受理记录操作明细 AppOperateDtl -集合
	 * */
	public List findAppOperateDtlsByObjectIdBeyondAppId(String className,long objectId,long appId){
		StringBuffer queryStr=new StringBuffer("select appOperateDtl from AppOperateDtl appOperateDtl,App app")
		.append(" where appOperateDtl.appId=app.appId ")
		.append(" and app.appAction like 'CS%'");
		queryStr.append(" and  appOperateDtl.className =? and appOperateDtl.objectId=? and appOperateDtl.appId >?");
		return this.query(queryStr.toString(),new Object[]{className,new Long(objectId),new Long(appId)} );
	}
}