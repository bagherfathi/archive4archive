package com.ft.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.EntityManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.entity.EntityQuery;
import com.ft.struts.AbstractActionFormAttributeLookup;

/**
 * �����������еĲ��������ݶ����ļ��еĶ�Ӧ��ϵת��Ϊʵ��/DTOʵ������ʵ�����õ�Form�С�
 * 
 * @version 1.0
 */
public class FormAttributeLookuper extends AbstractActionFormAttributeLookup {
    private static final String SUFFIX = "DTO";

    private EntityQuery entityQueryHelper;

    private Map beanMapping;

    protected Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.getName().endsWith(SUFFIX)) {
            EntityManager manager = (EntityManager) beanMapping.get(typeClass
                    .getName());
            try {
                return manager.getEntity(typeClass, id);
            } catch (Exception e) {
                throw new CommonRuntimeException("", e);
            }
        } else {
            return this.entityQueryHelper.load(typeClass, id);
        }
    }

    protected List loadByIds(Class typeClass, Serializable[] values) {
        if (typeClass.getName().endsWith(SUFFIX)) {
            EntityManager manager = (EntityManager) beanMapping.get(typeClass
                    .getName());
            try {
                return manager.loadByIds(typeClass, values);
            } catch (Exception e) {
                throw new CommonRuntimeException("", e);
            }
        } else {
            return this.entityQueryHelper.loadByIds(typeClass, values);
        }
    }

    public EntityQuery getEntityQueryHelper() {
        return entityQueryHelper;
    }

    public void setEntityQueryHelper(EntityQuery entityQueryHelper) {
        this.entityQueryHelper = entityQueryHelper;
    }

    /**
     * @param beanMapping
     *                the beanMapping to set
     */
    public void setBeanMapping(Map beanMapping) {
        this.beanMapping = beanMapping;
    }
}
