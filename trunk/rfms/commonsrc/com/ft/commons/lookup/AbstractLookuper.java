package com.ft.commons.lookup;

public abstract class AbstractLookuper implements Lookuper {
	private String name;
	private String source;

	/**
	 * 
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
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
}
