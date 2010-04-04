package com.ft.web.sm.taglib;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.OptionsCollectionTag;
import org.apache.struts.taglib.html.SelectTag;
import com.ft.busi.common.SpringBeanUtils;
import com.ft.hibernate.helper.EntityQueryHelper;

/**
 * 从某个表中选择两个字段做为下拉列表
 * 
 * @version 1.0
 * 
 */
public class SelectOfTableTag extends OptionsCollectionTag {
	private static final long serialVersionUID = 450663963371697351L;

	private String labelProperty;

	private String valueProperty;

	private String tableName;

	private String whereCondition;
	
	/**
	 * @return the labelProperty
	 */
	public String getLabelProperty() {
		return labelProperty;
	}

	/**
	 * @param labelProperty the labelProperty to set
	 */
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the valueProperty
	 */
	public String getValueProperty() {
		return valueProperty;
	}

	/**
	 * @param valueProperty the valueProperty to set
	 */
	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
	}

	/**
	 * @return the whereCondition
	 */
	public String getWhereCondition() {
		return whereCondition;
	}

	/**
	 * @param whereCondition the whereCondition to set
	 */
	public void setWhereCondition(String whereCondition) {
		this.whereCondition = whereCondition;
	}

	public int doStartTag() throws JspException {
		SelectTag selectTag = (SelectTag) super.pageContext
				.getAttribute("org.apache.struts.taglib.html.SELECT");
		if (selectTag == null) {
			JspException e = new JspException(messages
					.getMessage("optionsCollectionTag.select"));
			TagUtils.getInstance().saveException(super.pageContext, e);
			throw e;
		}
		//LabelValueBean bean;
		Object collection = null;
		StringBuffer hql=new StringBuffer("select new org.apache.struts.util.LabelValueBean(").append(labelProperty).append(" ,").append(valueProperty);
		hql.append("||'') from ").append(tableName).append(" tbn where 1=1 ");
		if(whereCondition!=null && whereCondition.length()>0){
			hql.append(whereCondition);
		}
		EntityQueryHelper entityQueryHelper=(EntityQueryHelper)SpringBeanUtils.getBean("entityQueryHelper");
		collection=entityQueryHelper.query(hql.toString());
		
		if (collection == null) {
			JspException e = new JspException(messages
					.getMessage("optionsCollectionTag.collection"));
			TagUtils.getInstance().saveException(super.pageContext, e);
			throw e;
		}
		Iterator iter = getIterator(collection);
		StringBuffer sb = new StringBuffer();
		String stringLabel;
		String stringValue;
		for (; iter.hasNext(); addOption(sb, stringLabel, stringValue,
				selectTag.isMatched(stringValue))) {
			Object bean = iter.next();
			Object beanLabel = null;
			Object beanValue = null;
			try {
				beanLabel = PropertyUtils.getProperty(bean, label);
				if (beanLabel == null)
					beanLabel = "";
			} catch (IllegalAccessException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.access", label, bean));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				JspException jspe = new JspException(messages.getMessage(
						"getter.result", label, t.toString()));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			} catch (NoSuchMethodException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.method", label, bean));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			}
			try {
				beanValue = PropertyUtils.getProperty(bean, value);
				if (beanValue == null)
					beanValue = "";
			} catch (IllegalAccessException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.access", value, bean));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				JspException jspe = new JspException(messages.getMessage(
						"getter.result", value, t.toString()));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			} catch (NoSuchMethodException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.method", value, bean));
				TagUtils.getInstance().saveException(super.pageContext, jspe);
				throw jspe;
			}
			stringLabel = beanLabel.toString();
			stringValue = beanValue.toString();
		}

		TagUtils.getInstance().write(super.pageContext, sb.toString());
		return 0;
	}
 

}
