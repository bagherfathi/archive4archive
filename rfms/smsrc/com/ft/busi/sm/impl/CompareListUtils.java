package com.ft.busi.sm.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.Region;
import com.ft.sm.entity.Resource;
import com.ft.sm.entity.Role;

/**
 * 比较列表取出差异的元素
 * 
 * @version 1.0
 */
public class CompareListUtils {
    /**
     * 比较功能权限实体列表，找出目标列表在源列表中不存在的功能权限实体
     * 
     * @param source
     * @param target
     * @return
     * @throws Exception
     */
    public static List compareResourceList(List source, List target) {
        List<Resource> result = new ArrayList<Resource>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Resource obj = (Resource) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                Resource element = (Resource) iterator.next();
                if (element.getResourceId() == obj.getResourceId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * 比较业务权限条目列表，找出目标列表在源列表中不存在的业务权限条目
     * 
     * @param source
     * @param target
     * @return
     * @throws Exception
     */
    public static List compareDataResourceEntryList(List source, List target) {
        List<DataResourceEntry> result = new ArrayList<DataResourceEntry>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            DataResourceEntry obj = (DataResourceEntry) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                DataResourceEntry element = (DataResourceEntry) iterator.next();
                if (element.getEntryId() == obj.getEntryId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * 比较组织列表，找出目标列表在源列表中不存在的组织
     * 
     * @param source
     * @param target
     * @return
     */
    public static List compareOrgList(List source, List target) {
        List<Organization> result = new ArrayList<Organization>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Organization obj = (Organization) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                Organization element = (Organization) iterator.next();
                if (element.getOrgId() == obj.getOrgId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * 比较操作员组列表，找出目标列表在源列表中不存在的操作员组
     * 
     * @param source
     * @param target
     * @return
     */
    public static List compareGroupList(List source, List target) {
        List<Group> result = new ArrayList<Group>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Group obj = (Group) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                Group element = (Group) iterator.next();
                if (element.getGroupId() == obj.getGroupId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * 比较角色列表，找出目标列表在源列表中不存在的角色
     * 
     * @param source
     * @param target
     * @return
     */
    public static List compareRoleList(List source, List target) {
        List<Role> result = new ArrayList<Role>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Role obj = (Role) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                Role element = (Role) iterator.next();
                if (element.getRoleId() == obj.getRoleId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * 比较区域列表，找出目标列表在源列表中不存在的角色
     * 
     * @param source
     * @param target
     * @return
     */
    public static List compareRegionList(List source, List target) {
        List<Region> result = new ArrayList<Region>();

        for (Iterator iter = target.iterator(); iter.hasNext();) {
            Region obj = (Region) iter.next();

            boolean in = false;
            for (Iterator iterator = source.iterator(); iterator.hasNext();) {
                Region element = (Region) iterator.next();
                if (element.getRegionId() == obj.getRegionId()) {
                    in = true;
                    break;
                }
            }

            if (!in) {
                result.add(obj);
            }
        }

        return result;
    }
    
    /**
     * 比较ID数组
     * @param source
     * @param target
     * @return
     */
    public static List compareByIds(Long[] source, Long[] target) {
        List<Long> result = new ArrayList<Long>();
        for (int i = 0; i < target.length; i++) {
            if (!ArrayUtils.contains(source, target[i])) {
                result.add(target[i]);
            }
        }
        return result;
    }
}
