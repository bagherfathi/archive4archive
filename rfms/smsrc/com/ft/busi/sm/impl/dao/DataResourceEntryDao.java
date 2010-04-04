package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleDataRes;

/**
 * DataResourceEntry 实体数据访问类
 * 
 * @spring.bean id="DataResourceEntryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceEntryDao extends BaseDao {

    /**
     * 构造函数
     */
    public DataResourceEntryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return DataResourceEntry.class;
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceEntry getById(Serializable key) {
        return (DataResourceEntry) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceEntry loadById(Serializable key) {
        return (DataResourceEntry) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        StringBuffer hql = new StringBuffer("from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        return this.query(hql.toString());
    }

    /**
     * 删除业务权限的所有条目
     * 
     * @param dataResourceId
     *            业务权限ID
     */
    public void deleteEntriesOfDataResource(Long dataResourceId) {
        StringBuffer hql = new StringBuffer("from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { dataResourceId });
    }

    /**
     * 查询角色可访问的业务权限。
     * 
     * @param roleId
     *            角色ID。
     * @return DataResourceEntry对象列表。
     */
    public List findDataResourcesOfRole(Long roleId) {
        StringBuffer hql = new StringBuffer(
                "select resEntry from DataResourceEntry resEntry,RelRoleDataRes rrd");
        hql.append(" where resEntry.").append(DataResourceEntry.PROP_ENTRY_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID)
                .append("=?");

        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * 根据业务权限ID查询业务权限条目
     * 
     * @param dataResourceId
     *            业务权限ID
     * @return 业务权限实体集合
     */
    public List findDataResourceEntriesOfOp(Long opId, long dataResourceId) {
        StringBuffer hql = new StringBuffer("select entry");
        hql
                .append(" from DataResourceEntry entry,RelRoleDataRes rrd,RelOperRole ror");
        hql.append(" where entry.").append(DataResourceEntry.PROP_ENTRY_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=ror.").append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");

        return this.query(hql.toString(), new Object[] { opId,
                new Long(dataResourceId) });
    }

    /**
     * 判断这条业务权限条目标题是否已在业务权限中存在
     * 
     * @param title
     *            业务权限条目标题
     * @param resourceId
     *            业务权限Id
     * @return
     */
    public boolean isExistEntryByDataResource(Long entryId, String title,
            Long resourceId) {
        StringBuffer hql = new StringBuffer("select entry.");
        hql.append(DataResourceEntry.PROP_ENTRY_ID);
        hql.append(" from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_ENTRY_NAME)
                .append("=?");
        hql.append(" and entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");
        hql.append(" and entry.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        List result = null;

        if (entryId != null) {
            hql.append(" and entry.").append(DataResourceEntry.PROP_ENTRY_ID)
                    .append("!=?");
            result = this.query(hql.toString(), new Object[] { title,
                    resourceId, entryId });
        } else {
            result = this.query(hql.toString(), new Object[] { title,
                    resourceId });
        }

        return result.size() > 0 ? true : false;
    }

    /**
     * 检查单值型业务权限的值是否重复
     * 
     * @param entryId
     *            条目ID
     * @param resourceId
     *            业务权限ID
     * @param entryValue
     *            条目值
     * @return 重复 true
     */
    public boolean isEntryValueDuplicatedOfSingleDataResource(Long entryId,
            Long resourceId, String entryValue) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append(" from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_MAX_VALUE);
        hql.append(" = ? ");
        params.add(entryValue);
        hql.append(" and entry.").append(DataResourceEntry.PROP_RESOURCE_ID);
        hql.append(" = ? ");
        params.add(resourceId);
        if (entryId != null) {
            hql.append(" and entry.").append(DataResourceEntry.PROP_ENTRY_ID);
            hql.append(" != ?");
            params.add(entryId);
        }
        hql.append(" and entry.").append(DataResourceEntry.PROP_EXPIRE_DATE);
        hql.append(" is null ");
        List result = this.query(hql.toString(), params.toArray());
        if (result != null && result.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param resourceId
     * @return
     */
    public List findEntryByDataResourceId(Long resourceId) {
        StringBuffer hql = new StringBuffer("from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");
        hql.append(" and entry.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        return this.query(hql.toString(), new Object[] { resourceId });
    }

    public List findDataResourceEntriesOfOp(Long opId, long resourceId,
            Long[] orgIds) {
        StringBuffer hql = new StringBuffer("select entry");
        hql.append(" from DataResourceEntry entry,RelRoleDataRes rrd");
        hql.append(" ,RelOperRole ror");
        hql.append(" where entry.").append(DataResourceEntry.PROP_ENTRY_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ENTRY_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=ror.").append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");
        
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append(" in (-1");
        for (int i = 0; i < orgIds.length; i++) {
            hql.append(",");
            hql.append(orgIds[i]);
        }
        hql.append(")");
        
        return this.query(hql.toString(), new Object[] { opId,
                new Long(resourceId )});

    }
}