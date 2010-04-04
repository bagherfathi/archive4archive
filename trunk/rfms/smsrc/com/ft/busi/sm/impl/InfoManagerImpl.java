package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.ft.busi.sm.impl.dao.AfficheDao;
import com.ft.busi.sm.impl.dao.AttachDao;
import com.ft.busi.sm.impl.dao.InfoCategoryDao;
import com.ft.busi.sm.impl.dao.InfoSharedDao;
import com.ft.busi.sm.model.InfoManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.Attach;
import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.RelAfficheOrg;

/**
 * 信息数据维护接口实现类
 * 
 * @spring.aop-bean id="infoManager" parent="transactionProxyFactoryBean"
 *                  target="infoManagerImpl"
 * 
 * @spring.bean id="infoManagerImpl"
 * 
 * @version 1.0
 */
public class InfoManagerImpl implements InfoManager {

    private AfficheDao afficheDao;

    private AttachDao attachDao;

    private InfoCategoryDao infoCategoryDao;

    private InfoSharedDao infoSharedDao;
    /**
     * @spring.property ref="AfficheDao"
     * @param afficheDao
     *                the afficheDao to set
     */
    public void setAfficheDao(AfficheDao afficheDao) {
        this.afficheDao = afficheDao;
    }

    /**
     * @spring.property ref="AttachDao"
     * @param attachDao
     *                the attachDao to set
     */
    public void setAttachDao(AttachDao attachDao) {
        this.attachDao = attachDao;
    }

    /**
     * @spring.property ref="InfoCategoryDao"
     * @param infoCategoryDao
     *                the infoCategoryDao to set
     */
    public void setInfoCategoryDao(InfoCategoryDao infoCategoryDao) {
        this.infoCategoryDao = infoCategoryDao;
    }

