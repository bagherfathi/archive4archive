
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.entity.RuleFile;
import com.ft.sm.entity.RuleInfo;

/**
 * RuleFile 实体数据访问类
 * 
 * @spring.bean id="RuleFileDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleFileDao extends BaseDao {

    /**
     * 构造函数
     */
    public RuleFileDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RuleFile.class;
    }

    /**
     * 根据ID查找对象
     */
    public RuleFileDTO getById(Serializable key) {
        StringBuffer hql = new StringBuffer("select new ");
        hql.append(" com.ft.sm.dto.RuleFileDTO( ruleFile ,ruleInfo) ");
        hql.append(" from RuleFile ruleFile,RuleInfo ruleInfo ");
        hql.append(" where ruleFile.ruleId = ruleInfo.ruleId ");
        hql.append(" and ruleFile.fileId = ?");
        return (RuleFileDTO) this.getSingle(hql.toString(),
                new Object[] { key });
    }

    /**
     * 根据ID查找对象
     */
    public RuleFile loadById(Serializable key) {
        return (RuleFile) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据规则ID删除规则文件
     * 
     * @param ruleId
     */
    public void deleteRuleFileByRuleId(Long ruleId) {
        String hql = "from RuleFile rf where rf.ruleId = ?";
        this.deleteFromQuery(hql, new Object[] { ruleId });
    }

    /**
     * 查找最大版本号
     * 
     * @param ruleId
     *                规则ID
     * @return 版本号
     */
    public long findMaxVersionByRuleId(Long ruleId) {
        StringBuffer hql = new StringBuffer(
                "select max(rf.version) from RuleFile rf ");
        hql.append("where rf.ruleId = ?");
        Long result = (Long) this.getSingle(hql.toString(),
                new Object[] { ruleId });
        
        if(result == null){
            return 0;
        }else{
            return result.longValue();
        }
    }

    /**
     * 根据规则代码查找发布的规则文件
     * 
     * @param ruleCode
     *                规则代码
     * @return RuleFile实体
     */
    public RuleFile findPublishedFileOfRule(String ruleCode) {
        StringBuffer hql = new StringBuffer("select file");
        hql.append(" from RuleFile file,RuleInfo rule");
        hql.append(" where file.").append(RuleFile.PROP_RULE_ID).append(
                "=rule.").append(RuleInfo.PROP_RULE_ID);
        hql.append(" and file.").append(RuleFile.PROP_VERSION).append("=rule.")
                .append(RuleInfo.PROP_PUBLISH_VERSION);
        hql.append(" and rule.").append(RuleInfo.PROP_RULE_CODE).append("=?");

        List result = this.query(hql.toString(), new Object[] { ruleCode });

        return result.size() > 0 ? (RuleFile) result.get(0) : null;
    }

    /**
     * 根据规则ID查找规则文件
     * 
     * @param ruleId
     *                规则ID
     * @return RuleFile实体列表
     */
    public List findRuleFileByRuleId(Long ruleId) {
        StringBuffer hql = new StringBuffer("from RuleFile rf ");
        hql.append(" where rf.ruleId = ?");
        hql.append(" order by rf.").append(RuleFile.PROP_VERSION);
        List result = this.query(hql.toString(), new Object[] { ruleId });
        return result;

    }
}