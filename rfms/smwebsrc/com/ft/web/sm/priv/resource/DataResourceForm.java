package com.ft.web.sm.priv.resource;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * ҵ��Ȩ�������� ���ҵ��Ȩ����Ϣ��ҵ��Ȩ����Ŀ������Ϣ
 * 
 * @struts.form name = "dataResourceForm"
 * @struts.form name = "dataResourceEntryForm"
 * 
 * @version 1.0
 */
public class DataResourceForm extends BaseValidatorForm {

    private static final long serialVersionUID = 4654611204730543185L;

    /**
     * ���ҵ��Ȩ�޶���
     */
    private DataResourceDTO dataResource;

    /**
     * ��Ŷ��ҵ��Ȩ�޶���
     */
    private DataResourceDTO[] dataResources;

    /**
     * ҵ��Ȩ����Ŀ��Ϣ
     */
    private DataResourceEntryDTO entry;

    private String maxValue;

    private String minValue;

    /**
     * ��Ŷ��ҵ��Ȩ����Ŀ����
     */
    private Set dataResourceEntrys;

    private String act;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == dataResource) {
            dataResource = new DataResourceDTO();
        }
        if (null == dataResourceEntrys) {
            dataResourceEntrys = new HashSet();
        }
        if (null == entry) {
            entry = new DataResourceEntryDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return Returns the attach.
     */
    public DataResourceDTO getDataResource() {
        return dataResource;
    }

    public void setDataResource(DataResourceDTO dataResource) {
        this.dataResource = dataResource;
    }

    /**
     * @struts.entity-field initial="ids"
     * @return Returns the attach.
     */
    public DataResourceDTO[] getDataResources() {
        return dataResources;
    }

    public void setDataResources(DataResourceDTO[] dataResources) {
        this.dataResources = dataResources;
    }

    /**
     * ���ҵ��Ȩ����Ŀ����
     * 
     * @return
     */
    public Set getDataResourceEntrys() {
        return dataResourceEntrys;
    }

    public void setDataResourceEntrys(Set dataResourceEntrys) {
        this.dataResourceEntrys = dataResourceEntrys;
    }

    /**
     * ����ҵ��Ȩ����Ŀ����
     * 
     * @struts.entity-field initial="eId"
     * 
     * @return
     */
    public DataResourceEntryDTO getEntry() {
        return entry;
    }

    public void setEntry(DataResourceEntryDTO entry) {
        this.entry = entry;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

}
