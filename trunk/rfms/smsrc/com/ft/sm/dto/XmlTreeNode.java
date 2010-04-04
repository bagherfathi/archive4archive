package com.ft.sm.dto;

import java.io.Serializable;


/**
 * ���ڵ�
 *
 */
public class XmlTreeNode implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public static final long MOCK_ROOT_NODE_ID = -1; //����ĸ��ڵ�ID
    
    public static final String ADD_NODE_FLAG = "add"; //��ӽڵ���
    
    public static final String UPDATE_NODE_FLAG = "update"; //���½ڵ���
    
    public static final String DELETE_NODE_FLAG = "delete";  //ɾ���ڵ���

    public static final String MOVE_NODE_FLAG = "move";  //�ƶ��ڵ���
    
    private Long nodeId;   //�ڵ�ID
    
    private String nodeName;  //�ڵ�����
    
    private Long nodeStatus = new Long(0);  //�ڵ�״̬
    
    private String nodeType = "";  //�ڵ�����

    /**
     * @return the nodeId
     */
    public Long getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return the nodeStatus
     */
    public Long getNodeStatus() {
        return nodeStatus;
    }

    /**
     * @param nodeStatus the nodeStatus to set
     */
    public void setNodeStatus(Long nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }  
    
    
}