package com.ft.commons.lookup;

import com.ft.entity.EntityQuery;

import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * �����ݿ��в�ѯbean ����.
 * 
 */
public class HibernateLookuper extends AbstractLookuper {
	/**
	 * ��ѯʱ��ʵ����.
	 */
	private Class entityClass;

	/** ��ѯʱ���е�����. **/
	private String labelProperty;

	/** ��ѯʱ�е�ֵ. */
	private String valueProperty;

	/** ��ѯʱ������. */
	private String where;

	/** ��ѯʱȡ�ı���. */
	private String aliasName;

	/**
	 * ���ڲ�ѯ����.
	 */
	private EntityQuery entityQueryHelper;

	/**
	 * ��������������ֵ�������ݿ��в�ѯ���ļ��ϣ�������LabelValueBean����ļ���.
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
	 * ���ݱ���������ֵ�������ݿ��в�ѯ�����ϣ�ת����LabelValueBean ����.
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
	 * ��ȡʵ�����Class.
	 * 
	 * @return this.entityClass
	 */
	public Class getEntityClass() {
		return entityClass;
	}

	/**
	 * ����ʵ�����Class.
	 * 
	 * @param entityClass
	 */
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * ��ȡ��.
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
