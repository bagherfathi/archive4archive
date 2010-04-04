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
 * Ȩ����������
 * 
 * @spring.bean id="resourceTreeBuilder"
 * 
 */
public class ResourceTreeBuilder implements TreeBuilder {

    /**
     * ����Ȩ��ʵ�建��
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
     * ����ϵͳȨ����
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        ResourceTreeNode root = new ResourceTreeNode("-1", "����Ȩ����");
        tree.setRoot(root);

        List resources = resourceRepository.iterator();

        buildResourceNode(root, resources);

        return root;
    }

    /**
     * �����������Ȩ�޹����ڵ�
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
     * ����һ����ѡ�нڵ���ɵ���
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
        ResourceTreeNode root = new ResourceTreeNode("-1", "����Ȩ��");
        tree.setRoot(root);

        buildResourceNode(checker, root, ownerResources);

        return root;
    }

    /**
     * ��������Ȩ�޽ڵ�
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
