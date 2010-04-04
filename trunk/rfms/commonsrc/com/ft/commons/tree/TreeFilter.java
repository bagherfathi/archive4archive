package com.ft.commons.tree;


/**
 * 过滤树节点
 */
public interface TreeFilter {

    public static final int REMOVE = -1;

    /**
     * 检查node的status应该是什么，如果要删除则返回REMOVE常量值
     *
     * @param node 树节点
     * @return 满足否
     */
    public int check(TreeNode node);
}
