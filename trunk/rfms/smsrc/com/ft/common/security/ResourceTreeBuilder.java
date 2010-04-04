package com.ft.common.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.ResourceDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 权限树构造类
 * 
 * @spring.bean id="resourceTreeBuilder"
 * 
 */
public class ResourceTreeBuilder implements TreeBuilder {

    /**
     * 功能权限实体缓存
     */
    private ResourceRepository resourceRepository;

    /**
     * @spring.property ref = "resourceRepository"
     * 
     * @param resourceRepository
     */
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * 构建系统权限树
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        ResourceTreeNode root = new ResourceTreeNode("-1", "功能权限树");
        tree.setRoot(root);

        List resources = resourceRepository.iterator();

        buildResourceNode(root, resources);

        return root;
    }

    /**
     * 根据排序过的权限构建节点
     * 
     * @param root
     * @param orderResourceList
     */
    private void buildResourceNode(ResourceTreeNode root, List orderResourceList) {
        Map<Long,ResourceTreeNode> nodes = new HashMap<Long,ResourceTreeNode>();
        ResourceTreeNode parent = null;
        for (Iterator iter = orderResourceList.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            ResourceTreeNode child = new ResourceTreeNode(element
                    .getResourceId().toString(), element.getTitle(), element
                    .getResourcePath());
            parent = (ResourceTreeNode) nodes.get(element.getParentId());
            if (parent == null) {
                parent = root;
            }

            nodes.put(element.getResourceId(), child);

            parent.addChildLast(child);
        }
    }

    /**
     * 创建一棵由选中节点组成的树
     * 
     * @param checker
     * @return
     */
    public TreeNode buildTreeNode(PermissionChecker checker) {
        List allResources = this.resourceRepository.iterator();

        List<ResourceDTO> ownerResources = new ArrayList<ResourceDTO>();
        for (Iterator iter = allResources.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            if (checker.checkMenuDisplay(element.getResourcePath())) {
                ownerResources.add(element);
            }
        }

        BaseTree tree = new BaseTree();
        ResourceTreeNode root = new ResourceTreeNode("-1", "功能权限");
        tree.setRoot(root);

        buildResourceNode(checker, root, ownerResources);

        return root;
    }

    /**
     * 创建功能权限节点
     * 
     * @param checker
     * @param root
     * @param orderResourceList
     */
    private void buildResourceNode(PermissionChecker checker,
            ResourceTreeNode root, List orderResourceList) {
        Map<Long,ResourceTreeNode> nodes = new HashMap<Long,ResourceTreeNode>();
        ResourceTreeNode parent = null;
        for (Iterator iter = orderResourceList.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            ResourceTreeNode child = new ResourceTreeNode(element
                    .getResourceId().toString(), element.getTitle(), element
                    .getResourcePath());

            if (!checker.checkPermission(element.getResourcePath())) {
                child.setChecked(false);
            }

            parent = (ResourceTreeNode) nodes.get(element.getParentId());
            if (parent == null) {
                parent = root;
            }

            nodes.put(element.getResourceId(), child);

            parent.addChildLast(child);
        }
    }
}