    /**
     * @spring.property ref="InfoSharedDao"
     * @param infoSharedDao
     *                the infoSharedDao to set
     */
    public void setInfoSharedDao(InfoSharedDao infoSharedDao) {
        this.infoSharedDao = infoSharedDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#saveInfoGategory(com.huashu.boss.sm.entity.InfoCategory)
     */
    public Long saveInfoGategory(InfoCategoryDTO categoryDto) {
        if (null == categoryDto) {
            throw new IllegalArgumentException();
        }

        InfoCategory category = (InfoCategory) categoryDto.getTarget();
        this.infoCategoryDao.save(category);

        return new Long(category.getCategoryId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#updateInfoGategory(com.huashu.boss.sm.entity.InfoCategory)
     */
    public void updateInfoGategory(InfoCategoryDTO categoryDto) {
        if (null == categoryDto) {
            throw new IllegalArgumentException();
        }

        InfoCategory category = (InfoCategory) categoryDto.getTarget();
        this.infoCategoryDao.update(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#deleteInfoGategory(com.huashu.boss.sm.entity.InfoCategory)
     */
    public void deleteInfoCategory(Long categoryId) {
        if (null == categoryId) {
            throw new IllegalArgumentException();
        }

        this.infoCategoryDao.deleteCategoryById(categoryId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findAllCategory()
     */
    public List findAllCategories() {
        List result = this.infoCategoryDao.findAll();

        return EntityDTOConverter.converInfoCategory2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#saveAffiche(com.huashu.boss.sm.entity.Affiche)
     */
    public Long saveAffiche(AfficheDTO afficheDto,Long[] orgIds) {
        if (null == afficheDto || orgIds == null) {
            throw new IllegalArgumentException();
        }

        //保存公告信息
        Affiche affiche = (Affiche) afficheDto.getTarget();
        this.afficheDao.saveOrUpdate(affiche);
        
        //删除原有适用组织
        this.afficheDao.deleteRelAfficheOrgs(afficheDto.getAfficheId());
        
        //保存公告适用组织
        for (int i = 0; i < orgIds.length; i++) {
            RelAfficheOrg relAfficheOrg = new RelAfficheOrg();
            relAfficheOrg.setAfficheId(affiche.getAfficheId());
            relAfficheOrg.setOrgId(orgIds[i].longValue());
            this.afficheDao.save(relAfficheOrg);
        }

        return new Long(affiche.getAfficheId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#deleteAffiche(com.huashu.boss.sm.entity.Affiche)
     */
    public void deleteAffiche(Long afficheId) {
        if (null == afficheId) {
            throw new IllegalArgumentException();
        }

        this.afficheDao.deleteAfficheById(afficheId);
        this.afficheDao.deleteRelAfficheOrgs(afficheId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#updateAffiche(com.huashu.boss.sm.entity.Affiche)
     */
    public void updateAffiche(AfficheDTO afficheDto) {
        if (null == afficheDto) {
            throw new IllegalArgumentException();
        }
        Affiche affiche = (Affiche) afficheDto.getTarget();
        this.afficheDao.update(affiche);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findInfoCategoryById(java.lang.Long)
     */
    public InfoCategoryDTO findInfoCategoryById(Long categoryId) {
        if (null == categoryId) {
            throw new IllegalArgumentException();
        }

        InfoCategory category = this.infoCategoryDao.getById(categoryId);

        if (category != null) {
            return new InfoCategoryDTO(category);
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#saveInfoShared(com.huashu.boss.sm.entity.InfoShared)
     */
    public Long saveInfoShared(InfoSharedDTO infoSharedDto) {
        if (null == infoSharedDto) {
            throw new IllegalArgumentException();
        }

        InfoShared info = (InfoShared) infoSharedDto.getTarget();
        this.infoSharedDao.save(info);
        return new Long(info.getInfoId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findAttachBySharedId(java.lang.Long)
     */
    public AttachDTO findAttachBySharedId(Long sharedId) {
        if (null == sharedId) {
            throw new IllegalArgumentException();
        }

        List result = this.attachDao.findAttachsByInfoId(sharedId);

        // 一条共享信息只有一个附件
        if (result.size() > 0) {
            Attach attach = (Attach) result.get(0);
            return new AttachDTO(attach);
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#saveAttach(com.huashu.boss.sm.entity.Attach)
     */
    public Long saveAttach(AttachDTO attachDto) {
        if (null == attachDto) {
            throw new IllegalArgumentException();
        }

        Attach attach = (Attach) attachDto.getTarget();

        this.attachDao.save(attach);

        return new Long(attach.getAttachId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#removeAttach(com.huashu.boss.sm.entity.Attach)
     */
    public void deleteAttach(Long attachId) {
        if (null == attachId) {
            throw new IllegalArgumentException();
        }

        this.attachDao.deleteAttachById(attachId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#removeInfoShared(com.huashu.boss.sm.entity.InfoShared)
     */
    public void deleteInfoShared(Long infoId) {
        if (null == infoId) {
            throw new IllegalArgumentException();
        }
        // 删除共享信息附件
        this.attachDao.deleteAttachByInfoId(infoId);

        // 删除共享信息
        this.infoSharedDao.deleteInfoById(infoId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#updateInfoShared(com.huashu.boss.sm.entity.InfoShared)
     */
    public void updateInfoShared(InfoSharedDTO sharedDto) {
        if (null == sharedDto) {
            throw new IllegalArgumentException();
        }

        InfoShared info = this.infoSharedDao.getById(sharedDto.getInfoId());
        info.setInfoTitle(sharedDto.getTitle());
        info.setInfoContent(sharedDto.getContent());
        info.setInceptOrgs(sharedDto.getInceptOrgs());
        this.infoSharedDao.update(info);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findAllAfficheByOrg(java.lang.Long)
     */
    public List findAllAfficheByOrg(Long orgId) {
        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.AfficheDTO(affiche,op)");
        hql.append("from Affiche affiche,Operator op,RelAfficheOrg rao");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append(
                "=affiche.").append(Affiche.PROP_PUBLISHER_ID);
        hql.append(" and affiche.").append(Affiche.PROP_VALID_TIME).append(
                "<=?");
        hql.append(" and affiche.").append(Affiche.PROP_EXPIRE_TIME).append(
                ">=?");
        hql.append(" and affiche.").append(Affiche.PROP_AFFICHE_ID).append(
                "=rao.").append(RelAfficheOrg.PROP_AFFICHE_ID);
        hql.append(" and rao.").append(RelAfficheOrg.PROP_ORG_ID).append("=?");
        hql.append(" order by affiche.").append(Affiche.PROP_AFFICHE_LEVEL)
                .append(" desc ,affiche.").append(Affiche.PROP_PUBLISH_TIME).append(" desc");

        Date now = new Date();
        return this.afficheDao.query(hql.toString(), new Object[] {now, now, orgId});
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findInfoShared(java.lang.Long,
     *      java.lang.String, java.lang.String, java.util.Date, java.util.Date)
     */
    public List findInfoShared(Long categoryId, String title, String publisher,
            Date beginTime, Date endTime) {
        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.InfoSharedDTO(category,info,op)");
        hql.append(" from InfoCategory category,InfoShared info,Operator op");
        hql.append(" where category.").append(InfoCategory.PROP_CATEGORY_ID)
                .append("=info.").append(InfoShared.PROP_CATEGORY_ID);
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID).append(
                "=info.").append(InfoShared.PROP_AUTHOR_ID);

        List<Object> params = new ArrayList<Object>();
        if (categoryId != null && categoryId.longValue() > 0) {
            hql.append(" and info.").append(InfoShared.PROP_CATEGORY_ID)
                    .append("=?");
            params.add(categoryId);
        }

        if (title != null && title.length() > 0) {
            hql.append(" and info.").append(InfoShared.PROP_INFO_TITLE).append(
                    " like ?");
            params.add("%" + title + "%");
        }

        if (publisher != null && publisher.length() > 0) {
            hql.append(" and op.").append(Operator.PROP_OP_NAME).append(
                    " like ?");
            params.add("%" + publisher + "%");
        }

        if (beginTime != null) {
            hql.append(" and info.").append(InfoShared.PROP_PUBLISH_TIME)
                    .append(" >= ?");
            params.add(beginTime);
        }

        if (endTime != null) {
            hql.append(" and info.").append(InfoShared.PROP_PUBLISH_TIME)
                    .append(" <= ?");
            params.add(endTime);
        }

        return this.infoSharedDao.query(hql.toString(), params
                .toArray(new Object[0]));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(AfficheDTO.class)) {
            StringBuffer hql = new StringBuffer("select new com.huashu.boss.sm.dto.AfficheDTO(affiche,op)");
            hql.append(" from Affiche affiche,Operator op");
            hql.append(" where affiche.").append(Affiche.PROP_PUBLISHER_ID).append("=op.").append(Operator.PROP_OPERATOR_ID);
            hql.append(" and affiche.").append(Affiche.PROP_AFFICHE_ID).append("=?");
            
            AfficheDTO afficheDto = (AfficheDTO)this.afficheDao.getSingle(hql.toString(),new Object[]{id});
            if(afficheDto != null){
                List relAfficheOrgs = this.afficheDao.findRelAfficheOrgs(afficheDto.getAfficheId());
                afficheDto.setRelAfficheOrgs(relAfficheOrgs);
                
                return afficheDto;
            }else{
                return null;
            }
        }

        if (typeClass.equals(AttachDTO.class)) {
            Attach attach = this.attachDao.getById(id);
            if (attach == null)
                return null;
            InfoShared info = this.infoSharedDao.getById(new Long(attach
                    .getInfoId()));
            return new AttachDTO(info, attach);
        }

        if (typeClass.equals(InfoSharedDTO.class)) {
            InfoShared info = this.infoSharedDao.getById(id);
            return new InfoSharedDTO(info);
        }

        if (typeClass.equals(InfoCategoryDTO.class)) {
            InfoCategory category = this.infoCategoryDao.getById(id);
            return new InfoCategoryDTO(category);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List result = null;
        if (typeClass.equals(Affiche.class)) {
            result = this.afficheDao.query(hql, params, page);
            Long[] opIds = ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Affiche element = (Affiche) iter.next();
                Long id = new Long(element.getPublisherId());
                if (!ArrayUtils.contains(opIds, id)) {
                    opIds = (Long[]) ArrayUtils.add(opIds, id);
                }
            }
            List ops = this.afficheDao.loadByIds(Operator.class, opIds);
            result = EntityDTOConverter.converAffiche2DTO(result);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                AfficheDTO element = (AfficheDTO) iter.next();
                for (Iterator iterator = ops.iterator(); iterator.hasNext();) {
                    Operator op = (Operator) iterator.next();
                    if (op.getOperatorId() == element.getPublisherId()
                            .longValue()) {
                        element.setOperator(op);
                    }

                }
            }
            return result;
        }

        if (typeClass.equals(Attach.class)) {
            result = this.attachDao.query(hql, params, page);
            return EntityDTOConverter.converAttach2DTO(result);
        }

        if (typeClass.equals(InfoShared.class)) {
            result = this.infoSharedDao.query(hql, params, page);
            return EntityDTOConverter.converInfoShared2DTO(result);
        }

        if (typeClass.equals(InfoCategory.class)) {
            result = this.infoCategoryDao.query(hql, params, page);
            return EntityDTOConverter.converInfoCategory2DTO(result);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = null;
        if (typeClass.equals(AfficheDTO.class)) {
            result = this.afficheDao.loadByIds(Affiche.class, ids);
            return EntityDTOConverter.converAffiche2DTO(result);
        }

        if (typeClass.equals(AttachDTO.class)) {
            result = this.attachDao.loadByIds(Attach.class, ids);
            return EntityDTOConverter.converAttach2DTO(result);
        }

        if (typeClass.equals(InfoSharedDTO.class)) {
            result = this.infoSharedDao.loadByIds(InfoShared.class, ids);
            return EntityDTOConverter.converInfoShared2DTO(result);
        }

        if (typeClass.equals(InfoCategoryDTO.class)) {
            result = this.infoCategoryDao.loadByIds(InfoCategory.class, ids);
            return EntityDTOConverter.converInfoCategory2DTO(result);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.InfoManager#findInfoNumberOfCategory(java.lang.Long)
     */
    public Integer findInfoNumberOfCategory(Long categoryId) {
        if (categoryId == null || categoryId.longValue() <= 0)
            return new Integer(0);

        return this.infoSharedDao.findInfoNumberOfCategory(categoryId);
    }

    /**
     * 查询类别中最后发布的信息。
     * 
     * @param categoryId
     *                信息分类ID。
     * @return
     */
    public InfoSharedDTO findLastPublishedInfo(Long categoryId) {
        if (categoryId == null || categoryId.longValue() <= 0)
            return null;

        StringBuffer hql = new StringBuffer(
                "select new com.huashu.boss.sm.dto.InfoSharedDTO(info,op)");
        hql.append(" from InfoShared info,Operator op");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append(
                "=info.").append(InfoShared.PROP_AUTHOR_ID);
        hql.append(" and info.").append(InfoShared.PROP_PUBLISH_TIME).append(
                "=(");
        hql.append("select max(shared.").append(InfoShared.PROP_PUBLISH_TIME)
                .append(") from InfoShared shared");
        hql.append(" where shared.").append(InfoShared.PROP_CATEGORY_ID)
                .append("=?");
        hql.append(")");

        List result = this.infoSharedDao.query(hql.toString(),
                new Object[] { categoryId });

        return result.size() > 0 ? (InfoSharedDTO) result.get(0) : null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.InfoManager#createAffiche(com.huashu.boss.sm.dto.AfficheDTO, com.huashu.boss.sm.dto.OperatorDTO, java.lang.Long[])
     */
    public Long createAffiche(AfficheDTO afficheDto, OperatorDTO publisher,
            Long[] orgIds) {
        if (null == afficheDto || publisher == null || orgIds == null) {
            throw new IllegalArgumentException();
        }

        //保存公告信息
        Affiche affiche = (Affiche) afficheDto.getTarget();
        affiche.setPublishTime(new Date());
        affiche.setPublisherId(publisher.getOperatorId().longValue());
        this.afficheDao.save(affiche);
        
        //保存公告适用组织
        for (int i = 0; i < orgIds.length; i++) {
            RelAfficheOrg relAfficheOrg = new RelAfficheOrg();
            relAfficheOrg.setAfficheId(affiche.getAfficheId());
            relAfficheOrg.setOrgId(orgIds[i].longValue());
            this.afficheDao.save(relAfficheOrg);
        }

        return new Long(affiche.getAfficheId());
    }

    /**
     * @param orgIds 当orgIds为空时，为admin登录，可以查询所有公告
     */
	public List findAffiches(Long[] orgIds, String title, Long categoryId, Date beginTime, Date endTime) throws Exception {
		StringBuffer hql = new StringBuffer(
        	"select new com.huashu.boss.sm.dto.AfficheDTO(affiche,op)");
		if(orgIds.length!=0) {
			hql.append("from Affiche affiche,Operator op");
			hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append(
				"=affiche.").append(Affiche.PROP_PUBLISHER_ID);
			hql.append(" and affiche.afficheId in (select rao.afficheId from RelAfficheOrg rao where "
					 //+ " rao.afficheId=affiche.afficheId and "
					 + " rao.orgId in (-1");
			for(int i=0;i<orgIds.length;i++) {
				hql.append(',');
				hql.append(orgIds[i]);
			}
			hql.append("))");
		}else {
			hql.append("from Affiche affiche,Operator op");
			hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append(
				"=affiche.").append(Affiche.PROP_PUBLISHER_ID);
		}
		if(!"".equals(title) && title!=null)
			hql.append(" and affiche.").append(Affiche.PROP_AFFICHE_TITLE).append(" like '%"+title +"%'");
		if(categoryId!=null)
			hql.append(" and affiche.").append(Affiche.PROP_CATEGORY_ID).append("=" + categoryId.longValue());
		hql.append(" and affiche.").append(Affiche.PROP_PUBLISH_TIME).append("<=?");
		hql.append(" and affiche.").append(Affiche.PROP_PUBLISH_TIME).append(">=?");
		hql.append(" order by affiche.").append(Affiche.PROP_AFFICHE_LEVEL)
        	.append(" desc ,affiche.").append(Affiche.PROP_PUBLISH_TIME).append(" desc");

		return this.afficheDao.query(hql.toString(), new Object[] {endTime, beginTime});
	}
}
