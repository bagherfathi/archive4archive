package com.ft.common.task.corn;

/**
 * ÿ��������
 * 
 */
public class DailyCronEntity extends CronEntity {
    private int dailydayOrWeekPeriod;

    /** ÿ����������. */
    private String dailyrunDayTime;

    /** ����ִ��ʱ�� ��ʽΪ��hh:mm:ss��. */

    public DailyCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺ÿ������ļ��㼸�ּ���ִ��
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ��ÿ������ļ��㼸�ּ���ִ�У�

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
            appearValue = "ÿ���" + s[2] + "��" + s[1] + "��" + s[0] + "��ִ��";
        } else {
            dailydayOrWeekPeriod = Integer.parseInt(s[3].substring(2));
            dailyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
            appearValue = "ÿ��" + s[3].substring(2) + "���" + s[2] + "��" + s[1]
                    + "��" + s[0] + "��ִ��";
        }

        return appearValue;
    }

    /**
     * �õ����й���.
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
