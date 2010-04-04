package com.ft.web.sm.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.security.OrgRepository;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 检查新建组织时的可用组织类型。
 * 
 * @version 1.0
 * 
 */
public class OrgTypeTag extends TagSupport {

    private static final long serialVersionUID = 7210734196432105565L;

    private String orgId;

    private String var;

    public int doStartTag() throws JspException {
        List<Long> result = new ArrayList<Long>();

        OrgRepository orgRepository = (OrgRepository) SpringWebUtils.getBean(
                pageContext.getServletContext(), "orgRepository");
        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("orgId", this.orgId, Long.class,
                this, pageContext);
        OrganizationDTO orgDTO = orgRepository.getOrgDTOById(aId);

        if (orgDTO != null) {
            result.add(new Long(OrganizationDTO.ORG_TYPE_DEP));

            // 找出path中所有组织的类型
            String[] orgTreeIds = orgDTO.getPath().split(
                    OrganizationDTO.PATH_SEPARATOR);
            long[] orgTreeTypes = ArrayUtils.EMPTY_LONG_ARRAY;
            for (int i = 0; i < orgTreeIds.length; i++) {
                if (StringUtils.isNotEmpty(orgTreeIds[i])) {
                    OrganizationDTO org = orgRepository.getOrgDTOById(Long
                            .valueOf(orgTreeIds[i]));
                    orgTreeTypes = ArrayUtils.add(orgTreeTypes, org.getType());
                }
            }

            boolean containRegion = false;
            boolean containCompany = false;

            if (ArrayUtils.contains(orgTreeTypes,
                    OrganizationDTO.ORG_TYPE_REGION)) {
                containRegion = true;
            }
            if (ArrayUtils.contains(orgTreeTypes,
                    OrganizationDTO.ORG_TYPE_COMPANY)) {
                containCompany = true;
            }

            // 是否可以添加分公司 分公司只能建在根组织和分公司下
            if (orgDTO.isRoot()
                    || (orgDTO.getType() == OrganizationDTO.ORG_TYPE_COMPANY && !containRegion)) {
                result.add(new Long(OrganizationDTO.ORG_TYPE_COMPANY));
            }

            // 是否可以添加数据区域 数据区域只能建在分公司下，一个path下只能有一个数据区域
            if (containCompany && !containRegion) {
                result.add(new Long(OrganizationDTO.ORG_TYPE_REGION));
            }

            // 是否可以添加营业厅和代理商 营业厅和代理商只能建在数据区域下
            if (containRegion) {
                result.add(new Long(OrganizationDTO.ORG_TYPE_BUSIHALL));
                result.add(new Long(OrganizationDTO.ORG_TYPE_AGENT));
            }
        }
        this.pageContext.setAttribute(var, result);
        return SKIP_BODY;
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *            the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }
}
