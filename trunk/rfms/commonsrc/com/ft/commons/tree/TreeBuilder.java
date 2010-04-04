package com.ft.commons.tree;

import java.util.Map;

/**
 * 构建树形数据接口
 */
public interface TreeBuilder {
	/**
	 * 构建树形数据
	 * @return
	 */
	public TreeNode buildTreeNode(Map initParams);
}
