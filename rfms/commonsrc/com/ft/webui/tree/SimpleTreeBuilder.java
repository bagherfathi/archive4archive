package com.ft.webui.tree;

import java.util.Iterator;

import javax.servlet.jsp.PageContext;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

/**
 * 
 * 生成相关树
 */
public class SimpleTreeBuilder implements TreeBuilder {

	public MenuRepository buildMenuRepository(TreeBuilderContext context) {
		
		MenuRepository defaultRepository = 
			(MenuRepository) context.getPageContext()
				.getAttribute(MenuRepository.MENU_REPOSITORY_KEY,
						PageContext.APPLICATION_SCOPE);
		MenuComponent menu = defaultRepository.getMenu("SSOMenu1");
		context.fillAttribute(menu);
		for (Iterator iter = menu.getComponents().iterator(); iter.hasNext();) {
			MenuComponent submenu = (MenuComponent) iter.next();
			context.fillAttribute(submenu);
		}
		
		return defaultRepository;
	}

}
