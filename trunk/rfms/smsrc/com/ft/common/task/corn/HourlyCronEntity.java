package com.ft.common.task.corn;

/**
 * ÿ����Сʱ����
 * 
 * 
 */
public class HourlyCronEntity extends CronEntity {
    private int hourlyrateperiod;

    public HourlyCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺ÿ����Сʱִ��
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ��ÿ����Сʱִ�У�

        String[] s = this.getSplitCronString(cronString);
        hourlyrateperiod = Integer.parseInt(s[2].substring(2));
        appearValue = "ÿ��" + s[2].substring(2) + "Сʱִ��";

        return appearValue;
    }

    public String getCronString() {
        // TODO Auto-generated method stub
        return "0 0 */" + hourlyrateperiod + " * * ?";
    }

    /**
     * @return Returns the hourlyrateperiod.
     */
    public int getHourlyrateperiod() {
        return hourlyrateperiod;
    }

    /**
     * @param hourlyrateperiod
     *                The hourlyrateperiod to set.
     */
    public void setHourlyrateperiod(int hourlyrateperiod) {
        this.hourlyrateperiod = hourlyrateperiod;
    }
}
