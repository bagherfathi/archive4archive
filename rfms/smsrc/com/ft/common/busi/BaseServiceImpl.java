/**
 * 
 */
package com.ft.common.busi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ft.commons.page.PageBean;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumEntry;
import com.ft.sm.entity.EnumGroup;

/**
 * @author soler
 *
 */
public class BaseServiceImpl implements BaseService {

	protected BaseDao baseDao;
	

		/**
	 * @return the baseDao
	 */
	public BaseDao getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao the baseDao to set
	 */
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

		/**
		 * ��������/���¼�¼
		 */
		public void batchSave(Collection list) {
			baseDao.batchSave(list);
		}

		/**
		 * ����ɾ��ɾ��
		 */
		public void batchDelete(final Collection list) {
			if (list != null) {
				baseDao.batchDelete(list);
			}
		}

		/**
		 * ������¼
		 */
		public void save(Object obj) {
			baseDao.save(obj);
		}

		/**
		 * ���¼�¼
		 */
		public void update(Object obj) {

			baseDao.update(obj);
		}

		/**
		 * ɾ����¼
		 */
		public void delete(Object obj) {
			baseDao.delete(obj);
		}

		/**
		 * �������¼�¼
		 */
		public void batchUpdate(Collection selected) {
			baseDao.batchUpdate(selected);
		}

		/**
		 * ����/���¼�¼
		 */
		public void saveOrUpdate(Object obj) {
			baseDao.saveOrUpdate(obj);
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
			return baseDao.read(entityCls, identifier);
		}

		/**
		 * ͨ��sql����ѯ����
		 */
		public List queryObj(String queryStr) {
			return baseDao.query(queryStr);
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

			result=this.baseDao.getResultByPageBean(pageBean, queryStr, paramObjs);
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
			return getResultByPageBean(pageBean, queryStr, null);
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
		public List loadByIds(final Class clazz, final Serializable[] keys) {
			
			return baseDao.loadByIds(clazz, keys);
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
			List result = baseDao.query(queryStr, params);
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
			return baseDao.query(queryStr);
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
			return baseDao.query(queryStr, params);
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

			return baseDao.query(queryStr, params, pageBean);
		}

		public List query(final String queryStr, final Object[] params, int begin,
				int size) {
			return baseDao.query(queryStr, params, begin, size);
		}

		public int getCount(final String hql, final Object[] args) {
			return baseDao.getCount(hql, args);
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
			return baseDao.getSingle(queryString, params);
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
			
			return baseDao.getEntityByIdentityAttribute(clazz, attribute, value);
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
			return baseDao.queryIn(queryString, ids);
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
			return baseDao.queryIn(queryString, ids, conditions);
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
			return baseDao.getObjectById(entityClass, id);
		}
		
		public Object getObjectById(String entityClass, Serializable id)
		throws Exception {
			Class cl=Class.forName(entityClass);
	    return baseDao.getObjectById(cl, id);
      }

		/**
		 * ��ѯ���ж���
		 * 
		 * @param aClass
		 *            ʵ���ࡣ
		 * @return
		 */
		public List loadAll(Class aClass) {
			return baseDao.loadAll(aClass);
		}
		
		/**
		 * 
		 * @param id
		 * @param hqlTableName
		 */
		public void delObject(Long id,String hqlTableName) {
			Object[] a = new Object[] { id};
			String hsql="from " + hqlTableName + " where id =?";

			baseDao.deleteFromQuery(hsql, a);
		}
		
		
		public Object getEntityObject(Class entityClass, Serializable key) {
	            return this.baseDao.getObjectById(entityClass, key);
	    }

	    /* (non-Javadoc)
	     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
	     */
	    public void saveHisObject(Object object){
	        this.baseDao.saveOrUpdate(object);
	    }
}
