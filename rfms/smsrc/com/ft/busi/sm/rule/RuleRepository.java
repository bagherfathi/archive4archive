package com.ft.busi.sm.rule;

import java.io.IOException;
import java.io.Reader;

/**
 * 规则文件仓库类。
 * 
 * @version 1.0
 */
public interface RuleRepository {
    /**
     * 根据规则代码得到一个规则。
     * 
     * @param code
     *                规则代码。
     * @return Reader对象。
     * @throws IOException
     */
    public Reader getRuleReader(String code) throws IOException, RuleException;
}
