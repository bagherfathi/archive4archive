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
 * ʵ�ֺ�SSOϵͳ����֯������Ա����ͬ��.
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

    private static final String PARAM_CODE_LAST_SYNC_ORG_TIME = "last_sync_org_time"; // ��������֯ʱ���������

    private static final String PARAM_CODE_LAST_SYNC_OP_TIME = "last_sync_op_time"; // �����²���Աʱ���������

    public static final String ERROR_CODE_SYNC_ORG = "sm.error.synchronize.org";

    public static final String ERROR_CODE_SYNC_OP = "sm.error.synchronize.op";

    private SSOClientFactory ssoClientFactory;

    private ConfigParamDao configParmaDao;

    private OperatorDao operatorDao;

    private OrganizationDao orgDao;

    private RoleDao roleDao;

    private RelOperRoleDao relOperRoleDao;

    private RelOperOrgDao relOperOrgDao;

    // ͬ������
    private boolean synOn;

    *//**
     * ����ʱ��SSOϵͳ�л�ȡ�������ݽ��и���
     * 
     *//*
    public void init() {
        if (!synOn)
            return;

        Thread syncThread = new Thread() {
            public void run() {
                // ͬ����֯�Ͳ���Ա��Ϣ
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
     * ͬ����֯������Ϣ��
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
            // ��ȡSSOϵͳ��ȫ����֯����
            orgVoArr = dataAccessClient.listOrg();
        } else {
            // ����������ʱ���ѯSSOϵͳ����֯������Ϣ
            orgVoArr = dataAccessClient.getOrgByModifyDate(lastSyncTime);
        }

        Map orgs = this.getAllOrgs();
        for (int i = 0; i < orgVoArr.length; i++) {
            OrganizationVO orgVo = orgVoArr[i];
            // ������֯����
            if (orgs.get(orgVo.getSsoCode()) == null) {
                this.addOrg(orgVo, orgs);
                // ������֯����
            } else {
                this.updateOrg(orgVo, orgs);
            }
        }

        // ����������ʱ��
        updateLastSyncTime(PARAM_CODE_LAST_SYNC_ORG_TIME, newLastUpdateTime);
    }

    *//**
     * ͬ������Ա��Ϣ��
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
            // ��ȡSSOϵͳ��ȫ����֯����
            opVoArr = dataAccessClient.listOperator();
        } else {
            // ����������ʱ���ѯSSOϵͳ����֯������Ϣ
            opVoArr = dataAccessClient.listOperatorByModifyDate(lastSyncTime);
        }

        Map orgs = this.getAllOrgs();
        Map operators = this.getOperators();
        for (int i = 0; i < opVoArr.length; i++) {
            OperatorVO opVo = opVoArr[i];
            // ��������Ա
            if (operators.get(opVo.getSsoCode()) == null) {
                this.addOperator(opVo, orgs, operators);
                // ���²���Ա
            } else {
                this.updateOperator(opVo, orgs, operators);
            }
        }

        // ����������ʱ��
        updateLastSyncTime(PARAM_CODE_LAST_SYNC_OP_TIME, newLastUpdateTime);
    }

    *//**
     * ͬ��ϵͳ����Ա��Ϣ��
     *//*
    public void syncAdmin() throws Exception {
        // ��ѯ����Ա��ɫ
        Role adminRole = this.roleDao.getById(new Long(ADMIN_ROLE_ID));
        if (adminRole == null) {
            throw new Exception(
                    "Sync administrator failed,not fount admin role.");
        }

        // ��ѯ����֯��Ϊ��Ȩ��֯
        Organization org = this.orgDao.findRootOrg();
        if (org == null) {
            throw new Exception(
                    "Sync administrator failed,not fount root organizaiton.");
        }

        // ͬ������Ա��Ϣ
        DataAccessClient dataAccessClient = ssoClientFactory
                .createDataAccessClient();

        OperatorVO[] adminVos = dataAccessClient.getAppAdmin();
        String[] ssoCodes = new String[adminVos.length];
        for (int i = 0; i < adminVos.length; i++) {
            OperatorVO opVO = adminVos[i];
            ssoCodes[i] = opVO.getSsoCode();
        }

        List operatorlist = this.operatorDao.findOperatorsBySSOCodes(ssoCodes);

        // �����Ƿ���Ҫ��Ȩ����δ��Ȩ�Ĺ���Ա������Ȩ
        for (Iterator iter = operatorlist.iterator(); iter.hasNext();) {
            Operator operator = (Operator) iter.next();
            // ���ò���Ա����Ա��ɫ
            addRelOperRole(adminRole, org, operator);
            // ���ò���Ա�ɷ�����֯
            addRelOperOrg(org, operator);
        }
    }

    *//**
     * ���ò���Ա�ɷ�����֯��
     * 
     * @param org
     * @param operator
     *//*
    private void addRelOperOrg(Organization org, Operator operator) {
        // ���ÿɷ�����֯
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
     * ���ò���Ա����Ա��ɫ��
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
        // ϵͳ�������Ա����Ա��ɫ
        if (relOperRole == null) {
            RelOperRole roleForOrg = new RelOperRole();
            roleForOrg.setOperatorId(operator.getOperatorId());
            roleForOrg.setOrgId(org.getOrgId());
            roleForOrg.setRoleId(adminRole.getRoleId());
            this.relOperRoleDao.save(roleForOrg);
        }
    }

    *//**
     * ������֯����
     * 
     * @param orgVo
     * @param allOrgs
     *//*
    private void addOrg(OrganizationVO orgVo, Map allOrgs) throws Exception {
        Organization org = EntityConverter.convertVO2Organization(orgVo)
                .getOrg();
        // ��������֯
        if (org.getSsoCode().equals(org.getParentSsoCode())) {
            this.orgDao.save(org);
            org.setOrgPath(org.getOrgId() + OrganizationDTO.PATH_SEPARATOR);
            org.setParentId(org.getOrgId());
            this.orgDao.update(org);
            // �����Ǹ���֯
        } else {
            Organization parentOrg = (Organization) allOrgs.get(orgVo
                    .getParentOrgSSOCode());
            if (parentOrg != null) {
                org.setParentId(parentOrg.getOrgId());
                this.orgDao.save(org);
                org.setOrgPath(parentOrg.getOrgPath() + org.getOrgId()
                        + OrganizationDTO.PATH_SEPARATOR);
                this.orgDao.update(org);
                // �޷��ҵ����ڵ�
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
     * ������֯����
     * 
     * @param orgVo
     * @param allOrgs
     *//*
    private void updateOrg(OrganizationVO orgVo, Map allOrgs) throws Exception {
        // ��ȡԭ��֯����
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

        // ����Ա������֯����������
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

        // ����Ա������֯����������
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
     * ��ȡ���ͬ������ʱ�䡣
     * 
     * @param paramCode
     *            �������롣
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
     * �������ͬ������ʱ�䡣
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
     * ��ѯ����ϵͳ�����еĲ���Ա����װ��Map��
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
     * ��ѯϵͳ�����е���֯��������װ��Map��
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