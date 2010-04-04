package com.ft.common.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.login.MenuBuilder;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;

/**
 * ʵ�ֳ�ʼ�������ٲ���Ա�Ự
 * 
 * @spring.bean id="opSessionHandler"
 */
public class OperatorSessionHandler {
    private static final Log logger = LogFactory
            .getLog(OperatorSessionHandler.class);

    public static final String SESSION_SYSTEM_MENU = "system.menu.Session";

    private OrgManager orgManager;

    private ResourceManager resourceManager;

    private OperatorManager opManager;

    private OperatorSessionRepository sessionRepository;

    private MenuBuilder menuBuilder;

    private LoginOrgCookieGenerator loginOrgCookieGenerator;

    /**
     * ��ʼ������Ա�Ự
     * 
     * @param request
     *                Http����
     * @param principal
     *                ����Աʵ��
     */
    public void init(HttpServletRequest request, OperatorDTO op) throws Exception{
        // ��������Ա�Ự
        OperatorSession userSession = this.createOperatorSession(request,op);

        // �������Ա�Ự
        this.sessionRepository.addUserSession(userSession);

        // �����˵�
        MenuRepository menuRepository = this.menuBuilder
                .buildTree(userSession);

        // ����˵���Session��
        request.getSession().setAttribute(SESSION_SYSTEM_MENU,
                menuRepository);
    }

    /**
     * ���ò���Ա�Ự��Ϣ
     * 
     * @param request
     * @param principal
     */
    public void reset(HttpServletRequest request, HttpServletResponse response,
            Long orgId) {
        try {
            // ���²���Ա�Ự
            OperatorSession userSession = resetUserSession(request, response,
                    orgId);

            // �������Ա�Ự
            this.sessionRepository.addUserSession(userSession);

            // �����˵�
            MenuRepository menuRepository = this.menuBuilder
                    .buildTree(userSession);

            // ����˵���Session��
            request.getSession().setAttribute(SESSION_SYSTEM_MENU,
                    menuRepository);

        } catch (Exception ex) {
            logger.error("Initialize user session failed.", ex);
        }
    }

    /**
     * ��ʼ������Ա�Ự
     * 
     * @param httpServletRquest
     *                Http����
     * @param ssoCode
     *                ����Ա��SSOϵͳ�е�Ωһ����
     */
    public void init(HttpServletRequest request, String ssoCode)
            throws Exception {
        OperatorDTO principal = this.opManager.findOperatorBySSOCode(ssoCode);
        if (principal == null) {
            logger.error("Not fount the user,sso code is " + ssoCode);
            return;
        }

        this.init(request, principal);
    }

    /**
     * ���ٲ���Ա�Ự
     * 
     * @param httpServletRquest
     *                Http����
     */
    public void destroy(HttpServletRequest request) {
        // ���ٲ˵�
        request.getSession().removeAttribute(SESSION_SYSTEM_MENU);
        // ���ٲ���Ա�Ự
        sessionRepository.removeUserSession(request.getSession().getId());
    }

    /**
     * ���ٲ���Ա�Ự
     * 
     * @param session
     */
    public void destroy(HttpSession session) {
        session.removeAttribute(SESSION_SYSTEM_MENU);
        sessionRepository.removeUserSession(session.getId());
    }

    /**
     * ��������Ա�Ự��
     * 
     * @param request
     *                HTTP����
     * @param op
     *                ��¼����Ա��
     * @return
     */
    private OperatorSession createOperatorSession(HttpServletRequest request,
            OperatorDTO op) throws Exception {
        String sessionId = request.getSession().getId();
        OperatorSession opSession = new OperatorSession(sessionId);

        // ���õ�¼����Ա
        opSession.setLoginOp(op);

        // ���õ�¼����Ա�����ֹ�˾ID
        opSession.setLoginOpCompanyId(this.orgManager.findCompanyIdOfOrg(op
                .getOrgId()));

        // ���ÿɷ�����֯ID�б�
        List accessOrgIds = this.orgManager.findAllAccessOrgIdsOfOperatorIncludeChild(op
                .getOperatorId());
        
        opSession.setAccessOrgIdsOfOp(accessOrgIds);

        // ���õ�ǰ��¼��֯ID
        String loginOrgIdInCookie = this.loginOrgCookieGenerator
                .getCookieValue(request);
        if (loginOrgIdInCookie != null) {
            for (Iterator iterator = accessOrgIds.iterator(); iterator
                    .hasNext();) {
                Long orgId = (Long) iterator.next();
                if(loginOrgIdInCookie.equals(String.valueOf(orgId))){
                    opSession.setLoginOrgId(orgId);
                    break;
                }
            }
        }

        // ���ò���Ա�Ự��Ϣ
        this.setOperatorSession(opSession);

        return opSession;
    }

