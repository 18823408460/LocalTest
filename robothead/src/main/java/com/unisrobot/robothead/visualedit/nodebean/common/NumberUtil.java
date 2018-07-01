package com.unisrobot.robothead.visualedit.nodebean.common;

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
    public static String calculateTwoNum(String operate, String number1, String number2) {
        String result = "";
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
            default:
                break;
        }
        return result;
    }

    /**
     * 取整操作
     *
     * @param operate
     * @param number
     * @return
     */
    public static String calculateOneNum(String operate, String number) {
        String result = "";
        switch (operate) {
            case NodeParams.Mind.SI_SHE_WU_RU://
                result = String.valueOf(Math.round(Double.valueOf(number)));
                break;
            case NodeParams.Mind.DOWN_ZHENG://
                result = String.valueOf(Math.floor(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.UP_ZHENG://
                result = String.valueOf(Math.ceil(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.SIN://
                result = String.valueOf(Math.sin(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.COS://
                result = String.valueOf(Math.cos(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.TAN://
                result = String.valueOf(Math.tan(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.SUQRY://
                result = String.valueOf(Math.sqrt(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.ABS://
                result = String.valueOf(Math.abs(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.ARCSIN://
                result = String.valueOf(Math.asin(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.ARCCOS://
                result = String.valueOf(Math.acos(Double.parseDouble(number)));
                break;
            case NodeParams.Mind.ARCTAN://
                result = String.valueOf(Math.atan(Double.parseDouble(number)));
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 在两个数中间获取一个随机数
     *
     * @param inputNumber1
     * @param inputNumber2
     * @return
     */
    public static String getRandomNum(String inputNumber1, String inputNumber2) {
        String data = "";
        float anInt1 = Float.parseFloat(inputNumber1);
        float anInt2 =  Float.parseFloat(inputNumber2);
        if (anInt1 > anInt2) {
            float diff = anInt1 - anInt2;
            int random = (int) (Math.random() * diff);
            data = String.valueOf(anInt2 + random);
        } else {
            float diff = anInt2 - anInt1;
            int random = (int) (Math.random() * diff);
            data = String.valueOf(anInt1 + random);
        }
        return data;
    }

    /**
     * 比较两个数
     *
     * @param operate
     * @param numberParams1
     * @param numberParams2
     * @return
     */
    public static boolean compareTowNum(String operate, String numberParams1, String numberParams2) {
        boolean result = false;
        float integer1 = Float.parseFloat(numberParams1);
        float integer2 =  Float.parseFloat(numberParams2);
        switch (operate) {
            case "Bigger":
                result = (integer1 > integer2);
                break;
            case "Smaller":
                result = (integer1 < integer2);
                break;
            case "Equal":
                result = (integer1 == integer2);
                break;
            case "NotEqual":
                result = (integer1 != integer2);
                break;
        }
        return result;
    }

    /**
     * 判断数字是 XXX
     *
     * @param content
     * @param numberParams
     * @return
     */
    public static boolean numberIs(String content, String numberParams) {
        boolean resultData = false;
        float numInt = Float.parseFloat(numberParams);
        switch (content) {
            case NodeParams.Mind.IS_OUSHU:
                resultData = (numInt / 2 == 0);
                break;
            case NodeParams.Mind.IS_JISHU:
                resultData = (numInt / 2 != 0);
                break;
            case NodeParams.Mind.IS_ZHISHU:
                break;
            case NodeParams.Mind.IS_ZHNEGSHU:
                break;
            case NodeParams.Mind.IS_ZHNEG:
                resultData = numInt > 0;
                break;
            case NodeParams.Mind.IS_FU:
                resultData = numInt < 0;
                break;
        }
        return resultData;
    }

    /**
     * 可以被整除
     *
     * @param content
     * @param numberParams2
     * @return
     * @parzham numberParams1
     */
    public static boolean numCanbeDone(String content, String numberParams1, String numberParams2) {
        boolean resultData = false;
        float integer1 = Float.parseFloat(numberParams1);
        float integer2 =  Float.parseFloat(numberParams2);
        switch (content) {
            case NodeParams.Mind.Can_Be_Zhengchu:
                resultData = (integer1 % integer2 == 0);
                break;
        }
        return resultData;
    }

    /**
     * 两个数的逻辑判断
     *
     * @param logicOperate
     * @param booleanAppendA
     * @param booleanAppendB
     * @return
     */
    public static boolean numLogicOperate(String logicOperate, boolean booleanAppendA, boolean booleanAppendB) {
        boolean resultData = false;
        switch (logicOperate) {
            case NodeEvent.Mind.AND:
                resultData = (booleanAppendA && booleanAppendB);
                break;
            case NodeEvent.Mind.OR:
                resultData = (booleanAppendA || booleanAppendB);
                break;
        }
        return resultData;
    }
}
