package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;

/**
 * Created by Administrator on 2018/6/26.
 * <p>
 * 可视化编程测试
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

    private void parseBlueDataThread(String data) {

    }
}