    /**
     * ����ָ���ĵ�½��֯ID�������ò���Ա�Ự��Ϣ
     * 
     * @param sessionId
     * @param orgId
     * @return
     */
    private OperatorSession resetUserSession(HttpServletRequest request,
            HttpServletResponse response, Long orgId) throws Exception {
        String sessionId = request.getSession().getId();

        // ��ȡ����Ա�Ự
        OperatorSession opSession = this.sessionRepository
                .getUserSession(sessionId);
        
        // ���ÿɷ�����֯ID�б�
        List accessOrgIds = this.orgManager.findAllAccessOrgIdsOfOperatorIncludeChild(opSession.getLoginOp()
                .getOperatorId());
        
        opSession.setAccessOrgIdsOfOp(accessOrgIds);

        // ���õ�ǰ��¼��֯
        opSession.setLoginOrgId(orgId);
        this.loginOrgCookieGenerator.addCookie(response, String.valueOf(orgId));

        // ���ò���Ա�Ự��Ϣ
        this.setOperatorSession(opSession);

        return opSession;
    }

    private void setOperatorSession(OperatorSession opSession) throws Exception {
        OperatorDTO op = opSession.getLoginOp();
        
        Long loginOrgId = opSession.getLoginOrgId();
        
        if(loginOrgId == null) return;

        OrganizationDTO loginOrg = this.orgManager.findOrgById(loginOrgId);

        // ���õ�¼��֯�����ֹ�˾ID
        opSession.setLoginOrgCompanyId(this.orgManager
                .findCompanyIdOfOrg(loginOrgId));

        // ���õ�¼��֯�ɷ��ʷֹ�˾ID�б�
        List result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_COMPANY, true);
        opSession.setAccessCompanyIdsOfLoginOrg(result);

        // ���õ�¼��֯�ɷ�����������ID�б�
        result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_REGION, true);
        opSession.setAccessRegionIdsOfLoginOrg(result);

        // ���õ�¼��֯�ɷ���Ӫҵ��ID�б�
        result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_BUSIHALL, true);
        opSession.setAccessBusiHallIdsOfLoginOrg(result);

        // ���ڴ����̣����ô����̿ɷ���Ӫҵ��ID�б�
        if (loginOrg.getType() == OrganizationDTO.ORG_TYPE_AGENT) {
            opSession.setAccessBusiHallIdsOfAgent(result);
        }else{
            opSession.setAccessBusiHallIdsOfAgent(new ArrayList());
        }

        // ����Ȩ�޼����
        opSession.setChecker(this.createPermissionChecker(op.getOperatorId(),
                loginOrg));
    }

    /**
     * ����Ȩ��У������
     * 
     * @param loginOpId
     *                ��¼����ԱID��
     * @param loginOrgId
     *                ��¼��֯ID��
     * @return
     * @throws Exception
     */
    private PermissionChecker createPermissionChecker(Long loginOpId,
            OrganizationDTO loginOrg) throws Exception {
        PermissionChecker checker = new PermissionChecker();

        if (loginOrg != null) {
            List resourceACL = this.resourceManager
                    .findAllACLResourcesOfOperator(loginOpId, loginOrg,
                            new Date());
            checker.addOrgPermission(loginOrg.getOrgId(), resourceACL);
        }

        return checker;
    }

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref="operatorManager"
     * @param opManager
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }

    /**
     * @spring.property ref="opSessionRepository"
     * @param sessionRepository
     *                The sessionRepository to set.
     */
    public void setSessionRepository(OperatorSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * @spring.property ref="menuBuilder"
     * @param menuBuilder
     *                The menuBuilder to set.
     */
    public void setMenuBuilder(MenuBuilder menuBuilder) {
        this.menuBuilder = menuBuilder;
    }

    public static String getBeanName() {
        return "opSessionHandler";
    }

    /**
     * @spring.property ref="loginOrgCookieGenerator"
     * @param loginOrgCookieGenerator
     *                the loginOrgCookieGenerator to set
     */
    public void setLoginOrgCookieGenerator(
            LoginOrgCookieGenerator loginOrgCookieGenerator) {
        this.loginOrgCookieGenerator = loginOrgCookieGenerator;
    }
}
