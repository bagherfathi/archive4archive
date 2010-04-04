package com.ft.common.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RegionManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * ���ڹ�����֯�ɷ���������.
 * 
 * @spring.bean id = "orgRegionTreeBuilder"
 */
public class OrgRegionTreeBuilder implements TreeBuilder{
    private RegionManager regionManager;
    private OrgManager orgManager;
    

    /* (non-Javadoc)
     * @see com.ft.commons.tree.TreeBuilder#buildTreeNode(java.util.Map)
     */
    @SuppressWarnings("unchecked")
	public TreeNode buildTreeNode(Map initParams) {
        Long orgId = (Long)initParams.get("orgId");
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root","�ɷ�������");
        tree.setRoot(root);
        try{
            List orgIds = this.orgManager.findAccessOrgIdsOfOrg(orgId, OrganizationDTO.ORG_TYPE_REGION,true);
            List accessRegions = this.regionManager.findRegionsOfOrgs((Long[])orgIds.toArray(new Long[0]));
            //���ո��ӹ�ϵ����
            java.util.Collections.sort(accessRegions);
            
            Map<Long,BaseTreeNode> nodeMap = new HashMap<Long,BaseTreeNode>();
            for (Iterator iterator = accessRegions.iterator(); iterator.hasNext();) {
                RegionDTO object = (RegionDTO) iterator.next();
                
                //����Ǹ�����ֱ�Ӽӵ����ڵ���
                if(object.getRegionId().equals(object.getParentId())){
                    BaseTreeNode regionNode = new BaseTreeNode(""+object.getRegionId(),object.getRegionName());
                    regionNode.setStatus(new Long(object.getStatus()).intValue());
                    root.addChildLast(regionNode);
                    nodeMap.put(object.getRegionId(),regionNode);
                }else{
                    //���丸�ڵ㲻���ڣ���ѯ������·������������
                    BaseTreeNode parentNode = (BaseTreeNode)nodeMap.get(object.getParentId());
                    
                    if(parentNode == null){
                        List location = regionManager.findRegionLocation(object.getRegionId());
                        for (int i = 0; i < location.size(); i++) {
                            RegionDTO temp = (RegionDTO)location.get(i);
                            
                            //�ڵ��Ѵ���
                            if(nodeMap.get(temp.getRegionId()) != null) continue;
                            
                            BaseTreeNode regionNode = new BaseTreeNode(""+temp.getRegionId(),temp.getRegionName());
                            
                            //����ѡ�ڵ�
                            if(!temp.getRegionId().equals(object.getRegionId())){
                                regionNode.setStatus(new Long(RegionDTO.STATUS_DISABLE).intValue());
                            }
                            
                            parentNode = (BaseTreeNode)nodeMap.get(temp.getParentId());
                            if(parentNode == null) parentNode = root;
                            
                            parentNode.addChildLast(regionNode);
                            
                            nodeMap.put(temp.getRegionId(), regionNode);
                        }
                    }else{
                        BaseTreeNode regionNode = new BaseTreeNode(""+object.getRegionId(),object.getRegionName());
                        regionNode.setStatus(new Long(object.getStatus()).intValue());
                        
                        if(parentNode == null) parentNode = root;
                        
                        parentNode.addChildLast(regionNode);
                        
                        nodeMap.put(object.getRegionId(),regionNode);
                    }
                }
            }
            
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
        
        return root;
    }

    /**
     * @spring.property ref="regionManager"
     * @param regionManager the regionManager to set
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager the orgManager to set
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }
}
