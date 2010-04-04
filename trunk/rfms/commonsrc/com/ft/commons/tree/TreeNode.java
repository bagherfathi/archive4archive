package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;

/**
 * 树结点接口
 */
public interface TreeNode extends Node {
    /**
     * 得到该树的总描述
     *
     * @return tree
     */
    public Tree getTree();

    /**
     * 设定该节点的名称
     */
    public void setNodeName(String name);

    /**
     * 得到节点名称
     *
     * @return 节点名称
     */
    public String getNodeName();

    /**
     * 得到节点id
     *
     * @return
     */
    public String getKey();

    /**
     * 得到所有节点列表
     *
     * @return 所有节点
     */
    public List getNodeList() throws FindNodeException;

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
     * 删除节点
     *
     * @param nodeName 节点名称
     */
    public void removeChild(String nodeName) throws RemoveNodeException;

    /**
     * 删除节点
     *
     * @param nodeId 节点关键字
     */
    public void removeChild(Long nodeId) throws RemoveNodeException;

    /**
     * 绑定一个节点到该对象上
     *
     * @param object 对象
     */
    public void bindObject(Object object);

    /**
     * 状态
     */
    public void setStatus(int status);

    public int getStatus();
    
    public void setNodeStatus(int status);
    
    public int getNodeStatus();

    /**
     * 得到绑定的对象
     *
     * @return
     */
    public Object getObject();
}
