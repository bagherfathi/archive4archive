package com.ft.commons.lookup;

import java.util.Map;

/**
 * Lookuper仓库对象，负责加载Lookuper对象
 * 
 * 
 */
public interface LookuperRepository {
	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Map loadLookuper();

	/**
	 * DOCUMENT ME!
	 */
	public void clear();
}
