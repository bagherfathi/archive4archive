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
 * 数据访问基类
 */
public class BaseDao extends EntityDaoSupport {

	/**
	 * 批量新增/更新记录
	 */
	public void batchSave(final Collection list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	/**
	 * 批量删除删除
	 */
	public void batchDelete(final Collection list) {
		if (list != null) {
			this.getHibernateTemplate().deleteAll(list);
		}
	}

	/**
	 * 新增记录
	 */
	public void save(Object obj) {
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createDate", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有createDate属性");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createTime", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有createTime属性");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateDate属性");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateTime属性");
		}
		getHibernateTemplate().save(obj);
	}

	/**
	 * 更新记录
	 */
	public void update(Object obj) {

		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateDate");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateDate属性");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "updateTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateTime属性");
		}
		super.update(obj);
	}

	/**
	 * 删除记录
	 */
	public void delete(Object obj) {
		super.delete(obj);
	}

	/**
	 * 批量更新记录
	 */
	public void batchUpdate(Collection selected) {
		this.getHibernateTemplate().saveOrUpdateAll(selected);
	}

	/**
	 * 新增/更新记录
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
			this.logger.error("该对象没有createDate属性");
		}
		try {
			Object createDate = PropertyUtils.getProperty(obj, "createTime");
			if (createDate instanceof Date)
				PropertyUtils.setProperty(obj, "createTime", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有createTime属性");
		}
		}
		try {
			Object updateDate = PropertyUtils.getProperty(obj, "updateDate");
			if (updateDate instanceof Date)
				PropertyUtils.setProperty(obj, "updateDate", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateDate属性");
		}
		try {
			Object updateTime = PropertyUtils.getProperty(obj, "updateTime");
			if (updateTime instanceof Date)
				PropertyUtils.setProperty(obj, "updateTime", new Date());
		} catch (Exception e) {
			this.logger.error("该对象没有updateTime属性");
		}
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * 根据实体类型及标识查找一个实体对象
	 * 
	 * @param cls
	 *            实体类型
	 * @param objId
	 *            对象标识
	 * @return 实体
	 */
	public Object readObj(Class entityCls, Integer objId) {
		return this.get(entityCls, objId);
	}

	/**
	 * 根据实体类型及标识查找一个实体对象
	 * 
	 * @param cls
	 *            实体类型
	 * @param identifier
	 *            对象标识
	 * @return 实体
	 */
	public Object read(Class entityCls, Serializable identifier) {
		return this.get(entityCls, identifier);
	}

	/**
	 * 通过sql语句查询对象
	 */
	public List queryObj(String queryStr) {
		return this.query(queryStr);
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

	/**
	 * 根据传入的翻页器pageBean查询后返回结果集
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return 根据翻页器pageBean返回的指定查询结果集
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr) {
		return this.getResultByPageBean(pageBean, queryStr, null);
	}

	/**
	 * 根据指定的一组标识查询指定实体。
	 * 
	 * @param clazz
	 *            实体类。
	 * @param keys
	 *            标识数组。
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
	 * 根据给定的查询语句HQL删除实体。
	 * 
	 * @param queryStr
	 *            查询语句。
	 * @param params
	 *            参数值。
	 */
	public void deleteFromQuery(String queryStr, Object[] params) {
		List result = this.getHibernateTemplate().find(queryStr, params);
		batchDelete(result);
	}

	/**
	 * 执行查询语句（HQL）。
	 * 
	 * @param queryStr
	 *            查询语句HQL。
	 * @return 查询结果。
	 */
	public List query(String queryStr) {
		return this.getHibernateTemplate().find(queryStr);
	}

	/**
	 * 执行查询语句（HQL）。
	 * 
	 * @param queryStr
	 *            查询语句HQL。
	 * @param params
	 *            参数值。
	 * @return 查询结果。
	 */
	public List query(String queryStr, Object[] params) {
		return this.getHibernateTemplate().find(queryStr, params);
	}

	/**
	 * 分页查询对象
	 * 
	 * @param queryStr
	 *            查询语句
	 * @param params
	 *            参数值
	 * @param pageBean
	 *            分页对象
	 * @return 记录结果
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
	 * 查询出单个对象。
	 * 
	 * @param queryString
	 *            查询语句。
	 * @param params
	 *            参数值。
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
	 * 根据某个特定属性查询一个对象ID。
	 * 
	 * @param name
	 *            对象名称。
	 * @param attribute
	 *            要求返回的属性。
	 * @param value
	 *            属性的参数值。
	 * @return 返回查询的实体对象。
	 */
	public Object getEntityByIdentityAttribute(Class clazz, String attribute,
			Object value) {
		Object result = (Object) this.getHibernateTemplate().execute(
				new GetByIdentityAttributeCallback(clazz, attribute, value));

		return result;
	}

	/**
	 * 查询一个数组中的ID的对象。
	 * 
	 * @param queryString
	 *            查询语句。
	 * @param ids
	 *            Id 数组。
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids));
		return result;
	}

	/**
	 * 查询一个数组中的ID的对象。
	 * 
	 * @param queryString
	 *            查询语句。
	 * @param ids
	 *            Id 数组。
	 * @param conditions
	 *            其它附加的条件。
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids,
			String conditions) {
		List result = (List) this.getHibernateTemplate().execute(
				new QueryInCallBack(queryString, ids, conditions));
		return result;
	}

	/**
	 * 根据对象类型和ID查询一个对象。
	 * 
	 * @param entityClass
	 *            要查询的实体类。
	 * @param id
	 *            对象标识。
	 * @return 查询结果。
	 * @throws DataAccessException
	 */
	public Object getObjectById(Class entityClass, Serializable id)
			throws DataAccessException {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 查询所有对象。
	 * 
	 * @param aClass
	 *            实体类。
	 * @return
	 */
	public List loadAll(Class aClass) {
		return this.getHibernateTemplate().loadAll(aClass);
	}
	
	/**
	 * 通过sql语句查询对象
	 */
	public List queryObj(String queryStr, Object[] params) {
		return super.query(queryStr, params);
	}
}
