package com.ft.sm.adapter;

import java.util.Iterator;
import java.util.List;

import com.ft.busi.sm.model.EnumManager;
import com.ft.busi.sm.service.EnumService;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;

/**
 * 枚举数据接口实现类。
 * 
 */
public class EnumServiceImpl implements EnumService {
    private EnumManager enumManager;

    /**
     * @param enumManager
     *                the enumManager to set
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.EnumService#findEnumEntryByEnum(java.lang.String,
     *      java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode) {
        try {
            List result = this.enumManager.findEnumEntriesByEnum(categoryCode,
                    enumCode);
            return (EnumEntryDTO[]) result.toArray(new EnumEntryDTO[0]);
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.EnumService#findEnumsByCategory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public EnumDTO[] findEnumsByCategory(String categoryCode) {
        List result;
        try {
            result = this.enumManager.findEnumDatasByCategory(categoryCode);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

        return (EnumDTO[]) result.toArray(new EnumDTO[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.EnumService#findEnumEntryByEnum(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode, String linkValue) {
        List result;
        try {
            result = this.enumManager.findEnumEntriesByEnum(categoryCode,
                    enumCode, linkValue);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

        return (EnumEntryDTO[]) result.toArray(new EnumEntryDTO[0]);
    }

    /* (non-Javadoc)
     * @see com.ft.busi.sm.service.EnumService#findEnumEntrie(java.lang.String, java.lang.String, java.lang.String)
     */
    public String findEnumEntrie(String categoryCode, String enumCode,
            String value) {
        try {
            List result = this.enumManager.findEnumEntriesByEnum(categoryCode,
                    enumCode);
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                EnumEntryDTO object = (EnumEntryDTO) iterator.next();
                if(object.getValue().equals(value)) return object.getLabel();
            }
            
            return "";
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }
    
    
}
