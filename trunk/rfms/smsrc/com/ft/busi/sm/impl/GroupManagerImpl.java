package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.GroupDao;
import com.ft.busi.sm.impl.dao.OrganizationDao;
import com.ft.busi.sm.impl.dao.PermitLogDao;
import com.ft.busi.sm.impl.dao.RelGroupOrgDao;
import com.ft.busi.sm.impl.dao.RelGroupRoleDao;
import com.ft.busi.sm.impl.dao.RoleDao;
import com.ft.busi.sm.model.GroupManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.PermitLogDTO;
import com.ft.sm.dto.RelGroupOrgDTO;
import com.ft.sm.dto.RelGroupRoleDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupOrg;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.Role;

/**
 * 组管理实现类.
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="groupManager" parent="transactionProxyFactoryBean"
 *                  target="groupManagerImpl"
 * 
 * @spring.bean id="groupManagerImpl"
 */
public class GroupManagerImpl implements GroupManager, BusiBaseService {
    private GroupDao groupDao;

    private OrganizationDao orgDao;

    private RoleDao roleDao;

    private RelGroupOrgDao relGroupOrgDao;

    private RelGroupRoleDao relGroupRoleDao;

    private PermitLogDao permitLogDao;

    private BusiAppService appService;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.GroupManager#saveGroupAccessOrg(com.huashu.boss.sm.entity.privilege.Organization[],
     *      com.huashu.boss.sm.entity.privilege.Group)
     */
    public void saveGroupAccessOrg(OperatorDTO grantor,
            OrganizationDTO[] selectOrgs, GroupDTO group) {
        if (grantor == null || group == null || selectOrgs == null) {
            throw new IllegalArgumentException();
        }

        List ownOrgs = this.orgDao.findAcessOrgByGroupId(group.getGroupId(),
                OrganizationDTO.STATUS_NORMAL);
        List temp = EntityDTOConverter.converDTO2Entity(Arrays
                .asList(selectOrgs));

        // 选择出需要添加的组织
        List addedOrgs = CompareListUtils.compareOrgList(ownOrgs, temp);

        for (Iterator iter = addedOrgs.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            RelGroupOrgDTO relGroupOrg = new RelGroupOrgDTO(group.getGroup(),
                    org);
            this.relGroupOrgDao.save(relGroupOrg.getRelGroupOrg());

            // 记录授权日志
            logRelGroupOrg(grantor, group, org, PermitLogDTO.OPERATION_TYPE_ADD);
        }

        List removedOrgs = CompareListUtils.compareOrgList(temp, ownOrgs);
        for (Iterator iter = removedOrgs.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            this.relGroupRoleDao.deleteRelGroupRole(group.getGroupId(), new Long(org.getOrgId()));
            this.relGroupOrgDao.deleteRelGroupOrg(group.getGroupId(), new Long(
                    org.getOrgId()));

            // 记录授权日志
            logRelGroupOrg(grantor, group, org,
                    PermitLogDTO.OPERATION_TYPE_REMOVE);
        }
    }

    /**
     * 记录操作员组授权可访问组织日志。
     * 
     * @param grantor
     *            授权操作员。
     * @param group
     *            操作员组。
     * @param org
     *            组织机构。
     * @param optionType
     *            操作类型。
     */
    private void logRelGroupOrg(OperatorDTO grantor, GroupDTO group,
            Organization org, long optionType) {
        // 记录授权日志
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_GROUP,
                PermitLogDTO.PERMIT_TYPE_ORG);

        permitLog.setGranteeId(group.getGroupId());
        permitLog.setOrgId(org.getOrgId());
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.GroupManager#saveGroup(com.huashu.boss.sm.entity.privilege.Group)
     */
    public Long saveGroup(GroupDTO groupDTO, AppRequest appRequest)
            throws Exception {
        if (null == groupDTO) {
            throw new IllegalArgumentException();
        }
        Group group = groupDTO.getGroup();
        group.setCreateDate(new Date());
        // this.appService.saveApp(appRequest);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                group, this);
        this.roleDao.saveOrUpdate(returnObj);
        return new Long(group.getGroupId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.GroupManager#delGroup(com.huashu.boss.sm.entity.privilege.Group[])
     */
    public void deleteGroup(GroupDTO[] groups, AppRequest appRequest)
            throws Exception {
        if (null == groups) {
            throw new IllegalArgumentException();
        }
        // List groupDTOlist = Arrays.asList(groups);
        this.appService.saveApp(appRequest);
        Object returnObj;
        for (int i = 0; i < groups.length; i++) {
            returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                    groups[i].getGroup(), this);
            this.roleDao.saveOrUpdate(returnObj);
        }
        // this.groupDao.batchDelete(EntityDTOConverter.converDTO2Entity(groupDTOlist));

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.GroupManager#saveGroupRoleForOrg(com.huashu.boss.sm.entity.privilege.Group,
     *      com.huashu.boss.sm.entity.privilege.Role[],
     *      com.huashu.boss.sm.entity.privilege.Organization)
     */
    public void saveGroupRoleForOrg(OperatorDTO grantor, GroupDTO group,
            RoleDTO[] selectRoles, OrganizationDTO org) {
        if (grantor == null || group == null || selectRoles == null
                || org == null) {
            throw new IllegalArgumentException();
        }

        List ownRoles = this.roleDao.findRoleOfGroup(group.getGroupId(), org
                .getOrgId());

        List temp = EntityDTOConverter.converDTO2Entity(Arrays
                .asList(selectRoles));

        // 选择需要添加的角色进行授权
        List addedRoles = CompareListUtils.compareRoleList(ownRoles, temp);

        for (Iterator iter = addedRoles.iterator(); iter.hasNext();) {
            Role role = (Role) iter.next();
            RelGroupRoleDTO relGroupRole = new RelGroupRoleDTO(
                    group.getGroup(), role, org.getOrg());
            this.relGroupRoleDao.save(relGroupRole.getRelGroupRole());

            // 记录授权日志
            this.logRelGroupRole(grantor, group, org.getOrg(), role,
                    PermitLogDTO.OPERATION_TYPE_ADD);
        }

        // 选择需要删除的角色进行权限回收
        List removeRoles = CompareListUtils.compareRoleList(temp, ownRoles);
        for (Iterator iter = removeRoles.iterator(); iter.hasNext();) {
            Role role = (Role) iter.next();
            this.relGroupRoleDao.deleteRelGroupRole(group.getGroupId(), org
                    .getOrgId(), new Long(role.getRoleId()));

            // 记录授权日志
            this.logRelGroupRole(grantor, group, org.getOrg(), role,
                    PermitLogDTO.OPERATION_TYPE_REMOVE);

        }
    }

    private void logRelGroupRole(OperatorDTO grantor, GroupDTO group,
            Organization org, Role role, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_GROUP,
                PermitLogDTO.PERMIT_TYPE_ROLE);

        permitLog.setGranteeId(group.getGroupId());
        permitLog.setOrgId(org.getOrgId());
        permitLog.setRoleId(role.getRoleId());
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupRoleForOrgByGroupId(java.lang.Long)
     */
    public List findGroupRoleForOrgsByGroupId(Long groupId) {
        if (null == groupId) {
            throw new IllegalArgumentException();
        }

        StringBuffer hql = new StringBuffer(
                "select new "+RelGroupRoleDTO.class.getName()+"(group,role,org)");
        hql
                .append(" from Group group,Role role,Organization org,RelGroupRole rgr");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and group.").append(Group.PROP_GROUP_ID).append("=rgr.")
                .append(RelGroupRole.PROP_GROUP_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_ID).append("=rgr.")
                .append(RelGroupRole.PROP_ROLE_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID)
                .append("=rgr.").append(RelGroupRole.PROP_ORG_ID);
        List result = this.groupDao.query(hql.toString(),
                new Object[] { groupId });
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupRoleForOrgsOfOperator(java.lang.Long,
     *      int)
     */
    public List findGroupRoleForOrgsOfOperator(Long operatorId, Long roleType) {
        if (null == operatorId) {
            throw new IllegalArgumentException();
        }

        StringBuffer hql = new StringBuffer(
                "select new "+RelGroupRoleDTO.class.getName()+"(group,role,org)");
        //StringBuffer hql = new StringBuffer("select group,role,org");
        hql
                .append(" from Group group,Role role,Organization org,RelGroupRole rgr,RelOperGroup rog");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        hql.append(" and group.").append(Group.PROP_GROUP_ID).append("=rgr.")
                .append(RelGroupRole.PROP_GROUP_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_ID).append("=rgr.")
                .append(RelGroupRole.PROP_ROLE_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID)
                .append("=rgr.").append(RelGroupRole.PROP_ORG_ID);
        List result = this.groupDao.query(hql.toString(), new Object[] {
                operatorId, roleType });
        
        /*List ret = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object[] item = (Object[]) iterator.next();
            RelGroupRoleDTO dto = new RelGroupRoleDTO((Group)item[0],(Role)item[1],(Organization)item[2]);
            ret.add(dto);
        }
        return ret;
        */
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findAvailableGroupsByOperator(java.util.List)
     */
    public List findAvailableGroupsByOperator(Long operatorId, Long orgId) {
        if (null == operatorId || orgId == null) {
            throw new IllegalArgumentException();
        }
        // modified by libf,2006/12/27
        /*
         * List result = this.groupDao.findAvailableGroupsByOperator(operatorId
         * ,orgIds); filterGroups(result);
         */
        List result = this.groupDao.findAvailableGroupsByOperator(operatorId,
                orgId);
        // end modified
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupsByOperator(java.lang.Long)
     */
    public List findGroupsByOperator(Long operatorId) {
        if (null == operatorId) {
            throw new IllegalArgumentException();
        }
        List result = this.groupDao.findGroupsByOperator(operatorId);
        filterGroups(result);
        return EntityDTOConverter.converGroup2DTO(result);
    }
    
    public List findGroupsByOperator(Long operatorId , Long orgId) {
        if (null == operatorId) {
            throw new IllegalArgumentException();
        }
        List result = this.groupDao.findGroupsByOperator(operatorId ,orgId);
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupAccessOrgsOfOperator(java.lang.Long)
     */
    public List findGroupAccessOrgsOfOperator(Long operatorId) {
        if (null == operatorId) {
            throw new IllegalArgumentException();
        }

        StringBuffer hql = new StringBuffer(
                "select new "+com.ft.sm.dto.RelGroupRoleDTO.class.getName()+"(group,org)");
        hql
                .append(" from Group group,Organization org,RelGroupOrg rgo,RelOperGroup rog");
        hql.append(" where rgo.").append(RelGroupOrg.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and group.").append(Group.PROP_GROUP_ID).append("=rgo.")
                .append(RelGroupOrg.PROP_GROUP_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append("rgo")
                .append(RelGroupOrg.PROP_ORG_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        List result = this.groupDao.query(hql.toString(),
                new Object[] { operatorId });
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupsByResource(java.lang.Long,
     *      java.lang.Long)
     */
    public List findGroupsByResource(Long orgId, Long resourceId) {
        if (null == resourceId) {
            throw new IllegalArgumentException();
        }

        List result = this.groupDao.findGroupsByResource(orgId, resourceId);
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        Group group = this.groupDao.loadById(id);
        Organization org = this.orgDao
                .loadById(new Long(group.getOwnerOrgId()));
        return new GroupDTO(group, org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.groupDao.loadByIds(Group.class, ids);
        filterGroups(result);
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List result = this.groupDao.query(hql, params, page);
        filterGroups(result);
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findAllGroup()
     */
    public List findAllGroup() {
        List result = this.groupDao.findAll();
        filterGroups(result);
        return EntityDTOConverter.converGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.GroupManager#findGroupsByNameAndOrgIdAndOpName(java.lang.String,
     *      java.lang.Long, java.lang.String)
     */
    public List searchGroup(Long orgId, String groupName, boolean includeAll) {
        // List result =
        // this.groupDao.findGroupsByNameAndOrgIdAndOpName(groupName,orgId,opName);
        List group_org = this.groupDao.searchGroup(orgId, groupName, includeAll);
        List<GroupDTO> result = new ArrayList<GroupDTO>();
        for (Iterator iterator = group_org.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            GroupDTO opDto = new GroupDTO((Group)object[0],(Organization)object[1]);
            result.add(opDto);
        }
        return result;
    }

    /**
     * 过滤过期(删除)的组信息
     * 
     * @param groups
     */
    private void filterGroups(List groups) {
        for (Iterator iter = groups.iterator(); iter.hasNext();) {
            Group element = (Group) iter.next();
            /*
             * if (element.getExpireDate() != null &&
             * element.getExpireDate().equals(element.getUpdateDate())) {
             * iter.remove(); }
             */

            if (element.getExpireDate() != null)
                iter.remove();
        }
    }

    /**
     * @spring.property ref="GroupDao"
     * 
     * @param groupDao
     *            The groupDao to set.
     */
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * @spring.property ref="OrganizationDao"
     * @param orgDao
     *            The orgDao to set.
     */
    public void setOrgDao(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    /**
     * @spring.property ref="RoleDao"
     * 
     * @param roleDao
     *            The roleDao to set.
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * @spring.property ref="RelGroupOrgDao"
     * @param relGroupOrgDao
     *            the relGroupOrgDao to set
     */
    public void setRelGroupOrgDao(RelGroupOrgDao relGroupOrgDao) {
        this.relGroupOrgDao = relGroupOrgDao;
    }

    /**
     * @spring.property ref="RelGroupRoleDao"
     * @param relGroupRoleDao
     *            the relGroupRoleDao to set
     */
    public void setRelGroupRoleDao(RelGroupRoleDao relGroupRoleDao) {
        this.relGroupRoleDao = relGroupRoleDao;
    }

    /**
     * @spring.property ref="PermitLogDao"
     * @param permitLogDao
     *            the permitLogDao to set
     */
    public void setPermitLogDao(PermitLogDao permitLogDao) {
        this.permitLogDao = permitLogDao;
    }

    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *            the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

    public Object getEntityObject(Class arg0, Serializable arg1) {
        return this.groupDao.loadById(arg1);
    }

    public void saveHisObject(Object arg0) {
        this.groupDao.saveOrUpdate(arg0);
    }

}