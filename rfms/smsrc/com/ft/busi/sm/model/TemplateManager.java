package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;

/**
 * ģ�����ӿڡ�
 * 
 * @ejb.client jndiName="ejb/sm/templateManager" id="templateManager"
 *             homepackage="com.ft.busi.sm.ejb"
 * 
 */

public interface TemplateManager extends EntityManager {
    /**
     * ����ģ����Ϣ��
     * 
     * @param template
     *                ģ����Ϣʵ�塣
     * @param appRequest
     *                ҵ�����¼��
     * @return
     */
    public Long saveTempate(TemplateDTO template, AppRequest appRequest)
            throws Exception;

    /**
     * ����ģ�塣
     * 
     * @param template
     *                ģ����Ϣʵ�塣
     * @param appRequest
     *                ҵ�����¼��
     * @param templateFile
     *                ģ���ļ���
     * @return
     * @throws Exception
     */
    public Long saveTemplate(TemplateDTO template, AppRequest appRequest,
            TemplateFileDTO templateFile) throws Exception;

    /**
     * ����ģ����Ϣ��
     * 
     * @param template
     *                ģ����Ϣʵ�塣
     * @param appRequest
     *                ҵ�����¼��
     * @throws Exception
     */
    public void updateTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception;

    /**
     * ɾ��ģ����Ϣ��
     * 
     * @param template
     *                ģ����Ϣʵ�塣
     * @param appRequest
     *                ҵ�����¼��
     *                
     * @throws Exception
     */
    public void deleteTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception;
    
    /**
     * ��Ӱ󶨹�ϵ��
     * @param orgId������������֯ID��
     * @param templateId
     * @param appRequest
     */
    public void addTemplateBind(Long orgId,Long templateId,AppRequest appRequest) throws Exception;
    
    /**
     * ɾ���󶨹�ϵ��
     * @param orgId
     * @param templateId
     * @param appRequest
     */
    public void deleteTemplateBind(Long orgId,Long templateId,AppRequest appRequest) throws Exception;

    /**
     * ��ȡģ�����ʷģ���ļ��б�
     * 
     * @param templateId
     *                ģ���ʶ��
     * @return
     */
    public List findTemplateFiles(Long templateId) throws Exception;

    /**
     * ��ȡָ�����������ָ����֯��ģ�塣
     * 
     * @param categoryCode
     *                �����룬�ο�ö������categoryCode@SM_TEMPLATE;
     * @param orgId
     *                ��֯ID��
     * @return
     */
    public List findBindTemplateOfOrg(String categoryCode, Long orgId)
            throws Exception;
    
    /**
     * ��ȡָ��ģ������е�ģ��
     * @param categoryCode
     * @return
     */
    public List findTemplateOfCategory(String categoryCode) throws Exception;

    /**
     * ����ģ������ȡģ����Ϣ��
     * 
     * @param templateCode
     *                ģ����롣
     * @return
     */
    public TemplateDTO findTemplateByCode(String templateCode) throws Exception;
    
    /**
     * ��ѯģ�嵱ǰ�����ļ���
     * @param template
     * @return
     * @throws Exception
     */
    public TemplateFileDTO findPublishFileOfTemplate(TemplateDTO template) throws Exception;

    /**
     * ���ģ���ļ�
     * 
     * @param templateDto
     *                ģ����Ϣ
     * @param templateFile
     *                �ļ�
     * @param appRequest
     *                �����¼
     * @param isPublish
     *                �Ƿ񷢲�
     */
    public void addTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest,
            boolean isPublish) throws Exception;

    /**
     * ����ģ���ļ���
     * 
     * @param templateDto
     * @param templateFile
     * @param appRequest
     */
    public void publisTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception;

    /**
     * ɾ��ģ����ʷ�ļ���
     * 
     * @param templateFile
     * @throws Exception
     */
    public void deleteTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception;
    
    /**
     * ��ȡ���е�ģ����Ϣ��
     * @return
     */
    public List findAllTemplates();

    /**
     * ��ѯģ�嵱ǰ�����ļ���
     * @param templateCode
     * @return
     */
    public TemplateFileDTO findPublishFileOfTemplate(String templateCode);
}
