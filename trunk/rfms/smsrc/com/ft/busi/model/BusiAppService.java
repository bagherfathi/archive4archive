package com.ft.busi.model;

import java.util.List;

import com.ft.busi.dto.AppAndAppTypeDTO;
import com.ft.busi.dto.AppDTO;
import com.ft.busi.dto.AppQueryDTO;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.dto.AppTypeDTO;
import com.ft.busi.dto.ResultDTO;
import com.ft.commons.page.PageBean;

/**
 * ���������
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/busi/busiAppService" id="busiAppService"
 *             homepackage="com.huashu.boss.busi.ejb"
 * 
 */
public interface BusiAppService {

	/**
	 * ����������ʷ��¼
	 * 
	 * @param appRequest
	 *            �����¼����
	 * 
	 * @return AppDTO �����������¼����
	 */
	public AppDTO saveApp(AppRequest appRequest) throws Exception;

	/**
	 * ��������������list�е���ʷ����
	 * 
	 * @param appRequest
	 *            �����¼����
	 * @param baseService
	 *            ʵ�ֱ�����ʷ�������
	 * @return AppResponse �����¼���ض��󣬰�����ǰ�������Ϣ
	 */
	public List batchSaveHisObject(AppRequest appRequest, List objectList,
			BusiBaseService baseService) throws Exception;

	/**
	 * ���õ�ǰ�������ʷ����ֵ
	 * 
	 * @param appRequest
	 *            �����������
	 * @param object
	 *            ��ǰ����
	 * @return Object ���ú�Ķ���
	 */
	public Object settingCurrentObject(AppRequest appRequest, Object object) throws Exception;

	/**
	 * ���õ�ǰ�������ʷ����ֵ
	 * 
	 * @param appRequest
	 *            �����������
	 * @param object
	 *            ��ǰ����
	 * @return Object ���ú󲢱����Ķ���
	 */
	public Object saveHistoryObject(AppRequest appRequest, Object object,
			BusiBaseService baseService) throws Exception;

	/**
	 * 
	 * ���õ�ǰ�������ʷ����ֵ
	 * 
	 * @param appRequest
	 *            �����������
	 * @param object
	 *            ��ǰ����
	 * @return Object ���ú󲢱����Ķ���
	 */
	public Object saveAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) throws Exception;

	/**
	 * 
	 * ���õ�ǰ��Ҫɾ���Ķ�����ʷ����ֵ
	 * 
	 * @param appRequest
	 *            �����������
	 * @param object
	 *            ��ǰ����
	 * @return Object ���ú󲢱����Ķ���
	 */
	public Object deleteAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) throws Exception;

	/**
	 * ���������ʶ��ѯ������Ϣ
	 * 
	 * @param appId
	 * @return �����¼
	 */
	public AppDTO getAppDTOByAppId(long appId) throws Exception;

	/**
	 * ���������Ų�ѯ������Ϣ
	 * 
	 * @param appCode
	 * @return �����¼
	 */
	public AppDTO findAppDTOByAppCode(String appCode) throws Exception;

	/**
	 * �����������ͱ����ѯ��������
	 * 
	 * @param appAction
	 * @return ��������
	 */
	public AppTypeDTO findAppTypeDTOByAppAction(String appAction) throws Exception;

	/**
	 * ����ҵ�����Ͳ�����������
	 * 
	 * @param category
	 * @return
	 */
	public List findAppTypeByCategory(long category) throws Exception;

	/**
	 * �����������ͱ�ʶ������������
	 */
	public List findAppTypeByIds(Long[] appTypeIds) throws Exception;
	
	/**
	 * ���������ʶ
	 * @param appIds
	 * @return
	 * @throws Exception
	 */
	public long filterCSLatestAppId(Long[] appIds) throws Exception;
	
	
	/**
	 * �޸�����״̬
	 * @param appId
	 * @param appStatus
	 * @throws Exception
	 */
	public void updateAppStatus(long appId,long appStatus) throws Exception;

	/**
	 * ���������ʶ�����������¼������ϸ -����
	 * 
	 * @param appId
	 *            �����ʶ
	 * @return java.util.List �����¼������ϸ AppOperateDtl -����
	 */
	public List findAppOperateDtlsByAppId(long appId) throws Exception;
	
	public AppTypeDTO findAppTypeDTOByAppTypeId(long appTypeId) ;
	
	/**
	 * ���������Ų�ѯ������Ϣ AppAndAppTypeDTO
	 * 
	 * @param appCode
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppCode(String appCode)throws Exception;
	
	
	/**
	 * ���������ʶ��ѯ������Ϣ AppAndAppTypeDTO
	 * @param appId
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppId(long appId) throws Exception;
	/** ���������ʶ,����[�����¼������ϸ���ʶ���];�Ƿ���[��������ϸ����]���� �µ�����. */
	public List findAppOperateDtlDTOsBeyondAppId(long appId) throws Exception;
	/**
	 * ����AppAndAppTypeDTO�б�
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO findAppAndAppTypeDTOsByQueryDTO(AppQueryDTO queryDTO,PageBean pageBean) throws Exception;
}
