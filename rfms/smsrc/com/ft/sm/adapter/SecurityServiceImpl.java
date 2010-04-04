package com.ft.sm.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RegionManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.busi.sm.service.SecurityService;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;
import com.ft.utils.encode.Md5PasswordEncoder;

/**
 * 操作员，组织，权限管理的外部接口实现
 * 
 * @spring.bean id="securityService"
 */
public class SecurityServiceImpl implements SecurityService {
    private static final Log logger = LogFactory
            .getLog(SecurityServiceImpl.class);

    private OrgManager orgManager;

    private OperatorManager operatorManager;

    private RegionManager regionManager;

    private ResourceManager resourceManager;
    
    public static final String ERROR_CODE_SYNC_OP = "sm.error.synchronize.op";

    //private SSOClientFactory ssoClientFactory;

    //private DataAccessClient dataAccessClient;
    // 同步开关
    @SuppressWarnings("unused")
	private boolean synOn;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#findOperatorById(java.lang.Long)
     */
    public OperatorDTO findOperatorById(Long operatorId) {
        try {
            return this.operatorManager.findOperatorById(operatorId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see com.ft.busi.sm.service.SecurityService#findRootOrg()
     */
    public OrganizationDTO findRootOrg() {
        try {
            return this.orgManager.findRootOrg();
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#findOperatorByLoginName(java.lang.String)
     */
    public OperatorDTO findOperatorByLoginName(String loginName) {
        try {
            return this.operatorManager.findOperatorByLoginName(loginName);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#findOpreatorByNameAndLoginName(java.lang.String,
     *      java.lang.String)
     */
    public OperatorDTO[] findOperators(Long orgId, String name, String loginName) {
        try {
            return this.operatorManager.findOperators(orgId, name, loginName);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#createOpreator(com.ft.sm.entity.OperatorDTO,
     *      java.lang.Long, java.lang.Long[], java.lang.Long[])
     */
    public OperatorDTO createOpreator(OperatorDTO op, Long orgId,
            Long[] roleIds, Long[] groupIds) {
        OperatorDTO opDTO = null;
        try {
            //OrganizationDTO org = this.orgManager.findOrgById(orgId);
            //this.addOperator2SSO(opDTO, org);
            opDTO = this.operatorManager.createOpreator(op, orgId, roleIds,
                    groupIds);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        return opDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getRegionById(java.lang.Long)
     */
    public RegionDTO getRegionById(Long regionId) {
        try {
            return this.regionManager.findRegionById(regionId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#findRegionByOrgId(java.lang.Long,
     *      boolean)
     */
    @SuppressWarnings("unchecked")
	public RegionDTO[] findRegionByOrgId(Long orgId, boolean allChild) {
        List result;
        try {
            // 查询指定组织可访问数据区域ID
            List orgIds = this.orgManager.findAccessOrgIdsOfOrg(orgId,
                    OrganizationDTO.ORG_TYPE_REGION, true);
            if (allChild)
                result = this.regionManager.findRegionsOfOrgs((Long[]) orgIds
                        .toArray(new Long[0]));
            else
                result = this.regionManager.findRefionsOfOrg(orgId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

        return (RegionDTO[]) result.toArray(new RegionDTO[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#findRegionByParentId(java.lang.Long,
     *      boolean)
     */
    @SuppressWarnings("unchecked")
	public RegionDTO[] findRegionByParentId(Long regionId, boolean allChild) {
        List result;
        try {
            result = regionManager.findRegionByParentId(regionId, allChild);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        return (RegionDTO[]) result.toArray(new RegionDTO[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getRegionOfOrg(java.lang.Long,
     *      long, java.lang.String)
     */
    public RegionDTO getRegionOfOrg(Long orgId, long regionType,
            String regionName) {
        try {
            List result = this.regionManager.findAccessRegionByOrgId(orgId);
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                RegionDTO regionDto = (RegionDTO) iterator.next();
                if (regionDto.getRegionType() == regionType
                        && regionDto.getRegionName().equals(regionName)) {
                    return regionDto;
                }
            }

        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getRegionLocation(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public RegionDTO[] getRegionLocation(Long regionId) {
        List result;
        try {
            result = this.regionManager.findRegionLocation(regionId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        return (RegionDTO[]) result.toArray(new RegionDTO[0]);
    }

    /*
     * (non-Javadoc)
     * @see com.ft.busi.sm.service.SecurityService#getDataResource(java.lang.Long, java.lang.String, java.lang.Long)
     */
    public DataResourceDTO getDataResource(Long opId, String code, OrganizationDTO orgDto) {
        try {
            return resourceManager.getDataResource(opId, code, orgDto);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getAccessOrgIdsOfOrg(java.lang.Long,
     *      java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public OrganizationDTO[] getAccessOrgsOfOrg(Long orgId, Long orgType,
            boolean includeAll) {
        if (orgId == null || orgType == null)
            throw new java.lang.IllegalArgumentException();

        long lorgType = orgType.longValue();

        // 目前只支持获取分公司、数据区域和营业厅
        if (lorgType != OrganizationDTO.ORG_TYPE_COMPANY
                && lorgType != OrganizationDTO.ORG_TYPE_REGION
                && lorgType != OrganizationDTO.ORG_TYPE_BUSIHALL)
            throw new java.lang.IllegalArgumentException();

        try {
            List result = orgManager.findAccessOrgsOfOrg(orgId, lorgType,
                    includeAll);

            return (OrganizationDTO[]) result.toArray(new OrganizationDTO[0]);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getAccessOrgsOfOrg(java.lang.Long,
     *      java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public Long[] getAccessOrgIdsOfOrg(Long orgId, Long orgType,
            boolean includeAll) {
        if (orgId == null || orgType == null)
            throw new java.lang.IllegalArgumentException();

        long lorgType = orgType.longValue();

        // 目前只支持获取分公司、数据区域和营业厅
        if (lorgType != OrganizationDTO.ORG_TYPE_COMPANY
                && lorgType != OrganizationDTO.ORG_TYPE_REGION
                && lorgType != OrganizationDTO.ORG_TYPE_BUSIHALL)
            throw new java.lang.IllegalArgumentException();

        try {
            List result = orgManager.findAccessOrgIdsOfOrg(orgId, lorgType,
                    includeAll);

            return (Long[]) result.toArray(new Long[0]);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getCompanyIdOfOrg(java.lang.Long)
     */
    public Long getCompanyIdOfOrg(Long orgId) {
        if (orgId == null)
            throw new java.lang.IllegalArgumentException();

        try {
            return orgManager.findCompanyIdOfOrg(orgId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getCompanyOfOrg(java.lang.Long)
     */
    public OrganizationDTO getCompanyOfOrg(Long orgId) {
        if (orgId == null)
            throw new java.lang.IllegalArgumentException();

        try {
            return orgManager.findCompanyOfOrg(orgId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getOrgById(java.lang.Long)
     */
    public OrganizationDTO getOrgById(Long orgId) {
        if (orgId == null)
            throw new java.lang.IllegalArgumentException();
        try {
            return orgManager.findOrgById(orgId);
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getAllOrgs(long)
     */
    @SuppressWarnings("unchecked")
	public OrganizationDTO[] getAllOrgs(long orgType) {
        try {
            OrganizationDTO org = this.orgManager.findRootOrg();
            List result = this.orgManager.findAccessOrgsOfOrg(org.getOrgId(),
                    orgType, true);
            return (OrganizationDTO[]) result.toArray(new OrganizationDTO[0]);
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#getOperatorOfRoleInOrg(java.lang.Long,
     *      java.lang.Long)
     */
    public OperatorDTO[] getOperatorOfRoleInOrg(Long roleId, OrganizationDTO org) {
        try {
            List<OperatorDTO> ret = new ArrayList<OperatorDTO>();
            // 查询角色中的操作员
            List result = this.operatorManager.findOperatorOfRole(roleId);
            List childCompanyList = this.orgManager.findAccessOrgOfCompany(org,
                    -1, false);

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                OperatorDTO op = (OperatorDTO) iterator.next();
                boolean in = false;
                for (Iterator iterator2 = childCompanyList.iterator(); iterator2
                        .hasNext();) {
                    OrganizationDTO childOrg = (OrganizationDTO) iterator2
                            .next();
                    if (op.getOrgId().equals(childOrg.getOrgId())) {
                        in = true;
                        break;
                    }
                }

                if (in) {
                    ret.add(op);
                }
            }

            return (OperatorDTO[]) ret.toArray(new OperatorDTO[0]);

        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#createOperator(java.lang.String,
     *      java.lang.String, java.lang.String, long)
     */
    public long createOperator(String opName, String loginName,
            String password, long orgId) {
        OperatorDTO op = new OperatorDTO();
        op.getContact().setName(opName);
        op.setLoginName(loginName);
        op.setPassword(password);
        op.setEmail("email empty!!!");

        try {
            OrganizationDTO org = this.orgManager.findOrgById(new Long(orgId));
            if (org == null) {
                logger.error("create operator error,not found organization,id="
                        + orgId);
                return 0;
            } else {
                //this.addOperator2SSO(op, org);
                Long opId = this.operatorManager.saveOrUpdateOperator(op, org);
                return opId.longValue();
            }
        } catch (Exception ex) {
            logger.error("create operator error", ex);
            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#deleteOperator(long)
     */
    public boolean deleteOperator(long opId) {
        try {
            OperatorDTO op = this.operatorManager.findOperatorById(new Long(
                    opId));
            if (op == null) {
                return true;
            } else {
                this.operatorManager.disableOperator(new OperatorDTO[] { op });
                return true;
            }
        } catch (Exception ex) {
            logger.error("delete operator error", ex);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.SecurityService#updateOperator(long,
     *      java.lang.String, java.lang.String, java.lang.String, long)
     */
    public boolean updateOperator(long opId, String opName, String loginName,
            String password, long orgId) {
        try {
            OperatorDTO op = this.operatorManager.findOperatorById(new Long(
                    opId));
            if (op == null) {
                logger.error("Update operator error,not found operator,id="
                        + opId);
                return false;
            } else {
                OrganizationDTO org = this.orgManager.findOrgById(new Long(
                        orgId));
                if (org == null) {
                    logger
                            .error("Update operator error,not found organization,id="
                                    + orgId);
                    return false;
                } else {
                    op.getContact().setName(opName);
                    op.setLoginName(loginName);
                    if(op.getEmail()==null || op.getEmail().trim()==""){
                        op.setEmail("email empty !!!");
                    }
                    //this.updateOperator2SSO(op, org);
                    op.setPassword(Md5PasswordEncoder.encode(password));
                    this.operatorManager.saveOrUpdateOperator(op, org);
                    return true;
                }
            }
        } catch (Exception ex) {
            logger.error("update operator error", ex);
            return false;
        }
    }
    
   /* private DataAccessClient getDataAccessClient() {
        if (this.dataAccessClient == null)
            this.dataAccessClient = this.ssoClientFactory
                    .createDataAccessClient();

        return this.dataAccessClient;
    }
    */
    /**
     * 添加到SSO系统中
     * 
     * @param operator
     * @return
     */
   /* private void addOperator2SSO(OperatorDTO operator, OrganizationDTO org) {
        if (!this.synOn)
            return;

        operator.setOrgSSOCode(org.getSsoCode());
        OperatorVO vo = EntityConverter.convertOperator2VO(operator);

        String ssoCode;
        try {
            vo.setPassword(Md5PasswordEncoder.encode(vo.getPassword())); 
            ssoCode = this.getDataAccessClient().addOperator(vo);
            operator.setSsoCode(ssoCode);
            logger.debug("add operator to sso success.");
        } catch (DataAccessException e) {
            logger.error("Synchronization error:add operator failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_OP, e);
        }
    }*/
    
  /*  private void updateOperator2SSO(OperatorDTO operator, OrganizationDTO org) {

        if (!this.synOn)
            return;

        operator.setOrgSSOCode(org.getSsoCode());
        OperatorVO vo = EntityConverter.convertOperator2VO(operator);

        try {
            this.getDataAccessClient().updateOperator(vo);
            logger.debug("update operator to sso success.");
        } catch (DataAccessException e) {
            logger.error("Synchronization error:update operator failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_OP, e);
        }
    }*/

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     *            The orgManager to set.
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref="regionManager"
     * @param regionManager
     *            The regionManager to set.
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * @spring.property ref="resourceManager"
     * @param resourceManager
     *            The resourceManager to set.
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref="operatorManager"
     * @param operatorManager
     *            The operatorManager to set.
     */
    public void setOperatorManager(OperatorManager operatorManager) {
        this.operatorManager = operatorManager;
    }
    
    /**
     * @spring.property ref="ssoClientFactory"
     * @param ssoClientFactory
     *                The ssoClientFactory to set.
     */
    /*public void setSsoClientFactory(SSOClientFactory ssoClientFactory) {
        this.ssoClientFactory = ssoClientFactory;
    }*/

    /**
     * @spring.property value="${data.syn.enable}"
     * @param synOn
     *                The synOn to set.
     */
    public void setSynOn(boolean synOn) {
        this.synOn = synOn;
    }
    
    
}
