package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.ft.busi.sm.impl.dao.GroupDao;
import com.ft.busi.sm.impl.dao.OperatorDao;
import com.ft.busi.sm.impl.dao.OrganizationDao;
import com.ft.busi.sm.impl.dao.PermitLogDao;
import com.ft.busi.sm.impl.dao.RelOperGroupDao;
import com.ft.busi.sm.impl.dao.RelOperOrgDao;
import com.ft.busi.sm.impl.dao.RelOperRoleDao;
import com.ft.busi.sm.impl.dao.RelRoleResDao;
import com.ft.busi.sm.impl.dao.RoleDao;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.PermitLogDTO;
import com.ft.sm.dto.RelOperRoleDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperOrg;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Role;
import com.ft.utils.encode.Md5PasswordEncoder;

/**
 * 操作员管理实现类.
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="operatorManager" parent="transactionProxyFactoryBean"
 *                  target="operatorManagerImpl"
 * 
 * @spring.bean id="operatorManagerImpl"
 */
public class OperatorManagerImpl implements OperatorManager {
    public static final long COPY_FUNCTION_ROLE = 1;

    public static final long COPY_DATA_ROLE = 2;

    public static final long COPY_DATA_ORG = 3;

    public static final long COPY_GROUP = 4;

    private OperatorDao operatorDao;

    private OrganizationDao orgDao;

    private GroupDao groupDao;

    private RoleDao roleDao;

    private PermitLogDao permitLogDao;

    private RelOperRoleDao relOperRoleDao;

    private RelOperGroupDao relOperGroupDao;

    private RelOperOrgDao relOperOrgDao;

