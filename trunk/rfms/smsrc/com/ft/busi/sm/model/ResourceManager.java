package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.ResourceDTO;

/**
 * ����Ȩ��ʵ���ҵ��Ȩ��ʵ�����ӿ�.
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/resourceManager" id="resourceManager"
 *             homepackage="com.huashu.boss.busi.sm.ejb"
 */
public interface ResourceManager extends EntityManager,XmlTreeManager {
    /**
     * �����˵�Ȩ��
     * 
     * @param menu
     *                ����Ȩ�޲˵�����
     * @param parentMenu
     *                �����Ȩ������
     * @param appRequest 
     *                �����¼
     * @return ����Ȩ��ʵ������
     */
    public ResourceDTO addResource(ResourceDTO menu, ResourceDTO parentMenu,AppRequest appRequest)
            throws Exception;

    /**
     * ���²˵�/��ťȨ��
     * 
     * @param resource
     *                ����Ȩ��ʵ������
     * @param appRequest
     *                �����¼
     */
    public void updateResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception;

    /**
     * ������Ȩ�޵�������Ϣ
     * 
     * @param resource
     *                ����Ȩ���б�
     */
    public void updateResources(List resource) throws Exception;

    /**
     * ��ѡ��Ȩ��
     * 
     * @param resourceId
     *                ����Ȩ��ID
     * @return �����ӹ���Ȩ�����ݵ��б�
     */
    public List findChildren(Long resourceId) throws Exception;

    /**
     * �������й���Ȩ��
     * 
     * @return ���й���Ȩ�������б�
     */
    public List findAllResources() throws Exception;

    /**
     * ����ҵ��Ȩ��
     * 
     * @param dataResource
     *                ҵ��Ȩ��ʵ������
     * @param appRequest �����¼
     * @return ҵ��Ȩ��ʵ��ID
     */
    public Long addDataResource(DataResourceDTO dataResource, AppRequest appRequest) throws Exception;

    /**
     * ɾ��ҵ��Ȩ��
     * 
     * @param appRequest
     *                �����¼
     * @param dataResources
     *                ҵ��Ȩ���б�
     */
    public void deleteDataResource(DataResourceDTO dr, AppRequest appRequest)
            throws Exception;

    /**
     * ����ҵ��Ȩ��
     * 
     * @param dataResource
     *                ҵ��Ȩ��ʵ������
     * @param dataResources
     *                ҵ��Ȩ���б�
     */
    public void updateDataResource(DataResourceDTO dataResource,
            AppRequest appRequest) throws Exception;

    /**
     * ������е�ҵ��Ȩ��
     * 
     * @return ����ҵ��Ȩ��ʵ�������б�
     */
    public List findAllDataResource() throws Exception;

    /**
     * ������е�ҵ��Ȩ����Ŀ
     * 
     * @return ����ҵ��Ȩ����Ŀ�����б�
     */
    public List findAllDataResourceEntry() throws Exception;

    /**
     * ɾ������Ȩ��
     * 
     * @param resource
     *                ����Ȩ��
     * @param appRequest
     *                �����¼
     */
    public void deleteResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception;

    /**
     * ��ѯ��ɫ�Ĺ���Ȩ��
     * 
     * @param roleId
     *                ��ɫID
     * @return ��ɫ��ӵ�еĹ���Ȩ�������б�
     */
    public List findResourceOfRole(Long roleId) throws Exception;

    /**
     * ��ѯ��ɫ�Ĺ���Ȩ�ޣ��������е��ӹ���Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @return ��ɫ��ӵ�еĹ���Ȩ���б�
     */
    public List findAllCheckedResourceByRoleId(Long roleId) throws Exception;

    /**
     * ��ѯ��ɫ��ҵ��Ȩ��
     * 
     * @param roleId
     *                ��ɫID
     * @return ��ɫ��ӵ�е�ҵ��Ȩ�������б�
     */
    public List findDataResourcesOfRole(Long roleId) throws Exception;

    /**
     * ��ѯ����Ա���еĹ���Ȩ��
     * 
     * @param operatorId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @return ����Ա��ӵ�еĹ���Ȩ�������б�
     */
    public List findAllACLResourcesOfOperator(Long operatorId, Long orgId,
            Date time) throws Exception;

    /**
     * ��ѯ����Ա����ί�������������Ȩ��
     * 
     * @param operatorId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @return ����Ȩ�������б�
     */
    public List findACLResourcesExcludeConsignedOfOperator(Long operatorId,
            Long orgId) throws Exception;

    /**
     * ��ѯ����Ա��ҵ��Ȩ��
     * 
     * @param operatorId
     *                ����ԱID
     * @return ҵ��Ȩ�������б�
     */
    public List findDataResourceEntriesOfOperator(Long operatorId)
            throws Exception;

