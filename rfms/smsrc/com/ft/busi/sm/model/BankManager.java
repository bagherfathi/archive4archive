package com.ft.busi.sm.model;

import java.io.Serializable;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.BankErrorDTO;
import com.ft.sm.dto.RelBankOrgDTO;

/**
 * ������Ϣ���������մ���������ӿڡ�
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/bankManager" id="bankManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface BankManager extends EntityManager {
    /**
     * �½�������Ϣ��
     * 
     * @param bank
     *                ����ʵ�塣
     * @return
     */
    public Long saveBank(BankDTO bank,AppRequest appRequest) throws Exception;
    
    /**
     * ����������Ϣ
     * @param bank
     * @throws Exception
     */
    public void updateBank(BankDTO bank,AppRequest appRequest) throws Exception;

    /**
     * ���ݸ���������ϢID����������Ϣ��
     * 
     * @param bankIds
     *                ����ID���顣
     */
    public void disableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception;

    /**
     * ����ָ����֯��������Ϣ��
     * 
     * @param orgId
     *                ��֯����ID��
     * @return
     */
    public List findBanksByOrg(Long orgId) throws Exception;

    /**
     * �½��������մ�����롣
     * 
     * @param bankError
     *                �������մ������ʵ�塣
     * @return
     */
    public Long saveBankError(BankErrorDTO bankError,AppRequest appRequest) throws Exception;
    
    public void updateBankError(BankErrorDTO bankError,AppRequest appRequest) throws Exception;

    /**
     * ���ݸ������������մ������ID�����������մ�����롣
     * 
     * @param bankErrors
     *                �������մ���ID���顣
     */
    public void disableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception;

    /**
     * ���ݸ������д�������������մ�����롣
     * 
     * @param bankId 
     *                ���б�ʶ��
     * @return
     */
    public List findBankErrorsByBank(Long bankId) throws Exception;

    /**
     * ��ѯϵͳ�������������մ�����롣
     * 
     * @return
     */
    public List findAllBankErrors() throws Exception;

    /**
     * ����������Ϣ��
     * 
     * @param bankIds
     *                ������ϢID���顣
     */
    public void enableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception;

    /**
     * �����������մ�����롣
     * 
     * @param bankErrorIds
     *                �������մ������ID���顣
     */
    public void enableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception;

    /**
     * ��ȡ��������״̬��������Ϣ��
     * 
     * @return
     */
    public List findAllBanks() throws Exception;

    /**
     * ����״̬��ѯ������Ϣ��
     * 
     * @param status
     *                ������Ϣ״̬��
     * @return
     */
    public List findBanksByStatus(Long status) throws Exception;

    /**
     * ��ȡָ��������Ϣ��
     * 
     * @param bankId
     *                ������ϢID��
     * @return
     */
    public BankDTO findBankById(Long bankId) throws Exception;
    
    /**
     * ��ѯ���а���֯��Ϣ��
     * @param bankId
     * @return
     * @throws Exception
     */
    public List findBankOrgsOfBank(Long bankId) throws Exception;
    
    /**
     * �������а���֯��Ϣ��
     * @param relBankOrg
     * @return
     * @throws Exception
     */
    public Long saveBankOrg(RelBankOrgDTO relBankOrg,AppRequest appRequest) throws Exception;
    
    /**
     * �������а���֯��Ϣ��
     * @param relBankOrg
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updateBankOrg(RelBankOrgDTO relBankOrg,AppRequest appRequest) throws Exception;
    
    /**
     * ɾ�����а���֯��Ϣ��
     * @param relBankOrgIds
     * @param appRequest
     * @throws Exception
     */
    public void deleteBankOrg(Serializable[] relBankOrgIds,AppRequest appRequest) throws Exception;
}
