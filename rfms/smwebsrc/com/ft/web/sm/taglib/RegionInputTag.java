package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;

import com.ft.webui.TemplateTagSupport;

/**
 * 显示区域信息
 * 
 * @version 1.0
 */
public class RegionInputTag extends TemplateTagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5667476819141790562L;

	private Long orgId;

    private String formName;

    private String province;

    private String city;

    private String district;

    private String zone;

    private String community;

    private String detail;

    private String street;

    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *                The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return Returns the community.
     */
    public String getCommunity() {
        return community;
    }

    /**
     * @param community
     *                The community to set.
     */
    public void setCommunity(String community) {
        this.community = community;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *                The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return Returns the district.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district
     *                The district to set.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return Returns the orgId.
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *                The orgId to set.
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * @return Returns the province.
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     *                The province to set.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return Returns the street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     *                The street to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return Returns the zone.
     */
    public String getZone() {
        return zone;
    }

    /**
     * @param zone
     *                The zone to set.
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getVarName() {
        return "regionTag";
    }

    /**
     * @return Returns the formName.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * @param formName
     *                The formName to set.
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

}
