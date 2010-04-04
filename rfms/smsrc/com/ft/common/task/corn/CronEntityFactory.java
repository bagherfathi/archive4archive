package com.ft.common.task.corn;

/**
 * CronEntity 的工厂类.
 * 
 * 
 */
public class CronEntityFactory {
    public CronEntityFactory() {
        super();

        // TODO Auto-generated constructor stub
    }

    public static CronEntity createCoreEntity(int type) {
        switch (type) {
        case 1:
            return new DailyCronEntity();

        case 2:
            return new WeeklyCronEntity();

        case 3:
            return new MonthlyCronEntity();

        case 4:
            return new OneTimeCronEntity();

        case 5:
            return new FixedCronEntity();
            
        case 6:
            return new HourlyCronEntity();    

        default:
            return new CronEntity();
        }
    }
}
