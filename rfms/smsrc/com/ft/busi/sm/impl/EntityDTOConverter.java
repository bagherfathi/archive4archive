
package com.ft.busi.sm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.BankErrorDTO;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.DTO;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.dto.MaintainLogDTO;
import com.ft.sm.dto.MaintainPlanDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.sm.dto.RuleInfoDTO;
import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.Attach;
import com.ft.sm.entity.Bank;
import com.ft.sm.entity.BankError;
import com.ft.sm.entity.ConfigParam;
import com.ft.sm.entity.ConsignPermit;
import com.ft.sm.entity.DataResource;
import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumEntry;
import com.ft.sm.entity.EnumGroup;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.MaintainLog;
import com.ft.sm.entity.MaintainPlan;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.Region;
import com.ft.sm.entity.Resource;
import com.ft.sm.entity.Role;
import com.ft.sm.entity.RuleCategory;
import com.ft.sm.entity.RuleInfo;
import com.ft.sm.entity.TaskCategory;
import com.ft.sm.entity.TaskJob;
import com.ft.sm.entity.Template;

/**
 * Class comments.
 * 
 * @version 1.0
 */
public class EntityDTOConverter {
    /**
     * ��DTO�б�ת��Ϊʵ���б�
     * 
     * @param dtoList
     *                DTO�б�
     * @return ʵ���б�
     */
    @SuppressWarnings("unchecked")
	public static List converDTO2Entity(List dtoList) {
        List result = new ArrayList();
        for (Iterator iter = dtoList.iterator(); iter.hasNext();) {
            DTO dto = (DTO) iter.next();
            result.add(dto.getTarget());
        }

        return result;
    }

