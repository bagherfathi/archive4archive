package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.AppOperateDtl;

/**
 * AppOperateDtl �����¼������ϸ ʵ�����ݷ�����
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
	 * ���캯��
	 */
	public AppOperateDtlDao() {
	}

	/**
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return AppOperateDtl.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public AppOperateDtl getById(Serializable key) {
		return (AppOperateDtl) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public AppOperateDtl loadById(Serializable key) {
		return (AppOperateDtl) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * �������ж���
	 */
	public List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}
	/**���������ʶ�����������¼������ϸ -����
	 * @param appId �����ʶ
	 * @return java.util.List �����¼������ϸ AppOperateDtl -����
	 * */
	public List findAppOperateDtlsByAppId(long appId){
		StringBuffer queryStr=new StringBuffer(AppOperateDtlDao.AppOperateDtlQueryStr);
		queryStr.append(" where appOperateDtl.appId=? ");
	   return this.query(queryStr.toString(),new Object[]{new Long(appId)} );		
	}
	/**���ݶ����ʶ����,�����ʶ,���������ʶ֮��������¼������ϸ -����
	 * @param className -  ����
	 * @param objectId - �����ʶ����
	 * @param appId    - �����ʶ
	 * @return java.util.List �����¼������ϸ AppOperateDtl -����
	 * */
	public List findAppOperateDtlsByObjectIdBeyondAppId(String className,long objectId,long appId){
		StringBuffer queryStr=new StringBuffer("select appOperateDtl from AppOperateDtl appOperateDtl,App app")
		.append(" where appOperateDtl.appId=app.appId ")
		.append(" and app.appAction like 'CS%'");
		queryStr.append(" and  appOperateDtl.className =? and appOperateDtl.objectId=? and appOperateDtl.appId >?");
		return this.query(queryStr.toString(),new Object[]{className,new Long(objectId),new Long(appId)} );
	}
}