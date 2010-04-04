package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.XmlTreeNode;

/**
 * XML�����б����ӿڡ�
 * @version 1.0
 *
 */

public interface XmlTreeManager {
    
    /**
     * ���Ҹ��ڵ�
     * @return
     */
    public XmlTreeNode findRootNode();
    
    /**
     * ����ָ���ڵ���ӽڵ�
     * @param nodeId
     * @return
     */
    public List findNodeChildren(Long nodeId,String type);
}
