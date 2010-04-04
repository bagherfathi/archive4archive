package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.FindNodeException;

/**
 * �����������ͽӿ�
 */
public interface Tree extends Node {

    /**
     * �趨��������
     */
    public void setName(String name);

    public String getName();

    /**
     * �õ����ڵ�
     *
     * @return ���ڵ�
     */
    public TreeNode getRoot() throws FindNodeException;

    /**
     * �õ����нڵ��б�
     *
     * @return ���нڵ�
     */
    public List getNodeList() throws FindNodeException;


    /**
     * ���ڵ����Ƶõ��ڵ�
     *
     * @param nodeName �ڵ�����
     * @return ���������Ľڵ�
     */
    public TreeNode[] findNodes(String nodeName) throws FindNodeException;

    /**
     * ���ڵ�id�õ��ڵ�
     *
     * @param nodeKey
     * @return �ڵ�
     */
    public TreeNode findNodeByKey(String nodeKey) throws FindNodeException;

    /**
     * �õ����в��Ϊlevel�����нڵ�
     *
     * @param level ���
     * @return �ڵ�
     */
    public TreeNode[] getNodes(int level) throws FindNodeException;

    /**
     * ����һ���µ�TreeNode
     *
     * @return �½��ڵ�
     */
    public TreeNode createTreeNode();

    /**
     * ���������������Ҫʱ��������
     */
    public void addListener(TreeListener listener);

}
