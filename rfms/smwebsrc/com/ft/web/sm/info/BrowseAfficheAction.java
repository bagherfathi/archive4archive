package com.ft.web.sm.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.InfoManager;
import com.ft.common.infoShared.AfficheUrl;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.BaseAction;

/**
 * 公告浏览控制类
 * 
 * @spring.bean id="browseAfficheAction"
 * 
 * @struts.action name="afficheForm" path="/browse" scope="request"
 *                input="sm.info.browse.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.info.browse.index"
 *               page="/WEB-INF/jsp/sm/info/browseAffiche.jsp"
 * 
 * @struts.action-forward name="list" path="sm.info.browse.list"
 * 
 * @struts.action-forward name="view" path="sm.info.browse.view"
 * 
 * @version 1.0
 */
public class BrowseAfficheAction extends BaseAction {

    private InfoManager infoManager;
    
    private AfficheUrl afficheUrl;

    /**
     * @spring.property ref="afficheUrl"
     * @param url the url to set
     */
    public void setAfficheUrl(AfficheUrl afficheUrl) {
        this.afficheUrl = afficheUrl;
    }

    /**
     * @spring.property ref="infoManager"
     * @param infoManager
     */
    public void setInfoManager(InfoManager infoManager) {
        this.infoManager = infoManager;
    }

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrganizationDTO org = OperatorSessionHelper.getLoginOrg(request);
        OperatorDTO op = OperatorSessionHelper.getLoginOp(request);
        String param = "";
        if(StringUtils.contains(afficheUrl.getUrl(),"?")){
            param = "&opId="
                + op.getOperatorId().toString() + "&orgId="
                + org.getOrgId().toString();
        }else{
            param = "?opId="
                + op.getOperatorId().toString() + "&orgId="
                + org.getOrgId().toString();
        }
        request.setAttribute("url", afficheUrl.getUrl() + param );
        return mapping.getInputForward();
    }

    /**
     * 公告浏览列表
     * 
     * @struts.tiles name="sm.info.browse.list"
     *               page="/WEB-INF/jsp/sm/info/listBrowseAffiche.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param respons
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // AfficheForm afficheForm = (AfficheForm) form;
        // Organization org = afficheForm.getCurrentUser().getOrg();
        // List allAffiche = this.infoManager.findAllAfficheByOrg(new Long(org
        // .getOrgId()));
      //  OrganizationDTO org = OperatorSessionHelper.getLoginOrg(request);
        String orgId = request.getParameter("orgId");
        List allAffiche = this.infoManager.findAllAfficheByOrg(Long.valueOf(orgId));
        request.setAttribute("affiches", allAffiche);
        request.setAttribute("opId", OperatorSessionHelper.getLoginOp(request)
                .getOperatorId().toString());
        return mapping.findForward("list");
    }

    /**
     * 根据公告的优先级对公告进行处理
     * 
     * @param afficheList
     * @return
     */
    @SuppressWarnings("unused")
	private List generatorAfficheList(List afficheList) {
        List<AfficheDTO> showAfficheList = new ArrayList<AfficheDTO>();
        for (int i = 1; i <= 9; i++) {
            for (Iterator iterator = afficheList.iterator(); iterator.hasNext();) {
                AfficheDTO affiche = (AfficheDTO) iterator.next();
                if (affiche.getLevel() >= i) {
                    showAfficheList.add(affiche);
                }
            }
        }

        return showAfficheList;

        /*
         * int total = 0; for (Iterator iterator = afficheList.iterator();
         * iterator.hasNext();) { AfficheDTO object = (AfficheDTO)
         * iterator.next(); total += object.getLevel(); }
         * 
         * List result = new ArrayList(); Map selNumberMap = new HashMap(); int
         * totalNumbe = total; while(result.size() < total){ AfficheDTO affiche =
         * selectAffiche(afficheList,totalNumbe); if(affiche != null){ Long
         * number = (Long)selNumberMap.get(affiche.getAfficheId()); if(number ==
         * null){ result.add(affiche); selNumberMap.put(affiche.getAfficheId(),
         * new Long(1)); }else{ if(number.longValue() < affiche.getLevel()){
         * result.add(affiche); selNumberMap.put(affiche.getAfficheId(), new
         * Long(number.longValue() + 1)); }else{ afficheList.remove(affiche);
         * for (Iterator iterator = afficheList.iterator(); iterator.hasNext();) {
         * AfficheDTO object = (AfficheDTO) iterator.next(); totalNumbe +=
         * object.getLevel(); } } } } }
         * 
         * return result;
         */
    }

    /**
     * 选中一个公告
     * 
     * @return
     */
    /*
     * private AfficheDTO selectAffiche(List afficheList,int total){ int index =
     * 0; Random random = new Random();
     * 
     * index = 0; int randomNum = random.nextInt(total); for (Iterator iterator =
     * afficheList.iterator(); iterator.hasNext();) { AfficheDTO affiche =
     * (AfficheDTO) iterator.next(); if(randomNum >=index && randomNum <= index +
     * affiche.getLevel()){ return affiche; }
     * 
     * index = index + new Long(affiche.getLevel()).intValue() + 1; }
     * 
     * return null; }
     */

    /**
     * 公告查看
     * 
     * @struts.tiles name="sm.info.browse.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/viewAfficheInfo.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param respons
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("view");
    }


}
