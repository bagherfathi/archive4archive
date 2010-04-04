package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.AppType;
import com.ft.utils.DBUtils;

/**
 * AppType ʵ�����ݷ�����
 * 
 * @version 1.0
 * 
 * @spring.bean id="appTypeDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AppTypeDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public AppTypeDao() {
	}

	/**
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return AppType.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public AppType getById(Serializable key) {
		return (AppType) this.getHibernateTemplate().get(getReferenceClass(),
				key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public AppType loadById(Serializable key) {
		return (AppType) this.getHibernateTemplate().load(getReferenceClass(),
				key);
	}

	/**
	 * �����������Ͳ��Ҷ���
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
	 * ��������������������������
	 * @param category
	 * @return ���������б�������Ϊ��
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
	 *�����������ͱ�ʶ������������
	 * @param appTypeIds �������ͱ�ʶ�б�
	 * @return AppType
	 */
	public List findByAppTypeIds(Long[] appTypeIds){
		StringBuffer hql = new StringBuffer(
		"from AppType apt where apt.appTypeId in (").append(
				DBUtils.getArrayAsString(appTypeIds,",")).append(")");
		return this.query(hql.toString());
	}

	/**
	 * �������ж���
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

}