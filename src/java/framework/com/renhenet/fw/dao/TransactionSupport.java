package com.renhenet.fw.dao;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionInterceptor;

public class TransactionSupport {
    public static void rollback() {
        TransactionStatus status = TransactionInterceptor
                .currentTransactionStatus();
        if (null == status) {
            throw new IllegalStateException(
                    "Rollback must be called in transaction context.");
        }
        status.setRollbackOnly();
    }
}
