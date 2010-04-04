package com.ft.hibernate.helper;

import com.ft.entity.EntityQuery;

import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EntityDAOHelper extends HibernateDaoSupport {
    protected EntityPersistentHelper persistentHelper =
        new EntityPersistentHelper();
    protected EntityQueryHelper queryHelper = new EntityQueryHelper();

    protected HibernateTemplate createHibernateTemplate(
        SessionFactory sessionFactory) {
        this.persistentHelper.setSessionFactory(sessionFactory);
        this.queryHelper.setSessionFactory(sessionFactory);

        return super.createHibernateTemplate(sessionFactory);
    }

    public void setPersistentHelper(EntityPersistentHelper persistentHelper) {
        this.persistentHelper = persistentHelper;
    }

    public void setQueryHelper(EntityQueryHelper queryHelper) {
        this.queryHelper = queryHelper;
    }

    public EntityPersistentHelper getPersistentHelper() {
        return persistentHelper;
    }

    public EntityQuery getQueryHelper() {
        return queryHelper;
    }
}
