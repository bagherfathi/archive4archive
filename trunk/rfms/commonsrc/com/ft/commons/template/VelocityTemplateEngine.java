package com.ft.commons.template;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.ListTool;

import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.IOException;
import java.io.Writer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class VelocityTemplateEngine implements TemplateEngine {
	/**
	 * VelocityEngine 的对象
	 */
	private VelocityEngine velocityEngine;
	private VelocityEngineFactory velocityEngineFactory;
	private Properties globalVar = new Properties();
	private DateFormat dateFormat = new SimpleDateFormat();
	private Map tools = new HashMap();

	/**
	 * Creates a new VelocityTemplateEngine object.
	 */
	@SuppressWarnings("unchecked")
	public VelocityTemplateEngine() {
		super();
		tools.put("list", new ListTool());
		tools.put("date", new DateTool());
	}

	/**
	 * 
	 * @return Returns the velocityEngine.
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * 
	 * @param velocityEngine
	 *            The velocityEngine to set.
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/*
         *
         */

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.onewaveinc.commons.template.TemplateEngine#execute(java.lang.Object,
	 *      java.io.Writer).
	 * @param obj
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	public void execute(Object obj, Writer writer) {
		if (obj != null) {
			String vmName = getTemplateName(obj.getClass());
			this.execute(obj, vmName, writer);
		}
	}

	/**
	 * 
	 * @param templateName
	 *            DOCUMENT ME!
	 * @param obj
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	public void execute(String templateName, Object obj, Writer writer) {
		if (obj != null) {
			this.execute(obj, templateName, writer);
		}
	}

	// end added
	/**
	 * DOCUMENT ME!
	 * 
	 * @param coll
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	public void execute(Collection coll, Writer writer) {
		for (Iterator iter = coll.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			this.execute(element, writer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onewaveinc.commons.template.TemplateEngine#execute(java.util.Map,
	 * java.lang.String, java.io.Writer)
	 */

	/**
	 * DOCUMENT ME!
	 * 
	 * @param props
	 *            DOCUMENT ME!
	 * @param vmName
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public void execute(Map props, String vmName, Writer writer) {
		Map model = initModel();

		model.putAll(props);

		try {
			VelocityEngineUtils.mergeTemplate(velocityEngine, vmName, model,
					writer);
		} catch (VelocityException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onewaveinc.commons.template.TemplateEngine#execute(java.util.Collection
	 * , java.lang.String, java.io.Writer)
	 */

	/**
	 * DOCUMENT ME!
	 * 
	 * @param coll
	 *            DOCUMENT ME!
	 * @param vmName
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	public void execute(Collection coll, String vmName, Writer writer) {
		for (Iterator iter = coll.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			this.execute(element, vmName, writer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onewaveinc.commons.template.TemplateEngine#execute(java.lang.Object,
	 * java.lang.String, java.lang.String, java.io.Writer)
	 */

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * @param objectName
	 *            DOCUMENT ME!
	 * @param vmName
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public void execute(Object obj, String objectName, String vmName,
			Writer writer) {
		Map model = initModel();
		model.put(objectName, obj);

		try {
			VelocityEngineUtils.mergeTemplate(velocityEngine, vmName, model,
					writer);
		} catch (VelocityException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Map initModel() {
		Map model = new HashMap();
		model.put("dateFormat", dateFormat);
		model.putAll(this.tools);
		model.putAll(this.globalVar);

		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onewaveinc.commons.template.TemplateEngine#execute(java.lang.Object,
	 * java.lang.String, java.io.Writer)
	 */

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * @param vmName
	 *            DOCUMENT ME!
	 * @param writer
	 *            DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public void execute(Object obj, String vmName, Writer writer) {
		Map model = initModel();
		model.put(getEntityName(obj.getClass()), obj);

		try {
			VelocityEngineUtils.mergeTemplate(velocityEngine, vmName, model,
					writer);
		} catch (VelocityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Properties getGlobalVar() {
		return globalVar;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param globalVar
	 *            DOCUMENT ME!
	 */
	public void setGlobalVar(Properties globalVar) {
		this.globalVar = globalVar;
	}

	/**
	 * 得到模板的名字
	 * 
	 * @param clazz
	 * 
	 * @return
	 */
	public static String getTemplateName(Class clazz) {
		String name = clazz.getName();
		name = name.replace('.', '/');

		return name + ".vm";
	}

	/**
	 * 得到实体的名字
	 * 
	 * @param clazz
	 * 
	 * @return
	 */
	public static String getEntityName(Class clazz) {
		String name = clazz.getName();
		int idx = name.lastIndexOf(".");
		name = name.substring(idx + 1);

		return name.toLowerCase().charAt(0) + name.substring(1);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public VelocityEngineFactory getVelocityEngineFactory() {
		return velocityEngineFactory;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param velocityEngineFactory
	 *            DOCUMENT ME!
	 */
	public void setVelocityEngineFactory(
			VelocityEngineFactory velocityEngineFactory) {
		this.velocityEngineFactory = velocityEngineFactory;

		try {
			velocityEngine = velocityEngineFactory.createVelocityEngine();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (VelocityException e) {
			e.printStackTrace();
		}
	}
}
