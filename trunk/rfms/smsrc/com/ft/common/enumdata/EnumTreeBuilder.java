package com.ft.common.enumdata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.EnumManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * ����ö��������
 * 
 * @spring.bean id = "enumTreeBuilder"
 * 
 */
public class EnumTreeBuilder implements TreeBuilder {
    private EnumManager enumManager;

    /**
     * @spring.property ref="enumManager"
     * 
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }

    /**
     * ����EnumGroup�ڵ�
     * 
     * @param rootNode
     * @param enumGroup
     */
    private void buildEnumGroupNode(BaseTreeNode rootNode,
            EnumGroupDTO enumGroup) throws Exception {
        BaseTreeNode groupNode = new BaseTreeNode(String.valueOf(enumGroup
                .getGroupId()), enumGroup.getGroupName());
        rootNode.addChildLast(groupNode);

        List enumCategories = this.enumManager
                .findEnumCategoriesOfGroup(enumGroup.getGroupId());
        List enumDatas = this.enumManager.findEnumsOfGroup(enumGroup
                .getGroupId());
        for (Iterator iter = enumCategories.iterator(); iter.hasNext();) {
            EnumCategoryDTO enumCategory = (EnumCategoryDTO) iter.next();
            buildEnumCategoryNode(groupNode, enumCategory, enumDatas);
        }
    }

    /**
     * ����EnumCategory�ڵ�
     * 
     * @param groupNode
     * @param enumCategory
     */
    private void buildEnumCategoryNode(BaseTreeNode groupNode,
            EnumCategoryDTO enumCategory, List enumDatas) {
        BaseTreeNode categoryNode = new BaseTreeNode(String
                .valueOf(enumCategory.getCategoryId()), enumCategory
                .getCategoryName());

        groupNode.addChildLast(categoryNode);

        for (Iterator iter = enumDatas.iterator(); iter.hasNext();) {
            EnumDTO enumData = (EnumDTO) iter.next();
            if (enumData.getCategoryId().equals(enumCategory.getCategoryId())) {
                buildEnumDataNode(categoryNode, enumData);
            }
        }
    }

    /**
     * ����EnumData�ڵ�
     * 
     * @param categoryNode
     * @param enumData
     */
    private void buildEnumDataNode(BaseTreeNode categoryNode, EnumDTO enumData) {
        BaseTreeNode enumNode = new BaseTreeNode(String.valueOf(enumData
                .getEnumId()), enumData.getEnumName());
        categoryNode.addChildLast(enumNode);
    }

    /**
     * ����ϵͳ������
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("-1", "ϵͳ����");
        tree.setRoot(root);

        try {
            List enumGroups = enumManager.findAllEnumGroups();
            for (Iterator iter = enumGroups.iterator(); iter.hasNext();) {
                EnumGroupDTO enumGroup = (EnumGroupDTO) iter.next();
                buildEnumGroupNode(root, enumGroup);
            }
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }

        return root;
    }
}
