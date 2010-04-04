package com.ft.sm.dto;

import java.io.Serializable;


/**
 * 树节点
 *
 */
public class XmlTreeNode implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public static final long MOCK_ROOT_NODE_ID = -1; //虚拟的根节点ID
    
    public static final String ADD_NODE_FLAG = "add"; //添加节点标记
    
    public static final String UPDATE_NODE_FLAG = "update"; //更新节点标记
    
    public static final String DELETE_NODE_FLAG = "delete";  //删除节点标记

    public static final String MOVE_NODE_FLAG = "move";  //移动节点标记
    
    private Long nodeId;   //节点ID
    
    private String nodeName;  //节点名称
    
    private Long nodeStatus = new Long(0);  //节点状态
    
    private String nodeType = "";  //节点类型

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