package com.ft.common.task.corn;

/**
 * �ض�ʱ��������.
 * 
 * 
 */
public class FixedCronEntity extends CronEntity {
    private int fixedrateperiod;

    /** ÿ�������� */

    public FixedCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * ����cronString(���й���)��ת��Ϊ�û��ܶ�������ʽ. ���磺ÿ��������ִ��
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString ����һ�ֱ�����ʽ��ÿ��������ִ�У�

        String[] s = this.getSplitCronString(cronString);
        fixedrateperiod = Integer.parseInt(s[1].substring(2));
        appearValue = "ÿ��" + s[1].substring(2) + "����ִ��";

        return appearValue;
    }

    public String getCronString() {
        // TODO Auto-generated method stub
        return "0 */" + fixedrateperiod + " * * * ?";
    }

    /**
     * @return Returns the fixedrateperiod.
     */
    public int getFixedrateperiod() {
        return fixedrateperiod;
    }

    /**
     * @param fixedrateperiod
     *                The fixedrateperiod to set.
     */
    public void setFixedrateperiod(int fixedrateperiod) {
        this.fixedrateperiod = fixedrateperiod;
    }
}
