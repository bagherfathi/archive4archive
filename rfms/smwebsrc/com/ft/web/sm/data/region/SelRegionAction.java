package com.ft.web.sm.data.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RegionManager;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;
import com.ft.web.sm.BaseAction;

/**
 * 区域设置类
 * 
 * @struts.action path="/selRegion" name="selRegionForm" scope="session"
 *                validate="false" parameter="act" input="sm.region.select"
 * 
 * @struts.tiles name="sm.region.select" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/region/selRegion.jsp"
 * 
 * @spring.bean id="selRegionAction"
 * 
 * @version 1.0
 * 
 */
public class SelRegionAction extends BaseAction {
    private RegionManager regionManager;

    private OrgManager orgManager;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SelRegionForm selRegionForm = (SelRegionForm) form;
        selRegionForm.init();

        String orgIdStr = request.getParameter("orgId");
        if (orgIdStr == null || orgIdStr.length() <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "Not found orgId parameter in request.");
        }

        Long orgId = new Long(orgIdStr);
        selRegionForm.setOrgId(orgId.longValue());

        // 查询指定组织可访问的数据区域
        List orgIds = this.orgManager.findAccessOrgIdsOfOrg(orgId,
                OrganizationDTO.ORG_TYPE_REGION, true);
        // 查询数据区域可访问的行政区域
        List accessRegions = this.regionManager
                .findRegionsOfOrgs((Long[]) orgIds.toArray(new Long[0]));
        // 按照父子关系排序
        java.util.Collections.sort(accessRegions);

        List<RegionDTO> accessRegionList = new ArrayList<RegionDTO>();

        Map<Long,RegionDTO> nodeMap = new HashMap<Long,RegionDTO>();
        for (Iterator iterator = accessRegions.iterator(); iterator.hasNext();) {
            RegionDTO object = (RegionDTO) iterator.next();
            // 如果是根区域，直接加到根节点下
            if (object.getRegionId().equals(object.getParentId())) {
                nodeMap.put(object.getRegionId(), object);
                accessRegionList.add(object);
            } else {
                // 若其父节点不存在，查询其所在路径上所有区域
                RegionDTO parentRegion = (RegionDTO) nodeMap.get(object
                        .getParentId());

                if (parentRegion == null) {
                    List location = regionManager.findRegionLocation(object
                            .getRegionId());
                    for (int i = 0; i < location.size(); i++) {
                        RegionDTO temp = (RegionDTO) location.get(i);
                        // 节点已存在
                        if (nodeMap.get(temp.getRegionId()) != null)
                            continue;

                        nodeMap.put(temp.getRegionId(), temp);
                        accessRegionList.add(temp);
                    }
                } else {
                    nodeMap.put(object.getRegionId(), object);
                    accessRegionList.add(object);
                }
            }
        }

        selRegionForm.setAccessRegionList(accessRegionList);
        for (Iterator iter = accessRegionList.iterator(); iter.hasNext();) {
            RegionDTO region = (RegionDTO) iter.next();
            if (region.getRegionType() == RegionDTO.REGION_TYPE_SHNEG) {
                selRegionForm.getShengList().add(region);
            } else if (region.getRegionType() == RegionDTO.REGION_TYPE_SHI) {
                selRegionForm.getShiList().add(region);
            } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QUXIAN) {
                selRegionForm.getQuxianList().add(region);
            } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                selRegionForm.getQuList().add(region);
            }
        }

        if (selRegionForm.getShengList().size() == 1) {
            RegionDTO region = (RegionDTO) selRegionForm.getShengList().get(0);
            selRegionForm.setSelShengId(region.getRegionId().longValue());
        }

        if (selRegionForm.getShiList().size() == 1) {
            RegionDTO region = (RegionDTO) selRegionForm.getShiList().get(0);
            selRegionForm.setSelShiId(region.getRegionId().longValue());
        }

        if (selRegionForm.getQuxianList().size() == 1) {
            RegionDTO region = (RegionDTO) selRegionForm.getQuxianList().get(0);
            selRegionForm.setSelQuxianId(region.getRegionId().longValue());
        }

        return mapping.getInputForward();
    }

    /**
     * 切换省
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward changePriv(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SelRegionForm selRegionForm = (SelRegionForm) form;
        List accessRegionList = selRegionForm.getAccessRegionList();
        long selShengId = selRegionForm.getSelShengId();

        selRegionForm.getShiList().clear();
        selRegionForm.getQuxianList().clear();
        selRegionForm.getQuList().clear();
        if (selShengId > 0) {
            RegionDTO selRegion = null;
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionId().longValue() == selShengId) {
                    selRegion = region;
                    break;
                }
            }

            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionPath()
                        .startsWith(selRegion.getRegionPath())) {
                    if (region.getRegionType() == RegionDTO.REGION_TYPE_SHI) {
                        selRegionForm.getShiList().add(region);
                    } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QUXIAN) {
                        selRegionForm.getQuxianList().add(region);
                    } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                        selRegionForm.getQuList().add(region);
                    }
                }
            }
        } else {
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();

                if (region.getRegionType() == RegionDTO.REGION_TYPE_SHI) {
                    selRegionForm.getShiList().add(region);
                } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QUXIAN) {
                    selRegionForm.getQuxianList().add(region);
                } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                    selRegionForm.getQuList().add(region);
                }
            }
        }

        selRegionForm.setSelShiId(-1);
        selRegionForm.setSelQuxianId(-1);
        selRegionForm.setSelQuId(-1);
        return mapping.getInputForward();
    }

    /**
     * 切换城市
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward changeCity(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SelRegionForm selRegionForm = (SelRegionForm) form;
        List accessRegionList = selRegionForm.getAccessRegionList();
        long selShengId = selRegionForm.getSelShiId();

        selRegionForm.getQuxianList().clear();
        selRegionForm.getQuList().clear();
        if (selShengId > 0) {
            RegionDTO selRegion = null;
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionId().longValue() == selShengId) {
                    selRegion = region;
                    break;
                }
            }

            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionPath()
                        .startsWith(selRegion.getRegionPath())) {
                    if (region.getRegionType() == RegionDTO.REGION_TYPE_QUXIAN) {
                        selRegionForm.getQuxianList().add(region);
                    } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                        selRegionForm.getQuList().add(region);
                    }
                }
            }
        } else {
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionType() == RegionDTO.REGION_TYPE_QUXIAN) {
                    selRegionForm.getQuxianList().add(region);
                } else if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                    selRegionForm.getQuList().add(region);
                }
            }
        }
        return mapping.getInputForward();
    }

    /**
     * 切换区县
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ActionForward changeDistrict(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SelRegionForm selRegionForm = (SelRegionForm) form;
        List accessRegionList = selRegionForm.getAccessRegionList();
        selRegionForm.getQuList().clear();
        long selShengId = selRegionForm.getSelQuxianId();
        if (selShengId > 0) {
            RegionDTO selRegion = null;
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionId().longValue() == selShengId) {
                    selRegion = region;
                    break;
                }
            }

            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionPath()
                        .startsWith(selRegion.getRegionPath())) {
                    if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                        selRegionForm.getQuList().add(region);
                    }
                }
            }
        } else {
            for (Iterator iterator = accessRegionList.iterator(); iterator
                    .hasNext();) {
                RegionDTO region = (RegionDTO) iterator.next();
                if (region.getRegionType() == RegionDTO.REGION_TYPE_QU) {
                    selRegionForm.getQuList().add(region);
                }
            }
        }

        selRegionForm.setSelQuId(-1);
        return mapping.getInputForward();
    }

    @SuppressWarnings("unchecked")
	public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SelRegionForm selRegionForm = (SelRegionForm) form;
        long selRegionId = 0;
        if (selRegionForm.getSelQuId() > 0) {
            selRegionId = selRegionForm.getSelQuId();
        } else if (selRegionForm.getSelQuxianId() > 0) {
            selRegionId = selRegionForm.getSelQuxianId();
        } else if (selRegionForm.getSelShiId() > 0) {
            selRegionId = selRegionForm.getSelShiId();
        } else if (selRegionForm.getSelShengId() > 0) {
            selRegionId = selRegionForm.getSelShengId();
        }

        String regionName = selRegionForm.getRegionName();
        int searchType = selRegionForm.getSearchType();

        // 查询可访问区域
        List orgIds = this.orgManager.findAccessOrgIdsOfOrg(new Long(
                selRegionForm.getOrgId()), OrganizationDTO.ORG_TYPE_REGION,
                true);
        // 查询数据区域可访问的行政区域
        List accessRegions = this.regionManager.findRegionsOfOrgs(
                (Long[]) orgIds.toArray(new Long[0]), regionName, new Long(
                        selRegionId), RegionDTO.REGION_TYPE_XIAOQU, searchType);

        List regions = new ArrayList();
        List temp = this.regionManager.findRegionsOfOrgs((Long[]) orgIds.toArray(new Long[0]));
        for (Iterator iter = accessRegions.iterator(); iter.hasNext();) {
            RegionDTO element = (RegionDTO) iter.next();
            for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
                RegionDTO regionDTO = (RegionDTO) iterator.next();
                if (element.getRegionPath().startsWith(
                        regionDTO.getRegionPath())) {
                    regions.add(element);
                    break;
                }
            }
        }
        request.setAttribute("accessRegions", regions);

        return mapping.getInputForward();
    }

    /**
     * @spring.property ref="regionManager"
     * @param regionManager
     *            the regionManager to set
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     *            the orgManager to set
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }
}
