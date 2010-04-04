package com.ft.web.sm.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.busi.sm.model.InfoManager;
import com.ft.common.infoShared.InfoSharedFile;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.commons.stream.StreamUtils;

/**
 * 信息共享设置类
 * 
 * @spring.bean id="infoSharedAction"
 * 
 * @struts.action name="infoSharedForm" path="/infoShared" scope="request"
 *                input="sm.info.shared.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.info.shared.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/info/indexShared.jsp"
 * 
 * @struts.action-forward name="categoryList" path="sm.info.shared.categoryList"
 * 
 * @struts.action-forward name="search" path="sm.info.shared.search"
 * 
 * @struts.action-forward name="view" path="sm.info.shared.view"
 * 
 * @struts.action-forward name="publish" path="sm.info.shared.publish"
 * 
 * @struts.action-forward name="edit" path="sm.info.shared.edit"
 * 
 * @version 1.0
 */
public class InfoSharedAction extends BaseAction {

    private static Log logger = LogFactory.getLog(InfoSharedAction.class);

    private InfoManager infoManager;

    private InfoSharedFile infoSharedFile;

    /**
     * 附件保存地址
     */
    // private Resource filePath;
    /**
     * @spring.property ref="infoManager"
     * @param infoManager
     */
    public void setInfoManager(InfoManager infoManager) {
        this.infoManager = infoManager;
    }

