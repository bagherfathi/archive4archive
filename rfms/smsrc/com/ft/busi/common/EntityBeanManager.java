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
 * ʵ�������Ĺ�����
 * 
 * @version 1.0
 */
public class EntityBeanManager {

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
	public static String  getObjectIdentifierName(Object object,
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
	public static String  getObjectIdentifierNameByClazz(Class  clazz,
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
	 * ��ȡָ����������Ե�Descriptor
	 * 
	 * @param object
	 *            ��Ҫ�����Ķ���
	 * @param propertys
	 *            ��Ҫ����Descriptor����������
	 * @return Map ���propertysΪ�� ���ؿյ�Properties�����򷵻�propertysָ�� ��descript
	 */

	public static java.util.Map<String,PropertyDescriptor>  getOjbectPropertyDescriptors(Object object,
			String[] propertys) {
		Map<String,PropertyDescriptor> descriptMap = new HashMap<String,PropertyDescriptor> ();

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

}
