package com.ft.commons.tree;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.commons.tree.exception.AddNodeException;
import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;
import com.ft.commons.tree.exception.UpdateException;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存树的节点
 */
public class BaseTreeNode implements TreeNode {
    protected static Log logger = LogFactory.getLog(BaseTreeNode.class);
    private List children = null;
    private TreeNode parent = null;
    private TreeNode nextNode = null;
    private TreeNode preNode = null;
    private BaseTree tree;
    private Object obj;
    private String name;
    private String key = "0";
    private boolean changed = false;
    private int status = 0;
    private int nodeStatus = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }       

    public int getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(int nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	protected BaseTreeNode() {
    }
	
	public BaseTreeNode(String key,String name){
		this.key = key;
		this.name = name;
	}

    public Tree getTree() {
        return tree;
    }

    protected void setTree(BaseTree tree) {
        this.tree = tree;
    }

    public Node getParent() {
        return parent;
    }

    private void setParent(TreeNode node) {
        parent = node;
    }

    public List getChildren() {
        return children;
    }

    public Node getChild(int index) {
        if (getChildren() != null)
            return (Node) children.get(index);
        return null;
    }

    public void setNodeName(String name) {
        if (!name.equals(this.name))
            setChanged(true);
        this.name = name;
    }

    public String getNodeName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getKey() {
        return key;
    }

    public boolean isRoot() {
        return (getParent() == null);
    }

    public boolean isLeaf() {
        return (getChildren() == null) || getChildren().size() == 0;
    }

    public boolean isFirst() {
        return preNode == null;
    }

    public Node preNode() {
        return preNode;
    }

    public boolean isLast() {
        return nextNode == null;
    }

    public Node nextNode() {
        return nextNode;
    }

    private void preAddChild(BaseTreeNode node) throws AddNodeException {//add child前的准备工作
        if (node == null)
            throw new AddNodeException("node is null");
        if (children == null)
            children = new ArrayList();
        node.setParent(this);
        node.setTree(tree);
    }

    @SuppressWarnings("unchecked")
	public void addChildFirst(Node node) throws AddNodeException {
        preAddChild((BaseTreeNode) node);
        List newChildren = new ArrayList();
        if (children.size() > 0) {
            ((BaseTreeNode) node).nextNode = (TreeNode) children.get(0);
            ((BaseTreeNode) children.get(0)).preNode = (TreeNode) node;
        }
        newChildren.add(node);
        newChildren.addAll(children);
        children = newChildren;
        tree.listenerAddChild(this, (TreeNode) node, 0);
    }

    @SuppressWarnings("unchecked")
	public void addChildLast(Node node) throws AddNodeException {
        preAddChild((BaseTreeNode) node);
        if (children.size() > 0) {
            ((BaseTreeNode) children.get(children.size() - 1)).nextNode = (TreeNode) node;
            ((BaseTreeNode) node).preNode = (TreeNode) children.get(children.size() - 1);
        }
        children.add(node);
        tree.listenerAddChild(this, (TreeNode) node, children.size());
    }

    @SuppressWarnings("unchecked")
	public void addChild(Node node, int index) throws AddNodeException {
        if (index < 0)
            throw new AddNodeException("index必须大于等于0");
        preAddChild((BaseTreeNode) node);
        if (index >= children.size()) {
            addChildLast(node);//如果index大就加到最后
            if (index != 0)
                ((BaseTreeNode) node).preNode = (TreeNode) children.get(index - 1);
        } else {
            ((BaseTreeNode) node).nextNode = (TreeNode) children.get(index);
            if (index != 0)
                ((BaseTreeNode) node).preNode = (TreeNode) children.get(index - 1);
            List newChildren = new ArrayList();
            newChildren.addAll(children.subList(0, index));
            newChildren.add(node);
            newChildren.addAll(children.subList(index, children.size()));
            children = newChildren;
            tree.listenerAddChild(this, (TreeNode) node, index);
        }
    }


    public void removeChild(Node node) throws RemoveNodeException {
        children.remove(node);
        final List<Node> removeList = new ArrayList<Node>();//要删除的列表
        TreeVisitor v = new BaseTreeVisitor() {
            public void visit(TreeNode node){
                removeList.add(node);
            }
        };
        node.traverseBreadthFirst(v);//找到该节点所有子节点，通知listener删除
        for (int i = removeList.size() - 1; i >= 0; i--) {//倒序，先删子节点
            TreeNode removeNode = (TreeNode) removeList.get(i);
            tree.listenerRemove(removeNode);
            tree.addRemovedNodes(removeNode);
        }
    }

    public void traverseBreadthFirst(TreeVisitor v) throws FindNodeException {
        ArrayList<NodeStackItem> queue = new ArrayList<NodeStackItem>();
        queue.add(new NodeStackItem(this, 0));
        int curlevel = 0;
        while (queue.size() > 0) {
            NodeStackItem item = (NodeStackItem) queue.remove(0);
            if (item.level > curlevel) {
                int times = item.level - curlevel;
                for (int i = 0; i < times; i++)
                    v.onDescent();
            } else if (curlevel > item.level) {
                int times = curlevel - item.level;
                for (int i = 0; i < times; i++)
                    v.onAscent();
            }
            curlevel = item.level;
            v.visit(item.node);
            List children = item.node.getChildren();
            if (null != children) {
                for (int i = 0; i < children.size(); i++) {
                    queue.add(new NodeStackItem((TreeNode) children.get(i), curlevel + 1));
                }
            }
        }
    }

    public void traverseDepthFirst(TreeVisitor v) throws FindNodeException {
        v.visit(this);
        List children = getChildren();
        if (children != null) {
            v.onDescent();
            for (int i = 0; i < children.size(); i++) {
                TreeNode node = (TreeNode) children.get(i);
                node.traverseDepthFirst(v);
            }
            v.onAscent();
        }
    }

    public void removeChild(String nodeName) throws RemoveNodeException {
        for (int i = 0; i < children.size(); i++) {
            TreeNode node = (TreeNode) children.get(i);
            if (node.getNodeName().equals(nodeName)) {
                removeChild(node);
                return;
            }
        }
        throw new RemoveNodeException("no such node:nodeName=" + nodeName);
    }

    public void removeChild(Long nodeId) throws RemoveNodeException {
        for (int i = 0; i < children.size(); i++) {
            TreeNode node = (TreeNode) children.get(i);
            if (node.getKey().equals(nodeId)) {
                removeChild(node);
                return;
            }
        }
        throw new RemoveNodeException("no such node:nodeId=" + nodeId);

    }

    public List getNodeList() throws FindNodeException {
        final List<Node> nodeList = new ArrayList<Node>();
        TreeVisitor v = new BaseTreeVisitor() {
            public void visit(TreeNode node) {
                nodeList.add(node);
            }
        };
        traverseBreadthFirst(v);
        return nodeList;
    }

    public TreeNode findNodeByKey(String nodeKey) throws FindNodeException {
        List nodeList = getNodeList();
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = (TreeNode) nodeList.get(i);
            if (nodeKey.equals(node.getKey()))
                return node;
        }
        return null;
    }

    public TreeNode[] getNodes(int level) throws FindNodeException {
        final List<Node> result = new ArrayList<Node>();
        final int l = level;
        TreeVisitor visitor = new BaseTreeVisitor() {
            public void visit(TreeNode node) {
                System.out.println("level:" + currentLevel());
                if (currentLevel() == l)
                    result.add(node);
            }
        };
        traverseBreadthFirst(visitor);
        return (TreeNode[]) result.toArray(new TreeNode[0]);
    }

    public void bindObject(Object object) {
        obj = object;
        setChanged(true);
    }

    public Object getObject() {
        return obj;
    }

    public void update() throws UpdateException {
        tree.listenerUpdate(this);
        setChanged(true);
    }

    private class NodeStackItem {
        int level;
        TreeNode node;

        NodeStackItem(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
