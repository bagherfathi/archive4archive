package com.ft.webui.tree;

import net.sf.navigator.menu.MenuRepository;

/**
 * ʹ��Struts Menu�������Ľӿ�
 */
public interface TreeBuilder {
	/**
	 * �����˵��ֿ�
	 * @return
	 */
	public MenuRepository buildMenuRepository(TreeBuilderContext context);
}
