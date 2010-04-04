package com.ft.common.task.corn;

/**
 * ÿ��������.
 * 
 */
public class MonthlyCronEntity extends CronEntity {
    private String[] monthlydayOrWeekPeriod;

    /** �죨1-31��. */
    private String[] monthlymonthsEnum;

    /** �£�1-12�� . */
    private String monthlyrunDayTime;

    /** ����ִ��ʱ�� ��ʽΪ��hh:mm:ss�� . */

    public MonthlyCronEntity() {
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺�ڼ��¼��յļ��㼸�ּ���ִ��
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ���ڼ��¼��յļ��㼸�ּ���ִ�У�
        String[] s = this.getSplitCronString(cronString);

        monthlydayOrWeekPeriod = s[3].split(",");
        monthlymonthsEnum = s[4].split(",");
        monthlyrunDayTime = s[2] + ":" + s[1] + ":" + s[0];
        appearValue = "��" + s[4] + "�µ�" + s[3] + "�յ�" + s[2] + "��" + s[1] + "��"
                + s[0] + "��ִ��";

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
