package com.ft.busi.sm.service;

import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;

/**
 * 对外部提供获取枚举数据的接口。
 * 
 * @version 1.0
 */
public interface EnumService {
    /**
     * 根据类别代码（表名）获取该类别中所有的枚举数据实体。
     * 
     * @param categoryCode
     *                类别代码（表名）。
     * @return EnumData实体列表。
     */
    public EnumDTO[] findEnumsByCategory(String categoryCode);

    /**
     * 根据枚举数据代码获取该枚举数据中所有数据条目。
     * 
     * @param categoryCode
     *                枚举数据所在类别代码（表名）。
     * @param enumCode
     *                枚举数据代码（字段名）。
     * @return EnumDataEntry实体列表。
     */
    public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode);

    /**
     * 根据枚举数据代码获取该枚举数据中数据条目，并根据给定的关联值进行过滤。
     * 
     * @param categoryCode
     *                枚举数据所在类别代码（表名）。
     * @param enumCode
     *                枚举数据代码（字段名）。
     * @param linkValue
     *                枚举数据条目关联值。
     * @return EnumDataEntry实体列表。
     */
    public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode, String linkValue);
    
    /**
     * 查询指定enum entry的值描述
     * @param categoryCode    类别代码
     * @param enumCode        数据代码
     * @param value           数据指
     * @return
     */
    public String findEnumEntrie(String categoryCode,
            String enumCode,String value);
}
