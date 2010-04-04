package com.ft.busi.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.ft.busi.common.Constants;
import com.ft.busi.common.DTOEntityTranlate;
import com.ft.busi.common.EntityBeanManager;
import com.ft.busi.common.SpringBeanUtils;
import com.ft.busi.dto.AppAndAppTypeDTO;
import com.ft.busi.dto.AppDTO;
import com.ft.busi.dto.AppQueryDTO;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.dto.AppTypeDTO;
import com.ft.busi.dto.ResultDTO;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.AppDao;
import com.ft.busi.sm.impl.dao.AppOperateDtlDao;
import com.ft.busi.sm.impl.dao.AppTypeDao;
import com.ft.busi.sm.service.BusiCodeGenerateService;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.commons.page.PageBean;
import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.hibernate.callback.FindByNotInIdsCallback;
import com.ft.sm.entity.App;
import com.ft.sm.entity.AppOperateDtl;
import com.ft.sm.entity.AppType;
import com.ft.utils.DBUtils;

/**
 * 提供受理记录的查询和维护
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="busiAppService" parent="transactionProxyFactoryBean"
 *                  target="appServiceImpl"
 * 
 * @spring.bean id="appServiceImpl"
 */
public class AppServiceImpl implements BusiAppService {

	Log logger = LogFactory.getLog(AppServiceImpl.class);

	/**
	 * 正常
	 */
	public final static long APP_STATUS_NORMAL = 0;

	/**
	 * 撤销
	 */
	public final static long APP_STATUS_CACEL = 1;

	/**
	 * 返销
	 */
	public final static long APP_STATUS_REVERSE = 2;

	/**
	 * 受理记录数据库访问对象
	 */
	private AppDao appDao;

	/**
	 * 受理记录操作明细访问对象
	 */
	private AppOperateDtlDao appOperateDtlDao;

	/**
	 * 受理类型记录数据库访问对象
	 */
	private AppTypeDao appTypeDao;
	
	

	/**
	 * 
	 * 构造函数
	 */
	public AppServiceImpl() {

	}

	/**
	 * 批量保存历史对象
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param objectList
	 *            需要保存历史对象的列表
	 * @param baseService
	 *            保存历史对象和查找对象的实现方法
	 * @return objectList 当前传入的对象
	 */
	public List batchSaveHisObject(AppRequest appRequest, List objectList,
			BusiBaseService baseService) {
		// 初始化返回对象
		List<Object> result = new ArrayList<Object>();

		AppDTO appDto = null;

		if (appRequest.getAppId() <= 0) {
			appDto = saveApp(appRequest);
			appRequest.setAppId(appDto.getApp().getAppId());
		} else {
			appDto = new AppDTO(this.appDao.getById(new Long(appRequest
					.getAppId())));
		}

		// 处理新增或修改的对象
		if (objectList != null) {
			for (Iterator ite = objectList.iterator(); ite.hasNext();) {
				result.add(this.saveAndsettingHistoryObject(appRequest, ite
						.next(), baseService));

			}
		}

		return result;
	}
	
	public String getAppCode(long category){
		BusiCodeGenerateService codeGenerateService=(BusiCodeGenerateService)SpringBeanUtils.getBean("busiCodeGenerateService");
		
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		paramMap.put("category", "" + category);
		paramMap.put("yyyymmdd", "" + formatter.format(new Date()));
		return codeGenerateService.generateBusiCode("AppCode", paramMap);
		
	}

