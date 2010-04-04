package com.ft.busi.common;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是工具类，DTO类和实体类的互转
 * 
 * @version 1.0
 */
public class DTOEntityTranlate {

    /**
     * 实现一个实体类向DTO类转换
     * 
     * @param entity
     *            实体
     * @param subSysTemName
     *            子系统名称
     * @return 封装实体的DTO类,如果实体是null 则返回null
     */
    @SuppressWarnings("unchecked")
	public static Object entity2DTO(Object entity, String subSysTemName) {
        //如果实体是null则返回null
        if (entity==null)
            return null;
        subSysTemName=subSysTemName.toLowerCase();
        String className = entity.getClass().getName();
        String entityName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // 设置DTO类的包名
        String packageName = "com.huashu.boss.busi." + subSysTemName + ".dto."
                + entityName + "DTO";
        Object obj = null;
        try {
            // 构造DTO类
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
     * 根据实体列表，返回简单DTO列表的数据
     * 
     * @param entity
     * @param subSysTemName
     * @return 如果参数列表有一个错，或列表为空，则返回一个空的列表
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
     * 根据实体列表，返回简单DTO列表的数据
     * 
     * @param entity
     * @return 如果参数列表有一个错，或列表为空，则返回一个空的列表
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
