package com.ft.web.sm.param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.ConfigParamManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.web.sm.priv.org.OrgAction;

/**
 * 系统参数配置Action类
 * 
 * @version 1.0
 * 
 * @spring.bean id="configParamAction"
 * 
 * @struts.action name="configParamForm" path="/param" scope="request"
 *                input="sm.param.list" parameter="act" validate="false"
 * 
 * @struts.action-forward name="create" path="sm.param.create"
 * @struts.action-forward name="edit" path="sm.param.edit"
 * @struts.action-forward name="list" path="sm.param.list"
 * @struts.action-forward name="nodeEdit" path="sm.param.node.edit"
 * @struts.action-forward name="nodeCreate" path="sm.param.node.create"
 * 
 * @struts.tiles name="sm.param.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/param/listParam.jsp"
 */
public class ConfigParamAction extends BaseAction {

    private static Log logger = LogFactory.getLog(OrgAction.class);

    private ConfigParamManager configParamManager;

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ConfigParamForm paramForm = (ConfigParamForm) form;
        // List params = new ArrayList();
        ConfigParamDTO parent = paramForm.getParent();
        if (parent == null) {
            parent = configParamManager.findRootParamNode();
            paramForm.setParent(parent);
            paramForm.setParentId(parent.getConfigId().toString());
        }
        // params =
        // configParamManager.findParamsByParentId(parent.getConfigId());
        // request.setAttribute("configParams", params);
        return mapping.findForward("list");
    }

    /**
     * 跳转到新建参数页
     * 
     * @struts.tiles name="sm.param.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/param/createParam.jsp"
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

        return mapping.findForward("create");
    }

    /**
     * 跳转到新建节点页
     * 
     * @struts.tiles name="sm.param.node.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/param/createNode.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createNode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ConfigParamForm paramForm = (ConfigParamForm) form;
        paramForm.setParamId(paramForm.getParentId().toString());
        return mapping.findForward("nodeCreate");
    }

    /**
     * 跳转到编辑参数页面
     * 
     * @struts.tiles name="sm.param.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/param/editParam.jsp"
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
     * 跳转到编辑节点页面
     * 
     * @struts.tiles name="sm.param.node.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/param/editNode.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editNode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("nodeEdit");
    }

    /**
     * 保存参数
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
        ConfigParamForm paramForm = (ConfigParamForm) form;
        Long id;
        ConfigParamDTO param = paramForm.getConfigParam();
        try {
            id = this.configParamManager
                    .saveParam(param, paramForm.getParent());
            param.setConfigId(id);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_PARAM, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Execute action " + ActionDefinition.SYS_ADD_PARAM
                    + " failed,action sequence =" + "", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_ADD_PARAM, param
                .getConfigCode(), ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("param.do?parentId="
                + paramForm.getParent().getConfigId().longValue());
    }

    /**
     * 保存节点
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveNode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ConfigParamForm paramForm = (ConfigParamForm) form;
        Long id;
        ConfigParamDTO param = paramForm.getConfigParam();
        try {
            id = this.configParamManager
                    .saveParam(param, paramForm.getParent());
            param.setConfigId(id);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_PARAM, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Execute action " + ActionDefinition.SYS_ADD_PARAM
                    + " failed,action sequence =" + "", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_ADD_PARAM, param
                .getConfigCode(), ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("param.do?act=editNode&paramId="
                        + paramForm.getConfigParam().getConfigId().longValue()
                        + "&refresh=true");
    }

    /**
     * 更新参数
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ConfigParamForm paramForm = (ConfigParamForm) form;
        ConfigParamDTO param = paramForm.getConfigParam();
        try {
            this.configParamManager.updateParam(param);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_PARAM, param
                    .getConfigCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_UPDATE_PARAM
                    + " failed,action sequence =" + param.getConfigCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_UPDATE_PARAM, param
                .getConfigCode(), ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("param.do?act=edit&paramId="
                + param.getConfigId().longValue());
    }

    /**
     * 更新节点
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateNode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ConfigParamForm paramForm = (ConfigParamForm) form;
        ConfigParamDTO param = paramForm.getConfigParam();
        try {
            this.configParamManager.updateParam(param);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_PARAM, param
                    .getConfigCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_UPDATE_PARAM
                    + " failed,action sequence =" + param.getConfigCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_UPDATE_PARAM, param
                .getConfigCode(), ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("param.do?act=editNode&paramId="
                        + param.getConfigId().longValue() + "&refresh=true");
    }

    /**
     * @spring.property ref="configParamManager"
     * @param configParamManager
     *                The configParamManager to set.
     */
    public void setConfigParamManager(ConfigParamManager configParamManager) {
        this.configParamManager = configParamManager;
    }

}
