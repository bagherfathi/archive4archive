package com.ft.sm.common;

/**
 * 事件的类型和方法定义类。
 * 
 */
public class EventConstants {
    public static String EVENT_TYPE_RULE = "10"; // 规则事件类型

    public static String EVENT_TYPE_TEMPLATE = "20"; // 模板事件类新
    
    public static String EVENT_TYPE_ORG = "30";  //组织事件类型
    
    public static String EVENT_TYPE_ENUM = "40"; //枚举数据事件类型
    
    public static String EVENT_TYPE_RESOURCE = "50";  //功能权限事件类型

    public static String EVENT_UPDATE_RULE = "1010";     //规则更新事件
    public static String EVENT_UPDATE_TEMPLATE = "2010"; //模板更新事件
    public static String EVENT_UPDATE_ORG = "3010";      //组织更新事件
    public static String EVENT_ADD_ORG = "3020";         //组织新增事件
    public static String EVENT_UPDATE_ENUM = "4010";     //枚举数据更新事件
    public static String EVENT_UPDATE_RESOURCE = "5010"; //功能权限更新事件
    public static String EVENT_REMOVE_RESOURCE = "5020"; //功能权限删除事件
}
