package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;

/**
 * �������ӿ�
 * 
 * @ejb.client jndiName="ejb/sm/ruleManager" id="ruleManager"
 *             homepackage="com.ft.busi.sm.ejb"
 * 
 */
public interface RuleManager extends EntityManager {

    /**
     * ���������������
     * 
     * @param ruleCategory
     *                ��������ʵ������
     */
    public void saveCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * ���¹�����������
     * 
     * @param ruleCategory
     *                ��������ʵ������
     */
    public void updateCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * ɾ��������������
     * 
     * @param ruleCategory
     *                ɾ��������������
     */
    public void deleteCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * ͨ��Id���Ҷ�Ӧ�Ĺ�������
     * 
     * @param categoryId
     *                ��������id
     * @return ��������ʵ������
     */
    public RuleCategoryDTO findCategoryById(Long categoryId) throws Exception;

    /**
     * ͨ��id���Ҷ�Ӧ�Ĺ���
     * 
     * @param ruleId
     *                ����id
     * @return ����ʵ������
     */
    public RuleInfoDTO findRuleById(Long ruleId) throws Exception;

    /**
     * ���¹���ʵ������
     * 
     * @param rule
     *                ����ʵ������
     */
    public void updateRule(RuleInfoDTO rule,AppRequest appRequest) throws Exception;

    /**
     * ɾ������ʵ������
     * 
     * @param rule
     *                ����ʵ������
     */
    public void deleteRule(RuleInfoDTO rule) throws Exception;

    /**
     * ͨ�������ļ�Id��ù����ļ���Ϣ
     * 
     * @param fileId
     *                �����ļ�Id
     * @return �����ļ�ʵ������
     */
    public RuleFileDTO findRuleFileByFileId(Long fileId) throws Exception;

    /**
     * ��ѯ������Ϣ
     * 
     * @param categoryId
     *                �������ID
     * @param ruleName
     *                ��������
     * @return
     */
    public List findRule(Long categoryId, String ruleName) throws Exception;

    /**
     * ��ù����й����ļ�����߰汾
     * 
     * @param ruleId
     *                ����ID
     * @return
     */
    public long findMaxVersionByRuleId(Long ruleId) throws Exception;

    /**
     * �ϴ������ļ���
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @param isPublish
     * @throws Exception
     */
    public void addRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest,boolean isPublish) throws Exception;
    
    /**
     * ���������ļ���
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @throws Exception
     */
    
    public void publishRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest) throws Exception;
    
    /**
     * ɾ�������ļ���
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @throws Exception
     */
    public void deleteRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest) throws Exception;

    /**
     * ����ָ���Ĺ�������ȡ���ѷ����Ĺ����ļ���
     * 
     * @param ruleCode
     * @return
     */
    public RuleFileDTO findPublishedFileOfRule(String ruleCode)
            throws Exception;

    /**
     * ��ȡ���еĹ�����Ϣ��
     * 
     * @return Ruleʵ���б�
     */
    public List findAllRules() throws Exception;

    /**
     * ��ȡ���еĹ�����ࡣ
     * 
     * @return Ruleʵ���б�
     */
    public List findAllRuleCategory() throws Exception;

    /**
     * ���ݹ���Id��ȡ�����ļ�
     * 
     * @param ruleId
     * @return RuleFileDTOʵ���б�
     */
    public List findRuleFileByRuleId(Long ruleId) throws Exception;

    /**
     * ��������
     * @param rule
     * @param categoryId
     * @param currentUser
     * @param appRequest
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,AppRequest appRequest) throws Exception;

    /**
     * ��������
     * @param rule
     * @param categoryId
     * @param currentUser
     * @param ruleFile
     * @param appRequest
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,
            OperatorDTO currentUser, RuleFileDTO ruleFile, AppRequest appRequest) throws Exception;
}
