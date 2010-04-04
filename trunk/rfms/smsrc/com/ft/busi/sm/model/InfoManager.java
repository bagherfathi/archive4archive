package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.dto.OperatorDTO;

/**
 * 信息信息维护接口。
 * 
 * 
 * @ejb.client jndiName="ejb/sm/infoManager" id="infoManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface InfoManager extends EntityManager {

    /**
     * 保存信息分类。
     * 
     * @param category
     *                信息分类实体数据。
     */
    public Long saveInfoGategory(InfoCategoryDTO category) throws Exception;

    /**
     * 更新信息分类数据。
     * 
     * @param category
     *                信息分类实体数据。
     */
    public void updateInfoGategory(InfoCategoryDTO category) throws Exception;

    /**
     * 删除信息分类。
     * 
     * @param categoryId
     *                信息分类ID。
     */
    public void deleteInfoCategory(Long categoryId) throws Exception;

    /**
     * 保存公告信息。
     * 
     * @param affiche
     *                公告信息实体数据。
     */
    public Long saveAffiche(AfficheDTO affiche,Long[] orgIds) throws Exception;
    
    /**
     * 创建公告
     * @param affiche    公告信息
     * @param publisher  发布者
     * @param orgIds     适用组织ID列表
     * @return
     */
    public Long createAffiche(AfficheDTO affiche,OperatorDTO publisher,Long[] orgIds);

    /**
     * 删除公告信息。
     * 
     * @param afficheId
     *                公告ID。
     */
    public void deleteAffiche(Long afficheId) throws Exception;

    /**
     * 更新公告信息。
     * 
     * @param affiche
     *                公告信息实体数据。
     */
    public void updateAffiche(AfficheDTO affiche) throws Exception;

    /**
     * 获得所有的信息分类数据。
     * 
     * @return 信息分类数据列表。
     */
    public List findAllCategories() throws Exception;

    /**
     * 通过categoryId获得InfoCategory对象。
     * 
     * @param categoryId
     *                信息分类ID。
     * @return 信息分类实体数据。
     */
    public InfoCategoryDTO findInfoCategoryById(Long categoryId)
            throws Exception;

    /**
     * 保存／发布共享信息。
     * 
     * @param infoShared
     *                共享信息实体数据。
     */
    public Long saveInfoShared(InfoSharedDTO infoShared) throws Exception;

    /**
     * 获得附件。
     * 
     * @param sharedId
     *                共享信息ID。
     * @return 信息附件实体数据。
     */
    public AttachDTO findAttachBySharedId(Long sharedId) throws Exception;

    /**
     * 保存附件。
     * 
     * @param attach
     *                信息附件实体数据。
     */
    public Long saveAttach(AttachDTO attach) throws Exception;

    /**
     * 删除附件信息。
     * 
     * @param attachId
     *                附件ID。
     */
    public void deleteAttach(Long attachId) throws Exception;

    /**
     * 删除共享信息。
     * 
     * @param infoId
     *                共享信息ID。
     */
    public void deleteInfoShared(Long infoId) throws Exception;

    /**
     * 更新共享信息。
     * 
     * @param shared
     *                共享信息实体数据。
     */
    public void updateInfoShared(InfoSharedDTO shared) throws Exception;

    /**
     * 获得相对组织的公告信息。
     * 
     * @param orgId
     * @return
     */
    public List findAllAfficheByOrg(Long orgId) throws Exception;

    /**
     * 通过查询条件获得共享信息列表。
     * 
     * @param categoryId
     *                信息分类ID。
     * @param title
     *                共享信息标题。
     * @param publisher
     *                信息发布者。
     * @param beginTime
     *                发布时间 开始。
     * @param endTime
     *                发布时间 结束。
     * @return
     */
    public List findInfoShared(Long categoryId, String title, String publisher,
            Date beginTime, Date endTime) throws Exception;

    /**
     * 查询信息分类中信息总数。
     * 
     * @param categoryId
     *                信息分类ID。
     * @return
     */
    public Integer findInfoNumberOfCategory(Long categoryId) throws Exception;

    /**
     * 查询信息分类中最后分布的信息。
     * 
     * @param categoryId
     *                信息分类ID。
     * @return
     */
    public InfoSharedDTO findLastPublishedInfo(Long categoryId)
            throws Exception;
    
}
