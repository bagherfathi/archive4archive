package com.ft.commons.tree;

import com.ft.commons.tree.exception.TreeListenerException;

/**
 * 树事件监听器
 */
public interface TreeListener {
    /**
     * 从树上删除一个节点时触发
     *
     * @param node 要删除的节点
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void remove(TreeNode node) throws TreeListenerException;

    /**
     * 向一个节点添加子节点时触发
     *
     * @param parent 父节点
     * @param node   要添加的节点
     * @param index  加入的节点在子节点中的顺序
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void addChild(TreeNode parent, TreeNode node, int index) throws TreeListenerException;

    /**
     * 更新一个节点时触发
     *
     * @param node 节点
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void update(TreeNode node) throws TreeListenerException;
}
