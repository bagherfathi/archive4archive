package com.ft.hibernate.datasource;

import java.util.List;


/**
 * ��ȡ����ֵ
 *
 */
public interface ParamsValueResolver {
    public abstract List getParamValues(List Params);
}
