package com.ft.busi.sm.service;

import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.RelBankOrgDTO;

/**
 * 银行数据获取接口。
 * 
 * @version 1.0
 */
public interface BankService {
    /**
     * 获取所有正常状态的银行信息。
     * 
     * @return
     */
    public BankDTO[] findAllBanks();

    /**
     * 获取属于指定组织的银行信息。
     * 
     * @param orgId
     *                组织机构ID。
     * @return
     */
    public RelBankOrgDTO[] findBanksOfOrg(Long orgId);

    /**
     * 获取指定银行信息。
     * 
     * @param bankId
     *                银行信息ID。
     * @return
     */
    public BankDTO findBankById(Long bankId);
}
