package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/6/26.
 * <p>
 * 可视化编程测试, 每个节点应该怎么执行?
 * 1. 普通节点,直接执行（需要根据不同的类型，换算对应的时间）
 * 2. 带有逻辑关系的节点，需要找到节点 执行的条件，节点执行完毕的条件,
 * 条件: 执行次数, 传感器, 逻辑运算,
 * <p>
 * 3.节点什么结束完毕,节点什么时候开始执行？？
 */

public class VisualEditActivity extends Activity {
        private static final String TAG = VisualEditActivity.class.getSimpleName();
        private BluToothMgr bluToothMgr;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initBlueTooth();
        }

        private void initBlueTooth() {
                bluToothMgr = BluToothMgr.getInstance();
                bluToothMgr.init();
                bluToothMgr.setBluetoothLisenter(new IBluetoothLisenter() {
                        @Override
                        public void OnReceiverData(String data) { //这里是handler 子线程
                                parseBlueDataThread(data);
                        }
                });
        }

        private void parseBlueDataThread(String readUTF) {
                if (readUTF.matches("\\w+&&#[1-9]\\.wav")) {
                        String[] split = readUTF.split("&&#");
                        Log.e(TAG, "parseBlueDataThread: music");
                } else {
                        VpJsonBean vpJsonBean = null;
                        try {
                                if (readUTF != null) {
                                        if (readUTF.startsWith("{") || readUTF.startsWith("[")) {
                                                vpJsonBean = new Gson().fromJson(readUTF, VpJsonBean.class);
                                                Log.e(TAG, "parseBlueDataThread: data=" + vpJsonBean);
                                        }
                                }
                        } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                Log.e(TAG, "parseBlueDataThread: jsonSyntaxException = " + e);
                        }
                }
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                bluToothMgr.destroy();
        }
}