    /**
     * ��ConsignPermitʵ���б�ת��ΪConsignPermitDTO�б�
     * 
     * @param consignPermitList
     *                ConsignPermitʵ���б�
     * @return ConsignPermitDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converConsignPermit2DTO(List consignPermitList) {
        List result = new ArrayList();
        for (Iterator iter = consignPermitList.iterator(); iter.hasNext();) {
            ConsignPermit consignPermit = (ConsignPermit) iter.next();
            result.add(new ConsignPermitDTO(consignPermit));
        }
        return result;
    }

    /**
     * ��Resourceʵ���б�ת��ΪResourceDTO�б�
     * 
     * @param resourceList
     *                Resourceʵ���б�
     * @return ResourceDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converResource2DTO(List resourceList) {
        List result = new ArrayList();
        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            Resource resource = (Resource) iter.next();
            result.add(new ResourceDTO(resource));
        }
        return result;
    }

    /**
     * ��Resourceʵ���б�ת��ΪResourceDTO�б�
     * 
     * @param resourceList
     *                Resourceʵ���б�
     * @return ResourceDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converResource2DTO(List parentResourceList,
            List resourceList) {
        List result = new ArrayList();

        Map parentMap = new HashMap();
        for (Iterator iterator = parentResourceList.iterator(); iterator
                .hasNext();) {
            Resource res = (Resource) iterator.next();
            parentMap.put(res.getResourceId() + "", res);
        }

        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            Resource resource = (Resource) iter.next();
            Resource parent = (Resource) parentMap.get(resource.getParentId()
                    + "");
            if (parent != null) {
                result.add(new ResourceDTO(parent, resource));
            } else {
                result.add(new ResourceDTO(resource));
            }
        }

        return result;
    }

    /**
     * ��Groupʵ���б�ת��ΪGrouDTO�б�
     * 
     * @param groupList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converGroup2DTO(List groupList) {
        List result = new ArrayList();
        for (Iterator iter = groupList.iterator(); iter.hasNext();) {
            Group group = (Group) iter.next();
            result.add(new GroupDTO(group));
        }
        return result;
    }

    /**
     * ��Organizationʵ���б�ת��ΪOrganizationDTO�б�
     * 
     * @param orgList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converOrg2DTO(List orgList) {
        List result = new ArrayList();
        for (Iterator iter = orgList.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            result.add(new OrganizationDTO(org));
        }
        return result;
    }

    /**
     * ��Organizationʵ���б�ת��ΪOrganizationDTO�б�
     * 
     * @param orgList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converOrg2DTO(Organization parent, List orgList) {
        List result = new ArrayList();
        for (Iterator iter = orgList.iterator(); iter.hasNext();) {
            Organization org = (Organization) iter.next();
            result.add(new OrganizationDTO(parent, org));
        }
        return result;
    }

    /**
     * ��Roleʵ���б�ת��ΪRoleDTO�б�
     * 
     * @param roleList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converRole2DTO(List roleList) {
        List result = new ArrayList();
        for (Iterator iter = roleList.iterator(); iter.hasNext();) {
            Role role = (Role) iter.next();
            result.add(new RoleDTO(role));
        }
        return result;
    }

    /**
     * ��Operatorʵ���б�ת��ΪOperatorDTO�б�
     * 
     * @param roleList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converOperator2DTO(List OperatorList) {
        List result = new ArrayList();
        for (Iterator iter = OperatorList.iterator(); iter.hasNext();) {
            Operator op = (Operator) iter.next();
            result.add(new OperatorDTO(op));
        }
        return result;
    }

    /**
     * ��Operatorʵ���б�ת��ΪOperatorDTO�б�
     * 
     * @param roleList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converOperator2DTO(List orgs, List OperatorList) {
        List result = new ArrayList();

        Map orgMap = new HashMap();
        for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
            Organization org = (Organization) iterator.next();
            orgMap.put(org.getOrgId() + "", org);
        }
        for (Iterator iter = OperatorList.iterator(); iter.hasNext();) {
            Operator op = (Operator) iter.next();
            Organization org = (Organization) orgMap.get(op.getOrgId() + "");
            if (org != null)
                result.add(new OperatorDTO(op, org));
            else
                result.add(new OperatorDTO(op));
        }
        return result;
    }

    /**
     * ��DataResourceʵ���б�ת��ΪDataResourceDTO�б�
     * 
     * @param resourceList
     *                DataResourceʵ���б�
     * @return DataResourceDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converDataResource2DTO(List resourceList) {
        List result = new ArrayList();
        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            DataResource resource = (DataResource) iter.next();
            result.add(new DataResourceDTO(resource));
        }
        return result;
    }

    /**
     * ��DataResourceEntryʵ���б�ת��ΪDataResourceEntryDTO�б�
     * 
     * @param resourceList
     *                DataResourceʵ���б�
     * @return DataResourceDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converDataResourceEntry2DTO(List resourceList) {
        List result = new ArrayList();
        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            DataResourceEntry resource = (DataResourceEntry) iter.next();
            result.add(new DataResourceEntryDTO(resource));
        }
        return result;
    }

    /**
     * ��Bankʵ���б�ת��ΪBankDTO�б�
     * 
     * @param bankList
     *                Bankʵ���б�
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converBank2DTO(List bankList) {
        List result = new ArrayList();
        for (Iterator iter = bankList.iterator(); iter.hasNext();) {
            Bank bank = (Bank) iter.next();
            result.add(new BankDTO(bank));
        }

        return result;
    }

    /**
     * ��BankErrorʵ���б�ת��ΪBankErrorDTO�б�
     * 
     * @param bankErrorList
     *                BankErrorʵ���б�
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converBankError2DTO(List bankErrorList) {
        List result = new ArrayList();
        for (Iterator iter = bankErrorList.iterator(); iter.hasNext();) {
            BankError bankError = (BankError) iter.next();
            result.add(new BankErrorDTO(bankError));
        }

        return result;
    }

    /**
     * ��Enumʵ���б�ת��ΪEnumDTO�б�
     * 
     * @param enumList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converEnum2DTO(List enumList) {
        List result = new ArrayList();
        for (Iterator iter = enumList.iterator(); iter.hasNext();) {
        	com.ft.sm.entity.Enum enumData = (com.ft.sm.entity.Enum) iter.next();
            result.add(new EnumDTO(enumData));
        }

        return result;
    }

    /**
     * ��EnumEntryʵ���б�ת��ΪEnumEntryDTO�б�
     * 
     * @param enumEntryList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converEnumEntry2DTO(List enumEntryList) {
        List result = new ArrayList();
        for (Iterator iter = enumEntryList.iterator(); iter.hasNext();) {
            EnumEntry entry = (EnumEntry) iter.next();
            result.add(new EnumEntryDTO(entry));
        }

        return result;
    }

    /**
     * ��EnumCategoryʵ���б�ת��ΪEnumCategoryDTO�б�
     * 
     * @param enumCategoryList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converEnumCategory2DTO(List enumCategoryList) {
        List result = new ArrayList();
        for (Iterator iter = enumCategoryList.iterator(); iter.hasNext();) {
            EnumCategory enumCatefory = (EnumCategory) iter.next();
            result.add(new EnumCategoryDTO(enumCatefory));
        }

        return result;
    }

    /**
     * ��EnumGroupʵ���б�ת��ΪEnumGroupDTO�б�
     * 
     * @param enumGroupList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converEnumGroup2DTO(List enumGroupList) {
        List result = new ArrayList();
        for (Iterator iter = enumGroupList.iterator(); iter.hasNext();) {
            EnumGroup enumGroup = (EnumGroup) iter.next();
            result.add(new EnumGroupDTO(enumGroup));
        }

        return result;
    }

    /**
     * ��Regionʵ���б�ת��ΪRegionDTO�б�
     * 
     * @param regionList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converRegion2DTO(List regionList) {
        List result = new ArrayList();
        for (Iterator iter = regionList.iterator(); iter.hasNext();) {
            Region region = (Region) iter.next();
            result.add(new RegionDTO(region));
        }

        return result;
    }

    /**
     * ��ConfigParamʵ���б�ת��ΪConfigParamDTO�б�
     * 
     * @param resourceList
     *                ConfigParamʵ���б�
     * @return ConfigParamDTO�б�
     */
    @SuppressWarnings("unchecked")
	public static List converConfigParam2DTO(List resourceList) {
        List result = new ArrayList();
        for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
            ConfigParam param = (ConfigParam) iter.next();
            result.add(new ConfigParamDTO(param));
        }
        return result;
    }

    /**
     * ��ά���ƻ�ʵ���б�ת��ΪDTO�б�
     * 
     * @param maintainPlanList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converMaintainPlan2DTO(List maintainPlanList) {
        List result = new ArrayList();
        for (Iterator iter = maintainPlanList.iterator(); iter.hasNext();) {
            MaintainPlan plan = (MaintainPlan) iter.next();
            result.add(new MaintainPlanDTO(plan));
        }
        return result;
    }

    /**
     * ��ά����־ʵ���б�ת��ΪDTO�б�
     * 
     * @param maintainLogList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converMaintainLog2DTO(List maintainLogList) {
        List result = new ArrayList();
        for (Iterator iter = maintainLogList.iterator(); iter.hasNext();) {
            MaintainLog log = (MaintainLog) iter.next();
            result.add(new MaintainLogDTO(log));
        }
        return result;
    }

    /**
     * ��������Ϣʵ���б�ת��ΪDTO�б�
     * 
     * @param afficheList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converAffiche2DTO(List afficheList) {
        List result = new ArrayList();
        for (Iterator iter = afficheList.iterator(); iter.hasNext();) {
            Affiche affiche = (Affiche) iter.next();
            result.add(new AfficheDTO(affiche));
        }

        return result;
    }

    /**
     * ������ʵ���б�ת��ΪDTO�б�
     * 
     * @param attachList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converAttach2DTO(List attachList) {
        List result = new ArrayList();
        for (Iterator iter = attachList.iterator(); iter.hasNext();) {
            Attach attach = (Attach) iter.next();
            result.add(new AttachDTO(attach));
        }

        return result;
    }

    /**
     * ��������Ϣʵ���б�ת��ΪDTO�б�
     * 
     * @param infoSharedList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converInfoShared2DTO(List infoSharedList) {
        List result = new ArrayList();
        for (Iterator iter = infoSharedList.iterator(); iter.hasNext();) {
            InfoShared info = (InfoShared) iter.next();
            result.add(new InfoSharedDTO(info));
        }

        return result;
    }

    /**
     * ��������Ϣ����ʵ���б�ת��ΪDTO�б�
     * 
     * @param infoSharedList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converInfoCategory2DTO(List infoCategoryList) {
        List result = new ArrayList();
        for (Iterator iter = infoCategoryList.iterator(); iter.hasNext();) {
            InfoCategory category = (InfoCategory) iter.next();
            result.add(new InfoCategoryDTO(category));
        }

        return result;
    }

    /**
     * ������ʵ���б�ת��ΪDTO�б�
     * 
     * @param infoCategoryList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converTaskJob2DTO(List taskJobList) {
        List result = new ArrayList();
        for (Iterator iter = taskJobList.iterator(); iter.hasNext();) {
            TaskJob element = (TaskJob) iter.next();
            result.add(new TaskJobDTO(element));
        }
        return result;
    }

    /**
     * ���������ʵ���б�ת��ΪDTO�б�
     * 
     * @param infoCategoryList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converRuleCategory2DTO(List ruleCategoryList) {
        List result = new ArrayList();
        for (Iterator iter = ruleCategoryList.iterator(); iter.hasNext();) {
            RuleCategory element = (RuleCategory) iter.next();
            result.add(new RuleCategoryDTO(element));
        }
        return result;
    }

    /**
     * ��������Ϣʵ���б�ת��ΪDTO�б�
     * 
     * @param ruleInfoList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converRuleInfo2DTO(List ruleInfoList) {
        List result = new ArrayList();
        for (Iterator iter = ruleInfoList.iterator(); iter.hasNext();) {
            RuleInfo element = (RuleInfo) iter.next();
            result.add(new RuleInfoDTO(element));
        }
        return result;
    }

    /**
     * ��ģ��ʵ���б�ת��ΪDTO�б�
     * @param result
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converTemplateDTO(List templateList) {
        List result = new ArrayList();
        for (Iterator iter = templateList.iterator(); iter.hasNext();) {
            Template element = (Template) iter.next();
            result.add(new TemplateDTO(element));
        }
        
        return result;
    }

    /**
     * ���������ʵ���б�ת��ΪDTO�б�
     * @param taskCategoryList
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List converTaskCategory2DTO(List taskCategoryList) {
        List result = new ArrayList();
        for (Iterator iter = taskCategoryList.iterator(); iter.hasNext();) {
            TaskCategory element = (TaskCategory) iter.next();
            result.add(new TaskCategoryDTO(element));
        }
        return result;
    }

    /**
     * ��ģ���ļ�ʵ���б�ת��ΪDTO�б�
     * @param result
     * @return
     */
    /*
    public static List converTemplateFileDTO(List templateFileList) {
        List result = new ArrayList();
        for (Iterator iter = templateFileList.iterator(); iter.hasNext();) {
            TemplateFile element = (TemplateFile) iter.next();
            result.add(new TemplateFileDTO(element));
        }
        
        return result;
    }
    */
}
