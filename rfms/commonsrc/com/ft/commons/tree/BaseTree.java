package com.ft.commons.tree;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.commons.tree.exception.AddNodeException;
import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;
import com.ft.commons.tree.exception.TreeListenerException;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存中的树
 */
public class BaseTree implements Tree {
    protected static Log logger = LogFactory.getLog(BaseTree.class);
    private BaseTreeNode root;
    private List<TreeNode> removedNodes;
    private List<TreeListener> listeners = new ArrayList<TreeListener>();//listener列表

    public BaseTree() {
        root = (BaseTreeNode) createTreeNode();
    }

    public String getName() {
        try {
            return getRoot().getNodeName();
        } catch (FindNodeException e) {
            logger.error(this, e);
        }

        return null;
    }

    public void setName(String name) {
        try {
            getRoot().setNodeName(name);
        } catch (FindNodeException e) {
            logger.error(this, e);
        }
    }

    public void setRoot(BaseTreeNode node) {
        root = node;
        node.setTree(this);
    }

    public TreeNode getRoot() throws FindNodeException {
        return root;
    }

    public List getNodeList() throws FindNodeException {
        final List<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeVisitor v = new BaseTreeVisitor() {
            public void visit(TreeNode node) {
                nodeList.add(node);
            }
        };
        traverseBreadthFirst(v);
        return nodeList;
    }

    public Node getParent() {
        return null;
    }

    public List getChildren() throws FindNodeException {
        return getRoot().getChildren();
    }

    public Node getChild(int index) throws FindNodeException {
        return getRoot().getChild(index);
    }

    public boolean isRoot() {
        return true;
    }

    public boolean isLeaf() {
        try {
            return getRoot().isLeaf();
        } catch (FindNodeException e) {
            logger.error(this, e);
            return false;
        }
    }

    public boolean isFirst() {
        return true;
    }

    public Node preNode() {
        return null;
    }

    public boolean isLast() {
        return true;
    }

    public Node nextNode() {
        return null;
    }

    public void addChildFirst(Node node) throws AddNodeException {
        try {
            getRoot().addChildFirst(node);
        } catch (FindNodeException e) {
            logger.error(this, e);
            throw new AddNodeException("root error");
        }
    }

    public void addChildLast(Node node) throws AddNodeException {
        try {
            getRoot().addChildLast(node);
        } catch (FindNodeException e) {
            logger.error(this, e);
            throw new AddNodeException("root error");
        }
    }

    public void addChild(Node node, int index) throws AddNodeException {
        try {
            getRoot().addChild(node, index);
        } catch (FindNodeException e) {
            logger.error(this, e);
            throw new AddNodeException("root error");
        }
    }

    public void removeChild(Node node) throws RemoveNodeException {
        try {
            getRoot().removeChild(node);
        } catch (FindNodeException e) {
            logger.error(this, e);
            throw new RemoveNodeException("root error");
        }
    }

    public void traverseBreadthFirst(TreeVisitor v) throws FindNodeException {
        getRoot().traverseBreadthFirst(v);
    }

    public void traverseDepthFirst(TreeVisitor v) throws FindNodeException {
        getRoot().traverseDepthFirst(v);
    }

    public TreeNode[] findNodes(String nodeName) throws FindNodeException {
        List<TreeNode> result = new ArrayList<TreeNode>();
        List nodeList = getNodeList();
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = (TreeNode) nodeList.get(i);
            if (node.getNodeName().equals(nodeName))
                result.add(node);
        }
        return (TreeNode[]) result.toArray(new TreeNode[0]);
    }

    public TreeNode findNodeByKey(String nodeKey) throws FindNodeException {
        return getRoot().findNodeByKey(nodeKey);
    }

    public TreeNode[] getNodes(int level) throws FindNodeException {
        return getRoot().getNodes(level);
    }

    public TreeNode createTreeNode() {
        BaseTreeNode node = new BaseTreeNode();
        node.setTree(this);
        return node;
    }

    public void addListener(TreeListener listener) {
        listeners.add(listener);
    }


    public List getListeners() {
        return listeners;
    }

    public void addRemovedNodes(TreeNode node) {
        if (getRemovedNodes() == null)
            removedNodes = new ArrayList<TreeNode>();
        if (removedNodes.indexOf(node) < 0)
            removedNodes.add(node);
    }

    public List getRemovedNodes() {
        return removedNodes;
    }

    protected void listenerRemove(TreeNode node) throws TreeListenerException {
        for (int i = 0; i < listeners.size(); i++) {
            TreeListener listener = (TreeListener) listeners.get(i);
            listener.remove(node);
        }
    }

    protected void listenerAddChild(TreeNode parent, TreeNode node, int index) throws TreeListenerException {
        for (int i = 0; i < listeners.size(); i++) {
            TreeListener listener = (TreeListener) listeners.get(i);
            listener.addChild(parent, node, index);
        }
    }

    protected void listenerUpdate(TreeNode node) throws TreeListenerException {
        for (int i = 0; i < listeners.size(); i++) {
            TreeListener listener = (TreeListener) listeners.get(i);
            listener.update(node);
        }
    }
}
