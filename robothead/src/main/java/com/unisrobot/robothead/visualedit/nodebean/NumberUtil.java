package com.unisrobot.robothead.visualedit.nodebean;

/**
 * Created by Administrator on 2018/6/27.
 */

public class NumberUtil {
        /**
         * 计算两个数
         *
         * @param operate
         * @param number1
         * @param number2
         * @return
         */
        public static String handlerNumber(String operate, String number1, String number2) {
                String result = number1;
                switch (operate) {
                        case "Add"://加
                                result = Double.parseDouble(number1) + Double.parseDouble(number2) + "";
                                break;
                        case "Dele"://减
                                result = Double.parseDouble(number1) - Double.parseDouble(number2) + "";
                                break;
                        case "Multi"://乘
                                result = Double.parseDouble(number1) * Double.parseDouble(number2) + "";
                                break;
                        case "Divide"://除
                                result = Double.parseDouble(number1) / Double.parseDouble(number2) + "";
                                break;
                        case "Bigger":// 大于
                        case "Smaller"://小于
                        case "Equal":// 等于
                        case "NotEqual":// 不等于
                                result = compareInt(number1, number2, operate);
                                break;
                        default:
                                break;
                }
                return result;
        }

        private static String compareInt(String number1, String number2, String oprator) {
                String result = "false";
                Integer integer = Integer.valueOf(number1);
                Integer integer2 = Integer.valueOf(number2);
                if ("Bigger".equals(oprator)) {
                        if (integer > integer2) {
                                result = "true";
                        }
                } else if ("Smaller".equals(oprator)) {
                        if (integer < integer2) {
                                result = "true";
                        }
                } else if ("Equal".equals(oprator)) {
                        if (integer == integer2) {
                                result = "true";
                        }
                } else if ("NotEqual".equals(oprator)) {
                        if (integer != integer2) {
                                result = "true";
                        }
                }
                return result;
        }
}
