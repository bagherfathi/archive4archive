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
 * SSO���ݱ��������.
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
            // ��ѯ����Ա
            OperatorDTO op = this.opManager.findOperatorBySSOCode(operatorVo
                    .getSsoCode());

            // ��ϵͳ�в����ڣ�����Ӳ���Ա
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

            // ����ϵͳ�в����ڣ������֯��Ϣ
            if (org == null) {
                this.createOrg(orgVo);
                // ������֯��Ϣ
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

            // ���ؽ�ֹ��֯��Ϣ
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

            // ����ɾ����֯��Ϣ
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
            // ��ѯ����Ա
            OperatorDTO op = this.opManager.findOperatorBySSOCode(operatorVo
                    .getSsoCode());

            // ��ϵͳ�в����ڣ�����Ӳ���Ա
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

            // ����ϵͳ�в����ڣ������֯��Ϣ
            if (org == null) {
                this.createOrg(orgVo);
                // ������֯��Ϣ
            } else {
                this.updateOrg(org, orgVo);
            }
        } catch (Exception ex) {
            logger.error("Fire update organization event error", ex);
        }
    }

    *//**
     * ���²���Ա��Ϣ
     * 
     * @param op
     * @param operatorVo
     *//*
    private void updateOperator(OperatorDTO op, OperatorVO operatorVo)
            throws Exception {
        // ���²���Ա
        OrganizationDTO org = new OrganizationDTO(op.getOrg());
        EntityConverter.convertVO2Operator(operatorVo, op);

        // ������֯���
        if (!org.getSsoCode().equals(operatorVo.getOrgSSOCode())) {
            org = this.orgManager.findOrgBySSOCode(operatorVo.getOrgSSOCode());

            // ��ָ����֯�������ʧ��
            if (org == null) {
                logger
                        .error("Update operator failed.Not found organizaiton for operator,operator sso code = "
                                + operatorVo.getSsoCode()
                                + ",org sso code = "
                                + operatorVo.getOrgSSOCode());
                return;
            }
        }

        // ���±�ϵͳ�в���Ա��Ϣ
        this.opManager.saveOrUpdateOperator(op, org);
    }

    *//**
     * ��������Ա
     * 
     * @param operatorVo
     *//*
    private OperatorDTO createOperator(OperatorVO operatorVo) throws Exception {
        OperatorDTO op = EntityConverter.convertVO2Operator(operatorVo);
        OrganizationDTO org = this.orgManager.findOrgBySSOCode(op
                .getOrgSSOCode());

        // ����Ա��֯�����ڣ����ʧ��
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
     * ������֯��Ϣ
     * 
     * @param org
     * @param orgVo
     *//*
    private void updateOrg(OrganizationDTO org, OrganizationVO orgVo)
            throws Exception {
        OrganizationDTO parent = new OrganizationDTO(org.getParent());
        EntityConverter.convertVO2Organization(orgVo, org);

        // �ϼ���֯���
        if (!parent.getSsoCode().equals(org.getParentSSOCode())) {
            parent = this.orgManager.findOrgBySSOCode(orgVo
                    .getParentOrgSSOCode());

            // ����֯�����ڣ���֯���ʧ��
            if (parent == null) {
                logger
                        .error("Update organizaiton failed.Not found parent organizaiton,sso code = "
                                + orgVo.getSsoCode()
                                + ",parent organizaiton sso code = "
                                + orgVo.getParentOrgSSOCode());
                return;
            }
        }

        // ���±���ϵͳ��֯��Ϣ
        this.orgManager.update(org, parent);
    }

    *//**
     * ������֯��Ϣ
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
            // ��ѯ����Ա��ɫ
            RoleDTO adminRole = this.roleManager.findRoleById(new Long(
                    ADMIN_ROLE_ID));

            if (adminRole == null) {
                logger.error("Sync administrator failed,not fount admin role.");
                return;
            }

            // ��ѯ����֯��Ϊ��Ȩ��֯
            OrganizationDTO org = this.orgManager.findRootOrg();
            if (org == null) {
                logger
                        .error("Sync administrator failed,not fount root organizaiton.");
                return;
            }

            OperatorDTO operator = this.opManager
                    .findOperatorBySSOCode(operatorVo.getSsoCode());

            // �����Ĳ���Ա
            if (operator == null) {
                operator = this.createOperator(operatorVo);
                // ��������Աʧ��
                if (operator == null)
                    return;
            }

            List result = this.opManager.findOperatorRoleForOrgsOfOperator(
                    operator.getOperatorId(), new Long(
                            RoleDTO.ROLE_TYPE_FUNCTION),false);
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                RelOperRoleDTO element = (RelOperRoleDTO) iterator.next();
                // ��ӵ�й���ԱȨ��
                if (ADMIN_ROLE_ID == element.getRole().getRoleId()) {
                    return;
                }
            }

            // ϵͳ�������Ա����Ա��ɫ
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
     * SSOϵͳ���ò���Ա����Ӧ����Ϣ����
     * 
     * @see com.ft.sso.api.app.DataEventHandler#onRemoveAppAccess(java.lang.String)
     *//*
    public void onRemoveAppAccess(String code) {
        try {
            // ��ѯ����Ա
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