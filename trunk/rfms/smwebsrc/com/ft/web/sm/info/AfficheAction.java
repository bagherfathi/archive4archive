package com.ft.web.sm.info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.InfoManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.utils.DateUtil;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * ������Ϣ������
 * 
 * @spring.bean id="afficheAction"
 * 
 * @struts.action name="afficheForm" path="/affiche" scope="request"
 *                input="sm.info.affiche.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.info.affiche.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/info/indexAffiche.jsp"
 * 
 * @struts.action-forward name="create" path="sm.info.affiche.create"
 * 
 * @struts.action-forward name="view" path="sm.info.affiche.view"
 * 
 * @struts.action-forward name="edit" path="sm.info.affiche.edit"
 * 
 * @version 1.0
 */
public class AfficheAction extends BaseAction {

    // ��¼��־��Ϣ
    private static Log logger = LogFactory.getLog(AfficheAction.class);

    private InfoManager infoManager;

    /**
     * @spring.property ref="infoManager"
     * @param infoManager
     */
    public void setInfoManager(InfoManager infoManager) {
        this.infoManager = infoManager;
    }
    
    /**
     * Ĭ�Ϸ���
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AfficheForm afficheForm = (AfficheForm) form;
        OperatorDTO loginOp = afficheForm.getCurrentUser();
        if(!"admin".equals(loginOp.getLoginName()))
            request.setAttribute("publisherId", OperatorSessionHelper.getLoginOp(request).getOperatorId());
        request.setAttribute("loginOp", loginOp);
        String endTime = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:dd");
        String beginTime = DateFormatUtils.format(System.currentTimeMillis()- 31* DateUtil.MILLSECOND_OF_DAY, "yyyy-MM-dd");
        //return mapping.getInputForward();
        return this.getRedirectForwardAction("affiche.do?act=search&publishTime.beginTime="+beginTime+"&publishTime.endTime=" + endTime);
    }

    /**
     * ��ѯ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AfficheForm afficheForm = (AfficheForm) form;
        OperatorDTO loginOp = afficheForm.getCurrentUser();
        if(!"admin".equals(loginOp.getLoginName()))
            request.setAttribute("publisherId", OperatorSessionHelper.getLoginOp(request).getOperatorId());
        request.setAttribute("loginOp", OperatorSessionHelper.getLoginOp(request));
        return mapping.getInputForward();
    }

    /**
     * �½�
     * 
     * @struts.tiles name="sm.info.affiche.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/addAffiche.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // ��ת������ҳ��
        if (request.getMethod().equals("GET")) {
            return mapping.findForward("create");
        }

        // ��ý�����֯
        String[] orgIds = request.getParameterValues("orgIds");
        List<Long> orgIdList = new ArrayList<Long>();
        for (int i = 0; i < orgIds.length; i++) {
            String orgId = orgIds[i];
            if (orgId != null && orgId.length() > 0) {
                orgIdList.add(new Long(orgId));
            }
        }

        // ����������Ϣ
        AfficheForm afficheForm = (AfficheForm) form;
        AfficheDTO affiche = afficheForm.getAffiche();

        String actionCode = ActionDefinition.SYS_ADD_INFO_AFFICHE;
        String title = affiche.getTitle();
        try {
            infoManager.createAffiche(affiche, afficheForm.getCurrentUser(),
                    (Long[]) orgIdList.toArray(new Long[0]));
        } catch (Exception e) {
            logger
                    .log(request, actionCode, title,
                            ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + title, e);
            throw e;
        }
        // ��¼�����ɹ��Ĳ�����־
        logger.log(request, actionCode, title, ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("affiche.do");
        //return mapping.getInputForward();
    }

    /**
     * �鿴������Ϣ
     * 
     * @struts.tiles name="sm.info.affiche.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/viewAffiche.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("view");
    }

    /**
     * ��ת������ҳ��
     * 
     * @struts.tiles name="sm.info.affiche.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/editAffiche.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("edit");
    }

    /**
     * ɾ��������Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // �������룺ɾ��������Ϣ
        String actionCode = ActionDefinition.SYS_DELETE_INFO_AFFICHE;
        AfficheForm afficheForm = (AfficheForm) form;
        AfficheDTO affiche = afficheForm.getAffiche();
        String title = affiche.getTitle();
        try {
            // ɾ��
            infoManager.deleteAffiche(affiche.getAfficheId());
        } catch (Exception e) {
            // ��¼ɾ��������Ϣʧ����־
            logger
                    .log(request, actionCode, title,
                            ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + title, e);
            throw e;
        }
        // ��¼ɾ���ɹ��Ĳ�����־
        logger.log(request, actionCode, title, ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ���¹�����Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param respons
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse respons)
            throws Exception {
        // ��ý�����֯
        String[] orgIds = request.getParameterValues("orgIds");
        List<Long> orgIdList = new ArrayList<Long>();
        for (int i = 0; i < orgIds.length; i++) {
            String orgId = orgIds[i];
            if (orgId != null && orgId.length() > 0) {
                orgIdList.add(new Long(orgId));
            }
        }

        // ���¹�����Ϣ
        AfficheForm afficheForm = (AfficheForm) form;
        AfficheDTO affiche = afficheForm.getAffiche();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date publishTime = format.parse(afficheForm.getCreateTime());
        affiche.setPublishTime(publishTime);
        
        String actionCode = ActionDefinition.SYS_UPDATE_INFO_AFFICHE;
        String title = affiche.getTitle();
        try {
            infoManager.saveAffiche(affiche, (Long[]) orgIdList
                    .toArray(new Long[0]));
        } catch (Exception e) {
            logger
                    .log(request, actionCode, title,
                            ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + title, e);
            throw e;
        }
        // ��¼�����ɹ��Ĳ�����־
        logger.log(request, actionCode, title, ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }
}
