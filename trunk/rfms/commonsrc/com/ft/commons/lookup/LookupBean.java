package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 查询bean对象.
 * 
 */
public class LookupBean {

	/** */
	private String lookup;

	private String source;

	private String append;

	private boolean loaded;

	private boolean cached = true;

	private Collection results;
	private Collection appends = new ArrayList();

	/**
	 * Creates a new LookupBean object.
	 */
	public LookupBean() {
		super();
	}

	/**
	 * Creates a new LookupBean object.
	 * 
	 * @param lookup
	 * @param source
	 * @param append
	 */
	public LookupBean(String lookup, String source, String append) {
		this.lookup = lookup;
		this.source = source;
		this.append = append;
	}

	/**
	 * 
	 * @return Returns the append.
	 */
	public String getAppend() {
		return append;
	}

	/**
	 * 
	 * @param append
	 *            The append to set.
	 */
	public void setAppend(String append) {
		this.append = append;
		this.appends = LookupUtil.list(append);
	}

	/**
	 * 
	 * @return Returns the appends.
	 */
	public Collection getAppends() {
		return appends;
	}

	/**
	 * 
	 * @param appends
	 *            The appends to set.
	 */
	public void setAppends(Collection appends) {
		this.appends = appends;
	}

	/**
	 * 
	 * @return Returns the loaded.
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * 
	 * @param loaded
	 *            The loaded to set.
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * 
	 * @return Returns the lookup.
	 */
	public String getLookup() {
		return lookup;
	}

	/**
	 * 
	 * @param lookup
	 *            The lookup to set.
	 */
	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	/**
	 * 
	 * @return Returns the results.
	 */
	public Collection getResults() {
		return results;
	}

	/**
	 * 
	 * @param results
	 *            The results to set.
	 */
	public void setResults(Collection results) {
		this.results = results;
	}

	/**
	 * 
	 * @return Returns the source.
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 
	 * @param source
	 *            The source to set.
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取数组.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LabelValueBean[] getBeans() {
		List list = new ArrayList();
		list.addAll(this.appends);
		list.addAll(this.results);

		return (LabelValueBean[]) list.toArray(new LabelValueBean[0]);
	}

	/**
	 * 在LabelValueBean对象查询值和value相等的标记名.
	 * 
	 * @param value
	 * 
	 * @return
	 */
	public String lookupValue(String value) {
		for (Iterator iter = this.results.iterator(); iter.hasNext();) {
			LabelValueBean bean = (LabelValueBean) iter.next();

			if (bean.getValue().equals(value)) {
				return bean.getLabel();
			}
		}

		return null;
	}

	/**
	 * 得到LabelValueBean对象中的标记名
	 * 
	 * @param value
	 * 
	 * @return
	 */
	public String getLabel(Object value) {
		return lookupValue(value.toString());
	}

	/**
	 * 
	 * @return Returns the cached.
	 */
	public boolean isCached() {
		return cached;
	}

	/**
	 * 
	 * @param cached
	 *            The cached to set.
	 */
	public void setCached(boolean cached) {
		this.cached = cached;
	}
}
