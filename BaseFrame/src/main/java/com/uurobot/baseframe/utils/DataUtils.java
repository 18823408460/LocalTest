package com.uurobot.baseframe.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataUtils {

        public static float floatTranslate(float data) {
                DecimalFormat fnum = new DecimalFormat("##0.0");
                return Float.parseFloat(fnum.format(data));
        }

}
