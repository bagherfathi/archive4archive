package com.ft.busi.sm.service;

import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;

/**
 * ���ⲿ�ṩ��ȡö�����ݵĽӿڡ�
 * 
 * @version 1.0
 */
public interface EnumService {
    /**
     * ���������루��������ȡ����������е�ö������ʵ�塣
     * 
     * @param categoryCode
     *                �����루��������
     * @return EnumDataʵ���б�
     */
    public EnumDTO[] findEnumsByCategory(String categoryCode);

    /**
     * ����ö�����ݴ����ȡ��ö������������������Ŀ��
     * 
     * @param categoryCode
     *                ö���������������루��������
     * @param enumCode
     *                ö�����ݴ��루�ֶ�������
     * @return EnumDataEntryʵ���б�
     */
    public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode);

    /**
     * ����ö�����ݴ����ȡ��ö��������������Ŀ�������ݸ����Ĺ���ֵ���й��ˡ�
     * 
     * @param categoryCode
     *                ö���������������루��������
     * @param enumCode
     *                ö�����ݴ��루�ֶ�������
     * @param linkValue
     *                ö��������Ŀ����ֵ��
     * @return EnumDataEntryʵ���б�
     */
    public EnumEntryDTO[] findEnumEntriesByEnum(String categoryCode,
            String enumCode, String linkValue);
    
    /**
     * ��ѯָ��enum entry��ֵ����
     * @param categoryCode    ������
     * @param enumCode        ���ݴ���
     * @param value           ����ָ
     * @return
     */
    public String findEnumEntrie(String categoryCode,
            String enumCode,String value);
}
