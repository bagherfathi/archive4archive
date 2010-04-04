package com.ft.web.sm.data.region;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.RegionManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.RegionDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.Region;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * 区域设置类
 * 
 * @struts.action path="/region" name="regionForm" scope="request"
 *                validate="false" parameter="act" input="sm.region.view"
 * 
 * @struts.action-forward name="edit" path="sm.region.edit"
 * 
 * @struts.tiles name="sm.region.view" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/region/viewRegion.jsp"
 * 
 * @spring.bean id="regionAction"
 * 
 * @version 1.0
 * 
 */
public class RegionAction extends BaseAction {
    private static Log logger = LogFactory.getLog(RegionAction.class);

    /**
     * 区域信息管理类
     */
    private RegionManager regionManager;

    /**
     * @spring.property ref="regionManager"
     * 
     * @param regionManager
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RegionForm regionForm = (RegionForm) form;
        RegionDTO region = regionManager.getRootRegion();
        regionForm.setRegion(region);
        return mapping.getInputForward();
    }

    /**
     * 浏览页面
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
        return mapping.getInputForward();
    }

    /**
     * 新建区域
     * 
     * @struts.tiles name="sm.region.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/region/editRegion.jsp"
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 编辑区域信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 保存区域信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RegionForm regionForm = (RegionForm) form;
        RegionDTO region = regionForm.getRegion();
        Long regionId = null;

        String actionCode = ActionDefinition.SYS_UPDATE_REGION;
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        
        RegionDTO existRegion = this.regionManager.findRegionByCode(region.getRegionCode());
        if(existRegion != null && !existRegion.getRegionId().equals(region.getRegionId())){
            String regionLocation = getRegionLocation(existRegion.getRegionId());
            ActionMessagesHelper.saveRequestMessage(request,
                    "sysadmin.error.region.code.exist",new Object[]{regionLocation});
            return mapping.findForward("edit");
        }

        try {
            if (region.getRegionId() == null
                    || region.getRegionId().longValue() <= 0) {
                actionCode = ActionDefinition.SYS_ADD_REGION;
                flag = XmlTreeNode.ADD_NODE_FLAG;
                RegionDTO parent = regionForm.getParentRegion();
                region.setParent((Region) parent.getTarget());
                regionId = this.regionManager.saveRegion(region);
            } else {
                this.regionManager.saveRegion(region);
                regionId = region.getRegionId();
            }
        } catch (Exception ex) {
            logger.log(request, actionCode, region.getRegionName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + region.getRegionName(), ex);
            throw ex;
        }

        logger.log(request, actionCode, region.getRegionName(),
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("region.do?&act=view&regionId="
                + regionId + "&flag=" + flag);
    }

    private String getRegionLocation(Long regionId) throws Exception{
        StringBuffer buffer = new StringBuffer();
        List result = this.regionManager.findRegionLocation(regionId);
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            RegionDTO object = (RegionDTO) iterator.next();
            if(buffer.length() ==0){
                buffer.append(object.getRegionName());
            }else{
                buffer.append("-");
                buffer.append(object.getRegionName());
            }
        }
        return buffer.toString();
    }
    /**
     * 禁用Region
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RegionForm regionForm = (RegionForm) form;
        RegionDTO region = regionForm.getRegion();
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        try {
            this.regionManager.disableRegion(region.getRegionId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DISABLE_REGION, region
                    .getRegionName(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DISABLE_REGION
                    + " failed,action sequence =" + region.getRegionName(), ex);
        }

        logger.log(request, ActionDefinition.SYS_DISABLE_REGION, region
                .getRegionName(), ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("region.do?act=view&regionId="
                + region.getRegionId() + "&flag=" + flag );

    }

    /**
     * 启用Region
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward enable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RegionForm regionForm = (RegionForm) form;
        RegionDTO region = regionForm.getRegion();
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        try {
            this.regionManager.enableRegion(region.getRegionId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ENABLE_REGION, region
                    .getRegionName(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_ENABLE_REGION
                    + " failed,action sequence =" + region.getRegionName(), ex);
        }

        logger.log(request, ActionDefinition.SYS_ENABLE_REGION, region
                .getRegionName(), ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("region.do?act=view&regionId="
                + region.getRegionId() + "&flag=" + flag);
    }
}
