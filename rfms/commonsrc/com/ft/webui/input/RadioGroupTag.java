package com.ft.webui.input;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.RadioTag;
import org.apache.struts.util.LabelValueBean;

public class RadioGroupTag extends RadioTag {

	private static final long serialVersionUID = 1L;

	private String labelProperty;

	private String beanName;

	private String beanProperty;

	private String valueProperty;

	private int cols;

	private String split;

	private String defaultValue;
	
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return Returns the cols.
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols
	 *            The cols to set.
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return Returns the split.
	 */
	public String getSplit() {
		return split;
	}

	/**
	 * @param split
	 *            The split to set.
	 */
	public void setSplit(String split) {
		this.split = split;
	}

	/**
	 * @return Returns the beanName.
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            The beanName to set.
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return Returns the beanProperty.
	 */
	public String getBeanProperty() {
		return beanProperty;
	}

	/**
	 * @param beanProperty
	 *            The beanProperty to set.
	 */
	public void setBeanProperty(String beanProperty) {
		this.beanProperty = beanProperty;
	}

	/**
	 * @return Returns the labelProperty.
	 */
	public String getLabelProperty() {
		return labelProperty;
	}

	/**
	 * @param labelProperty
	 *            The labelProperty to set.
	 */
	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}

	/**
	 * @return Returns the valueProperty.
	 */
	public String getValueProperty() {
		return valueProperty;
	}

	/**
	 * @param valueProperty
	 *            The valueProperty to set.
	 */
	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
	}

	public int doStartTag() throws JspException {

		if (this.beanName == null) {
			this.beanName = this.getName();
		}

		Object collection = TagUtils.getInstance().lookup(pageContext,
				beanName, beanProperty, null);

		if (collection == null) {
			JspException e = new JspException(messages
					.getMessage("optionsCollectionTag.collection"));
			TagUtils.getInstance().saveException(pageContext, e);
			throw e;
		}
		LabelValueBean[] beans = GroupTagHelper.toLabelValueBean(pageContext,
				collection, this.labelProperty, this.valueProperty);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < beans.length; i++) {
			this.setValue(beans[i].getValue());
			String radioTag = renderRadioElement(serverValue(), currentValue());
	        TagUtils.getInstance().write(super.pageContext, radioTag);
	        text = null;
			TagUtils.getInstance().write(pageContext, beans[i].getLabel());
		}
		TagUtils.getInstance().write(pageContext, sb.toString());

		return SKIP_BODY;

	}
	
	 private String serverValue()
     throws JspException
     {
     if(idName == null)
     {
         return value;
     } else
     {
         String serverValue = lookupProperty(idName, value);
         return serverValue != null ? serverValue : "";
     }
    }

 private String currentValue()
     throws JspException
 {
     String current = lookupProperty(name, property);
     current=current != null ? current : this.defaultValue;
     current=current != null ? current :"";
     return current;
 }

	/**
	 * Release any acquired resources.
	 */
	public void release() {
		super.release();
		this.labelProperty = "label";
		this.valueProperty = "name";
		this.beanName = null;
		this.beanProperty = null;
		this.cols = -1;
		this.split = "";
	}
}
