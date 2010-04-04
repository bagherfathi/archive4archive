package com.ft.common.op;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.common.security.DataResourceTreeBuilder;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.RelGroupRoleDTO;
import com.ft.sm.dto.RelOperRoleDTO;
import com.ft.sm.dto.RelRoleDataResDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.Role;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 构建操作员的权限树
 * @spring.bean id = "opResourceTreeBuilder"
 * 
 * @version 1.0
 * 
 */
public class OpResourceTreeBuilder implements TreeBuilder {

    private ResourceManager resourceManager;
    
    private RoleManager roleManager;
    
    private ResourceTreeBuilder resourceTreeBuilder;

    private DataResourceTreeBuilder dataResourceTreeBuilder;

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.commons.tree.TreeBuilder#buildTreeNode(java.util.Map)
     */
    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "权限列表");
        tree.setRoot(root);
        
        Long type = (Long)initParams.get("roleType");
        
        try{
            if(type != null){
                //功能角色
                if(RoleDTO.ROLE_TYPE_FUNCTION == type.longValue()){
                    buildFunctionRoleTree(initParams,root);
                }else{
                    buildDateRoleTree(initParams,root);
                }
            //默认为功能角色
            }else{
                buildFunctionRoleTree(initParams,root);
            }
        }catch(Exception ex){
            throw new CommonRuntimeException("", ex);
        }

        /*
        List roleList = (List) initParams.get("roleList");
        List roleInGroup = (List) initParams.get("roleInGroup");

        Map nodes = new HashMap();
        
        try {
            if (roleList != null) {
                List allRoleDataRes = this.roleManager.findAllRelRoleDataRes();
                List dateResourceEntries = this.resourceManager.findAllDataResourceEntry();
                for (Iterator iter = roleList.iterator(); iter.hasNext();) {
                    RelOperRoleDTO element = (RelOperRoleDTO) iter.next();
                    Organization org = element.getOrg();
                    String orgId = String.valueOf(org.getOrgId());
                    BaseTreeNode orgNode = getNode(nodes, orgId, org
                            .getOrgName(), root);
                    long roleType = element.getRole().getRoleType();
                 
                    BaseTreeNode roleNodeRoot = getNode(nodes, orgId + "role",
                            "角色列表", orgNode);
                    if (roleType == RoleDTO.ROLE_TYPE_FUNCTION ) {
                        createRoleNode(element.getRole(), roleNodeRoot);
                    } else if (roleType == RoleDTO.ROLE_TYPE_DATA ) {
                        //createDataRoleNode(element.getRole(), roleNodeRoot);
                        createDataRoleNode(element.getRole(), roleNodeRoot,allRoleDataRes,dateResourceEntries);
                    } else if (roleType == RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION){
                        createVirturlRoleNode(element.getRole(),getNode(nodes, orgId + "virtualRole",
                                "权限列表", orgNode));
                    }
                }
            }
            if (roleInGroup != null) {
                for (Iterator iter = roleInGroup.iterator(); iter.hasNext();) {
                    RelGroupRoleDTO element = (RelGroupRoleDTO) iter.next();
                    Organization org = element.getOrg();
                    String orgId = String.valueOf(org.getOrgId());
                    BaseTreeNode orgNode = getNode(nodes, orgId, org
                            .getOrgName(), root);
                    BaseTreeNode roleExtend = getNode(nodes, orgId
                            + "roleExtend", "组角色", orgNode);
                    Group group = element.getGroup();
                    String groupNodeKey = roleExtend.getKey()
                            + group.getGroupId();
                    BaseTreeNode groupNode = getNode(nodes, groupNodeKey, group
                            .getGroupName(), roleExtend);
                    createRoleNode(element.getRole(), groupNode);
                }
            }
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
        */
        return root;
    }
    
    /**
     * 构建功能角色树
     * @param initParams
     * @param root
     * @throws Exception
     */
    private void buildFunctionRoleTree(Map initParams,BaseTreeNode root) throws Exception{
        List roleList = (List) initParams.get("roleList");
        List roleInGroup = (List) initParams.get("roleInGroup");
        
        Map nodes = new HashMap();
        
        if (roleList != null) {
            for (Iterator iter = roleList.iterator(); iter.hasNext();) {
                RelOperRoleDTO element = (RelOperRoleDTO) iter.next();
                Organization org = element.getOrg();
                String orgId = String.valueOf(org.getOrgId());
                BaseTreeNode orgNode = getNode(nodes, orgId, org
                        .getOrgName(), root);
                long roleType = element.getRole().getRoleType();
             
                BaseTreeNode roleNodeRoot = getNode(nodes, orgId + "role",
                        "角色列表", orgNode);
                if (roleType == RoleDTO.ROLE_TYPE_FUNCTION ) {
                    createRoleNode(element.getRole(), roleNodeRoot);
                } else if (roleType == RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION){
                    createVirturlRoleNode(element.getRole(),getNode(nodes, orgId + "virtualRole",
                            "权限列表", orgNode));
                }
            }
        }
        
        if (roleInGroup != null) {
            for (Iterator iter = roleInGroup.iterator(); iter.hasNext();) {
                RelGroupRoleDTO element = (RelGroupRoleDTO) iter.next();
                Organization org = element.getOrg();
                String orgId = String.valueOf(org.getOrgId());
                BaseTreeNode orgNode = getNode(nodes, orgId, org
                        .getOrgName(), root);
                BaseTreeNode roleExtend = getNode(nodes, orgId
                        + "roleExtend", "组角色", orgNode);
                Group group = element.getGroup();
                String groupNodeKey = roleExtend.getKey()
                        + group.getGroupId();
                BaseTreeNode groupNode = getNode(nodes, groupNodeKey, group
                        .getGroupName(), roleExtend);
                createRoleNode(element.getRole(), groupNode);
            }
        }
    }
    
    /**
     * 构建业务角色树
     * @param initParams
     * @param root
     * @throws Exception
     */
    private void buildDateRoleTree(Map initParams,BaseTreeNode root) throws Exception{
        List roleList = (List) initParams.get("roleList");
     
        Map nodes = new HashMap();
        
        if (roleList != null) {
            List allRoleDataRes = this.roleManager.findAllRelRoleDataRes();
            List dateResourceEntries = this.resourceManager.findAllDataResourceEntry();
            for (Iterator iter = roleList.iterator(); iter.hasNext();) {
                RelOperRoleDTO element = (RelOperRoleDTO) iter.next();
                Organization org = element.getOrg();
                String orgId = String.valueOf(org.getOrgId());
                BaseTreeNode orgNode = getNode(nodes, orgId, org
                        .getOrgName(), root);
                //long roleType = element.getRole().getRoleType();
             
                BaseTreeNode roleNodeRoot = getNode(nodes, orgId + "role",
                        "角色列表", orgNode);
                
                createDataRoleNode(element.getRole(), roleNodeRoot,allRoleDataRes,dateResourceEntries);
            }
        }
    }

    /**
     * 新建功能角色节点并生成角色的资源树。
     * 
     * @param role
     * @param roleNodeRoot
     */
    private void createRoleNode(Role role, BaseTreeNode roleNodeRoot)
            throws Exception {
        BaseTreeNode roleNode = new BaseTreeNode(String.valueOf(role
                .getRoleId()), role.getRoleName());
        roleNode.setStatus(100);
        roleNodeRoot.addChildLast(roleNode);
        /*
        List resourceList = resourceManager.findResourceOfRole(new Long(role
                .getRoleId()));
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(resourceList);
        TreeNode node = this.resourceTreeBuilder.buildTreeNode(checker);
        List children = node.getChildren();
        if (children != null) {
            for (Iterator iter = children.iterator(); iter.hasNext();) {
                TreeNode element = (TreeNode) iter.next();
                roleNode.addChildLast(element);
            }
        }
        */
    }

    /**
     * 新建业务角色节点并生成角色的资源树。
     * 
     * @param role
     * @param roleNodeRoot
     * @deprecated
     */
    @SuppressWarnings("unused")
	private void createDataRoleNode(Role role, BaseTreeNode roleNodeRoot)
            throws Exception {
        BaseTreeNode roleNode = new BaseTreeNode(String.valueOf(role
                .getRoleId()), role.getRoleName());
        roleNodeRoot.addChildLast(roleNode);
        List dataResourceEntry = this.resourceManager
                .findDataResourcesOfRole(new Long(role.getRoleId()));
        Set<Object> temp = new HashSet<Object>();
        List<DataResourceDTO> dataResource = new ArrayList<DataResourceDTO>();
        for (Iterator iter = dataResourceEntry.iterator(); iter.hasNext();) {
            DataResourceEntryDTO element = (DataResourceEntryDTO) iter.next();
            DataResourceDTO drDTO = new DataResourceDTO(element
                    .getDataResource());
            if (!temp.contains(drDTO.getResourceId())) {
                temp.add(drDTO.getResourceId());
                dataResource.add(drDTO);
            }
        }
        TreeNode node = dataResourceTreeBuilder.buildTreeNode(dataResource,
                dataResourceEntry);
        List children = node.getChildren();
        if (children != null) {
            for (Iterator iter = children.iterator(); iter.hasNext();) {
                TreeNode element = (TreeNode) iter.next();
                roleNode.addChildLast(element);
            }
        }
    }
    
    /**
     * 新建业务角色节点并生成角色的资源树。
     * 
     * @param role
     * @param roleNodeRoot
     */
    private void createDataRoleNode(Role role, BaseTreeNode roleNodeRoot,List allRoleDataRes,List dateResourceEntries)
            throws Exception {
        BaseTreeNode roleNode = new BaseTreeNode(String.valueOf(role
                .getRoleId()), role.getRoleName());
        roleNodeRoot.addChildLast(roleNode);
        
        List<DataResourceEntryDTO> dataResourceEntry = new ArrayList<DataResourceEntryDTO>();
        for (Iterator iterator = allRoleDataRes.iterator(); iterator
                .hasNext();) {
            RelRoleDataResDTO res = (RelRoleDataResDTO) iterator.next();
            if(res.getRelRoleDataRes().getRoleId() == role.getRoleId()){
                for (Iterator iterator2 = dateResourceEntries.iterator(); iterator2
                        .hasNext();) {
                    DataResourceEntryDTO entry = (DataResourceEntryDTO) iterator2.next();
                    if(entry.getDataResourceEntry().getEntryId() == res.getRelRoleDataRes().getEntryId()){
                        dataResourceEntry.add(entry);
                    }
                }
            }
            
        }
        Set<Object> temp = new HashSet<Object>();
        List<DataResourceDTO> dataResource = new ArrayList<DataResourceDTO>();
        for (Iterator iter = dataResourceEntry.iterator(); iter.hasNext();) {
            DataResourceEntryDTO element = (DataResourceEntryDTO) iter.next();
            DataResourceDTO drDTO = new DataResourceDTO(element
                    .getDataResource());
            if (!temp.contains(drDTO.getResourceId())) {
                temp.add(drDTO.getResourceId());
                dataResource.add(drDTO);
            }
        }
        TreeNode node = dataResourceTreeBuilder.buildTreeNode(dataResource,
                dataResourceEntry);
        List children = node.getChildren();
        if (children != null) {
            for (Iterator iter = children.iterator(); iter.hasNext();) {
                TreeNode element = (TreeNode) iter.next();
                roleNode.addChildLast(element);
            }
        }
    }
    
    /**
     * 生成虚拟角色的资源
     * @param role
     * @param roleNodeRoot
     * @throws Exception
     */
    private void createVirturlRoleNode(Role role, BaseTreeNode roleNodeRoot)
    throws Exception {
        List resourceList = resourceManager.findResourceOfRole(new Long(role
                .getRoleId()));
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(resourceList);
        TreeNode node = this.resourceTreeBuilder.buildTreeNode(checker);
        List children = node.getChildren();
        if (children != null) {
            for (Iterator iter = children.iterator(); iter.hasNext();) {
                TreeNode element = (TreeNode) iter.next();
                roleNodeRoot.addChildLast(element);
            }
        }
    }

    /**
     * 查找节点，如果不存在，则新建节点。
     * 
     * @param nodes
     * @param key
     * @param nodeName
     * @param parent
     * @return
     */
    @SuppressWarnings("unchecked")
	private BaseTreeNode getNode(Map nodes, String key, String nodeName,
            BaseTreeNode parent) {
        BaseTreeNode node = (BaseTreeNode) nodes.get(key);
        if (node == null) {
            node = new BaseTreeNode(key, nodeName);
            parent.addChildLast(node);
            nodes.put(key, node);
        }
        return node;
    }

    /**
     * @spring.property ref = "resourceManager"
     * @param resourceManager
     *                the resourceManager to set
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref = "resourceTreeBuilder"
     * @param resourceTreeBuilder
     *                the resourceTreeBuilder to set
     */
    public void setResourceTreeBuilder(ResourceTreeBuilder resourceTreeBuilder) {
        this.resourceTreeBuilder = resourceTreeBuilder;
    }

    /**
     * @spring.property ref="dataResourceTreeBuilder"
     * @param dataResourceTreeBuilder
     *                the dataResourceTreeBuilder to set
     */
    public void setDataResourceTreeBuilder(
            DataResourceTreeBuilder dataResourceTreeBuilder) {
        this.dataResourceTreeBuilder = dataResourceTreeBuilder;
    }

    /**
     * @spring.property ref="roleManager"
     * @param roleManager the roleManager to set
     */
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }
}