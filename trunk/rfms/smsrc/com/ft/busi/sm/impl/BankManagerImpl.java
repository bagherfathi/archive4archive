package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.BankDao;
import com.ft.busi.sm.impl.dao.BankErrorDao;
import com.ft.busi.sm.impl.dao.RelBankOrgDao;
import com.ft.busi.sm.model.BankManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.BankErrorDTO;
import com.ft.sm.dto.RelBankOrgDTO;
import com.ft.sm.entity.Bank;
import com.ft.sm.entity.BankError;
import com.ft.sm.entity.RelBankOrg;

/**
 * 银行信息和银行托收错误代码管理实现类.
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="bankManager" parent="transactionProxyFactoryBean"
 *                  target="bankManagerImpl"
 * 
 * @spring.bean id="bankManagerImpl"
 */
public class BankManagerImpl implements BankManager,BusiBaseService {
    private BankDao bankDao;

    private BankErrorDao bankErrorDao;
    
    private RelBankOrgDao relBankOrgDao;
    
    private BusiAppService appService;

    /**
     * @spring.property ref = "BankDao"
     * 
     * @param bankDao
     *                The bankDao to set.
     */
    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    /**
     * @spring.property ref = "BankErrorDao"
     * @param bankErrorDao
     *                the bankErrorDao to set
     */
    public void setBankErrorDao(BankErrorDao bankErrorDao) {
        this.bankErrorDao = bankErrorDao;
    }
    
    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }
    
    /**
     * @spring.property ref="RelBankOrgDao"
     * @param relBankOrgDao the relBankOrgDao to set
     */
    public void setRelBankOrgDao(RelBankOrgDao relBankOrgDao) {
        this.relBankOrgDao = relBankOrgDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#deleteBankErrors(java.io.Serializable[])
     */
    public void disableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception{
        if (bankErrorIds == null) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);
        
        List result = this.bankErrorDao
                .loadByIds(BankError.class, bankErrorIds);

        for (Iterator iter = result.iterator(); iter.hasNext();) {
            BankError element = (BankError) iter.next();
            element.setStatus(BankErrorDTO.STATUS_DISABLE);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    element, this);
            this.bankErrorDao.saveOrUpdate(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#deleteBanks(java.io.Serializable[])
     */
    public void disableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception{
        if (null == bankIds) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);
        List result = this.bankDao.loadByIds(Bank.class, bankIds);
        for (Iterator iter = result.iterator(); iter.hasNext();) {
            Bank element = (Bank) iter.next();
            element.setStatus(BankDTO.STATUS_DISABLE);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    element, this);
            this.bankDao.saveOrUpdate(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#findBankErrorsByBank(java.lang.String)
     */
    public List findBankErrorsByBank(Long bankId) {
        if (null == bankId) {
            throw new IllegalArgumentException();
        }
        List result = this.bankErrorDao.findBankErrorsByBank(bankId);

        return EntityDTOConverter.converBankError2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#findBanksByOrg(java.lang.Long)
     */
    public List findBanksByOrg(Long orgId) {
        if (null == orgId) {
            throw new IllegalArgumentException();
        }

        return this.relBankOrgDao.findBanksByOrg(orgId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#saveBank(com.huashu.boss.sm.entity.basedata.Bank)
     */
    public Long saveBank(BankDTO bankDto,AppRequest appRequest) throws Exception{
        if (null == bankDto) {
            throw new IllegalArgumentException();
        }
        
        this.appService.saveApp(appRequest);
        bankDto.setAppId(appRequest.getAppId());
        
        Bank bank = (Bank) bankDto.getTarget();
        this.bankDao.saveOrUpdate(bank);

        return new Long(bank.getBankId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.BankManager#saveBankError(com.huashu.boss.sm.entity.basedata.BankError)
     */
    public Long saveBankError(BankErrorDTO bankErrorDto,AppRequest appRequest) throws Exception{
        if (null == bankErrorDto) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);
        bankErrorDto.setAppId(appRequest.getAppId());
        BankError bankError = (BankError) bankErrorDto.getTarget();
        this.bankDao.saveOrUpdate(bankError);
        return new Long(bankError.getBankErrorId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#findAllBankErrors()
     */
    public List findAllBankErrors() {
        List result = this.bankErrorDao.findAll();

        return EntityDTOConverter.converBankError2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#enableBank(java.lang.Long)
     */
    public void enableBanks(Serializable[] bankIds,AppRequest appRequest) throws Exception{
        this.appService.saveApp(appRequest);
        List result = this.bankDao.loadByIds(Bank.class, bankIds);

        for (Iterator iter = result.iterator(); iter.hasNext();) {
            Bank element = (Bank) iter.next();
            element.setStatus(BankDTO.STATUS_NORMAL);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    element, this);
            this.bankDao.saveOrUpdate(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#enableBankError(java.lang.Long)
     */
    public void enableBankErrors(Serializable[] bankErrorIds,AppRequest appRequest) throws Exception{
        if (bankErrorIds == null) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);
        List result = this.bankErrorDao
                .loadByIds(BankError.class, bankErrorIds);

        for (Iterator iter = result.iterator(); iter.hasNext();) {
            BankError element = (BankError) iter.next();
            element.setStatus(BankErrorDTO.STATUS_NORMAL);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    element, this);
            this.bankDao.saveOrUpdate(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#findAllBanks()
     */
    public List findAllBanks() {
        List result = this.bankDao.findAll();
        return EntityDTOConverter.converBank2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#findBanksByStatus(java.lang.Long)
     */
    public List findBanksByStatus(Long status) {
        return this.bankDao.findBanksByStatus(status);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.BankManager#findBankById(java.lang.Long)
     */
    public BankDTO findBankById(Long bankId) {
        Bank bank = this.bankDao.getById(bankId);
        
        return new BankDTO(bank);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(BankDTO.class)) {
            Bank bank = bankDao.getById(id);
            return new BankDTO(bank);
        }

        if (typeClass.equals(BankErrorDTO.class)) {
            BankError bankError = this.bankErrorDao.getById(id);
            return new BankErrorDTO(bankError);
        }
        
        if(typeClass.equals(RelBankOrgDTO.class)){
            return this.relBankOrgDao.findBankOrgById(id);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List result = new ArrayList();
        if (typeClass.equals(Bank.class)) {
            result = bankDao.query(hql, params, page);
            return result;
            /*
            List bankList = bankDao.query(hql, params, page);
            if (!bankList.isEmpty()) {
                Long[] orgIds = new Long[bankList.size()];
                for (int i = 0; i < bankList.size(); i++) {
                    Bank bank = (Bank) bankList.get(i);
                    orgIds[i] = new Long(bank.getOrgId());
                }
                List orgList = this.orgDao
                        .loadByIds(Organization.class, orgIds);
                for (Iterator iterator = bankList.iterator(); iterator
                        .hasNext();) {
                    Bank bank = (Bank) iterator.next();
                    for (Iterator iter = orgList.iterator(); iter.hasNext();) {
                        Organization org = (Organization) iter.next();
                        if (bank.getOrgId() == org.getOrgId()) {
                            result.add(new BankDTO(bank, org));
                            break;
                        }
                    }
                }
            }
            return result;
            */
        }

        if (typeClass.equals(BankError.class)) {
            result = this.bankErrorDao.query(hql, params, page);
            return EntityDTOConverter.converBankError2DTO(result);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        if (typeClass.equals(BankDTO.class)) {
            List result = bankDao.loadByIds(Bank.class, ids);
            return EntityDTOConverter.converBank2DTO(result);
        }

        if (typeClass.equals(BankErrorDTO.class)) {
            List result = this.bankErrorDao.loadByIds(BankError.class, ids);
            return EntityDTOConverter.converBankError2DTO(result);
        }

        return null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#updateBank(com.huashu.boss.sm.dto.BankDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateBank(BankDTO bank, AppRequest appRequest)
            throws Exception {
        this.appService.saveApp(appRequest);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                bank.getTarget(), this);
        this.bankDao.saveOrUpdate(returnObj);
    }
    
    

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#updateBankError(com.huashu.boss.sm.dto.BankErrorDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateBankError(BankErrorDTO bankError, AppRequest appRequest)
            throws Exception {
        this.appService.saveApp(appRequest);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                bankError.getTarget(), this);
        this.bankErrorDao.saveOrUpdate(returnObj);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class, java.io.Serializable)
     */
    public Object getEntityObject(Class clazz, Serializable key) {
        if(clazz.equals(Bank.class)){
            return bankDao.getById(key);
        }else if(clazz.equals(BankError.class)){
            return bankErrorDao.getById(key);
        }else if(clazz.equals(RelBankOrg.class)){
            return this.relBankOrgDao.getById(key);
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object obj) {
        this.bankDao.saveOrUpdate(obj);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#findBankOrgsOfBank(java.lang.Long)
     */
    public List findBankOrgsOfBank(Long bankId) throws Exception {
        return this.relBankOrgDao.findBankOrgsOfBank(bankId);
    }
    
    public BankDTO findBankByCode(String bankCode) throws Exception {
        return this.bankDao.findBankByCode(bankCode);
    }
    
    public BankDTO findBankByName(String bankName) throws Exception {
        return this.bankDao.findBankByName(bankName);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#saveBankOrg(com.huashu.boss.sm.dto.RelBankOrgDTO)
     */
    public Long saveBankOrg(RelBankOrgDTO relBankOrgDto,AppRequest appRequest) throws Exception {
        if (null == relBankOrgDto) {
            throw new IllegalArgumentException();
        }

        this.appService.saveApp(appRequest);
        relBankOrgDto.setAppId(appRequest.getAppId());
        RelBankOrg relBankOrg = relBankOrgDto.getRelBankOrg();
        this.relBankOrgDao.save(relBankOrg);

        return new Long(relBankOrg.getRelId());
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#updateBankOrg(com.huashu.boss.sm.dto.RelBankOrgDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateBankOrg(RelBankOrgDTO relBankOrg, AppRequest appRequest)
            throws Exception {
        this.appService.saveApp(appRequest);
        
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                relBankOrg.getTarget(), this);
        this.relBankOrgDao.saveOrUpdate(returnObj);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.BankManager#deleteBankOrg(java.io.Serializable[], com.huashu.boss.busi.dto.AppRequest)
     */
    public void deleteBankOrg(Serializable[] relBankOrgIds,
            AppRequest appRequest) throws Exception {
        this.appService.saveApp(appRequest);
        
        List result = this.relBankOrgDao.loadByIds(RelBankOrg.class,relBankOrgIds);
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            RelBankOrg delObject = (RelBankOrg) iterator.next();
            Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                    delObject, this);
            this.relBankOrgDao.saveOrUpdate(returnObj);
        }
    }
    
    public List findRelBankOrgsByStatusTypeAndOrg(Long onlineStatus,Long onlineType,Long bankOrgId) 
    	throws Exception {
    	return this.relBankOrgDao.findRelBankOrgsByStatusTypeAndOrg(onlineStatus,onlineType,bankOrgId);
    }
}
