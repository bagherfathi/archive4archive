/*
package com.ft.busi.sm.impl.sso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.impl.dao.ConfigParamDao;
import com.ft.busi.sm.impl.dao.OperatorDao;
import com.ft.busi.sm.impl.dao.OrganizationDao;
import com.ft.busi.sm.impl.dao.RelOperOrgDao;
import com.ft.busi.sm.impl.dao.RelOperRoleDao;
import com.ft.busi.sm.impl.dao.RoleDao;
import com.ft.sm.common.EntityConverter;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.ConfigParam;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperOrg;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.Role;
import com.ft.sso.agent.SSOClientFactory;
import com.ft.sso.agent.client.DataAccessClient;
import com.ft.sso.api.dto.OperatorVO;
import com.ft.sso.api.dto.OrganizationVO;

*//**
 * 实现和SSO系统中组织、操作员数据同步.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.bean id="syncImpl" init-method="init"
 *//*
public class SyncImpl {
    private static Log logger = LogFactory.getLog(SyncImpl.class);

    private static final long ADMIN_ROLE_ID = 1;

    private static SimpleDateFormat format = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final String PARAM_CODE_LAST_SYNC_ORG_TIME = "last_sync_org_time"; // 最后更新组织时间参数代码

    private static final String PARAM_CODE_LAST_SYNC_OP_TIME = "last_sync_op_time"; // 最后更新操作员时间参数代码

    public static final String ERROR_CODE_SYNC_ORG = "sm.error.synchronize.org";

    public static final String ERROR_CODE_SYNC_OP = "sm.error.synchronize.op";

    private SSOClientFactory ssoClientFactory;

    private ConfigParamDao configParmaDao;

    private OperatorDao operatorDao;

    private OrganizationDao orgDao;

    private RoleDao roleDao;

    private RelOperRoleDao relOperRoleDao;

    private RelOperOrgDao relOperOrgDao;

    // 同步开关
    private boolean synOn;

    *//**
     * 启动时从SSO系统中获取更新数据进行更新
     * 
     *//*
    public void init() {
        if (!synOn)
            return;

        Thread syncThread = new Thread() {
            public void run() {
                // 同步组织和操作员信息
                try {
                    syncOrgs();
                    syncOperators();
                    syncAdmin();
                } catch (Exception e) {
                    logger.error("Sync data from sso occur an error:"
                            + e.getMessage());
                }
            }
        };

        syncThread.start();
    }

    *//**
     * 同步组织机构信息。
     * 
     * @return
     *//*
    public void syncOrgs() throws Exception {
        Date lastSyncTime = this.getLastSyncTime(PARAM_CODE_LAST_SYNC_ORG_TIME);
        Date newLastUpdateTime = new Date();

        OrganizationVO[] orgVoArr = new OrganizationVO[0];

        DataAccessClient dataAccessClient = ssoClientFactory
                .createDataAccessClient();
        if (lastSyncTime == null) {
            // 获取SSO系统中全部组织机构
            orgVoArr = dataAccessClient.listOrg();
        } else {
            // 根据最后更新时间查询SSO系统中组织机构信息
            orgVoArr = dataAccessClient.getOrgByModifyDate(lastSyncTime);
        }

        Map orgs = this.getAllOrgs();
        for (int i = 0; i < orgVoArr.length; i++) {
            OrganizationVO orgVo = orgVoArr[i];
            // 新增组织机构
            if (orgs.get(orgVo.getSsoCode()) == null) {
                this.addOrg(orgVo, orgs);
                // 更新组织机构
            } else {
                this.updateOrg(orgVo, orgs);
            }
        }

        // 更新最后更新时间
        updateLastSyncTime(PARAM_CODE_LAST_SYNC_ORG_TIME, newLastUpdateTime);
    }

    *//**
     * 同步操作员信息。
     * 
     * @return
     *//*
    public void syncOperators() throws Exception {
        Date lastSyncTime = this.getLastSyncTime(PARAM_CODE_LAST_SYNC_OP_TIME);
        Date newLastUpdateTime = new Date();

        OperatorVO[] opVoArr = new OperatorVO[0];

        DataAccessClient dataAccessClient = ssoClientFactory
                .createDataAccessClient();
        if (lastSyncTime == null) {
            // 获取SSO系统中全部组织机构
            opVoArr = dataAccessClient.listOperator();
        } else {
            // 根据最后更新时间查询SSO系统中组织机构信息
            opVoArr = dataAccessClient.listOperatorByModifyDate(lastSyncTime);
        }

        Map orgs = this.getAllOrgs();
        Map operators = this.getOperators();
        for (int i = 0; i < opVoArr.length; i++) {
            OperatorVO opVo = opVoArr[i];
            // 新增操作员
            if (operators.get(opVo.getSsoCode()) == null) {
                this.addOperator(opVo, orgs, operators);
                // 更新操作员
            } else {
                this.updateOperator(opVo, orgs, operators);
            }
        }

        // 更新最后更新时间
        updateLastSyncTime(PARAM_CODE_LAST_SYNC_OP_TIME, newLastUpdateTime);
    }

    *//**
     * 同步系统管理员信息。
     *//*
    public void syncAdmin() throws Exception {
        // 查询管理员角色
        Role adminRole = this.roleDao.getById(new Long(ADMIN_ROLE_ID));
        if (adminRole == null) {
            throw new Exception(
                    "Sync administrator failed,not fount admin role.");
        }

        // 查询根组织作为赋权组织
        Organization org = this.orgDao.findRootOrg();
        if (org == null) {
            throw new Exception(
                    "Sync administrator failed,not fount root organizaiton.");
        }

        // 同步管理员信息
        DataAccessClient dataAccessClient = ssoClientFactory
                .createDataAccessClient();

        OperatorVO[] adminVos = dataAccessClient.getAppAdmin();
        String[] ssoCodes = new String[adminVos.length];
        for (int i = 0; i < adminVos.length; i++) {
            OperatorVO opVO = adminVos[i];
            ssoCodes[i] = opVO.getSsoCode();
        }

        List operatorlist = this.operatorDao.findOperatorsBySSOCodes(ssoCodes);

        // 检验是否需要授权，对未授权的管理员进行授权
        for (Iterator iter = operatorlist.iterator(); iter.hasNext();) {
            Operator operator = (Operator) iter.next();
            // 设置操作员管理员角色
            addRelOperRole(adminRole, org, operator);
            // 设置操作员可访问组织
            addRelOperOrg(org, operator);
        }
    }

    *//**
     * 设置操作员可访问组织。
     * 
     * @param org
     * @param operator
     *//*
    private void addRelOperOrg(Organization org, Operator operator) {
        // 设置可访问组织
        RelOperOrg relOperOrg = this.relOperOrgDao.findRelOperOrg(new Long(
                operator.getOperatorId()), new Long(org.getOrgId()));
        if (relOperOrg == null) {
            RelOperOrg operOrg = new RelOperOrg();
            operOrg.setOperatorId(operator.getOperatorId());
            operOrg.setOperatorId(org.getOrgId());
            this.relOperOrgDao.save(operOrg);
        }
    }

    *//**
     * 设置操作员管理员角色。
     * 
     * @param adminRole
     * @param org
     * @param operator
     *//*
    private void addRelOperRole(Role adminRole, Organization org,
            Operator operator) {
        RelOperRole relOperRole = this.relOperRoleDao.findRelOperRole(new Long(
                operator.getOperatorId()), new Long(ADMIN_ROLE_ID), new Long(
                org.getOrgId()));
        // 系统赋予管理员管理员角色
        if (relOperRole == null) {
            RelOperRole roleForOrg = new RelOperRole();
            roleForOrg.setOperatorId(operator.getOperatorId());
            roleForOrg.setOrgId(org.getOrgId());
            roleForOrg.setRoleId(adminRole.getRoleId());
            this.relOperRoleDao.save(roleForOrg);
        }
    }

    *//**
     * 新增组织机构
     * 
     * @param orgVo
     * @param allOrgs
     *//*
    private void addOrg(OrganizationVO orgVo, Map allOrgs) throws Exception {
        Organization org = EntityConverter.convertVO2Organization(orgVo)
                .getOrg();
        // 新增根组织
        if (org.getSsoCode().equals(org.getParentSsoCode())) {
            this.orgDao.save(org);
            org.setOrgPath(org.getOrgId() + OrganizationDTO.PATH_SEPARATOR);
            org.setParentId(org.getOrgId());
            this.orgDao.update(org);
            // 新增非根组织
        } else {
            Organization parentOrg = (Organization) allOrgs.get(orgVo
                    .getParentOrgSSOCode());
            if (parentOrg != null) {
                org.setParentId(parentOrg.getOrgId());
                this.orgDao.save(org);
                org.setOrgPath(parentOrg.getOrgPath() + org.getOrgId()
                        + OrganizationDTO.PATH_SEPARATOR);
                this.orgDao.update(org);
                // 无法找到父节点
            } else {
                throw new Exception(
                        "Create organizaiton failed,not found parent org.Organizaiton sso code="
                                + orgVo.getSsoCode()
                                + "parent organizaiton sso code="
                                + orgVo.getParentOrgSSOCode());
            }
        }

        allOrgs.put(org.getSsoCode(), org);
    }

    *//**
     * 更新组织机构
     * 
     * @param orgVo
     * @param allOrgs
     *//*
    private void updateOrg(OrganizationVO orgVo, Map allOrgs) throws Exception {
        // 获取原组织机构
        Organization oldOrg = (Organization) allOrgs.get(orgVo.getSsoCode());
        Organization parentOrg = (Organization) allOrgs.get(orgVo
                .getParentOrgSSOCode());
        if (parentOrg == null) {
            throw new Exception(
                    "Update organizaiton failed,not found parent org.Organizaiton sso code="
                            + orgVo.getSsoCode()
                            + "parent organizaiton sso code="
                            + orgVo.getParentOrgSSOCode());
        }

        OrganizationDTO orgDto = new OrganizationDTO(oldOrg);
        EntityConverter.convertVO2Organization(orgVo, orgDto);
        oldOrg = orgDto.getOrg();
        oldOrg.setParentId(parentOrg.getOrgId());
        oldOrg.setOrgPath(parentOrg.getOrgPath() + oldOrg.getOrgId()
                + OrganizationDTO.PATH_SEPARATOR);
        this.orgDao.saveOrUpdate(oldOrg);
    }

    *//**
     * @param opVo
     * @param orgs
     * @param operators
     *//*
    private void updateOperator(OperatorVO opVo, Map orgs, Map operators)
            throws Exception {
        Organization org = (Organization) orgs.get(opVo.getOrgSSOCode());

        // 操作员所属组织机构不存在
        if (org == null) {
            throw new Exception(
                    "Update operator failed,not found org.Operator sso code ="
                            + opVo.getSsoCode() + ",organizaiton sso code ="
                            + opVo.getOrgSSOCode());
        }

        Operator op = (Operator) operators.get(opVo.getSsoCode());
        OperatorDTO opDto = new OperatorDTO(op);

        EntityConverter.convertVO2Operator(opVo, opDto);
        op = opDto.getOperator();
        op.setOrgId(org.getOrgId());

        this.operatorDao.saveOrUpdate(op);
    }

    *//**
     * @param opVo
     * @param orgs
     * @param operators
     *//*
    private void addOperator(OperatorVO opVo, Map orgs, Map operators)
            throws Exception {
        Operator op = EntityConverter.convertVO2Operator(opVo).getOperator();
        Organization org = (Organization) orgs.get(opVo.getOrgSSOCode());

        // 操作员所属组织机构不存在
        if (org == null) {
            throw new Exception(
                    "Create operator failed,not found org.Operator sso code ="
                            + opVo.getSsoCode() + ",organizaiton sso code ="
                            + opVo.getOrgSSOCode());
        }

        op.setOrgId(org.getOrgId());
        this.operatorDao.save(op);
    }

    *//**
     * 获取最后同步数据时间。
     * 
     * @param paramCode
     *            参数代码。
     * @return
     * @throws ParseException
     *//*
    private Date getLastSyncTime(String paramCode) throws ParseException {
        ConfigParam param = this.configParmaDao
                .findConfigParamByCode(paramCode);
        if (param == null)
            return null;

        String value = param.getConfigValue();
        if (value == null || value.length() <= 0)
            return null;

        return format.parse(param.getConfigValue());
    }

    *//**
     * 更新最后同步数据时间。
     * 
     * @param paramCode
     * @param lastSyncTime
     *//*
    private void updateLastSyncTime(String paramCode, Date lastSyncTime) {
        ConfigParam param = this.configParmaDao
                .findConfigParamByCode(paramCode);
        if (param == null) {
            param = new ConfigParam();
            param.setConfigCode(paramCode);
            param.setConfigValue(format.format(lastSyncTime));
            this.configParmaDao.save(param);
        } else {
            param.setConfigValue(format.format(lastSyncTime));
            this.configParmaDao.update(param);
        }
    }

    *//**
     * 查询所有系统中已有的操作员并组装成Map。
     * 
     * @return
     *//*
    private Map getOperators() {
        Map ret = new HashMap();
        List oplist = this.operatorDao.findAll();

        for (Iterator iter = oplist.iterator(); iter.hasNext();) {
            Operator element = (Operator) iter.next();
            ret.put(element.getSsoCode(), element);
        }

        return ret;
    }

    *//**
     * 查询系统中已有的组织机构并组装成Map。
     * 
     * @return
     *//*
    private Map getAllOrgs() {
        Map orgs = new HashMap();
        List result = this.orgDao.findAll();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Organization org = (Organization) iterator.next();
            orgs.put(org.getSsoCode(), org);
        }

        return orgs;
    }

    *//**
     * @spring.property ref="ssoClientFactory"
     * @param ssoClientFactory
     *            The ssoClientFactory to set.
     *//*
    public void setSsoClientFactory(SSOClientFactory ssoClientFactory) {
        this.ssoClientFactory = ssoClientFactory;
    }

    *//**
     * @spring.property value="${data.syn.enable}"
     * @param synOn
     *            The synOn to set.
     *//*
    public void setSynOn(boolean synOn) {
        this.synOn = synOn;
    }

    *//**
     * @spring.property ref="ConfigParamDao"
     * @param configParmaDao
     *            The configParmaDao to set.
     *//*
    public void setConfigParmaDao(ConfigParamDao configParmaDao) {
        this.configParmaDao = configParmaDao;
    }

    *//**
     * @spring.property ref="OperatorDao"
     * @param operatorDao
     *            The operatorDao to set.
     *//*
    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    *//**
     * @spring.property ref="OrganizationDao"
     * @param orgDao
     *            The orgDao to set.
     *//*
    public void setOrgDao(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    *//**
     * @spring.property ref="RoleDao"
     * @param roleDao
     *            The roleDao to set.
     *//*
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    *//**
     * @spring.property ref="RelOperRoleDao"
     * @param relOperRoleDao
     *            the relOperRoleDao to set
     *//*
    public void setRelOperRoleDao(RelOperRoleDao relOperRoleDao) {
        this.relOperRoleDao = relOperRoleDao;
    }

    *//**
     * @spring.property ref="RelOperOrgDao"
     * @param relOperOrgDao
     *            the relOperOrgDao to set
     *//*
    public void setRelOperOrgDao(RelOperOrgDao relOperOrgDao) {
        this.relOperOrgDao = relOperOrgDao;
    }
}
*/