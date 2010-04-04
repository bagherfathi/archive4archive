package com.ft.hibernate.helper;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ft.commons.page.PageBean;
import com.ft.hibernate.callback.CountCallback;
import com.ft.hibernate.callback.CountGroupCallback;
import com.ft.hibernate.callback.ExistCallback;
import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.hibernate.callback.FindByNotInIdsCallback;
import com.ft.hibernate.callback.GetByIdentityAttributeCallback;
import com.ft.hibernate.callback.QueryByNamedCallback;
import com.ft.hibernate.callback.QueryByNamedPageCallback;
import com.ft.hibernate.callback.QueryCallback;
import com.ft.hibernate.callback.QueryInCallBack;
import com.ft.hibernate.callback.QueryPageCallback;
import com.ft.hibernate.commons.EntityGroupCount;

public class EntityDaoSupport extends HibernateDaoSupport {

	/**
	 * ���ݶ������ͺ�ID��ѯһ������
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	protected Object get(Class entityClass, Serializable id)
			throws DataAccessException {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * ��ѯ��һ��HSQL��ֵ
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	protected int count(String queryString, final Object[] params) {
		Number result = (Number) this.getHibernateTemplate().execute(
				new CountCallback(queryString, params));

		return result.intValue();
	}

	/**
	 * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
	 * 
	 * @param clazz
	 *            ������
	 * @param attribute
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ���ڷ���true
	 */
	protected boolean exist(final Class clazz, String attribute, Object value) {
		Boolean result = (Boolean) this.getHibernateTemplate().execute(
				new ExistCallback("and", clazz, new String[] { attribute },
						new Object[] { value }));

		return result.booleanValue();
	}

	/**
	 * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
	 * 
	 * @param clazz
	 *            ������
	 * @param attribute
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ���ڷ���true
	 */
	protected boolean exist(final Class clazz, String[] attribute,
			Object[] value) {
		Boolean result = (Boolean) this.getHibernateTemplate().execute(
				new ExistCallback("and", clazz, attribute, value));

		return result.booleanValue();
	}

	/**
	 * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
	 * 
	 * @param clazz
	 *            ������
	 * @param attribute
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ���ڷ���true
	 */
	protected boolean existByOr(final Class clazz, String attribute,
			Object value) {
		Boolean result = (Boolean) this.getHibernateTemplate().execute(
				new ExistCallback("or", clazz, new String[] { attribute },
						new Object[] { value }));

		return result.booleanValue();
	}

	/**
	 * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
	 * 
	 * @param clazz
	 *            ������
	 * @param attribute
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ���ڷ���true
	 */
	protected boolean existByOr(final Class clazz, String[] attribute,
			Object[] value) {
		Boolean result = (Boolean) this.getHibernateTemplate().execute(
				new ExistCallback("or", clazz, attribute, value));

		return result.booleanValue();
	}

	/**
	 * ����ID������ѯID
	 * 
	 * @param clazz
	 * @param keys
	 * @return
	 */
	protected List loadByIds(final Class clazz, final Serializable[] keys) {
		List result = (List) this.getHibernateTemplate().execute(
				new FindByIdsCallback(clazz, keys));

		return result;
	}

	/**
	 * ����ĳ���ض����Բ�ѯһ������ID
	 * 
	 * @param name
	 *            ��������
	 * @param attribute
	 *            Ҫ�󷵻ص�����
	 * @param value
	 *            ���ԵĲ���ֵ
	 * @return ���ز�ѯ��ʵ�����
	 */
	protected Object getEntityByIdentityAttribute(Class clazz,
			String attribute, Object value) {
		Object result = (Object) this.getHibernateTemplate().execute(
				new GetByIdentityAttributeCallback(clazz, attribute, value));

		return result;
	}

