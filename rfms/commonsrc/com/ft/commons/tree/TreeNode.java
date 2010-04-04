package com.ft.commons.tree;

import java.util.List;

import com.ft.commons.tree.exception.FindNodeException;
import com.ft.commons.tree.exception.RemoveNodeException;

/**
 * �����ӿ�
 */
public interface TreeNode extends Node {
    /**
     * �õ�������������
     *
     * @return tree
     */
    public Tree getTree();

    /**
     * �趨�ýڵ������
     */
    public void setNodeName(String name);

    /**
     * �õ��ڵ�����
     *
     * @return �ڵ�����
     */
    public String getNodeName();

    /**
     * �õ��ڵ�id
     *
     * @return
     */
    public String getKey();

    /**
     * �õ����нڵ��б�
     *
     * @return ���нڵ�
     */
    public List getNodeList() throws FindNodeException;

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
     * ɾ���ڵ�
     *
     * @param nodeName �ڵ�����
     */
    public void removeChild(String nodeName) throws RemoveNodeException;

    /**
     * ɾ���ڵ�
     *
     * @param nodeId �ڵ�ؼ���
     */
    public void removeChild(Long nodeId) throws RemoveNodeException;

    /**
     * ��һ���ڵ㵽�ö�����
     *
     * @param object ����
     */
    public void bindObject(Object object);

    /**
     * ״̬
     */
    public void setStatus(int status);

    public int getStatus();
    
    public void setNodeStatus(int status);
    
    public int getNodeStatus();

    /**
     * �õ��󶨵Ķ���
     *
     * @return
     */
    public Object getObject();
}
