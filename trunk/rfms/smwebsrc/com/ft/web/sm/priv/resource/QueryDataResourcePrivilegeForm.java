package com.ft.web.sm.priv.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 业务权限分配查询表单类
 * 
 * @struts.form name="queryDataResourcePrivilegeForm"
 * 
 * @version 1.0
 */
public class QueryDataResourcePrivilegeForm extends BaseValidatorForm {

    private static final long serialVersionUID = -8654996162935753023L;

    /**
     * 存放业务权限信息
     */
    private DataResourceDTO dataResource;

    /**
     * 存放业务权限条目信息
     */
    private DataResourceEntryDTO dataResourceEntry;

    private String act;

    private String title;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == dataResource) {
            dataResource = new DataResourceDTO();
        }
        if (null == dataResourceEntry) {
            dataResourceEntry = new DataResourceEntryDTO();
        }
    }

    /**
     * @struts.entity-field initial="drId"
     * @return
     */
    public DataResourceDTO getDataResource() {
        return dataResource;
    }

    public void setDataResource(DataResourceDTO dataResource) {
        this.dataResource = dataResource;
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public DataResourceEntryDTO getDataResourceEntry() {
        return dataResourceEntry;
    }

    public void setDataResourceEntry(DataResourceEntryDTO dataResourceEntry) {
        this.dataResourceEntry = dataResourceEntry;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
