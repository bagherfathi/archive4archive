
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.entity.RuleFile;
import com.ft.sm.entity.RuleInfo;

/**
 * RuleFile ʵ�����ݷ�����
 * 
 * @spring.bean id="RuleFileDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleFileDao extends BaseDao {

    /**
     * ���캯��
     */
    public RuleFileDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RuleFile.class;
    }

    /**
     * ����ID���Ҷ���
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
     * ����ID���Ҷ���
     */
    public RuleFile loadById(Serializable key) {
        return (RuleFile) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ���ݹ���IDɾ�������ļ�
     * 
     * @param ruleId
     */
    public void deleteRuleFileByRuleId(Long ruleId) {
        String hql = "from RuleFile rf where rf.ruleId = ?";
        this.deleteFromQuery(hql, new Object[] { ruleId });
    }

    /**
     * �������汾��
     * 
     * @param ruleId
     *                ����ID
     * @return �汾��
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
     * ���ݹ��������ҷ����Ĺ����ļ�
     * 
     * @param ruleCode
     *                �������
     * @return RuleFileʵ��
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
     * ���ݹ���ID���ҹ����ļ�
     * 
     * @param ruleId
     *                ����ID
     * @return RuleFileʵ���б�
     */
    public List findRuleFileByRuleId(Long ruleId) {
        StringBuffer hql = new StringBuffer("from RuleFile rf ");
        hql.append(" where rf.ruleId = ?");
        hql.append(" order by rf.").append(RuleFile.PROP_VERSION);
        List result = this.query(hql.toString(), new Object[] { ruleId });
        return result;

    }
}