package com.ft.commons.lookup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.util.LabelValueBean;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

/**
 * SQL 查询 bean.
 * 
 */
public class SQLLookuper extends JdbcDaoSupport implements Lookuper {
	/** 日志的定义. */
	protected final Log logger = LogFactory.getLog(getClass());
	private String name;
	private String source;
	private String valueField;
	private String labelField;

	/**
	 * 
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getValueField() {
		return valueField;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param valueField
	 *            DOCUMENT ME!
	 */
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	/**
	 * 获取bean 的名字
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getSource() {
		return source;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param source
	 *            DOCUMENT ME!
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取结果集
	 */
	public List getResult() {
		LookupQuery query = new LookupQuery(this.getDataSource(), source);
		List result = query.execute();

		return result;
	}

	/**
	 * 根据source 和 valueField 值，拼凑SQL 查询语句 查询bean
	 */
	public LabelValueBean lookup(Object value) {
		String sql = this.source + "where " + this.valueField + " = " + value;
		LookupQuery query = new LookupQuery(this.getDataSource(), sql);
		List result = query.execute();

		if (0 == result.size()) {
			return null;
		} else {
			return (LabelValueBean) result.iterator().next();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getLabelField() {
		return labelField;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param labelField
	 *            DOCUMENT ME!
	 */
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}

	protected class LookupQuery extends MappingSqlQuery {
		/**
		 * Creates a new instance of VisitsQuery
		 * 
		 * @param ds
		 *            the DataSource to use for the update.
		 */
		protected LookupQuery(DataSource ds, String sql) {
			super(ds, sql);
			// declareParameter(new SqlParameter(Types.INTEGER));
			compile();
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			LabelValueBean result = new LabelValueBean(rs.getString(2), rs
					.getString(1));

			return result;
		}
	}
}
