package com.ft.common.region;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.RegionManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.RegionDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 区域树构造类
 * @spring.bean id="regionTreeBuilder"
 * 
 * @version 1.0
 * 
 */
public class RegionTreeBuilder implements TreeBuilder {
    private long showLevel;
    
    /**
     * 区域信息管理类
     */
    private RegionManager regionManager;

    /**
     * @spring.property ref="regionManager"
     * 
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * 构建区域树节点,一个递归过程
     * 
     * @param rootNode
     * @param childList
     * @param allNode
     */
    public void buildRegionNode(BaseTreeNode rootNode, List childList,
            List allNode) {
        if (!childList.isEmpty()) {
            for (Iterator iter = childList.iterator(); iter.hasNext();) {
                RegionDTO region = (RegionDTO) iter.next();
                BaseTreeNode node = new BaseTreeNode(region.getRegionId()
                        .toString(), region.getRegionName());
                node.setStatus(new Long(region.getStatus()).intValue());
                node.bindObject(region);
                rootNode.addChildLast(node);
                
                if(this.showLevel > 0 ){
                    if(region.getRegionType() < showLevel){
                        List childs = findChildForAll(region, allNode);
                        buildRegionNode(node, childs, allNode);
                    }
                }else{
                    List childs = findChildForAll(region, allNode);
                    buildRegionNode(node, childs, allNode);
                }
            }
        }

    }

    /**
     * 构建区域数据树
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        
        Long level = (Long)initParams.get("showLevel");
        
        if(level != null){
            showLevel = level.longValue();
        }else{
            showLevel = 0;
        }

        try {
            // 先将根取出来
            RegionDTO region = this.regionManager.getRootRegion();
            BaseTreeNode root = new BaseTreeNode(region.getRegionId()
                    .toString(), region.getRegionName());
            root.setStatus(new Long(region.getStatus()).intValue());
            root.bindObject(region);
            tree.setRoot(root);
            List allRegion = this.regionManager.findAllRegionOrderByParent();
            List childs = findChildForAll(region, allRegion);
            buildRegionNode(root, childs, allRegion);

            return root;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * 根据region和所有的Region对象的列表，从中找到region的子项
     * 
     * @param region
     * @param allRegion
     * @return
     */
    public List findChildForAll(RegionDTO region, List allRegion) {
        List<RegionDTO> children = new ArrayList<RegionDTO>();
        for (Iterator iter = allRegion.iterator(); iter.hasNext();) {
            RegionDTO child = (RegionDTO) iter.next();

            if (child.getParentId().equals(region.getRegionId())
                    && !region.getRegionId().equals(child.getRegionId())) {
                children.add(child);
            }
        }

        return children;
    }
}
