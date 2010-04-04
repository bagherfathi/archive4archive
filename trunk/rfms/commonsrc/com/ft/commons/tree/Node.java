package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.AddNodeException;
import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;

/**
 * ���νṹ����ز���
 */
public interface Node {
    /**
     * �õ����ڵ�
     *
     * @return ���ڵ�
     */
    public Node getParent();

    /**
     * �õ������ӽڵ�
     *
     * @return �����ӽڵ�
     */
    public List getChildren() throws FindNodeException;

    /**
     * �õ���index���ӽڵ�
     *
     * @return ���ӽڵ�
     */
    public Node getChild(int index) throws FindNodeException;

    /**
     * �Ƿ���ڵ�
     *
     * @return true/false
     */
    public boolean isRoot();

    /**
     * �Ƿ�Ҷ�ڵ�
     *
     * @return true/false
     */
    public boolean isLeaf();

    /**
     * �Ƿ��ǵ�һ��ͬ���ڵ�
     *
     * @return true/false
     */
    public boolean isFirst();

    /**
     * ǰһ���ڵ�
     *
     * @return
     */
    public Node preNode();

    /**
     * �Ƿ���ͬ�����һ���ڵ�
     *
     * @return true/false
     */
    public boolean isLast();

    /**
     * ��һ��ͬ���ڵ�
     *
     * @return �ڵ�
     */
    public Node nextNode();


    /**
     * �ڿ�ʼλ�ò���һ���ӽڵ�
     *
     * @param node �ڵ����
     */
    public void addChildFirst(Node node) throws AddNodeException;

    /**
     * ��ĩβλ�ò���һ���ӽڵ�
     *
     * @param node �ڵ����
     */
    public void addChildLast(Node node) throws AddNodeException;

    /**
     * ��ָ��λ�ò���һ���ӽڵ�
     *
     * @param node  �ڵ����
     * @param index ����λ��
     */
    public void addChild(Node node, int index) throws AddNodeException;

    /**
     * ɾ���ƶ��ڵ�
     *
     * @param node �ڵ����
     */
    public void removeChild(Node node) throws RemoveNodeException;

    /**
     * ��������Ƚ��б���
     *
     * @param v
     */
    public void traverseBreadthFirst(TreeVisitor v) throws FindNodeException;

    /**
     * ��������Ƚ��б���
     *
     * @param v
     */
    public void traverseDepthFirst(TreeVisitor v) throws FindNodeException;


}
