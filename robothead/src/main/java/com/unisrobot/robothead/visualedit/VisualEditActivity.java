package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;
import com.unisrobot.robothead.visualedit.nodebean.basic.JointBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.SpeedTimeBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.TurnAngleBean;
import com.unisrobot.robothead.visualedit.nodebean.combineaction.CombineBean;
import com.unisrobot.robothead.visualedit.nodebean.ear.EarBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeFeelingBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeLookAngle;
import com.unisrobot.robothead.visualedit.nodebean.language.MusicBean;
import com.unisrobot.robothead.visualedit.nodebean.language.TtsBean;

import java.util.ArrayList;
import java.util.List;

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
    private List<VpJsonBean.NodeDataBase> nodeDataBaseList = new ArrayList<>();

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
                        parseNodeDataThread(vpJsonBean);
                    }
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                Log.e(TAG, "parseBlueDataThread: jsonSyntaxException = " + e);
            }
        }
    }

    private void parseNodeDataThread(VpJsonBean vpJsonBean) {
        if (vpJsonBean != null) { // 这里是在子线程执行
            switch (vpJsonBean.TaskType) {
                case TaskType.MSG_TYPE_BASE_TASK:
                    handlerVpTaskThread(vpJsonBean.Tasks);
                    break;
                case TaskType.MSG_TYPE_STOP:
                    nodeDataBaseList.clear();
                    Log.e(TAG, "parseNodeDataThread: stop task");
                    break;
            }
        }
    }

    /**
     * 执行可视化编程 Task
     *
     * @param tasks
     */
    private void handlerVpTaskThread(final List<VpJsonBean.NodeDataBase> tasks) {
        if (tasks != null && tasks.size() > 0) {
            nodeDataBaseList.clear();
            nodeDataBaseList.addAll(tasks);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // 这里不能用 remove，因为可能是重复执行
                    VpJsonBean.NodeDataBase node = nodeDataBaseList.remove(0);
                    dispatchNode(node);
                }
            });
        }
    }

    /**
     * 节点分发
     *
     * @param node
     */
    private void dispatchNode(VpJsonBean.NodeDataBase node) {
        String type = node.Type;
        switch (type) {
            case NodeType.BASIC:
                exeBasicNode(node);
                break;
            case NodeType.COMBINEACTION:
                exeCombineActionNode(node);
                break;
            case NodeType.EARS:
                exeEarNode(node);
                break;
            case NodeType.EYES:
                exeEyesNode(node);
                break;
            case NodeType.LANGUAGE:
                exeLanguageNode(node);
                break;
            case NodeType.LOGIC:
                exeLogicNode(node);
                break;
            case NodeType.MIND:
                break;
            case NodeType.PERCEPTION:
                break;
        }
    }

    private void exeLogicNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeType.Logic.LogicPrefab_:
                break;
            case NodeType.Logic.LogicPrefab_CallFunction:
                break;
            case NodeType.Logic.LogicPrefab_ContinueRepeat:
                break;
            case NodeType.Logic.LogicPrefab_ContinueUntil:
                break;
            case NodeType.Logic.LogicPrefab_If:
                break;
            case NodeType.Logic.LogicPrefab_IfElse:
                break;
            case NodeType.Logic.LogicPrefab_IfElseFace:
                break;
            case NodeType.Logic.LogicPrefab_MakeFunction:
                break;
            case NodeType.Logic.LogicPrefab_RepeatTimes:
                break;
            case NodeType.Logic.LogicPrefab_WaitSecond:
                break;
        }
    }

    private void exeEarNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeType.Ears.EarsPrefab_: //要去云端获取答案然后才能执行下一个节点
                EarBean earBean = EarBean.getBean(nodeData);
                earBean.exeNode();
                break;
            case NodeType.Ears.EarsPrefab_Hear:
                break;
        }
    }

    private void exeEyesNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeType.Eyes.EyesPrefab_:
            case NodeType.Eyes.EyesPrefab_LookAngle:
                EyeLookAngle eyeLookAngle = EyeLookAngle.getBean(nodeData);
                eyeLookAngle.exeNode();
                break;
            case NodeType.Eyes.EyesPrefab_Feelings:
                EyeFeelingBean eyeFeelingBean = EyeFeelingBean.getBean(nodeData);
                eyeFeelingBean.exeNode();
                break;
        }
    }

    private void exeLanguageNode(VpJsonBean.NodeDataBase node) {
        switch (node.PrefabName) {
            case NodeType.Language.LanguagePrefab_Speak:// tts
                TtsBean bean = TtsBean.getBean(node);
                bean.exeNode();
                break;
            case NodeType.Language.LanguagePrefab_:// music
                MusicBean musicBean = MusicBean.getBean(node);
                musicBean.exeNode();
                break;
        }
    }

    private void exeCombineActionNode(VpJsonBean.NodeDataBase node) {
        CombineBean bean = CombineBean.getBean(node);
        bean.exeNode();
    }

    /**
     * basic 节点的执行
     *
     * @param nodeData
     */
    private void exeBasicNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeType.Basic.BasicActionPrefab_GoSpeedSeconed:
                SpeedTimeBean bean = SpeedTimeBean.getBean(nodeData);
                bean.exeNode();
                break;
            case NodeType.Basic.BasicActionPrefab_BasicJoint:
                JointBean bean1 = JointBean.getBean(nodeData);
                bean1.exeNode();
                break;
            case NodeType.Basic.BasicActionPrefab_TrunAngle:
                TurnAngleBean bean2 = TurnAngleBean.getBean(nodeData);
                bean2.exeNode();
                break;
            case NodeType.Basic.BasicActionPrefab_Second:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluToothMgr.destroy();
    }
}
