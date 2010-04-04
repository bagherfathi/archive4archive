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
 * ������������
 * @spring.bean id="regionTreeBuilder"
 * 
 * @version 1.0
 * 
 */
public class RegionTreeBuilder implements TreeBuilder {
    private long showLevel;
    
    /**
     * ������Ϣ������
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
     * �����������ڵ�,һ���ݹ����
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
     * ��������������
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
            // �Ƚ���ȡ����
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
     * ����region�����е�Region������б������ҵ�region������
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
