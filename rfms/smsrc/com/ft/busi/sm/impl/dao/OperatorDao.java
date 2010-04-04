package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.ConsignPermit;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperOrg;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.RelRoleDataRes;
import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Resource;

/**
 * Operator 实体数据访问类
 * 
 * @spring.bean id="OperatorDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class OperatorDao extends BaseDao {

    /**
     * 构造函数
     */
    public OperatorDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Operator.class;
    }

    /**
     * 根据ID查找对象
     */
    public Operator getById(Serializable key) {
        return (Operator) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public Operator loadById(Serializable key) {
        return (Operator) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询组织中所有操作员
     * 
     * @param orgId
     *                组织ID
     * @return 操作员实体列表
     */
    public List findOperatorsByOrg(Long orgId) {
        StringBuffer hql = new StringBuffer("from Operator op");
        hql.append(" where op.").append(Operator.PROP_ORG_ID).append("=?");

        return this.query(hql.toString(), new Object[] { orgId });
    }

    /**
     * 查询操作员可访问组织中其他操作员
     * 
     * @param operatorId
     *                操作员ID
     * @return 操作员实体列表
     */
    public List findCanConsignedOperators(Long operatorId, String opName,
            String loginName) {
        StringBuffer hql = new StringBuffer(
                "select op from Operator op,RelOperOrg roo");
        hql.append(" where op.").append(Operator.PROP_OPERATOR_ID)
                .append("!=?");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=roo.")
                .append(RelOperOrg.PROP_ORG_ID);

        List<Object> params = new ArrayList<Object>();
        params.add(operatorId);
        params.add(operatorId);

        if (opName != null && opName.length() > 0) {
            hql.append(" and op.").append(Operator.PROP_OP_NAME).append(
                    " like ?");
            params.add("%" + opName + "%");
        }

        if (loginName != null && loginName.length() > 0) {
            hql.append(" and op.").append(Operator.PROP_LOGIN_NAME).append(
                    " like ?");
            params.add("%" + loginName + "%");
        }

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }

    /**
     * 查询功能权限已分配的操作员组中的操作员。
     * 
     * @param resourceId
     * @return
     */
    public List findOperatorsByResourceInGroup(Long orgId,Long resourceId) {
        StringBuffer hql = new StringBuffer("select distinct op");
        hql
                .append(" from Operator op,Resource res,RelRoleRes rrr,RelGroupRole rgr,RelOperGroup rog");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID)
                .append("=?");
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=rgr.")
                .append(RelGroupRole.PROP_ROLE_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append(
                "=rgr.").append(RelGroupRole.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and rgr.").append(RelGroupRole.PROP_ORG_ID).append("=?");

        return this.query(hql.toString(), new Object[] { resourceId,orgId });
    }
    
    /**
     * 根据资源ID查询被委托的操作员。
     * @param orgId
     * @param resourceId
     * @param date
     * @return
     */
    public List findOperatorsByResourceOfConsign(Long orgId,Long resourceId,Date date){
        StringBuffer hql = new StringBuffer("select distinct op");
        hql.append(" from Operator op,ConsignPermit conPermit where 1=1");
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID).append("=conPermit.").append(ConsignPermit.PROP_CONSIGNEE_ID);
        hql.append(" and conPermit.").append(ConsignPermit.PROP_ORG_ID).append("=?");
        hql.append(" and conPermit.").append(ConsignPermit.PROP_RESOURCE_ID).append("=?");
        hql.append(" and conPermit.").append(ConsignPermit.PROP_VALID_TIME).append("<=?");
        hql.append(" and conPermit.").append(ConsignPermit.PROP_EXPIRE_TIME).append(">=?");
        
        
        return this.query(hql.toString(),new Object[]{orgId,resourceId,date,date});
        
    }

    /**
     * 查询功能权限已分配的操作员。
     * 
     * @param resourceId
     * @return
     */
    public List findOperatorsByResource(Long orgId,Long resourceId) {
        StringBuffer hql = new StringBuffer("select distinct op");
        hql
                .append(" from Operator op,Resource res,RelRoleRes rrr,RelOperRole ror");
        hql.append(" where res.").append(Resource.PROP_RESOURCE_ID)
                .append("=?");
        hql.append(" and res.").append(Resource.PROP_RESOURCE_PATH).append(
                " like rrr.").append(RelRoleRes.PROP_RESOURCE_PATH).append(
                " || '%'");
        hql.append(" and rrr.").append(RelRoleRes.PROP_ROLE_ID).append("=ror.")
                .append(RelOperRole.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_ORG_ID).append("=?");

        return this.query(hql.toString(), new Object[] { resourceId ,orgId});
    }

    /**
     * 查询业务权限分配给的操作员
     * 
     * @param resourceId
     *                业务权限ID
     * @return
     */
    public List findOperatorOfDataResourceEntry(Long entryId) {
        StringBuffer hql = new StringBuffer("select distinct op");
        hql.append(" from Operator op,RelRoleDataRes rrd,RelOperRole ror");
        hql.append(" where rrd.").append(RelRoleDataRes.PROP_ENTRY_ID).append(
                "=?");
        hql.append(" and ror.").append(RelOperRole.PROP_ROLE_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);

        return this.query(hql.toString(), new Object[] { entryId });
    }
    /**
     * 查询业务权限分配给的操作员
     * 
     * @param resourceId
     *                业务权限ID数组
     * @return 
     */
    @SuppressWarnings("unchecked")
	public List findOperatorOfDataResourceEntry(Long[] entryIds){
        StringBuffer hql = new StringBuffer("select op.operatorId,rrd.entryId");
        hql.append(" from Operator op,RelRoleDataRes rrd,RelOperRole ror");
        hql.append(" where ror.").append(RelOperRole.PROP_ROLE_ID)
                .append("=rrd.").append(RelRoleDataRes.PROP_ROLE_ID);
        hql.append(" and ror.").append(RelOperRole.PROP_OPERATOR_ID).append(
                "=op.").append(Operator.PROP_OPERATOR_ID);
        hql.append(" and rrd.").append(RelRoleDataRes.PROP_ENTRY_ID).append(
        " in ");
        
        Map<Object,Object> operatorMap = new HashMap<Object,Object>(); 
        List result = this.queryIn(hql.toString(),  entryIds );
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object[] element = (Object[]) iterator.next();
            Long operatorId = (Long)element[0];
            Long entryId = (Long)element[1];
            if(!operatorMap.containsKey(operatorId)){
                operatorMap.put(operatorId,new HashSet());
            }
            
            Set<Object> entryIdsOfOp = (Set)operatorMap.get(operatorId);
            entryIdsOfOp.add(entryId);
        }
        
        Set<Long> opIds = new HashSet<Long>();
        for (Iterator iterator = operatorMap.keySet().iterator(); iterator.hasNext();) {
            Long opId = (Long)iterator.next();
            Set entryIdsOfOp = (Set)operatorMap.get(opId);
            if(this.includeAllEntryId(entryIds, entryIdsOfOp)){
                opIds.add(opId);
            }
        }
        
        hql = new StringBuffer("from Operator op where op.operatorId in ");
        return this.queryIn(hql.toString(), (Long[])opIds.toArray(new Long[0]));
        
        /*
        select op.operator_id,rdr.entry_id
        from sm.sm_operator op,
             sm.Sm_Rel_Oper_Role opRole,
             sm.sm_role rl,
             sm.sm_rel_role_data_res rdr
        where 
             op.operator_id = opRole.Operator_Id
             and opRole.Role_Id = rl.role_id
             and rl.role_id = rdr.role_id
             and rl.role_type = 2
         */
        /*
        StringBuffer hql = new StringBuffer("select op.operatorId,rdr.entryId");
        hql.append(" from Operator op,RelOperRole opRole,Role r");
        */
    }
    
    private boolean includeAllEntryId(Long[] entryIds,Set entryIdOfOps){
        for (int i = 0; i < entryIds.length; i++) {
            if(!entryIdOfOps.contains(entryIds[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 根据指定条件查询操作员。
     * 
     * @param orgId
     *                操作员所属组织ID，为null是查询所有组织下的操作员。
     * @param name
     *                操作员名称，不指定时为null。
     * @param loginName
     *                操作员登陆名，不指定时为null。
     * @return 操作员实体数组
     */
    public List findOperators(Long orgId, String name, String loginName) {
        StringBuffer hql = new StringBuffer("from Operator op where 1=1");

        List<Object> parameters = new ArrayList<Object>();

        if (orgId != null) {
            hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=?");
            parameters.add(orgId);
        }

        if (name != null) {
            hql.append(" and op.").append(Operator.PROP_OP_NAME).append(
                    " like ?");
            parameters.add(name);
        }

        if (loginName != null) {
            hql.append(" and op.").append(Operator.PROP_LOGIN_NAME).append(
                    " like %");
            parameters.add(loginName);
        }

        return this.query(hql.toString(), (Object[]) parameters
                .toArray(new Object[0]));
    }

    /**
     * 根据ssoCode查询操作员信息
     * 
     * @param ssoCodes
     *                操作员在SSO系统中的代码
     * @return 操作员实体列表
     */
    public List findOperatorsBySSOCodes(String[] ssoCodes) {
        StringBuffer hql = new StringBuffer("from Operator op");
        hql.append(" where op.").append(Operator.PROP_SSO_CODE).append(" in");
        return this.queryIn(hql.toString(), ssoCodes);
    }

    /**
     * 查询操作员组中操作员。
     * 
     * @param groupId
     *                操作员组ID。
     * @return
     */
    public List findOperatorsOfGroup(Long groupId) {
        StringBuffer hql = new StringBuffer("select op");
        hql.append(" from Operator op,RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_GROUP_ID).append(
                "=?");
        hql.append(" and op.").append(Operator.PROP_OPERATOR_ID)
                .append("=rog.").append(RelOperGroup.PROP_OPERATOR_ID);

        return this.query(hql.toString(), new Object[] { groupId });
    }

    /**
     * 查询拥有指定角色的操作员。
     * 
     * @param roleId
     *                角色ID。
     * @return
     */
    public List findOperatorOfRole(Long roleId) {
        StringBuffer hql = new StringBuffer();
        //hql.append(" select distinct op from Operator op,RelOperRole ror,Role role ");
        hql.append(" select distinct op,org from Operator op,RelOperRole ror,Role role,Organization org ");
        hql.append(" where op.operatorId = ror.operatorId ");
        hql.append(" and ror.roleId = role.roleId ");
        hql.append(" and role.roleId = ?");
        hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        return this.query(hql.toString(), new Object[] { roleId });
    }
    /**
     * 
     * @param operatorId
     * @return
     */
	public List findOperatorsInAccssOrgs(String loginName,String name,Long operatorId,Long loginOrgId) {
                List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select op from Operator op ,Organization org");
//		hql.append(" where op.").append(Operator.PROP_ORG_ID);
//		hql.append(" in (");
//		hql.append("select roo.").append(RelOperOrg.PROP_ORG_ID);
//		hql.append(" from RelOperOrg roo where roo.") ;
//		hql.append(RelOperOrg.PROP_OPERATOR_ID).append(" = ? )");
                hql.append(" where op.").append(Operator.PROP_OPERATOR_ID).append(" != ?");
                params.add(operatorId);
                hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
                hql.append(" and org.").append(Organization.PROP_ORG_PATH).append(" like '%#' || ? || '#%'");
                params.add(loginOrgId);
                if(loginName != null && loginName.length() > 0){
                    hql.append(" and op.").append(Operator.PROP_LOGIN_NAME).append(" like ?");
                    params.add("%" + loginName + "%");
                }
                
                if(name != null && name.length() > 0){
                    hql.append(" and op.").append(Operator.PROP_OP_NAME).append(" like ?");
                    params.add("%" + name + "%");
                }
		//return this.query(hql.toString(),new Object[]{operatorId , loginOrgId});
                return this.query(hql.toString(),params.toArray(new Object[0]));
		
	}
    
	/**
	 * 查询操作员
	 * @param opId  当前操作员
	 * @param orgId  组织ID 可以为null
	 * @param loginName  操作员登陆名 可以为null
	 * @param name   操作员姓名 可以为null
	 * @return
	 */
	public List searchOperator(Long opId , Long orgId, String loginName, String name) {
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		hql.append(" select op from Operator op ");
		hql.append(" where op.").append(Operator.PROP_ORG_ID);
		if(orgId != null && orgId.longValue() !=0){
			hql.append(" = ?");	
			params.add(orgId);
		}else{
			hql.append(" in (");
			hql.append("select roo.").append(RelOperOrg.PROP_ORG_ID);
			hql.append(" from RelOperOrg roo where roo.") ;
			hql.append(RelOperOrg.PROP_OPERATOR_ID).append(" = ? )");
			params.add(opId);
		}
		if(StringUtils.isNotEmpty(loginName)){
			hql.append(" and op.").append(Operator.PROP_LOGIN_NAME);
			hql.append(" like ?");
			params.add("%" + loginName + "%");
		}
        if(StringUtils.isNotEmpty(name)){
        	hql.append(" and op.").append(Operator.PROP_OP_NAME);
			hql.append(" like ?");
			params.add("%" + name + "%");
		}
		return this.query(hql.toString(),params.toArray());
	}

    public List searchOperator(Long orgId, String loginName, String name,boolean includeAll) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer();
        //hql.append("select new com.huashu.boss.sm.dto.OperatorDTO(op,org)");
        hql.append("from Operator op,Organization org");
        hql.append(" where 1=1 ");
        if(StringUtils.isNotEmpty(loginName)){
            hql.append(" and op.").append(Operator.PROP_LOGIN_NAME);
            hql.append(" like ?");
            params.add("%" + loginName + "%");
        }
        if(StringUtils.isNotEmpty(name)){
            hql.append(" and op.").append(Operator.PROP_OP_NAME);
            hql.append(" like ?");
            params.add("%" + name + "%");
        }
        
        if(!includeAll){
            hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
            hql.append(" and org.").append(Organization.PROP_ORG_ID).append("=?");
            params.add(orgId);
        }else{
            hql.append(" and op.").append(Operator.PROP_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
            hql.append(" and org.").append(Organization.PROP_ORG_PATH).append(" like '%#' || ? || '#%'");
            params.add(orgId);
        }
       
        return this.query(hql.toString(),params.toArray(new Object[0]));
    }

    public List searchOperator(String orgIdStr) {
        StringBuffer hql = new StringBuffer();
        hql.append(" select op from Operator op,Organization org");
        hql.append(" where op.").append(Operator.PROP_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append(" in (").append(orgIdStr).append(")");
        return this.query(hql.toString());
    }
    
}