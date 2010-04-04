package com.ft.busi.sm.impl.task;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 调度监控.
 * 
 * 
 */
public class SchedulerMonitor {
    private Scheduler scheduler;

    /**
     * 
     */
    public SchedulerMonitor() {
        super();

        // TODO Auto-generated constructor stub
    }

    public void init(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 获取状态正常的Trigger集合.
     * 
     * @return
     * @throws SchedulerException
     */
    public List getTriggerStatuses() throws SchedulerException {
        List<TriggerStatus> result = new ArrayList<TriggerStatus>();
        String[] groupNames = this.scheduler.getTriggerGroupNames();

        for (int i = 0; i < groupNames.length; i++) {
            String string = groupNames[i];
            String[] taskNames = this.scheduler.getTriggerNames(string);

            for (int j = 0; j < taskNames.length; j++) {
                String taskName = taskNames[j];
                int status = this.scheduler.getTriggerState(taskName, string);
                result.add(new TriggerStatus(taskName, string, status, 0));
            }
        }

        return result;
    }

    /**
     * 获取正在运行的Trigger集合.
     * 
     * @return
     * @throws SchedulerException
     */
    public List getRunTriggers() throws SchedulerException {
        List<TriggerStatus> result = new ArrayList<TriggerStatus>();
        List list;
        list = this.scheduler.getCurrentlyExecutingJobs();

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            JobExecutionContext element = (JobExecutionContext) iter.next();
            Trigger trigger = element.getTrigger();
            List trigerStatus = this.getTriggerStatuses();

            for (Iterator iterator = trigerStatus.iterator(); iterator
                    .hasNext();) {
                TriggerStatus triggers = (TriggerStatus) iterator.next();

                if (triggers.getTaskName() == trigger.getName()) {
                    triggers.setRunStatus(1);
                    result.add(triggers);
                }
            }
        }

        return result;
    }

    /**
     * @return Returns the scheduler.
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * @param scheduler
     *                The scheduler to set.
     */
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public final class TriggerStatus {
        private String taskName;

        private String groupName;

        private int status;

        private int runStatus;

        public TriggerStatus(String tName, String grpName, int status,
                int runstatus) {
            super();
            groupName = grpName;
            this.status = status;
            taskName = tName;
            this.runStatus = runstatus;
        }

        /**
         * @return Returns the runStatus.
         */
        public int getRunStatus() {
            return runStatus;
        }

        /**
         * @param runStatus
         *                The runStatus to set.
         */
        public void setRunStatus(int runStatus) {
            this.runStatus = runStatus;
        }

        /**
         * @return Returns the groupName.
         */
        public String getGroupName() {
            return groupName;
        }

        /**
         * @return Returns the status.
         */
        public int getStatus() {
            return status;
        }

        /**
         * @return Returns the taskName.
         */
        public String getTaskName() {
            return taskName;
        }
    }
}