	/**
	 * 创建一个受理记录
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @return AppDTO 受理记录
	 * 
	 */
	public AppDTO saveApp(AppRequest appRequest) {
		AppType appType = this.appTypeDao.findByAppAction(appRequest
				.getAppAction());
		if (appType == null) {
			CommonRuntimeException exception = new CommonRuntimeException(
					"busi.error.appaction.notexist");
			exception.setParams(new Object[] { appRequest.getAppAction() });

			throw exception;
		}

		App app = new App();
		/*if (appRequest.getAppCode() != null && appRequest.getAppCode().length() > 0)
			app.setAppCode(appRequest.getAppCode());
		else
		{*/
		app.setAppCode(getAppCode(appType.getCategory()));
		
			
		
		app.setAppAction(appRequest.getAppAction());
		if (appRequest.getOfficeOrgId() > 0)
			app.setLoginOrgId(appRequest.getOfficeOrgId());
		else
			app.setLoginOrgId(appRequest.getLoginOrgId());
		app.setRecOrgId(appRequest.getRecOrgId());
		app.setCorpOrgId(appRequest.getCorpOrgId());
		app.setOperatorId(appRequest.getOperatorId());
		app.setOrgId(appRequest.getOrgId());
		app.setAppTime(DBUtils.getSysDate(this.appDao.getSessionFactory()));
		app.setNotes(appRequest.getNotes());
		app.setRelatAppId(appRequest.getRelatAppId());
		
		logger.debug("生成app 的信息"+ToStringBuilder.reflectionToString(app,ToStringStyle.MULTI_LINE_STYLE));
		this.appDao.save(app);
		appRequest.setAppId(app.getAppId());
		//appRequest.setAppCode(app.getAppCode());
		return new AppDTO(app);

	}

	/**
	 * 设置当前对象的历史属性值
	 * 
	 * @param appRequest
	 *            请求参数对象
	 * @param object
	 *            当前对象
	 * @return Object 设置后的对象
	 */
	public Object settingCurrentPoJo(AppRequest appRequest, Object object,
			String[] propertys) {

		// 获取对象的属性列表
		Map descriptMap = EntityBeanManager.getOjbectPropertyDescriptors(
				object, propertys);
		// 对象不存在需要设置的属性,直接返回
		if (descriptMap == null || descriptMap.isEmpty())
			return object;

		Date date = DBUtils.getSysDate(this.appDao.getSessionFactory());
		// 循环解析属性数组
		for (int i = 0; i < propertys.length; i++) {
			PropertyDescriptor propDescript = (PropertyDescriptor) descriptMap
					.get(propertys[i]);
			// 分公司和记录归属标识为非必填属性，其他属性必须存在，否则抛出错误
			if (propDescript == null)
				continue;
			/*
			 * && !propertys[i].equals(Constants.PROP_CORP_ORG_ID) &&
			 * !propertys[i].equals(Constants.PROP_REC_ORG_ID)) throw new
			 * NoSuchFieldException("对象未存在属性：" + propertys[i]);
			 */

			// 设置当前对象的属性值
			object = setObjectValue(appRequest, object, propDescript, date);
		}
		return object;
	}

