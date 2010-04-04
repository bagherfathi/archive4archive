package com.ft.common.security;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 业务权限树构造器
 * 
 * @spring.bean id="dataResourceTreeBuilder"
 * 
 * @version 1.0
 */
public class DataResourceTreeBuilder implements TreeBuilder {

    /**
     * 权限处理类
     */
    private ResourceManager resourceManager;

    /**
     * 构建树
     */
    public TreeNode buildTreeNode(Map initParams) {

        BaseTree tree = new BaseTree();
        // 设置根节点
        ResourceTreeNode root = new ResourceTreeNode("-1", "业务权限树");
        tree.setRoot(root);

        try {
            // 1 获得所有的DataResource
            List allDataResource = resourceManager.findAllDataResource();
            // 2 获得所有的DataResourceEntry
            List allDataEntry = resourceManager.findAllDataResourceEntry();
            // 创建树
            buildDataResourceNode(root, allDataResource, allDataEntry);
            return root;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * 根据dataResources和entrys创建一棵树
     * 
     * @param dataResources
     * @param entrys
     * @return
     */
    public TreeNode buildTreeNode(List dataResources, List entrys) {

        BaseTree tree = new BaseTree();
        // 设置根节点
        ResourceTreeNode root = new ResourceTreeNode("-1", "已选业务权限");
        tree.setRoot(root);
        buildDataResourceNode(root, dataResources, entrys);
        return root;
    }

    /**
     * 创建节点和叶子
     * 
     * @param rootNode
     * @param dataResources
     * @param entrys
     */
    private void buildDataResourceNode(ResourceTreeNode rootNode,
            List dataResources, List entrys) {

        if (null != rootNode && null != dataResources && null != entrys) {

            for (Iterator drIter = dataResources.iterator(); drIter.hasNext();) {
                DataResourceDTO dr = (DataResourceDTO) drIter.next();
                ResourceTreeNode node = new ResourceTreeNode(dr.getResourceId()
                        .toString(), dr.getTitle());
                rootNode.addChildLast(node);
                for (Iterator eIter = entrys.iterator(); eIter.hasNext();) {
                    DataResourceEntryDTO entry = (DataResourceEntryDTO) eIter
                            .next();
                    if (dr.getResourceId().equals(entry.getResourceId())) {
                        // 判断是单选还是多选
                        // 单选和多选是指页面显示的radio和checkbox，根据类型分辨业务权限数据的分配方式
                        if (dr.getAssignType() == DataResourceDTO.ASSIGN_YTPE_MULTI) {
                            // 多选型
                            ResourceTreeNode leaf = new ResourceTreeNode(entry
                                    .getEntryId().toString(), entry.getTitle(),
                                    DataResourceDTO.ASSIGN_YTPE_MULTI, dr
                                            .getTitle());
                            node.addChildLast(leaf);
                        } else {
                            // 单选型
                            ResourceTreeNode leaf = new ResourceTreeNode(entry
                                    .getEntryId().toString(), entry.getTitle(),
                                    DataResourceDTO.ASSIGN_TYPE_SINGLE, dr
                                            .getTitle());
                            node.addChildLast(leaf);
                        }
                    }
                }
            }
        }
    }

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

}
