package com.ft.busi.sm.model;

import java.io.Serializable;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.BankErrorDTO;
import com.ft.sm.dto.RelBankOrgDTO;

/**
 * 银行信息和银行托收错误代码管理接口。
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/bankManager" id="bankManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface BankManager extends EntityManager {
    /**
     * 新建银行信息。
     * 
     * @param bank
     *                银行实体。
     * @return
     */
    public Long saveBank(BankDTO bank,AppRequest appRequest) throws Exception;
    
    /**
     * 更新银行信息
     * @param bank
     * @throws Exception
     */
    public void updateBank(BankDTO bank,AppRequest appRequest) throws Exception;

    /**
     * 根据给定银行信息ID禁用银行信息。
     * 
     * @param bankIds
     *                银行ID数组。
     */
    public void disableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception;

    /**
     * 查找指定组织的银行信息。
     * 
     * @param orgId
     *                组织机构ID。
     * @return
     */
    public List findBanksByOrg(Long orgId) throws Exception;

    /**
     * 新建银行托收错误代码。
     * 
     * @param bankError
     *                银行托收错误代码实体。
     * @return
     */
    public Long saveBankError(BankErrorDTO bankError,AppRequest appRequest) throws Exception;
    
    public void updateBankError(BankErrorDTO bankError,AppRequest appRequest) throws Exception;

    /**
     * 根据给定的银行托收错误代码ID禁用银行托收错误代码。
     * 
     * @param bankErrors
     *                银行托收错误ID数组。
     */
    public void disableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception;

    /**
     * 根据给定银行代码查找银行托收错误代码。
     * 
     * @param bankId 
     *                银行标识。
     * @return
     */
    public List findBankErrorsByBank(Long bankId) throws Exception;

    /**
     * 查询系统中所有银行托收错误代码。
     * 
     * @return
     */
    public List findAllBankErrors() throws Exception;

    /**
     * 启用银行信息。
     * 
     * @param bankIds
     *                银行信息ID数组。
     */
    public void enableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception;

    /**
     * 启用银行托收错误代码。
     * 
     * @param bankErrorIds
     *                银行托收错误代码ID数组。
     */
    public void enableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception;

    /**
     * 获取所有正常状态的银行信息。
     * 
     * @return
     */
    public List findAllBanks() throws Exception;

    /**
     * 根据状态查询银行信息。
     * 
     * @param status
     *                银行信息状态。
     * @return
     */
    public List findBanksByStatus(Long status) throws Exception;

    /**
     * 获取指定银行信息。
     * 
     * @param bankId
     *                银行信息ID。
     * @return
     */
    public BankDTO findBankById(Long bankId) throws Exception;
    
    /**
     * 查询银行绑定组织信息。
     * @param bankId
     * @return
     * @throws Exception
     */
    public List findBankOrgsOfBank(Long bankId) throws Exception;
    
    /**
     * 保存银行绑定组织信息。
     * @param relBankOrg
     * @return
     * @throws Exception
     */
    public Long saveBankOrg(RelBankOrgDTO relBankOrg,AppRequest appRequest) throws Exception;
    
    /**
     * 更新银行绑定组织信息。
     * @param relBankOrg
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updateBankOrg(RelBankOrgDTO relBankOrg,AppRequest appRequest) throws Exception;
    
    /**
     * 删除银行绑定组织信息。
     * @param relBankOrgIds
     * @param appRequest
     * @throws Exception
     */
    public void deleteBankOrg(Serializable[] relBankOrgIds,AppRequest appRequest) throws Exception;
}
