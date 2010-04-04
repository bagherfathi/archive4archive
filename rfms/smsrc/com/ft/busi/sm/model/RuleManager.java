package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;

/**
 * 规则管理接口
 * 
 * @ejb.client jndiName="ejb/sm/ruleManager" id="ruleManager"
 *             homepackage="com.ft.busi.sm.ejb"
 * 
 */
public interface RuleManager extends EntityManager {

    /**
     * 保存规则种类数据
     * 
     * @param ruleCategory
     *                规则种类实体数据
     */
    public void saveCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * 更新规则种类数据
     * 
     * @param ruleCategory
     *                规则种类实体数据
     */
    public void updateCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * 删除规则种类数据
     * 
     * @param ruleCategory
     *                删除规则种类数据
     */
    public void deleteCategory(RuleCategoryDTO ruleCategory) throws Exception;

    /**
     * 通过Id查找对应的规则种类
     * 
     * @param categoryId
     *                规则种类id
     * @return 规则种类实体数据
     */
    public RuleCategoryDTO findCategoryById(Long categoryId) throws Exception;

    /**
     * 通过id查找对应的规则
     * 
     * @param ruleId
     *                规则id
     * @return 规则实体数据
     */
    public RuleInfoDTO findRuleById(Long ruleId) throws Exception;

    /**
     * 更新规则实体数据
     * 
     * @param rule
     *                规则实体数据
     */
    public void updateRule(RuleInfoDTO rule,AppRequest appRequest) throws Exception;

    /**
     * 删除规则实体数据
     * 
     * @param rule
     *                规则实体数据
     */
    public void deleteRule(RuleInfoDTO rule) throws Exception;

    /**
     * 通过规则文件Id获得规则文件信息
     * 
     * @param fileId
     *                规则文件Id
     * @return 规则文件实体数据
     */
    public RuleFileDTO findRuleFileByFileId(Long fileId) throws Exception;

    /**
     * 查询规则信息
     * 
     * @param categoryId
     *                规则类别ID
     * @param ruleName
     *                规则名称
     * @return
     */
    public List findRule(Long categoryId, String ruleName) throws Exception;

    /**
     * 获得规则中规则文件的最高版本
     * 
     * @param ruleId
     *                规则ID
     * @return
     */
    public long findMaxVersionByRuleId(Long ruleId) throws Exception;

    /**
     * 上传规则文件。
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @param isPublish
     * @throws Exception
     */
    public void addRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest,boolean isPublish) throws Exception;
    
    /**
     * 发布规则文件。
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @throws Exception
     */
    
    public void publishRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest) throws Exception;
    
    /**
     * 删除规则文件。
     * @param rule
     * @param ruleFile
     * @param appRequest
     * @throws Exception
     */
    public void deleteRuleFile(RuleInfoDTO rule,RuleFileDTO ruleFile,AppRequest appRequest) throws Exception;

    /**
     * 根据指定的规则代码获取其已发布的规则文件。
     * 
     * @param ruleCode
     * @return
     */
    public RuleFileDTO findPublishedFileOfRule(String ruleCode)
            throws Exception;

    /**
     * 获取所有的规则信息。
     * 
     * @return Rule实体列表。
     */
    public List findAllRules() throws Exception;

    /**
     * 获取所有的规则分类。
     * 
     * @return Rule实体列表。
     */
    public List findAllRuleCategory() throws Exception;

    /**
     * 根据规则Id获取规则文件
     * 
     * @param ruleId
     * @return RuleFileDTO实体列表
     */
    public List findRuleFileByRuleId(Long ruleId) throws Exception;

    /**
     * 创建规则。
     * @param rule
     * @param categoryId
     * @param currentUser
     * @param appRequest
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,AppRequest appRequest) throws Exception;

    /**
     * 创建规则。
     * @param rule
     * @param categoryId
     * @param currentUser
     * @param ruleFile
     * @param appRequest
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,
            OperatorDTO currentUser, RuleFileDTO ruleFile, AppRequest appRequest) throws Exception;
}
