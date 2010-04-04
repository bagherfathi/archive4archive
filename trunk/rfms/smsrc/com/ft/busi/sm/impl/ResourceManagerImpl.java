package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.ConsignPermitDao;
import com.ft.busi.sm.impl.dao.DataResourceDao;
import com.ft.busi.sm.impl.dao.DataResourceEntryDao;
import com.ft.busi.sm.impl.dao.ResourceDao;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.DataResource;
import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleDataRes;
import com.ft.sm.entity.Resource;

/**
 * 功能权限实体和业务权限实体管理实现类.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.bean id="resourceManagerImpl"
 */
public class ResourceManagerImpl implements ResourceManager, BusiBaseService {
    /**
     * 权限数据处理类
     */
    private ResourceDao resourceDao;

    private DataResourceDao dataResourceDao;

    private DataResourceEntryDao dataResourceEntryDao;

    private BusiAppService appService;

    /**
     * 委托权限数据访问类
     */
    private ConsignPermitDao commissionDao;

    /**
     * @spring.property ref = "ConsignPermitDao"
     * @param commissionDao
     *                The commissionDao to set.
     */
    public void setCommissionDao(ConsignPermitDao commissionDao) {
        this.commissionDao = commissionDao;
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
     * @spring.property ref="DataResourceDao"
     * @param dataResourceDao
     *                the dataResourceDao to set
     */
    public void setDataResourceDao(DataResourceDao dataResourceDao) {
        this.dataResourceDao = dataResourceDao;
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
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.ResourceManager#createMenu(com.huashu.boss.sm.entity.privilege.Resource,
     *      com.huashu.boss.sm.entity.privilege.Resource)
     */
    public ResourceDTO addResource(ResourceDTO resourceDto,
            ResourceDTO parentDto, AppRequest appRequest) throws Exception {
        if (resourceDto == null) {
            throw new IllegalArgumentException();
        }

        // Resource resource = resourceDto.getResource();
        resourceDto.setTitle(resourceDto.getTitle().trim());
        resourceDto.getResource().setCreateDate(new Date());

        int order = 0;
        // 对菜单类功能权限做排序设置
        if (resourceDto.getType() == ResourceDTO.MENU_TYPE) {
            resourceDto.setUrl(resourceDto.getUrl().trim());
            resourceDto.setResourceKey(resourceDto.getUrl());

            if (null != parentDto && null != parentDto.getResourceId()
                    && parentDto.getResourceId().longValue() != 0) {
                order = this.resourceDao.getMaxOrder(parentDto.getResourceId()) + 1;
            } else {
                order = this.resourceDao.getRootMaxOrder() + 1;
            }
        }

        // 判断是否有父对象
        if (null != parentDto && null != parentDto.getResourceId()
                && parentDto.getResourceId().longValue() != 0) {
            resourceDto.setOrder(order);
            resourceDto.setParent(parentDto.getResource());
            // this.resourceDao.save(resourceDto.getResource());
            this.saveOrUpdateResource(resourceDto, appRequest);
            resourceDto.setResourcePath(parentDto.getResourcePath()
                    + resourceDto.getResourceId() + ResourceDTO.PATH_SEPARATOR);

        } else {
            // 添加父项
            resourceDto.setOrder(order);
            resourceDto.getResource().setCreateDate(new Date());
            // this.resourceDao.save(resourceDto.getResource());
            this.saveOrUpdateResource(resourceDto, appRequest);
            resourceDto.setParentId(resourceDto.getResourceId());
            resourceDto.setResourcePath(ResourceDTO.PATH_SEPARATOR
                    + resourceDto.getResourceId().toString()
                    + ResourceDTO.PATH_SEPARATOR);
        }

        if (null == resourceDto.getResourceKey()
                || resourceDto.getResourceKey().length() <= 0) {
            resourceDto.setResourceKey(resourceDto.getResourcePath());
        }

        this.resourceDao.update(resourceDto.getResource());
        // this.saveOrUpdateResource(resourceDto, appRequest);

        return resourceDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#delResource(com.huashu.boss.sm.entity.Resource)
     */
    public void deleteResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception {
        if (resource.getResource() == null) {
            throw new IllegalArgumentException();
        }
        this.appService.saveApp(appRequest);
        Object returnObj = this.appService.deleteAndsettingHistoryObject(
                appRequest, resource.getResource(), this);
        this.dataResourceDao.saveOrUpdate(returnObj);
        // this.resourceDao.deleteResource(resourceId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.ResourceManager#updateResource(com.huashu.boss.sm.entity.privilege.Resource,
     *      java.util.Map)
     */
    public void updateResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception {
        if (resource == null) {
            throw new IllegalArgumentException();
        }

        // 设置菜单的Code值
        if (resource.getType() == ResourceDTO.MENU_TYPE) {
            if (resource.getUrl() == null
                    || resource.getUrl().trim().equals("")) {
                resource.setResourceKey(resource.getResourcePath());
            } else {
                if (!resource.getResourceKey().equals(resource.getUrl().trim()))
                    resource.setResourceKey(resource.getUrl().trim());
            }
        }
        // this.resourceDao.update(resource.getResource());
        saveOrUpdateResource(resource, appRequest);
    }

    /**
     * 新增或更新资源,并记录历史记录
     * 
     * @param resource
     * @param appRequest
     * @throws Exception
     */
    private void saveOrUpdateResource(ResourceDTO resource,
            AppRequest appRequest) throws Exception {
        // this.appService.saveApp(appRequest);
        Object returnObj = this.appService.saveAndsettingHistoryObject(
                appRequest, resource.getResource(), this);
        this.dataResourceDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#addDataResource(com.huashu.boss.sm.dto.DataResourceDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public Long addDataResource(DataResourceDTO dataResource,
            AppRequest appRequest) throws Exception {
        if (dataResource == null) {
            throw new IllegalArgumentException();
        }

        dataResource.getDataResource().setCreateDate(new Date());

        // this.appService.saveApp(appRequest);
        Object returnObj = this.appService.saveAndsettingHistoryObject(
                appRequest, dataResource.getDataResource(), this);
        this.dataResourceDao.save(returnObj);

        return dataResource.getResourceId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#deleteDataResource(com.huashu.boss.sm.dto.DataResourceDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public void deleteDataResource(DataResourceDTO dataResource,
            AppRequest appRequest) throws Exception {
        if (dataResource == null) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);

        // this.dataResourceEntryDao.deleteEntriesOfDataResource(dataResourceId);
        Object returnObj;
        List entries = this.dataResourceEntryDao
                .findEntryByDataResourceId(dataResource.getResourceId());
        for (Iterator iter = entries.iterator(); iter.hasNext();) {
            DataResourceEntry element = (DataResourceEntry) iter.next();
            returnObj = this.appService.deleteAndsettingHistoryObject(
                    appRequest, element, this);
            this.dataResourceEntryDao.saveOrUpdate(returnObj);
        }
        returnObj = this.appService.deleteAndsettingHistoryObject(appRequest,
                dataResource.getDataResource(), this);
        this.dataResourceDao.saveOrUpdate(returnObj);
        // this.dataResourceDao.deleteDataResource(dataResource);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#updateDataResource(com.huashu.boss.sm.dto.DataResourceDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateDataResource(DataResourceDTO dataResource,
            AppRequest appRequest) throws Exception {
        if (dataResource == null) {
            throw new IllegalArgumentException();
        }
        // this.appService.saveApp(appRequest);
        Object returnObj = this.appService.saveAndsettingHistoryObject(
                appRequest, dataResource.getDataResource(), this);
        this.dataResourceDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllDataResource()
     */
    public List findAllDataResource() {
        List result = this.dataResourceDao.findAllDataResource();
        return EntityDTOConverter.converDataResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllDataResourceEntry()
     */
    public List findAllDataResourceEntry() {
        //modified by libf,2007/03/05
        StringBuffer hql = new StringBuffer("select res,resEntry ");
        hql.append("from DataResource res,DataResourceEntry resEntry");
        hql.append(" where res.").append(DataResource.PROP_RESOURCE_ID).append(
                "=resEntry.").append(DataResourceEntry.PROP_RESOURCE_ID);
        hql.append(" and resEntry.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        List entryDTOs = this.dataResourceEntryDao.query(hql.toString());

        List<DataResourceEntryDTO> result = new ArrayList<DataResourceEntryDTO>();
        for (Iterator iterator = entryDTOs.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            DataResourceEntryDTO dto = new DataResourceEntryDTO(
                    (DataResource) object[0], (DataResourceEntry) object[1]);
            result.add(dto);
        }
        return result;
        /*
        List result = this.dataResourceEntryDao.findAll();
        return EntityDTOConverter.converDataResourceEntry2DTO(result);
        */
        //end modified
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findChildren(java.lang.Long)
     */
    public List findChildren(Long resourceId) {
        if (null == resourceId) {
            throw new IllegalArgumentException();
        }

        List result = this.resourceDao.findChildrenResourceById(resourceId);

        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#updateResource(java.util.List)
     */
    public void updateResources(List resource) {
        if (null == resource) {
            throw new IllegalArgumentException();
        }

        List entityList = EntityDTOConverter.converDTO2Entity(resource);
        this.resourceDao.batchUpdate(entityList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllResources()
     */
    public List findAllResources() {
        List result = this.resourceDao.findAllResources();
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findResourceOfRole(java.lang.Long)
     */
    public List findResourceOfRole(Long roleId) {
        if (null == roleId) {
            throw new IllegalArgumentException();
        }

        List result = this.resourceDao.findResourcesOfRole(roleId);
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllCheckedResourceByRoleId(java.lang.Long)
     */
    public List findAllCheckedResourceByRoleId(Long roleId) {
        if (null == roleId) {
            throw new IllegalArgumentException();
        }

        List result = this.resourceDao.findAllCheckedResourceByRoleId(roleId);
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findDataResourcesOfRole(java.lang.Long)
     */
    public List findDataResourcesOfRole(Long roleId) {
        if (null == roleId) {
            throw new IllegalArgumentException();
        }

        // modified by libf,2007/03/05
        /*
         * StringBuffer hql = new StringBuffer( "select new
         * com.huashu.boss.sm.dto.DataResourceEntryDTO(res,resEntry)"); hql
         * .append("from DataResource res,DataResourceEntry
         * resEntry,RelRoleDataRes rrd"); hql.append(" where
         * resEntry.").append(DataResourceEntry.PROP_ENTRY_ID)
         * .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID); hql.append("
         * and res.").append(DataResource.PROP_RESOURCE_ID).append(
         * "=resEntry.").append(DataResourceEntry.PROP_RESOURCE_ID);
         * hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID)
         * .append("=?"); hql.append(" and
         * res.").append(DataResourceEntry.PROP_EXPIRE_DATE) .append(" is
         * null");
         * 
         * List entryDTOs = this.dataResourceEntryDao.query(hql.toString(), new
         * Object[] { roleId });
         * 
         * return entryDTOs;
         */

        StringBuffer hql = new StringBuffer("select res,resEntry ");
        hql
                .append("from DataResource res,DataResourceEntry resEntry,RelRoleDataRes rrd");
        hql.append(" where resEntry.").append(DataResourceEntry.PROP_ENTRY_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID);
        hql.append(" and res.").append(DataResource.PROP_RESOURCE_ID).append(
                "=resEntry.").append(DataResourceEntry.PROP_RESOURCE_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID)
                .append("=?");
        hql.append(" and res.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        List entryDTOs = this.dataResourceEntryDao.query(hql.toString(),
                new Object[] { roleId });

        List<DataResourceEntryDTO> result = new ArrayList<DataResourceEntryDTO>();
        for (Iterator iterator = entryDTOs.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            DataResourceEntryDTO dto = new DataResourceEntryDTO(
                    (DataResource) object[0], (DataResourceEntry) object[1]);
            result.add(dto);
        }
        return result;
        // end modified
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllResourcesOfOperator(java.lang.Long,
     *      java.lang.Long, java.util.Date)
     */
    @SuppressWarnings("unchecked")
	public List findAllACLResourcesOfOperator(Long operatorId, Long orgId,
            Date time) {

        if (null == operatorId || null == orgId || null == time) {
            throw new IllegalArgumentException();
        }

        Map ret = new HashMap();

        // 查询操作员直接赋予的角色拥有的权限
        List result = this.resourceDao.findOpRoleACLResources(operatorId,
                orgId, RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询操作员继承组中角色拥有的权限
        result = this.resourceDao.findOpGroupRoleACLResources(operatorId,
                orgId, RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询直接赋予操作员的权限
        result = this.resourceDao.findOpRoleACLResources(operatorId, orgId,
                RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询委托权限
        result = this.commissionDao.findValidResources(operatorId, orgId, time);
        putResourceList2Map(ret, result);

        List resourceACL = new ArrayList();

        for (Iterator iter = ret.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            resourceACL.add(ret.get(key));
        }

        filterResources(resourceACL);

        return EntityDTOConverter.converResource2DTO(resourceACL);
    }

    @SuppressWarnings("unchecked")
	public List findAllACLResourcesOfOperator(Long operatorId,
            OrganizationDTO org, Date time) {

        if (null == operatorId || null == org || null == time) {
            throw new IllegalArgumentException();
        }

        Map ret = new HashMap();

        // 查询操作员直接赋予的角色拥有的权限

        List result = this.resourceDao.findOpRoleResources(operatorId, org
                .getLocationIds(), RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询操作员继承组中角色拥有的权限
        result = this.resourceDao.findOpGroupRoleACLResources(operatorId, org.getLocationIds(),RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询直接赋予操作员的权限
        result = this.resourceDao.findOpRoleACLResources(operatorId, org.getLocationIds(),RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询委托权限
        result = this.commissionDao.findValidResources(operatorId,org.getLocationIds(), time);
        putResourceList2Map(ret, result);

        List resourceACL = new ArrayList();

        for (Iterator iter = ret.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            resourceACL.add(ret.get(key));
        }

        filterResources(resourceACL);

        return EntityDTOConverter.converResource2DTO(resourceACL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findResourceOfOpByResource(java.lang.Long,
     *      java.lang.Long)
     */
    public List findACLResourcesOfOp(Long operatorId, Long orgId) {
        List result = this.resourceDao.findOpRoleACLResources(operatorId,
                orgId, RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);

        filterResources(result);

        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findResourcesExcludeConsignedOfOperator(java.lang.Long,
     *      java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findACLResourcesExcludeConsignedOfOperator(Long operatorId,
            Long orgId) {

        if (null == operatorId || null == orgId) {
            throw new IllegalArgumentException();
        }
        
        Organization org = (Organization)this.resourceDao.getObjectById(Organization.class, orgId);
        if(org == null) 
            return new ArrayList();
        
        OrganizationDTO orgDTO = new OrganizationDTO(org);
        
        Map ret = new HashMap();

        // 查询操作员直接赋予的角色拥有的权限
        List result = this.resourceDao.findOpRoleACLResources(operatorId,
                orgDTO.getLocationIds(), RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询操作员继承组中角色拥有的权限
        result = this.resourceDao.findOpGroupRoleACLResources(operatorId,
                orgDTO.getLocationIds(), RoleDTO.ROLE_TYPE_FUNCTION);
        putResourceList2Map(ret, result);

        // 查询直接赋予操作员的权限
        result = this.resourceDao.findOpRoleACLResources(operatorId, orgDTO.getLocationIds(),
                RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);
        putResourceList2Map(ret, result);

        List resourceACL = new ArrayList();

        for (Iterator iter = ret.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            resourceACL.add(ret.get(key));
        }

        filterResources(resourceACL);

        return EntityDTOConverter.converResource2DTO(resourceACL);
    }

    /**
     * 功能权限List转Map
     * 
     * @param map
     * @param resourceList
     */
    @SuppressWarnings("unchecked")
	private void putResourceList2Map(Map map, List resourceList) {
        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            Resource element = (Resource) iter.next();
            if (!map.containsKey("" + element.getResourceId())) {
                map.put("" + element.getResourceId(), element);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findDataResourcesExcludeConsignedOfOperator(java.lang.Long)
     */
    public List findDataResourceEntriesOfOperator(Long operatorId) {
        if (null == operatorId) {
            throw new IllegalArgumentException();
        }

        // 获得操作员对应的业务权限
        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.DataResourceEntryDTO(res,entry)");
        hql
                .append(" from DataResource res,DataResourceEntry entry,RelRoleDataRes rrd,RelOperRole ror");
        // hql.append(" where
        // role.").append(Role.PROP_ROLE_ID).append("=rrd.").append(RelRoleDataRes.PROP_ROLE_ID);
        hql.append(" where entry.").append(DataResourceEntry.PROP_ENTRY_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=ror.").append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and res.").append(DataResource.PROP_RESOURCE_ID).append(
                "=entry.").append(DataResourceEntry.PROP_RESOURCE_ID);

        List entryDTOs = this.resourceDao.query(hql.toString(),
                new Object[] { operatorId });
        filterDataResourceEntryDTO(entryDTOs);
        return entryDTOs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllResourcesExcludeConsignedOfOperator(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findAllResourcesExcludeConsignedOfOperator(Long operatorId) {

        if (null == operatorId) {
            throw new IllegalArgumentException();
        }
        Map ret = new HashMap();

        List result = this.resourceDao.findAllResourceOpRole(operatorId);
        putResourceList2Map(ret, result);

        result = this.resourceDao.findAllResourcesOpGroupRole(operatorId);
        putResourceList2Map(ret, result);

        List resourceList = new ArrayList();

        for (Iterator iter = ret.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            resourceList.add(ret.get(key));
        }

        filterResources(resourceList);

        return EntityDTOConverter.converResource2DTO(resourceList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findAllResourceOfGroup(java.lang.Long)
     */
    public List findAllResourceOfGroup(Long groupId) {
        if (null == groupId) {
            throw new IllegalArgumentException();
        }

        List result = this.resourceDao.findAllResourceOfGroup(groupId);
        filterResources(result);
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#addDataResourceEntry(com.huashu.boss.sm.dto.DataResourceEntryDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public Long addDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception {

        if (null == entry) {
            throw new IllegalArgumentException();
        }

        if (null == entry.getMinValue() || entry.getMinValue().equals("")) {
            entry.setMinValue("0");
        }
        entry.getDataResourceEntry().setCreateDate(new Date());

        // this.appService.saveApp(appRequest);
        Object returnObj = this.appService.saveAndsettingHistoryObject(
                appRequest, entry.getDataResourceEntry(), this);

        this.resourceDao.saveOrUpdate(returnObj);

        return entry.getEntryId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#deleteDataResourceEntry(com.huashu.boss.sm.entity.DataResourceEntry)
     */
    public void deleteDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception {

        if (null == entry) {
            throw new IllegalArgumentException();
        }
        this.appService.saveApp(appRequest);
        Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                entry.getDataResourceEntry(), this);
        this.dataResourceEntryDao.saveOrUpdate(returnObj);
        // this.resourceDao.delete(entry.getDataResourceEntry());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#updateDataResourceEntry(com.huashu.boss.sm.dto.DataResourceEntryDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception {

        if (null == entry) {
            throw new IllegalArgumentException();
        }

        DataResourceEntry dataResourceEntry = this.dataResourceEntryDao
                .getById(entry.getEntryId());
        dataResourceEntry.setEntryName(entry.getTitle());
        dataResourceEntry.setEntryNotes(entry.getDescription());
        dataResourceEntry.setMaxValue(entry.getMaxValue());
        dataResourceEntry.setMinValue(entry.getMinValue());
        // this.dataResourceEntryDao.update(dataResourceEntry);
        // this.appService.saveApp(appRequest);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                dataResourceEntry, this);
        this.dataResourceEntryDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findDataResourceEntryById(java.lang.Long)
     */
    public DataResourceEntryDTO findDataResourceEntryById(Long id) {

        if (null == id) {
            throw new IllegalArgumentException();
        }

        DataResourceEntry entry = this.dataResourceEntryDao.getById(id);
        if (entry == null)
            return null;

        DataResource dataResource = this.dataResourceDao.getById(new Long(entry
                .getResourceId()));

        return new DataResourceEntryDTO(dataResource, entry);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findChildResourceByPatch(java.lang.String)
     */
    public List findChildResourceByPatch(String path) {

        if (null == path) {
            throw new IllegalArgumentException();
        }

        List result = this.resourceDao.findChildResourceByPatch(path);
        filterResources(result);
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#isExistByParent(com.huashu.boss.sm.entity.Resource)
     */
    public boolean isExistResource(ResourceDTO resource, ResourceDTO parent) {
        if (null == resource) {
            throw new IllegalArgumentException();
        }

        List resourceList = null;
        // 没有指定父节点时
        if (parent == null
                || parent.getResourceId() == null
                || parent.getResourceId().longValue() <= 0
                || resource.getResourceId().longValue() == resource
                        .getParentId().longValue()) {
            resourceList = this.resourceDao.findResourceByTitleInSameLevel(
                    resource.getTitle(), null);
        } else {
            resourceList = this.resourceDao.findResourceByTitleInSameLevel(
                    resource.getTitle(), parent.getResourceId());
        }

        // 不存在
        if (resourceList.size() <= 0)
            return false;

        for (Iterator iterator = resourceList.iterator(); iterator.hasNext();) {
            Resource object = (Resource) iterator.next();
            if (object.getResourceId() == resource.getResourceId().longValue()) {
                return false;
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#isExistMenuUrl(java.lang.String)
     */
    public boolean isExistMenuUrl(String url, Long resourceId) {
        if (null == url) {
            throw new IllegalArgumentException();
        }
        return resourceDao.isExistMenuUrl(url, resourceId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#isExistEntryByDataResource(java.lang.String,
     *      java.lang.Long)
     */
    public boolean isExistEntryByDataResource(Long entryId, String title,
            Long resourceId) {
        if (null == title || null == resourceId) {
            throw new IllegalArgumentException();
        }
        return this.dataResourceEntryDao.isExistEntryByDataResource(entryId,
                title, resourceId);
    }

    public boolean isEntryValueDuplicatedOfSingleDataResource(Long entryId,
            Long resourceId, String entryValue) {
        return this.dataResourceEntryDao
                .isEntryValueDuplicatedOfSingleDataResource(entryId,
                        resourceId, entryValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#getDataResource(java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public DataResourceDTO getDataResource(Long opId, String code) {
        DataResource dataResource = (DataResource) this.dataResourceDao
                .getEntityByIdentityAttribute(DataResource.class,
                        DataResource.PROP_PRIV_CODE, code);

        List entries = this.dataResourceEntryDao.findDataResourceEntriesOfOp(
                opId, dataResource.getResourceId());
        filterDataResourceEntry(entries);

        DataResourceDTO result = new DataResourceDTO();
        result.setDataResource(dataResource);
        result.setEntries((DataResourceEntry[]) entries
                .toArray(new DataResourceEntry[entries.size()]));

        return result;
    }

    @SuppressWarnings("unchecked")
	public DataResourceDTO getDataResource(Long opId, String code, OrganizationDTO orgDto) {
        DataResource dataResource = (DataResource) this.dataResourceDao
                .getEntityByIdentityAttribute(DataResource.class,
                        DataResource.PROP_PRIV_CODE, code);
        List entries = this.dataResourceEntryDao.findDataResourceEntriesOfOp(
                opId, dataResource.getResourceId(), orgDto.getLocationIds());
        filterDataResourceEntry(entries);

        DataResourceDTO result = new DataResourceDTO();
        result.setDataResource(dataResource);
        result.setEntries((DataResourceEntry[]) entries
                .toArray(new DataResourceEntry[entries.size()]));

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(ResourceDTO.class)) {
            Resource res = this.resourceDao.getById(id);
            if (res == null)
                return null;
            Resource parent = this.resourceDao.getById(new Long(res
                    .getParentId()));
            return new ResourceDTO(parent, res);
        }

        if (typeClass.equals(DataResourceDTO.class)) {
            DataResource res = this.dataResourceDao.getById(id);
            return new DataResourceDTO(res);
        }

        if (typeClass.equals(DataResourceEntryDTO.class)) {
            DataResourceEntry entry = this.dataResourceEntryDao.getById(id);

            if (entry == null)
                return null;

            DataResource resource = this.dataResourceDao.getById(new Long(entry
                    .getResourceId()));
            return new DataResourceEntryDTO(resource, entry);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = null;
        if (typeClass.equals(ResourceDTO.class)) {
            result = this.dataResourceDao.loadByIds(Resource.class, ids);
            filterResources(result);
            return EntityDTOConverter.converResource2DTO(result);
        }

        if (typeClass.equals(DataResourceDTO.class)) {
            result = this.resourceDao.loadByIds(DataResource.class, ids);
            filterDataResources(result);
            return EntityDTOConverter.converDataResource2DTO(result);
        }

        if (typeClass.equals(DataResourceEntryDTO.class)) {
            result = this.dataResourceEntryDao.loadByIds(
                    DataResourceEntry.class, ids);
            filterDataResourceEntry(result);
            return EntityDTOConverter.converDataResourceEntry2DTO(result);
        }

        return null;
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
        List result = null;
        if (typeClass.equals(Resource.class)) {
            result = this.resourceDao.query(hql, params, page);
            Long[] parentIds = new Long[result.size()];
            filterResources(result);
            for (int i = 0; i < result.size(); i++) {
                Resource res = (Resource) result.get(i);
                parentIds[i] = new Long(res.getParentId());
            }

            List parentResources = this.resourceDao.loadByIds(Resource.class,
                    parentIds);

            return EntityDTOConverter.converResource2DTO(parentResources,
                    result);
        }

        if (typeClass.equals(DataResource.class)) {
            result = this.dataResourceDao.query(hql, params, page);
            filterDataResources(result);
            return EntityDTOConverter.converDataResource2DTO(result);
        }

        if (typeClass.equals(DataResourceEntry.class)) {
            result = this.dataResourceEntryDao.query(hql, params, page);
            filterDataResourceEntry(result);
            long[] resIds = ArrayUtils.EMPTY_LONG_ARRAY;
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                DataResourceEntry element = (DataResourceEntry) iter.next();
                long resourceId = element.getResourceId();
                if (!ArrayUtils.contains(resIds, resourceId)) {
                    resIds = ArrayUtils.add(resIds, resourceId);
                }
            }
            List resList = this.dataResourceDao.loadByIds(DataResource.class,
                    ArrayUtils.toObject(resIds));
            List entryDTO = EntityDTOConverter
                    .converDataResourceEntry2DTO(result);
            for (Iterator iter = entryDTO.iterator(); iter.hasNext();) {
                DataResourceEntryDTO element = (DataResourceEntryDTO) iter
                        .next();
                for (Iterator iterator = resList.iterator(); iterator.hasNext();) {
                    DataResource res = (DataResource) iterator.next();
                    if (element.getResourceId().longValue() == res
                            .getResourceId()) {
                        element.setDataResource(res);
                        break;
                    }
                }
            }
            return entryDTO;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ResourceManager#findEntryByDataResourceId(java.lang.Long)
     */
    public List findEntryByDataResourceId(Long resourceId) {
        List result = this.dataResourceEntryDao
                .findEntryByDataResourceId(resourceId);
        // filterDataResourceEntry(result);
        return EntityDTOConverter.converDataResourceEntry2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntityObject(Class clazz, Serializable key) {
        return this.dataResourceDao.getObjectById(clazz, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object obj) {
        this.dataResourceEntryDao.saveOrUpdate(obj);

    }

    /**
     * 过滤过期(删除)的Resource信息
     * 
     * @param resources
     */
    private void filterResources(List resources) {
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            Resource element = (Resource) iter.next();
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
     * 过滤过期(删除)的DataResource信息
     * 
     * @param dataResources
     */
    private void filterDataResources(List dataResources) {
        for (Iterator iter = dataResources.iterator(); iter.hasNext();) {
            DataResource element = (DataResource) iter.next();
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
     * 过滤过期(删除)的DataResourceEntry信息
     * 
     * @param entries
     */
    private void filterDataResourceEntry(List entries) {
        for (Iterator iter = entries.iterator(); iter.hasNext();) {
            DataResourceEntry element = (DataResourceEntry) iter.next();
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
     * 过滤过期(删除)的DataResourceEntryDTO信息
     * 
     * @param entries
     */
    private void filterDataResourceEntryDTO(List entries) {
        for (Iterator iter = entries.iterator(); iter.hasNext();) {
            DataResourceEntryDTO dto = (DataResourceEntryDTO) iter.next();
            DataResourceEntry element = dto.getDataResourceEntry();

            /*
             * if (element.getExpireDate() != null &&
             * element.getExpireDate().equals(element.getUpdateDate())) {
             * iter.remove(); }
             */
            if (element.getExpireDate() != null)
                iter.remove();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findNodeChildren(java.lang.Long,
     *      java.lang.String)
     */
    public List findNodeChildren(Long nodeId, String type) {
        List result = new ArrayList();
        if (nodeId.longValue() == XmlTreeNode.MOCK_ROOT_NODE_ID) {
            result = this.resourceDao.findRoots();
        } else {
            result = this.resourceDao.findChildrenMenuResourceById(nodeId);
        }
        filterResources(result);
        return EntityDTOConverter.converResource2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findRootNode()
     */
    public XmlTreeNode findRootNode() {
        // 新建一个虚的根节点
        XmlTreeNode root = new XmlTreeNode();
        root.setNodeId(new Long(XmlTreeNode.MOCK_ROOT_NODE_ID));
        root.setNodeName("功能权限");
        return root;
    }

}
