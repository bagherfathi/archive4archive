package com.ft.busi.dto;

import java.util.List;

import com.ft.commons.page.PageBean;

/**
 * @version 1.0
 */
public class ResultDTO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4821337808770106101L;
	
	private List result;
	private PageBean pageBean;
	/**
	 * @return Returns the pageBean.
	 */
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * @param pageBean The pageBean to set.
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	/**
	 * @return Returns the result.
	 */
	public List getResult() {
		return result;
	}
	/**
	 * @param result The result to set.
	 */
	public void setResult(List result) {
		this.result = result;
	}

}
