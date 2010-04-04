package com.ft.webui.input;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.CheckboxTag;
import org.apache.struts.util.LabelValueBean;
/**
 * 
 * Check box 输入标签，生成多个Checkbox 使用同一个名称
 */
public class CheckGroupTag extends CheckboxTag {

	private static final long serialVersionUID = 1L;
	//显示属性
	private String labelProperty;
	//显示数据源
	private String beanName;
	//显示数据源属性
	private String beanProperty;
	//显示值属性
	private String valueProperty;
	//每行显示个数
	private int cols;
	//每行分隔串
	private String split;
	//要进行配的值
	protected String match[] = null;

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
	 * 生成Check 输入
	 */
	public int doStartTag() throws JspException {
		calculateMatchValues();
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
			super.doStartTag();
			TagUtils.getInstance().write(pageContext, beans[i].getLabel());
		}
		TagUtils.getInstance().write(pageContext, sb.toString());

        //added 2006/10/9
        this.value = null;
        //end added
		return SKIP_BODY;
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
		this.cols=-1;
		this.split="";
		match = null;
	}
	/**
	 * 检查值是否匹配
	 * @param value
	 * @return
	 */
	public boolean isMatched(String value) {
		if ((this.match == null) || (value == null)) {
			return false;
		}

		for (int i = 0; i < this.match.length; i++) {
			if (value.equals(this.match[i]))
				return true;
		}

		return false;
	}

	private void calculateMatchValues() throws JspException {
		if (this.value != null) {
			this.match = new String[1];
			this.match[0] = this.value;

		} else {
			Object bean = TagUtils.getInstance()
					.lookup(pageContext, name, null);
			if (bean == null) {
				JspException e = new JspException(messages.getMessage(
						"getter.bean", name));

				TagUtils.getInstance().saveException(pageContext, e);
				throw e;
			}

			try {
				this.match = BeanUtils.getArrayProperty(bean, property);
				if (this.match == null) {
					this.match = new String[0];
				}

			} catch (IllegalAccessException e) {
				TagUtils.getInstance().saveException(pageContext, e);
				throw new JspException(messages.getMessage("getter.access",
						property, name));

			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				TagUtils.getInstance().saveException(pageContext, t);
				throw new JspException(messages.getMessage("getter.result",
						property, t.toString()));

			} catch (NoSuchMethodException e) {
				TagUtils.getInstance().saveException(pageContext, e);
				throw new JspException(messages.getMessage("getter.method",
						property, name));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.taglib.html.CheckboxTag#isChecked()
	 */
	protected boolean isChecked() throws JspException {
		return this.isMatched(this.value);
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
	 * @return Returns the match.
	 */
	public String[] getMatch() {
		return match;
	}

	/**
	 * @param match
	 *            The match to set.
	 */
	public void setMatch(String[] match) {
		this.match = match;
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

}
