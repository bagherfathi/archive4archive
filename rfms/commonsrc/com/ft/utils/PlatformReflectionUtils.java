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
	 * �ǲ���ĳ�����ʵ��
	 * 
	 * @param obj
	 *            ʵ��
	 * @param cls
	 *            ��
	 * @return ��� obj �Ǵ����ʵ�����򷵻� true
	 */
	public static boolean isInstance(Object obj, Class cls) {
		return cls.isInstance(obj);
	}

	/**
	 * �õ������е�ĳ��Ԫ��
	 * 
	 * @param array
	 *            ����
	 * @param index
	 *            ����
	 * @return ����ָ��������������������ֵ
	 */
	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}

	/**
	 * ��ȡʵ������Ψһ��ʶ����
	 * 
	 * @param object
	 *            ʵ�����
	 * @param sessionFactory
	 *            Hibernate��session����
	 * @return Serializable ������pojo���󣬷���Ϊ��
	 */
	public static Serializable getObjectIdentifier(Object object,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());
		if (classMetadata != null) {
			// ���һ��ʵ�����ı�ʶ
			Serializable id = classMetadata.getIdentifier(object,
					EntityMode.POJO);
			return id;
		} else
			return null;

	}

	/**
	 * ��ȡʵ������Ψһ��ʶ������
	 * 
	 * @param object
	 *            ʵ�����
	 * @param sessionFactory
	 *            Hibernate��session����
	 * @return String
	 */
	public static String getObjectIdentifierName(Object object,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());
		if (classMetadata != null) {
			// ���һ��ʵ�����ı�ʶ
			return classMetadata.getIdentifierPropertyName();
		} else
			return null;

	}

	/**
	 * ��ȡʵ������Ψһ��ʶ������
	 * 
	 * @param Class
	 *            ʵ����
	 * @param sessionFactory
	 *            Hibernate��session����
	 * @return String
	 */
	public static String getObjectIdentifierNameByClazz(Class clazz,
			SessionFactory sessionFactory) {

		ClassMetadata classMetadata = sessionFactory.getClassMetadata(clazz);
		if (classMetadata != null) {
			// ���һ��ʵ�����ı�ʶ
			return classMetadata.getIdentifierPropertyName();
		} else
			return null;

	}

	/**
	 * ��ȡһ������������ʶ��ֵ
	 * 
	 * @param object
	 *            �����ʶ
	 * @param sessionFactory
	 *            Hibernate��session����
	 * @return ������ʶ��ֵ
	 */
	public static Object getObjectIdentifierId(Object object,
			SessionFactory sessionFactory) {
		Object resultObj = null;
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(object
				.getClass());
		if (classMetadata != null) {
			// ���һ��ʵ�����ı�ʶ
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
	 * ��ȡָ����������Ե�Descriptor
	 * 
	 * @param object
	 *            ��Ҫ�����Ķ���
	 * @param propertys
	 *            ��Ҫ����Descriptor����������
	 * @return Map ���propertysΪ�� ���ؿյ�Properties�����򷵻�propertysָ�� ��descript
	 */

	public static java.util.Map<String, PropertyDescriptor> getOjbectPropertyDescriptors(
			Object object, String[] propertys) {
		Map<String, PropertyDescriptor> descriptMap = new HashMap<String, PropertyDescriptor>();

		// �õ�һ�����������desriptor
		PropertyDescriptor[] desriptor = PropertyUtils
				.getPropertyDescriptors(object);
		// �������������ԣ�ֱ�ӷ���
		if (propertys == null || propertys.length == 0)
			return descriptMap;

		if (desriptor != null && desriptor.length > 0) {
			for (int i = 0; i < desriptor.length; i++) {
				PropertyDescriptor dest = desriptor[i];
				for (int j = 0; j < propertys.length; j++) {
					// �ҵ����������б��˳�
					if (dest.getName().equals(propertys[j])) {
						descriptMap.put(propertys[j], dest);
						break;
					}

				}
			}
		}
		// �������
		return descriptMap;
	}

	/**
	 * ��ȡ��������ֵ
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param propertyName
	 *            ������
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
	 * ���ö�������ֵ
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param propertyName
	 *            ������
	 * @param value
	 *            ����ֵ
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
	 * ����ʵ������Լ���
	 * 
	 * @param object
	 *            ʵ��
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