    private RelRoleResDao relRoleResDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OperatorManager#saveOperatorAccessOrg(com.huashu.boss.sm.entity.privilege.Operator,
     *      com.huashu.boss.sm.entity.privilege.Organization[])
     */
    public void saveOperatorAccessOrg(OperatorDTO grantor, OperatorDTO op,
            OrganizationDTO[] selectOrgs) {

        if (grantor == null || op == null || selectOrgs == null) {
            throw new IllegalArgumentException();
        }

        // 查询已有的可访问组织
        List ownAccessOrgs = this.orgDao.findAcessOrgByOperatorId(op
                .getOperatorId(), OrganizationDTO.STATUS_NORMAL);

        List temp = EntityDTOConverter.converDTO2Entity(Arrays
                .asList(selectOrgs));

        // 选择需要添加的可访问组织
        List addedOrgs = CompareListUtils.compareOrgList(ownAccessOrgs, temp);
        for (Iterator iter = addedOrgs.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            RelOperOrg relOperOrg = new RelOperOrg();
            relOperOrg.setOperatorId(op.getOperatorId().longValue());
            relOperOrg.setOrgId(org.getOrgId());
            this.relOperOrgDao.save(relOperOrg);

            // 记录变更日志
            logRelOperOrg(grantor, op, org, PermitLogDTO.OPERATION_TYPE_ADD);
        }

        // 选择需要删除的可访问组织
        List removedOrgs = CompareListUtils.compareOrgList(temp, ownAccessOrgs);
        for (Iterator iter = removedOrgs.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            this.relOperRoleDao.deleteRelOperRole(op.getOperatorId(), null,
                    new Long(org.getOrgId()), true);
            this.relOperOrgDao.deleteRelOperOrg(op.getOperatorId(), new Long(
                    org.getOrgId()));
            logRelOperOrg(grantor, op, org, PermitLogDTO.OPERATION_TYPE_REMOVE);
        }
    }

    /**
     * @param grantor
     * @param op
     * @param org
     */
    private void logRelOperOrg(OperatorDTO grantor, OperatorDTO op,
            Organization org, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_OP,
                PermitLogDTO.PERMIT_TYPE_ORG);
        permitLog.setGranteeId(op.getOperatorId());
        permitLog.setOrgId(org.getOrgId());
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OperatorManager#saveOperatorGroup(com.huashu.boss.sm.entity.privilege.Operator,
     *      com.huashu.boss.sm.entity.privilege.Group[])
     */
    public void saveOperatorGroup(OperatorDTO grantor, OperatorDTO op,
            GroupDTO[] selectGroups) {
        if (grantor == null || op == null || selectGroups == null) {
            throw new IllegalArgumentException();
        }

        // 查询已有的操作员组
        List ownGroups = this.groupDao.findGroupByOperator(op.getOperatorId());
        List temp = EntityDTOConverter.converDTO2Entity(Arrays
                .asList(selectGroups));

        // 选择需要添加的操作员组
        List addedGroups = CompareListUtils.compareGroupList(ownGroups, temp);
        for (Iterator iter = addedGroups.iterator(); iter.hasNext();) {
            Group group = (Group) iter.next();
            RelOperGroup relOperGroup = new RelOperGroup();
            relOperGroup.setGroupId(group.getGroupId());
            relOperGroup.setOperatorId(op.getOperatorId().longValue());
            this.relOperGroupDao.save(relOperGroup);

            logRelOperGroup(grantor, op, group, PermitLogDTO.OPERATION_TYPE_ADD);
        }

        // 选择需要删除的操作员组
        List removedGroups = CompareListUtils.compareGroupList(temp, ownGroups);
        for (Iterator iter = removedGroups.iterator(); iter.hasNext();) {
            Group group = (Group) iter.next();
            this.relOperGroupDao.deleteRelOperGroup(op.getOperatorId(),
                    new Long(group.getGroupId()));
            logRelOperGroup(grantor, op, group,
                    PermitLogDTO.OPERATION_TYPE_REMOVE);
        }
    }

    /**
     * @param grantor
     * @param op
     * @param group
     */
    private void logRelOperGroup(OperatorDTO grantor, OperatorDTO op,
            Group group, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_OP,
                PermitLogDTO.PERMIT_TYPE_GROUP);
        permitLog.setGranteeId(op.getOperatorId());
        permitLog.setGroupId(group.getGroupId());
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OperatorManager#saveOperatorRoleForOrg(com.huashu.boss.sm.entity.privilege.Operator,
     *      com.huashu.boss.sm.entity.privilege.Role[],
     *      com.huashu.boss.sm.entity.privilege.Organization)
     */
    public void saveOperatorRoleForOrg(OperatorDTO grantor, OperatorDTO op,
            RoleDTO[] selectRoles, OrganizationDTO org, long roleType) {
        if (grantor == null || op == null || selectRoles == null) {
            throw new IllegalArgumentException();
        }

        if (roleType != RoleDTO.ROLE_TYPE_DATA
                && roleType != RoleDTO.ROLE_TYPE_FUNCTION) {
            throw new IllegalArgumentException("Not support role type:"
                    + roleType);
        }

        List ownRoles = this.roleDao.findRolesOfOperator(op.getOperatorId(),
                org.getOrgId(), roleType);
        List temp = EntityDTOConverter.converDTO2Entity(Arrays
                .asList(selectRoles));

        // 选择需要添加的角色
        List addedRoles = CompareListUtils.compareRoleList(ownRoles, temp);
        for (Iterator iter = addedRoles.iterator(); iter.hasNext();) {
            Role role = (Role) iter.next();
            RelOperRoleDTO relOperRole = new RelOperRoleDTO(role, org.getOrg(),
                    op.getOperator());
            this.relOperRoleDao.save(relOperRole.getRelOperRole());

            logRelOperRole(grantor, op, org, role,
                    PermitLogDTO.OPERATION_TYPE_ADD);
        }

        // 选择需要删除的角色
        List removedRoles = CompareListUtils.compareRoleList(temp, ownRoles);
        for (Iterator iter = removedRoles.iterator(); iter.hasNext();) {
            Role role = (Role) iter.next();
            this.relOperRoleDao.deleteRelOperRole(op.getOperatorId(), new Long(
                    role.getRoleId()), org.getOrgId(), false);
            logRelOperRole(grantor, op, org, role,
                    PermitLogDTO.OPERATION_TYPE_REMOVE);
        }
    }

    /**
     * 记录授权日志。
     * 
     * @param grantor
     * @param op
     * @param org
     * @param role
     */
    private void logRelOperRole(OperatorDTO grantor, OperatorDTO op,
            OrganizationDTO org, Role role, long optionType) {
        PermitLogDTO permitLog = new PermitLogDTO(grantor.getOperator(),
                optionType, PermitLogDTO.GRANTEE_TYPE_OP,
                PermitLogDTO.PERMIT_TYPE_ROLE);
        permitLog.setGranteeId(op.getOperatorId());
        permitLog.setOrgId(org.getOrgId().longValue());
        permitLog.setRoleId(role.getRoleId());
        this.permitLogDao.save(permitLog.getPermitLog());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#saveOperatorResourceForOrg(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OrganizationDTO, java.lang.Long[])
     */
    public void saveOperatorResourceForOrg(OperatorDTO grantor, OperatorDTO op,
            OrganizationDTO org, Long[] resourceIds) {
        if (grantor == null || op == null || org == null || resourceIds == null) {
            throw new IllegalArgumentException();
        }

        List roleList = this.roleDao.findRolesOfOperator(op.getOperatorId(),
                org.getOrgId(), RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);
        Role virtualRole = null;

        if (roleList.size() > 0) {
            // 操作员在一个组织中只有一个虚拟的功能角色
            virtualRole = (Role) roleList.get(0);
            // 删除该角色所有对应关系
            this.relRoleResDao.deleteRelRoleRes(new Long(virtualRole
                    .getRoleId()));
        } else {
            virtualRole = new Role();
            virtualRole.setRoleName("虚拟角色_" + op.getOperatorId() + "_"
                    + org.getOrgId());
            virtualRole.setRoleType(RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION);
            this.roleDao.save(virtualRole);

            RelOperRoleDTO relOperRole = new RelOperRoleDTO(virtualRole, org
                    .getOrg(), op.getOperator());
            this.relOperRoleDao.save(relOperRole.getRelOperRole());
        }

        List<RelRoleRes> addRelRoleResList = new ArrayList<RelRoleRes>();
        RelRoleRes relRoleRes = null;
        for (int i = 0; i < resourceIds.length; i++) {
            relRoleRes = new RelRoleRes();
            relRoleRes.setResourceId(resourceIds[i].longValue());
            relRoleRes.setRoleId(virtualRole.getRoleId());
            addRelRoleResList.add(relRoleRes);
        }

        this.relRoleResDao.batchSave(addRelRoleResList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OperatorManager#saveOrUpdateOperator(com.huashu.boss.sm.entity.privilege.Operator,
     *      com.huashu.boss.sm.entity.privilege.Organization)
     */
    public Long saveOrUpdateOperator(OperatorDTO opDto, OrganizationDTO org) {
        opDto.setContact(opDto.getContact());
        Operator op = opDto.getOperator();
        op.setOrgId(org.getOrgId().longValue());

        if (op.getOperatorId() <= 0) {
            op.setPassword(Md5PasswordEncoder.encode(op.getPassword()));
        } else {
            op.setModifyTime(new Date());
        }

        this.operatorDao.saveOrUpdate(op);
        return new Long(op.getOperatorId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#recoverOperator(com.huashu.boss.sm.entity.Operator)
     */
    public void enableOperator(OperatorDTO op) {
        op.setStatus(OperatorDTO.STATUS_NORMAL);
        this.operatorDao.update(op.getOperator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOpByOrg(java.lang.Long)
     */
    public List findOpByOrg(Long orgId) {
        List result = this.operatorDao.findOperatorsByOrg(orgId);

        return assembleOpDTOwithOrg(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorByLoginName(java.lang.String)
     */
    public OperatorDTO findOperatorByLoginName(String loginName) {
        StringBuffer hql = new StringBuffer(
                "select new com.ft.sm.dto.OperatorDTO(op,org)");
        hql.append(" from Operator op,Organization org");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=op.").append(Operator.PROP_ORG_ID);
        hql.append(" and op.").append(Operator.PROP_LOGIN_NAME).append("=?");

        List result = this.operatorDao.query(hql.toString(),
                new Object[] { loginName });

        return result.size() > 0 ? (OperatorDTO) result.get(0) : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#deleteOperator(com.huashu.boss.sm.entity.Operator[])
     */
    public void disableOperator(OperatorDTO[] ops) {
        List<Operator> result = new ArrayList<Operator>();

        for (int i = 0; i < ops.length; i++) {
            Operator op = ops[i].getOperator();
            op.setStatus(OperatorDTO.STATUS_DISABLE);
            result.add(op);
        }

        this.operatorDao.batchUpdate(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#changePassword(com.huashu.boss.sm.entity.Operator,
     *      java.lang.String, java.lang.String)
     */
    public boolean changePassword(OperatorDTO op, String oldPassword,
            String newPassword) {
        String password = Md5PasswordEncoder.encode(oldPassword);
        if (op.getPassword().equals(password)) {
            changePassword(op, newPassword);
        } else {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#changePassword(com.huashu.boss.sm.entity.Operator,
     *      java.lang.String)
     */
    public void changePassword(OperatorDTO op, String newPassword) {
        op.setPassword(Md5PasswordEncoder.encode(newPassword));
        this.operatorDao.saveOrUpdate(op.getOperator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findCanConsignedOperators(java.lang.Long)
     */
    public List findCanConsignedOperators(Long operatorId, String opName,
            String loginName, Long orgId, boolean likeOrg) {
        //StringBuffer hql = new StringBuffer(
        //        "select new com.huashu.boss.sm.dto.OperatorDTO(op,org)");
        StringBuffer hql = new StringBuffer(
                        "select op,org");
        hql.append(" from Operator op,Organization org");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID)
                .append("!=?");
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append("=op.")
                .append(Operator.PROP_ORG_ID);
        hql.append(" and op.").append(Operator.PROP_STATUS).append("=").append(
                OperatorDTO.STATUS_NORMAL);

        List<Object> params = new ArrayList<Object>();
        params.add(operatorId);

        if (opName != null && opName.length() > 0) {
            hql.append(" and op.").append(Operator.PROP_OP_NAME).append(
                    " like ?");
            params.add("%" + opName + "%");
        }

        if (loginName != null && loginName.length() > 0) {
            hql.append(" and op.").append(Operator.PROP_LOGIN_NAME).append(
                    " like ?");
            params.add("%" + loginName + "%");
        }

        if (orgId != null && orgId.longValue() > 0) {
            if (!likeOrg) {
                hql.append(" and op.").append(Operator.PROP_ORG_ID)
                        .append("=?");
            } else {
                hql.append(" and org.").append(Organization.PROP_ORG_PATH)
                        .append(" like '%#'|| ? || '#%'");
            }
            params.add(orgId);
        }

        List resultSet = this.operatorDao.query(hql.toString(), params
                .toArray(new Object[0]));
        List<OperatorDTO> result = new ArrayList<OperatorDTO>();
        for (Iterator iterator = resultSet.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            OperatorDTO dto = new OperatorDTO((Operator)object[0],(Organization)object[1]);
            result.add(dto);
        }
        //return this.operatorDao.query(hql.toString(), params
        //        .toArray(new Object[0]));
        return result;

        // List result = this.operatorDao.findCanConsignedOperators(operatorId,
        // opName,
        // loginName);

        // return EntityDTOConverter.converOperator2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorRoleForOrgsOfOperator(java.lang.Long,
     *      int)
     */
    public List findOperatorRoleForOrgsOfOperator(Long operatorId,
            Long roleType, boolean virtual) {
         //modified by libf,2007/03/05
        /*
        List params = new ArrayList();
        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.RelOperRoleDTO(role,org,op)");
        hql
                .append(" from Role role,Organization org,Operator op,RelOperRole ror");
        hql.append(" where role.").append(Role.PROP_ROLE_ID).append("=ror.")
                .append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID)
                .append("=ror.").append(RelOperRole.PROP_ORG_ID);
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID)
                .append("=ror.").append(RelOperRole.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        params.add(operatorId);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE);
        if (!virtual) {
            hql.append("=?");
            params.add(roleType);
        } else {
            hql.append(" in (?,?)");
            params.add(roleType);
            if (roleType.longValue() == RoleDTO.ROLE_TYPE_FUNCTION) {
                params.add(new Long(RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION));
            } else if (roleType.longValue() == RoleDTO.ROLE_TYPE_DATA) {
                params.add(new Long(RoleDTO.ROLE_TYPE_VIRTUAL_DATA));
            }

        }

        return this.operatorDao.query(hql.toString(), params.toArray());
        */
        
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer(
                "select role,org,op");
        hql
                .append(" from Role role,Organization org,Operator op,RelOperRole ror");
        hql.append(" where role.").append(Role.PROP_ROLE_ID).append("=ror.")
                .append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID)
                .append("=ror.").append(RelOperRole.PROP_ORG_ID);
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID)
                .append("=ror.").append(RelOperRole.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        params.add(operatorId);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE);
        if (!virtual) {
            hql.append("=?");
            params.add(roleType);
        } else {
            hql.append(" in (?,?)");
            params.add(roleType);
            if (roleType.longValue() == RoleDTO.ROLE_TYPE_FUNCTION) {
                params.add(new Long(RoleDTO.ROLE_TYPE_VIRTUAL_FUNCTION));
            } else if (roleType.longValue() == RoleDTO.ROLE_TYPE_DATA) {
                params.add(new Long(RoleDTO.ROLE_TYPE_VIRTUAL_DATA));
            }

        }

        List resultSet = this.operatorDao.query(hql.toString(), params.toArray());
        List<RelOperRoleDTO> result = new ArrayList<RelOperRoleDTO>();
        for (Iterator iterator = resultSet.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            result.add(new RelOperRoleDTO((Role)object[0],(Organization)object[1],(Operator)object[2]));
        }
        return result;
        //end modified
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findResourceDistributeOperator(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findOperatorsByResource(Long orgId, Long resourceId) {
        List result = this.operatorDao.findOperatorsByResource(orgId,
                resourceId);
        List opsInGroup = this.operatorDao.findOperatorsByResourceInGroup(
                orgId, resourceId);

        for (Iterator iter = opsInGroup.iterator(); iter.hasNext();) {
            Operator element = (Operator) iter.next();
            boolean inFlag = false;

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Operator op = (Operator) iterator.next();
                if (op.getOperatorId() == element.getOperatorId()) {
                    inFlag = true;
                    break;
                }
            }

            if (!inFlag)
                result.add(element);
        }

        List opsOfConsine = this.operatorDao.findOperatorsByResourceOfConsign(
                orgId, resourceId, new Date());

        for (Iterator iter = opsOfConsine.iterator(); iter.hasNext();) {
            Operator element = (Operator) iter.next();
            boolean inFlag = false;

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Operator op = (Operator) iterator.next();
                if (op.getOperatorId() == element.getOperatorId()) {
                    inFlag = true;
                    break;
                }
            }

            if (!inFlag)
                result.add(element);
        }

        return EntityDTOConverter.converOperator2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorOfDataResource(java.lang.Long)
     */
    public List findOperatorOfDataResourceEntry(Long entryId) {
        List result = this.operatorDao.findOperatorOfDataResourceEntry(entryId);

        return EntityDTOConverter.converOperator2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorOfDataResourceEntry(java.lang.Long[])
     */
    public List findOperatorOfDataResourceEntry(Long[] entryIds) {
        List result = this.operatorDao
                .findOperatorOfDataResourceEntry(entryIds);

        return EntityDTOConverter.converOperator2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorBySSOCode(java.lang.String)
     */
    public OperatorDTO findOperatorBySSOCode(String ssoCode) {
        StringBuffer hql = new StringBuffer(
                "select new com.ft.sm.dto.OperatorDTO(op,org)");
        hql.append(" from Operator op,Organization org");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=op.").append(Operator.PROP_ORG_ID);
        hql.append(" and op.").append(Operator.PROP_SSO_CODE).append("=?");

        List result = this.operatorDao.query(hql.toString(),
                new Object[] { ssoCode });

        return result.size() > 0 ? (OperatorDTO) result.get(0) : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#updateOperator(com.huashu.boss.sm.entity.Operator)
     */
    public void updateOperator(OperatorDTO op) {
        op.setContact(op.getContact());
        this.operatorDao.update(op.getOperator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOpreatorByNameAndLoginName(java.lang.String,
     *      java.lang.String)
     */
    public OperatorDTO[] findOperators(Long orgId, String name, String loginName) {
        List result = this.operatorDao.findOperators(orgId, name, loginName);

        OperatorDTO[] ret = new OperatorDTO[result.size()];

        for (int i = 0; i < result.size(); i++) {
            Operator op = (Operator) result.get(i);
            ret[i] = new OperatorDTO(op);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#createOpreator(com.huashu.boss.sm.entity.Operator,
     *      java.lang.Long, java.lang.Long[], java.lang.Long[])
     */
    public OperatorDTO createOpreator(OperatorDTO opDto, Long orgId,
            Long[] roleIds, Long[] groupIds) {
        Organization org = this.orgDao.getById(orgId);
        Operator op = opDto.getOperator();

        op.setOrgId(org.getOrgId());

        this.operatorDao.save(op);

        if (null != roleIds) {
            for (int i = 0; i < roleIds.length; i++) {
                RelOperRole opRole = new RelOperRole();
                opRole.setOperatorId(op.getOperatorId());
                opRole.setOrgId(org.getOrgId());
                opRole.setRoleId(roleIds[i].longValue());
                this.operatorDao.save(opRole);
            }
        }

        if (null != groupIds) {
            for (int i = 0; i < groupIds.length; i++) {
                RelOperGroup opGroup = new RelOperGroup();
                opGroup.setGroupId(groupIds[i].longValue());
                opGroup.setOperatorId(op.getOperatorId());
                this.operatorDao.save(opGroup);
            }
        }

        return new OperatorDTO(op);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorById(java.lang.Long)
     */
    public OperatorDTO findOperatorById(Long operatorId) {
        Operator op = this.operatorDao.getById(operatorId);

        return new OperatorDTO(op);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        Operator op = this.operatorDao.getById(id);
        if (op == null)
            return null;
        Organization org = this.orgDao.getById(new Long(op.getOrgId()));

        return new OperatorDTO(op, org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.operatorDao.loadByIds(Operator.class, ids);

        return EntityDTOConverter.converOperator2DTO(result);
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
        List result = this.operatorDao.query(hql, params, page);

        return assembleOpDTOwithOrg(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorsOfGroup(java.lang.Long)
     */
    public List findOperatorsOfGroup(Long groupId) {
        List result = this.operatorDao.findOperatorsOfGroup(groupId);
        return EntityDTOConverter.converOperator2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorOfRole(java.lang.Long)
     */
    public List<OperatorDTO> findOperatorOfRole(Long roleId) {
        List result = this.operatorDao.findOperatorOfRole(roleId);
        // return EntityDTOConverter.converOperator2DTO(result);
        List<OperatorDTO> ret = new ArrayList<OperatorDTO>();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            OperatorDTO opDto = new OperatorDTO((Operator) object[0],
                    (Organization) object[1]);
            ret.add(opDto);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchSaveOperatorRoleForOrg(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.RoleDTO[],
     *      com.huashu.boss.sm.dto.OrganizationDTO, long)
     */
    public void batchSaveOperatorRoleForOrg(OperatorDTO currentUser,
            OperatorDTO[] ops, RoleDTO[] roles, OrganizationDTO org,
            long roleType) {
        for (int i = 0; i < ops.length; i++) {
            // 如果操作员对组织不可访问,则添加可访问组织。
            RelOperOrg opOrg = relOperOrgDao.findRelOperOrg(ops[i]
                    .getOperatorId(), org.getOrgId());
            if (opOrg == null) {
                RelOperOrg relOperOrg = new RelOperOrg();
                relOperOrg.setOperatorId(ops[i].getOperatorId().longValue());
                relOperOrg.setOrgId(org.getOrgId().longValue());
                this.relOperOrgDao.save(relOperOrg);
                // 记录变更日志
                logRelOperOrg(currentUser, ops[i], org.getOrg(),
                        PermitLogDTO.OPERATION_TYPE_ADD);
            }
            for (int j = 0; j < roles.length; j++) {
                RelOperRole existOperRole = relOperRoleDao.findRelOperRole(
                        ops[i].getOperatorId(), roles[j].getRoleId(), org
                                .getOrgId());
                // 不存在访问关系
                if (existOperRole == null) {
                    RelOperRoleDTO relOperRole = new RelOperRoleDTO(roles[j]
                            .getRole(), org.getOrg(), ops[i].getOperator());
                    this.relOperRoleDao.save(relOperRole.getRelOperRole());
                    logRelOperRole(currentUser, ops[i], org,
                            roles[j].getRole(), PermitLogDTO.OPERATION_TYPE_ADD);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchDeleteOperatorRoleForOrg(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.RoleDTO[],
     *      com.huashu.boss.sm.dto.OrganizationDTO, long)
     */
    public void batchDeleteOperatorRoleForOrg(OperatorDTO currentUser,
            OperatorDTO[] ops, RoleDTO[] roles, OrganizationDTO org,
            long role_type_function) throws Exception {
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < roles.length; j++) {
                RelOperRole opRole = this.relOperRoleDao.findRelOperRole(ops[i]
                        .getOperatorId(), roles[j].getRoleId(), org.getOrgId());
                if (opRole != null) {
                    this.relOperOrgDao.delete(opRole);
                    logRelOperRole(currentUser, ops[i], org,
                            roles[j].getRole(),
                            PermitLogDTO.OPERATION_TYPE_REMOVE);
                }
            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchSaveOperatorAccessOrg(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.OrganizationDTO[])
     */
    public void batchSaveOperatorAccessOrg(OperatorDTO currentUser,
            OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception {
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < orgs.length; j++) {
                RelOperOrg opOrg = this.relOperOrgDao.findRelOperOrg(ops[i]
                        .getOperatorId(), orgs[j].getOrgId());
                if (opOrg == null) {
                    RelOperOrg relOperOrg = new RelOperOrg();
                    relOperOrg
                            .setOperatorId(ops[i].getOperatorId().longValue());
                    relOperOrg.setOrgId(orgs[j].getOrgId().longValue());
                    this.relOperOrgDao.save(relOperOrg);

                    // 记录变更日志
                    logRelOperOrg(currentUser, ops[i], orgs[j].getOrg(),
                            PermitLogDTO.OPERATION_TYPE_ADD);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchDeleteOperatorAccessOrg(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.OrganizationDTO[])
     */
    public void batchDeleteOperatorAccessOrg(OperatorDTO currentUser,
            OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception {
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < orgs.length; j++) {
                RelOperOrg opOrg = this.relOperOrgDao.findRelOperOrg(ops[i]
                        .getOperatorId(), orgs[j].getOrgId());
                if (opOrg != null) {
                    this.relOperOrgDao.delete(opOrg);
                    this.relOperRoleDao.deleteRelOperRole(ops[i]
                            .getOperatorId(), null, orgs[j].getOrgId(), true);
                    // 记录变更日志
                    logRelOperOrg(currentUser, ops[i], orgs[j].getOrg(),
                            PermitLogDTO.OPERATION_TYPE_REMOVE);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchSaveOperatorGroup(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.GroupDTO[])
     */
    public void batchSaveOperatorGroup(OperatorDTO currentUser,
            OperatorDTO[] ops, GroupDTO[] groups) throws Exception {
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < groups.length; j++) {
                RelOperGroup opGroup = relOperGroupDao.findRelOperGroup(ops[i]
                        .getOperatorId(), groups[j].getGroupId());
                if (opGroup == null) {
                    RelOperGroup relOperGroup = new RelOperGroup();
                    relOperGroup.setGroupId(groups[j].getGroupId().longValue());
                    relOperGroup.setOperatorId(ops[i].getOperatorId()
                            .longValue());
                    this.relOperGroupDao.save(relOperGroup);
                    logRelOperGroup(currentUser, ops[i], groups[j].getGroup(),
                            PermitLogDTO.OPERATION_TYPE_ADD);
                }

            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#batchDeleteOperatorGroup(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[],
     *      com.huashu.boss.sm.dto.GroupDTO[])
     */
    public void batchDeleteOperatorGroup(OperatorDTO currentUser,
            OperatorDTO[] ops, GroupDTO[] groups) throws Exception {
        for (int i = 0; i < ops.length; i++) {
            for (int j = 0; j < groups.length; j++) {
                RelOperGroup opGroup = relOperGroupDao.findRelOperGroup(ops[i]
                        .getOperatorId(), groups[j].getGroupId());
                if (opGroup != null) {
                    this.relOperGroupDao.delete(opGroup);
                    logRelOperGroup(currentUser, ops[i], groups[j].getGroup(),
                            PermitLogDTO.OPERATION_TYPE_REMOVE);
                }

            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#copyOperator(com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO,
     *      com.huashu.boss.sm.dto.OperatorDTO[], long[])
     */
    @SuppressWarnings({ "unchecked", "unchecked" })
	public void copyOperator(OperatorDTO currentUser, Long sourceOpId,
            OperatorDTO[] targetOps, long[] types) throws Exception {

        // 复制可访问组织
        if (ArrayUtils.contains(types, COPY_DATA_ORG)) {
            List orgs = this.orgDao.findAcessOrgByOperatorId(sourceOpId,
                    OrganizationDTO.STATUS_NORMAL);
            if (!orgs.isEmpty()) {
                OrganizationDTO[] orgDTOs = (OrganizationDTO[]) EntityDTOConverter
                        .converOrg2DTO(orgs).toArray(new OrganizationDTO[0]);
                this
                        .batchSaveOperatorAccessOrg(currentUser, targetOps,
                                orgDTOs);
            }
        }

        boolean dataRole = ArrayUtils.contains(types, COPY_DATA_ROLE);
        boolean role = ArrayUtils.contains(types, COPY_FUNCTION_ROLE);

        if (dataRole && role) {
            // 同时复制业务角色和功能角色
            copyRole(sourceOpId, targetOps, null);
        } else if (dataRole && !role) {
            // 复制业务角色
            copyRole(sourceOpId, targetOps, new Long(RoleDTO.ROLE_TYPE_DATA));
        } else if (role && !dataRole) {
            // 复制功能角色
            copyRole(sourceOpId, targetOps,
                    new Long(RoleDTO.ROLE_TYPE_FUNCTION));
        }

        // 复制组
        if (ArrayUtils.contains(types, COPY_GROUP)) {
            List groups = this.groupDao.findGroupByOperator(sourceOpId);
            if (!groups.isEmpty()) {
                GroupDTO[] groupDTO = (GroupDTO[]) EntityDTOConverter
                        .converGroup2DTO(groups).toArray(
                                new GroupDTO[groups.size()]);
                this.batchSaveOperatorGroup(currentUser, targetOps, groupDTO);
            }
        }
    }

    /**
     * 复制角色
     * 
     * @param sourceOpId
     * @param targetOps
     * @param roleType
     */
    private void copyRole(Long sourceOpId, OperatorDTO[] targetOps,
            Long roleType) {
        List opRole = this.relOperRoleDao.findRelOperRoleOfOp(sourceOpId,
                roleType);
        for (Iterator iterator = opRole.iterator(); iterator.hasNext();) {
            RelOperRole element = (RelOperRole) iterator.next();
            for (int i = 0; i < targetOps.length; i++) {
                RelOperRole existOperRole = relOperRoleDao.findRelOperRole(
                        targetOps[i].getOperatorId(), new Long(element
                                .getRoleId()), new Long(element.getOrgId()));
                if (existOperRole == null) {
                    // 检查组织是否可访问
                    RelOperOrg opOrg = relOperOrgDao.findRelOperOrg(
                            targetOps[i].getOperatorId(), new Long(element
                                    .getOrgId()));
                    if (opOrg == null) {
                        RelOperOrg relOperOrg = new RelOperOrg();
                        relOperOrg.setOperatorId(targetOps[i].getOperatorId()
                                .longValue());
                        relOperOrg.setOrgId(element.getOrgId());
                        this.relOperOrgDao.save(relOperOrg);
                        // 记录变更日志
                        // logRelOperOrg(currentUser, ops[i], orgs[j].getOrg(),
                        // PermitLogDTO.OPERATION_TYPE_ADD);
                    }
                    RelOperRole relOperRole = new RelOperRole();
                    relOperRole.setOrgId(element.getOrgId());
                    relOperRole.setOperatorId(targetOps[i].getOperatorId()
                            .longValue());
                    relOperRole.setRoleId(element.getRoleId());
                    this.relOperOrgDao.save(relOperRole);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#findOperatorsInAccessOrgs(java.lang.Long)
     */
    public List findOperatorsInAccessOrgs(String loginName,String name,Long operatorId, Long loginOrgId) {
        List result = this.operatorDao.findOperatorsInAccssOrgs(loginName,name,operatorId,
                loginOrgId);
        return assembleOpDTOwithOrg(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperatorManager#searchOperator(java.lang.Long[],
     *      java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public List searchOperator(Long orgId, String loginName, String name,
            boolean includeAll) {
        List result = this.operatorDao.searchOperator(orgId, loginName, name,
                includeAll);

        List ret = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object[] object = (Object[]) iterator.next();
            OperatorDTO opDto = new OperatorDTO((Operator) object[0],
                    (Organization) object[1]);
            ret.add(opDto);
        }

        return ret;
    }

    public List searchOperator(String orgIdStr) {
        List result = this.operatorDao.searchOperator(orgIdStr);
        return EntityDTOConverter.converOperator2DTO(result);
    }

    /**
     * 找出操作员的组织并组装成DTO
     * 
     * @param result
     * @return
     */
    private List assembleOpDTOwithOrg(List result) {
        long[] orgIds = ArrayUtils.EMPTY_LONG_ARRAY;
        
        for (Iterator iter = result.iterator(); iter.hasNext();) {
            Operator element = (Operator) iter.next();
            if (!ArrayUtils.contains(orgIds, element.getOrgId())) {
                orgIds = ArrayUtils.add(orgIds, element.getOrgId());
            }
        }
        List orgs = this.orgDao.loadByIds(Organization.class, ArrayUtils
                .toObject(orgIds));
        return EntityDTOConverter.converOperator2DTO(orgs, result);
    }

    /**
     * @spring.property ref = "OperatorDao"
     * 
     * @param operatorDao
     *            The operatorDao to set.
     */
    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

    /**
     * @spring.property ref = "OrganizationDao"
     * 
     * @param orgDao
     *            The orgDao to set.
     */
    public void setOrgDao(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    /**
     * @spring.property ref = "GroupDao"
     * 
     * @param groupDao
     *            The groupDao to set.
     */
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * @spring.property ref = "RoleDao"
     * @param roleDao
     *            The roleDao to set.
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * @spring.property ref = "PermitLogDao"
     * @param permitLogDao
     *            the permitLogDao to set
     */
    public void setPermitLogDao(PermitLogDao permitLogDao) {
        this.permitLogDao = permitLogDao;
    }

    /**
     * @spring.property ref = "RelOperRoleDao"
     * @param relOperRoleDao
     *            the relOperRoleDao to set
     */
    public void setRelOperRoleDao(RelOperRoleDao relOperRoleDao) {
        this.relOperRoleDao = relOperRoleDao;
    }

    /**
     * @spring.property ref = "RelOperGroupDao"
     * @param relOperGroupDao
     *            the relOperGroupDao to set
     */
    public void setRelOperGroupDao(RelOperGroupDao relOperGroupDao) {
        this.relOperGroupDao = relOperGroupDao;
    }

    /**
     * @spring.property ref = "RelOperOrgDao"
     * @param relOperOrgDao
     *            the relOperOrgDao to set
     */
    public void setRelOperOrgDao(RelOperOrgDao relOperOrgDao) {
        this.relOperOrgDao = relOperOrgDao;
    }

    /**
     * @spring.property ref = "RelRoleResDao"
     * @param relRoleResDao
     *            the relRoleResDao to set
     */
    public void setRelRoleResDao(RelRoleResDao relRoleResDao) {
        this.relRoleResDao = relRoleResDao;
    }

}
