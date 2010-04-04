/**
 * 
 */
package com.ft.utils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

/**
 * @author adminxp
 * 
 */
public class PlatformReflectionUtils {

	/**
	 * 是不是某个类的实例
	 * 
	 * @param obj
	 *            实例
	 * @param cls
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public static boolean isInstance(Object obj, Class cls) {
		return cls.isInstance(obj);
	}

	/**
	 * 得到数组中的某个元素
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            索引
	 * @return 返回指定数组对象中索引组件的值
	 */
	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}

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
	public static String getObjectIdentifierName(Object object,
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
	public static String getObjectIdentifierNameByClazz(Class clazz,
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
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			try {
				resultObj = desriptor.getReadMethod().invoke(object,
						new Object[] {});
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
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

	public static java.util.Map<String, PropertyDescriptor> getOjbectPropertyDescriptors(
			Object object, String[] propertys) {
		Map<String, PropertyDescriptor> descriptMap = new HashMap<String, PropertyDescriptor>();

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

	/**
	 * 获取对象属性值
	 * 
	 * @param object
	 *            对象实例
	 * @param propertyName
	 *            属性名
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectPropertyValue(Object object,
			String propertyName) throws Exception {
		Map<String, PropertyDescriptor> propDesc = getOjbectPropertyDescriptors(
				object, new String[] { propertyName });
		PropertyDescriptor prop = propDesc.get(propertyName);
		return prop.getReadMethod().invoke(object, new Object[] {});
	}

	/**
	 * 设置对象属性值
	 * 
	 * @param object
	 *            对象实例
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            属性值
	 * @throws Exception
	 */
	public static void setObjectPropertyValue(Object object,
			String propertyName, Object value) throws Exception {
		Map<String, PropertyDescriptor> propDesc = getOjbectPropertyDescriptors(
				object, new String[] { propertyName });
		PropertyDescriptor prop = propDesc.get(propertyName);
		prop.getWriteMethod().invoke(object, new Object[] { value });
	}

	/**
	 * 设置实体的属性集合
	 * 
	 * @param object
	 *            实例
	 * @param propertyAndValues
	 * @throws Exception
	 */
	public static void setObjectPropertyValue(Object object,
			Map<String, Object> propertyAndValues) throws Exception {
		String[] propertyNames = propertyAndValues.keySet().toArray(
				new String[] {});
		Map<String, PropertyDescriptor> props = PlatformReflectionUtils
				.getOjbectPropertyDescriptors(object, propertyNames);
		if (props != null) {
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				props.get(it.next()).getWriteMethod().invoke(object,
						new Object[] { propertyAndValues.get(it.next()) });
			}
		}

	}


	
	private static final Class[] NO_CLASSES = new Class[0];



	public static Class classForName(String name) throws ClassNotFoundException {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(name);
		}
		catch (Exception e) {
			return Class.forName(name);
		}
	}

	public static boolean isPublic(Class clazz, Member member) {
		return Modifier.isPublic( member.getModifiers() ) && Modifier.isPublic( clazz.getModifiers() );
	}

	public static Object getConstantValue(String name) {
		Class clazz;
		try {
			clazz = classForName( StringHelper.qualifier(name) );
		}
		catch(ClassNotFoundException cnfe) {
			return null;
		}
		try {
			return clazz.getField( StringHelper.unqualify(name) ).get(null);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Constructor getDefaultConstructor(Class<Object> clazz) throws Exception {

		if ( isAbstractClass(clazz) ) return null;

		try {
			Constructor constructor = clazz.getDeclaredConstructor(NO_CLASSES);
			if ( !isPublic(clazz, constructor) ) {
				constructor.setAccessible(true);
			}
			return constructor;
		}
		catch (NoSuchMethodException nme) {
			throw nme;
		}

	}

	public static boolean isAbstractClass(Class clazz) {
		int modifier = clazz.getModifiers();
		return Modifier.isAbstract(modifier) || Modifier.isInterface(modifier);
	}
}
