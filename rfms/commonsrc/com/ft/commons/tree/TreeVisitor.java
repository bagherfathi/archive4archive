package com.ft.commons.tree;


/**
 * ���������߽ӿ�
 */
public interface TreeVisitor {

    /**
     * ��ĳһ�ڵ�ʱ
     *
     * @param node
     */
    public void visit(TreeNode node);

    /**
     * �������ʱ
     */
    public void onAscent();

    /**
     * ����½�ʱ
     */
    public void onDescent();

    /**
     * Ŀǰ�������Ĳ��
     *
     * @return ���
     */
    public int currentLevel();
}
