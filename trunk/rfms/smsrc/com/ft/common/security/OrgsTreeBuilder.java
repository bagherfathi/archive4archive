package com.ft.common.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 组织树创建类
 * 
 * @spring.bean id = "orgsTreeBuilder"
 */

public class OrgsTreeBuilder implements TreeBuilder {

    private OrgRepository orgRepository;

    public TreeNode buildTreeNode(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    public TreeNode buildOrgNode(List orgs) {

        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "可访问组织");
        tree.setRoot(root);
        
        long[] orgIds = ArrayUtils.EMPTY_LONG_ARRAY;
        for (Iterator iter = orgs.iterator(); iter.hasNext();) {
            OrganizationDTO element = (OrganizationDTO) iter.next();
            orgIds = ArrayUtils.add(orgIds, element.getOrgId().longValue());
        }

        Map<Long,BaseTreeNode> nodeMap = new HashMap<Long,BaseTreeNode>();
        for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
            OrganizationDTO object = (OrganizationDTO) iterator.next();
            
            

            // 如果是根组织，直接加到根节点下
            if (object.getParentOrgId().equals(object.getOrgId())) {
                if (nodeMap.get(object.getOrgId()) == null) {
                    BaseTreeNode orgNode = new BaseTreeNode(""
                            + object.getOrgId(), object.getName());
                    orgNode.setStatus(new Long(object.getStatus()).intValue());
                    orgNode.bindObject(object);
                    root.addChildLast(orgNode);
                    nodeMap.put(object.getOrgId(), orgNode);
                }
            } else {
                // 若其父节点不存在，查询其所在路径上所有组织
                BaseTreeNode parentNode = (BaseTreeNode) nodeMap.get(object
                        .getParentOrgId());

                if (parentNode == null) {
                    // List location =
                    // regionManager.findRegionLocation(object.getRegionId());

                    String[] ids = object.getPath().split(
                            OrganizationDTO.PATH_SEPARATOR);

                    for (int i = 0; i < ids.length; i++) {
                        if (StringUtils.isNotEmpty(ids[i])) {
                            OrganizationDTO org = orgRepository
                                    .getOrgDTOById(Long.valueOf(ids[i]));
                            if (org != null) {
                                // 节点已存在
                                if (nodeMap.get(org.getOrgId()) != null || !ArrayUtils.contains(orgIds, org.getOrgId().longValue()))
                                    continue;

                                BaseTreeNode node = new BaseTreeNode(""
                                        + org.getOrgId(), org.getName());
                                node.bindObject(org);
                                // 不可选节点
//                                if (!org.getOrgId().equals(object.getOrgId())) {
//                                    node.setStatus(new Long(
//                                            OrganizationDTO.STATUS_DISABLE)
//                                            .intValue());
//                                }
                                
//                                if( !ArrayUtils.contains(orgIds, org.getOrgId().longValue())){
//                                    node.setStatus(new Long(
//                                          OrganizationDTO.STATUS_DISABLE)
//                                          .intValue());
//                                }

                                parentNode = (BaseTreeNode) nodeMap.get(org
                                        .getParentOrgId());
                                if (parentNode == null)
                                    parentNode = root;

                                parentNode.addChildLast(node);

                                nodeMap.put(org.getOrgId(), node);
                            }
                        }
                    }
                } else {
                    if (nodeMap.get(object.getOrgId()) == null) {
                        BaseTreeNode node = new BaseTreeNode(""
                                + object.getOrgId(), object.getName());
                        node.setStatus(new Long(object.getStatus()).intValue());
                        node.bindObject(object);
                        if (parentNode == null)
                            parentNode = root;

                        parentNode.addChildLast(node);

                        nodeMap.put(object.getOrgId(), node);
                    }
                }
            }
        }
        return root;
    }
    
    /**
     * @spring.property ref="orgRepository"
     * @param orgRepository
     *            the orgRepository to set
     */
    public void setOrgRepository(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }
}
