package com.ft.common.task.corn;

/**
 * ֻ����һ�ε�ʵ����
 * 
 */
public class OneTimeCronEntity extends CronEntity {
    private String onetimerunDayTime;

    /** ʱ���ʽΪ��yyyy-MM-dd hh:mm:ss ��. */

    public OneTimeCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺ĳ��ĳ��ĳ�ռ��켸�ּ���ִ��
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ �磨ĳ��ĳ��ĳ�ռ��켸�ּ���ִ�У�

        String[] s = this.getSplitCronString(cronString);
        onetimerunDayTime = s[6] + "-" + s[4] + "-" + s[3] + " " + s[2] + ":"
                + s[1] + ":" + s[0];
        appearValue = "��" + s[6] + "��" + s[4] + "��" + s[3] + "��" + s[2] + "��"
                + s[1] + "��" + s[0] + "��ִ��";

        return appearValue;
    }

    public String getCronString() {
        // TODO Auto-generated method stub
        return parserDateToString(onetimerunDayTime);
    }

    /**
     * @return Returns the onetimerunDayTime.
     */
    public String getOnetimerunDayTime() {
        return onetimerunDayTime;
    }

    /**
     * @param onetimerunDayTime
     *                The onetimerunDayTime to set.
     */
    public void setOnetimerunDayTime(String onetimerunDayTime) {
        this.onetimerunDayTime = onetimerunDayTime;
    }
}
