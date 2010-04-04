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
 * 受理服务类
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/busi/busiAppService" id="busiAppService"
 *             homepackage="com.huashu.boss.busi.ejb"
 * 
 */
public interface BusiAppService {

	/**
	 * 保存受理历史记录
	 * 
	 * @param appRequest
	 *            受理记录类型
	 * 
	 * @return AppDTO 保存后的受理记录对象
	 */
	public AppDTO saveApp(AppRequest appRequest) throws Exception;

	/**
	 * 创建受理，并生成list中的历史对象
	 * 
	 * @param appRequest
	 *            受理记录请求
	 * @param baseService
	 *            实现保存历史对象的类
	 * @return AppResponse 受理记录返回对象，包含当前对象的信息
	 */
	public List batchSaveHisObject(AppRequest appRequest, List objectList,
			BusiBaseService baseService) throws Exception;

	/**
	 * 设置当前对象的历史属性值
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param object
	 *            当前对象
	 * @return Object 设置后的对象
	 */
	public Object settingCurrentObject(AppRequest appRequest, Object object) throws Exception;

	/**
	 * 设置当前对象的历史属性值
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param object
	 *            当前对象
	 * @return Object 设置后并保存后的对象
	 */
	public Object saveHistoryObject(AppRequest appRequest, Object object,
			BusiBaseService baseService) throws Exception;

	/**
	 * 
	 * 设置当前对象的历史属性值
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param object
	 *            当前对象
	 * @return Object 设置后并保存后的对象
	 */
	public Object saveAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) throws Exception;

	/**
	 * 
	 * 设置当前需要删除的对象历史属性值
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param object
	 *            当前对象
	 * @return Object 设置后并保存后的对象
	 */
	public Object deleteAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) throws Exception;

	/**
	 * 根据受理标识查询受理信息
	 * 
	 * @param appId
	 * @return 受理记录
	 */
	public AppDTO getAppDTOByAppId(long appId) throws Exception;

	/**
	 * 根据受理编号查询受理信息
	 * 
	 * @param appCode
	 * @return 受理记录
	 */
	public AppDTO findAppDTOByAppCode(String appCode) throws Exception;

	/**
	 * 根据受理类型编码查询受理类型
	 * 
	 * @param appAction
	 * @return 受理类型
	 */
	public AppTypeDTO findAppTypeDTOByAppAction(String appAction) throws Exception;

	/**
	 * 根据业务类型查找受理类型
	 * 
	 * @param category
	 * @return
	 */
	public List findAppTypeByCategory(long category) throws Exception;

	/**
	 * 根据受理类型标识查找受理类型
	 */
	public List findAppTypeByIds(Long[] appTypeIds) throws Exception;
	
	/**
	 * 过虑受理标识
	 * @param appIds
	 * @return
	 * @throws Exception
	 */
	public long filterCSLatestAppId(Long[] appIds) throws Exception;
	
	
	/**
	 * 修改受理状态
	 * @param appId
	 * @param appStatus
	 * @throws Exception
	 */
	public void updateAppStatus(long appId,long appStatus) throws Exception;

	/**
	 * 根据受理标识，返回受理记录操作明细 -集合
	 * 
	 * @param appId
	 *            受理标识
	 * @return java.util.List 受理记录操作明细 AppOperateDtl -集合
	 */
	public List findAppOperateDtlsByAppId(long appId) throws Exception;
	
	public AppTypeDTO findAppTypeDTOByAppTypeId(long appTypeId) ;
	
	/**
	 * 根据受理编号查询受理信息 AppAndAppTypeDTO
	 * 
	 * @param appCode
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppCode(String appCode)throws Exception;
	
	
	/**
	 * 根据受理标识查询受理信息 AppAndAppTypeDTO
	 * @param appId
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppId(long appId) throws Exception;
	/** 根据受理标识,查找[受理记录操作明细访问对象];是否有[被操作明细对象]接受 新的受理. */
	public List findAppOperateDtlDTOsBeyondAppId(long appId) throws Exception;
	/**
	 * 返回AppAndAppTypeDTO列表
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO findAppAndAppTypeDTOsByQueryDTO(AppQueryDTO queryDTO,PageBean pageBean) throws Exception;
}
