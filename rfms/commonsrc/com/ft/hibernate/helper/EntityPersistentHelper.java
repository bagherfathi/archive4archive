package com.ft.hibernate.helper;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Collection;
import java.util.List;


/**
 * @spring.bean
 * id="entityPersistentHelper"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class EntityPersistentHelper extends HibernateDaoSupport {
    public void batchSave(final Collection list) {
        this.getHibernateTemplate().saveOrUpdateAll(list);
    }

    public void batchDelete(final Collection list) {
        if (list != null) {
            this.getHibernateTemplate().deleteAll(list);
        }
    }

    /**
     * @param role
     */
    public void save(Object obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    /**
     * @param element
     */
    public void update(Object element) {
        this.getHibernateTemplate().update(element);
    }

    /**
     * @param string
     */
    public void delete(String string) {
        this.getHibernateTemplate().delete(string);
    }

    /**
     * @param sectionOffice
     */
    public void delete(Object obj) {
        this.getSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().delete(obj);
    }

    public void deleteSQL(final String str) {
        this.getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                    PreparedStatement statement =
                        session.connection().prepareStatement(str);

                    try {
                        statement.execute();
                    } catch (SQLException e) {
                        if (statement != null) {
                            statement.close();
                        }

                        throw e;
                    }

                    return null;
                }
            });
    }

    /**
     * @param selected
     */
    public void batchUpdate(Collection selected) {
        this.getHibernateTemplate().saveOrUpdateAll(selected);
    }

    /**
     * @param string
     * @param objects
     * @param types
     */
    public void deleteFromQuery(String string, Object[] objects) {
        List result = this.getHibernateTemplate().find(string, objects);
        batchDelete(result);
    }

    public void insert(Object obj) {
        this.getHibernateTemplate().save(obj);
    }

    public void saveOrUpdate(Object obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    public boolean isPersistent(Object entity) {
        return this.getSession().contains(entity);
    }
}
