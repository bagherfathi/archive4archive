package com.renhenet.fw.dao;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.renhenet.fw.orm.Persistent;

@SuppressWarnings("unchecked")
public class BaseJdbcDao extends JdbcDaoSupport {

	protected Persistent instanciateFromDB(Map map, Persistent obj) {
		Field afields[] = obj.getClass().getDeclaredFields();
		String name = null;
		try {
			for (int i = 0; i < afields.length; i++) {
				Field currentField = afields[i];
				name = getDbName(currentField.getName());
				Class type = currentField.getType();

				if (map.get(name) != null) {
					if (type == Integer.TYPE
							|| type.getName().equals("java.lang.Integer")) {

						currentField.set(obj, (Integer) map.get(name));
					}else if (type == Long.TYPE) {
						currentField.setLong(obj, (Long) map.get(name));
					}else if (type.getName().equals("java.lang.String")) {
						String tempVal = (String) map.get(name);

						currentField.set(obj, tempVal);
					} else if (type.getName().equals("java.util.Date")) {
						currentField.set(obj, (Date) map.get(name));
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to instanciate the class "
					+ getClass().getName() + "'s field " + name + " " + e);
		}

		name = null;
		return obj;
	}

	protected static String getSimpleName(String fullName) {
		int pos = fullName.lastIndexOf(".");
		if (pos == -1)
			return fullName;
		else
			return fullName.substring(pos + 1);
	}

	protected static String getDbName(String objName) {
		objName = getSimpleName(objName);
		String dbObjName;

		String delimiter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		dbObjName = getDbName(objName, delimiter);
		delimiter = "1234567890";
		dbObjName = getDbName(dbObjName, delimiter);
		return dbObjName;

	}

	protected static String getDbName(String objName, String delimiter) {
		try {
			StringTokenizer st = new StringTokenizer(objName, delimiter, true);

			String dbFieldName = st.nextToken();
			if (Character.isUpperCase(dbFieldName.charAt(0))) {
				if (st.hasMoreTokens())
					dbFieldName += st.nextToken();
			}

			while (st.hasMoreTokens()) {
				dbFieldName += "_" + st.nextToken().toLowerCase();

				if (st.hasMoreTokens())
					dbFieldName += st.nextToken().toLowerCase();
			}

			return dbFieldName;
		} catch (Exception e) {
			return null;
		}
	}
}
