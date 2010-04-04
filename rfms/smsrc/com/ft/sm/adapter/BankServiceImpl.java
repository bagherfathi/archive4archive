package com.ft.sm.adapter;

import java.util.List;

import com.ft.busi.sm.model.BankManager;
import com.ft.busi.sm.service.BankService;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.RelBankOrgDTO;

/**
 * 银行数据获取接口实现。
 */
public class BankServiceImpl implements BankService {
    private BankManager bankManager;

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.BankService#getAllBanks()
     */
    @SuppressWarnings("unchecked")
	public BankDTO[] findAllBanks() {
        try {
            List result = this.bankManager.findBanksByStatus(new Long(
                    BankDTO.STATUS_NORMAL));
            return (BankDTO[]) result.toArray(new BankDTO[0]);
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.BankService#findBankById(java.lang.Long)
     */
    public BankDTO findBankById(Long bankId) {
        try {
            return this.bankManager.findBankById(bankId);
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.service.BankService#findBanksOfOrg(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public RelBankOrgDTO[] findBanksOfOrg(Long orgId) {
        try {
            List result = this.bankManager.findBanksByOrg(orgId);
            return (RelBankOrgDTO[]) result.toArray(new RelBankOrgDTO[0]);
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * @param bankManager
     *                the bankManager to set
     */
    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }
}
