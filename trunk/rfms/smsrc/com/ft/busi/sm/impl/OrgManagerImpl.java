/**
 * @{#} OrgManagerImpl.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.ft.busi.sm.impl.dao.OrganizationDao;
import com.ft.busi.sm.impl.dao.RegionDao;
import com.ft.busi.sm.impl.dao.RelOrgRegionDao;
import com.ft.busi.sm.impl.dao.RelRoleOrgDao;
import com.ft.busi.sm.model.OrgManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.Region;
import com.ft.sm.entity.RelGroupOrg;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOrgRegion;

/**
 * ��֯��������ʵ����.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.bean id="orgManagerImpl"
 */
public class OrgManagerImpl implements OrgManager {
    private OrganizationDao orgDao;

    private RegionDao regionDao;

    private RelOrgRegionDao relOrgRegionDao;

    private RelRoleOrgDao relRoleOrgDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OrgManager#findAllOrgOrderByParentId()
     */
    public List findAllOrgOrderByOrgName() {
        List result = this.orgDao.findAllOrgsOrderByOrgName();

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OrgManager#findRootOrg()
     */
    public OrganizationDTO findRootOrg() {
        Organization org = this.orgDao.findRootOrg();

        if (org != null)
            return new OrganizationDTO(org);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OrgManager#getOrgById(java.lang.Long)
     */
    public OrganizationDTO findOrgById(Long orgId) {
        Organization org = (Organization) this.orgDao.getObjectById(
                Organization.class, orgId);

        if (org != null)
            return new OrganizationDTO(org);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OrgManager#saveOrg(com.huashu.boss.sm.entity.privilege.Organization,
     *      com.huashu.boss.sm.entity.privilege.Organization)
     */
    public Long save(OrganizationDTO orgDto, OrganizationDTO parentDto) {
        Organization org = orgDto.getOrg();
        Organization parent = parentDto.getOrg();
        orgDto.setContact(orgDto.getContact());
        org.setParentId(parent.getOrgId());

        this.orgDao.saveOrUpdate(org);
        org.setOrgPath(parent.getOrgPath() + org.getOrgId()
                + OrganizationDTO.PATH_SEPARATOR);
        this.orgDao.saveOrUpdate(org);
        return new Long(org.getOrgId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#update(com.huashu.boss.sm.dto.OrganizationDTO,
     *      com.huashu.boss.sm.dto.OrganizationDTO)
     */
    public void update(OrganizationDTO orgDto, OrganizationDTO parentDto) {
        Organization org = orgDto.getOrg();
        Organization parent = parentDto.getOrg();
        orgDto.setContact(orgDto.getContact());

        org.setParentId(parent.getOrgId());
        org.setModifyTime(new Date());

        org.setOrgPath(parent.getOrgPath() + org.getOrgId()
                + OrganizationDTO.PATH_SEPARATOR);
        this.orgDao.saveOrUpdate(org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.OrgManager#saveOrgAccessRegion(com.huashu.boss.sm.entity.privilege.Organization,
     *      com.huashu.boss.sm.entity.basedata.Region[])
     */
    public void saveOrgAccessRegion(OrganizationDTO org, Region[] selectRegion) {
        List ownRegions = this.regionDao.findRegionByOrgId(org.getOrgId());
        List tempList = Arrays.asList(selectRegion);
        List<RelOrgRegion> tempListB = new ArrayList<RelOrgRegion>();

        List addedRegions = CompareListUtils.compareRegionList(ownRegions,
                tempList);

        for (Iterator iter = addedRegions.iterator(); iter.hasNext();) {
            Region element = (Region) iter.next();
            RelOrgRegion oar = new RelOrgRegion();
            oar.setOrgId(org.getOrgId().longValue());
            oar.setRegionId(element.getRegionId());
            tempListB.add(oar);
        }

        // �����֯�ɷ�������
        if (!tempListB.isEmpty()) {
            this.relOrgRegionDao.batchSave(tempListB);
        }

        // ɾ����֯�ɷ�������
        List removedRegions = CompareListUtils.compareRegionList(tempList,
                ownRegions);
        for (Iterator iter = removedRegions.iterator(); iter.hasNext();) {
            Region element = (Region) iter.next();
            this.relOrgRegionDao.deleteRelOrgRegion(org.getOrgId(), new Long(
                    element.getRegionId()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#disableOrg(com.huashu.boss.sm.entity.Organization[])
     */
    public void disableOrg(OrganizationDTO[] orgs) {
        List<Organization> entityList = new ArrayList<Organization>();

        for (int i = 0; i < orgs.length; i++) {
            Organization org = orgs[i].getOrg();
            org.setStatus(OrganizationDTO.STATUS_DISABLE);
            entityList.add(org);
        }

        this.orgDao.batchUpdate(entityList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#recoverOrg(com.huashu.boss.sm.entity.Organization)
     */
    public void enableOrg(OrganizationDTO orgDto) {
        Organization org = orgDto.getOrg();
        org.setStatus(OrganizationDTO.STATUS_NORMAL);
        this.orgDao.update(org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAcessOrgByGroupId(java.lang.Long)
     */
    public List findAcessOrgByGroupId(Long groupId) {
        List result = this.orgDao.findAcessOrgByGroupId(groupId,
                OrganizationDTO.STATUS_NORMAL);

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAcessOrgByGroupIdWithChild(java.lang.Long)
     */
    public List findAcessOrgByGroupIdWithChild(Long groupId) {
        List result = this.orgDao.findAcessOrgByGroupIdIncludeChild(groupId,
                OrganizationDTO.STATUS_NORMAL);

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgsByOperator(java.lang.Long)
     */
    public List findAccessOrgsForOperator(Long operatorId) {
        List result = this.orgDao.findAcessOrgByOperatorId(operatorId,
                OrganizationDTO.STATUS_NORMAL);
        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgsForOperatorWithChild(java.lang.Long)
     */
    public List findAccessOrgsForOperatorWithChild(Long operatorId) {
        List result = this.orgDao.findAcessOrgByOperatorIdIncludeChild(
                operatorId, OrganizationDTO.STATUS_NORMAL);
        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAllAccessOrgsByOperator(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findAllAccessOrgsForOperator(Long operatorId) {
        // ��ѯ����Ա�Լ��ɷ��ʵ���֯�б�
        List result = this.findAccessOrgsForOperator(operatorId);

        // ��ѯ����Ա������Ŀɷ�����֯�б�
        List groupAccessOrgs = this.orgDao.findAccessOrgForOperatorInGroups(
                operatorId, OrganizationDTO.STATUS_NORMAL);

        groupAccessOrgs = EntityDTOConverter.converOrg2DTO(groupAccessOrgs);

        for (Iterator iter = groupAccessOrgs.iterator(); iter.hasNext();) {
            OrganizationDTO element = (OrganizationDTO) iter.next();

            boolean in = false;
            // �����ظ��Ŀɷ�����֯
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                OrganizationDTO org = (OrganizationDTO) iterator.next();
                if (org.getOrgId().equals(element.getOrgId())) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(element);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
	public List findAllAccessOrgsForOperatorIncludeChildren(Long operatorId) {
        List result = new ArrayList();

        // ��ѯ����Ա�Լ��ɷ��ʵ���֯�б�

        List orgs = this.orgDao.findAcessOrgByOperatorIdIncludeChild(
                operatorId, OrganizationDTO.STATUS_NORMAL);
        result.addAll(orgs);
        List orgsInGroup = this.orgDao
                .findAccessOrgForOperatorInGroupsWithChild(operatorId,
                        OrganizationDTO.STATUS_NORMAL);

        for (Iterator iter = orgsInGroup.iterator(); iter.hasNext();) {
            Organization element = (Organization) iter.next();
            boolean flag = true;
            for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
                Organization org = (Organization) iterator.next();
                if (element.getOrgId() == org.getOrgId()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(element);
            }

        }

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgForOperatorInGroups(java.lang.Long)
     */
    public List findAccessOrgForOperatorInGroups(Long operatorId) {
        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.RelGroupOrgDTO(gp,org)");
        hql
                .append(" from Group gp,Organization org,RelGroupOrg rgo,RelOperGroup rog");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=rgo.").append(RelGroupOrg.PROP_ORG_ID);
        hql.append(" and gp.").append(Group.PROP_GROUP_ID).append("=rgo.")
                .append(RelGroupOrg.PROP_GROUP_ID);
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");

        return this.orgDao.query(hql.toString(), new Object[] { operatorId,
                new Long(OrganizationDTO.STATUS_NORMAL) });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findOrgBySSOCode(java.lang.String)
     */
    public OrganizationDTO findOrgBySSOCode(String ssoCode) {
        Organization org = (Organization) this.orgDao
                .getEntityByIdentityAttribute(Organization.class,
                        Organization.PROP_SSO_CODE, ssoCode);
        if (org == null) {
            return null;
        }
        return new OrganizationDTO(org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findChildrenOrgs(java.lang.Long)
     */
    public List findChildrenOrgs(Long parentId) {
        Organization org = this.orgDao.getById(parentId);
        List result = this.orgDao.findChildrenOfOrg(org, -1, false, false);
        Organization parent = this.orgDao.getById(parentId);
        return EntityDTOConverter.converOrg2DTO(parent, result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        Organization org = this.orgDao.getById(id);

        if (org == null)
            return null;

        Organization parent = this.orgDao.getById(new Long(org.getParentId()));

        return new OrganizationDTO(parent, org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.orgDao.loadByIds(Organization.class, ids);

        return EntityDTOConverter.converOrg2DTO(result);
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
        List result = this.orgDao.query(hql, params, page);

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findNodeChildren(java.lang.Long,
     *      java.lang.String)
     */
    public List findNodeChildren(Long nodeId, String type) {
        List result = null;
        if (type == null || type.length() == 0) {
            result = this.orgDao.findChildrenOfOrgOrderByName(nodeId, -1);
        } else {
            result = this.orgDao.findChildrenOfOrgOrderByName(nodeId, new Long(
                    type).longValue());
        }

        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TreeManager#findRootNode()
     */
    public XmlTreeNode findRootNode() {
        return this.findRootOrg();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findOrgsOfRole(java.lang.Long)
     */
    public List findOrgsOfRole(Long roleId) throws Exception {
        List result = this.relRoleOrgDao.findOrgsOfRole(roleId);
        return EntityDTOConverter.converOrg2DTO(result);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgIdsOfOrg(java.lang.Long,
     *      long)
     */
    @SuppressWarnings("unchecked")
	public List findAccessOrgIdsOfOrg(Long orgId, long lorgType,
            boolean includeAll) {
        List result = this.findAccessOrgsOfOrg(orgId, lorgType, includeAll);

        List ret = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            OrganizationDTO object = (OrganizationDTO) iterator.next();
            ret.add(object.getOrgId());
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgsOfOrg(java.lang.Long,
     *      long)
     */
    @SuppressWarnings("unchecked")
	public List findAccessOrgsOfOrg(Long orgId, long lorgType,
            boolean includeAll) {
        Organization org = this.orgDao.getById(orgId);

        if (org == null)
            throw new CommonRuntimeException("error.not.found.oranization",
                    new Object[] { orgId });

        List result = null;
        // �ɷ��ʷֹ�˾
        if (lorgType == OrganizationDTO.ORG_TYPE_COMPANY) {
            result = findAccessCompaniesOfOrg(org);

            // �ɷ�����������
        } else if (lorgType == OrganizationDTO.ORG_TYPE_REGION) {
            result = findAccessRegionsOfOrg(org, includeAll);
            // �ɷ���Ӫҵ��
        } else if (lorgType == OrganizationDTO.ORG_TYPE_BUSIHALL) {
            result = findAccessBusiHallOfOrg(org, includeAll);
            // ����ָ����֯�µ�������֯
        } else {
            result = this.findChildOrgsOfOrg(org, lorgType, includeAll);
        }
        List orgs = EntityDTOConverter.converOrg2DTO(result);
        Collections.sort(orgs, new java.util.Comparator() {
        	public int compare(Object o1, Object o2) {
        		OrganizationDTO org1 = (OrganizationDTO) o1;
        		OrganizationDTO org2 = (OrganizationDTO) o2;
        		return org1.getName().compareTo(org2.getName());
        	}
        	public boolean equals(Object obj) {
        		return this.hashCode()==obj.hashCode();
        	}
        });
        return orgs;
    }

    /**
     * ��ѯָ����֯�ɷ��ʷֹ�˾
     * 
     * @param includeAll
     * @param org
     * @return
     */
    private List findAccessCompaniesOfOrg(Organization org) {
        // Ϊ�ֹ�˾ʱ���ɷ��ʷֹ�˾�ɰ������ӷֹ�˾
        if (org.getOrgType() == OrganizationDTO.ORG_TYPE_COMPANY) {
            return this.findChildOrgsOfOrg(org,
                    OrganizationDTO.ORG_TYPE_COMPANY, true);

            // ����������֯ʱֻ�ܷ����������ֹ�˾
        } else {
            Organization company = this.findParentOrgOfOrg(org,
                    OrganizationDTO.ORG_TYPE_COMPANY);
            List<Organization> ret = new ArrayList<Organization>();

            if (company != null)
                ret.add(company);

            return ret;
        }
    }

    /**
     * ��ѯָ����֯�ɷ�����������
     * 
     * @param org
     * @param includeAll
     *            ����ָ����֯Ϊ�ֹ�˾ʱ��ָ���Ƿ�������ӷֹ�˾�µ�ָ��������֯��
     * @return
     */
    @SuppressWarnings("unchecked")
	private List findAccessRegionsOfOrg(Organization org, boolean includeAll) {
        if (org.getOrgType() == OrganizationDTO.ORG_TYPE_COMPANY) {
            List result = this.findChildOrgsOfOrg(org,
                    OrganizationDTO.ORG_TYPE_REGION, true);

            // �������ӷֹ�˾�µĿɷ�����������
            if (includeAll) {
                return result;
                // ���������ӷֹ�˾�µĿɷ�������
            } else {
                return findAccessOrgOfCompany(org,
                        OrganizationDTO.ORG_TYPE_REGION);
            }
        } else {
            List ret = new ArrayList();
            // ���ϲ�������������
            Organization regionOrg = this.findParentOrgOfOrg(org,
                    OrganizationDTO.ORG_TYPE_REGION);

            // ���ڲ���������֯�������ϼ������޷��ҵ��������������²���
            if (regionOrg == null) {
                ret = this.findChildOrgsOfOrg(org,
                        OrganizationDTO.ORG_TYPE_REGION, includeAll);
            } else {
                ret.add(regionOrg);
            }

            return ret;
        }
    }

    /**
     * ��ѯָ����֯�ɷ�����������
     * 
     * @param org
     * @param includeAll
     * @return
     */
    private List findAccessBusiHallOfOrg(Organization org, boolean includeAll) {
        // ����Ӫҵ������ɷ������������������������е�Ӫҵ���ʹ�����
        if (org.getOrgType() == OrganizationDTO.ORG_TYPE_BUSIHALL) {
            Organization regionOrg = this.findParentOrgOfOrg(org,
                    OrganizationDTO.ORG_TYPE_REGION);
            return this.findChildOrgsOfOrg(regionOrg,
                    OrganizationDTO.ORG_TYPE_BUSIHALL, includeAll);
            // ��������������֯����ɷ��ʸ���֯�µ����е�Ӫҵ���ʹ�����
        } else if (org.getOrgType() == OrganizationDTO.ORG_TYPE_COMPANY
                && !includeAll) {
            return this.findAccessOrgOfCompany(org,
                    OrganizationDTO.ORG_TYPE_BUSIHALL);
        } else {
            return this.findChildOrgsOfOrg(org,
                    OrganizationDTO.ORG_TYPE_BUSIHALL, includeAll);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgOfCompany(com.huashu.boss.sm.dto.OrganizationDTO,
     *      long, boolean)
     */
    public List findAccessOrgOfCompany(OrganizationDTO org, long type,
            boolean includeChildCompany) throws Exception {
        // ��ѯ�ֹ�˾������ָ����������֯���������ӷֹ�˾�µ�
        List result = this.findChildOrgsOfOrg(org.getOrg(), type, true);

        if (includeChildCompany) {
            return EntityDTOConverter.converOrg2DTO(result);
        } else {
            List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();
            // ��ѯ�ֹ�˾�µ��ӷֹ�˾
            List childCompanies = this.orgDao.findChildrenOfOrg(org.getOrg(),
                    OrganizationDTO.ORG_TYPE_COMPANY, true, false);
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Organization child = (Organization) iterator.next();

                // �ж��Ƿ�Ϊ�ӷֹ�˾�µ���֯
                boolean in = false;
                for (Iterator iterator2 = childCompanies.iterator(); iterator2
                        .hasNext();) {
                    Organization childCompany = (Organization) iterator2.next();
                    if (child.getOrgPath()
                            .startsWith(childCompany.getOrgPath())) {
                        in = true;
                        break;
                    }
                }

                if (!in) {
                    ret.add(new OrganizationDTO(child));
                }
            }

            return ret;
        }
    }

    /**
     * ��ѯ�ӹ�˾�µ�ָ�����͵���֯��
     * 
     * @param company
     * @param orgType
     * @return
     */
    private List findAccessOrgOfCompany(Organization company, long orgType) {
        List<Organization> ret = new ArrayList<Organization>();

        // ��ѯ�ֹ�˾������ָ����������֯���������ӷֹ�˾�µ�
        List result = this.findChildOrgsOfOrg(company, orgType, true);
        // ��ѯ�ֹ�˾�µ��ӷֹ�˾
        List childCompanies = this.orgDao.findChildrenOfOrg(company,
                OrganizationDTO.ORG_TYPE_COMPANY, true, false);
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Organization child = (Organization) iterator.next();

            // �ж��Ƿ�Ϊ�ӷֹ�˾�µ���֯
            boolean in = false;
            for (Iterator iterator2 = childCompanies.iterator(); iterator2
                    .hasNext();) {
                Organization childCompany = (Organization) iterator2.next();
                if (child.getOrgPath().startsWith(childCompany.getOrgPath())) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                ret.add(child);
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findCompanyIdOfOrg(java.lang.Long)
     */
    public Long findCompanyIdOfOrg(Long orgId) {
        OrganizationDTO org = this.findCompanyOfOrg(orgId);
        return org.getOrgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findCompanyOfOrg(java.lang.Long)
     */
    public OrganizationDTO findCompanyOfOrg(Long orgId) {
        Organization org = this.orgDao.getById(orgId);

        if (org == null)
            throw new CommonRuntimeException("error.not.found.oranization",
                    new Object[] { orgId });

        Organization parentOrg = findParentOrgOfOrg(org,
                OrganizationDTO.ORG_TYPE_COMPANY);

        if (parentOrg == null)
            parentOrg = this.orgDao.findRootOrg();

        return new OrganizationDTO(parentOrg);
    }

    /**
     * ����ָ����֯���ϼ�ָ�����͵���֯��
     * 
     * @param org
     *            ָ������֯����
     * @param orgType
     *            Ҫ���ҵ���֯��������
     * @return
     */
    private Organization findParentOrgOfOrg(Organization org, long orgType) {
        if (org.getOrgType() == orgType)
            return org;

        String orgPath = org.getOrgPath();
        String[] ids = orgPath.split(OrganizationDTO.PATH_SEPARATOR);
        List parentOrgList = this.orgDao.findLocationOfOrg(ids);
        for (int i = parentOrgList.size() - 1; i >= 0; i--) {
            Organization inOrg = (Organization) parentOrgList.get(i);
            if (inOrg.getOrgType() == orgType)
                return inOrg;
        }

        return null;
    }

    /**
     * ����ָ����֯�¼���֯��ָ�����͵���֯��������֯����
     * 
     * @param org
     *            ָ������֯����
     * @param orgType
     *            Ҫ���ҵ���֯�������ͣ�Ϊ-1��Ϊ��ָ��
     * @param includeAll
     *            �Ƿ��������������
     * @return
     */
     private List findChildOrgsOfOrg(Organization org, long orgType,
            boolean includeAll) {
        if (orgType == OrganizationDTO.ORG_TYPE_BUSIHALL) {
            // ��ѯ��������֯
            List result = this.orgDao.findChildrenOfOrg(org, -1, includeAll,
                    true);

            // ���˳�Ӫҵ���ʹ���Ӫҵ��
            List<Organization> ret = new ArrayList<Organization>();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Organization child = (Organization) iterator.next();
                if (child.getOrgType() == OrganizationDTO.ORG_TYPE_BUSIHALL
                        || child.getOrgType() == OrganizationDTO.ORG_TYPE_AGENT) {
                    ret.add(child);
                }
            }

            return ret;
        } else {
            return this.orgDao
                    .findChildrenOfOrg(org, orgType, includeAll, true);
        }
    }

     /*
      * (non-Javadoc)
      * 
      * @see com.huashu.boss.busi.sm.model.OrgManager#findChildOrgsOfOrg(java.lang.Long,long,boolean)
      */
     public List findChildOrgsOfOrg(Long orgId, long lorgType,boolean includeAll){
         Organization org = this.orgDao.getById(orgId);
         List ret = findChildOrgsOfOrg(org,lorgType,includeAll);
         return EntityDTOConverter.converOrg2DTO(ret);
     }
     
    /*
     * ���Ҳ���Ա�ɷ�����֯ID�б������ɷ�����֯������֯�����ɷ�����֯�̳У�
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAllAccessOrgIdsOfOperatorIncludeChild(java.lang.Long)
     */
    public List findAllAccessOrgIdsOfOperatorIncludeChild(Long operatorId)
            throws Exception {
        List<Long> result = new ArrayList<Long>();

        // ��ѯ����Ա�Լ��ɷ��ʵ���֯ID�б�
        List accessOrgIdsOfOp = this.orgDao.findAccessOrgIdsOfOperator(
                operatorId, OrganizationDTO.STATUS_NORMAL);
        for (Iterator iterator = accessOrgIdsOfOp.iterator(); iterator
                .hasNext();) {
            Long orgId = (Long) iterator.next();
            if (!result.contains(orgId)) {
                result.add(orgId);
            }
        }

        // ��ѯ����Ա������Ŀɷ�����֯ID�б�
        List accessOrgIdsOfOpInGroup = this.orgDao
                .findAccessOrgIdsOfOperatorInGroups(operatorId,
                        OrganizationDTO.STATUS_NORMAL);

        for (Iterator iter = accessOrgIdsOfOpInGroup.iterator(); iter.hasNext();) {
            Long element = (Long) iter.next();
            if (!result.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgForOperatorInGroupsWithChild(java.lang.Long)
     */
    public List findAccessOrgForOperatorInGroupsWithChild(Long opId) {
        List result = new ArrayList();
        result = this.orgDao.findAccessOrgForOperatorInGroupsWithChild(opId,
                OrganizationDTO.STATUS_NORMAL);
        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgForOperatorInLoginOrg(java.lang.Long,
     *      java.lang.Long[])
     */
    public List findAccessOrgForOperatorInLoginOrg(Long opId , long[] ids)
            throws Exception {
        List<Organization> result = new ArrayList<Organization>();
        List orgs = this.orgDao.findAccessOrgForOperatorInLoginOrg(opId);
        
        for (Iterator iter = orgs.iterator(); iter.hasNext();) {
            Organization element = (Organization) iter.next();
            if(ArrayUtils.contains(ids, element.getOrgId())){
                result.add(element);
            }
        }
        
        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAccessOrgForGroupInLoginOrg(java.lang.Long)
     */
    public List findAccessOrgForGroupInLoginOrg(Long groupId , long[] ids)
            throws Exception {
        List<Organization> result = new ArrayList<Organization>();
        List orgs = this.orgDao.findAccessOrgForGroupInLoginOrg(groupId);
        
        for (Iterator iter = orgs.iterator(); iter.hasNext();) {
            Organization element = (Organization) iter.next();
            if(ArrayUtils.contains(ids, element.getOrgId())){
                result.add(element);
            }
        }
        return EntityDTOConverter.converOrg2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findOrgsByType(int)
     */
    public List findOrgsByType(int orgType) {
        return EntityDTOConverter.converOrg2DTO(this.orgDao
                .findOrgsByType(orgType));
    }

    /**
     * @spring.property ref="OrganizationDao"
     * 
     * @param orgDao
     *            The orgDao to set.
     */
    public void setOrgDao(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    /**
     * @spring.property ref="RegionDao"
     * 
     * @param regionDao
     *            The regionDao to set.
     */
    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    /**
     * @spring.property ref="RelOrgRegionDao"
     * @param relOrgRegionDao
     *            the relOrgRegionDao to set
     */
    public void setRelOrgRegionDao(RelOrgRegionDao relOrgRegionDao) {
        this.relOrgRegionDao = relOrgRegionDao;
    }

    /**
     * @spring.property ref="RelRoleOrgDao"
     * @param relRoleOrgDao
     */
    public void setRelRoleOrgDao(RelRoleOrgDao relRoleOrgDao) {
        this.relRoleOrgDao = relRoleOrgDao;
    }
}
