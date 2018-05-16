package com.unisrobot.robothead.voice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2018/5/16.
 */

/**
 * adb shell am broadcast -a com.android.test --es test_string "this is test string" --ei test_int 100 --ez test_boolean true
 * (--es string)                          --ei int         --ez boolean
 *
 *adb shell am broadcast -a action.wifi  --es ssid "uurobotyin" --es pwd "987654321"
 *
 * adb shell am broadcat -a action.text --es text "hello"
 * adb shell am broadcat -a action.text --es text "今天天气怎么样"
 *
 */
public class GlobalCmdReciever extends BroadcastReceiver {
        private static final String action_wifi = "action.wifi";
        private static final String action_text = "action.text";
        private static final String TAG = "GlobalCmdReciever";

        @Override
        public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                        String action = intent.getAction();
                        Log.e(TAG, "onReceive: " + action);
                        switch (action) {
                                case action_text:
                                        String text = intent.getStringExtra("text");
                                        Log.e(TAG, "onReceive: text== " + text);
                                        break;
                                case action_wifi:
                                        String ssid = intent.getStringExtra("ssid");
                                        String pwd = intent.getStringExtra("pwd");
                                        Log.e(TAG, "onReceive: ssid=" + ssid + "   pwd=" + pwd);
                                        break;
                        }
                }
        }
}
