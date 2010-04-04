package com.ft.webui.table;

import org.extremecomponents.table.tag.TableTag;

import com.ft.commons.page.PageBean;

import javax.servlet.jsp.JspException;

/**
 * @jsp.tag name="entityTable" display-name="EntityTableTag"
 *          body-content="empty"
 * 
 */
public class EntityTableTag extends TableTag {
    private static final long serialVersionUID = 1L;

    private String dataSource;

    private String paramResolver;

    private String scope;

    private String pageBean;

    /**
     * 
     * @return
     */
    public String getScope() {
	return scope;
    }

    /**
     * @jsp.attribute description=" scope " required="false" rtexprvalue="true"
     * @param scope
     */
    public void setScope(String scope) {
	this.scope = scope;
    }

    public String getDataSource() {
	return dataSource;
    }

    /**
     * @jsp.attribute description=" dataSource " required="false"
     *                rtexprvalue="true"
     * @param dataSource
     */
    public void setDataSource(String dataSource) {
	this.dataSource = dataSource;
    }

    public String getParamResolver() {
	return paramResolver;
    }

    /**
     * @jsp.attribute description=" paramResolver " required="false"
     *                rtexprvalue="true"
     * @param dataSource
     */
    public void setParamResolver(String paramResolver) {
	this.paramResolver = paramResolver;
    }

    public int doStartTag() throws JspException {
	if (this.dataSource != null) {
	    super.setItems(this.dataSource);
	    String callbackname = HbiernateTableCallback.class.getName();
	    super.setRetrieveRowsCallback(callbackname);
	    super.setFilterRowsCallback(callbackname);
	    super.setSortRowsCallback(callbackname);

	} else if (this.pageBean != null) {
	    PageBean bean = (PageBean) this.pageContext.findAttribute(pageBean);
	    if (bean != null) {
		String callbackname = PageBeanTableCallback.class.getName();
		super.setRetrieveRowsCallback(callbackname);
		super.setFilterRowsCallback(callbackname);
		super.setSortRowsCallback(callbackname);
		pageContext.setAttribute("current_table_page_bean", bean);
		super.setRowsDisplayed("" + bean.getPageSize());
	    }
	}
	return super.doStartTag();
    }

    public int doEndTag() throws JspException {
	int result = super.doEndTag();
	if (this.dataSource != null) {
	    super.setRetrieveRowsCallback(null);
	    super.setFilterRowsCallback(null);
	    super.setSortRowsCallback(null);
	    super.setItems(null);
	    this.dataSource = null;
	} else if (this.pageBean != null) {
	    super.setRetrieveRowsCallback(null);
	    super.setFilterRowsCallback(null);
	    super.setSortRowsCallback(null);
	    super.setItems(null);
	    model.getContext().removePageAttribute("current_table_page_bean");
	}
	return result;
    }

    /**
     * @return Returns the pageBean.
     */
    public String getPageBean() {
	return pageBean;
    }

    /**
     * @param pageBean
     *                The pageBean to set.
     */
    public void setPageBean(String pageBean) {
	this.pageBean = pageBean;
    }
}
