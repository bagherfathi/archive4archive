package com.ft.hibernate.datasource;

import java.util.List;


/**
 * 获取参数值
 *
 */
public interface ParamsValueResolver {
    public abstract List getParamValues(List Params);
}
