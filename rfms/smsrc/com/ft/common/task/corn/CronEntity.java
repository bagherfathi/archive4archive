package com.ft.common.task.corn;

/**
 * ���й�����
 * 
 */
public class CronEntity {
    /** Ĭ�ϵ����й��� . */
	
    static final String DEFAULT_CRON_STRING = "0 0 1 * * ?";

    /** Ĭ�ϵ���ʾ��ʽ. */
    static final String DEFAULT_CRON_APPEAR = "ÿ���賿1�㿪ʼִ��";

    /** ���й��������. */
    private int cronType;

    /**
     * ����cronType����������.
     * 
     * @param cronType
     */
    public CronEntity(int cronType) {
        super();
        // TODO Auto-generated constructor stub
        this.cronType = cronType;
    }

    public CronEntity() {
    }

    public String ConfApperValue(String cronString) {
        return DEFAULT_CRON_APPEAR;
    }

    /**
     * ��ȡ���й���.
     * 
     * @return Returns the DEFAULT_CRON_STRING.
     */
    public String getCronString() {
        return DEFAULT_CRON_STRING;
    }

    /**
     * ��ʱ��ģ�hh:mm:ss����ʽת��Ϊ ��ss mm hh����ʽ.
     * 
     * @param quarter
     * @return
     */
    public String getSecondMinuteHourStr(String quarter) {
        if (quarter == null) {
            return "0 * * ";
        }

        String[] s = quarter.split(":");

        if (s.length == 2) {
            return new StringBuffer("0 ").append(s[1]).append(" ").append(s[0])
                    .append(" ").toString();
        }

        if (s.length == 3) {
            return new StringBuffer(s[2]).append(" ").append(s[1]).append(" ")
                    .append(s[0]).append(" ").toString();
        }

        return "0 * * ";
    }

    /**
     * ������ת�����ַ���.
     * 
     * @param array
     * @return
     */
    public static String arrayToString(String[] array) {
        if (array == null) {
            String w = "*";

            return w;
        }

        String a = "";

        for (int i = 0; i < array.length; i++) {
            a = a + "," + array[i];
        }

        a = a.substring(1);

        return a;
    }

    /**
     * ����cronString.
     * 
     * @param cronString
     * @return
     */
    public String[] getSplitCronString(String cronString) {
        if (cronString == null) {
            cronString = DEFAULT_CRON_STRING;
        }

        cronType = getScheduleType(cronString);

        String[] s = cronString.split(" ");

        if (s.length < 5) {
            cronString = DEFAULT_CRON_STRING;
            s = cronString.split(" ");
        }

        return s;
    }

    /**
     * �������й��򣬵õ�cronType.
     * 
     * @param cronString
     * @return
     */
    public int getScheduleType(String cronString) {
        if (cronString == null) {
            return -1;
        }

        String[] crons = cronString.split(" ");

        if (crons.length == 7) {
            return 4; // by some specific time
        }

        if ((crons[3].indexOf("/") != -1)
                || ((crons[1].indexOf("/") == -1) && crons[3].equals("*"))) {
            return 1; // by daily
        }

        if (!crons[5].equals("?") && "?".equals(crons[3])) {
            return 2; // by weekly
        }

        if (!crons[4].equals("*")) {
            return 3; // by monthly
        }

        if (crons[1].indexOf("/") != -1) {
            return 5; // by some minute interval
        }

        return 0;
    }

    /**
     * ��ʱ���ʽΪ��yyyy-MM-dd hh:mm:ss �� ת��Ϊ (ss mm hh dd MM ? yyyy) ��ʽ.
     * 
     * @param date
     * @return
     */
    public String parserDateToString(String date) {
        if ((date == null) || date.equals("")) {
            return "null";
        }

        String[] a = date.substring(0, date.indexOf(" ")).split("-");
        String[] t = date.substring(date.indexOf(" ") + 1).split(":");
        String[] b = new String[3];

        if (t.length < 1) {
            b = new String[] { "00", "00", "00" };
        } else if (t.length < 2) {
            b[0] = t[0];
            b[1] = "00";
            b[2] = "00";
        } else if (t.length < 3) {
            b[0] = t[0];
            b[1] = t[1];
            b[2] = "00";
        } else {
            b = t;
        }

        return b[2] + " " + b[1] + " " + b[0] + " " + a[2] + " " + a[1] + " ? "
                + a[0];
    }

    /**
     * @return Returns the cronType.
     */
    public int getCronType() {
        return cronType;
    }

    /**
     * @param cronType
     *                The cronType to set.
     */
    public void setCronType(int cronType) {
        this.cronType = cronType;
    }
}
