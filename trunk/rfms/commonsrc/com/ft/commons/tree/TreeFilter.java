package com.ft.commons.tree;


/**
 * �������ڵ�
 */
public interface TreeFilter {

    public static final int REMOVE = -1;

    /**
     * ���node��statusӦ����ʲô�����Ҫɾ���򷵻�REMOVE����ֵ
     *
     * @param node ���ڵ�
     * @return �����
     */
    public int check(TreeNode node);
}
