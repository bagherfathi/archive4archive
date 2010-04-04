package com.ft.hibernate.support;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EntityPersistentSupport {
    /**
     * 根据SQL语句删除一个对象
     * @param sql
     * @param session
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    public Object deleteBySQL(String sql, Session session)
        throws HibernateException, SQLException {
        int result = 0;
        PreparedStatement statement =
            session.connection().prepareStatement(sql);

        try {
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        return new Integer(result);
    }
}
