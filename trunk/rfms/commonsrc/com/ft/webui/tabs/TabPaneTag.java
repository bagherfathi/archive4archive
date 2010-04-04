package com.ft.webui.tabs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;


/**
 *
 *        JSP Tag that renders individual tab pane components and their tabs.
 */
public final class TabPaneTag extends BodyTagSupport {
    private String id;
    private String tabTitle;
    private TabContainerTag tabContainer;
    private TabPaneModule module;
    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTabTitle(final String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabTitle() {
        return tabTitle;
    }



    public TabContainerTag getTabContainer() {
        if (null == tabContainer) {
            tabContainer = (TabContainerTag) findAncestorWithClass(
                    this, TabContainerTag.class);
        }

        return tabContainer;
    }

    private void addToContainer() {
        module = new TabPaneModule();
    	module.setId(this.id);
    	module.setTabTitle(this.tabTitle);
        getTabContainer().addChild(module);
    }

   



    public void release() {
		
		super.release();
		clear();
	
	}
    
    public void clear(){
    	this.id = null;
		this.module = null;
		this.tabTitle = null;
		this.tabContainer = null;
    }

	public void setBodyContent(BodyContent arg0) {
        
        super.setBodyContent(arg0);
    }

    public int doStartTag() throws JspException {
        Evaluator aEvaluator = new Evaluator();
        setId((String) aEvaluator.evaluate("id", this.id,
                String.class, this, pageContext));
        setTabTitle( (String) aEvaluator.evaluate("tabTitle", this.tabTitle,
                String.class, this, pageContext));
        addToContainer();
        
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspException {      
    	this.module.setContent(this.getBodyContent().getString());
    	try {
			this.getBodyContent().clear() ;
		} catch (IOException e) {
			throw new JspException(e);
		}
		clear();
        return super.doEndTag();
    }

//    public int doAfterBody() throws JspException {
//        // TODO Auto-generated method stub
//        return super.doAfterBody();
//    }
//
//    public void doInitBody() throws JspException {
//        // TODO Auto-generated method stub
//        super.doInitBody();
//    }


}
