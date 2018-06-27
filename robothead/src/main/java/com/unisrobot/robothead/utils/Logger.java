package com.unisrobot.robothead.utils;

import android.util.Log;

/**
 * Created by Administrator on 2018/6/27.
 */

public class Logger {
        public static void logAll(String tag, String xml) {
                if (xml.length() > 4000) {
                        for (int i = 0; i < xml.length(); i += 4000) {
                                if (i + 4000 < xml.length())
                                        Log.e(tag + "_rescounter" + i, xml.substring(i, i + 4000));
                                else
                                        Log.e(tag + "_rescounter" + i, xml.substring(i, xml.length()));
                        }
                } else
                        Log.e(tag + "_resinfo", xml);
        }
}
