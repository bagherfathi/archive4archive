package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.DataResourceEntryDao;
import com.ft.busi.sm.impl.dao.PermitLogDao;
import com.ft.busi.sm.impl.dao.RelOperRoleDao;
import com.ft.busi.sm.impl.dao.RelRoleDataResDao;
import com.ft.busi.sm.impl.dao.RelRoleOrgDao;
import com.ft.busi.sm.impl.dao.RelRoleResDao;
import com.ft.busi.sm.impl.dao.ResourceDao;
import com.ft.busi.sm.impl.dao.RoleDao;
import com.ft.busi.sm.impl.dao.RoleHisDao;
import com.ft.busi.sm.model.RoleManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.PermitLogDTO;
import com.ft.sm.dto.RelRoleDataResDTO;
import com.ft.sm.dto.RelRoleResDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelRoleDataRes;
import com.ft.sm.entity.RelRoleOrg;
import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Resource;
import com.ft.sm.entity.Role;

/**
 * 角色管理实现类.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.aop-bean id="roleManager" parent="transactionProxyFactoryBean"
 *                  target="roleManagerImpl"
 * 
 * @spring.bean id="roleManagerImpl"
 */
public class RoleManagerImpl implements RoleManager, BusiBaseService {
    /**
     * 角色数据处理类
     */
    private RoleDao roleDao;

    /**
     * 权限数据处理类
     */
    private ResourceDao resourceDao;

    private DataResourceEntryDao dataResourceEntryDao;

    private PermitLogDao permitLogDao;

    private RelRoleResDao relRoleResDao;

    private RelRoleDataResDao relRoleDataResDao;

    private RelRoleOrgDao relRoleOrgDao;

    private RelOperRoleDao relOperRoleDao;

    private RoleHisDao roleHisDao;

    private BusiAppService appService;

    public void updateRole(OperatorDTO grantor, RoleDTO role,
            List resourcesDTO, Long[] orgIds, AppRequest appRequest)
            throws Exception {
        if (grantor == null || role == null || resourcesDTO == null) {
            throw new IllegalArgumentException();
        }

        List resources = EntityDTOConverter.converDTO2Entity(resourcesDTO);

        Role oldRole = (Role) this.roleDao.getEntityByIdentityAttribute(
                Role.class, Role.PROP_ROLE_ID, role.getRoleId());
        oldRole.setRoleName(role.getRoleName());
        oldRole.setRoleDesc(role.getDescription());

        // oldRole.setOperatorId(grantor.getOperatorId().longValue());
        // oldRole.setOrgId(grantor.getOrgId().longValue());

        // 查找角色已有的功能权限
        List ownResources = resourceDao.findResourcesOfRole(new Long(oldRole
                .getRoleId()));

        // 比较已有的功能权限列表和更新的功能权限列表，找出需要增加的功能权限
        List addResource = CompareListUtils.compareResourceList(ownResources,
                resources);
        for (Iterator iter = addResource.iterator(); iter.hasNext();) {
            Resource resource = (Resource) iter.next();
            RelRoleResDTO relRoleRes = new RelRoleResDTO(role.getRole(),
                    resource);
            this.relRoleResDao.save(relRoleRes.getRelRoleRes());

            // 记录授权日志
            logRelRoleRes(grantor, role.getRoleId(), resource.getResourceId(),
                    PermitLogDTO.OPERATION_TYPE_ADD);
        }

        // 比较已有的功能权限列表和更新的功能权限列表，找出需要删除的功能权限
        List removedResources = CompareListUtils.compareResourceList(resources,
                ownResources);

        for (Iterator iter = removedResources.iterator(); iter.hasNext();) {
            Resource resource = (Resource) iter.next();
            this.relRoleResDao.deleteRelRoleRes(role.getRoleId(), new Long(
                    resource.getResourceId()));
        }

        // 适应组织授权更新

        List ownOrgs = relRoleOrgDao.findOrgsOfRole(role.getRoleId());
        List<Long> ownOrgIds = new ArrayList<Long>();
        for (Iterator iterator = ownOrgs.iterator(); iterator.hasNext();) {
            Organization org = (Organization) iterator.next();
            ownOrgIds.add(new Long(org.getOrgId()));
        }

        List addOrgIds = CompareListUtils.compareByIds((Long[]) ownOrgIds
                .toArray(new Long[ownOrgIds.size()]), orgIds);

        for (Iterator iterator = addOrgIds.iterator(); iterator.hasNext();) {
            Long orgId = (Long) iterator.next();
            RelRoleOrg relRoleOrg = new RelRoleOrg();
            relRoleOrg.setOrgId(orgId.longValue());
            relRoleOrg.setRoleId(role.getRoleId().longValue());
            relRoleOrgDao.save(relRoleOrg);
            // 记录授权日志
            logRelRoleOrg(grantor, role.getRoleId(), orgId.longValue(),
                    PermitLogDTO.OPERATION_TYPE_ADD);
        }

        List removeOrgIds = CompareListUtils.compareByIds(orgIds,
                (Long[]) ownOrgIds.toArray(new Long[ownOrgIds.size()]));
        for (Iterator iterator = removeOrgIds.iterator(); iterator.hasNext();) {
            Long orgId = (Long) iterator.next();
            this.relRoleOrgDao.deleteRelRoleOrg(role.getRoleId(), orgId);
            // 删除相关操作员的权限
            this.relOperRoleDao
                    .deleteRelOperRole(null, role.getRoleId(), orgId,false);
            // 记录授权日志
            logRelRoleOrg(grantor, role.getRoleId(), orgId.longValue(),
                    PermitLogDTO.OPERATION_TYPE_REMOVE);
        }
        // this.roleDao.saveOrUpdate(oldRole);
        saveOrUpdateRole(appRequest, oldRole);

    }

