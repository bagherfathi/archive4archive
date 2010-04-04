package com.ft.common.security;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ft.busi.sm.model.OrgManager;
import com.ft.common.cache.Cache;
import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.OrganizationDTO;

/**
 * 组织机构实体缓存。
 * 
 * @spring.bean id = "orgRepository" init-method="init"
 */
public class OrgRepository implements SMEventListener {
    private static final Log logger = LogFactory.getLog(OrgRepository.class);

    protected Cache cache;

    private OrgManager orgManager;

    /**
     * @param cache
     *                the cache to set
     * @spring.property ref = "oscache"
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * @param orgManager
     *                the orgManager to set
     * @spring.property ref = "orgManager"
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * 启动时装载组织机构实体到缓存中。
     * 
     * @throws Exception
     */
    public void init() throws Exception {
        this.cache.clear();
        // 启动线程装载系统中的组织机构
        Thread loadOrgsThread = new Thread() {
            public void run() {
                List orgs;
                try {
                    orgs = orgManager.findAllOrgOrderByOrgName();
                } catch (Exception e) {
                    logger.error("Load oranization error.", e);
                    return;
                }

                for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
                    OrganizationDTO orgDto = (OrganizationDTO) iterator.next();
                    cache.put(orgDto.getOrgId(), orgDto);
                }
            }
        };

        loadOrgsThread.start();
    }

    /**
     * 根据ID获取组织机构。
     * 
     * @param orgId
     * @return
     */
    public OrganizationDTO getOrgDTOById(Long orgId) {
        OrganizationDTO orgDto = (OrganizationDTO) cache.get(orgId);
        if (orgDto == null) {
            try {
                orgDto = this.orgManager.findOrgById(orgId);
                if (orgDto != null) {
                    cache.put(orgDto.getOrgId(), orgDto);
                }
            } catch (Exception e) {
                logger.error("Not found organization,id=" + orgId, e);
                return null;
            }
        }

        if (orgDto == null) {
            logger.warn("Not found organization,id=" + orgId);
        }

        return orgDto;
    }

    /**
     * 禁止组织。
     * 
     * @param orgs
     *                组织列表。
     * @throws Exception
     */
    public void disableOrg(OrganizationDTO[] orgs) throws Exception {
        this.orgManager.disableOrg(orgs);
        for (int i = 0; i < orgs.length; i++) {
            OrganizationDTO orgDto = orgs[i];
            orgDto.setStatus(OrganizationDTO.STATUS_DISABLE);
            this.cache.put(orgDto.getOrgId(), orgDto);
        }
    }

    /**
     * 启用组织。
     * 
     * @param org
     *                组织机构。
     * @throws Exception
     */
    public void enableOrg(OrganizationDTO org) throws Exception {
        this.orgManager.enableOrg(org);
        org.setStatus(OrganizationDTO.STATUS_NORMAL);
        this.cache.put(org.getOrgId(), org);
    }

    /**
     * 新建组织机构。
     * 
     * @param org
     *                组织机构。
     * @param parentOrg
     *                父组织机构。
     * @return
     * @throws Exception
     */
    public Long save(OrganizationDTO org, OrganizationDTO parentOrg)
            throws Exception {
        Long orgId = this.orgManager.save(org, parentOrg);
        OrganizationDTO orgDto = this.orgManager.findOrgById(orgId);
        this.cache.put(orgId, orgDto);
        return orgId;
    }

    /**
     * 更新组织机构
     * @param org
     * @param parentOrg
     * @throws Exception
     */
    public void update(OrganizationDTO org, OrganizationDTO parentOrg)
            throws Exception {
        this.orgManager.update(org, parentOrg);
        OrganizationDTO orgDto = this.orgManager.findOrgById(org.getOrgId());
        this.cache.put(org.getOrgId(), orgDto);
    }

    public static String getBeanName() {
        return "orgRepository";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.common.event.SMEventListener#isSupport(com.ft.common.event.SMEvent)
     */
    public boolean isSupport(SMEvent event) {
        if (EventConstants.EVENT_TYPE_ORG.equals(event.getType()))
            return true;

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.common.event.SMEventListener#onEvent(com.ft.common.event.SMEvent)
     */
    public void onEvent(SMEvent event) {
        logger.info("Receive message:" + event.toString());
        String key = event.getKey();
        if (EventConstants.EVENT_UPDATE_ORG.equals(event.getAction())) {
            if (key != null && key.length() > 0) {
                Long orgId = new Long(key);
                this.addOrUpdate(orgId);
            }
        }
    }

    private void addOrUpdate(Long orgId) {
        OrganizationDTO orgDto;
        try {
            orgDto = this.orgManager.findOrgById(orgId);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }

        this.cache.put(orgId, orgDto);
    }
}
