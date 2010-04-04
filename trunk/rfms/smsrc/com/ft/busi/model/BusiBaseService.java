package com.ft.busi.model;

import java.io.Serializable;

/**
 * @version 1.0
 */
public interface BusiBaseService {
	/**
	 * ������ʷ����
	 * 
	 * @param object
	 *            ��ʷ����
	 */
	public abstract void saveHisObject(Object object) throws Exception;

	/**
	 * ���ݶ����ʶ���ҵ�ʵ�����
	 * 
	 * @param entityClass
	 *            ��������
	 * @param key
	 *            Ψһ��ʶ����
	 * @return Object����������ڣ�����ΪNULL��
	 */
	public abstract Object getEntityObject(Class entityClass,
			Serializable identifier) throws Exception;
}
