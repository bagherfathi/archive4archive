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
 * ҵ��Ȩ����������
 * 
 * @spring.bean id="dataResourceTreeBuilder"
 * 
 * @version 1.0
 */
public class DataResourceTreeBuilder implements TreeBuilder {

    /**
     * Ȩ�޴�����
     */
    private ResourceManager resourceManager;

    /**
     * ������
     */
    public TreeNode buildTreeNode(Map initParams) {

        BaseTree tree = new BaseTree();
        // ���ø��ڵ�
        ResourceTreeNode root = new ResourceTreeNode("-1", "ҵ��Ȩ����");
        tree.setRoot(root);

        try {
            // 1 ������е�DataResource
            List allDataResource = resourceManager.findAllDataResource();
            // 2 ������е�DataResourceEntry
            List allDataEntry = resourceManager.findAllDataResourceEntry();
            // ������
            buildDataResourceNode(root, allDataResource, allDataEntry);
            return root;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * ����dataResources��entrys����һ����
     * 
     * @param dataResources
     * @param entrys
     * @return
     */
    public TreeNode buildTreeNode(List dataResources, List entrys) {

        BaseTree tree = new BaseTree();
        // ���ø��ڵ�
        ResourceTreeNode root = new ResourceTreeNode("-1", "��ѡҵ��Ȩ��");
        tree.setRoot(root);
        buildDataResourceNode(root, dataResources, entrys);
        return root;
    }

    /**
     * �����ڵ��Ҷ��
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
                        // �ж��ǵ�ѡ���Ƕ�ѡ
                        // ��ѡ�Ͷ�ѡ��ָҳ����ʾ��radio��checkbox���������ͷֱ�ҵ��Ȩ�����ݵķ��䷽ʽ
                        if (dr.getAssignType() == DataResourceDTO.ASSIGN_YTPE_MULTI) {
                            // ��ѡ��
                            ResourceTreeNode leaf = new ResourceTreeNode(entry
                                    .getEntryId().toString(), entry.getTitle(),
                                    DataResourceDTO.ASSIGN_YTPE_MULTI, dr
                                            .getTitle());
                            node.addChildLast(leaf);
                        } else {
                            // ��ѡ��
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
