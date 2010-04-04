/**
 * 
 */
package com.ft.common.busi;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ft.busi.model.BusiBaseService;
import com.ft.commons.page.PageBean;

/**
 * @author soler
 * 
 */
public interface BaseService {

	/**
	 * ��������/���¼�¼
	 */
	public void batchSave(Collection list) throws Exception;

	/**
	 * ����ɾ��ɾ��
	 */
	public void batchDelete(final Collection list) throws Exception;

	/**
	 * ������¼
	 */
	public void save(Object obj) throws Exception;

	/**
	 * ���¼�¼
	 */
	public void update(Object obj) throws Exception;

	/**
	 * ɾ����¼
	 */
	public void delete(Object obj) throws Exception;

	/**
	 * �������¼�¼
	 */
	public void batchUpdate(Collection selected) throws Exception;

	/**
	 * ����/���¼�¼
	 */
	public void saveOrUpdate(Object obj) throws Exception;

	/**
	 * ����ʵ�����ͼ���ʶ����һ��ʵ�����
	 * 
	 * @param cls
	 *            ʵ������
	 * @param identifier
	 *            �����ʶ
	 * @return ʵ��
	 */
	public Object read(Class entityCls, Serializable identifier)
			throws Exception;

	/**
	 * ͨ��sql����ѯ����
	 */
	public List queryObj(String queryStr) throws Exception;

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
			Object[] paramObjs) throws Exception;

	/**
	 * ���ݴ���ķ�ҳ��pageBean��ѯ�󷵻ؽ����
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return ���ݷ�ҳ��pageBean���ص�ָ����ѯ�����
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr)
			throws Exception;

	/**
	 * ����ָ����һ���ʶ��ѯָ��ʵ�塣
	 * 
	 * @param clazz
	 *            ʵ���ࡣ
	 * @param keys
	 *            ��ʶ���顣
	 * @return
	 */
	public List loadByIds(final Class clazz, final Serializable[] keys)
			throws Exception;

	/**
	 * ���ݸ����Ĳ�ѯ���HQLɾ��ʵ�塣
	 * 
	 * @param queryStr
	 *            ��ѯ��䡣
	 * @param params
	 *            ����ֵ��
	 */
	public void deleteFromQuery(String queryStr, Object[] params)
			throws Exception;

	/**
	 * ִ�в�ѯ��䣨HQL����
	 * 
	 * @param queryStr
	 *            ��ѯ���HQL��
	 * @return ��ѯ�����
	 */
	public List query(String queryStr) throws Exception;

	/**
	 * ִ�в�ѯ��䣨HQL����
	 * 
	 * @param queryStr
	 *            ��ѯ���HQL��
	 * @param params
	 *            ����ֵ��
	 * @return ��ѯ�����
	 */
	public List query(String queryStr, Object[] params) throws Exception;

	/**
	 * ��ҳ��ѯ����
	 * 
	 * @param queryStr
	 *            ��ѯ���
	 * @param params
	 *            ����ֵ
	 * @param pageBean
	 *            ��ҳ����
	 * @return ��¼���
	 */
	public List query(final String queryStr, final Object[] params,
			final PageBean pageBean) throws Exception;

	public List query(final String queryStr, final Object[] params, int begin,
			int size) throws Exception;

	public int getCount(final String hql, final Object[] args) throws Exception;

	/**
	 * ��ѯ����������
	 * 
	 * @param queryString
	 *            ��ѯ��䡣
	 * @param params
	 *            ����ֵ��
	 */
	public Object getSingle(String queryString, Object[] params)
			throws Exception;

	/**
	 * ����ĳ���ض����Բ�ѯһ������ID��
	 * 
	 * @param name
	 *            �������ơ�
	 * @param attribute
	 *            Ҫ�󷵻ص����ԡ�
	 * @param value
	 *            ���ԵĲ���ֵ��
	 * @return ���ز�ѯ��ʵ�����
	 */
	public Object getEntityByIdentityAttribute(Class clazz, String attribute,
			Object value) throws Exception;

	/**
	 * ��ѯһ�������е�ID�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ��䡣
	 * @param ids
	 *            Id ���顣
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids)
			throws Exception;

	/**
	 * ��ѯһ�������е�ID�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ��䡣
	 * @param ids
	 *            Id ���顣
	 * @param conditions
	 *            �������ӵ�������
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids,
			String conditions) throws Exception;

	/**
	 * ���ݶ������ͺ�ID��ѯһ������
	 * 
	 * @param entityClass
	 *            Ҫ��ѯ��ʵ���ࡣ
	 * @param id
	 *            �����ʶ��
	 * @return ��ѯ�����
	 * @throws DataAccessException
	 */
	public Object getObjectById(Class entityClass, Serializable id)
			throws Exception;

	public Object getObjectById(String entityClass, Serializable id)
			throws Exception;

	/**
	 * ��ѯ���ж���
	 * 
	 * @param aClass
	 *            ʵ���ࡣ
	 * @return
	 */
	public List loadAll(Class aClass) throws Exception;

	/**
	 * 
	 * @param id
	 * @param hqlTableName
	 */
	public void delObject(Long id, String hqlTableName) throws Exception;

}
