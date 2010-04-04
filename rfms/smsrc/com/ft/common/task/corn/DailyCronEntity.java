package com.ft.common.task.corn;

/**
 * 每日运行类
 * 
 */
public class DailyCronEntity extends CronEntity {
    private int dailydayOrWeekPeriod;

    /** 每隔几天运行. */
    private String dailyrunDayTime;

    /** 具体执行时间 格式为（hh:mm:ss）. */

    public DailyCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：每隔几天的几点几分几秒执行
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式（每隔几天的几点几分几秒执行）

        if (cronString == null) {
            cronString = DEFAULT_CRON_STRING;
        }

        String[] s = cronString.split(" ");

        if (s.length < 5) {
            cronString = DEFAULT_CRON_STRING;
            s = cronString.split(" ");
        }

        if (s[3].length() < 2) {
            dailydayOrWeekPeriod = 0;
            dailyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
            appearValue = "每天的" + s[2] + "点" + s[1] + "分" + s[0] + "秒执行";
        } else {
            dailydayOrWeekPeriod = Integer.parseInt(s[3].substring(2));
            dailyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
            appearValue = "每隔" + s[3].substring(2) + "天的" + s[2] + "点" + s[1]
                    + "分" + s[0] + "秒执行";
        }

        return appearValue;
    }

    /**
     * 得到运行规则.
     */
    public String getCronString() {
        // TODO Auto-generated method stub
        if (dailydayOrWeekPeriod == 0) {
            return getSecondMinuteHourStr(dailyrunDayTime) + "* * ?";
        }

        return getSecondMinuteHourStr(dailyrunDayTime) + "*/"
                + dailydayOrWeekPeriod + " * ?";
    }

    /**
     * @return Returns the dailydayOrWeekPeriod.
     */
    public int getDailydayOrWeekPeriod() {
        return dailydayOrWeekPeriod;
    }

    /**
     * @param dailydayOrWeekPeriod
     *                The dailydayOrWeekPeriod to set.
     */
    public void setDailydayOrWeekPeriod(int dailydayOrWeekPeriod) {
        this.dailydayOrWeekPeriod = dailydayOrWeekPeriod;
    }

    /**
     * @return Returns the dailyrunDayTime.
     */
    public String getDailyrunDayTime() {
        return dailyrunDayTime;
    }

    /**
     * @param dailyrunDayTime
     *                The dailyrunDayTime to set.
     */
    public void setDailyrunDayTime(String dailyrunDayTime) {
        this.dailyrunDayTime = dailyrunDayTime;
    }
}
