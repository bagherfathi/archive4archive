package com.ft.busi.sm.impl.task;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 任务调度的工厂类.
 * 
 * 
 */
public class MonitoredSchedulerFactoryBean extends SchedulerFactoryBean {
    private SchedulerMonitor schedulerMonitor;

    /**
     * @return Returns the schedulerMonitor.
     */
    public SchedulerMonitor getSchedulerMonitor() {
        return schedulerMonitor;
    }

    /**
     * @param schedulerMonitor
     *                The schedulerMonitor to set.
     */
    public void setSchedulerMonitor(SchedulerMonitor schedulerMonitor) {
        this.schedulerMonitor = schedulerMonitor;
    }

    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#createScheduler(org.quartz.SchedulerFactory,
     *      java.lang.String)
     */
    public Scheduler createScheduler(SchedulerFactory schedulerFactory,
            String schedulerName) throws SchedulerException {
        Scheduler result = super.createScheduler(schedulerFactory,
                schedulerName);

        this.schedulerMonitor = new SchedulerMonitor();
        this.schedulerMonitor.init(result);

        return result;
    }
}