    /**
     * @spring.property value="${article.file.path}"
     * @param filePath
     */
    /*
     * public void setFilePath(Resource filePath) { this.filePath = filePath; }
     */

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return categoryList(mapping, form, request, response);
    }

    /**
     * 分类列表
     * 
     * @struts.tiles name="sm.info.shared.categoryList" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/indexShared.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward categoryList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("categoryList");
    }

    /**
     * 跳转到共享信息查询页面
     * 
     * @struts.tiles name="sm.info.shared.search" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/searchShared.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toSearch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        // 获取查询条件
        // 标题
        String title = infoSharedForm.getTitle();
        // 分类类型
        Long categoryId = infoSharedForm.getCategory().getCategoryId();
        // 发表人
        String publisher = infoSharedForm.getPublisher();
        // 开始时间
        Date beginTime = infoSharedForm.getBeginTime();
        // 结束时间
        Date endTime = infoSharedForm.getEndTime();
        List shareds = infoManager.findInfoShared(categoryId, title, publisher,
                beginTime, endTime);
        request.setAttribute("shareds", shareds);
        return mapping.findForward("search");
    }

    /**
     * 查看各个共享信息
     * 
     * @struts.tiles name="sm.info.shared.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/viewShared.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        OperatorDTO op = infoSharedForm.getCurrentUser();
        InfoSharedDTO shared = infoSharedForm.getInfoShared();
        AttachDTO attach = infoManager.findAttachBySharedId(shared.getInfoId());
        request.setAttribute("attach", attach);
        request.setAttribute("op", op);
        return mapping.findForward("view");
    }

    /**
     * 跳转到共享信息发布页面
     * 
     * @struts.tiles name="sm.info.shared.publish" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/addShared.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toPublish(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("publish");
    }

    /**
     * 文件上传
     * 
     * @param file
     *            上传的文件数据
     * @throws Exception
     */
    private void attachFileUpload(FormFile file, String dirName,
            InfoSharedDTO shared) throws Exception {

        AttachDTO attach = infoSharedFile.attachFileUpload(file, dirName,
                shared);
        // 保存附件信息到数据库
        infoManager.saveAttach(attach);
    }

    /**
     * 创建共享信息
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
        // 发表新的共享信息
        String actionCode = ActionDefinition.SYS_SAVE_SHARED;
        // 设置共享信息
        InfoSharedForm sharedForm = (InfoSharedForm) form;
        // 从request中获得组织id数组
        String[] orgIds = request.getParameterValues("ids");
        // 获得接收组织
        String orgStr = createOrgs(orgIds);
        // 获得共享信息
        InfoSharedDTO shared = sharedForm.getInfoShared();
        // 设置信息种类
        Long categoryId = sharedForm.getCategory().getCategoryId();
        InfoCategoryDTO category = infoManager.findInfoCategoryById(categoryId);
        OperatorDTO operator = sharedForm.getCurrentUser();
        // 设置共享信息数据:种类，接收组织，作者，发布时间
        shared.setCategory((InfoCategory) category.getTarget());
        shared.setInceptOrgs(orgStr);
        shared.setOperator(operator.getOperator());
        shared.setPublishTime(new Date());
        try {
            // 发布共享信息
            Long infoId = infoManager.saveInfoShared(shared);
            shared.setInfoId(infoId);
            // 保存并上传附件
            FormFile file = sharedForm.getAttachFile();
            if (null != file.getFileName() && file.getFileName().length() > 0) {
                attachFileUpload(file, category.getName(), shared);
            }
        } catch (Exception e) {
            // 记录发表共享信息失败日志
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // 转到共享信息列表
        sharedForm.setCategory(category);
        return toSearch(mapping, sharedForm, request, response);
    }

    /**
     * 获得接收组织
     * 
     * @param orgIds
     *            组织的ID数组
     * @return
     */
    private String createOrgs(String[] orgIds) {
        // 接收组织的规则为#+组织id+#
        StringBuffer orgStr = new StringBuffer(OrganizationDTO.PATH_SEPARATOR);
        if (null != orgIds && orgIds.length > 0) {
            for (int i = 0; i < orgIds.length; i++) {
                orgStr.append(orgIds[i]);
                orgStr.append(OrganizationDTO.PATH_SEPARATOR);
            }
        }
        return orgStr.toString();
    }

    /**
     * 删除共享信息
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
        // 删除共享信息和其附件信息
        String actionCode = ActionDefinition.SYS_DELETE_SHARED;

        InfoSharedForm sharedForm = (InfoSharedForm) form;
        // 删除共享信息，删除共享信息附件
        // 获得附件信息查找附件所在目录
        InfoSharedDTO shared = sharedForm.getInfoShared();
        // 获得共享信息所在分类
        Long categoryId = shared.getCategoryId();
        try {
            // 获得共享信息中的附件
            AttachDTO attach = infoManager.findAttachBySharedId(shared
                    .getInfoId());
            // 删除附件
            deleteAttach(attach);
            // 删除共享信息
            infoManager.deleteInfoShared(shared.getInfoId());
        } catch (Exception e) {
            // 记录删除共享信息失败日志
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // sharedForm.setCategory(new InfoCategoryDTO(category));
        // return toSearch(mapping, sharedForm, request, response);
        return this.getRedirectForwardAction("infoShared.do?act=toSearch&cId="
                + categoryId.toString());
    }

    /**
     * 删除附件和数据库中的附件记录
     * 
     * @param attach
     *            附件实体数据
     */
    private void deleteAttach(AttachDTO attach) throws Exception {
        if (null != attach) {
            String filePath = attach.getFilePath();
            File attachFile = new File(filePath);
            // 判断文件是否可读，可读说明文件存在可以执行删除操作
            if (attachFile.canRead()) {
                if (attachFile.delete()) {
                    // 删除数据库中附件的信息
                    infoManager.deleteAttach(attach.getAttachId());
                }
            } else {
                infoManager.deleteAttach(attach.getAttachId());
            }
        }
    }

    /**
     * 跳转到编辑页面
     * 
     * @struts.tiles name="sm.info.shared.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/editShared.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        InfoSharedDTO shared = infoSharedForm.getInfoShared();
        AttachDTO attach = infoManager.findAttachBySharedId(shared.getInfoId());
        request.setAttribute("attach", attach);
        return mapping.findForward("edit");
    }

    /**
     * 编辑共享信息
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
        // 编辑共享信息
        String actionCode = ActionDefinition.SYS_UPDATE_SHARED;

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        // 从request中获得组织id数组
        String orgIds[] = request.getParameterValues("ids");
        // 获得接收组织
        String orgStr = createOrgs(orgIds);
        // 获得共享信息
        InfoSharedDTO shared = infoSharedForm.getInfoShared();
        shared.setInceptOrgs(orgStr);
        // 种类
        Long categoryId = infoSharedForm.getCategoryId();
        InfoCategoryDTO category = infoManager.findInfoCategoryById(categoryId);
        try {
            // 更新共享信息
            infoManager.updateInfoShared(shared);
            // 更新附件
            FormFile file = infoSharedForm.getAttachFile();
            // 判断有无新附件提交
            if (null != file && null != file.getFileName()
                    && file.getFileName().length() > 0) {
                // 说明有新的附件提交
                attachFileUpload(file, category.getName(), shared);
            }
        } catch (Exception e) {
            // 记录编辑共享信息失败日志
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // 返回
        infoSharedForm.setCategory(category);
        return toSearch(mapping, infoSharedForm, request, response);
    }

    /**
     * 删除附件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAttach(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 删除附件
        String actionCode = ActionDefinition.SYS_DELETE_ATTACH;

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        // 获得附件信息
        AttachDTO attach = infoSharedForm.getAttach();
        // 获得附件相关的共享信息
        InfoShared shared = attach.getInfo();
        // 执行删除
        try {
            deleteAttach(attach);
        } catch (Exception e) {
            // 记录删除共享信息附件失败日志
            logger.log(request, actionCode, attach.getFilePath(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + attach.getFilePath(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, attach.getFilePath(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("infoShared.do?act=view&id="
                + shared.getInfoId());
    }

    /**
     * @spring.property ref="infoSharedFile"
     * @param infoSharedFile
     *            The infoSharedFile to set.
     */
    public void setInfoSharedFile(InfoSharedFile infoSharedFile) {
        this.infoSharedFile = infoSharedFile;
    }

    /**
     * 下载附件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward download(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        AttachDTO attach = infoSharedForm.getAttach();
        File file = new File(attach.getFilePath());
        response.addHeader("Content-disposition", "attachment; filename="
                + new String(attach.getFileName().getBytes("gb2312"),
                        "iso8859-1"));
        response.setContentType("application/octet-stream");
        InputStream inputStream = new FileInputStream(file);
        StreamUtils.flow(inputStream, response.getOutputStream(),
                new byte[1024]);
        response.getOutputStream().close();
        inputStream.close();
        return null;
    }
}