	/**
	 * 设置对象的属性
	 * 
	 * @param appRequest
	 *            请求参数
	 * @param object
	 *            请求对象
	 * @param propDescript
	 *            对象属性
	 * @param date
	 *            当前数据库时间
	 * @return Object 设置后的对象
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 */
	private Object setObjectValue(AppRequest appRequest, Object object,
			PropertyDescriptor propDescript, Date sydDate) {
		if (propDescript == null)
			return object;

		// 属性的值
		Object value = null;
		try {
			if (propDescript.getName().equals(Constants.PROP_CREATE_DATE)) {
				// 如果创建时间为空，则设置
				Serializable identifier = EntityBeanManager.getObjectIdentifier(object,
						this.appDao.getSessionFactory());
			
				if (((Long) identifier).longValue() == 0) {// 标识符对应的ID不为0
					propDescript.getWriteMethod().invoke(object,
							new Object[] { sydDate });
				}

				
			} else if (propDescript.getName().equals(Constants.PROP_VALID_DATE)) {
				// 设置有效时间
				// if (propDescript.getReadMethod().invoke(object, null) ==
				// null)
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });
			} else if (propDescript.getName()
					.equals(Constants.PROP_UPDATE_DATE)) {
				// 设置修改时间
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });
			} else if (propDescript.getName()
					.equals(Constants.PROP_EXPIRE_DATE)) {
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });

			} else if (propDescript.getName().equals(Constants.PROP_APP_ID)) {
				// 设置当前记录的受理标识
				propDescript.getWriteMethod().invoke(object,
						new Object[] { new Long(appRequest.getAppId()) });

			} else if (propDescript.getName()
					.equals(Constants.PROP_OPERATOR_ID)) {
				// 如果创建操作员为空或等于0，则设置
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getOperatorId()) });
				}

			} else if (propDescript.getName().equals(Constants.PROP_ORG_ID)) {
				// 如果操作员所在营业厅为空，则设置
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod().invoke(object,
							new Object[] { new Long(appRequest.getOrgId()) });
				}

			} else if (propDescript.getName().equals(
					Constants.PROP_OFFICE_ORG_ID)) {
				// 如果开户营业厅为空，则设置
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getOfficeOrgId()) });
				}

			} else if (propDescript.getName().equals(
					Constants.PROP_LOGIN_ORG_ID)) {
				// 如果操作员登陆营业厅为空，则设置
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getLoginOrgId()) });
				}
			} else if (propDescript.getName()
					.equals(Constants.PROP_CORP_ORG_ID)) {
				// 分公司标识大于0，需要修改分公司标识
				// if (appRequest.getCorpOrgId() > 0) {
				value = propDescript.getReadMethod().invoke(object);
				// 当记录的记录归属表示为空或者等于0的时候进行设置
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getCorpOrgId()) });
				}

				// }

			} else if (propDescript.getName().equals(Constants.PROP_REC_ORG_ID)) {
				// 记录归属标识大于0，需要修改分公司标识

				// if (appRequest.getRecOrgId() > 0) {
				value = propDescript.getReadMethod().invoke(object);
				// 当记录的分公司归属表示为空或者等于0的时候进行设置
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getRecOrgId()) });
				}

				// }
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return object;
	}

	/** 保存受理记录操作的明细记录 */
	private void saveAppOprateDtl(long identifierId, String objClassName,
			long newAppId, long operatorType) {
		// 记录受理操作明细
		AppOperateDtl operateDtl = new AppOperateDtl();
		operateDtl.setAppId(newAppId);
		// long operatorType=Constants.OPERATOR_TYPE_ADD;
		try {
			operateDtl.setObjectId(identifierId);
		} catch (java.lang.ClassCastException ce) {
			operateDtl.setObjectId(0);
			ce.printStackTrace();
		}
		// String objClassName=object.getClass().getName();
		operateDtl.setClassName(objClassName);
		/*
		 * if(objClassName.endsWith(Constants.HIS_OBJECT_SUFFIX)){
		 * operatorType=Constants.OPERATOR_TYPE_MODIFY; }
		 */
		operateDtl.setOperatorType(operatorType);
		// 保存受理明细
		this.appOperateDtlDao.saveOrUpdate(operateDtl);
	}

	/**
	 * 设置历史对象
	 * 
	 * @param object
	 *            当前对象
	 * @param newAppId
	 *            当前受理标识
	 * @return 返回历史对象
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Object wirteHistoryObject(Class classHis, Object object,
			AppRequest appRequest, Serializable identifier) {
		// 实例化历史对象
		Object objectHis = null;
		try {
			objectHis = classHis.newInstance();

			if (objectHis == null)
				return object;

			// 拷贝当前对象到历史对象中        <----
			PropertyUtils.copyProperties(objectHis, object);
			// 设置过期日期
			PropertyDescriptor desriptor = PropertyUtils.getPropertyDescriptor(
					objectHis, Constants.PROP_EXPIRE_DATE);
			if(desriptor==null)
				return object;
			desriptor.getWriteMethod().invoke(
					objectHis,
					new Object[] { DBUtils.getSysDate(this.appDao
							.getSessionFactory()) });
			// 设置新受理标识
			desriptor = PropertyUtils.getPropertyDescriptor(objectHis,
					Constants.PROP_NEW_APP_ID);
			desriptor.getWriteMethod().invoke(objectHis,
					new Object[] { new Long(appRequest.getAppId()) });
			// 保存受理记录操作明细
			long operatorType = Constants.OPERATOR_TYPE_MODIFY;
			if (appRequest.getOperatorType() != null) {
				operatorType = appRequest.getOperatorType().longValue();
			}
			this.saveAppOprateDtl(((Long) identifier).longValue(), object
					.getClass().getName(), appRequest.getAppId(), operatorType);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return objectHis;
	}

	/**
	 * 根据受理编号查询受理信息
	 * 
	 * @param appCode
	 * @return 受理记录
	 */
	public AppDTO findAppDTOByAppCode(String appCode) {
		App app = this.appDao.findByAppCode(appCode);
		return null != app ? new AppDTO(app) : null;
	}

	/**
	 * 根据受理编号查询受理信息 AppAndAppTypeDTO
	 * 
	 * @param appCode
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppCode(String appCode) {
		StringBuffer hql = new StringBuffer(
				"select new com.huashu.boss.busi.dto").append(
				".AppAndAppTypeDTO(ap,apt) from App ap,AppType apt").append(
				" where ap.appAction=apt.appAction").append(
				" and ap.appCode= ?");
		List result = this.appDao.queryObj(hql.toString(),
				new Object[] { appCode });

		return (null != result && result.size() > 0) ? (AppAndAppTypeDTO) result
				.get(0)
				: null;
	}

	/**
	 * 根据受理标识查询受理信息 AppAndAppTypeDTO
	 * 
	 * @param appId
	 * @return AppAndAppTypeDTO
	 */
	public AppAndAppTypeDTO findAppAndAppTypeDTOByAppId(long appId) {
		StringBuffer hql = new StringBuffer(
				"select new com.huashu.boss.busi.dto").append(
				".AppAndAppTypeDTO(ap,apt) from App ap,AppType apt").append(
				" where ap.appAction=apt.appAction").append(" and ap.appId= ?");
		List result = this.appDao.queryObj(hql.toString(),
				new Object[] { new Long(appId) });

		return (null != result && result.size() > 0) ? (AppAndAppTypeDTO) result
				.get(0)
				: null;
	}

	/**
	 * 根据受理类型编码查询受理类型
	 * 
	 * @param appAction
	 * @return 受理类型
	 */
	public AppTypeDTO findAppTypeDTOByAppTypeId(long appTypeId) {
		AppType appType = this.appTypeDao.getById(new Long(appTypeId));
		return null != appType ? new AppTypeDTO(appType) : null;
	}
	
	public AppTypeDTO findAppTypeDTOByAppAction(String appAction) {
		AppType appType = this.appTypeDao.findByAppAction(appAction);
		return null != appType ? new AppTypeDTO(appType) : null;
	}


	/**
	 * 根据受理类型类别查找受理类型
	 * 
	 * @param category 0 为所有
	 *            受理类型类别
	 * @return 受理类型列表 不存在为空
	 */
	public List findAppTypeByCategory(long category) {
		return DTOEntityTranlate.entityArray2DTOArray(this.appTypeDao
				.findByCategory(category));

	}

	/**
	 * 根据受理标识查询受理信息
	 * 
	 * @param appId
	 * @return 受理记录
	 */
	public AppDTO getAppDTOByAppId(long appId) {
		App app = this.appDao.getById(new Long(appId));
		return null != app ? new AppDTO(app) : null;
	}

	/**
	 * @return Returns the appDao.
	 */
	public AppDao getAppDao() {
		return appDao;
	}

	/**
	 * @spring.property ref="appDao"
	 * @param appDao
	 *            The appDao to set.
	 */
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}

	/**
	 * 
	 * @return Returns the appTypeDao.
	 */
	public AppTypeDao getAppTypeDao() {
		return appTypeDao;
	}

	/**
	 * @spring.property ref="appTypeDao"
	 * @param appTypeDao
	 *            The appTypeDao to set.
	 */
	public void setAppTypeDao(AppTypeDao appTypeDao) {
		this.appTypeDao = appTypeDao;
	}

	/**
	 * @return Returns the appOperateDtlDao.
	 */
	public AppOperateDtlDao getAppOperateDtlDao() {
		return appOperateDtlDao;
	}

	/**
	 * @spring.property ref="appOperateDtlDao"
	 * @param appOperateDtlDao
	 *            The appOperateDtlDao to set.
	 */
	public void setAppOperateDtlDao(AppOperateDtlDao appOperateDtlDao) {
		this.appOperateDtlDao = appOperateDtlDao;
	}

	/**
	 * 保存单个对象的历史
	 * 
	 * @param appRequest
	 *            请求类型
	 * @param object
	 *            记录的对象
	 * @param baseService
	 *            对象保存的服务
	 * @return 传入的对象
	 * @throws Exception
	 */
	private Object savePoJoHistory(AppRequest appRequest, Object object,
			BusiBaseService baseService) {

			String className = object.getClass().getName()
				+ Constants.HIS_OBJECT_SUFFIX;
		// 查找历史对象类
		Class classHis = null;
		try {
			classHis = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// 历史类没有找到，则不处理
			return object;
		}
		
//		 获取对象标识符
		Object destObject = null;
		try {
			destObject = object.getClass().newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BeanUtils.copyProperties(object,destObject);

		Object dbObject = null;
		// 获取对象标识符
		Serializable identifier = EntityBeanManager.getObjectIdentifier(object,
				this.appDao.getSessionFactory());
		if (identifier == null)
			throw new CommonRuntimeException("无法获取对象的标识符！"
					+ object.getClass().getName());
		if (((Long) identifier).longValue() != 0) {// 标识符对应的ID不为0
			// 从数据库中载入对象
			try {
				dbObject = baseService.getEntityObject(object.getClass(),
						identifier);
				if(dbObject!=null)
				this.appDao.getHibernateTemplate().refresh(dbObject);
				else
					identifier=new Long(0);
			} catch (Exception e) {
				e.printStackTrace();
				throw new CommonRuntimeException(e);

			}
		}else
			appRequest.setOperatorType(new Long(Constants.OPERATOR_TYPE_ADD));

		try {
			// 数据库中不存在，表明始新建对象，无需记录历史;但需要记录受理记录操作明细
			if (dbObject == null) {
				// 保存受理记录操作明细
				if (//appRequest.isDirectSaveUpdate()&& 
						((Long) identifier).longValue() == 0) {// 新建对象，
					// 直接通过busi保存对象
					Object saveObj = this.settingCurrentObject(appRequest,
							object);
					baseService.saveHisObject(saveObj);
					Serializable newIdentifier = EntityBeanManager
							.getObjectIdentifier(saveObj, this.appDao
									.getSessionFactory());
					this.saveAppOprateDtl(((Long) newIdentifier).longValue(),
							object.getClass().getName(), appRequest.getAppId(),
							Constants.OPERATOR_TYPE_ADD);
				}
				return object;
			} else {
				appRequest.setDirectSaveUpdate(false);
			}
			// 将当前数据库对象记入历史
            Object hisObj=wirteHistoryObject(classHis, dbObject,
					appRequest, identifier);
			baseService.saveHisObject(hisObj);
			// / --->
			String[] ignorePropertys = { Constants.PROP_CREATE_DATE,
					Constants.PROP_APP_ID, Constants.PROP_OFFICE_ORG_ID,
					Constants.PROP_OPERATOR_ID, Constants.PROP_ORG_ID,
					Constants.PROP_VALID_DATE };
			// 设置dbObject的属性
			BeanUtils.copyProperties(destObject, dbObject, ignorePropertys);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return dbObject;

	}
	
/*	private String getEntityPrefix(String clazzName) {
		String entityPrefix = null;
		if (clazzName != null && (!clazzName.trim().equals(""))) {
			int lastIndex = clazzName.lastIndexOf(".");
			if (lastIndex > -1)
				entityPrefix = clazzName.substring(lastIndex + 1);
			entityPrefix = entityPrefix.toLowerCase();
			this.logger.debug(".....entityPrefix=" + entityPrefix);
		}
		return entityPrefix;
	}*/
	
	/*private Object findObjectBy(String clazzName, String identifierName,
			Serializable identifier) {
		StringBuffer queryStr = new StringBuffer();
		String entityPrefix = this.getEntityPrefix(clazzName);
		queryStr.append(" from ").append(clazzName).append(" ").append(
				entityPrefix).append(" where ").append(entityPrefix)
				.append(".").append(
						identifierName).append("=?");

		List result = this.appDao.queryObj(
				queryStr.toString(),
				new Object[] {identifier });
		if (result == null || result.isEmpty()) {
			return null;
		} else {
			this.logger.debug(".....size=" + result.size());
			return result.get(0);
		}
	}*/

	/**
	 * 
	 * 保存并设置单个对象的历史
	 * 
	 * @param appRequest
	 *            请求类型
	 * @param object
	 *            记录的对象
	 * @param baseService
	 *            对象保存的服务
	 * @return 传入的对象
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 */
	public Object saveAndsettingPoJoHistory(AppRequest appRequest,
			Object object, BusiBaseService baseService) {
		Object saveObject = savePoJoHistory(appRequest, object, baseService);
		return this.settingCurrentObject(appRequest, saveObject);
	}

	/**
	 * 
	 * 保存单个对象的历史
	 * 
	 * @param appRequest
	 *            请求类型
	 * @param object
	 *            记录的对象
	 * @param baseService
	 *            对象保存的服务
	 * @return 传入的对象
	 */
	public Object saveHistoryObject(AppRequest appRequest, Object object,
			BusiBaseService baseService) {
		// TODO Auto-generated method stub
		if (object == null)
			return object;

		if (object instanceof List) {
			List<Object> result = new ArrayList<Object>();
			List objectList = (List) object;
			for (Iterator ite = objectList.iterator(); ite.hasNext();) {
				result
						.add(savePoJoHistory(appRequest, ite.next(),
								baseService));
			}
			return result;

		} else {
			return savePoJoHistory(appRequest, object, baseService);
		}

	}

	/**
	 * 
	 * 保存并设置单个对象的历史
	 * 
	 * @param appRequest
	 *            请求类型
	 * @param object
	 *            记录的对象
	 * @param baseService
	 *            对象保存的服务
	 * @return 传入的对象
	 */
	public Object saveAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) {
		// TODO Auto-generated method stub
		if (object == null)
			return object;

		if (object instanceof List) {
			List<Object> result = new ArrayList<Object>();
			List objectList = (List) object;
			for (Iterator ite = objectList.iterator(); ite.hasNext();) {
				result.add(saveAndsettingPoJoHistory(appRequest, ite.next(),
						baseService));
			}
			return result;

		} else {
			return saveAndsettingPoJoHistory(appRequest, object, baseService);
		}
	}

	/**
	 * 设置当前对象的历史字段属性值，并返回对象
	 * 
	 * @param appRequest
	 *            请求参数
	 * @param object
	 *            设置的对象
	 * @return 返回设置后的对象
	 */
	public Object settingCurrentObject(AppRequest appRequest, Object object) {
		// 初始化数组，设置属性
		String[] propertys = { Constants.PROP_CREATE_DATE,
				Constants.PROP_UPDATE_DATE, Constants.PROP_APP_ID,
				Constants.PROP_VALID_DATE, Constants.PROP_ORG_ID,
				Constants.PROP_OPERATOR_ID, Constants.PROP_LOGIN_ORG_ID,
				Constants.PROP_CORP_ORG_ID, Constants.PROP_REC_ORG_ID,
				Constants.PROP_OFFICE_ORG_ID };
		if (object == null)
			return object;
		if (object instanceof List) {
			List<Object> result = new ArrayList<Object>();
			List objectList = (List) object;
			for (Iterator ite = objectList.iterator(); ite.hasNext();) {
				result
						.add(settingCurrentPoJo(appRequest, ite.next(),
								propertys));
			}
			return result;
		} else {
			return settingCurrentPoJo(appRequest, object, propertys);
		}
	}

	/**
	 * 记录删除对象的历史并设置对象的过期日期
	 * 
	 * @param appRequest
	 *            请求参数
	 * @param object
	 *            设置的对象
	 * @param baseService
	 *            保留历史对象的服务
	 * @return 返回设置的对象
	 */
	public Object deleteAndsettingHistoryObject(AppRequest appRequest,
			Object object, BusiBaseService baseService) {
		// TODO Auto-generated method stub
		appRequest.setOperatorType(new Long(Constants.OPERATOR_TYPE_DELETE));
		Object saveObject = savePoJoHistory(appRequest, object, baseService);
		String[] propertys = { Constants.PROP_EXPIRE_DATE ,Constants.PROP_APP_ID};
		if (saveObject == null)
			return object;
		if (saveObject instanceof List) {
			List<Object> result = new ArrayList<Object>();
			List objectList = (List) saveObject;
			for (Iterator ite = objectList.iterator(); ite.hasNext();) {
				result
						.add(settingCurrentPoJo(appRequest, ite.next(),
								propertys));
			}
			return result;
		} else {
			return settingCurrentPoJo(appRequest, saveObject, propertys);
		}
	}

	/**
	 * 根据受理标识，返回受理记录操作明细 -集合
	 * 
	 * @param appId
	 *            受理标识
	 * @return java.util.List 受理记录操作明细 AppOperateDtl -集合
	 */
	public List findAppOperateDtlsByAppId(long appId) {
		return DTOEntityTranlate.entityArray2DTOArray(this.appOperateDtlDao
				.findAppOperateDtlsByAppId(appId));
	}

	/**
	 * 根据受理类型标识查找受理类型
	 * 
	 * @param appTypeIds
	 *            受理类型id列表
	 * @return 返回AppTypeDTO
	 */
	public List findAppTypeByIds(Long[] appTypeIds) {
		// TODO Auto-generated method stub
		List result = this.appTypeDao.findByAppTypeIds(appTypeIds);
		return DTOEntityTranlate.entityArray2DTOArray(result);
	}

	/**
	 * 修改受理状态
	 * 
	 * @param appId
	 * @param appStatus
	 * @throws Exception
	 */
	public void updateAppStatus(long appId, long appStatus) throws Exception {
		// TODO Auto-generated method stub
		if (!(appStatus == APP_STATUS_CACEL || appStatus == APP_STATUS_REVERSE || appStatus == APP_STATUS_NORMAL)) {
			throw new IllegalArgumentException();
		}
		App app = this.appDao.getById(new Long(appId));
		if (app != null) {
			// app.setAppStatus(appStatus);
			appDao.saveOrUpdate(app);
		}
	}

	/** 根据受理标识,查找[受理记录操作明细访问对象];是否有[被操作明细对象]接受 新的受理. */
	public List findAppOperateDtlDTOsBeyondAppId(long appId) {
		List appOperateDtls = this.appOperateDtlDao
				.findAppOperateDtlsByAppId(appId);

		for (Iterator appOpDtlIter = appOperateDtls.iterator(); appOpDtlIter
				.hasNext();) {
			AppOperateDtl appOperateDtl = (AppOperateDtl) appOpDtlIter.next();
			List newAppOperateDtls = this.appOperateDtlDao
					.findAppOperateDtlsByObjectIdBeyondAppId(appOperateDtl
							.getClassName(), appOperateDtl.getObjectId(), appId);
			if (newAppOperateDtls != null && (!newAppOperateDtls.isEmpty())) {
				return DTOEntityTranlate
						.entityArray2DTOArray(newAppOperateDtls);
			}
		}
		return new ArrayList();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO findAppAndAppTypeDTOsByQueryDTO(AppQueryDTO queryDTO,PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		boolean bFilterOrg = false;
		StringBuffer hql = new StringBuffer(
		"select new com.huashu.boss.busi.dto").append(
		".AppAndAppTypeDTO(ap,apt) from ").append(
		" AppType apt,App ap").append(
		" where apt.appAction=ap.appAction");
		
		if(queryDTO.getAppCode()!=null && queryDTO.getAppCode().length()>0){
			hql.append(" and ap.appCode ='").append(queryDTO.getAppCode()).append("'");
		}else{
			if(queryDTO.getLoginOrgId()>0){//按登陆组织过滤一下受理查询
				hql.append(" and ap.loginOrgId =").append(queryDTO.getLoginOrgId());
				bFilterOrg =true;
			}
			
			if(queryDTO.getCategory()>0){
				hql.append(" and apt.category =").append(queryDTO.getCategory());
			}
			
			if (!(queryDTO.getAppAction() == null || queryDTO.getAppAction().equals("0") || queryDTO.getAppAction().length() == 0)) {
				hql.append(" and ap.appAction='" + queryDTO.getAppAction() + "'");
			}else if((queryDTO.getAppActions()!=null&&queryDTO.getAppActions().length>0)){
				hql.append(" and ap.appAction in ").append(
                        FindByNotInIdsCallback.joinKeys(queryDTO.getAppActions()));
			}
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if (queryDTO.getStartDate() != null) {
				hql.append(" and ap.appTime>=to_date('"
						+ dft.format(queryDTO.getStartDate())
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			if (queryDTO.getEndDate() != null) {
				hql.append(" and ap.appTime<=to_date('"
						+ dft.format(queryDTO.getEndDate())
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			
			if(queryDTO.getRecOrgId()>0){
				hql.append(" and ap.recOrgId =").append(queryDTO.getRecOrgId());
				//bFilterOrg =true;
			}
			
		
			if(queryDTO.getOperatorId()>0){
				hql.append(" and ap.operatorId =").append(queryDTO.getOperatorId());
			}
			if(queryDTO.getAppIds()!=null && queryDTO.getAppIds().size()>0){
				Long[] appIds=(Long[]) queryDTO.getAppIds().toArray(new Long[0]);
				
				hql.append(" and ap.appId in ").append(
						FindByNotInIdsCallback.joinKeys(appIds));
			}
			//如果没有按组织过虑，则直接按可访问的组织来过虑
			if(!bFilterOrg && queryDTO.getLoginOrgIds()!=null && queryDTO.getLoginOrgIds().length>0){
				hql.append(" and ap.loginOrgId in ").append(FindByIdsCallback.joinKeys(queryDTO.getLoginOrgIds()));
			}
			
		}
		hql.append(" ORDER BY ap.appTime DESC ");
		
		List result=this.appDao.getResultByPageBean(pageBean, hql.toString());
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setPageBean(pageBean);
		resultDTO.setResult(result);
		return resultDTO;

	}

	/**
	 * 
	 */
	public long filterCSLatestAppId(Long[] appIds) throws Exception {
		// TODO Auto-generated method stub
		return this.appDao.filterLatestAppId(appIds);
	}

}
