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
 * ��Ϣ����������
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
     * ���������ַ
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
     * Ĭ�Ϸ���
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return categoryList(mapping, form, request, response);
    }

    /**
     * �����б�
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
     * ��ת��������Ϣ��ѯҳ��
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
        // ��ȡ��ѯ����
        // ����
        String title = infoSharedForm.getTitle();
        // ��������
        Long categoryId = infoSharedForm.getCategory().getCategoryId();
        // ������
        String publisher = infoSharedForm.getPublisher();
        // ��ʼʱ��
        Date beginTime = infoSharedForm.getBeginTime();
        // ����ʱ��
        Date endTime = infoSharedForm.getEndTime();
        List shareds = infoManager.findInfoShared(categoryId, title, publisher,
                beginTime, endTime);
        request.setAttribute("shareds", shareds);
        return mapping.findForward("search");
    }

    /**
     * �鿴����������Ϣ
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
     * ��ת��������Ϣ����ҳ��
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
     * �ļ��ϴ�
     * 
     * @param file
     *            �ϴ����ļ�����
     * @throws Exception
     */
    private void attachFileUpload(FormFile file, String dirName,
            InfoSharedDTO shared) throws Exception {

        AttachDTO attach = infoSharedFile.attachFileUpload(file, dirName,
                shared);
        // ���渽����Ϣ�����ݿ�
        infoManager.saveAttach(attach);
    }

    /**
     * ����������Ϣ
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
        // �����µĹ�����Ϣ
        String actionCode = ActionDefinition.SYS_SAVE_SHARED;
        // ���ù�����Ϣ
        InfoSharedForm sharedForm = (InfoSharedForm) form;
        // ��request�л����֯id����
        String[] orgIds = request.getParameterValues("ids");
        // ��ý�����֯
        String orgStr = createOrgs(orgIds);
        // ��ù�����Ϣ
        InfoSharedDTO shared = sharedForm.getInfoShared();
        // ������Ϣ����
        Long categoryId = sharedForm.getCategory().getCategoryId();
        InfoCategoryDTO category = infoManager.findInfoCategoryById(categoryId);
        OperatorDTO operator = sharedForm.getCurrentUser();
        // ���ù�����Ϣ����:���࣬������֯�����ߣ�����ʱ��
        shared.setCategory((InfoCategory) category.getTarget());
        shared.setInceptOrgs(orgStr);
        shared.setOperator(operator.getOperator());
        shared.setPublishTime(new Date());
        try {
            // ����������Ϣ
            Long infoId = infoManager.saveInfoShared(shared);
            shared.setInfoId(infoId);
            // ���沢�ϴ�����
            FormFile file = sharedForm.getAttachFile();
            if (null != file.getFileName() && file.getFileName().length() > 0) {
                attachFileUpload(file, category.getName(), shared);
            }
        } catch (Exception e) {
            // ��¼��������Ϣʧ����־
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // ת��������Ϣ�б�
        sharedForm.setCategory(category);
        return toSearch(mapping, sharedForm, request, response);
    }

    /**
     * ��ý�����֯
     * 
     * @param orgIds
     *            ��֯��ID����
     * @return
     */
    private String createOrgs(String[] orgIds) {
        // ������֯�Ĺ���Ϊ#+��֯id+#
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
        // ɾ��������Ϣ���丽����Ϣ
        String actionCode = ActionDefinition.SYS_DELETE_SHARED;

        InfoSharedForm sharedForm = (InfoSharedForm) form;
        // ɾ��������Ϣ��ɾ��������Ϣ����
        // ��ø�����Ϣ���Ҹ�������Ŀ¼
        InfoSharedDTO shared = sharedForm.getInfoShared();
        // ��ù�����Ϣ���ڷ���
        Long categoryId = shared.getCategoryId();
        try {
            // ��ù�����Ϣ�еĸ���
            AttachDTO attach = infoManager.findAttachBySharedId(shared
                    .getInfoId());
            // ɾ������
            deleteAttach(attach);
            // ɾ��������Ϣ
            infoManager.deleteInfoShared(shared.getInfoId());
        } catch (Exception e) {
            // ��¼ɾ��������Ϣʧ����־
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // sharedForm.setCategory(new InfoCategoryDTO(category));
        // return toSearch(mapping, sharedForm, request, response);
        return this.getRedirectForwardAction("infoShared.do?act=toSearch&cId="
                + categoryId.toString());
    }

    /**
     * ɾ�����������ݿ��еĸ�����¼
     * 
     * @param attach
     *            ����ʵ������
     */
    private void deleteAttach(AttachDTO attach) throws Exception {
        if (null != attach) {
            String filePath = attach.getFilePath();
            File attachFile = new File(filePath);
            // �ж��ļ��Ƿ�ɶ����ɶ�˵���ļ����ڿ���ִ��ɾ������
            if (attachFile.canRead()) {
                if (attachFile.delete()) {
                    // ɾ�����ݿ��и�������Ϣ
                    infoManager.deleteAttach(attach.getAttachId());
                }
            } else {
                infoManager.deleteAttach(attach.getAttachId());
            }
        }
    }

    /**
     * ��ת���༭ҳ��
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
     * �༭������Ϣ
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
        // �༭������Ϣ
        String actionCode = ActionDefinition.SYS_UPDATE_SHARED;

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        // ��request�л����֯id����
        String orgIds[] = request.getParameterValues("ids");
        // ��ý�����֯
        String orgStr = createOrgs(orgIds);
        // ��ù�����Ϣ
        InfoSharedDTO shared = infoSharedForm.getInfoShared();
        shared.setInceptOrgs(orgStr);
        // ����
        Long categoryId = infoSharedForm.getCategoryId();
        InfoCategoryDTO category = infoManager.findInfoCategoryById(categoryId);
        try {
            // ���¹�����Ϣ
            infoManager.updateInfoShared(shared);
            // ���¸���
            FormFile file = infoSharedForm.getAttachFile();
            // �ж������¸����ύ
            if (null != file && null != file.getFileName()
                    && file.getFileName().length() > 0) {
                // ˵�����µĸ����ύ
                attachFileUpload(file, category.getName(), shared);
            }
        } catch (Exception e) {
            // ��¼�༭������Ϣʧ����־
            logger.log(request, actionCode, shared.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + shared.getTitle(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, shared.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        // ����
        infoSharedForm.setCategory(category);
        return toSearch(mapping, infoSharedForm, request, response);
    }

    /**
     * ɾ������
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
        // ɾ������
        String actionCode = ActionDefinition.SYS_DELETE_ATTACH;

        InfoSharedForm infoSharedForm = (InfoSharedForm) form;
        // ��ø�����Ϣ
        AttachDTO attach = infoSharedForm.getAttach();
        // ��ø�����صĹ�����Ϣ
        InfoShared shared = attach.getInfo();
        // ִ��ɾ��
        try {
            deleteAttach(attach);
        } catch (Exception e) {
            // ��¼ɾ��������Ϣ����ʧ����־
            logger.log(request, actionCode, attach.getFilePath(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + attach.getFilePath(), e);
            throw e;
        }
        // ��¼������־
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
     * ���ظ���
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
