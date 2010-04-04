package com.ft.common.task.corn;

/**
 * 每周运行的实体类.
 * 
 * 
 */
public class WeeklyCronEntity extends CronEntity {
    private String[] weeklydayOrWeekPeriod;

    /** 星期（星期一、星期二、星期三、星期四、星期五、星期六、星期日）. */
    private String weeklyrunDayTime;

    /** 具体执行时间 格式为（hh:mm:ss）. */

    public WeeklyCronEntity() {
        super();
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：每星期几的几点几分几秒执行
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式（每星期几的几点几分几秒执行）

        String[] s = this.getSplitCronString(cronString);
        weeklydayOrWeekPeriod = s[5].split(",");
        weeklyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
        appearValue = "每" + "星期" + s[5] + "的" + s[2] + "点" + s[1] + "分" + s[0]
                + "秒" + "执行";

        return appearValue;
    }

    /**
     * 获取CronString.
     */
    public String getCronString() {
        return getSecondMinuteHourStr(weeklyrunDayTime) + "? * "
                + arrayToString(weeklydayOrWeekPeriod);
    }

    /**
     * @return Returns the weeklydayOrWeekPeriod.
     */
    public String[] getWeeklydayOrWeekPeriod() {
        return weeklydayOrWeekPeriod;
    }

    /**
     * @param weeklydayOrWeekPeriod
     *                The weeklydayOrWeekPeriod to set.
     */
    public void setWeeklydayOrWeekPeriod(String[] weeklydayOrWeekPeriod) {
        this.weeklydayOrWeekPeriod = weeklydayOrWeekPeriod;
    }

    /**
     * @return Returns the weeklyrunDayTime.
     */
    public String getWeeklyrunDayTime() {
        return weeklyrunDayTime;
    }

    /**
     * @param weeklyrunDayTime
     *                The weeklyrunDayTime to set.
     */
    public void setWeeklyrunDayTime(String weeklyrunDayTime) {
        this.weeklyrunDayTime = weeklyrunDayTime;
    }
}
