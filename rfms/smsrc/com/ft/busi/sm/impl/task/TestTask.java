package com.ft.busi.sm.impl.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.CommissionManager;
import com.ft.busi.sm.task.TaskJob;
import com.ft.busi.sm.task.TaskJobContext;
import com.ft.busi.sm.task.TaskJobException;
import com.ft.spring.web.SpringContextUtils;

public class TestTask implements TaskJob{
    private static final Log logger = LogFactory.getLog(TestTask.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(TaskJobContext context) throws TaskJobException {
        CommissionManager commissionManager = (CommissionManager)SpringContextUtils.getBean("commissionManager");
        if(commissionManager != null){
            try {
                commissionManager.removeExpiredCommission();
            } catch (Exception e) {
                logger.error(e);
            }
        }else{
            logger.info("*************Not found commissionManager bean.");
        }
        String name = (String) context.getParameter("name");
        logger.info(name + "测试--------Hello 类的execute方法-----------------");
    }
}
