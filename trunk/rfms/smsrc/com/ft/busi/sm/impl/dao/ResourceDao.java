package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleDataRes;
import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Resource;
import com.ft.sm.entity.Role;

/**
 * Resource ʵ�����ݷ�����
 * 
 * @spring.bean id="ResourceDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ResourceDao extends BaseDao {

    /**
     * ���캯��
     */
    public ResourceDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Resource.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Resource getById(Serializable key) {
        return (Resource) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Resource loadById(Serializable key) {
        return (Resource) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ��ɫ�ɷ��ʹ���Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @return Resource�����б�
     */
    public List findResourcesOfRole(Long roleId) {
        StringBuffer hql = new StringBuffer(
                "select res from Resource res,RelRoleRes rrr");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        hql.append(" and res.").append(Resource.PROP_EXPIRE_DATE).append(" is null");

        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * ��ѯ��ɫ�ɷ��ʵĹ���Ȩ�ޣ��������е��ӹ���Ȩ�ޡ�
     * 
     * @param roleId
     *                ��ɫID��
     * @return
     */
    public List findAllCheckedResourceByRoleId(Long roleId) {
        StringBuffer hql = new StringBuffer(
                "select res from Resource res,RelRoleRes rrr");
        hql.append(" where rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=?");
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");
        hql.append(" and res.").append(Resource.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString(), new Object[] { roleId });
    }

    /**
     * ɾ�����к�roleId��ص�RelRoleDataRes��Ϣ
     * 
     * @param roleId
     *                ��ɫID
     */
    public void delDataResourceByRoleId(Long roleId) {

        StringBuffer hql = new StringBuffer("from RelRoleDataRes rrd");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { roleId });
    }

    /**
     * ��ѯ�˵����Ӳ˵����˳���
     * 
     * @param parentId
     *                �˵�ID
     * @return �˵����Ӳ˵���������
     */
    public int getMaxOrder(Long parentId) {
        StringBuffer hql = new StringBuffer("select max (res.").append(
                Resource.PROP_MENU_ORDER).append(")");
        hql.append(" from Resource res");
        hql.append(" where res.").append(Resource.PROP_PARENT_ID).append("=?");
        hql.append(" and res.").append(Resource.PROP_PARENT_ID)
                .append("!=res.").append(Resource.PROP_RESOURCE_ID);

        Long order = (Long) this.getSingle(hql.toString(),
                new Object[] { parentId });
        if (order != null)
            return order.intValue();

        return 0;
    }

    /**
     * ��ѯ���˵����˳���
     * 
     * @return ���˵����Ӳ˵���������
     */
    public int getRootMaxOrder() {
        StringBuffer hql = new StringBuffer("select max (res.").append(
                Resource.PROP_MENU_ORDER).append(")");
        hql.append(" from Resource res");
        hql.append(" where res.").append(Resource.PROP_PARENT_ID).append(
                "=res.").append(Resource.PROP_RESOURCE_ID);

        Long order = (Long) this.getSingle(hql.toString(), new Object[0]);
        if (order != null)
            return order.intValue();

        return 0;
    }

    /**
     * ɾ������Ȩ��
     * 
     * @param resourceId
     *                ����Ȩ��ID
     */
    public void deleteResource(Long resourceId) {
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { resourceId });
    }

    /**
     * ��ѯ����Ȩ�������ӹ���Ȩ��
     * 
     * @param resourceId
     *                ����Ȩ��ID
     * @return ����Ȩ��ʵ���б�
     */
    public List findChildrenResourceById(Long resourceId) {
        StringBuffer query = new StringBuffer("from Resource r");
        query.append(" where r.").append(Resource.PROP_PARENT_ID).append("=?");
        query.append(" and r.").append(Resource.PROP_RESOURCE_ID).append("!=?");
        query.append(" and r.").append(Resource.PROP_EXPIRE_DATE).append(" is null");
        query.append(" order by r.").append(Resource.PROP_MENU_ORDER);

        return this.query(query.toString(), new Object[] { resourceId,
                resourceId });
    }

    /**
     * ��ѯ���й���Ȩ��
     * 
     * @return ����Ȩ��ʵ���б�
     */
    public List findAllResources() {
        StringBuffer query = new StringBuffer("from Resource r");
        query.append(" where r.").append(Resource.PROP_EXPIRE_DATE).append(" is null");
        query.append(" order by r.").append(Resource.PROP_MENU_ORDER);

        return this.query(query.toString());
    }

    /**
     * ��ѯ����Աӵ�н�ɫ�Ĺ���Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                �ɷ�����֯ID
     * @return ����Ȩ��ʵ���б�
     */
    public List findOpRoleACLResources(Long opId, Long orgId, long roleType) {
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,Operator op,Role role,RelRoleRes rrr,RelOperRole ror");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append("=?");
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append("=?");
        hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID)
                .append("=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID)
                .append("=role.").append(Role.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);

        return this.query(hql.toString(), new Object[] { opId, orgId,
                new Long(roleType) });
    }
    
    /**
     * ��ѯ����Աӵ�н�ɫ�Ĺ���Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                �ɷ�����֯ID
     * @return ����Ȩ��ʵ���б�
     */
    public List findOpRoleACLResources(Long opId, Long[] orgIds, long roleType) {
        List<Object> params = new ArrayList<Object>();
        
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,Operator op,Role role,RelRoleRes rrr,RelOperRole ror");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append("=?");
        
        params.add(opId);
        
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID)
                .append("=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID)
                .append("=role.").append(Role.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        
        params.add(new Long(roleType));
        
        hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);
        
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append(" in(-1");
        
        for (int i = 0; i < orgIds.length; i++) {
            hql.append(",?");
            params.add(orgIds[i]);
        }
        hql.append(")");

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }
    
    public List findOpRoleResources(Long opId,Long[] orgIds,long roleType){
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select distinct res");
        hql
                .append(" from Resource res,Operator op,Role role,RelRoleRes rrr,RelOperRole ror");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append("=?");
        params.add(opId);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append(" in (-1");
        
    
        for (int i = 0; i < orgIds.length; i++) {
            hql.append(",?");
            params.add(orgIds[i]);
        }
        hql.append(")");
        
        hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID)
                .append("=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID)
                .append("=role.").append(Role.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        params.add(new Long(roleType));
        hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);

        return this.query(hql.toString(),params.toArray(new Object[0]));
    }

    /**
     * ��ѯ����Ա������ӵ�н�ɫ�Ĺ���Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                �ɷ�����֯ID
     * @return ����Ȩ��ʵ���б�
     */
    public List findOpGroupRoleACLResources(Long opId, Long orgId, long roleType) {
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,RelRoleRes rrr,RelGroupRole rgr,RelOperGroup rog,Role role");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=rgr.")
                .append(RelGroupRole.PROP_ROLE_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");
        hql.append(" and role.").append(Role.PROP_ROLE_ID).append("=rrr.")
                .append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");

        return this.query(hql.toString(), new Object[] { opId, orgId,
                new Long(roleType) });
    }
    
    /**
     * ��ѯ����Ա������ӵ�н�ɫ�Ĺ���Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @param orgId
     *                �ɷ�����֯ID
     * @return ����Ȩ��ʵ���б�
     */
    public List findOpGroupRoleACLResources(Long opId, Long[] orgIds, long roleType) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,RelRoleRes rrr,RelGroupRole rgr,RelOperGroup rog,Role role");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_RESOURCE_ID);
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=rgr.")
                .append(RelGroupRole.PROP_ROLE_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        params.add(opId);
        hql.append(" and role.").append(Role.PROP_ROLE_ID).append("=rrr.")
                .append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and role.").append(Role.PROP_ROLE_TYPE).append("=?");
        params.add(new Long(roleType));
        
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append(" in(-1");
        
        for (int i = 0; i < orgIds.length; i++) {
            hql.append(",?");
            params.add(orgIds[i]);
        }
        hql.append(")");
        

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }

    /**
     * ��ѯ����Ա�û���ȫ������Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @return ����Ȩ��ʵ���б�
     */
    public List findAllResourceOpRole(Long opId) {
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,Operator op,RelRoleRes rrr,RelOperRole ror");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append("=?");
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelRoleRes.PROP_ROLE_ID).append("=rrr.")
                .append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");

        return this.query(hql.toString(), new Object[] { opId });
    }

    /**
     * ��ѯ����Ա�������ȫ������Ȩ���б�
     * 
     * @param opId
     *                ����ԱID
     * @return ����Ȩ��ʵ���б�
     */
    public List findAllResourcesOpGroupRole(Long opId) {
        StringBuffer hql = new StringBuffer("select res");
        hql
                .append(" from Resource res,Operator op,RelRoleRes rrr,RelGroupRole rgr,RelOperGroup rog");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append("=?");
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID)
                .append("=rog.").append(RelOperGroup.PROP_OPERATOR_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_ROLE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");

        return this.query(hql.toString(), new Object[] { opId });
    }

    /**
     * ���Ҳ���Ա����ӵ�е�ȫ������Ȩ��
     * 
     * @param groupId
     *                ����ԱID
     * @return ����Ȩ��ʵ���б�
     */
    public List findAllResourceOfGroup(Long groupId) {
        StringBuffer hql = new StringBuffer("select distinct res");
        hql.append(" from RelGroupRole rgr,RelRoleRes rrr,Resource res");
        hql.append(" where rgr.").append(RelGroupRole.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and rgr.").append(RelGroupRole.PROP_ROLE_ID).append(
                "=rrr.").append(RelRoleRes.PROP_ROLE_ID);
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");

        return this.query(hql.toString(), new Object[] { groupId });
    }

    /**
     * ͨ��Path���������Ӧ����������Դ
     * 
     * @param path
     *                ����Ȩ��·��
     * @return ����Ȩ��ʵ���б�
     */
    public List findChildResourceByPatch(String path) {
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like ? || '%'");

        return this.query(hql.toString(), new Object[] { path });

    }

    /**
     * �ж�Url�Ƿ��ظ���������""��"/"
     * 
     * @param url
     *                ����Ȩ��URL
     * @return
     */
    public boolean isExistMenuUrl(String url, Long resourceId) {
        boolean exist = false;
        if (null != url) {
            if (!url.equals("") && !url.equals("/")) {
                List result = null;
                StringBuffer hql = new StringBuffer("from Resource res");
                hql.append(" where res.").append(Resource.PROP_MENU_URL)
                        .append("=?");
                if (null != resourceId) {
                    hql.append(" and res.").append(Resource.PROP_RESOURCE_ID)
                            .append("!=?");
                    result = this.query(hql.toString(), new Object[] { url,
                            resourceId });
                } else {
                    result = this.query(hql.toString(), new Object[] { url });
                }
                exist = (null != result && result.size() > 0) ? true : false;
            }
        }
        return exist;
    }
    
    /**
     * ����Ȩ�ޱ�����ͬһlevel�в�ѯ����Ȩ�ޡ�
     */
    public List findResourceByTitleInSameLevel(String title,Long parentId){
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where 1=1");
        if(parentId == null){
            hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append("=res.").append(Resource.PROP_PARENT_ID);
            hql.append(" and res.").append(Resource.PROP_RESOURCE_TITLE).append("=?");
            hql.append(" and res.").append(Resource.PROP_EXPIRE_DATE).append(" is null ");
            return this.query(hql.toString(), new Object[]{title});
        }else{
            hql.append(" and res.").append(Resource.PROP_RESOURCE_TITLE).append("=?");
            hql.append(" and res.").append(Resource.PROP_PARENT_ID).append("=?");
            hql.append(" and res.").append(Resource.PROP_PARENT_ID).append("!=");
            hql.append(" res.").append(Resource.PROP_RESOURCE_ID);
            hql.append(" and res.").append(Resource.PROP_EXPIRE_DATE).append(" is null ");
            return this.query(hql.toString(),new Object[]{title,parentId});
        }
    }

    /**
     * �ж�һ���˵��Ƿ�������
     * 
     * @param title
     *                ����Ȩ�ޱ���
     * @param resourceId
     *                ����Ȩ��ID
     * @param parentId
     *                ��ID
     * @return
     */
    public boolean isExistMenu(String title, Long resourceId, Long parentId) {
        List result = null;
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_TITLE).append(
                "=?");

        if (resourceId.longValue() == parentId.longValue()) {
            hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                    "=res.").append(Resource.PROP_PARENT_ID);
            hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                    "=?");
            result = this.query(hql.toString(), new Object[] { title,
                    resourceId });
        } else {
            hql.append("and res.").append(Resource.PROP_PARENT_ID).append("=?");
            hql.append(" and res.").append(Resource.PROP_RESOURCE_ID).append(
                    "<>?");
            result = this.query(hql.toString(), new Object[] { title, parentId,
                    resourceId });
        }

        return result.size() > 0 ? true : false;
    }
    
    /**
     * ��������ParentIdΪ�����ʵ��
     * @return  
     */
    public List findRoots() {
        StringBuffer hql = new StringBuffer(" from Resource res ");
        hql.append("where res.").append(Resource.PROP_RESOURCE_ID);
        hql.append(" = res.").append(Resource.PROP_PARENT_ID);
        hql.append(" order by res.").append(Resource.PROP_MENU_ORDER);
        List result = this.query(hql.toString());
        return result;
    }
    
    /**
     * 
     * ��ѯ����Ȩ�������ӹ���Ȩ�ޣ�ֻ�����˵�Ȩ�ޡ�
     * 
     * @param resourceId
     *                ����Ȩ��ID
     * @return ����Ȩ��ʵ���б�
     */
	public List findChildrenMenuResourceById(Long resourceId) {
		 StringBuffer query = new StringBuffer("from Resource r");
	        query.append(" where r.").append(Resource.PROP_PARENT_ID).append("=?");
	        query.append(" and r.").append(Resource.PROP_RESOURCE_ID).append("!=?");
	        query.append(" and r.").append(Resource.PROP_RESOURCE_TYPE).append("=");
	        query.append(ResourceDTO.MENU_TYPE);
	        query.append(" order by r.").append(Resource.PROP_MENU_ORDER);

	        return this.query(query.toString(), new Object[] { resourceId,
	                resourceId });
	}
}