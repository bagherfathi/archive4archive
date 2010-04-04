package com.ft.commons.lookup;

import com.ft.entity.EntityQuery;

import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 在数据库中查询bean 对象.
 * 
 */
public class HibernateLookuper extends AbstractLookuper {
	/**
	 * 查询时的实体类.
	 */
	private Class entityClass;

	/** 查询时的列的属性. **/
	private String labelProperty;

	/** 查询时列的值. */
	private String valueProperty;

	/** 查询时的条件. */
	private String where;

	/** 查询时取的别名. */
	private String aliasName;

	/**
	 * 用于查询的类.
	 */
	private EntityQuery entityQueryHelper;

	/**
	 * 根据类名和属性值，从数据库中查询出的集合，构建出LabelValueBean对象的集合.
	 */
	@SuppressWarnings("unchecked")
	public List getResult() {
		List result = new ArrayList();
		List list = entityQueryHelper.findAttribute(entityClass, new String[] {
				valueProperty, labelProperty });

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object[] objs = (Object[]) iter.next();
			LabelValueBean bean = new LabelValueBean(objs[1].toString(),
					objs[0].toString());
			result.add(bean);
		}

		return result;
	}

	/**
	 * 根据别名和属性值，从数据库中查询出集合，转化成LabelValueBean 对象.
	 */
	public LabelValueBean lookup(Object value) {
		if (this.aliasName == null) {
			this.aliasName = "obj";
		}

		List list = entityQueryHelper.query("select new "
				+ LabelLongValueBean.class.getName() + "(" + aliasName + "."
				+ labelProperty + "," + aliasName + "." + valueProperty
				+ ") from  " + entityClass.getName() + " " + aliasName
				+ " where " + aliasName + "." + valueProperty + "=?",
				new Object[] { value });

		if (list.size() == 0) {
			return null;
		} else {
			return (LabelValueBean) list.iterator().next();
		}
	}

	/**
	 * 获取实体类的Class.
	 * 
	 * @return this.entityClass
	 */
	public Class getEntityClass() {
		return entityClass;
	}

	/**
	 * 设置实体类的Class.
	 * 
	 * @param entityClass
	 */
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 获取列.
	 * 
	 * @return this.labelProperty
	 */
	public String getLabelProperty() {
		return labelProperty;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param labelProperty
	 *            DOCUMENT ME!
	 */
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getValueProperty() {
		return valueProperty;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param valueProperty
	 *            DOCUMENT ME!
	 */
	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param entityQueryHelper
	 *            DOCUMENT ME!
	 */
	public void setEntityQueryHelper(EntityQuery entityQueryHelper) {
		this.entityQueryHelper = entityQueryHelper;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param where
	 *            DOCUMENT ME!
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param aliasName
	 *            DOCUMENT ME!
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public static class LabelLongValueBean extends LabelValueBean {

		private static final long serialVersionUID = 1L;

		public LabelLongValueBean(String desc, Long id) {
			super(desc, id.toString());
		}
	}
}
