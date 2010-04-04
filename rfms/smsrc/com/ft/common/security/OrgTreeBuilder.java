package com.ft.common.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ft.busi.sm.model.OrgManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 组织树创建类
 * 
 * @spring.bean id = "orgTreeBuilder"
 * 
 */
public class OrgTreeBuilder implements TreeBuilder {

    private OrgManager orgManager;

    private OrgRepository orgRepository;

    /**
     * 递归生成树节点
     * 
     * @param rootNode
     *                根节点
     * @param allChild
     *                子组织
     * @param allNode
     *                所有组织
     */
    private void buildOrgNode(BaseTreeNode rootNode, List allChild, List allNode) {
        if (!allChild.isEmpty()) {
            for (Iterator iter = allChild.iterator(); iter.hasNext();) {
                OrganizationDTO org = (OrganizationDTO) iter.next();
                BaseTreeNode node = new BaseTreeNode(org.getOrgId().toString(),
                        org.getName());
                node.setStatus(new Long(org.getStatus()).intValue());
                node.bindObject(org);
                rootNode.addChildLast(node);
                List childs = findChild(org, allNode);
                buildOrgNode(node, childs, allNode);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.commons.tree.TreeBuilder#buildTreeNode(java.util.Map)
     */
    public TreeNode buildTreeNode(Map initParams) {
        Long orgId = (Long) initParams.get("rootId");
        OrganizationDTO rootOrg;

        try {
            if (orgId == null || orgId.longValue() == 0) {
                rootOrg = orgManager.findRootOrg();
            } else {
                rootOrg = orgManager.findOrgById(orgId);
            }
            BaseTree tree = new BaseTree();
            // 先将根取出来
            BaseTreeNode root = new BaseTreeNode(rootOrg.getOrgId().toString(),
                    rootOrg.getName());
            root.setStatus(new Long(rootOrg.getStatus()).intValue());
            root.bindObject(rootOrg);
            tree.setRoot(root);
            List allOrg = orgManager.findAllOrgOrderByOrgName();
            buildOrgNode(root, findChild(rootOrg, allOrg), allOrg);
            return root;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * 查找子节点
     * 
     * @param org
     *                组织
     * @param allOrg
     *                所有组织
     * @return
     */
    private List findChild(OrganizationDTO org, List allOrg) {
        List<OrganizationDTO> childs = new ArrayList<OrganizationDTO>();
        long id = org.getOrgId().longValue();
        for (Iterator iter = allOrg.iterator(); iter.hasNext();) {
            OrganizationDTO child = (OrganizationDTO) iter.next();
            long cpid = child.getParentOrgId().longValue();
            long cid = child.getOrgId().longValue();
            if (id == cpid && id != cid) {
                childs.add(child);
            }
        }
        return childs;
    }

    /**
     * 生成可访问组织的节点树
     * 
     * @param includeChildren
     *                是否包括子节点
     * @param accessOrgs
     *                可访问组织实体集合
     * @return
     */
    public TreeNode buildAccessOrgTreeNode(boolean includeChildren,boolean onlyCompany,
            List accessOrgList) {
        List accessOrgs;
        //added by libf,2006/12/18
        if(onlyCompany){
            accessOrgs = getCompaniesOfOrgs(accessOrgList);
        }else{
            accessOrgs = accessOrgList;
        }
        //end added
        
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "可访问组织");
        tree.setRoot(root);
        Map<String,BaseTreeNode>  nodes = new HashMap<String,BaseTreeNode> ();
        for (Iterator iter = accessOrgs.iterator(); iter.hasNext();) {
            OrganizationDTO element = (OrganizationDTO) iter.next();
            String orgPath = element.getPath();
            String[] ids = orgPath.split(OrganizationDTO.PATH_SEPARATOR);
            for (int i = 0; i < ids.length; i++) {
                String orgId = ids[i];
                
                if(orgId == null || orgId.length() <=0) 
                    continue;
                
                if (!nodes.containsKey(ids[i])) {
                    OrganizationDTO orgDTO = orgRepository.getOrgDTOById(Long
                            .valueOf(ids[i]));
                    BaseTreeNode node = new BaseTreeNode(orgDTO.getOrgId()
                            .toString(), orgDTO.getName());
                    node.bindObject(orgDTO);
                    node.setStatus(Integer.parseInt(String.valueOf(orgDTO
                            .getStatus())));
                    if (!ids[i].equals(element.getOrgId().toString())) {
                        node.setStatus(2); // 不是可访问组织
                    }
                    nodes.put(ids[i], node);
                    if (orgDTO.getOrgId().equals(orgDTO.getParentOrgId())) {
                        root.addChildLast(node);
                    } else if (nodes.containsKey(orgDTO.getParentOrgId()
                            .toString())) {
                        BaseTreeNode parentNode = (BaseTreeNode) nodes
                                .get(orgDTO.getParentOrgId().toString());
                        parentNode.addChildLast(node);
                    }
                }
            }
        }
        
        if (includeChildren) {
            try{
                List allOrgs = this.orgManager.findAllOrgOrderByOrgName();
                for (Iterator iter = accessOrgs.iterator(); iter.hasNext();) {
                    OrganizationDTO element = (OrganizationDTO) iter.next();
                    BaseTreeNode node = (BaseTreeNode) nodes.get(element.getOrgId()
                            .toString());
                    buildOrgNode(node, findChild(element, allOrgs), allOrgs, nodes);
                }
            }catch(Exception ex){
                throw new CommonRuntimeException(ex);
            }
        }
        return root;
    }
    
    /**
     * 生成适应组织
     * @param orgs
     * @return
     */
    public TreeNode buildOrgNode(List orgs,String rootTitle) {
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", rootTitle);
        tree.setRoot(root);
        Map<String,BaseTreeNode>  nodes = new HashMap<String,BaseTreeNode> ();
        for (Iterator iter = orgs.iterator(); iter.hasNext();) {
            OrganizationDTO element = (OrganizationDTO) iter.next();
            String orgPath = element.getPath();
            String[] ids = orgPath.split(OrganizationDTO.PATH_SEPARATOR);
            for (int i = 0; i < ids.length; i++) {
                if (StringUtils.isNotEmpty(ids[i]) && !nodes.containsKey(ids[i])) {
                    OrganizationDTO orgDTO = orgRepository.getOrgDTOById(Long
                            .valueOf(ids[i]));
                    BaseTreeNode node = new BaseTreeNode(orgDTO.getOrgId()
                            .toString(), orgDTO.getName());
                    node.setStatus(Integer.parseInt(String.valueOf(orgDTO
                            .getStatus())));
                    if (!ids[i].equals(element.getOrgId().toString())) {
                        node.setStatus(2); // 不是可访问组织
                    }
                    nodes.put(ids[i], node);
                    
                    if (orgDTO.getOrgId().equals(orgDTO.getParentOrgId())) {
                        root.addChildLast(node);
                    } else if (nodes.containsKey(orgDTO.getParentOrgId()
                            .toString())) {
                        BaseTreeNode parentNode = (BaseTreeNode) nodes
                                .get(orgDTO.getParentOrgId().toString());
                        parentNode.addChildLast(node);
                    }
                }
            }
        }
        return root;
    }
    
    

    /**
     * @param accessOrgs
     * @return
     */
    private List getCompaniesOfOrgs(List accessOrgs) {
        List<OrganizationDTO> result = new ArrayList<OrganizationDTO>();
        
        for (Iterator iterator = accessOrgs.iterator(); iterator.hasNext();) {
            OrganizationDTO orgDto = (OrganizationDTO) iterator.next();
            if(orgDto.getType() == OrganizationDTO.ORG_TYPE_COMPANY)
                result.add(orgDto);
            else{
                String[] ids = orgDto.getPath().split(OrganizationDTO.PATH_SEPARATOR);
                
                for (int i = ids.length-1; i >=0; i--) {
                    OrganizationDTO org = this.orgRepository.getOrgDTOById(new Long(ids[i]));
                    if(org != null && org.getType() == OrganizationDTO.ORG_TYPE_COMPANY){
                        result.add(org);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 递归生成树节点
     * 
     * @param rootNode
     *                根结点
     * @param allChild
     *                子组织
     * @param nodes
     *                已添加的节点
     */
    private void buildOrgNode(BaseTreeNode rootNode, List allChild,
            List allOrgs, Map<String,BaseTreeNode> nodes) {
        if (!allChild.isEmpty()) {
            for (Iterator iter = allChild.iterator(); iter.hasNext();) {
                OrganizationDTO org = (OrganizationDTO) iter.next();
                BaseTreeNode node = (BaseTreeNode) nodes.get(org.getOrgId()
                        .toString());
                if (node == null) {
                    node = new BaseTreeNode(org.getOrgId().toString(), org
                            .getName());
                    node.bindObject(org);
                    rootNode.addChildLast(node);
                    nodes.put(org.getOrgId().toString(), node);
                }
                node.setStatus(Integer
                        .parseInt(String.valueOf(org.getStatus())));
                if (!rootNode.getKey().equals(org.getOrgId().toString())) {
                    buildOrgNode(node, findChild(org, allOrgs), allOrgs, nodes);
                }
            }
        }
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref="orgRepository"
     * @param orgRepository
     *                the orgRepository to set
     */
    public void setOrgRepository(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }
    
    
    public static void main(String[] args){
        String var = "#1000#1010#1079#1298#";
        String[] vars = var.split("#");
        
        for (int i = 0; i < vars.length; i++) {
            System.out.println (i + " = " + vars[i]);
        }
    }

}
