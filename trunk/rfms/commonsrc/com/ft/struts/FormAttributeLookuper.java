package com.ft.struts;

import com.ft.entity.EntityQuery;

import java.io.Serializable;

import java.util.List;


public class FormAttributeLookuper extends AbstractActionFormAttributeLookup {
    private EntityQuery entityQueryHelper;

    protected Object getEntity(Class typeClass, Serializable id) {
        return this.entityQueryHelper.load(typeClass, id);
    }

    protected List loadByIds(Class typeClass, Serializable[] values) {
        return this.entityQueryHelper.loadByIds(typeClass, values);
    }

    public EntityQuery getEntityQueryHelper() {
        return entityQueryHelper;
    }

    public void setEntityQueryHelper(EntityQuery entityQueryHelper) {
        this.entityQueryHelper = entityQueryHelper;
    }
}
