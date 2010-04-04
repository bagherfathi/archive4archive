package com.ft.webui.tree;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.navigator.menu.MenuRepository;

/**
 * 使用Struts Menu架构生成树
 * 
 * @jsp.tag name="tree" display-name="Tree-Tag" body-content="empty"
 */
public class TreeTag extends TagSupport{

	private static final long serialVersionUID = 3066172469741223927L;
	private String beanName;
	private String var = "MenuRepository";
	private TreeBuilderContext context = new TreeBuilderContext();


	public int doStartTag() throws JspException {
		context.setPageContext(this.pageContext);
//		TreeBuilder treeBuilder = (TreeBuilder) SpringWebUtils.getBean(
//				this.pageContext,beanName);
		
		TreeBuilder treeBuilder  = new SimpleTreeBuilder();
		
		MenuRepository defaultRepository = (MenuRepository) pageContext
				.getAttribute(MenuRepository.MENU_REPOSITORY_KEY,
						PageContext.APPLICATION_SCOPE);
		
		MenuRepository nodeRepository = treeBuilder.buildMenuRepository(context);

		nodeRepository.getDisplayers().putAll(defaultRepository.getDisplayers());

		pageContext.setAttribute(var, nodeRepository,PageContext.APPLICATION_SCOPE);
		return super.doStartTag();
	}

	public String getBeanName() {
		return beanName;
	}

	/**
     * @jsp.attribute description="Bean id of tree builder"
     *                required="true" rtexprvalue="false"
     */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getVar() {
		return var;
	}

	 /**
     * @jsp.attribute description="var name of"
     *                required="false" rtexprvalue="true"
     */
	public void setVar(String var) {
		this.var = var;
	}

	public void addNodeModel(TreeNodeModel model) {
		this.context.addModel(model);
		
	}


	public void setChecked(boolean checked) {
		context.getDefaultModel().setChecked(checked);
	}

	public void setHref(String href) {
		context.getDefaultModel().setHref(href);
	}

	public void setImg(String img) {
		context.getDefaultModel().setImg(img);
	}

	public void setLevel(int level) {
		context.getDefaultModel().setLevel(level);
	}

	public void setNodeId(String nodeId) {
		context.getDefaultModel().setNodeId(nodeId);
	}

	public void setOnclick(String onclick) {
		context.getDefaultModel().setOnclick(onclick);
	}

	public void setOpenImg(String openImg) {
		context.getDefaultModel().setOpenImg(openImg);
	}

	public void setTarget(String target) {
		context.getDefaultModel().setTarget(target);
	}
}
