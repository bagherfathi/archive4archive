package com.ft.web.sm.data.enumdata;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.EnumGroupDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 系统数据组维护页面控制类
 * 
 * @struts.form name="enumGroupForm"
 * 
 * @version 1.0
 */
public class EnumGroupForm extends BaseValidatorForm {
    private static final long serialVersionUID = -7726156286119644416L;

    private EnumGroupDTO enumGroup;

    private FormFile enumDataGroupFile;

    /**
     * Enum数据组
     * 
     * @struts.entity-field initial="groupId"
     * @return
     */
    public EnumGroupDTO getEnumGroup() {
        return enumGroup;
    }

    public void setEnumGroup(EnumGroupDTO enumGroup) {
        this.enumGroup = enumGroup;
    }


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == enumGroup) {
            enumGroup = new EnumGroupDTO();
        }
    }

    public FormFile getEnumDataGroupFile() {
        return enumDataGroupFile;
    }

    public void setEnumDataGroupFile(FormFile enumDataGroupFile) {
        this.enumDataGroupFile = enumDataGroupFile;
    }

}
