package com.ft.busi.common;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ǹ����࣬DTO���ʵ����Ļ�ת
 * 
 * @version 1.0
 */
public class DTOEntityTranlate {

    /**
     * ʵ��һ��ʵ������DTO��ת��
     * 
     * @param entity
     *            ʵ��
     * @param subSysTemName
     *            ��ϵͳ����
     * @return ��װʵ���DTO��,���ʵ����null �򷵻�null
     */
    @SuppressWarnings("unchecked")
	public static Object entity2DTO(Object entity, String subSysTemName) {
        //���ʵ����null�򷵻�null
        if (entity==null)
            return null;
        subSysTemName=subSysTemName.toLowerCase();
        String className = entity.getClass().getName();
        String entityName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // ����DTO��İ���
        String packageName = "com.huashu.boss.busi." + subSysTemName + ".dto."
                + entityName + "DTO";
        Object obj = null;
        try {
            // ����DTO��
            Class cls = Class.forName(packageName);
            Constructor consturctor = cls.getConstructor(new Class[] { entity
                    .getClass() });
            obj = consturctor.newInstance(new Object[] { entity });
        } catch (Exception e) {
            return null;
        }
        return obj;
    }

    /**
     * ����ʵ���б����ؼ�DTO�б������
     * 
     * @param entity
     * @param subSysTemName
     * @return ��������б���һ�������б�Ϊ�գ��򷵻�һ���յ��б�
     */
    @SuppressWarnings("unchecked")
	public static List entityArray2DTOArray(List entityList,
            String subSysTemName) {
        if ((entityList == null) || (entityList.size() == 0))
            return new ArrayList();
        List list = new ArrayList();
        subSysTemName=subSysTemName.toLowerCase();
        for (int i = 0; i < entityList.size(); i++) {
            String className = entityList.get(i).getClass().getName();
            String entityName = className.substring(
                    className.lastIndexOf(".") + 1, className.length());
            String packageName = "com.huashu.boss.busi." + subSysTemName
                    + ".dto." + entityName + "DTO";
            Object obj = null;
            try {
                Class cls = Class.forName(packageName);
                Constructor consturctor = cls
                        .getConstructor(new Class[] { entityList.get(i)
                                .getClass() });
                obj = consturctor
                        .newInstance(new Object[] { entityList.get(i) });
            } catch (Exception e) {
                return null;
            }
            list.add(obj);
        }
        return list;
    }
    /**
     * ����ʵ���б����ؼ�DTO�б������
     * 
     * @param entity
     * @return ��������б���һ�������б�Ϊ�գ��򷵻�һ���յ��б�
     */
    @SuppressWarnings("unchecked")
	public static List entityArray2DTOArray(List entityList) {
        if ((entityList == null) || (entityList.size() == 0))
            return new ArrayList();
        List list = new ArrayList();        
        for (int i = 0; i < entityList.size(); i++) {
            String className = entityList.get(i).getClass().getName();
            String entityName = className.substring(
                    className.lastIndexOf(".") + 1, className.length());
            String packageName = "com.huashu.boss.busi.dto." + entityName + "DTO";
            Object obj = null;
            try {
                Class cls = Class.forName(packageName);
                Constructor consturctor = cls
                        .getConstructor(new Class[] { entityList.get(i)
                                .getClass() });
                obj = consturctor
                        .newInstance(new Object[] { entityList.get(i) });
            } catch (Exception e) {
                return null;
            }
            list.add(obj);
        }
        return list;
    }

}
