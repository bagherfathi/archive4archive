package com.ft.common.enumdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

import com.ft.busi.sm.model.EnumManager;
import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.sm.entity.EnumEntry;
import com.ft.commons.lookup.EnumLookuper;

/**
 * 枚举类型数据仓库，用于缓存枚举数据类型数据
 * 
 * @spring.bean id = "enumRepository"
 * 
 * @version 1.0
 */
public class EnumRepository implements EnumLookuper,SMEventListener {
    private static final Log logger = LogFactory.getLog(EnumRepository.class);
    public static final String KEY_SEPARATOR = "@";

    public static final String ENUM_CONTEXT_ATTRIBUTE = "enumSet";

    private Map<String,EnumDTO> enumDataMap = new HashMap<String,EnumDTO>();

    private EnumManager enumManager;

    /**
     * @spring.property ref="enumManager"
     * 
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }

    /**
     * 初始化装载枚举类型数据
     * 
     */
    public void loadEnumDatas() throws Exception {
        this.enumDataMap.clear();
        
        List enumDataGroups = enumManager.findAllEnumGroups();

        for (Iterator iter = enumDataGroups.iterator(); iter.hasNext();) {
            EnumGroupDTO group = (EnumGroupDTO) iter.next();
            List enumcategories = enumManager.findEnumCategoriesOfGroup(group
                    .getGroupId());
            List enumDatas = enumManager.findEnumsWithEntriesOfGroup(group
                    .getGroupId());

            for (Iterator iterator = enumcategories.iterator(); iterator
                    .hasNext();) {
                EnumCategoryDTO category = (EnumCategoryDTO) iterator.next();

                for (Iterator itera = enumDatas.iterator(); itera.hasNext();) {
                    EnumDTO enumData = (EnumDTO) itera.next();
                    if (enumData.getCategoryId().equals(
                            category.getCategoryId())) {
                        this.addEnumData(category, enumData);
                    }
                }
            }
        }

    }

    /**
     * 增加系统数据到缓存中.
     * 
     * @param category
     *                系统数据类别
     * @param enumData
     *                系统数据实体对象
     */
    public void addEnumData(EnumCategoryDTO category, EnumDTO enumData) {
        String key = generateKey(category.getCategoryCode(), enumData
                .getEnumCode());
        enumDataMap.put(key, enumData);
    }

    /**
     * 生成缓存主键. 缓存中以系统数据类别的编码和系统数据的编码形成Key：enumCode@categoryCode
     * 
     * @param categoryCode
     *                类别编码
     * @param enumCode
     *                数据编码
     * @return
     */
    private String generateKey(String categoryCode, String enumCode) {
        String key = enumCode + KEY_SEPARATOR + categoryCode;
        return key;
    }

    /**
     * 更新缓存中系统数据状态为禁用
     * 
     * @param categoryCode
     *                类别编码
     * @param enumCode
     *                数据编码
     */
    public void disable(String categoryCode, String enumCode) {
        String key = generateKey(categoryCode, enumCode);
        if (enumDataMap.containsKey(key)) {
            EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
            enumData.setStatus(EnumDTO.STATUS_DISABLE);
        }
    }

    /**
     * 更新缓存中系统数据状态为启用
     * 
     * @param categoryCode
     *                类别编码
     * @param enumCode
     *                数据编码
     */
    public void enable(String categoryCode, String enumCode) {
        String key = generateKey(categoryCode, enumCode);
        if (enumDataMap.containsKey(key)) {
            EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
            enumData.setStatus(EnumDTO.STATUS_NORMAL);
        }
    }

    public EnumEntry[] getEnumDataEntryByKey(String key) {
        if (enumDataMap.containsKey(key)) {
            List<EnumEntry> result = new ArrayList<EnumEntry>();
            EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
            if (enumData.getStatus() == EnumDTO.STATUS_NORMAL) {
                for (Iterator iter = enumData.getEntries().iterator(); iter
                        .hasNext();) {
                    EnumEntry entry = (EnumEntry) iter.next();
                    result.add(entry);
                }
                return (EnumEntry[]) result.toArray(new EnumEntry[0]);
            } else {
                return new EnumEntry[0];
            }
        } else {
            return new EnumEntry[0];
        }
    }

    /**
     * 从缓存中查询系统数据，并以LabelValueBean形式返回
     * 
     * @param tableKey
     * @return
     */
    public LabelValueBean[] getElementCategory(String tableKey) {
        Iterator ite = enumDataMap.keySet().iterator();
        List<LabelValueBean> result = new ArrayList<LabelValueBean>();
        for (; ite.hasNext();) {
            String key = (String) ite.next();
            if ((key != null) && (key.endsWith(KEY_SEPARATOR + tableKey))) {
                EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
                if (enumData.getStatus() == EnumDTO.STATUS_NORMAL) {
                    LabelValueBean bean = new LabelValueBean();
                    bean.setLabel(enumData.getEnumName());
                    bean.setValue(enumData.getEnumCode());
                    result.add(bean);
                }
            }
        }
        return (LabelValueBean[]) result.toArray(new LabelValueBean[0]);
    }

