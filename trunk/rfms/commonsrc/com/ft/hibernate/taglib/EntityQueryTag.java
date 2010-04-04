package com.ft.hibernate.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.commons.page.PageBean;
import com.ft.commons.web.SpringWebUtils;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.datasource.EntityDataSource;
import com.ft.hibernate.datasource.RequestParamsValueResolver;
import com.ft.hibernate.helper.EntityQueryHelper;


/**
 * 查询实体的Tag
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EntityQueryTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String var;
    private String className;
    private String query;
    private String type;
    private String queryName;
    private String pageSize;
    private String pageNo;
    private String scope;
    private String pagevarscope;
    private PageBean pageBean;
    private String pagevar;
    private String dataSource;
    private String order;
    private int size;
    protected List params = new ArrayList();

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        return EVAL_BODY_AGAIN;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        if (this.getDataSource() != null) {
            this.executeDataSource();
        } else {
            List list = this.query();

            if (this.var != null) {
                pageContext.setAttribute(
                    var, list, PageContextUtils.getScope(this.scope));
            }
        }

        this.clear();

        return EVAL_PAGE;
    }

    private void evalPageBean() {
        if (this.pageBean == null) {
            this.pageBean = new PageBean();
        }

        if (this.pageSize != null) {
            this.pageBean.setPageSize(Integer.parseInt(pageSize));
        } else {
            this.pageBean.setPageSize(100);
        }

        if (this.pageNo != null) {
            this.pageBean.setCurrentPage(Integer.parseInt(this.pageNo));
        } else {
            this.pageBean.setCurrentPage(1);
        }

        if (this.pagevar != null) {
            int inscope = PageContextUtils.getScope(this.pagevarscope);
            this.pageContext.setAttribute(
                this.pagevar, this.pageBean, inscope);
        }
    }

    public void executeDataSource() {
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        EntityDataSource aDataSource =
            (EntityDataSource) SpringWebUtils.getBean(
                request, this.dataSource);
        RequestParamsValueResolver paramResolver =
            new RequestParamsValueResolver(request);

        this.evalPageBean();

        int recCount = aDataSource.count(paramResolver);
        pageBean.setRecordCount(recCount);

        List result = aDataSource.getResultSet(pageBean, paramResolver);
        pageContext.setAttribute(this.var, result);

        pageContext.setAttribute("lengthOfList", new Long(result.size()));
    }

    public List query() throws JspException {
        ApplicationContext app =
            PageContextUtils.getApplicationContext(this.pageContext);

        EntityQuery queryHelper =
            (EntityQuery) app.getBean(EntityQueryHelper.NAME);
        
        StringBuffer buf = new StringBuffer();
        if(this.queryName!=null){
        	return queryHelper.queryByNamed(this.queryName, this.params.toArray(),Integer.MAX_VALUE);
        }else{
	        if (this.className != null) {
	            buf.append("from " + className);
	        } else if(this.query!=null) {
	            buf.append(this.query);
	        }
	        this.evalPageBean();
	        return queryHelper.query(
	            buf.toString(), this.params.toArray(), this.pageBean);
        } 
    }

    public void clear() {
        this.queryName = null;
        this.type = null;
        this.query = null;
        this.className = null;
        this.pagevar = null;
        this.pagevarscope = null;
        this.pageNo = null;
        this.pageSize = null;
        this.pageBean = null;
        this.params.clear();
    }

    public void release() {
        clear();
        super.release();
    }

    /**
     * @return Returns the size.
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size The size to set.
     */
    public void setSize(int size) {
        this.size = size;
    }

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
     * @return Returns the pagevarscope.
     */
    public String getPagevarscope() {
        return pagevarscope;
    }

    /**
     * @param pagevarscope The pagevarscope to set.
     */
    public void setPagevarscope(String pagevarscope) {
        this.pagevarscope = pagevarscope;
    }

    /**
    
    /**
     * @return Returns the scope.
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     *            The scope to set.
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return Returns the pagevar.
     */
    public String getPagevar() {
        return pagevar;
    }

    /**
     * @param pagevar
     *            The pagevar to set.
     */
    public void setPagevar(String pagevar) {
        this.pagevar = pagevar;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the var.
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *            The var to set.
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * @return Returns the className.
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     *            The className to set.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return Returns the query.
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query
     *            The query to set.
     */
    public void setQuery(String query) {
    	Evaluator Evalutator = new Evaluator();
    	try {
    		this.query  = (String) Evalutator.evaluate("query",query,String.class,this,pageContext);
		} catch (JspException e) {
			throw new RuntimeException(e);
		}
    
    }

    /**
     * @return Returns the queryName.
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * @param queryName
     *            The queryName to set.
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * @return Returns the pageNo.
     */
    public String getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo
     *            The pageNo to set.
     */
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return Returns the pageSize.
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            The pageSize to set.
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
