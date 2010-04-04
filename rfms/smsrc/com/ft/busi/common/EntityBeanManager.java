package com.ft.busi.common;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

/**
 * 实体对象反射的管理类
 * 
 * @version 1.0
 */
public class EntityBeanManager {

	/**
	 * 获取实体对象的唯一标识符号
	 * 
	 * @param object
	 *            实体对象
	 * @param sessionFactory
	 *            Hibernate的session工厂
	 * @return Serializable ，不是pojo对象，返回为空
	 */
	public static Serializable getObjectIdentifier(Object object,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());
		if (classMetadata != null) {
			// 获得一个实体对象的标识			
			Serializable id = classMetadata.getIdentifier(object,
					EntityMode.POJO);
			return id;
		} else
			return null;

	}
	/**
	 * 获取实体对象的唯一标识的名称
	 * 
	 * @param object
	 *            实体对象
	 * @param sessionFactory
	 *            Hibernate的session工厂
	 * @return String 
	 */
	public static String  getObjectIdentifierName(Object object,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());		
		if (classMetadata != null) {
			// 获得一个实体对象的标识			
			return classMetadata.getIdentifierPropertyName();			
		} else
			return null;

	}
	/**
	 * 获取实体对象的唯一标识的名称
	 * 
	 * @param Class 
	 *            实体类
	 * @param sessionFactory
	 *            Hibernate的session工厂
	 * @return String 
	 */
	public static String  getObjectIdentifierNameByClazz(Class  clazz,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(clazz);		
		if (classMetadata != null) {
			// 获得一个实体对象的标识			
			return classMetadata.getIdentifierPropertyName();			
		} else
			return null;

	}
	/**
	 * 获取一个对象主键标识的值
	 * 
	 * @param object
	 *            对象标识
	 * @param sessionFactory
	 *            Hibernate的session工厂
	 * @return 主键标识的值
	 */
	public static Object getObjectIdentifierId(Object object,
			SessionFactory sessionFactory) {
		Object resultObj = null;
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());
		if (classMetadata != null) {
			// 获得一个实体对象的标识
			String identifierProperty = classMetadata
					.getIdentifierPropertyName();
			PropertyDescriptor desriptor = null;
			try {
				desriptor = PropertyUtils.getPropertyDescriptor(object,
						identifierProperty);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				resultObj = desriptor.getReadMethod().invoke(object);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultObj;

	}

	/**
	 * 获取指定对象的属性的Descriptor
	 * 
	 * @param object
	 *            需要解析的对象
	 * @param propertys
	 *            需要返回Descriptor的属性数组
	 * @return Map 如果propertys为空 返回空的Properties，否则返回propertys指定 的descript
	 */

	public static java.util.Map<String,PropertyDescriptor>  getOjbectPropertyDescriptors(Object object,
			String[] propertys) {
		Map<String,PropertyDescriptor> descriptMap = new HashMap<String,PropertyDescriptor> ();

		// 得到一个对象的所有desriptor
		PropertyDescriptor[] desriptor = PropertyUtils
				.getPropertyDescriptors(object);
		// 如果无需过滤属性，直接返回
		if (propertys == null || propertys.length == 0)
			return descriptMap;

		if (desriptor != null && desriptor.length > 0) {
			for (int i = 0; i < desriptor.length; i++) {
				PropertyDescriptor dest = desriptor[i];
				for (int j = 0; j < propertys.length; j++) {
					// 找到，放入结果列表并退出
					if (dest.getName().equals(propertys[j])) {
						descriptMap.put(propertys[j], dest);
						break;
					}

				}
			}
		}
		// 结果返回
		return descriptMap;
	}

}