    /**
     * 更新或新增角色信息，并保存历史记录
     * 
     * @param appRequest
     * @param oldRole
     */
    private void saveOrUpdateRole(AppRequest appRequest, Role oldRole)
            throws Exception {
        // this.appService.saveApp(appRequest);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                oldRole, this);
        this.roleDao.saveOrUpdate(returnObj);
    }

    /**
     * @param grantor
     * @param role
     * @param resource
     */
    private void logRelRoleRes(OperatorDTO grantor, Long roleId,
            long resourceId, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_ROLE,
                PermitLogDTO.PERMIT_TYPE_RESOURCE);
        permitLog.setGranteeId(roleId);
        permitLog.setResourceId(resourceId);
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#deleteRoles(com.huashu.boss.sm.entity.Operator,
     *      java.io.Serializable[])
     */
    public void deleteRole(OperatorDTO grantor, RoleDTO role,
            AppRequest appRequest) throws Exception {
        if (grantor == null || role == null) {
            throw new IllegalArgumentException();
        }

        // 功能角色
        if (role.getType() == RoleDTO.ROLE_TYPE_FUNCTION) {
            List result = this.relRoleDataResDao
                    .findRelRoleDataResByRoleId(role.getRoleId());
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                RelRoleDataRes roleDataRes = (RelRoleDataRes) iterator.next();
                logRelRoleDataRes(grantor, role.getRole(), roleDataRes
                        .getEntryId(), PermitLogDTO.OPERATION_TYPE_REMOVE);
            }

            this.relRoleDataResDao.batchDelete(result);
            this.relRoleOrgDao.deleteRelRoleOrg(role.getRoleId(), null);
        } else {
            List relRoleResList = this.relRoleResDao
                    .findRelRoleResByRoleId(role.getRoleId());
            for (Iterator iterator = relRoleResList.iterator(); iterator
                    .hasNext();) {
                RelRoleRes roleRes = (RelRoleRes) iterator.next();
                // 记录授权日志
                logRelRoleRes(grantor, role.getRoleId(), roleRes
                        .getResourceId(), PermitLogDTO.OPERATION_TYPE_REMOVE);
            }
            // 删除角色拥有功能权限
            this.relRoleResDao.batchDelete(relRoleResList);
        }

        Role delObject = (Role) this.roleDao.getEntityByIdentityAttribute(
                Role.class, Role.PROP_ROLE_ID, role.getRoleId());
        // this.roleDao.delete(delObject);
        this.appService.saveApp(appRequest);
        Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                delObject, this);
        this.roleDao.saveOrUpdate(returnObj);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#addDataRole(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.RoleDTO, java.util.List,
     *      com.huashu.boss.busi.dto.AppRequest)
     */

    public Long addDataRole(OperatorDTO grantor, RoleDTO roleDto,
            List entryList, AppRequest appRequest) throws Exception {
        if (grantor == null || roleDto == null || entryList == null) {
            throw new IllegalArgumentException();
        }

        Role role = roleDto.getRole();
        // this.roleDao.save(role);
        this.saveOrUpdateRole(appRequest, role);

        for (Iterator iter = entryList.iterator(); iter.hasNext();) {
            DataResourceEntryDTO resourceEntryDto = (DataResourceEntryDTO) iter
                    .next();
            DataResourceEntry resourceEntry = resourceEntryDto
                    .getDataResourceEntry();
            RelRoleDataResDTO roleDataRes = new RelRoleDataResDTO(role,
                    resourceEntry);
            this.relRoleDataResDao.save(roleDataRes.getRelRoleDataRes());

            logRelRoleDataRes(grantor, role, resourceEntry.getEntryId(),
                    PermitLogDTO.OPERATION_TYPE_ADD);
        }

        return new Long(role.getRoleId());
    }

    /**
     * @param grantor
     * @param role
     * @param resourceEntry
     */
    private void logRelRoleDataRes(OperatorDTO grantor, Role role,
            long entryId, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_ROLE,
                PermitLogDTO.PERMIT_TYPE_DATARESOURCE);
        permitLog.setGranteeId(new Long(role.getRoleId()));
        permitLog.setResourceId(entryId);
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#saveRole(com.huashu.boss.sm.entity.Operator,
     *      com.huashu.boss.sm.entity.Role, java.util.List)
     */
    public Long addRole(OperatorDTO grantor, RoleDTO roleDto, List resources,
            Long[] orgIds, AppRequest appRequest) throws Exception {
        if (grantor == null || roleDto == null || resources == null) {
            throw new IllegalArgumentException();
        }

        Role role = roleDto.getRole();
        role.setCreateDate(new Date());
        role.setOrgId(grantor.getOrg().getOrgId());
        role.setOperatorId(grantor.getOperatorId().longValue());
        // this.roleDao.save(role);
        this.saveOrUpdateRole(appRequest, role);

        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            ResourceDTO resourceDto = (ResourceDTO) iter.next();
            Resource resource = resourceDto.getResource();
            RelRoleResDTO relRoleRes = new RelRoleResDTO(role, resource);
            this.relRoleResDao.save(relRoleRes.getRelRoleRes());

            // 记录授权日志
            logRelRoleRes(grantor, new Long(role.getRoleId()), resource
                    .getResourceId(), PermitLogDTO.OPERATION_TYPE_ADD);
        }

        //
        if (orgIds != null) {
            for (int i = 0; i < orgIds.length; i++) {
                RelRoleOrg relRoleOrg = new RelRoleOrg();
                relRoleOrg.setOrgId(orgIds[i].longValue());
                relRoleOrg.setRoleId(role.getRoleId());
                relRoleOrgDao.save(relRoleOrg);

                // 记录授权日志
                logRelRoleOrg(grantor, new Long(role.getRoleId()), orgIds[i]
                        .longValue(), PermitLogDTO.OPERATION_TYPE_ADD);

            }
        }

        return new Long(role.getRoleId());
    }

    /**
     * 记录角色适应组织的授权记录
     * 
     * @param grantor
     * @param roleId
     * @param entryId
     * @param optionType
     */
    private void logRelRoleOrg(OperatorDTO grantor, Long roleId, long entryId,
            long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_ROLE,
                PermitLogDTO.PERMIT_TYPE_RESOURCE);
        permitLog.setGranteeId(roleId);
        permitLog.setOrgId(entryId);
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#updateDataRole(com.huashu.boss.sm.entity.Operator,
     *      com.huashu.boss.sm.entity.Role, java.util.List)
     */
    public void updateDataRole(OperatorDTO grantor, RoleDTO role,
            List entryDTOList, AppRequest appRequest) throws Exception {
        if (grantor == null || role == null || entryDTOList == null) {
            throw new IllegalArgumentException();
        }

        List entryList = EntityDTOConverter.converDTO2Entity(entryDTOList);

        Role oldRole = (Role) this.roleDao.getEntityByIdentityAttribute(
                Role.class, Role.PROP_ROLE_ID, role.getRoleId());
        oldRole.setRoleName(role.getRoleName());
        oldRole.setRoleDesc(role.getDescription());

        if (entryList.size() > 0) {
            // 查找角色已有的业务权限
            List ownEntrys = this.dataResourceEntryDao
                    .findDataResourcesOfRole(role.getRoleId());

            // 比较已有的权限列表和更新的权限列表，找出需要增加的权限
            List addResource = CompareListUtils.compareDataResourceEntryList(
                    ownEntrys, entryList);
            for (Iterator iter = addResource.iterator(); iter.hasNext();) {
                DataResourceEntry entry = (DataResourceEntry) iter.next();
                RelRoleDataResDTO roleDataRes = new RelRoleDataResDTO(role
                        .getRole(), entry);
                this.relRoleDataResDao.save(roleDataRes.getRelRoleDataRes());

                logRelRoleDataRes(grantor, role.getRole(), entry.getEntryId(),
                        PermitLogDTO.OPERATION_TYPE_ADD);
            }

            // 比较已有的权限列表和更新的权限列表，找出需要删除的权限
            List removedResources = CompareListUtils
                    .compareDataResourceEntryList(entryList, ownEntrys);
            for (Iterator iter = removedResources.iterator(); iter.hasNext();) {
                DataResourceEntry entry = (DataResourceEntry) iter.next();
                this.relRoleDataResDao.deleteRelRoleDataRes(role.getRoleId(),
                        new Long(entry.getEntryId()));
                logRelRoleDataRes(grantor, role.getRole(), entry.getEntryId(),
                        PermitLogDTO.OPERATION_TYPE_REMOVE);
            }
        } else {
            // 清除所有和Role关联的DataResourceEntry
            resourceDao.delDataResourceByRoleId(role.getRoleId());
        }

        this.saveOrUpdateRole(appRequest, oldRole);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findAvailableRoleOfGroup(java.lang.Long,
     *      java.lang.Long)
     */
    public List findAvailableRolesOfGroup(Long groupId, Long orgId) {
        if (null == groupId) {
            throw new IllegalArgumentException();
        }

        List result = this.roleDao.findAvailableRoleOfGroup(groupId, orgId,
                RoleDTO.ROLE_TYPE_FUNCTION);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleOfGroup(java.lang.Long,
     *      java.lang.Long)
     */
    public List findRolesOfGroup(Long groupId, Long orgId) {
        if (null == groupId) {
            throw new IllegalArgumentException();
        }

        List result = this.roleDao.findRoleOfGroup(groupId, orgId);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findAvailableRolesOfOperator(java.lang.Long,
     *      java.lang.Long, int)
     */
    public List findAvailableRolesOfOperator(Long opId, Long orgId,
            long roleType) {
        if (null == opId || roleType <= 0) {
            throw new IllegalArgumentException();
        }

        List result = this.roleDao.findAvailableRolesOfOperator(opId, orgId,
                roleType);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRolesOfOperator(java.lang.Long,
     *      java.lang.Long, int)
     */
    public List findRolesOfOperator(Long opId, Long orgId, long roleType) {
        if (null == opId || roleType <= 0) {
            throw new IllegalArgumentException();
        }

        List result = this.roleDao.findRolesOfOperator(opId, orgId, roleType);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleByType(int)
     */
    public List findRoleByType(Long roleType) {
        List result = this.roleDao.findRoleByType(roleType);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleById(java.lang.Long)
     */
    public RoleDTO findRoleById(Long roleId) {
        if (null == roleId) {
            throw new IllegalArgumentException();
        }
        Role role = (Role) this.roleDao.getObjectById(Role.class, roleId);

        if (role != null && filterRole(role))
            return new RoleDTO(role);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        Role role = this.roleDao.getById(id);
        return new RoleDTO(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.roleDao.loadByIds(Role.class, ids);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
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
        List result = this.roleDao.query(hql, params, page);
        filterRoles(result);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntityObject(Class clazz, Serializable key) {
        return this.roleDao.getById(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object obj) {
        this.roleHisDao.saveOrUpdate(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleByRoleName(java.lang.String,
     *      long)
     */

    public List findRoleByRoleName(String roleName, long roleType) {
        List result = this.roleDao.findRoleByRoleName(roleName, roleType);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleByAdaptOrgId(java.lang.Long,
     *      long)
     */
    public List findRoleByAdaptOrgId(Long orgId, long roleType) {
        List result = this.roleDao.findRoleByAdaptOrgId(orgId, roleType);
        return EntityDTOConverter.converRole2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RoleManager#findRoleByName(java.lang.String,
     *      long)
     */
    public RoleDTO findRoleByName(String roleName, long roleType) {
        Role role = this.roleDao.findRoleByName(roleName, roleType);
        if(role != null){
            return new RoleDTO(role);
        }else{
            return null;
        }
    }
    
    /**
     * 获取所有业务角色对应业务权限关系。
     * @return
     * @throws Exception
     */
    public List findAllRelRoleDataRes() {
        List relRoleDataResList = this.relRoleDataResDao.loadAll(RelRoleDataRes.class);
        
        List<RelRoleDataResDTO> result = new ArrayList<RelRoleDataResDTO>();
        for (Iterator iterator = relRoleDataResList.iterator(); iterator.hasNext();) {
            RelRoleDataRes res = (RelRoleDataRes) iterator.next();
            result.add(new RelRoleDataResDTO(res));
        }
        
        return result;
    }

    /**
     * 过滤已过期的角色。
     * 
     * @param roles
     *                角色实体列表。
     */
    private void filterRoles(List roles) {
        for (Iterator iter = roles.iterator(); iter.hasNext();) {
            Role element = (Role) iter.next();
            if (filterRole(element)) {
                iter.remove();
            }
        }
    }

    /**
     * 
     * @param role
     * @return
     */
    private boolean filterRole(Role role) {
        /*
         * if (role.getExpireDate() != null &&
         * role.getExpireDate().equals(role.getUpdateDate())) { return true; }
         */
        if (role.getExpireDate() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @spring.property ref="RoleDao"
     * 
     * @param roleDao
     *                The roleDao to set.
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * @spring.property ref="ResourceDao"
     * 
     * @param resourceDao
     *                The resourceDao to set.
     */
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    /**
     * @spring.property ref="DataResourceEntryDao"
     * @param dataResourceEntryDao
     *                the dataResourceEntryDao to set
     */
    public void setDataResourceEntryDao(
            DataResourceEntryDao dataResourceEntryDao) {
        this.dataResourceEntryDao = dataResourceEntryDao;
    }

    /**
     * @spring.property ref="PermitLogDao"
     * @param permitLogDao
     *                the permitLogDao to set
     */
    public void setPermitLogDao(PermitLogDao permitLogDao) {
        this.permitLogDao = permitLogDao;
    }

    /**
     * @spring.property ref="RelRoleResDao"
     * @param relRoleResDao
     *                the relRoleResDao to set
     */
    public void setRelRoleResDao(RelRoleResDao relRoleResDao) {
        this.relRoleResDao = relRoleResDao;
    }

    /**
     * @spring.property ref="RelRoleDataResDao"
     * @param relRoleDataResDao
     *                the relRoleDataResDao to set
     */
    public void setRelRoleDataResDao(RelRoleDataResDao relRoleDataResDao) {
        this.relRoleDataResDao = relRoleDataResDao;
    }

    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

    /**
     * @spring.property ref="RoleHisDao"
     * @param roleHisDao
     *                the roleHisDao to set
     */
    public void setRoleHisDao(RoleHisDao roleHisDao) {
        this.roleHisDao = roleHisDao;
    }

    /**
     * @spring.property ref="RelRoleOrgDao"
     * @param relRoleOrgDao
     */
    public void setRelRoleOrgDao(RelRoleOrgDao relRoleOrgDao) {
        this.relRoleOrgDao = relRoleOrgDao;
    }

    /**
     * @spring.property ref="RelOperRoleDao"
     * @param relOperRoleDao
     */
    public void setRelOperRoleDao(RelOperRoleDao relOperRoleDao) {
        this.relOperRoleDao = relOperRoleDao;
    }

	public List findOperatorsOfRole(Long roleId, Long orgId) throws Exception {
		// TODO Auto-generated method stub
		return this.relOperRoleDao.findOperatorsOfRole(roleId, orgId);
	}

}
