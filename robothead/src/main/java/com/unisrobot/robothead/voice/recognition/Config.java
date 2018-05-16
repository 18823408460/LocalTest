package com.unisrobot.robothead.voice.recognition;

import android.os.Environment;

/**
 * Created by Administrator on 2018/5/16.
 */

public class Config {
        public static final boolean OpenFiveMic = true;
        public static final String GRAMMAR_PATH = Environment.getExternalStorageDirectory() + "/grammar/";
        public static boolean OpenUnderstander = true;
}
