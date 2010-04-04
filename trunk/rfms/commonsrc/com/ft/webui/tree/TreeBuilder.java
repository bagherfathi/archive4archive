package com.ft.webui.tree;

import net.sf.navigator.menu.MenuRepository;

/**
 * 使用Struts Menu构建树的接口
 */
public interface TreeBuilder {
	/**
	 * 构建菜单仓库
	 * @return
	 */
	public MenuRepository buildMenuRepository(TreeBuilderContext context);
}
