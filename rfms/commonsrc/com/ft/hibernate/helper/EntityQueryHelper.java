package com.ft.hibernate.helper;

import com.ft.commons.page.PageBean;

import com.ft.entity.EntityQuery;

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

import org.springframework.dao.DataAccessException;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;


/**
 *
 * @spring.bean
 * id="entityQueryHelper"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class EntityQueryHelper extends HibernateDaoSupport
    implements EntityQuery {
    public final static String NAME = "entityQueryHelper";

    /**
     * 根据对象类型和ID查询一个对象
     * @param entityClass
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Object get(Class entityClass, Serializable id)
        throws DataAccessException {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    /**
     * 查询对一个HSQL的值
     * @param queryString
     * @param params
     * @return
     */
    public int count(String queryString, final Object[] params) {
        Number result =
            (Number) this.getHibernateTemplate()
                         .execute(new CountCallback(queryString, params));

        return result.intValue();
    }

    /**
     * 根据某个属性查询是否存在这样的记录
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     * @return 存在返回true
     */
    public boolean exist(final Class clazz, String attribute, Object value) {
        Boolean result =
            (Boolean) this.getHibernateTemplate()
                          .execute(
                new ExistCallback(
                    "and", clazz, new String[] { attribute },
                    new Object[] { value }));

        return result.booleanValue();
    }

    /**
     * 根据某个属性查询是否存在这样的记录
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     * @return 存在返回true
     */
    public boolean exist(
        final Class clazz, String[] attribute, Object[] value) {
        Boolean result =
            (Boolean) this.getHibernateTemplate()
                          .execute(
                new ExistCallback("and", clazz, attribute, value));

        return result.booleanValue();
    }

    /**
     * 根据某个属性查询是否存在这样的记录
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     * @return 存在返回true
     */
    public boolean existByOr(
        final Class clazz, String attribute, Object value) {
        Boolean result =
            (Boolean) this.getHibernateTemplate()
                          .execute(
                new ExistCallback(
                    "or", clazz, new String[] { attribute },
                    new Object[] { value }));

        return result.booleanValue();
    }

    /**
     * 根据某个属性查询是否存在这样的记录
     * @param clazz 对象类
     * @param attribute 属性名称
     * @param value 属性值
     * @return 存在返回true
     */
    public boolean existByOr(
        final Class clazz, String[] attribute, Object[] value) {
        Boolean result =
            (Boolean) this.getHibernateTemplate()
                          .execute(
                new ExistCallback("or", clazz, attribute, value));

        return result.booleanValue();
    }

    /**
     * 根据ID表名查询ID
     * @param clazz
     * @param keys
     * @return
     */
    public List loadByIds(final Class clazz, final Serializable[] keys) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(new FindByIdsCallback(clazz, keys));

        return result;
    }

    /**
     * 根据某个特定属性查询一个对象ID
     * @param name 对象名称
     * @param attribute 要求返回的属性
     * @param value 属性的参数值
     * @return 返回查询的实体对象
     */
    public Object getEntityByIdentityAttribute(
        Class clazz, String attribute, Object value) {
        Object result =
            (Object) this.getHibernateTemplate()
                         .execute(
                new GetByIdentityAttributeCallback(clazz, attribute, value));

        return result;
    }

    /**
     * 查询所有对象
     * @param aClass 加载所有的类
     * @return
     */
    public List loadAll(Class aClass) {
        return this.getHibernateTemplate().loadAll(aClass);
    }

    /**
     * 分页查询对象
     * @param queryStr 查询语句
     * @param params 参数值
     * @param pageBean 分页对象
     * @return 记录结果
     */
    public List query(
        final String queryStr, final Object[] params, final PageBean pageBean) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(
                new QueryPageCallback(queryStr, params, pageBean));

        return result;
    }

    /**
     * 分面查询
     * @param queryStr 查询语句
     * @param params 参数值
     * @param begin 起始记录数
     * @param length 查询个数
     * @return 查询结果
     */
    public List query(
        final String queryStr, final Object[] params, final int begin,
        final int length) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(
                new QueryCallback(queryStr, params, begin, length));

        return result;
    }

    /**
     * 根据ID得到一个对象，在调用之前要保证这个记录已经存在否则会抛出异常
     * @param entityClass 类名
     * @param key 主键值
     * @return entityClass的查询结果
     */
    public Object load(Class entityClass, Serializable key) {
        return this.getHibernateTemplate().load(entityClass, key);
    }

    /**
     * 查询
     * @param string 查询语句
     * @return 查询结果
     */
    public List query(String queryStr) {
        return this.getHibernateTemplate().find(queryStr);
    }

    /**
     * 带值数查询
     * @param string 查询语句
     * @param params 参数值
     * @return 查询结果
     */
    public List query(String queryStr, Object[] params) {
        return this.getHibernateTemplate().find(queryStr, params);
    }

    /**
     * 根据预定查询
     * @param 查询名
     * @return 查询结果
     */
    public List findByNamedQuery(String queryName) {
        return this.getHibernateTemplate().findByNamedQuery(queryName);
    }

    /**
     * 根据预定查询
     * @param queueName 查询名称
     * @param params 参数
     * @return 查询结果
     */
    public List findByNamedQuery(String queryName, Object[] params) {
        return this.getHibernateTemplate().findByNamedQuery(
            queryName, params);
    }

    /**
     * 
     * 根据预定查询
     * @param queryName 查询定义
     * @param params 参数值
     * @param size 返回记录数
     
     * @return 查询结果
     */
    public List queryByNamed(
        final String queryName, final Object[] params, final int size) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(
                new QueryByNamedCallback(queryName, params, 0, size));

        return result;
    }

    /**
     * 根据预定查询
     * @param name  查询定义
     * @param params  参数值
     * @param pageBean 分页对象
     * @return  查询结果
     */
    public List queryByNamed(
        final String named, final Object[] params, PageBean pageBean) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(
                new QueryByNamedPageCallback(named, params, pageBean));

        return result;
    }

    /**
     * @param clazz  要查询的类对象
     * @param pageBean 分页对象
     * @return 查询结果
     */
    public List query(Class clazz, PageBean pageBean) {
        return this.query("from " + clazz.getName(), new Object[0], pageBean);
    }
    /**
     * @param clazz  要查询语句
     * @param params 参数
     * @param maxSize 最大记录数
     * @return 查询结果
     */
    public List query(String string,Object[] params, int maxSize) {
        return query(string, params, 0, maxSize);
    }

    /**
     * 分组查询
     * @param attribute 分组属性
     * @param clazz 要查询的类
     * @return 查询结果 EntityGroupCount对象
     */
    public List countGroup(Class clazz, String attribute) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(
                new CountGroupCallback(
                    clazz, EntityGroupCount.class, attribute));

        return result;
    }

    /**
     * 根据查询语句，返回Iterator对象
     * @param 查询语句
     * @return 查询的Itearator对象
     */
    public Iterator iterate(String queryString) {
        return this.getHibernateTemplate().iterate(queryString);
    }
    /**
     * 查询对象中的一些属性
     */
    public List findAttribute(Class entityClass, String[] strings) {
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
     * 查询不在一个数组中的ID的对象
     * @param queryString   查询语句
     * @param ids  Id 数组
     * @return
     */ 
    public List queryByNotInIds(Class class1, Long[] ids) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(new FindByNotInIdsCallback(class1, ids));

        return result;
    }
    /**
     * 查询出单个对象
     * @param queryString   查询语句
     * @param params 参数值
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
     * 查询一个数组中的ID的对象
     * @param queryString   查询语句
     * @param ids  Id 数组
     * @return
     */
    public List queryIn(String queryString,Serializable[] ids){
    	   List result =
               (List) this.getHibernateTemplate()
                          .execute(
                   new QueryInCallBack(
                		   queryString, ids));
           return result;   	
    }
    /**
     * 查询一个组织中的ID
     * @param queryString   查询语句
     * @param ids  Id 数组
     * @param conditions 其它附加的条件
     * @return
     */
    public List queryIn(String queryString, Serializable[] ids, String conditions){
    	List result = (List) this.getHibernateTemplate().execute(
    			new QueryInCallBack(queryString, ids, conditions));
    	return result;
    }
    /**
     * 根据类的唯一属性查询对象的ID
     * @param entityClass 查询对象
     * @param keyAttribute  Id属性名称
     * @param attribue 唯一属性名
     * @param obj  唯一属性值
     * @return 
     */
    public Object getEntityIdByIdentityAttribute(
        Class entityClass, String keyAttribute, String attribute, Object obj) {
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
     * 根据名称查询单个对象
     * @param namedQuery 查询名称
     * @param params 参数
     * @return
     */
	public Object getSingleByNamedQuery(String queryName, Object[] params) {
		List result = this.getHibernateTemplate().findByNamedQuery(
				queryName, params);
		if(result.size() > 0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
}
