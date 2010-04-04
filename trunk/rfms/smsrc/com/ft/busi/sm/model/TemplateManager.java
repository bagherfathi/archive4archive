package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;

/**
 * 模板管理接口。
 * 
 * @ejb.client jndiName="ejb/sm/templateManager" id="templateManager"
 *             homepackage="com.ft.busi.sm.ejb"
 * 
 */

public interface TemplateManager extends EntityManager {
    /**
     * 保存模板信息。
     * 
     * @param template
     *                模板信息实体。
     * @param appRequest
     *                业务处理记录。
     * @return
     */
    public Long saveTempate(TemplateDTO template, AppRequest appRequest)
            throws Exception;

    /**
     * 保存模板。
     * 
     * @param template
     *                模板信息实体。
     * @param appRequest
     *                业务处理记录。
     * @param templateFile
     *                模板文件。
     * @return
     * @throws Exception
     */
    public Long saveTemplate(TemplateDTO template, AppRequest appRequest,
            TemplateFileDTO templateFile) throws Exception;

    /**
     * 更新模板信息。
     * 
     * @param template
     *                模板信息实体。
     * @param appRequest
     *                业务处理记录。
     * @throws Exception
     */
    public void updateTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception;

    /**
     * 删除模板信息。
     * 
     * @param template
     *                模板信息实体。
     * @param appRequest
     *                业务处理记录。
     *                
     * @throws Exception
     */
    public void deleteTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception;
    
    /**
     * 添加绑定关系。
     * @param orgId　　　　　组织ID。
     * @param templateId
     * @param appRequest
     */
    public void addTemplateBind(Long orgId,Long templateId,AppRequest appRequest) throws Exception;
    
    /**
     * 删除绑定关系。
     * @param orgId
     * @param templateId
     * @param appRequest
     */
    public void deleteTemplateBind(Long orgId,Long templateId,AppRequest appRequest) throws Exception;

    /**
     * 获取模板的历史模板文件列表。
     * 
     * @param templateId
     *                模板标识。
     * @return
     */
    public List findTemplateFiles(Long templateId) throws Exception;

    /**
     * 获取指定类别中适用指定组织的模板。
     * 
     * @param categoryCode
     *                类别代码，参看枚举数据categoryCode@SM_TEMPLATE;
     * @param orgId
     *                组织ID。
     * @return
     */
    public List findBindTemplateOfOrg(String categoryCode, Long orgId)
            throws Exception;
    
    /**
     * 获取指定模板类别中的模板
     * @param categoryCode
     * @return
     */
    public List findTemplateOfCategory(String categoryCode) throws Exception;

    /**
     * 根据模板编码获取模板信息。
     * 
     * @param templateCode
     *                模板编码。
     * @return
     */
    public TemplateDTO findTemplateByCode(String templateCode) throws Exception;
    
    /**
     * 查询模板当前发布文件。
     * @param template
     * @return
     * @throws Exception
     */
    public TemplateFileDTO findPublishFileOfTemplate(TemplateDTO template) throws Exception;

    /**
     * 添加模板文件
     * 
     * @param templateDto
     *                模板信息
     * @param templateFile
     *                文件
     * @param appRequest
     *                受理记录
     * @param isPublish
     *                是否发布
     */
    public void addTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest,
            boolean isPublish) throws Exception;

    /**
     * 发布模板文件。
     * 
     * @param templateDto
     * @param templateFile
     * @param appRequest
     */
    public void publisTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception;

    /**
     * 删除模板历史文件。
     * 
     * @param templateFile
     * @throws Exception
     */
    public void deleteTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception;
    
    /**
     * 获取所有的模板信息。
     * @return
     */
    public List findAllTemplates();

    /**
     * 查询模板当前发布文件。
     * @param templateCode
     * @return
     */
    public TemplateFileDTO findPublishFileOfTemplate(String templateCode);
}