    /**
     * 从缓存中查询系统数据，并以LabelValueBean形式返回
     * 
     * @param key
     *                enumCode@categoryCode
     */
    public LabelValueBean[] getElement(String key) {
        if (enumDataMap.containsKey(key)) {
            List<LabelValueBean> result = new ArrayList<LabelValueBean>();
            EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
            if (enumData.getStatus() == EnumDTO.STATUS_NORMAL) {
                for (Iterator iter = enumData.getEntries().iterator(); iter
                        .hasNext();) {
                    EnumEntry entry = (EnumEntry) iter.next();
                    result.add(new LabelValueBean(entry.getEnumEntryLabel(),
                            entry.getEnumEntryValue()));
                }

                return (LabelValueBean[]) result.toArray(new LabelValueBean[0]);
            } else {
                return new LabelValueBean[0];
            }
        } else {
            return new LabelValueBean[0];
        }
    }

    /**
     * 从缓存中查询系统数据，并以LabelValueBean形式返回
     * 
     * @param key
     *                enumCode@categoryCode
     * @param linkValue
     *                数据关联值
     */
    public LabelValueBean[] getElement(String key, String linkValue) {
        if (enumDataMap.containsKey(key)) {
            List<LabelValueBean> result = new ArrayList<LabelValueBean>();
            EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
            if (enumData.getStatus() == EnumDTO.STATUS_NORMAL) {
                for (Iterator iter = enumData.getEntries().iterator(); iter
                        .hasNext();) {
                    EnumEntry entry = (EnumEntry) iter.next();
                    if (linkValue.equals(entry.getEntryLinkValue())) {
                        result.add(new LabelValueBean(
                                entry.getEnumEntryLabel(), entry
                                        .getEnumEntryValue()));
                    }
                }

                return (LabelValueBean[]) result.toArray(new LabelValueBean[0]);
            } else {
                return new LabelValueBean[0];
            }
        } else {
            return new LabelValueBean[0];
        }
    }

    /**
     * 查询系统数据中指定值的显示名称
     * 
     * @param key
     *                enumCode@categoryCode
     * @param value
     *                系统数据条目值
     */
    public LabelValueBean lookup(String key, Object value) {
        if (key.indexOf('@') > 0) {
            if (enumDataMap.containsKey(key)) {
                EnumDTO enumData = (EnumDTO) enumDataMap.get(key);
                for (Iterator iter = enumData.getEntries().iterator(); iter
                        .hasNext();) {
                    EnumEntry entry = (EnumEntry) iter.next();
                    if (entry.getEnumEntryValue().equals(value.toString())) {
                        return new LabelValueBean(entry.getEnumEntryLabel(),
                                entry.getEnumEntryValue());
                    }
                }
            }
        } else {
            if (enumDataMap.containsKey(value + "@" + key)) {
                EnumDTO enumData = (EnumDTO) enumDataMap.get(value + "@" + key);
                return new LabelValueBean(enumData.getEnumName(), value
                        .toString());
            }
        }

        return null;
    }

    /**
     * 系统数据缓存装载到WEB应用上下文中的参数名称
     */
    public String getName() {
        return ENUM_CONTEXT_ATTRIBUTE;
    }

    /**
     * Spring Bean id
     * 
     * @return
     */
    public static String getBeanName() {
        return "enumRepository";
    }

    /* (non-Javadoc)
     * @see com.ft.common.event.SMEventListener#isSupport(com.ft.common.event.SMEvent)
     */
    public boolean isSupport(SMEvent event) {
        if (EventConstants.EVENT_TYPE_ENUM.equals(event.getType()))
            return true;

        return false;
    }

    /* (non-Javadoc)
     * @see com.ft.common.event.SMEventListener#onEvent(com.ft.common.event.SMEvent)
     */
    public void onEvent(SMEvent event) {
        logger.info("Receive message:" + event.toString());
        String key = event.getKey();
        if(EventConstants.EVENT_UPDATE_ENUM.equals(event.getAction())){
            if(key != null && key.length() > 0){
                Long enumId = new Long(key);
                this.addOrUpdate(enumId);
            }
        }
    }
    
    private void addOrUpdate(Long enumId){
        try{
            EnumDTO enumDto = this.enumManager.findEnumDataById(enumId);
            if(enumDto != null){
                EnumCategoryDTO enumCategory = this.enumManager.findEnumCategoryById(enumDto.getCategoryId());
                this.addEnumData(enumCategory, enumDto);
            }
            
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
    }
}
