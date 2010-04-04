package com.ft.hibernate.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.metadata.ClassMetadata;

import org.hibernate.type.Type;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * 实体的元数据
 *
 */
public class EntityMetadataSupport {
    private final static Log logger =
        LogFactory.getLog(EntityMetadataSupport.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据类，获取实体的Identity属性名
     * @param clazz
     * @return
     */
    public String getIdentityPropertyName(Class clazz) {
        ClassMetadata catMeta = this.getClassMetadata(clazz);

        return catMeta.getIdentifierPropertyName();
    }

    public boolean isIdentityAttribute(Class clazz, String attribute) {
        return false;
    }

    /**
     * 根据类，获取实体名
     * @param clazz
     * @return
     */
    public String getEntityName(Class clazz) {
        ClassMetadata catMeta;
        catMeta = getClassMetadata(clazz);

        return catMeta.getEntityName();
    }

    /**
     * 根据实体名，转化成类
     * @param name
     * @return
     */
    public Class getEntityClass(String name) {
        ClassMetadata catMeta = sessionFactory.getClassMetadata(name);

        return catMeta.getMappedClass(EntityMode.POJO);
    }

    /**
     * 根据类，一组实体，获取实体的Identity对象数组
     * @param clazz
     * @param entities
     * @return
     */
    public Object[] getEntityIdentity(Class clazz, Collection entities) {
        ClassMetadata cateMeta = this.getClassMetadata(clazz);
        Object[] result = new Object[entities.size()];
        int i = 0;

        for (Iterator iter = entities.iterator(); iter.hasNext(); i++) {
            Object element = iter.next();
            result[i] = cateMeta.getIdentifier(
                    element.getClass(), EntityMode.POJO);
        }

        return result;
    }

    public Class getProperytType(Class clazz, String attribute) {
        ClassMetadata cateMeta = this.getClassMetadata(clazz);
        Type type = cateMeta.getPropertyType(attribute);

        return type.getReturnedClass();
    }

    public Map getAllClassMateData() {
        return sessionFactory.getAllClassMetadata();
    }

    private ClassMetadata getClassMetadata(Class clazz) {
        ClassMetadata catMeta;

        try {
            catMeta = sessionFactory.getClassMetadata(clazz);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalArgumentException(e.getMessage());
        }

        return catMeta;
    }

    public Object getKeyValue(Object entity) {
        ClassMetadata cateMeta = this.getClassMetadata(entity.getClass());

        return cateMeta.getIdentifier(entity.getClass(), EntityMode.POJO);
    }
}
