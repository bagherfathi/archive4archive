package com.ft.common.enumdata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.EnumManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.sm.entity.EnumEntry;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 构建枚举数据树(带数据)
 * 
 * @spring.bean id = "enumEntryTreeBuilder"
 * 
 * @version 1.0
 */
public class EnumEntryTreeBuilder implements TreeBuilder {
    private EnumManager enumManager;

    /**
     * @spring.property ref="enumManager"
     * 
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }

    /**
     * 创建EnumGroup的节点
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
        List enumDatas = this.enumManager.findEnumsWithEntriesOfGroup(enumGroup
                .getGroupId());

        for (Iterator iter = enumCategories.iterator(); iter.hasNext();) {
            EnumCategoryDTO enumCategory = (EnumCategoryDTO) iter.next();
            buildEnumCategoryNode(groupNode, enumCategory, enumDatas);
        }
    }

    /**
     * 创建EnumCategory节点
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
     * 创建EnumData节点
     * 
     * @param categoryNode
     * @param enumData
     */
    private void buildEnumDataNode(BaseTreeNode categoryNode, EnumDTO enumData) {
        BaseTreeNode enumNode = new BaseTreeNode(String.valueOf(enumData
                .getEnumId()), enumData.getEnumName());
        enumNode.setStatus(new Long(enumData.getStatus()).intValue());
        categoryNode.addChildLast(enumNode);

        List enumEntries = enumData.getEntries();
        for (Iterator iter = enumEntries.iterator(); iter.hasNext();) {
            EnumEntry element = (EnumEntry) iter.next();
            buildEnumDataEntryNode(enumNode, element);
        }
    }

    /**
     * 创建EnumDataEntry节点
     * 
     * @param enumNode
     * @param enumEntry
     */
    private void buildEnumDataEntryNode(BaseTreeNode enumNode,
            EnumEntry enumEntry) {
        BaseTreeNode entryNode = new BaseTreeNode(
                enumEntry.getEnumEntryValue(), enumEntry.getEnumEntryLabel());
        enumNode.addChildLast(entryNode);
    }

    /**
     * 创建系统数据树
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("-1", "系统数据");
        tree.setRoot(root);

        try {
            List enumGroups = enumManager.findAllEnumGroups();
            for (Iterator iter = enumGroups.iterator(); iter.hasNext();) {
                EnumGroupDTO enumGroup = (EnumGroupDTO) iter.next();
                buildEnumGroupNode(root, enumGroup);
            }

            return root;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }
}
