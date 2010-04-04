package com.ft.web.sm.info;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.AfficheDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 公告信息处理类
 * 
 * @struts.form name="afficheForm"
 * 
 * @version 1.0
 */
public class AfficheForm extends BaseValidatorForm {
    private static final long serialVersionUID = 4607371406373767803L;
    private String afficheTitle;
    private Long categoryId;
    private String createTime;
    
    private AfficheDTO affiche;
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == affiche) {
            affiche = new AfficheDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public AfficheDTO getAffiche() {
        return affiche;
    }

    public void setAffiche(AfficheDTO affiche) {
        this.affiche = affiche;
    }
    
    /**
     * @return the categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the afficheTitle
     */
    public String getAfficheTitle() {
        return afficheTitle;
    }

    /**
     * @param afficheTitle the afficheTitle to set
     */
    public void setAfficheTitle(String afficheTitle) {
        this.afficheTitle = afficheTitle;
    }

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
