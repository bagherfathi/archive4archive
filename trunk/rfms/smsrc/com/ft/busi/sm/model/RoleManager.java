package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * ��ɫ����ӿ�.
 * 
 * @ejb.client jndiName="ejb/sm/roleManager" id="roleManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface RoleManager extends EntityManager {
    /**
     * ���¹��ܽ�ɫ
     * 
     * @param grantor
     *                ִ�в����Ĳ���Ա
     * @param role
     *                ��ɫ
     * @param resources
     *                �������ɫ�Ĺ���Ȩ��
     * @param longs
     * @param appRequest
     *                �����¼
     */
    public void updateRole(OperatorDTO grantor, RoleDTO role, List resources,
            Long[] orgIds, AppRequest appRequest) throws Exception;

    /**
     * ɾ����ɫ��Ϣ
     * 
     * @param grantor
     *                ����Աʵ������
     * @param role
     *                ��ɫʵ������
     * @param appRequest
     *                �����¼
     */
    public void deleteRole(OperatorDTO grantor, RoleDTO role,
            AppRequest appRequest) throws Exception;

    /**
     * �������ܽ�ɫ
     * 
     * @param role
     *                ִ�в����Ĳ���Ա
     * @param resources
     *                ����Ȩ���б�
     * @param orgIds
     *                ������֯id ����Ϊ��
     * @param appRequest
     *                �����¼
     * @return ����Ȩ�޽�ɫʵ��ID
     */
    public Long addRole(OperatorDTO grantor, RoleDTO role, List resources,
            Long[] orgIds, AppRequest appRequest) throws Exception;

    /**
     * ����ҵ���ɫ
     * 
     * @param grantor
     *                ����Աʵ������
     * @param role
     *                ��ɫʵ������
     * @param entryList
     *                ҵ��Ȩ����Ŀ�����б�
     * @param appRequest
     *                �����¼
     * @return ҵ��Ȩ�޽�ɫID
     */
    public Long addDataRole(OperatorDTO grantor, RoleDTO role, List entryList,
            AppRequest appRequest) throws Exception;

    /**
     * ����ҵ���ɫ
     * 
     * @param grantor
     *                ����Աʵ������
     * @param role
     *                ��ɫʵ������
     * @param entryList
     *                ҵ��Ȩ����Ŀ�����б�
     * @param appRequest
     *                �����¼
     */
    public void updateDataRole(OperatorDTO grantor, RoleDTO role,
            List entryList, AppRequest appRequest) throws Exception;

    /**
     * ��ѯ����Ա������֯ӵ�еĽ�ɫ
     * 
     * @param groupId
     *                ����Ա��ID
     * @param orgId
     *                ��֯ID����orgIdΪnullʱ����ѯ����Ա�����н�ɫ
     * @return ��ɫ�����б�
     */
    public List findRolesOfGroup(Long groupId, Long orgId) throws Exception;

    /**
     * ��ѯ����Ա��ɷ���Ľ�ɫ
     * 
     * @param groupId
     *                ����Ա��ID
     * @param orgId
     *                ��֯ID����orgIdΪnullʱ����ѯ����Ա�����пɷ����ɫ
     * @return ��ɫ�����б�
     */
    public List findAvailableRolesOfGroup(Long groupId, Long orgId)
            throws Exception;

    /**
     * ��ѯ����Ա����֯�еĽ�ɫ
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @param roleType
     *                ��ɫ����
     * @return ��ɫ�����б�
     */
    public List findRolesOfOperator(Long opId, Long orgId, long roleType)
            throws Exception;

    /**
     * ��ѯ����Ա����֯�п����õĽ�ɫ
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                ��֯ID
     * @param roleType
     *                ��ɫ����
     * @return ��ɫ�����б�
     */
    public List findAvailableRolesOfOperator(Long opId, Long orgId,
            long roleType) throws Exception;

    /**
     * ����ID��ѯ��ɫ
     * 
     * @param roleId
     *                ��ɫID
     * @return ��ɫʵ������
     */
    public RoleDTO findRoleById(Long roleId) throws Exception;

    /**
     * ��ȡָ�����͵Ľ�ɫ����ɫ��Ϊ���ܽ�ɫ��ҵ���ɫ��
     * 
     * @param roleType
     * @return
     */
    public List findRoleByType(Long roleType) throws Exception;

    /**
     * ��ѯ��ɫ��
     * 
     * @param roleName
     *                ��ɫ����
     * @param roleType
     *                ��ɫ����
     * @return
     */
    public RoleDTO findRoleByName(String roleName, long roleType);

    /**
     * ����������֯�ͽ�ɫ����ѯ��ɫ
     * 
     * @param roleName
     *                ��ɫ���� ����Ϊnull
     * @param roleType
     *                ��ɫ����
     * @return
     */
    public List findRoleByRoleName(String roleName, long roleType);

    /**
     * ������Ӧ��֯��ѯ��ɫ
     * 
     * @param orgId
     *                ��Ӧ��֯ID
     * @param roleType
     *                ��ɫ����
     */
    public List findRoleByAdaptOrgId(Long orgId, long roleType);
    
    /**
     * ��ȡ����ҵ���ɫ��Ӧҵ��Ȩ�޹�ϵ��
     * @return
     * @throws Exception
     */
    public List findAllRelRoleDataRes() throws Exception;
    
    /**
     * ���ݽ�ɫ��ʶ����֯��ʶ��ѯ�ڸ���֯�¸ý�ɫ������Ա��
     * @param roleId
     * @param orgId
     * @return
     * @throws Exception
     */
    public List findOperatorsOfRole(Long roleId,Long orgId)throws Exception;

}
