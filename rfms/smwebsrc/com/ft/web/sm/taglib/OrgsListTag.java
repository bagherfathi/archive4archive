package com.ft.web.sm.taglib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.OrgManager;
import com.ft.common.security.OrgRepository;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 组织下拉列表标签
 * 
 * @version 1.0
 */
public class OrgsListTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String inputName;

    private String orgType;

    private String orgId;

    private String selOrgId;

    private String onchange;

    private boolean defaultChoice = false; // 是否支持不选择
    
    private boolean emptyChoice = false; //是否支持请选择项，该项值为""

    @SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {

        HttpServletRequest request = (HttpServletRequest) this.pageContext
                .getRequest();
        OrgRepository orgRepository = (OrgRepository) SpringWebUtils.getBean(
                this.pageContext, "orgRepository");

        Evaluator aEvaluator = new Evaluator();
        Long aOrgType = (Long) aEvaluator.evaluate("orgType", this.orgType,
                Long.class, this, pageContext);

        if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_AGENT) {
            aOrgType = new Long(OrganizationDTO.ORG_TYPE_BUSIHALL);
        }

        OrganizationDTO[] childrens = new OrganizationDTO[0];
        Long aOrgId = null;
        Long aSelOrgId = null;
        if (StringUtils.isNotEmpty(selOrgId)) {
            try {
                aSelOrgId = (Long) aEvaluator.evaluate("selOrgId",
                        this.selOrgId, Long.class, this, pageContext);
            } catch (Exception ex) {

            }
        }

        if (StringUtils.isEmpty(orgId)) {
            if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_COMPANY) {
                childrens = OperatorSessionHelper
                        .getAccessCompaniesOfLoginOrg(request);
            } else if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_REGION) {
                childrens = OperatorSessionHelper
                        .getAccessRegionsOfLoginOrg(request);
            } else if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_BUSIHALL) {
                childrens = OperatorSessionHelper
                        .getAccessBusiHallsOfLoginOrg(request);
            } else if (aOrgType.longValue() == -2) {
                childrens = OperatorSessionHelper.getAccessOrgsOfLoginOrg(
                        request, true);
            } else {
                List childrensList = OperatorSessionHelper
                        .getAccessOrgsOfLoginOp(request);
                java.util.Collections.sort(childrensList);
                childrens = (OrganizationDTO[]) childrensList
                        .toArray(new OrganizationDTO[0]);
            }
        } else {
            aOrgId = (Long) aEvaluator.evaluate("orgId", this.orgId,
                    Long.class, this, pageContext);
            OrgManager orgManager = (OrgManager) SpringWebUtils.getBean(
                    this.pageContext, "orgManager");

            try {
                List childrensList = orgManager.findAccessOrgsOfOrg(aOrgId,
                        aOrgType.longValue(), true);
                
                //modifiyed by libf,2007/03/22
                //childrens = (OrganizationDTO[]) childrensList
                //        .toArray(new OrganizationDTO[childrensList.size()]);
                List result = new ArrayList();
                for (Iterator iterator = childrensList.iterator(); iterator.hasNext();) {
                    OrganizationDTO orgDto = (OrganizationDTO) iterator.next();
                    if(orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL){
                        result.add(orgDto);
                    }
                }
                childrens = (OrganizationDTO[]) result
                        .toArray(new OrganizationDTO[result.size()]);
                //end modified
            } catch (Exception ex) {
                throw new JspException(ex);
            }
        }

        Map temp = new HashMap();
        List result = new ArrayList();

        String allIds = "";

        for (int i = 0; i < childrens.length; i++) {
            if (temp.get(childrens[i].getOrgId()) == null) {
                temp.put(childrens[i].getOrgId(), childrens[i].getName());
                result.add(childrens[i].getOrgId());
                getOrgs(childrens[i].getPath(), childrens[i], Arrays
                        .asList(childrens), temp, result, orgRepository,
                        aOrgType);
            }
            if (defaultChoice) {
                allIds = allIds + childrens[i].getOrgId().toString();
                if (i != childrens.length - 1) {
                    allIds = allIds + ",";
                }
            }
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("<select name=\"").append(inputName).append("\"");
        if (StringUtils.isNotEmpty(this.onchange)) {
            buffer.append(" onchange=\"").append(this.onchange).append("\"");
        }
        buffer.append(">");
        if (defaultChoice) {
            buffer.append("<option value=\"").append(allIds).append("\"> ");
            buffer.append("请选择");
            buffer.append("</option>");
        }else if(this.emptyChoice){
            buffer.append("<option value=\"").append("\"> ");
            buffer.append("请选择");
            buffer.append("</option>");
        }
        
        for (Iterator iter = result.iterator(); iter.hasNext();) {
            Long element = (Long) iter.next();
            String orgName = (String) temp.get(element);
            buffer.append("<option value=\"").append(element.toString())
                    .append("\" ");
            if (aSelOrgId != null && aSelOrgId.equals(element)) {
                buffer.append("selected");
            }
            buffer.append(" >");
            buffer.append(orgName);
            buffer.append("</option>");
        }
        buffer.append("</select>");
        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        return SKIP_BODY;
    }

    @SuppressWarnings("unchecked")
	private void getOrgs(String topOrgPath, OrganizationDTO org,
            List childrens, Map map, List result, OrgRepository orgRepository,
            Long orgType) {
        for (Iterator iter = childrens.iterator(); iter.hasNext();) {
            OrganizationDTO element = (OrganizationDTO) iter.next();
            if (element.getParentOrgId().equals(org.getOrgId())
                    && !element.isRoot()) {
                if (map.get(id) == null) {
                    if (element != null
                            && element.getPath().startsWith(org.getPath())) {
                        addOrg(topOrgPath, element, orgRepository, map, orgType
                                .longValue());
                        //modified by libf,2007/03/22
                        //result.add(element.getOrgId());
                        if(!result.contains(element.getOrgId())){
                            result.add(element.getOrgId());
                        }
                        //end modified
                        getOrgs(topOrgPath, element, childrens, map, result,
                                orgRepository, orgType);
                    }
                }
            }
        }
    }

    /**
     * 处理组织的名称,根据级别在组织名称前添加"-" ,排除类型为部门的组织
     */
    @SuppressWarnings("unchecked")
	private void addOrg(String topPath, OrganizationDTO org,
            OrgRepository orgRepository, Map map, long orgType) {
        int index = 0;
        String[] p = StringUtils.remove(org.getPath(), topPath).split(
                OrganizationDTO.PATH_SEPARATOR);
        for (int i = 0; i < p.length; i++) {
            if (StringUtils.isNotEmpty(p[i])) {
                OrganizationDTO aOrg = orgRepository.getOrgDTOById(Long
                        .valueOf(p[i]));
                //modified by libf,2006/03/22
                //if (aOrg != null
                //        && (aOrg.getType() != OrganizationDTO.ORG_TYPE_DEP)) {
                //    index++;
                //}
                if(aOrg != null){
                    index ++;
                }
                //end modified
            }
        }

        /*
         * String[] orgIds =
         * org.getPath().split(OrganizationDTO.PATH_SEPARATOR); String name =
         * org.getName(); if(orgIds.length > 2){ for (int i = 2; i <
         * orgIds.length; i++) { name = "-" + name; } }
         */
        String name = StringUtils.repeat("-", index) + org.getName();
        map.put(org.getOrgId(), name);
    }

    /**
     * @return the inputName
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * @param inputName
     *            the inputName to set
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     *            the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the onchange
     */
    public String getOnchange() {
        return onchange;
    }

    /**
     * @param onchange
     *            the onchange to set
     */
    public void setOnchange(String onchange) {
        this.onchange = onchange;
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
     * @return the selOrgId
     */
    public String getSelOrgId() {
        return selOrgId;
    }

    /**
     * @param selOrgId
     *            the selOrgId to set
     */
    public void setSelOrgId(String selOrgId) {
        this.selOrgId = selOrgId;
    }

    /**
     * @return the defaultChoice
     */
    public boolean isDefaultChoice() {
        return defaultChoice;
    }

    /**
     * @param defaultChoice
     *            the defaultChoice to set
     */
    public void setDefaultChoice(boolean defaultChoice) {
        this.defaultChoice = defaultChoice;
    }
    
    public boolean isEmptyChoice() {
        return emptyChoice;
    }

    public void setEmptyChoice(boolean emptyChoice) {
        this.emptyChoice = emptyChoice;
    }

}
