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
 * Resource 实体数据访问类
 * 
 * @spring.bean id="ResourceDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ResourceDao extends BaseDao {

    /**
     * 构造函数
     */
    public ResourceDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Resource.class;
    }

    /**
     * 根据ID查找对象
     */
    public Resource getById(Serializable key) {
        return (Resource) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public Resource loadById(Serializable key) {
        return (Resource) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询角色可访问功能权限。
     * 
     * @param roleId
     *                角色ID。
     * @return Resource对象列表。
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
     * 查询角色可访问的功能权限，包括所有的子功能权限。
     * 
     * @param roleId
     *                角色ID。
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
     * 删除所有和roleId相关的RelRoleDataRes信息
     * 
     * @param roleId
     *                角色ID
     */
    public void delDataResourceByRoleId(Long roleId) {

        StringBuffer hql = new StringBuffer("from RelRoleDataRes rrd");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ROLE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { roleId });
    }

    /**
     * 查询菜单下子菜单最大顺序号
     * 
     * @param parentId
     *                菜单ID
     * @return 菜单下子菜单的最大序号
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
     * 查询根菜单最大顺序号
     * 
     * @return 根菜单下子菜单的最大序号
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
     * 删除功能权限
     * 
     * @param resourceId
     *                功能权限ID
     */
    public void deleteResource(Long resourceId) {
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { resourceId });
    }

    /**
     * 查询功能权限所有子功能权限
     * 
     * @param resourceId
     *                功能权限ID
     * @return 功能权限实体列表
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
     * 查询所有功能权限
     * 
     * @return 功能权限实体列表
     */
    public List findAllResources() {
        StringBuffer query = new StringBuffer("from Resource r");
        query.append(" where r.").append(Resource.PROP_EXPIRE_DATE).append(" is null");
        query.append(" order by r.").append(Resource.PROP_MENU_ORDER);

        return this.query(query.toString());
    }

    /**
     * 查询操作员拥有角色的功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                可访问组织ID
     * @return 功能权限实体列表
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
     * 查询操作员拥有角色的功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                可访问组织ID
     * @return 功能权限实体列表
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
     * 查询操作员所属组拥有角色的功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                可访问组织ID
     * @return 功能权限实体列表
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
     * 查询操作员所属组拥有角色的功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                可访问组织ID
     * @return 功能权限实体列表
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
     * 查询操作员用户的全部功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @return 功能权限实体列表
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
     * 查询操作员所在组的全部功能权限列表
     * 
     * @param opId
     *                操作员ID
     * @return 功能权限实体列表
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
     * 查找操作员组所拥有的全部功能权限
     * 
     * @param groupId
     *                操作员ID
     * @return 功能权限实体列表
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
     * 通过Path获得他所对应的所有子资源
     * 
     * @param path
     *                功能权限路径
     * @return 功能权限实体列表
     */
    public List findChildResourceByPatch(String path) {
        StringBuffer hql = new StringBuffer("from Resource res");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like ? || '%'");

        return this.query(hql.toString(), new Object[] { path });

    }

    /**
     * 判断Url是否重复，不包含""和"/"
     * 
     * @param url
     *                功能权限URL
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
     * 根据权限标题在同一level中查询功能权限。
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
     * 判断一级菜单是否有重名
     * 
     * @param title
     *                功能权限标题
     * @param resourceId
     *                功能权限ID
     * @param parentId
     *                父ID
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
     * 返回所有ParentId为自身的实体
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
     * 查询功能权限所有子功能权限，只包括菜单权限。
     * 
     * @param resourceId
     *                功能权限ID
     * @return 功能权限实体列表
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