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
     * ���ݶ������ͺ�ID��ѯһ������
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
     * ��ѯ��һ��HSQL��ֵ
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
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     * @return ���ڷ���true
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
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     * @return ���ڷ���true
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
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     * @return ���ڷ���true
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
     * ����ĳ�����Բ�ѯ�Ƿ���������ļ�¼
     * @param clazz ������
     * @param attribute ��������
     * @param value ����ֵ
     * @return ���ڷ���true
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
     * ����ID������ѯID
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
     * ����ĳ���ض����Բ�ѯһ������ID
     * @param name ��������
     * @param attribute Ҫ�󷵻ص�����
     * @param value ���ԵĲ���ֵ
     * @return ���ز�ѯ��ʵ�����
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
     * ��ѯ���ж���
     * @param aClass �������е���
     * @return
     */
    public List loadAll(Class aClass) {
        return this.getHibernateTemplate().loadAll(aClass);
    }

    /**
     * ��ҳ��ѯ����
     * @param queryStr ��ѯ���
     * @param params ����ֵ
     * @param pageBean ��ҳ����
     * @return ��¼���
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
     * �����ѯ
     * @param queryStr ��ѯ���
     * @param params ����ֵ
     * @param begin ��ʼ��¼��
     * @param length ��ѯ����
     * @return ��ѯ���
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
     * ����ID�õ�һ�������ڵ���֮ǰҪ��֤�����¼�Ѿ����ڷ�����׳��쳣
     * @param entityClass ����
     * @param key ����ֵ
     * @return entityClass�Ĳ�ѯ���
     */
    public Object load(Class entityClass, Serializable key) {
        return this.getHibernateTemplate().load(entityClass, key);
    }

    /**
     * ��ѯ
     * @param string ��ѯ���
     * @return ��ѯ���
     */
    public List query(String queryStr) {
        return this.getHibernateTemplate().find(queryStr);
    }

    /**
     * ��ֵ����ѯ
     * @param string ��ѯ���
     * @param params ����ֵ
     * @return ��ѯ���
     */
    public List query(String queryStr, Object[] params) {
        return this.getHibernateTemplate().find(queryStr, params);
    }

    /**
     * ����Ԥ����ѯ
     * @param ��ѯ��
     * @return ��ѯ���
     */
    public List findByNamedQuery(String queryName) {
        return this.getHibernateTemplate().findByNamedQuery(queryName);
    }

    /**
     * ����Ԥ����ѯ
     * @param queueName ��ѯ����
     * @param params ����
     * @return ��ѯ���
     */
    public List findByNamedQuery(String queryName, Object[] params) {
        return this.getHibernateTemplate().findByNamedQuery(
            queryName, params);
    }

    /**
     * 
     * ����Ԥ����ѯ
     * @param queryName ��ѯ����
     * @param params ����ֵ
     * @param size ���ؼ�¼��
     
     * @return ��ѯ���
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
     * ����Ԥ����ѯ
     * @param name  ��ѯ����
     * @param params  ����ֵ
     * @param pageBean ��ҳ����
     * @return  ��ѯ���
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
     * @param clazz  Ҫ��ѯ�������
     * @param pageBean ��ҳ����
     * @return ��ѯ���
     */
    public List query(Class clazz, PageBean pageBean) {
        return this.query("from " + clazz.getName(), new Object[0], pageBean);
    }
    /**
     * @param clazz  Ҫ��ѯ���
     * @param params ����
     * @param maxSize ����¼��
     * @return ��ѯ���
     */
    public List query(String string,Object[] params, int maxSize) {
        return query(string, params, 0, maxSize);
    }

    /**
     * �����ѯ
     * @param attribute ��������
     * @param clazz Ҫ��ѯ����
     * @return ��ѯ��� EntityGroupCount����
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
     * ���ݲ�ѯ��䣬����Iterator����
     * @param ��ѯ���
     * @return ��ѯ��Itearator����
     */
    public Iterator iterate(String queryString) {
        return this.getHibernateTemplate().iterate(queryString);
    }
    /**
     * ��ѯ�����е�һЩ����
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
     * ��ѯ����һ�������е�ID�Ķ���
     * @param queryString   ��ѯ���
     * @param ids  Id ����
     * @return
     */ 
    public List queryByNotInIds(Class class1, Long[] ids) {
        List result =
            (List) this.getHibernateTemplate()
                       .execute(new FindByNotInIdsCallback(class1, ids));

        return result;
    }
    /**
     * ��ѯ����������
     * @param queryString   ��ѯ���
     * @param params ����ֵ
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
     * ��ѯһ�������е�ID�Ķ���
     * @param queryString   ��ѯ���
     * @param ids  Id ����
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
     * ��ѯһ����֯�е�ID
     * @param queryString   ��ѯ���
     * @param ids  Id ����
     * @param conditions �������ӵ�����
     * @return
     */
    public List queryIn(String queryString, Serializable[] ids, String conditions){
    	List result = (List) this.getHibernateTemplate().execute(
    			new QueryInCallBack(queryString, ids, conditions));
    	return result;
    }
    /**
     * �������Ψһ���Բ�ѯ�����ID
     * @param entityClass ��ѯ����
     * @param keyAttribute  Id��������
     * @param attribue Ψһ������
     * @param obj  Ψһ����ֵ
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
     * �������Ʋ�ѯ��������
     * @param namedQuery ��ѯ����
     * @param params ����
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
