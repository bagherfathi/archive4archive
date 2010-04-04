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
	 * 批量新增/更新记录
	 */
	public void batchSave(Collection list) throws Exception;

	/**
	 * 批量删除删除
	 */
	public void batchDelete(final Collection list) throws Exception;

	/**
	 * 新增记录
	 */
	public void save(Object obj) throws Exception;

	/**
	 * 更新记录
	 */
	public void update(Object obj) throws Exception;

	/**
	 * 删除记录
	 */
	public void delete(Object obj) throws Exception;

	/**
	 * 批量更新记录
	 */
	public void batchUpdate(Collection selected) throws Exception;

	/**
	 * 新增/更新记录
	 */
	public void saveOrUpdate(Object obj) throws Exception;

	/**
	 * 根据实体类型及标识查找一个实体对象
	 * 
	 * @param cls
	 *            实体类型
	 * @param identifier
	 *            对象标识
	 * @return 实体
	 */
	public Object read(Class entityCls, Serializable identifier)
			throws Exception;

	/**
	 * 通过sql语句查询对象
	 */
	public List queryObj(String queryStr) throws Exception;

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
			Object[] paramObjs) throws Exception;

	/**
	 * 根据传入的翻页器pageBean查询后返回结果集
	 * 
	 * @param pagetBean
	 * @param queryStr
	 * @return 根据翻页器pageBean返回的指定查询结果集
	 */
	public List getResultByPageBean(PageBean pageBean, String queryStr)
			throws Exception;

	/**
	 * 根据指定的一组标识查询指定实体。
	 * 
	 * @param clazz
	 *            实体类。
	 * @param keys
	 *            标识数组。
	 * @return
	 */
	public List loadByIds(final Class clazz, final Serializable[] keys)
			throws Exception;

	/**
	 * 根据给定的查询语句HQL删除实体。
	 * 
	 * @param queryStr
	 *            查询语句。
	 * @param params
	 *            参数值。
	 */
	public void deleteFromQuery(String queryStr, Object[] params)
			throws Exception;

	/**
	 * 执行查询语句（HQL）。
	 * 
	 * @param queryStr
	 *            查询语句HQL。
	 * @return 查询结果。
	 */
	public List query(String queryStr) throws Exception;

	/**
	 * 执行查询语句（HQL）。
	 * 
	 * @param queryStr
	 *            查询语句HQL。
	 * @param params
	 *            参数值。
	 * @return 查询结果。
	 */
	public List query(String queryStr, Object[] params) throws Exception;

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
			final PageBean pageBean) throws Exception;

	public List query(final String queryStr, final Object[] params, int begin,
			int size) throws Exception;

	public int getCount(final String hql, final Object[] args) throws Exception;

	/**
	 * 查询出单个对象。
	 * 
	 * @param queryString
	 *            查询语句。
	 * @param params
	 *            参数值。
	 */
	public Object getSingle(String queryString, Object[] params)
			throws Exception;

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
			Object value) throws Exception;

	/**
	 * 查询一个数组中的ID的对象。
	 * 
	 * @param queryString
	 *            查询语句。
	 * @param ids
	 *            Id 数组。
	 * @return
	 */
	public List queryIn(String queryString, Serializable[] ids)
			throws Exception;

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
			String conditions) throws Exception;

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
			throws Exception;

	public Object getObjectById(String entityClass, Serializable id)
			throws Exception;

	/**
	 * 查询所有对象。
	 * 
	 * @param aClass
	 *            实体类。
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
