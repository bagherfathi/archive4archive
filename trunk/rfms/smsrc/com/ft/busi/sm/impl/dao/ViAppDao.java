package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.ViApp;

/**
 * 
 * AppView ʵ�����ݷ�����
 * 
 * @version 1.0
 * 
 * @spring.bean id="appViewDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ViAppDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public ViAppDao() {
	}

	/**
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return ViApp.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public ViApp getById(Serializable key) {
		return (ViApp) this.getHibernateTemplate().get(getReferenceClass(),
				key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public ViApp loadById(Serializable key) {
		return (ViApp) this.getHibernateTemplate().load(getReferenceClass(),
				key);
	}
	
	/**
	 * ���������Ų���������Ϣ
	 * 
	 * @param appCode
	 *            ������
	 * @return App �����ڷ���Ϊ��
	 */
	public ViApp findByAppCode(String appCode) {
		List result = this.getHibernateTemplate().find(
				"from ViApp where appCode = ?", new Object[] { appCode });

		if (result == null || result.isEmpty()) {
			return null;
		} else {
			return (ViApp) result.get(0);
		}
	}


}