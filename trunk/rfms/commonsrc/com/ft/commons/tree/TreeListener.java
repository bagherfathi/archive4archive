package com.ft.commons.tree;

import com.ft.commons.tree.exception.TreeListenerException;

/**
 * ���¼�������
 */
public interface TreeListener {
    /**
     * ������ɾ��һ���ڵ�ʱ����
     *
     * @param node Ҫɾ���Ľڵ�
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void remove(TreeNode node) throws TreeListenerException;

    /**
     * ��һ���ڵ�����ӽڵ�ʱ����
     *
     * @param parent ���ڵ�
     * @param node   Ҫ��ӵĽڵ�
     * @param index  ����Ľڵ����ӽڵ��е�˳��
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void addChild(TreeNode parent, TreeNode node, int index) throws TreeListenerException;

    /**
     * ����һ���ڵ�ʱ����
     *
     * @param node �ڵ�
     * @throws com.onewaveinc.framework.tree.TreeListenerException
     *
     */
    public void update(TreeNode node) throws TreeListenerException;
}
