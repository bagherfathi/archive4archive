package com.ft.common.task.corn;

/**
 * ÿ�����е�ʵ����.
 * 
 * 
 */
public class WeeklyCronEntity extends CronEntity {
    private String[] weeklydayOrWeekPeriod;

    /** ���ڣ�����һ�����ڶ����������������ġ������塢�������������գ�. */
    private String weeklyrunDayTime;

    /** ����ִ��ʱ�� ��ʽΪ��hh:mm:ss��. */

    public WeeklyCronEntity() {
        super();
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺ÿ���ڼ��ļ��㼸�ּ���ִ��
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ��ÿ���ڼ��ļ��㼸�ּ���ִ�У�

        String[] s = this.getSplitCronString(cronString);
        weeklydayOrWeekPeriod = s[5].split(",");
        weeklyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
        appearValue = "ÿ" + "����" + s[5] + "��" + s[2] + "��" + s[1] + "��" + s[0]
                + "��" + "ִ��";

        return appearValue;
    }

    /**
     * ��ȡCronString.
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
