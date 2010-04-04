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
 * �ṩ�����¼�Ĳ�ѯ��ά��
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
	 * ����
	 */
	public final static long APP_STATUS_NORMAL = 0;

	/**
	 * ����
	 */
	public final static long APP_STATUS_CACEL = 1;

	/**
	 * ����
	 */
	public final static long APP_STATUS_REVERSE = 2;

	/**
	 * �����¼���ݿ���ʶ���
	 */
	private AppDao appDao;

	/**
	 * �����¼������ϸ���ʶ���
	 */
	private AppOperateDtlDao appOperateDtlDao;

	/**
	 * �������ͼ�¼���ݿ���ʶ���
	 */
	private AppTypeDao appTypeDao;
	
	

	/**
	 * 
	 * ���캯��
	 */
	public AppServiceImpl() {

	}

	/**
	 * ����������ʷ����
	 * 
	 * @param appRequest
	 *            �����������
	 * @param objectList
	 *            ��Ҫ������ʷ������б�
	 * @param baseService
	 *            ������ʷ����Ͳ��Ҷ����ʵ�ַ���
	 * @return objectList ��ǰ����Ķ���
	 */
	public List batchSaveHisObject(AppRequest appRequest, List objectList,
			BusiBaseService baseService) {
		// ��ʼ�����ض���
		List<Object> result = new ArrayList<Object>();

		AppDTO appDto = null;

		if (appRequest.getAppId() <= 0) {
			appDto = saveApp(appRequest);
			appRequest.setAppId(appDto.getApp().getAppId());
		} else {
			appDto = new AppDTO(this.appDao.getById(new Long(appRequest
					.getAppId())));
		}

		// �����������޸ĵĶ���
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
	 * ����һ�������¼
	 * 
	 * @param appRequest
	 *            �����������
	 * @return AppDTO �����¼
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
		
		logger.debug("����app ����Ϣ"+ToStringBuilder.reflectionToString(app,ToStringStyle.MULTI_LINE_STYLE));
		this.appDao.save(app);
		appRequest.setAppId(app.getAppId());
		//appRequest.setAppCode(app.getAppCode());
		return new AppDTO(app);

	}

	/**
	 * ���õ�ǰ�������ʷ����ֵ
	 * 
	 * @param appRequest
	 *            �����������
	 * @param object
	 *            ��ǰ����
	 * @return Object ���ú�Ķ���
	 */
	public Object settingCurrentPoJo(AppRequest appRequest, Object object,
			String[] propertys) {

		// ��ȡ����������б�
		Map descriptMap = EntityBeanManager.getOjbectPropertyDescriptors(
				object, propertys);
		// ���󲻴�����Ҫ���õ�����,ֱ�ӷ���
		if (descriptMap == null || descriptMap.isEmpty())
			return object;

		Date date = DBUtils.getSysDate(this.appDao.getSessionFactory());
		// ѭ��������������
		for (int i = 0; i < propertys.length; i++) {
			PropertyDescriptor propDescript = (PropertyDescriptor) descriptMap
					.get(propertys[i]);
			// �ֹ�˾�ͼ�¼������ʶΪ�Ǳ������ԣ��������Ա�����ڣ������׳�����
			if (propDescript == null)
				continue;
			/*
			 * && !propertys[i].equals(Constants.PROP_CORP_ORG_ID) &&
			 * !propertys[i].equals(Constants.PROP_REC_ORG_ID)) throw new
			 * NoSuchFieldException("����δ�������ԣ�" + propertys[i]);
			 */

			// ���õ�ǰ���������ֵ
			object = setObjectValue(appRequest, object, propDescript, date);
		}
		return object;
	}

	/**
	 * ���ö��������
	 * 
	 * @param appRequest
	 *            �������
	 * @param object
	 *            �������
	 * @param propDescript
	 *            ��������
	 * @param date
	 *            ��ǰ���ݿ�ʱ��
	 * @return Object ���ú�Ķ���
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 */
	private Object setObjectValue(AppRequest appRequest, Object object,
			PropertyDescriptor propDescript, Date sydDate) {
		if (propDescript == null)
			return object;

		// ���Ե�ֵ
		Object value = null;
		try {
			if (propDescript.getName().equals(Constants.PROP_CREATE_DATE)) {
				// �������ʱ��Ϊ�գ�������
				Serializable identifier = EntityBeanManager.getObjectIdentifier(object,
						this.appDao.getSessionFactory());
			
				if (((Long) identifier).longValue() == 0) {// ��ʶ����Ӧ��ID��Ϊ0
					propDescript.getWriteMethod().invoke(object,
							new Object[] { sydDate });
				}

				
			} else if (propDescript.getName().equals(Constants.PROP_VALID_DATE)) {
				// ������Чʱ��
				// if (propDescript.getReadMethod().invoke(object, null) ==
				// null)
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });
			} else if (propDescript.getName()
					.equals(Constants.PROP_UPDATE_DATE)) {
				// �����޸�ʱ��
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });
			} else if (propDescript.getName()
					.equals(Constants.PROP_EXPIRE_DATE)) {
				propDescript.getWriteMethod().invoke(object,
						new Object[] { sydDate });

			} else if (propDescript.getName().equals(Constants.PROP_APP_ID)) {
				// ���õ�ǰ��¼�������ʶ
				propDescript.getWriteMethod().invoke(object,
						new Object[] { new Long(appRequest.getAppId()) });

			} else if (propDescript.getName()
					.equals(Constants.PROP_OPERATOR_ID)) {
				// �����������ԱΪ�ջ����0��������
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getOperatorId()) });
				}

			} else if (propDescript.getName().equals(Constants.PROP_ORG_ID)) {
				// �������Ա����Ӫҵ��Ϊ�գ�������
				value = propDescript.getReadMethod().invoke(object);
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod().invoke(object,
							new Object[] { new Long(appRequest.getOrgId()) });
				}

			} else if (propDescript.getName().equals(
					Constants.PROP_OFFICE_ORG_ID)) {
				// �������Ӫҵ��Ϊ�գ�������
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
				// �������Ա��½Ӫҵ��Ϊ�գ�������
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
				// �ֹ�˾��ʶ����0����Ҫ�޸ķֹ�˾��ʶ
				// if (appRequest.getCorpOrgId() > 0) {
				value = propDescript.getReadMethod().invoke(object);
				// ����¼�ļ�¼������ʾΪ�ջ��ߵ���0��ʱ���������
				if (value == null || ((Long) value).longValue() == 0) {
					propDescript.getWriteMethod()
							.invoke(
									object,
									new Object[] { new Long(appRequest
											.getCorpOrgId()) });
				}

				// }

			} else if (propDescript.getName().equals(Constants.PROP_REC_ORG_ID)) {
				// ��¼������ʶ����0����Ҫ�޸ķֹ�˾��ʶ

				// if (appRequest.getRecOrgId() > 0) {
				value = propDescript.getReadMethod().invoke(object);
				// ����¼�ķֹ�˾������ʾΪ�ջ��ߵ���0��ʱ���������
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

	/** ���������¼��������ϸ��¼ */
	private void saveAppOprateDtl(long identifierId, String objClassName,
			long newAppId, long operatorType) {
		// ��¼���������ϸ
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
		// ����������ϸ
		this.appOperateDtlDao.saveOrUpdate(operateDtl);
	}

	/**
	 * ������ʷ����
	 * 
	 * @param object
	 *            ��ǰ����
	 * @param newAppId
	 *            ��ǰ�����ʶ
	 * @return ������ʷ����
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Object wirteHistoryObject(Class classHis, Object object,
			AppRequest appRequest, Serializable identifier) {
		// ʵ������ʷ����
		Object objectHis = null;
		try {
			objectHis = classHis.newInstance();

			if (objectHis == null)
				return object;

			// ������ǰ������ʷ������        <----
			PropertyUtils.copyProperties(objectHis, object);
			// ���ù�������
			PropertyDescriptor desriptor = PropertyUtils.getPropertyDescriptor(
					objectHis, Constants.PROP_EXPIRE_DATE);
			if(desriptor==null)
				return object;
			desriptor.getWriteMethod().invoke(
					objectHis,
					new Object[] { DBUtils.getSysDate(this.appDao
							.getSessionFactory()) });
			// �����������ʶ
			desriptor = PropertyUtils.getPropertyDescriptor(objectHis,
					Constants.PROP_NEW_APP_ID);
			desriptor.getWriteMethod().invoke(objectHis,
					new Object[] { new Long(appRequest.getAppId()) });
			// ���������¼������ϸ
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
	 * ���������Ų�ѯ������Ϣ
	 * 
	 * @param appCode
	 * @return �����¼
	 */
	public AppDTO findAppDTOByAppCode(String appCode) {
		App app = this.appDao.findByAppCode(appCode);
		return null != app ? new AppDTO(app) : null;
	}

	/**
	 * ���������Ų�ѯ������Ϣ AppAndAppTypeDTO
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
	 * ���������ʶ��ѯ������Ϣ AppAndAppTypeDTO
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
	 * �����������ͱ����ѯ��������
	 * 
	 * @param appAction
	 * @return ��������
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
	 * ��������������������������
	 * 
	 * @param category 0 Ϊ����
	 *            �����������
	 * @return ���������б� ������Ϊ��
	 */
	public List findAppTypeByCategory(long category) {
		return DTOEntityTranlate.entityArray2DTOArray(this.appTypeDao
				.findByCategory(category));

	}

	/**
	 * ���������ʶ��ѯ������Ϣ
	 * 
	 * @param appId
	 * @return �����¼
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
	 * ���浥���������ʷ
	 * 
	 * @param appRequest
	 *            ��������
	 * @param object
	 *            ��¼�Ķ���
	 * @param baseService
	 *            ���󱣴�ķ���
	 * @return ����Ķ���
	 * @throws Exception
	 */
	private Object savePoJoHistory(AppRequest appRequest, Object object,
			BusiBaseService baseService) {

			String className = object.getClass().getName()
				+ Constants.HIS_OBJECT_SUFFIX;
		// ������ʷ������
		Class classHis = null;
		try {
			classHis = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// ��ʷ��û���ҵ����򲻴���
			return object;
		}
		
//		 ��ȡ�����ʶ��
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
		// ��ȡ�����ʶ��
		Serializable identifier = EntityBeanManager.getObjectIdentifier(object,
				this.appDao.getSessionFactory());
		if (identifier == null)
			throw new CommonRuntimeException("�޷���ȡ����ı�ʶ����"
					+ object.getClass().getName());
		if (((Long) identifier).longValue() != 0) {// ��ʶ����Ӧ��ID��Ϊ0
			// �����ݿ����������
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
			// ���ݿ��в����ڣ�����ʼ�½����������¼��ʷ;����Ҫ��¼�����¼������ϸ
			if (dbObject == null) {
				// ���������¼������ϸ
				if (//appRequest.isDirectSaveUpdate()&& 
						((Long) identifier).longValue() == 0) {// �½�����
					// ֱ��ͨ��busi�������
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
			// ����ǰ���ݿ���������ʷ
            Object hisObj=wirteHistoryObject(classHis, dbObject,
					appRequest, identifier);
			baseService.saveHisObject(hisObj);
			// / --->
			String[] ignorePropertys = { Constants.PROP_CREATE_DATE,
					Constants.PROP_APP_ID, Constants.PROP_OFFICE_ORG_ID,
					Constants.PROP_OPERATOR_ID, Constants.PROP_ORG_ID,
					Constants.PROP_VALID_DATE };
			// ����dbObject������
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
	 * ���沢���õ����������ʷ
	 * 
	 * @param appRequest
	 *            ��������
	 * @param object
	 *            ��¼�Ķ���
	 * @param baseService
	 *            ���󱣴�ķ���
	 * @return ����Ķ���
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
	 * ���浥���������ʷ
	 * 
	 * @param appRequest
	 *            ��������
	 * @param object
	 *            ��¼�Ķ���
	 * @param baseService
	 *            ���󱣴�ķ���
	 * @return ����Ķ���
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
	 * ���沢���õ����������ʷ
	 * 
	 * @param appRequest
	 *            ��������
	 * @param object
	 *            ��¼�Ķ���
	 * @param baseService
	 *            ���󱣴�ķ���
	 * @return ����Ķ���
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
	 * ���õ�ǰ�������ʷ�ֶ�����ֵ�������ض���
	 * 
	 * @param appRequest
	 *            �������
	 * @param object
	 *            ���õĶ���
	 * @return �������ú�Ķ���
	 */
	public Object settingCurrentObject(AppRequest appRequest, Object object) {
		// ��ʼ�����飬��������
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
	 * ��¼ɾ���������ʷ�����ö���Ĺ�������
	 * 
	 * @param appRequest
	 *            �������
	 * @param object
	 *            ���õĶ���
	 * @param baseService
	 *            ������ʷ����ķ���
	 * @return �������õĶ���
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
	 * ���������ʶ�����������¼������ϸ -����
	 * 
	 * @param appId
	 *            �����ʶ
	 * @return java.util.List �����¼������ϸ AppOperateDtl -����
	 */
	public List findAppOperateDtlsByAppId(long appId) {
		return DTOEntityTranlate.entityArray2DTOArray(this.appOperateDtlDao
				.findAppOperateDtlsByAppId(appId));
	}

	/**
	 * �����������ͱ�ʶ������������
	 * 
	 * @param appTypeIds
	 *            ��������id�б�
	 * @return ����AppTypeDTO
	 */
	public List findAppTypeByIds(Long[] appTypeIds) {
		// TODO Auto-generated method stub
		List result = this.appTypeDao.findByAppTypeIds(appTypeIds);
		return DTOEntityTranlate.entityArray2DTOArray(result);
	}

	/**
	 * �޸�����״̬
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

	/** ���������ʶ,����[�����¼������ϸ���ʶ���];�Ƿ���[��������ϸ����]���� �µ�����. */
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
			if(queryDTO.getLoginOrgId()>0){//����½��֯����һ�������ѯ
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
			//���û�а���֯���ǣ���ֱ�Ӱ��ɷ��ʵ���֯������
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
