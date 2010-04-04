/**
 * 
 */
package com.ft.hibernate.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import com.ft.commons.page.PageBean;
import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.hibernate.callback.GetByIdentityAttributeCallback;
import com.ft.hibernate.callback.QueryCallback;
import com.ft.hibernate.callback.QueryInCallBack;
import com.ft.hibernate.callback.QueryPageCallback;
import com.ft.hibernate.helper.EntityDaoSupport;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * ���ݷ��ʻ���
 */
public class BaseDao extends EntityDaoSupport {

	/**
	 * ��������/���¼�¼
	 */
	public void batchSave(final Collection list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	/**
	 * ����ɾ��ɾ��
	 */
	public void batchDelete(final Collection list) {
		if (list != null) {
			this.getHibernateTemplate().deleteAll(list);
		}
	}

	/**
	 * ������¼
	 */
	public void save(Object obj) {
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createDate", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��createDate����");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createTime", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��createTime����");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateDate����");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateTime����");
		}
		getHibernateTemplate().save(obj);
	}

	/**
	 * ���¼�¼
	 */
	public void update(Object obj) {

		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateDate����");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateTime����");
		}
		super.update(obj);
	}

	/**
	 * ɾ����¼
	 */
	public void delete(Object obj) {
		super.delete(obj);
	}

	/**
	 * �������¼�¼
	 */
	public void batchUpdate(Collection selected) {
		this.getHibernateTemplate().saveOrUpdateAll(selected);
	}

	/**
	 * ����/���¼�¼
	 */
	public void saveOrUpdate(Object obj) {
		Object id=null;
		try {
			id = PropertyUtils.getProperty(obj, "id");
			if (id != null) {
				if(id instanceof Integer &&((Integer)id).intValue()==0){
					PropertyUtils.setProperty(obj, "id", null);
					id=null;
				}
				if(id instanceof Long &&((Long)id).longValue()==0){
					PropertyUtils.setProperty(obj, "id", null);
					id=null;
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if(id==null){
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createDate", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��createDate����");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createTime", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��createTime����");
		}
		}
		try {
			Object updateDate = PropertyUtils.getProperty(obj, "updateDate");
			if (updateDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateDate����");
		}
		try {
			Object updateTime = PropertyUtils.getProperty(obj, "updateTime");
			if (updateTime instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("�ö���û��updateTime����");
		}
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * ����ʵ�����ͼ���ʶ����һ��ʵ�����
	 * 
	 * @param cls
	 *            ʵ������
	 * @param objId
	 *            �����ʶ
	 * @return ʵ��
	 */
	public Object readObj(Class entityCls, Integer objId) {
		return this.get(entityCls, objId);
	}

	/**
	 * ����ʵ�����ͼ���ʶ����һ��ʵ�����
	 * 
	 * @param cls
	 *            ʵ������
	 * @param identifier
	 *            �����ʶ
	 * @return ʵ��
	 */
	public Object read(Class entityCls, Serializable identifier) {
		return this.get(entityCls, identifier);
	}

	/**
	 * ͨ��sql����ѯ����
	 */
	public List queryObj(String queryStr) {
		return this.query(queryStr);
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

	/**
	 * ���ݴ���ķ�ҳ��pageBean��ѯ�󷵻ؽ����
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return ���ݷ�ҳ��pageBean���ص�ָ����ѯ�����
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr) {
		return this.getResultByPageBean(pageBean, queryStr, null);
	}

	/**
	 * ����ָ����һ���ʶ��ѯָ��ʵ�塣
	 * 
	 * @param clazz
	 *            ʵ���ࡣ
	 * @param keys
	 *            ��ʶ���顣
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List loadByIds(final Class clazz, final Serializable[] keys) {
		List result = new ArrayList();
		if (keys != null && keys.length > 1000) {
			int size = keys.length / 1000;
			for (int i = 0; i <= size; i++) {
				int end;
				if (i != size) {
					end = (i + 1) * 1000;
				} else {
					end = keys.length;
				}
				Serializable[] ids = (Serializable[]) ArrayUtils.subarray(keys,
						i * 1000, end);
				result.addAll((List) this.getHibernateTemplate().execute(
						new FindByIdsCallback(clazz, ids)));
			}
		} else {
			result = (List) this.getHibernateTemplate().execute(
					new FindByIdsCallback(clazz, keys));
		}
		return result;
	}

	/**
	 * ���ݸ����Ĳ�ѯ���HQLɾ��ʵ�塣
	 * 
	 * @param queryStr
	 *            ��ѯ��䡣
	 * @param params
	 *            ����ֵ��
	 */
	public void deleteFromQuery(String queryStr, Object[] params) {
		List result = this.getHibernateTemplate().find(queryStr, params);
		batchDelete(result);
	}

	/**
	 * ִ�в�ѯ��䣨HQL����
	 * 
	 * @param queryStr
	 *            ��ѯ���HQL��
	 * @return ��ѯ�����
	 */
	public List query(String queryStr) {
		return this.getHibernateTemplate().find(queryStr);
	}

	/**
	 * ִ�в�ѯ��䣨HQL����
	 * 
	 * @param queryStr
	 *            ��ѯ���HQL��
	 * @param params
	 *            ����ֵ��
	 * @return ��ѯ�����
	 */
	public List query(String queryStr, Object[] params) {
		return this.getHibernateTemplate().find(queryStr, params);
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
	public List query(final String queryStr, final Object[] params,
			final PageBean pageBean) {

		if (pageBean == null) {
			return query(queryStr, params);
		}

		List result = (List) this.getHibernateTemplate().execute(
				new QueryPageCallback(queryStr, params, pageBean));

		return result;
	}

	public List query(final String queryStr, final Object[] params, int begin,
			int size) {

		List result = (List) this.getHibernateTemplate().execute(
				new QueryCallback(queryStr, params, begin, size));

		return result;
	}

	public int getCount(final String hql, final Object[] args) {
		try {
			List l = getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query queryObject = session.createQuery(hql);
							if (args != null && args.length > 0) {
								for (int i = 0; i < args.length; i++) {
									queryObject.setParameter(i, args[i]);
								}
							}
							return queryObject.list();
						}
					});
			return Integer.parseInt(l.get(0) + "");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * ��ѯ����������
	 * 
	 * @param queryString
	 *            ��ѯ��䡣
	 * @param params
	 *            ����ֵ��
	 */
	public Object getSingle(String queryString, Object[] params) {
		List list = this.getHibernateTemplate().find(queryString, params);

		if (list.size() > 0) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

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
			Object value) {
		Object result = (Object) this.getHibernateTemplate().execute(
				new GetByIdentityAttributeCallback(clazz, attribute, value));

		return result;
	}

	/**
	 * ��ѯһ�������е�ID�Ķ���
	 * 
	 * @param queryString
	 *            ��ѯ��䡣
	 * @param ids
	 *            Id ���顣
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids));
		return result;
	}

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
			String conditions) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids, conditions));
		return result;
	}

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
			throws DataAccessException {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * ��ѯ���ж���
	 * 
	 * @param aClass
	 *            ʵ���ࡣ
	 * @return
	 */
	public List loadAll(Class aClass) {
		return this.getHibernateTemplate().loadAll(aClass);
	}
	
	/**
	 * ͨ��sql����ѯ����
	 */
	public List queryObj(String queryStr, Object[] params) {
		return super.query(queryStr, params);
	}
}
