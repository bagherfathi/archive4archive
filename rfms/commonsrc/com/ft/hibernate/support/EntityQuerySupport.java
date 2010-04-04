package com.ft.hibernate.support;

import com.ft.commons.page.PageBean;

import com.ft.hibernate.commons.EntityUtils;

import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.impl.SessionFactoryImpl;

import org.hibernate.persister.entity.EntityPersister;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityQuerySupport {
    /** �����ڲ�ѯʱ�����ض����б�ĸ��� .*/
    private static final int MAX_SIZE = Integer.MAX_VALUE;

    /**
     * EntityQuerySupport ����.
     */
    private static EntityQuerySupport entityQuerySupport;

    /**
     * EntityMetadataSupport ����.
     */
    private EntityMetadataSupport entityMetadataSupport;

    /**
    * ʵ����.
    * @return
     */
    public static EntityQuerySupport getInstance() {
        if (entityQuerySupport == null) {
            entityQuerySupport = new EntityQuerySupport();
        }

        return entityQuerySupport;
    }

    /**
     * ���������Ĳ�ѯ���.
     * @param queryStr
     * @return
     */
    private String createCountQL(String queryStr) {
        int idxF = queryStr.indexOf("from");
        int idxD = queryStr.indexOf("distinct");

        if (idxD > 0) {
            String result =
                queryStr.substring(idxD + "distinct".length(), idxF);

            return "select distinct count(" + result + ") "
            + queryStr.substring(idxF);
        } else {
            return "select count(*) " + queryStr.substring(idxF);
        }
    }

    /**
     * ��ѯ��һ��HSQL��ֵ.
     *
     * @param queryString
     * @param params
     * @return
     */
    public Number count(
        String queryString, final Object[] params, Session session) {
        String countSql = createCountQL(queryString);
        Query countQuery = session.createQuery(countSql);

        for (int i = 0; i < params.length; i++) {
            countQuery.setParameter(i, params[i]);
        }

        Iterator iter = countQuery.iterate();
        Number countValue = null;

        if (iter.hasNext()) {
            countValue = (Number) iter.next();
        }

        if (countValue == null) {
            return new Integer(0);
        }

        return countValue;
    }

    /**
     * ����һ��ID��ѯ����.
     * @param clazz
     * @param keys
     * @param session
     * @return ���ض����б�
     */
    public List loadByIds(
        final Class clazz, final Serializable[] keys, Session session) {
        if ((keys == null) || (keys.length == 0)) {
            return new ArrayList();
        }

        if (session.getSessionFactory() instanceof SessionFactoryImpl) {
            SessionFactoryImpl factoryImpl =
                (SessionFactoryImpl) session.getSessionFactory();
            EntityPersister persister =
                (EntityPersister) factoryImpl.getEntityPersister(
                    clazz.getName());
            String keyName = persister.getIdentifierPropertyName();
            String queryStr =
                "from " + clazz.getName() + " obKeys where obKeys." + keyName
                + " in " + this.joinKeys(keys) + " order by obKeys."
                + keyName;
            List result = session.createQuery(queryStr).list();

            return result;
        }

        return new ArrayList();
    }

    /**
     * ���ݲ�ѯ����ѯ����.
     * @param queryString ��ѯ���
     * @param params ����
     * @param pageBean ��ҳ����
     * @param session Hibernate Session;
     * @return ���ض����б�
     */
    public List query(
        String queryString, Object[] params, PageBean pageBean,
        Session session) {
        Number countValue = this.count(queryString, params, session);
        pageBean.setRecordCount(countValue.longValue());

        return query(
            queryString, params, pageBean.getCurrentPageFirstRecord(),
            pageBean.getPageSize(), session);
    }

    /**
     * ���ݲ�ѯ����ѯ����.
     * @param queryString ��ѯ���
     * @param params ����
     * @param begin ����
     * @param size  ��С
     * @param session Hibernate Session;
     * @return ���ض����б�
     */
    public List query(
        String queryString, Object[] params, int begin, int count,
        Session session) {
        Query query = session.createQuery(queryString);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        query.setFirstResult(begin);
        query.setMaxResults(count);

        return query.list();
    }

    /**
     * ���ݶ����ѯ�����в�ѯ.
     * @param named ��ѯ����
     * @param params ����
     * @param begin ��ʼ
     * @param size  ��С
     * @param session Hibernate Session
     * @return
     */
    public List queryByNamed(
        String named, Object[] params, int begin, int size, Session session) {
        Query query = session.getNamedQuery(named);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        query.setFirstResult(begin);
        query.setMaxResults(size);

        return query.list();
    }

    /**
     * ���ݶ����ѯ�����в�.
     * @param queryString
     * @param params
     * @param pageBean
     * @param session
     * @return
     */
    public List queryByNamed(
        String queryString, Object[] params, PageBean pageBean,
        Session session) {
        Number countValue = this.count(queryString, params, session);
        pageBean.setRecordCount(countValue.longValue());

        return query(
            queryString, params, pageBean.getCurrentPageFirstRecord(),
            pageBean.getPageSize(), session);
    }

    /**
     * ����һ�����Խ���Count����.
     * @param entityCountClass
     * @param attribute
     * @param clazz
     * @param session
     * @return
     */
    public List countGroup(
        Class entityCountClass, String attribute, Class clazz, Session session) {
        String name = EntityUtils.getEntityName(clazz);
        String idName = entityMetadataSupport.getIdentityPropertyName(clazz);
        StringBuffer sql =
            new StringBuffer("select new ").append(
                entityCountClass.getName());
        sql.append("(").append("count(").append(name.toLowerCase()).append(
            ".").append(idName).append("),").append(name.toLowerCase())
           .append(".").append(attribute).append(") from ").append(name)
           .append(" ").append(name.toLowerCase()).append(" group by ")
           .append(name.toLowerCase()).append(".").append(attribute);

        return this.query(
            sql.toString(), new Object[0], 0, MAX_SIZE, session);
    }

    /**
     * ����һ��Ψһ���Բ�ѯ����.
     * @param clazz ��������
     * @param attribute ��������
     * @param value ֵ
     * @param session
     * @return
     */
    public Object getEntityByIdentityAttribute(
        Class clazz, String attribute, Object value, Session session) {
        StringBuffer sql = new StringBuffer("from ");
        sql.append(clazz.getName()).append(" where ");
        sql.append(attribute).append("=?");

        List list =
            this.query(
                sql.toString(), new Object[] { value }, 0, MAX_SIZE, session);

        if (!list.isEmpty()) {
            return list.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * �������Լ������Ƿ����.
     * @param clazz ʵ�����
     * @param attribute ��������
     * @param value ����ֵ
     * @param session Hibernate Session
     * @return �Ƿ����
     */

    //	public boolean exist(Class clazz, String attribute,Object value,Session session){
    //		StringBuffer sql=new StringBuffer("from ");
    //		sql.append(clazz.getName()).append(" where ");
    //		sql.append(attribute).append("=?");
    //		Number number = this.count(sql.toString(),new Object[]{value},session);
    //		return number.longValue() >0;
    //	}
    public boolean exist(
        Class clazz, String[] attribute, Object[] value, Session session) {
        return exist("and", clazz, attribute, value, session);
    }

    /**
     * �ж϶����Ƿ���������ݿ���.
     * @param type ֻ���� "and" ���� "or"
     * @param clazz
     * @param attribute
     * @param value
     * @param session
     * @return
     */
    public boolean exist(
        String type, Class clazz, String[] attribute, Object[] value,
        Session session) {
        if (!("and".equals(type) || "or".equals(type))) {
            throw new RuntimeException(
                "bad parameter for type, only and/or allowed!");
        }

        StringBuffer sql = new StringBuffer("from ");
        sql.append(clazz.getName()).append(" obj where ");
        sql.append("obj." + attribute[0]).append("=?");

        for (int i = 1; i < attribute.length; i++) {
            sql.append(" " + type + " ").append("obj." + attribute[i])
               .append("=?");
        }

        Number number = this.count(sql.toString(), value, session);

        return number.longValue() > 0;
    }

    protected String joinKeys(Object[] keys) {
        StringBuffer inStr = new StringBuffer("(");

        for (int i = 0; i < (keys.length - 1); i++) {
            inStr.append(keys[i]).append(",");
        }

        inStr.append(keys[keys.length - 1]).append(")");

        return inStr.toString();
    }

    /**
     * ���ز�������ָ��Ids�еĶ���.
     * @param entityClass
     * @param keys
     * @param session
     * @return
     */
    public List loadByNotInIds(
        Class entityClass, Serializable[] keys, Session session) {
        if (session.getSessionFactory() instanceof SessionFactoryImpl) {
            SessionFactoryImpl factoryImpl =
                (SessionFactoryImpl) session.getSessionFactory();
            EntityPersister persister =
                (EntityPersister) factoryImpl.getEntityPersister(
                    entityClass.getName());
            String keyName = persister.getIdentifierPropertyName();
            String queryStr = "from " + entityClass.getName() + " obKeys ";

            if ((keys == null) || (keys.length == 0)) {
            } else {
                queryStr = queryStr + "  where obKeys." + keyName
                    + " not in " + this.joinKeys(keys);
            }

            queryStr = queryStr + " order by obKeys." + keyName;

            List result = session.createQuery(queryStr).list();

            return result;
        }

        return new ArrayList();
    }

    /**
     * �������Լ������Ƿ񲻴���.
     * @param type
     * @param entityClass
     * @param attribute
     * @param value
     * @param session
     * @return
     */
    public boolean notExsit(
        String type, Class entityClass, String[] attribute, Object[] value,
        Session session) {
        // TODO Auto-generated method stub
        return false;
    }
}
