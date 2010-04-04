package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.AddNodeException;
import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;

/**
 * 树形结构的相关操作
 */
public interface Node {
    /**
     * 得到父节点
     *
     * @return 父节点
     */
    public Node getParent();

    /**
     * 得到所有子节点
     *
     * @return 所有子节点
     */
    public List getChildren() throws FindNodeException;

    /**
     * 得到第index个子节点
     *
     * @return 该子节点
     */
    public Node getChild(int index) throws FindNodeException;

    /**
     * 是否根节点
     *
     * @return true/false
     */
    public boolean isRoot();

    /**
     * 是否叶节点
     *
     * @return true/false
     */
    public boolean isLeaf();

    /**
     * 是否是第一个同级节点
     *
     * @return true/false
     */
    public boolean isFirst();

    /**
     * 前一个节点
     *
     * @return
     */
    public Node preNode();

    /**
     * 是否是同级最后一个节点
     *
     * @return true/false
     */
    public boolean isLast();

    /**
     * 下一个同级节点
     *
     * @return 节点
     */
    public Node nextNode();


    /**
     * 在开始位置插入一个子节点
     *
     * @param node 节点对象
     */
    public void addChildFirst(Node node) throws AddNodeException;

    /**
     * 在末尾位置插入一个子节点
     *
     * @param node 节点对象
     */
    public void addChildLast(Node node) throws AddNodeException;

    /**
     * 在指定位置插入一个子节点
     *
     * @param node  节点对象
     * @param index 插入位置
     */
    public void addChild(Node node, int index) throws AddNodeException;

    /**
     * 删除制定节点
     *
     * @param node 节点对象
     */
    public void removeChild(Node node) throws RemoveNodeException;

    /**
     * 按广度优先进行遍历
     *
     * @param v
     */
    public void traverseBreadthFirst(TreeVisitor v) throws FindNodeException;

    /**
     * 按深度优先进行遍历
     *
     * @param v
     */
    public void traverseDepthFirst(TreeVisitor v) throws FindNodeException;


}
