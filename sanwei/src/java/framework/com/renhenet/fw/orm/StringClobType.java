package com.renhenet.fw.orm;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.type.StringType;

public class StringClobType extends StringType {
	private static final long serialVersionUID = -4599218988637415265L;

	public Object get(ResultSet resultSet, String name) throws SQLException {
		Reader reader = resultSet.getCharacterStream(name);
		if (reader == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		try {
			char chars[] = new char[4096];
			int i = reader.read(chars);
			while (i != -1) {
				buffer.append(chars, 0, i);
				i = reader.read(chars);
			}

		} catch (IOException e) {
			throw new SQLException(e.getMessage());
		}
		return buffer.toString();
	}

	public void set(PreparedStatement statement, Object value, int index)
			throws SQLException {

		StringReader reader = new StringReader((String) value);
		statement.setCharacterStream(index, reader, ((String) value).length());
	}

	public int sqlType() {
		return Types.VARCHAR;
	}

	public boolean hasNiceEquals() {
		return false;
	}
}