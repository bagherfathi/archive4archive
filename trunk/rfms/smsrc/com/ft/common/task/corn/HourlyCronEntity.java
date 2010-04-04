package com.ft.common.task.corn;

/**
 * 每隔几小时运行
 * 
 * 
 */
public class HourlyCronEntity extends CronEntity {
    private int hourlyrateperiod;

    public HourlyCronEntity() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 根据cronString(运行规则)，转化为用户能读懂的形式. 例如：每隔几小时执行
     * 
     * @param cronString
     */
    public String ConfApperValue(String cronString) {
        String appearValue; // cronString 的另一种表现形式（每隔几小时执行）

        String[] s = this.getSplitCronString(cronString);
        hourlyrateperiod = Integer.parseInt(s[2].substring(2));
        appearValue = "每隔" + s[2].substring(2) + "小时执行";

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
