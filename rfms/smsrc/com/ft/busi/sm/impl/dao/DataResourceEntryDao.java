package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleDataRes;

/**
 * DataResourceEntry ʵ�����ݷ�����
 * 
 * @spring.bean id="DataResourceEntryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceEntryDao extends BaseDao {

    /**
     * ���캯��
     */
    public DataResourceEntryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return DataResourceEntry.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceEntry getById(Serializable key) {
        return (DataResourceEntry) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceEntry loadById(Serializable key) {
        return (DataResourceEntry) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        StringBuffer hql = new StringBuffer("from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_EXPIRE_DATE)
                .append(" is null");

        return this.query(hql.toString());
    }

    /**
     * ɾ��ҵ��Ȩ�޵�������Ŀ
     * 
     * @param dataResourceId
     *            ҵ��Ȩ��ID
     */
    public void deleteEntriesOfDataResource(Long dataResourceId) {
        StringBuffer hql = new StringBuffer("from DataResourceEntry entry");
        hql.append(" where entry.").append(DataResourceEntry.PROP_RESOURCE_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { dataResourceId });
    }

    /**
     * ��ѯ��ɫ�ɷ��ʵ�ҵ��Ȩ�ޡ�
     * 
     * @param roleId
     *            ��ɫID��
     * @return DataResourceEntry�����б�
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
     * ����ҵ��Ȩ��ID��ѯҵ��Ȩ����Ŀ
     * 
     * @param dataResourceId
     *            ҵ��Ȩ��ID
     * @return ҵ��Ȩ��ʵ�弯��
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
     * �ж�����ҵ��Ȩ����Ŀ�����Ƿ�����ҵ��Ȩ���д���
     * 
     * @param title
     *            ҵ��Ȩ����Ŀ����
     * @param resourceId
     *            ҵ��Ȩ��Id
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
     * ��鵥ֵ��ҵ��Ȩ�޵�ֵ�Ƿ��ظ�
     * 
     * @param entryId
     *            ��ĿID
     * @param resourceId
     *            ҵ��Ȩ��ID
     * @param entryValue
     *            ��Ŀֵ
     * @return �ظ� true
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