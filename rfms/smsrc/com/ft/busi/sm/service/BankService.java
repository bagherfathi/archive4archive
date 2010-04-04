package com.ft.busi.sm.service;

import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.RelBankOrgDTO;

/**
 * �������ݻ�ȡ�ӿڡ�
 * 
 * @version 1.0
 */
public interface BankService {
    /**
     * ��ȡ��������״̬��������Ϣ��
     * 
     * @return
     */
    public BankDTO[] findAllBanks();

    /**
     * ��ȡ����ָ����֯��������Ϣ��
     * 
     * @param orgId
     *                ��֯����ID��
     * @return
     */
    public RelBankOrgDTO[] findBanksOfOrg(Long orgId);

    /**
     * ��ȡָ��������Ϣ��
     * 
     * @param bankId
     *                ������ϢID��
     * @return
     */
    public BankDTO findBankById(Long bankId);
}
