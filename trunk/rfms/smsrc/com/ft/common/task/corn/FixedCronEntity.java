package com.ft.common.task.corn;

/**
 * 特定时间运行类.
 * 
 * 
 */
public class FixedCronEntity extends CronEntity {
    private int fixedrateperiod;

    /** 每隔几分钟 */

    public FixedCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：每隔几分种执行
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式（每隔几分种执行）

        String[] s = this.getSplitCronString(cronString);
        fixedrateperiod = Integer.parseInt(s[1].substring(2));
        appearValue = "每隔" + s[1].substring(2) + "分钟执行";

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
