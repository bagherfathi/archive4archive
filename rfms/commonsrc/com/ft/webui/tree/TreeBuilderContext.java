package com.ft.webui.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.PageContext;

import net.sf.navigator.menu.MenuComponent;

public class TreeBuilderContext {
	private TreeNodeModel defaultModel = new TreeNodeModel();
	private List levelModel = new ArrayList();	
	private PageContext pageContext ;
	
	/**
	 * 树结点相关参数绑定
	 * @param menu
	 */
	public void fillAttribute(MenuComponent menu){
//		TreeNodeModel	model = this.getTreeNodeModel(menu.getMenuDepth());
//		if(menu.getTarget()==null){
//			menu.setTarget(model.getTarget());
//		}
//	    if(menu.getUrl() == null && model.getHref()!=null){
//	    	String url = ExpressionEvaluator.evaluate(model.getHref(),menu);
//			menu.setUrl(url);
//	    }
//	    if(menu.getImage()==null){
//	    	menu.setImage(model.getImg());
//	    }
//	    if(menu.getAltImage()==null){
//	    	menu.setAltImage(model.getOpenImg());
//	    }
//	    if(menu instanceof EntityMenuComponent){
//	    	   if(((EntityMenuComponent)menu).getMenuId()==null && model.getNodeId()!=null){
//	   	    	String url = ExpressionEvaluator.evaluate(model.getNodeId(),menu);
//	   			menu.setUrl(url);
//	   	    }
//	    }
	 
	 }
	
	/**
	 * 得到某层级的节点对象
	 * @param level
	 * @return
	 */
	public TreeNodeModel getTreeNodeModel(int level){
		for (Iterator iter = levelModel.iterator(); iter.hasNext();) {
			TreeNodeModel element = (TreeNodeModel) iter.next();
			if(element.getLevel() == level){
				return element;
			}
			
		}
		return defaultModel;
	}

	public void addModel(TreeNodeModel model) {
		this.levelModel.add(model);
		
	}

	public TreeNodeModel getDefaultModel() {
		return defaultModel;
	}

	public void setDefaultModel(TreeNodeModel defaultModel) {
		this.defaultModel = defaultModel;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}
	
	 
}
