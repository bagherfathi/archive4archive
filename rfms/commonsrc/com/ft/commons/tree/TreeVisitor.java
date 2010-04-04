package com.ft.commons.tree;


/**
 * 树结点访问者接口
 */
public interface TreeVisitor {

    /**
     * 到某一节点时
     *
     * @param node
     */
    public void visit(TreeNode node);

    /**
     * 层次上升时
     */
    public void onAscent();

    /**
     * 层次下降时
     */
    public void onDescent();

    /**
     * 目前遍历到的层次
     *
     * @return 层次
     */
    public int currentLevel();
}
