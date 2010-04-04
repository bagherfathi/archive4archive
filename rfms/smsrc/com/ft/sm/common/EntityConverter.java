/*
package com.ft.sm.common;

import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Contact;
import com.ft.sm.entity.CycleDate;
import com.ft.sso.api.dto.OperatorVO;
import com.ft.sso.api.dto.OrganizationVO;

*//**
 * 实体类型转换类。
 * 
 * 在和SSO系统进行数据同步时进行数据转换。
 * 
 *//*
public class EntityConverter {

    *//**
     * Operator转换为OperatorVO。
     * 
     * @param operator
     * @return
     *//*
    public static OperatorVO convertOperator2VO(OperatorDTO operator) {
        OperatorVO vo = new OperatorVO();

        vo.setEmail(operator.getEmail());
        vo.setLoginName(operator.getLoginName());
        vo.setMemo(operator.getMemo());
        vo.setMsn(operator.getMsn());
        vo.setOrgSSOCode(operator.getOrgSSOCode());
        vo.setPassword(operator.getPassword());
        vo.setSsoCode(operator.getSsoCode());
        vo.setStatus(new Long(operator.getStatus()).intValue());
        vo.setJobNumber(operator.getJobNumber());

        if (operator.getContact() != null) {
            vo.setAddress(operator.getContact().getAddress());
            vo.setContactName(operator.getContact().getName());
            vo.setMobilePhone(operator.getContact().getMobilePhone());
            vo.setPostCode(operator.getContact().getPostCode());
            vo.setTelephone(operator.getContact().getTelephone());
        }

        if (operator.getCycleDate() != null) {
            vo.setCreateDate(operator.getCycleDate().getCreateDate());
            vo.setExpireDate(operator.getCycleDate().getExpireDate());
            vo.setModifyDate(operator.getCycleDate().getModifyDate());
            vo.setValidDate(operator.getCycleDate().getValidDate());
        }

        return vo;
    }

    *//**
     * OperatorVO转换为Operator。
     * 
     * @param vo
     * @return
     *//*
    public static OperatorDTO convertVO2Operator(OperatorVO vo) {
        OperatorDTO op = new OperatorDTO();
        convertVO2Operator(vo, op);
        return op;
    }

    *//**
     * OperatorVO转换为Operator。
     * 
     * @param vo
     * @return
     *//*
    public static void convertVO2Operator(OperatorVO vo, OperatorDTO op) {
        Contact contact = new Contact();
        contact.setAddress(vo.getAddress());
        contact.setMobilePhone(vo.getMobilePhone());
        contact.setName(vo.getContactName());
        contact.setPostCode(vo.getPostCode());
        contact.setTelephone(vo.getTelephone());

        op.setContact(contact);

        CycleDate cycleDate = new CycleDate();
        cycleDate.setCreateDate(vo.getCreateDate());
        cycleDate.setExpireDate(vo.getExpireDate());
        cycleDate.setModifyDate(vo.getModifyDate());
        cycleDate.setValidDate(vo.getValidDate());
        op.setCycleDate(cycleDate);

        op.setEmail(vo.getEmail());
        op.setLoginName(vo.getLoginName());
        op.setMemo(vo.getMemo());
        op.setMsn(vo.getMsn());
        op.setOrgSSOCode(vo.getOrgSSOCode());
        op.setPassword(vo.getPassword());
        op.setSsoCode(vo.getSsoCode());
        op.setStatus(vo.getStatus());
        op.setJobNumber(vo.getJobNumber());
    }

    *//**
     * Organization转换为OrganizaitonVO。
     * 
     * @param org
     * @return
     *//*
    public static OrganizationVO convertOrganization2VO(OrganizationDTO org) {
        OrganizationVO vo = new OrganizationVO();

        vo.setCode(org.getCode());
        vo.setCreateTime(org.getCreateTime());
        vo.setDesc(org.getDesc());
        vo.setModifyDate(org.getModifyDate());
        vo.setName(org.getName());
        vo.setParentOrgSSOCode(org.getParentSSOCode());
        vo.setSsoCode(org.getSsoCode());
        vo.setStatus(new Long(org.getStatus()).intValue());
        vo.setType(new Long(org.getType()).intValue());

        if (org.getContact() != null) {
            vo.setContactName(org.getContact().getName());
            vo.setMobilePhone(org.getContact().getMobilePhone());
            vo.setPostCode(org.getContact().getPostCode());
            vo.setTelephone(org.getContact().getTelephone());
            vo.setAddress(org.getContact().getAddress());
        }

        return vo;
    }

    *//**
     * OrganizaitonVO转换为Organizaiton。
     * 
     * @param vo
     * @return
     *//*
    public static OrganizationDTO convertVO2Organization(OrganizationVO vo) {
        OrganizationDTO org = new OrganizationDTO();
        convertVO2Organization(vo, org);
        return org;
    }

    *//**
     * OrganizaitonVO转换为Organizaiton。
     * 
     * @param vo
     * @return
     *//*
    public static void convertVO2Organization(OrganizationVO vo,
            OrganizationDTO org) {
        Contact contact = new Contact();
        contact.setAddress(vo.getAddress());
        contact.setMobilePhone(vo.getMobilePhone());
        contact.setName(vo.getContactName());
        contact.setPostCode(vo.getPostCode());
        contact.setTelephone(vo.getTelephone());

        org.setContact(contact);

        org.setCode(vo.getCode());
        org.setCreateTime(vo.getCreateTime());
        org.setDesc(vo.getDesc());
        org.setModifyDate(vo.getModifyDate());
        org.setName(vo.getName());
        org.setParentSSOCode(vo.getParentOrgSSOCode());
        org.setSsoCode(vo.getSsoCode());
        org.setStatus(vo.getStatus());
        org.setType(vo.getType());
    }
}
*/