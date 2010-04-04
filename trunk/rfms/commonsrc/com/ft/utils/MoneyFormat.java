package com.ft.utils;

import java.text.DecimalFormat;

/**
 * 金额管理常用数据转换类
 * 
 * @version 1.0
 */
public class MoneyFormat {
    /**
     * 精确到分的金额格式
     */
    private final static String MONEY_FORMAT = "#,##0.00";

    /**
     * 精确到厘的金额格式
     */
    private final static String LI_MONEY_FORMAT = "#,##0.000";

    /**
     * 中文数字常量
     */
    private final static String[] CHINESE_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };

    /**
     * 中文数字单位常量
     */
    private final static String[] CHINESE_NUMBER_UNIT = { "分", "角", "圆", "拾",
            "佰", "仟", "万", "拾", "佰", "仟", "亿" };

    /**
     * 分到元的转换
     * 
     * @param aLong
     *            分
     * @return String 元
     */
    public static String fen2Yuan(long aLong) {
        DecimalFormat df = new DecimalFormat(MONEY_FORMAT);
        return df.format(aLong / 100.00);
    }

    /**
     * 厘到元的转换
     * 
     * @param aLong
     *            厘
     * @return String 元
     */
    public static String li2Yuan(long aLong) {
        DecimalFormat df = new DecimalFormat(LI_MONEY_FORMAT);
        return df.format(aLong / 1000.000);
    }

    /**
     * 分到元的转换
     * 
     * @param aStr
     *            分
     * @return String 元
     */
    public static String fen2Yuan(String aStr) {
        try {
            return fen2Yuan(Long.parseLong(aStr));
        } catch (Exception e) {
            return fen2Yuan(0L);
        }
    }

    /**
     * 厘到元的转换
     * 
     * @param aStr
     *            厘
     * @return String 元
     */
    public static String li2Yuan(String aStr) {
        try {
            return li2Yuan(Long.parseLong(aStr));
        } catch (Exception e) {
            return li2Yuan(0);
        }
    }

    /**
     * 元到分的转换
     * 
     * @param aStr
     *            元
     * @return String 分
     */
    public static long yuan2Fen(String aStr) {
        aStr = aStr.replaceAll(",", "");
        String fStr = aStr.replace('.', ' ');
        fStr = fStr.replaceAll(" ", "");
        long money = Long.parseLong(fStr);
        int pointPosition = 0;
        if (aStr.indexOf(".") != -1)
            pointPosition = aStr.length() - aStr.indexOf(".") - 1;
        switch (pointPosition) {
        case 0:
            return money * 100;
        case 1:
            return money * 10;
        case 2:
            return money;
        default:
            return Long.parseLong(fStr.substring(0, aStr.indexOf(".") + 2));
        }
    }

    /**
     * 分到中文的转化
     * 
     * @param num
     *            分
     * @return 中文
     */
    public static String num2Chinese(String num) {
        String yuan, fen;
        String yuanStr = "", fenStr = "";
        int len = 0;

        /**
         * 数据准备
         */
        // 去除千分符","
        num = num.replaceAll(",", "");

        // 判断输入参数是否为数字
        try {
            Float.parseFloat(num);
        } catch (Exception e) {
            return "";
        }

        // 拆分小数位和整数位
        int dot = num.indexOf('.');
        if (dot < 0) {
            yuan = num;
            fen = "00";
        } else {
            yuan = num.substring(0, dot);
            fen = num.substring(dot + 1);
        }

        /**
         * 小数位
         */
        // 限制小数为两位小数
        if (fen.length() == 1) {
            fen += "0";
        } else {
            fen = fen.substring(0, 2);
        }

        // 如果小数位为零，则显示"整"
        if (Integer.parseInt(fen) != 0) {
            len = fen.length();
            for (int i = 0; i < len; i++) {
                int pos = Integer.parseInt(String.valueOf(fen.charAt(len - 1
                        - i)));
                fenStr = CHINESE_NUMBER[pos] + CHINESE_NUMBER_UNIT[i] + fenStr;
            }
        } else {
            fenStr = "整";
        }

        /**
         * 整数位
         */
        // 去除负号
        if (yuan.charAt(0) == '-') {
            yuan = yuan.substring(1);
        }

        // 限制最大位数为9
        if (yuan.length() >= 9) {
            yuan = yuan.substring(yuan.length() - 9);
        }

        // 去除前导0字符
        yuan = String.valueOf(Integer.parseInt(yuan));

        len = yuan.length();

        // 如果为0，则显示"零元"
        if (yuan.equals("0")) {
            yuanStr = "零元";
            len = 0;
        }

        // 其他情况
        boolean zeroFlag1 = false; // 个位至千位零标志
        boolean zeroFlag2 = false; // 万位至千万位零标志

        boolean prevIsZero = false;

        for (int i = 0; i < len; i++) {
            int pos = Integer
                    .parseInt(String.valueOf(yuan.charAt(len - 1 - i)));
            if (pos != 0) {
                if (i > 0 && i < 5) {
                    if (zeroFlag1) {
                        yuanStr = CHINESE_NUMBER[pos]
                                + CHINESE_NUMBER_UNIT[i + 2]
                                + CHINESE_NUMBER_UNIT[2] + yuanStr;
                    } else {
                        yuanStr = CHINESE_NUMBER[pos]
                                + CHINESE_NUMBER_UNIT[i + 2] + yuanStr;
                    }
                    zeroFlag1 = false;
                } else if (i >= 5 && i < 9) {
                    if (zeroFlag2) {
                        yuanStr = CHINESE_NUMBER[pos]
                                + CHINESE_NUMBER_UNIT[i + 2]
                                + CHINESE_NUMBER_UNIT[6] + yuanStr;
                    } else {
                        yuanStr = CHINESE_NUMBER[pos]
                                + CHINESE_NUMBER_UNIT[i + 2] + yuanStr;
                    }
                    zeroFlag2 = false;
                } else {
                    yuanStr = CHINESE_NUMBER[pos] + CHINESE_NUMBER_UNIT[i + 2]
                            + yuanStr;
                }
                prevIsZero = false;
            } else {
                if (i == 0) {
                    zeroFlag1 = true;
                }

                if (i == 4) {
                    zeroFlag2 = true;
                }

                if (!zeroFlag1 && !zeroFlag2 && !prevIsZero) {
                    yuanStr = CHINESE_NUMBER[pos] + yuanStr;
                }
                prevIsZero = true;
            }
        }

        /**
         * 整合
         */
        return yuanStr + fenStr;
    }

    public static void main(String[] args) {
        // String result =
        // MoneyFormat.num2Chinese(MoneyFormat.fen2Yuan(114765));
        long result = MoneyFormat.yuan2Fen("11.909");
        System.out.print(result);
    }
}
