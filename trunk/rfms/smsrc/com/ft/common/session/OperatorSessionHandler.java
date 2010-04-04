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
 * 实现初始化和销毁操作员会话
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
     * 初始化操作员会话
     * 
     * @param request
     *                Http请求
     * @param principal
     *                操作员实体
     */
    public void init(HttpServletRequest request, OperatorDTO op) throws Exception{
        // 创建操作员会话
        OperatorSession userSession = this.createOperatorSession(request,op);

        // 保存操作员会话
        this.sessionRepository.addUserSession(userSession);

        // 构建菜单
        MenuRepository menuRepository = this.menuBuilder
                .buildTree(userSession);

        // 保存菜单到Session中
        request.getSession().setAttribute(SESSION_SYSTEM_MENU,
                menuRepository);
    }

    /**
     * 重置操作员会话信息
     * 
     * @param request
     * @param principal
     */
    public void reset(HttpServletRequest request, HttpServletResponse response,
            Long orgId) {
        try {
            // 更新操作员会话
            OperatorSession userSession = resetUserSession(request, response,
                    orgId);

            // 保存操作员会话
            this.sessionRepository.addUserSession(userSession);

            // 构建菜单
            MenuRepository menuRepository = this.menuBuilder
                    .buildTree(userSession);

            // 保存菜单到Session中
            request.getSession().setAttribute(SESSION_SYSTEM_MENU,
                    menuRepository);

        } catch (Exception ex) {
            logger.error("Initialize user session failed.", ex);
        }
    }

    /**
     * 初始化操作员会话
     * 
     * @param httpServletRquest
     *                Http请求
     * @param ssoCode
     *                操作员在SSO系统中的惟一代码
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
     * 销毁操作员会话
     * 
     * @param httpServletRquest
     *                Http请求
     */
    public void destroy(HttpServletRequest request) {
        // 销毁菜单
        request.getSession().removeAttribute(SESSION_SYSTEM_MENU);
        // 销毁操作员会话
        sessionRepository.removeUserSession(request.getSession().getId());
    }

    /**
     * 销毁操作员会话
     * 
     * @param session
     */
    public void destroy(HttpSession session) {
        session.removeAttribute(SESSION_SYSTEM_MENU);
        sessionRepository.removeUserSession(session.getId());
    }

    /**
     * 创建操作员会话。
     * 
     * @param request
     *                HTTP请求
     * @param op
     *                登录操作员。
     * @return
     */
    private OperatorSession createOperatorSession(HttpServletRequest request,
            OperatorDTO op) throws Exception {
        String sessionId = request.getSession().getId();
        OperatorSession opSession = new OperatorSession(sessionId);

        // 设置登录操作员
        opSession.setLoginOp(op);

        // 设置登录操作员所属分公司ID
        opSession.setLoginOpCompanyId(this.orgManager.findCompanyIdOfOrg(op
                .getOrgId()));

        // 设置可访问组织ID列表
        List accessOrgIds = this.orgManager.findAllAccessOrgIdsOfOperatorIncludeChild(op
                .getOperatorId());
        
        opSession.setAccessOrgIdsOfOp(accessOrgIds);

        // 设置当前登录组织ID
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

        // 设置操作员会话信息
        this.setOperatorSession(opSession);

        return opSession;
    }

    /**
     * 根据指定的登陆组织ID重新设置操作员会话信息
     * 
     * @param sessionId
     * @param orgId
     * @return
     */
    private OperatorSession resetUserSession(HttpServletRequest request,
            HttpServletResponse response, Long orgId) throws Exception {
        String sessionId = request.getSession().getId();

        // 获取操作员会话
        OperatorSession opSession = this.sessionRepository
                .getUserSession(sessionId);
        
        // 设置可访问组织ID列表
        List accessOrgIds = this.orgManager.findAllAccessOrgIdsOfOperatorIncludeChild(opSession.getLoginOp()
                .getOperatorId());
        
        opSession.setAccessOrgIdsOfOp(accessOrgIds);

        // 设置当前登录组织
        opSession.setLoginOrgId(orgId);
        this.loginOrgCookieGenerator.addCookie(response, String.valueOf(orgId));

        // 设置操作员会话信息
        this.setOperatorSession(opSession);

        return opSession;
    }

    private void setOperatorSession(OperatorSession opSession) throws Exception {
        OperatorDTO op = opSession.getLoginOp();
        
        Long loginOrgId = opSession.getLoginOrgId();
        
        if(loginOrgId == null) return;

        OrganizationDTO loginOrg = this.orgManager.findOrgById(loginOrgId);

        // 设置登录组织所属分公司ID
        opSession.setLoginOrgCompanyId(this.orgManager
                .findCompanyIdOfOrg(loginOrgId));

        // 设置登录组织可访问分公司ID列表
        List result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_COMPANY, true);
        opSession.setAccessCompanyIdsOfLoginOrg(result);

        // 设置登录组织可访问数据区域ID列表
        result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_REGION, true);
        opSession.setAccessRegionIdsOfLoginOrg(result);

        // 设置登录组织可访问营业厅ID列表
        result = this.orgManager.findAccessOrgIdsOfOrg(loginOrgId,
                OrganizationDTO.ORG_TYPE_BUSIHALL, true);
        opSession.setAccessBusiHallIdsOfLoginOrg(result);

        // 对于代理商，设置代理商可访问营业厅ID列表
        if (loginOrg.getType() == OrganizationDTO.ORG_TYPE_AGENT) {
            opSession.setAccessBusiHallIdsOfAgent(result);
        }else{
            opSession.setAccessBusiHallIdsOfAgent(new ArrayList());
        }

        // 设置权限检查器
        opSession.setChecker(this.createPermissionChecker(op.getOperatorId(),
                loginOrg));
    }

    /**
     * 创建权限校验器。
     * 
     * @param loginOpId
     *                登录操作员ID。
     * @param loginOrgId
     *                登录组织ID。
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
