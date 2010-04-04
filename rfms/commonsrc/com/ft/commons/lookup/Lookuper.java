package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

import java.util.List;

/**
 * 
 *         Lookuper∂‘œÛ
 * 
 */
public interface Lookuper {
	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getName();

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public List getResult();

	/**
	 * DOCUMENT ME!
	 * 
	 * @param value
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public LabelValueBean lookup(Object value);
}
