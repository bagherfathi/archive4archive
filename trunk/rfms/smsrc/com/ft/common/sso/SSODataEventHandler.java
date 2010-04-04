/*
package com.ft.common.sso;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.sm.common.EntityConverter;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RelOperRoleDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sso.api.app.DataEventHandler;
import com.ft.sso.api.dto.OperatorVO;
import com.ft.sso.api.dto.OrganizationVO;

*//**
 * SSO数据变更监听器.
 * @version 1.0
 * 
 * @spring.bean id="ssoDataEventHandler"
 *//*
public class SSODataEventHandler implements DataEventHandler {
    private static final Log logger = LogFactory
            .getLog(SSODataEventHandler.class);

    private static final long ADMIN_ROLE_ID = 1;

    private OperatorManager opManager;

    private OrgManager orgManager;

    private RoleManager roleManager;

    *//**
     * @spring.property ref="operatorManager"
     * @param opManager
     *                The opManager to set.
     *//*
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }

    *//**
     * @spring.property ref="orgManager"
     * @param orgManager
     *                The orgManager to set.
     *//*
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    *//**
     * @spring.property ref="roleManager"
     * @param roleManager
     *                The orgManager to set.
     *//*
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onAddOperator(com.ft.sso.api.dto.OperatorVO)
     
    public void onAddOperator(OperatorVO operatorVo) {
        logger.debug("Fire add operator event,sso code is "
                + operatorVo.getSsoCode());

        try {
            // 查询操作员
            OperatorDTO op = this.opManager.findOperatorBySSOCode(operatorVo
                    .getSsoCode());

            // 本系统中不存在，则添加操作员
            if (op == null) {
                this.createOperator(operatorVo);
            } else {
                if (op.getSsoAccessed() == OperatorDTO.STATUS_DISABLE) {
                    op.setSsoAccessed(OperatorDTO.STATUS_NORMAL);
                }

                this.updateOperator(op, operatorVo);
            }
        } catch (Exception ex) {
            logger.error("Fire add operator event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onAddOrg(com.ft.sso.api.dto.OrganizationVO)
     
    public void onAddOrg(OrganizationVO orgVo) {
        logger.debug("Fire add organization event,sso code is "
                + orgVo.getSsoCode() + ",parent sso code is "
                + orgVo.getParentOrgSSOCode());

        try {
            OrganizationDTO org = this.orgManager.findOrgBySSOCode(orgVo
                    .getSsoCode());

            // 本地系统中不存在，添加组织信息
            if (org == null) {
                this.createOrg(orgVo);
                // 更新组织信息
            } else {
                this.updateOrg(org, orgVo);
            }

        } catch (Exception ex) {
            logger.error("Fire add organization event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onDisableOperator(java.lang.String)
     
    public void onDisableOperator(String ssoCode) {
        logger.debug("Fire disable operator event,sso code is " + ssoCode);

        try {
            OperatorDTO op = this.opManager.findOperatorBySSOCode(ssoCode);
            if (op != null) {
                op.setSsoStatus(OperatorDTO.STATUS_DISABLE);
                this.opManager.updateOperator(op);
                // this.opManager.disableOperator(new Operator[] { op });
            } else {
                logger.error("Not found operator,sso code is " + ssoCode);
            }
        } catch (Exception ex) {
            logger.error("Fire disable operator event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onDisableOrg(java.lang.String)
     
    public void onDisableOrg(String ssoCode) {
        logger.debug("Fire disable organization event,sso code is " + ssoCode);

        try {
            OrganizationDTO org = this.orgManager.findOrgBySSOCode(ssoCode);

            // 本地禁止组织信息
            if (org != null) {
                this.orgManager.disableOrg(new OrganizationDTO[] { org });
            } else {
                logger.error("Not found organizaiton,sso code is " + ssoCode);
            }
        } catch (Exception ex) {
            logger.error("Fire disable organization event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onEnableOperator(java.lang.String)
     
    public void onEnableOperator(String ssoCode) {
        logger.debug("Fire enable operator event,sso code is " + ssoCode);

        try {
            OperatorDTO op = this.opManager.findOperatorBySSOCode(ssoCode);
            if (op != null) {
                op.setSsoStatus(OperatorDTO.STATUS_NORMAL);
                this.opManager.updateOperator(op);
                // this.opManager.enableOperator(op);
            } else {
                logger.error("Not found operator,sso code is " + ssoCode);
            }
        } catch (Exception ex) {
            logger.error("Fire enable operator event error", ex);
        }

    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onEnableOrg(java.lang.String)
     
    public void onEnableOrg(String ssoCode) {
        logger.debug("Fire enable organization event,sso code is " + ssoCode);

        try {
            OrganizationDTO org = this.orgManager.findOrgBySSOCode(ssoCode);

            // 本地删除组织信息
            if (org != null) {
                this.orgManager.enableOrg(org);
            } else {
                logger.error("Not found organizaiton,sso code is " + ssoCode);
            }

        } catch (Exception ex) {
            logger.error("Fire enable organization event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onUpdateOperator(com.ft.sso.api.dto.OperatorVO)
     
    public void onUpdateOperator(OperatorVO operatorVo) {
        logger.debug("Fire update operator event,sso code is "
                + operatorVo.getSsoCode());

        try {
            // 查询操作员
            OperatorDTO op = this.opManager.findOperatorBySSOCode(operatorVo
                    .getSsoCode());

            // 本系统中不存在，则添加操作员
            if (op == null) {
                this.createOperator(operatorVo);
            } else {
                this.updateOperator(op, operatorVo);
            }
        } catch (Exception ex) {
            logger.error("Fire update operator event error", ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onUpdateOrg(com.ft.sso.api.dto.OrganizationVO)
     
    public void onUpdateOrg(OrganizationVO orgVo) {
        logger.debug("Fire update organization event,sso code is "
                + orgVo.getSsoCode() + ",parent sso code is "
                + orgVo.getParentOrgSSOCode());

        try {
            OrganizationDTO org = this.orgManager.findOrgBySSOCode(orgVo
                    .getSsoCode());

            // 本地系统中不存在，添加组织信息
            if (org == null) {
                this.createOrg(orgVo);
                // 更新组织信息
            } else {
                this.updateOrg(org, orgVo);
            }
        } catch (Exception ex) {
            logger.error("Fire update organization event error", ex);
        }
    }

    *//**
     * 更新操作员信息
     * 
     * @param op
     * @param operatorVo
     *//*
    private void updateOperator(OperatorDTO op, OperatorVO operatorVo)
            throws Exception {
        // 更新操作员
        OrganizationDTO org = new OrganizationDTO(op.getOrg());
        EntityConverter.convertVO2Operator(operatorVo, op);

        // 所属组织变更
        if (!org.getSsoCode().equals(operatorVo.getOrgSSOCode())) {
            org = this.orgManager.findOrgBySSOCode(operatorVo.getOrgSSOCode());

            // 无指定组织，则更新失败
            if (org == null) {
                logger
                        .error("Update operator failed.Not found organizaiton for operator,operator sso code = "
                                + operatorVo.getSsoCode()
                                + ",org sso code = "
                                + operatorVo.getOrgSSOCode());
                return;
            }
        }

        // 更新本系统中操作员信息
        this.opManager.saveOrUpdateOperator(op, org);
    }

    *//**
     * 创建操作员
     * 
     * @param operatorVo
     *//*
    private OperatorDTO createOperator(OperatorVO operatorVo) throws Exception {
        OperatorDTO op = EntityConverter.convertVO2Operator(operatorVo);
        OrganizationDTO org = this.orgManager.findOrgBySSOCode(op
                .getOrgSSOCode());

        // 操作员组织不存在，添加失败
        if (org == null) {
            logger
                    .error("Add Operator failed.Not found organizaiton for operator,operator sso code = "
                            + operatorVo.getSsoCode()
                            + ",org sso code = "
                            + operatorVo.getOrgSSOCode());
            return null;
        }

        this.opManager.saveOrUpdateOperator(op, org);

        return op;
    }

    *//**
     * 更新组织信息
     * 
     * @param org
     * @param orgVo
     *//*
    private void updateOrg(OrganizationDTO org, OrganizationVO orgVo)
            throws Exception {
        OrganizationDTO parent = new OrganizationDTO(org.getParent());
        EntityConverter.convertVO2Organization(orgVo, org);

        // 上级组织变更
        if (!parent.getSsoCode().equals(org.getParentSSOCode())) {
            parent = this.orgManager.findOrgBySSOCode(orgVo
                    .getParentOrgSSOCode());

            // 父组织不存在，组织变更失败
            if (parent == null) {
                logger
                        .error("Update organizaiton failed.Not found parent organizaiton,sso code = "
                                + orgVo.getSsoCode()
                                + ",parent organizaiton sso code = "
                                + orgVo.getParentOrgSSOCode());
                return;
            }
        }

        // 更新本地系统组织信息
        this.orgManager.update(org, parent);
    }

    *//**
     * 创建组织信息
     * 
     * @param orgVo
     *//*
    private void createOrg(OrganizationVO orgVo) throws Exception {
        OrganizationDTO org = EntityConverter.convertVO2Organization(orgVo);

        OrganizationDTO parent = this.orgManager.findOrgBySSOCode(orgVo
                .getParentOrgSSOCode());
        if (parent == null) {
            logger
                    .error("Add organizaiton failed.Not found parent organizaiton,sso code = "
                            + orgVo.getSsoCode()
                            + ",parent sso code="
                            + orgVo.getParentOrgSSOCode());
            return;
        }

        this.orgManager.save(org, parent);
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onAddAdmin(com.ft.sso.api.dto.OperatorVO)
     
    public void onAddAdmin(OperatorVO operatorVo) {
        try {
            // 查询管理员角色
            RoleDTO adminRole = this.roleManager.findRoleById(new Long(
                    ADMIN_ROLE_ID));

            if (adminRole == null) {
                logger.error("Sync administrator failed,not fount admin role.");
                return;
            }

            // 查询根组织作为赋权组织
            OrganizationDTO org = this.orgManager.findRootOrg();
            if (org == null) {
                logger
                        .error("Sync administrator failed,not fount root organizaiton.");
                return;
            }

            OperatorDTO operator = this.opManager
                    .findOperatorBySSOCode(operatorVo.getSsoCode());

            // 新增的操作员
            if (operator == null) {
                operator = this.createOperator(operatorVo);
                // 创建操作员失败
                if (operator == null)
                    return;
            }

            List result = this.opManager.findOperatorRoleForOrgsOfOperator(
                    operator.getOperatorId(), new Long(
                            RoleDTO.ROLE_TYPE_FUNCTION),false);
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                RelOperRoleDTO element = (RelOperRoleDTO) iterator.next();
                // 已拥有管理员权限
                if (ADMIN_ROLE_ID == element.getRole().getRoleId()) {
                    return;
                }
            }

            // 系统赋予管理员管理员角色
            this.opManager.saveOperatorRoleForOrg(operator, operator,
                    new RoleDTO[] { adminRole }, org,
                    RoleDTO.ROLE_TYPE_FUNCTION);
        } catch (Exception ex) {
            logger
                    .error(
                            "Sync administrator failed,not fount root organizaiton",
                            ex);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onRemoveAdmin(com.ft.sso.api.dto.OperatorVO)
     
    public void onRemoveAdmin(OperatorVO arg0) {
        // TODO Auto-generated method stub

    }

    *//**
     * SSO系统禁用操作员访问应用消息处理
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onRemoveAppAccess(java.lang.String)
     *//*
    public void onRemoveAppAccess(String code) {
        try {
            // 查询操作员
            OperatorDTO op = this.opManager.findOperatorBySSOCode(code);
            if (op != null) {
                op.setSsoAccessed(OperatorDTO.STATUS_DISABLE);
                this.opManager.updateOperator(op);
            }
        } catch (Exception ex) {
            logger.error("Fire remove app error", ex);
        }
    }
}
*/