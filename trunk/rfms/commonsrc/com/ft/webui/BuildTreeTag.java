package com.ft.webui;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;
import com.ft.commons.web.SpringWebUtils;

/**
 * 调用TreeBuilder生成树形结构数据并保存到页面中.
 * 
 * @jsp.tag name="buildTree" display-name="BuildTreeTag" body-content="empty"
 */
public class BuildTreeTag extends TagSupport{
	private String beanName;
	private String var = "rootNode";
    
    protected Map params = new HashMap();
	
	public int doStartTag() throws JspException {
		return EVAL_BODY_AGAIN;
	}

	public String getBeanName() {
		return beanName;
	}
    
    public int doEndTag() throws JspException {
        TreeBuilder treeBuilder = (TreeBuilder) SpringWebUtils.getBean(
                this.pageContext,beanName);
        
        TreeNode treeNode = treeBuilder.buildTreeNode(params);

        pageContext.setAttribute(var, treeNode,PageContext.APPLICATION_SCOPE);
        
        clear();
        
        return EVAL_PAGE;
    }

    /**
     * 
     */
    private void clear() {
        this.beanName = null;
        this.var = null;
        if(this.params!=null){
        	this.params.clear();
        }
    }

    /**
     * @jsp.attribute description="TreeBuilder bean name"
     * required="true" rtexprvalue="false"
     */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getVar() {
		return var;
	}

	 /**
	  * @jsp.attribute required="false" rtexprvalue="false"
	  */
	public void setVar(String var) {
		this.var = var;
	}
}
