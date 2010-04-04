
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RuleInfo;

/**
 * RuleInfo ʵ�����ݷ�����
 * 
 * @spring.bean id="RuleInfoDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleInfoDao extends BaseDao {

    /**
     * ���캯��
     */
    public RuleInfoDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RuleInfo.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RuleInfo getById(Serializable key) {
        return (RuleInfo) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RuleInfo loadById(Serializable key) {
        return (RuleInfo) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ���ݹ������͹������Ʋ�ѯ����
     * 
     * @param categoryId
     * @param ruleName
     * @return
     */
    public List findRule(Long categoryId, String ruleName) {
        List rules = new ArrayList();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from RuleInfo r ");
        if ((null == categoryId || categoryId.longValue() == 0)
                && (null == ruleName || ruleName.length() == 0)) {
            rules = this.query(hql.toString());
        } else {
            hql.append("where ");
            if (null != categoryId && categoryId.longValue() != 0) {
                hql.append("r.categoryId = ? ");
                params.add(categoryId);
            }
            if (null != ruleName && ruleName.length() > 0) {
                if (null != categoryId && categoryId.longValue() != 0) {
                    hql.append("and ");
                }
                hql.append("r.ruleName like ? ");
                params.add("%" + ruleName + "%");
            }
            rules = this.query(hql.toString(), params.toArray(new Object[0]));
        }

        return rules;
    }
}