	/**
	 * ��ѯ���ж���
	 * 
	 * @param aClass
	 *            �������е���
	 * @return
	 */
	protected List loadAll(Class aClass) {
		return this.getHibernateTemplate().loadAll(aClass);
	}

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
	protected List query(final String queryStr, final Object[] params,
			final PageBean pageBean) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryPageCallback(queryStr, params, pageBean));

		return result;
	}

	/**
	 * �����ѯ
	 * 
	 * @param queryStr
	 *            ��ѯ���
	 * @param params
	 *            ����ֵ
	 * @param begin
	 *            ��ʼ��¼��
	 * @param length
	 *            ��ѯ����
	 * @return ��ѯ���
	 */
	protected List query(final String queryStr, final Object[] params,
			final int begin, final int length) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryCallback(queryStr, params, begin, length));

		return result;
	}

	/**
	 * ����ID�õ�һ�������ڵ���֮ǰҪ��֤�����¼�Ѿ����ڷ�����׳��쳣
	 * 
	 * @param entityClass
	 *            ����
	 * @param key
	 *            ����ֵ
	 * @return entityClass�Ĳ�ѯ���
	 */
	protected Object load(Class entityClass, Serializable key) {
		return this.getHibernateTemplate().load(entityClass, key);
	}

	/**
	 * ��ѯ
	 * 
	 * @param string
	 *            ��ѯ���
	 * @return ��ѯ���
	 */
	protected List query(String queryStr) {
		return this.getHibernateTemplate().find(queryStr);
	}

	/**
	 * ��ֵ����ѯ
	 * 
	 * @param string
	 *            ��ѯ���
	 * @param params
	 *            ����ֵ
	 * @return ��ѯ���
	 */
	protected List query(String queryStr, Object[] params) {
		return this.getHibernateTemplate().find(queryStr, params);
	}

	/**
	 * ����Ԥ����ѯ
	 * 
	 * @param ��ѯ��
	 * @return ��ѯ���
	 */
	protected List findByNamedQuery(String queryName) {
		return this.getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * ����Ԥ����ѯ
	 * 
	 * @param queueName
	 *            ��ѯ����
	 * @param params
	 *            ����
	 * @return ��ѯ���
	 */
	protected List findByNamedQuery(String queryName, Object[] params) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, params);
	}

	/**
	 * 
	 * ����Ԥ����ѯ
	 * 
	 * @param queryName
	 *            ��ѯ����
	 * @param params
	 *            ����ֵ
	 * @param size
	 *            ���ؼ�¼��
	 * 
	 * @return ��ѯ���
	 */
	protected List queryByNamed(final String queryName, final Object[] params,
			final int size) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryByNamedCallback(queryName, params, 0, size));

		return result;
	}

	/**
	 * ����Ԥ����ѯ
	 * 
	 * @param name
	 *            ��ѯ����
	 * @param params
	 *            ����ֵ
	 * @param pageBean
	 *            ��ҳ����
	 * @return ��ѯ���
	 */
	protected List queryByNamed(final String named, final Object[] params,
			PageBean pageBean) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryByNamedPageCallback(named, params, pageBean));

		return result;
	}

	/**
	 * @param clazz
	 *            Ҫ��ѯ�������
	 * @param pageBean
	 *            ��ҳ����
	 * @return ��ѯ���
	 */
	protected List query(Class clazz, PageBean pageBean) {
		return this.query("from " + clazz.getName(), new Object[0], pageBean);
	}

	/**
	 * @param clazz
	 *            Ҫ��ѯ���
	 * @param params
	 *            ����
	 * @param maxSize
	 *            ����¼��
	 * @return ��ѯ���
	 */
	protected List query(String string, Object[] params, int maxSize) {
		return query(string, params, 0, maxSize);
	}

	/**
	 * �����ѯ
	 * 
	 * @param attribute
	 *            ��������
	 * @param clazz
	 *            Ҫ��ѯ����
	 * @return ��ѯ��� EntityGroupCount����
	 */
	protected List countGroup(Class clazz, String attribute) {
		List result = (List) this.getHibernateTemplate()
				.execute(
						new CountGroupCallback(clazz, EntityGroupCount.class,
								attribute));

		return result;
	}

	/**
	 * ���ݲ�ѯ��䣬����Iterator����
	 * 
	 * @param ��ѯ���
	 * @return ��ѯ��Itearator����
	 */
	protected Iterator iterate(String queryString) {
		return this.getHibernateTemplate().iterate(queryString);
	}

	/**
	 * ��ѯ�����е�һЩ����
	 */
	protected List findAttribute(Class entityClass, String[] strings) {
		StringBuffer sql = new StringBuffer("select ");

		for (int i = 0; i < strings.length; i++) {
			sql.append(" entity.").append(strings[i]);
			sql.append(",");
		}

		sql.append("0");
		sql.append(" from ").append(entityClass.getName());
		sql.append(" entity");

		return query(sql.toString());
	}

	/**
	 * ��ѯ����һ�������е�ID�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param ids
	 *            Id ����
	 * @return
	 */
	protected List queryByNotInIds(Class class1, Long[] ids) {
		List result = (List) this.getHibernateTemplate().execute(
				new FindByNotInIdsCallback(class1, ids));

		return result;
	}

	/**
	 * ��ѯ����������
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param params
	 *            ����ֵ
	 */
	protected Object getSingle(String queryString, Object[] params) {
		List list = this.getHibernateTemplate().find(queryString, params);

		if (list.size() > 0) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * ��ѯһ�������е�ID�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param ids
	 *            Id ����
	 * @return
	 */
	protected List queryIn(String queryString, Serializable[] ids) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids));
		return result;
	}

	/**
	 * ��ѯһ����֯�е�ID
	 * 
	 * @param queryString
	 *            ��ѯ���
	 * @param ids
	 *            Id ����
	 * @param conditions
	 *            �������ӵ�����
	 * @return
	 */
	protected List queryIn(String queryString, Serializable[] ids,
			String conditions) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids, conditions));
		return result;
	}

	/**
	 * �������Ψһ���Բ�ѯ�����ID
	 * 
	 * @param entityClass
	 *            ��ѯ����
	 * @param keyAttribute
	 *            Id��������
	 * @param attribue
	 *            Ψһ������
	 * @param obj
	 *            Ψһ����ֵ
	 * @return
	 */
	protected Object getEntityIdByIdentityAttribute(Class entityClass,
			String keyAttribute, String attribute, Object obj) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append(" entity.").append(keyAttribute);
		sql.append(" from ").append(entityClass.getName());
		sql.append(" entity where entity.").append(attribute).append("=?");

		List list = query(sql.toString(), new Object[] { obj });

		if (list.size() > 0) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * �������Ʋ�ѯ��������
	 * 
	 * @param namedQuery
	 *            ��ѯ����
	 * @param params
	 *            ����
	 * @return
	 */
	protected Object getSingleByNamedQuery(String queryName, Object[] params) {
		List result = this.getHibernateTemplate().findByNamedQuery(queryName,
				params);
		if (result.size() > 0) {
			return result.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * �����������
	 * 
	 * @param list
	 */
	protected void batchSave(final Collection list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	/**
	 * ����ɾ������
	 * 
	 * @param list
	 */
	protected void batchDelete(final Collection list) {
		if (list != null) {
			this.getHibernateTemplate().deleteAll(list);
		}
	}

	/**
	 * @param role
	 */
	protected void save(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * ���¶���
	 * 
	 * @param element
	 */
	protected void update(Object element) {
		this.getHibernateTemplate().update(element);
	}

	/**
	 * ���ݲ�ѯ��HQLɾ������
	 * 
	 * @param string
	 *            ��ѯ��HQL
	 */
	protected void delete(String string) {
		this.getHibernateTemplate().delete(string);
	}

	/**
	 * ɾ������
	 * 
	 * @param obj
	 */
	protected void delete(Object obj) {
		this.getSession().setFlushMode(FlushMode.AUTO);
		this.getHibernateTemplate().delete(obj);
	}

	/**
	 * ����SQL����ɾ����һ�����ڴ�����������������
	 * 
	 * @param str
	 *            SQLɾ��
	 */
	protected void deleteSQL(final String str) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				PreparedStatement statement = session.connection()
						.prepareStatement(str);

				try {
					statement.execute();
				} catch (SQLException e) {
					if (statement != null) {
						statement.close();
					}

					throw e;
				}

				return null;
			}
		});
	}

	/**
	 * ��������
	 * 
	 * @param selected
	 */
	protected void batchUpdate(Collection selected) {
		this.getHibernateTemplate().saveOrUpdateAll(selected);
	}

	/**
	 * ͨ��һ��HSQLɾ������
	 * 
	 * @param string
	 * @param objects
	 * @param types
	 */
	protected void deleteFromQuery(String string, Object[] objects) {
		List result = this.getHibernateTemplate().find(string, objects);
		batchDelete(result);
	}

	/**
	 * ����һ������
	 * 
	 * @param obj
	 */
	protected long insert(Object obj) {
		return ((Long)getHibernateTemplate().save(obj)).longValue();
	}

	/**
	 * ��������һ������
	 * 
	 * @param obj
	 */
	protected void saveOrUpdate(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * һ�������Ƿ��Ѿ���Session��
	 * 
	 * @param entity
	 * @return
	 */
	protected boolean isPersistent(Object entity) {
		return this.getSession().contains(entity);
	}
}
