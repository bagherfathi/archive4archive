package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.commons.page.PageBean;
import com.ft.hibernate.callback.FindByNotInIdsCallback;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.App;

/**
 * App ʵ�����ݷ�����
 * 
 * @version 1.0
 * 
 * @spring.bean id="appDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AppDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public AppDao() {
	}

	/**
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return App.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public App getById(Serializable key) {
		return (App) this.getHibernateTemplate().get(getReferenceClass(), key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public App loadById(Serializable key) {
		return (App) this.getHibernateTemplate().load(getReferenceClass(), key);
	}

	/**
	 * ���������Ų���������Ϣ
	 * 
	 * @param appCode
	 *            ������
	 * @return App �����ڷ���Ϊ��
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
	 * ���ݴ���ķ�ҳ��pageBean��ѯ�󷵻ؽ����
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return ���ݷ�ҳ��pageBean���ص�ָ����ѯ�����
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr) {
		return this.getResultByPageBean(pageBean, queryStr, new Object[0]);
	}
	
	/**
	 * ���ݴ���ķ�ҳ��pageBean��ѯ�󷵻ز�ѯ�����
	 * 
	 * @param pagetBean
	 *            ��ҳ��
	 * @param queryStr
	 *            ����ѯ������HSQL���
	 * @param paramObjs
	 *            ��ѯ��������
	 * @return ���ݷ�ҳ��pageBean���ص�ָ����ѯ�����
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