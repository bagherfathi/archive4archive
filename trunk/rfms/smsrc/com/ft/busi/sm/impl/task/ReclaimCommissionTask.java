package com.ft.busi.sm.impl.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.CommissionManager;
import com.ft.busi.sm.task.TaskJob;
import com.ft.busi.sm.task.TaskJobContext;
import com.ft.busi.sm.task.TaskJobException;
import com.ft.spring.web.SpringContextUtils;

/**
 * 用于自动回收已过期的委托权限。
 * 
 * 
 */
public class ReclaimCommissionTask implements TaskJob{
    private static final Log logger = LogFactory.getLog(ReclaimCommissionTask.class);
    
    public void execute(TaskJobContext context) throws TaskJobException {
        CommissionManager commissionManager = (CommissionManager)SpringContextUtils.getBean("commissionManager");
        if(commissionManager != null){
            try {
                commissionManager.removeExpiredCommission();
            } catch (Exception e) {
                logger.error("Execute reclaim commission task failed",e);
            }
        }else{
            logger.error("Execute reclaim commission task failed,not found commissionManager bean in spring config.");
        }
    }
}
