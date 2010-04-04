package com.ft.common.task.corn;

/**
 * 每月运行类.
 * 
 */
public class MonthlyCronEntity extends CronEntity {
    private String[] monthlydayOrWeekPeriod;

    /** 天（1-31）. */
    private String[] monthlymonthsEnum;

    /** 月（1-12） . */
    private String monthlyrunDayTime;

    /** 具体执行时间 格式为（hh:mm:ss） . */

    public MonthlyCronEntity() {
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：第几月几日的几点几分几秒执行
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式（第几月几日的几点几分几秒执行）
        String[] s = this.getSplitCronString(cronString);

        monthlydayOrWeekPeriod = s[3].split(",");
        monthlymonthsEnum = s[4].split(",");
        monthlyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
        appearValue = "第" + s[4] + "月的" + s[3] + "日的" + s[2] + "点" + s[1] + "分"
                + s[0] + "秒执行";

        return appearValue;
    }

    public String getCronString() {
        // TODO Auto-generated method stub
        return getSecondMinuteHourStr(monthlyrunDayTime)
                + arrayToString(monthlydayOrWeekPeriod) + " "
                + arrayToString(monthlymonthsEnum) + " ?";
    }

    /**
     * @return Returns the monthlydayOrWeekPeriod.
     */
    public String[] getMonthlydayOrWeekPeriod() {
        return monthlydayOrWeekPeriod;
    }

    /**
     * @param monthlydayOrWeekPeriod
     *                The monthlydayOrWeekPeriod to set.
     */
    public void setMonthlydayOrWeekPeriod(String[] monthlydayOrWeekPeriod) {
        this.monthlydayOrWeekPeriod = monthlydayOrWeekPeriod;
    }

    /**
     * @return Returns the monthlymonthsEnum.
     */
    public String[] getMonthlymonthsEnum() {
        return monthlymonthsEnum;
    }

    /**
     * @param monthlymonthsEnum
     *                The monthlymonthsEnum to set.
     */
    public void setMonthlymonthsEnum(String[] monthlymonthsEnum) {
        this.monthlymonthsEnum = monthlymonthsEnum;
    }

    /**
     * @return Returns the monthlyrunDayTime.
     */
    public String getMonthlyrunDayTime() {
        return monthlyrunDayTime;
    }

    /**
     * @param monthlyrunDayTime
     *                The monthlyrunDayTime to set.
     */
    public void setMonthlyrunDayTime(String monthlyrunDayTime) {
        this.monthlyrunDayTime = monthlyrunDayTime;
    }
}
