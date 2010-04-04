package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.FindNodeException;

/**
 * 树形数据类型接口
 */
public interface Tree extends Node {

    /**
     * 设定该树名称
     */
    public void setName(String name);

    public String getName();

    /**
     * 得到根节点
     *
     * @return 根节点
     */
    public TreeNode getRoot() throws FindNodeException;

    /**
     * 得到所有节点列表
     *
     * @return 所有节点
     */
    public List getNodeList() throws FindNodeException;


    /**
     * 按节点名称得到节点
     *
     * @param nodeName 节点名称
     * @return 满足条件的节点
     */
    public TreeNode[] findNodes(String nodeName) throws FindNodeException;

    /**
     * 按节点id得到节点
     *
     * @param nodeKey
     * @return 节点
     */
    public TreeNode findNodeByKey(String nodeKey) throws FindNodeException;

    /**
     * 得到树中层次为level的所有节点
     *
     * @param level 层次
     * @return 节点
     */
    public TreeNode[] getNodes(int level) throws FindNodeException;

    /**
     * 构建一个新的TreeNode
     *
     * @return 新建节点
     */
    public TreeNode createTreeNode();

    /**
     * 加入监听器，在需要时触发监听
     */
    public void addListener(TreeListener listener);

}
