package com.ft.common.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ft.common.security.OrgRepository;
import com.ft.commons.web.SpringWebUtils;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;

/**
 * 用于获取操作员会话信息
 * 
 */
public final class OperatorSessionHelper {
    private static OperatorSessionRepository userSessionRepository;

    private static OrgRepository orgRepository;

    /**
     * 获取代理商可访问营业厅，只有当登录组织为代理商时有效。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO[] getAccessBusiHallsOfAgent(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            List result = opSession.getAccessBusiHallIdsOfAgent();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request)
                        .getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL)
                    ret.add(orgDto);
            }
        }

        return (OrganizationDTO[]) ret.toArray(new OrganizationDTO[0]);
    }
    
    /**
     * 获取代理商可访问营业厅ID，只有当登录组织为代理商是有效。
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Long[] getAccessBusiHallIdssOfAgent(HttpServletRequest request){
        OperatorSession opSession = getOperatorSession(request);
        
        if (opSession != null) {
            List result = opSession.getAccessBusiHallIdsOfAgent();
            return (Long[])result.toArray(new Long[0]);
        }
        
        return new Long[0];
        
    }

    /**
     * 获取当前登录组织可访问营业厅和代理商营业厅。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO[] getAccessBusiHallsOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            List result = opSession.getAccessBusiHallIdsOfLoginOrg();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request)
                        .getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL)
                    ret.add(orgDto);
            }
        }

        return (OrganizationDTO[]) ret.toArray(new OrganizationDTO[0]);
    }
    
    /**
     * 获取当前登录组织可访问营业厅和代理商营业厅ID
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Long[] getAccessBusiHallIdssOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            List result = opSession.getAccessBusiHallIdsOfLoginOrg();
            return (Long[])result.toArray(new Long[0]);
        }

        return new Long[0];
    }

    /**
     * 获取当前登录组织可访问分公司。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO[] getAccessCompaniesOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            List result = opSession.getAccessCompanyIdsOfLoginOrg();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request)
                        .getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL)
                    ret.add(orgDto);
            }
        }

        return (OrganizationDTO[]) ret.toArray(new OrganizationDTO[0]);
    }
    
    /**
     * 获取当前登录组织可访问分公司ID。
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Long[] getAccessCompanyIdsOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);
        
        if (opSession != null) {
            List result = opSession.getAccessCompanyIdsOfLoginOrg();
            return (Long[])result.toArray(new Long[0]);
        }

        return new Long[0];
    }

    /**
     * 获取当前登录操作员可访问组织。
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List getAccessOrgsOfLoginOp(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            List result = opSession.getAccessOrgIdsOfOp();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request)
                        .getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL)
                    ret.add(orgDto);
            }
        }
        java.util.Collections.sort(ret);
        return ret;
    }
    
    /**
     * 获取当前登录操作员可访问组织ID。
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Long[] getAccessOrgIdsOfLoginOp(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            List result = opSession.getAccessOrgIdsOfOp();
            return (Long[])result.toArray(new Long[0]);
        }

        return new Long[0];
    }

    /**
     * 获取当前登录组织可访问数据区域。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO[] getAccessRegionsOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            List result = opSession.getAccessRegionIdsOfLoginOrg();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request)
                        .getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL)
                    ret.add(orgDto);
            }
        }

        return (OrganizationDTO[]) ret.toArray(new OrganizationDTO[0]);
    }
    
    /**
     * 获取当前登录组织可访问数据区域ID。
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Long[] getAccessRegionIdsOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            List result = opSession.getAccessRegionIdsOfLoginOrg();
            return (Long[])result.toArray(new Long[0]);
        }

        return new Long[0];
    }

    /**
     * 获取当前登录操作员所属分公司
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO getCompanyOfLoginOp(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            Long orgId = opSession.getLoginOpCompanyId();
            return getOrgRepository(request).getOrgDTOById(orgId);
        }

        return null;
    }

    /**
     * 获取当前登录组织所属分公司。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO getCompanyOfLoginOrg(
            HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            Long orgId = opSession.getLoginOrgCompanyId();
            return getOrgRepository(request).getOrgDTOById(orgId);
        }

        return null;

    }

    /**
     * 获取当前登录操作员。
     * 
     * @param request
     * @return
     */
    public static OperatorDTO getLoginOp(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null)
            return opSession.getLoginOp();

        return null;
    }

    /**
     * 获取当前登录组织。
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO getLoginOrg(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            Long orgId = opSession.getLoginOrgId();
            if(orgId != null)
                return getOrgRepository(request).getOrgDTOById(orgId);
        }

        return null;
    }

    /**
     * 获取当前操作员会话信息
     * 
     * @param request
     * @return
     */
    private static OperatorSession getOperatorSession(HttpServletRequest request) {
        OperatorSessionRepository sessionRepositoy = getUserSessionRepository(request);
        return sessionRepositoy.getUserSession(request.getSession().getId());
    }

    /**
     * 获取当前登录操作员所属组织
     * 
     * @param request
     * @return
     */
    public static OrganizationDTO getOrgOfLoginOp(HttpServletRequest request) {
        OperatorSession opSession = getOperatorSession(request);

        if (opSession != null) {
            OperatorDTO loginOp = opSession.getLoginOp();
            return new OrganizationDTO(loginOp.getOrg());
        }

        return null;
    }
    
    /**
     * 获取当前登录组织下所有子组织
     * @param request
     * @param includeAll  是否包括所有子孙组织。
     * @return
     */
    @SuppressWarnings("unchecked")
	public static OrganizationDTO[] getAccessOrgsOfLoginOrg(HttpServletRequest request,boolean includeAll){
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            //取得登录组织
            Long loginOrgId = opSession.getLoginOrgId();
            OrganizationDTO loginOrg = getOrgRepository(request).getOrgDTOById(loginOrgId);
            List result = opSession.getAccessOrgIdsOfOp();
            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Long orgId = (Long) iterator.next();
                OrganizationDTO orgDto = getOrgRepository(request).getOrgDTOById(orgId);
                if (orgDto != null && orgDto.getStatus() == OrganizationDTO.STATUS_NORMAL){
                    if(includeAll && orgDto.getPath().startsWith(loginOrg.getPath())){
                        if(!ret.contains(orgDto)) ret.add(orgDto);
                    }else if(!includeAll && orgDto.getParentOrgId().equals(loginOrg.getOrgId())){
                        if(!ret.contains(orgDto)) ret.add(orgDto);
                    }
                }
            }
        }
        
        java.util.Collections.sort(ret);

        return (OrganizationDTO[]) ret.toArray(new OrganizationDTO[0]);
    }

    /**
     * 获取组织机构仓库实例。
     * @param request
     * @return
     */
    private static OrgRepository getOrgRepository(HttpServletRequest request) {
        if (null == orgRepository) {
            orgRepository = (OrgRepository) SpringWebUtils.getBean(request,
                    OrgRepository.getBeanName());
        }

        return orgRepository;
    }

    /**
     * 获取操作员会话仓库实例。
     * 
     * @param request
     * @return
     */
    private static OperatorSessionRepository getUserSessionRepository(
            HttpServletRequest request) {
        if (null == userSessionRepository) {
            userSessionRepository = (OperatorSessionRepository) SpringWebUtils
                    .getBean(request, OperatorSessionRepository.getBeanName());
        }

        return userSessionRepository;
    }
}
