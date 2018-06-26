package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;

/**
 * Created by Administrator on 2018/6/26.
 * <p>
 * 可视化编程测试, 每个节点应该怎么执行?
 * 1. 普通节点,直接执行（需要根据不同的类型，换算对应的时间）
 * 2. 带有逻辑关系的节点，需要找到节点 执行的条件，节点执行完毕的条件,
 *    条件: 执行次数, 传感器, 逻辑运算,
 *
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

    private void parseBlueDataThread(String data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluToothMgr.destroy();
    }
}
