package com.ft.webui.input;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.IteratorAdapter;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

public class GroupTagHelper {

	protected static MessageResources messages = MessageResources
			.getMessageResources(Constants.Package + ".LocalStrings");

	public static LabelValueBean[] toLabelValueBean(PageContext pageContext,
			Object collection, String label, String value)
			throws JspException {

		List result = new ArrayList();

		// Acquire an iterator over the options collection
		Iterator iter = getIterator(collection);

	

		// Render the options
		while (iter.hasNext()) {

			Object bean = iter.next();
			Object beanLabel = null;
			Object beanValue = null;

			// Get the label for this option
			try {
				beanLabel = PropertyUtils.getProperty(bean, label);
				if (beanLabel == null) {
					beanLabel = "";
				}
			} catch (IllegalAccessException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.access", label, bean));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				JspException jspe = new JspException(messages.getMessage(
						"getter.result", label, t.toString()));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			} catch (NoSuchMethodException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.method", label, bean));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			}

			// Get the value for this option
			try {
				beanValue = PropertyUtils.getProperty(bean, value);
				if (beanValue == null) {
					beanValue = "";
				}
			} catch (IllegalAccessException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.access", value, bean));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				JspException jspe = new JspException(messages.getMessage(
						"getter.result", value, t.toString()));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			} catch (NoSuchMethodException e) {
				JspException jspe = new JspException(messages.getMessage(
						"getter.method", value, bean));
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;
			}
			String stringLabel = beanLabel.toString();
			String stringValue = beanValue.toString();
			LabelValueBean labelValue = new LabelValueBean();
			labelValue.setLabel(stringLabel);
			labelValue.setValue(stringValue);
			result.add(labelValue);
		}
		return (LabelValueBean[]) result.toArray(new LabelValueBean[result
				.size()]);
	}

	public static Iterator getIterator(Object collection) throws JspException {

		if (collection.getClass().isArray()) {
			collection = Arrays.asList((Object[]) collection);
		}

		if (collection instanceof Collection) {
			return (((Collection) collection).iterator());

		} else if (collection instanceof Iterator) {
			return ((Iterator) collection);

		} else if (collection instanceof Map) {
			return (((Map) collection).entrySet().iterator());

		} else if (collection instanceof Enumeration) {
			return new IteratorAdapter((Enumeration) collection);

		} else {
			throw new JspException(messages.getMessage(
					"optionsCollectionTag.iterator", collection.toString()));
		}
	}

}
