package com.ft.common.task.corn;

/**
 * 只运行一次的实体类
 * 
 */
public class OneTimeCronEntity extends CronEntity {
    private String onetimerunDayTime;

    /** 时间格式为（yyyy-MM-dd hh:mm:ss ）. */

    public OneTimeCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：某年某月某日几天几分几秒执行
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式 如（某年某月某日几天几分几秒执行）

        String[] s = this.getSplitCronString(cronString);
        onetimerunDayTime = s[6] + "-" + s[4] + "-" + s[3] + " " + s[2] + ":"
                + s[1] + ":" + s[0];
        appearValue = "在" + s[6] + "年" + s[4] + "月" + s[3] + "日" + s[2] + "点"
                + s[1] + "分" + s[0] + "秒执行";

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
