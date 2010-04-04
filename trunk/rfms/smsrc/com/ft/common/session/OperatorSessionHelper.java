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
 * ���ڻ�ȡ����Ա�Ự��Ϣ
 * 
 */
public final class OperatorSessionHelper {
    private static OperatorSessionRepository userSessionRepository;

    private static OrgRepository orgRepository;

    /**
     * ��ȡ�����̿ɷ���Ӫҵ����ֻ�е���¼��֯Ϊ������ʱ��Ч��
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
     * ��ȡ�����̿ɷ���Ӫҵ��ID��ֻ�е���¼��֯Ϊ����������Ч��
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
     * ��ȡ��ǰ��¼��֯�ɷ���Ӫҵ���ʹ�����Ӫҵ����
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
     * ��ȡ��ǰ��¼��֯�ɷ���Ӫҵ���ʹ�����Ӫҵ��ID
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
     * ��ȡ��ǰ��¼��֯�ɷ��ʷֹ�˾��
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
     * ��ȡ��ǰ��¼��֯�ɷ��ʷֹ�˾ID��
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
     * ��ȡ��ǰ��¼����Ա�ɷ�����֯��
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
     * ��ȡ��ǰ��¼����Ա�ɷ�����֯ID��
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
     * ��ȡ��ǰ��¼��֯�ɷ�����������
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
     * ��ȡ��ǰ��¼��֯�ɷ�����������ID��
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
     * ��ȡ��ǰ��¼����Ա�����ֹ�˾
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
     * ��ȡ��ǰ��¼��֯�����ֹ�˾��
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
     * ��ȡ��ǰ��¼����Ա��
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
     * ��ȡ��ǰ��¼��֯��
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
     * ��ȡ��ǰ����Ա�Ự��Ϣ
     * 
     * @param request
     * @return
     */
    private static OperatorSession getOperatorSession(HttpServletRequest request) {
        OperatorSessionRepository sessionRepositoy = getUserSessionRepository(request);
        return sessionRepositoy.getUserSession(request.getSession().getId());
    }

    /**
     * ��ȡ��ǰ��¼����Ա������֯
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
     * ��ȡ��ǰ��¼��֯����������֯
     * @param request
     * @param includeAll  �Ƿ��������������֯��
     * @return
     */
    @SuppressWarnings("unchecked")
	public static OrganizationDTO[] getAccessOrgsOfLoginOrg(HttpServletRequest request,boolean includeAll){
        OperatorSession opSession = getOperatorSession(request);

        List<OrganizationDTO> ret = new ArrayList<OrganizationDTO>();

        if (opSession != null) {
            //ȡ�õ�¼��֯
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
     * ��ȡ��֯�����ֿ�ʵ����
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
     * ��ȡ����Ա�Ự�ֿ�ʵ����
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
