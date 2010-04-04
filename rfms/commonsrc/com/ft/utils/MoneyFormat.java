package com.ft.utils;

import java.text.DecimalFormat;

/**
 * ������������ת����
 * 
 * @version 1.0
 */
public class MoneyFormat {
    /**
     * ��ȷ���ֵĽ���ʽ
     */
    private final static String MONEY_FORMAT = "#,##0.00";

    /**
     * ��ȷ����Ľ���ʽ
     */
    private final static String LI_MONEY_FORMAT = "#,##0.000";

    /**
     * �������ֳ���
     */
    private final static String[] CHINESE_NUMBER = { "��", "Ҽ", "��", "��", "��",
            "��", "½", "��", "��", "��" };

    /**
     * �������ֵ�λ����
     */
    private final static String[] CHINESE_NUMBER_UNIT = { "��", "��", "Բ", "ʰ",
            "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��" };

    /**
     * �ֵ�Ԫ��ת��
     * 
     * @param aLong
     *            ��
     * @return String Ԫ
     */
    public static String fen2Yuan(long aLong) {
        DecimalFormat df = new DecimalFormat(MONEY_FORMAT);
        return df.format(aLong / 100.00);
    }

    /**
     * �嵽Ԫ��ת��
     * 
     * @param aLong
     *            ��
     * @return String Ԫ
     */
    public static String li2Yuan(long aLong) {
        DecimalFormat df = new DecimalFormat(LI_MONEY_FORMAT);
        return df.format(aLong / 1000.000);
    }

    /**
     * �ֵ�Ԫ��ת��
     * 
     * @param aStr
     *            ��
     * @return String Ԫ
     */
    public static String fen2Yuan(String aStr) {
        try {
            return fen2Yuan(Long.parseLong(aStr));
        } catch (Exception e) {
            return fen2Yuan(0L);
        }
    }

    /**
     * �嵽Ԫ��ת��
     * 
     * @param aStr
     *            ��
     * @return String Ԫ
     */
    public static String li2Yuan(String aStr) {
        try {
            return li2Yuan(Long.parseLong(aStr));
        } catch (Exception e) {
            return li2Yuan(0);
        }
    }

    /**
     * Ԫ���ֵ�ת��
     * 
     * @param aStr
     *            Ԫ
     * @return String ��
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
     * �ֵ����ĵ�ת��
     * 
     * @param num
     *            ��
     * @return ����
     */
    public static String num2Chinese(String num) {
        String yuan, fen;
        String yuanStr = "", fenStr = "";
        int len = 0;

        /**
         * ����׼��
         */
        // ȥ��ǧ�ַ�","
        num = num.replaceAll(",", "");

        // �ж���������Ƿ�Ϊ����
        try {
            Float.parseFloat(num);
        } catch (Exception e) {
            return "";
        }

        // ���С��λ������λ
        int dot = num.indexOf('.');
        if (dot < 0) {
            yuan = num;
            fen = "00";
        } else {
            yuan = num.substring(0, dot);
            fen = num.substring(dot + 1);
        }

        /**
         * С��λ
         */
        // ����С��Ϊ��λС��
        if (fen.length() == 1) {
            fen += "0";
        } else {
            fen = fen.substring(0, 2);
        }

        // ���С��λΪ�㣬����ʾ"��"
        if (Integer.parseInt(fen) != 0) {
            len = fen.length();
            for (int i = 0; i < len; i++) {
                int pos = Integer.parseInt(String.valueOf(fen.charAt(len - 1
                        - i)));
                fenStr = CHINESE_NUMBER[pos] + CHINESE_NUMBER_UNIT[i] + fenStr;
            }
        } else {
            fenStr = "��";
        }

        /**
         * ����λ
         */
        // ȥ������
        if (yuan.charAt(0) == '-') {
            yuan = yuan.substring(1);
        }

        // �������λ��Ϊ9
        if (yuan.length() >= 9) {
            yuan = yuan.substring(yuan.length() - 9);
        }

        // ȥ��ǰ��0�ַ�
        yuan = String.valueOf(Integer.parseInt(yuan));

        len = yuan.length();

        // ���Ϊ0������ʾ"��Ԫ"
        if (yuan.equals("0")) {
            yuanStr = "��Ԫ";
            len = 0;
        }

        // �������
        boolean zeroFlag1 = false; // ��λ��ǧλ���־
        boolean zeroFlag2 = false; // ��λ��ǧ��λ���־

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
         * ����
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