    /**
     * ��ѯ����Ա����ί���������ȫ������Ȩ��
     * 
     * @param operatorId
     *                ����ԱID
     * @return ����Ȩ�������б�
     */
    public List findAllResourcesExcludeConsignedOfOperator(Long operatorId)
            throws Exception;

    /**
     * ���Ҳ���Ա����ӵ�е�ȫ������Ȩ��
     * 
     * @param groupId
     *                ����Ա��ID
     * @return
     */
    public List findAllResourceOfGroup(Long groupId) throws Exception;

    /**
     * ���ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param entry
     *                ҵ��Ȩ����Ŀ����
     * @param appRequest �����¼
     * @return ҵ��Ȩ����ĿID
     */
    public Long addDataResourceEntry(DataResourceEntryDTO entry, AppRequest appRequest)
            throws Exception;

    /**
     * ɾ��ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param entry
     *                ҵ��Ȩ����Ŀ����
     * @param appRequest
     *                �����¼
     */
    public void deleteDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception;

    /**
     * ����ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param entry
     *                ҵ��Ȩ����Ŀ����
     * @param appRequest
     *                �����¼
     */
    public void updateDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception;

    /**
     * ���id��Ӧ��ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param id
     *                ҵ��Ȩ��ID
     * @return ҵ��Ȩ����Ŀ����
     */
    public DataResourceEntryDTO findDataResourceEntryById(Long id)
            throws Exception;

    /**
     * ͨ��Path���������Ӧ����������Դ
     * 
     * @param path
     *                ����Ȩ��Path����
     * @return ����Ȩ�������б�
     */
    public List findChildResourceByPatch(String path) throws Exception;

    /**
     * ���빦��Ȩ����
     * 
     * @param resourceList
     *                ����Ȩ���б�
     */
    // public List addImportResource(List resourceList) throws Exception;
    /**
     * �ж�ͬ���Ƿ��ظ�
     * 
     * @param resource
     *                ����Ȩ������
     * @param parent
     *                �����Ȩ������
     * @return
     */
    public boolean isExistResource(ResourceDTO resource, ResourceDTO parent)
            throws Exception;

    /**
     * �ж�Url�Ƿ��ظ���������""��"/"
     * 
     * @param url
     *                ����Ȩ��url����
     * @param resourceId
     *                ����Ȩ��ID
     * @return
     */
    public boolean isExistMenuUrl(String url, Long resourceId) throws Exception;

    /**
     * �жϸ��ڵ��Ƿ����
     * 
     * @param resourceName
     *                ����Ȩ������
     * @return
     */
    // public boolean isExistRoot(String resourceName);
    /**
     * �ж�����ҵ��Ȩ����Ŀ�����Ƿ�����ҵ��Ȩ���д���
     * 
     * @param entryId
     *                ��ĿId
     * @param title
     *                ҵ��Ȩ����Ŀ����
     * @param resourceId
     *                ҵ��Ȩ��Id
     * @return
     */
    public boolean isExistEntryByDataResource(Long entryId, String title,
            Long resourceId) throws Exception;
    
    /**
     * ��鵥ֵ��ҵ��Ȩ�޵�ֵ�Ƿ��ظ�
     * @param resourceId  ҵ��Ȩ��ID
     * @param entryId     ��ĿID 
     * @param entryValue  ��Ŀֵ
     * @return
     */
    public boolean isEntryValueDuplicatedOfSingleDataResource(Long entryId,
            Long resourceId, String entryValue) throws Exception;

    /**
     * ��ѯҵ��Ȩ��
     * 
     * @param opId
     *                ����ԱID��
     * @param code
     *                ҵ��Ȩ�޴���
     * @return ҵ��Ȩ��DTO
     */
    public DataResourceDTO getDataResource(Long opId, String code)
            throws Exception;
    
    public DataResourceDTO getDataResource(Long opId, String code, OrganizationDTO orgDto);

    /**
     * ��ȡָ��ID��ҵ��Ȩ���е�������Ŀ��
     * 
     * @param resourceId
     *                ҵ��Ȩ��ID��
     * @return
     */
    public List findEntryByDataResourceId(Long resourceId) throws Exception;
    
    /**
     * ��ѯֱ�Ӹ������Ա��Ȩ�ޡ�
     * @param operatorId    ����ԱID��
     * @param orgId         ��֯����ID��
     * @return
     */
    public List findACLResourcesOfOp(Long operatorId,Long orgId) throws Exception;

    /**
     * ��ȡ����Ա�ڵ�¼��֯�µ�Ȩ�ޡ�
     * @param loginOpId
     * @param loginOrg
     * @param date
     * @return
     */
    public List findAllACLResourcesOfOperator(Long loginOpId,
            OrganizationDTO loginOrg, Date date) throws Exception;

    
}
