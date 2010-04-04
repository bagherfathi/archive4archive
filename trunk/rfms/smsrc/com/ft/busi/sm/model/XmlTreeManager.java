package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.XmlTreeNode;

/**
 * XML树型列表管理接口。
 * @version 1.0
 *
 */

public interface XmlTreeManager {
    
    /**
     * 查找根节点
     * @return
     */
    public XmlTreeNode findRootNode();
    
    /**
     * 查找指定节点的子节点
     * @param nodeId
     * @return
     */
    public List findNodeChildren(Long nodeId,String type);
}
