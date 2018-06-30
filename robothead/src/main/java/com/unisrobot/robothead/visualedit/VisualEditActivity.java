package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;
import com.unisrobot.robothead.visualedit.model.LinkNode;
import com.unisrobot.robothead.visualedit.nodebean.basic.JointBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.SpeedTimeBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.TurnAngleBean;
import com.unisrobot.robothead.visualedit.nodebean.combineaction.CombineBean;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.ear.EarBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeFeelingBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeLookAngle;
import com.unisrobot.robothead.visualedit.nodebean.language.MusicBean;
import com.unisrobot.robothead.visualedit.nodebean.language.TtsBean;
import com.unisrobot.robothead.visualedit.type.NodeJsonType;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;
import com.unisrobot.robothead.visualedit.type.TaskJsonType;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
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
 * --------------------------------------------------------
 *{"TaskType":"MSG_TYPE_BASE_TASK","TaskName":"\u6211\u7684\u7B2C\u4E00\u4E2A\u9879\u76EE","TaskIcon":"project_figure00","Tasks":[{"NodeID":104,"Type":"Basic","PrefabName":"BasicActionPrefab_Second","Event":"\u505C\u6B62\u79FB\u52A8","HeartInfo":null,"Args":null,"InputInits":[{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}],"Interrupt":null,"TasksOth":null,"FuncName":[],"TasksOth2":[{"HeadNodePos":{"X":"251.283","Y":"-1889.775"},"TasksOther":[{"NodeID":105,"Type":"Mind","PrefabName":"MindPrefab_AndOrRelation","Event":"\u6216","HeartInfo":null,"Args":null,"InputInits":null,"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"250.6313","Y":"-1984.564"},"TasksOther":[{"NodeID":106,"Type":"Mind","PrefabName":"MindPrefab_AndOrRelation","Event":"\u4E14","HeartInfo":null,"Args":null,"InputInits":null,"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"289.6626","Y":"-2095.489"},"TasksOther":[{"NodeID":107,"Type":"Mind","PrefabName":"MindPrefab_NumberIs","Event":"Mind_\u6570\u5B57\u662F..","HeartInfo":null,"Args":[{"Content":"\u662F\u5076\u6570","WordsStyle":null}],"InputInits":[{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"341.4963","Y":"-2192.331"},"TasksOther":[{"NodeID":108,"Type":"Mind","PrefabName":"MindPrefab_CalculateNumber","Event":"Mind_\u8BA1\u7B97\u6570\u5B57","HeartInfo":null,"Args":[{"Content":"sin","WordsStyle":null}],"InputInits":[{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"227.3254","Y":"-2298.847"},"TasksOther":[{"NodeID":109,"Type":"Mind","PrefabName":"MindPrefab_NumberCanBeDone","Event":"\u6570\u5B57\u53EF\u88AB..","HeartInfo":null,"Args":[{"Content":"\u53EF\u88AB\u6574\u9664","WordsStyle":null}],"InputInits":[{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"304.9502","Y":"-2402.204"},"TasksOther":[{"NodeID":110,"Type":"Mind","PrefabName":"MindPrefab_CalculateNumber","Event":"Mind_\u8BA1\u7B97\u6570\u5B57","HeartInfo":null,"Args":[{"Content":"\u56DB\u820D\u4E94\u5165","WordsStyle":null}],"InputInits":[{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"398.2499","Y":"-2632.737"},"TasksOther":[{"NodeID":111,"Type":"Mind","PrefabName":"MindPrefab_Heart","Event":"\u7231\u5FC3","HeartInfo":null,"Args":null,"InputInits":null,"Colors":null,"Pictures":[{"Picture":"Pink"}],"Appendent":null,"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"641.8521","Y":"-2656.82"},"TasksOther":[{"NodeID":112,"Type":"Mind","PrefabName":"MindPrefab_Not","Event":"\u975E","HeartInfo":null,"Args":null,"InputInits":null,"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"269.5613","Y":"-2639.185"},"TasksOther":[{"NodeID":113,"Type":"Mind","PrefabName":"MindPrefab_Num","Event":"\u6570\u5B57","HeartInfo":null,"Args":[{"Content":"0","WordsStyle":null}],"InputInits":null,"Colors":null,"Pictures":null,"Appendent":null,"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"239.4246","Y":"-338.377"},"TasksOther":[{"NodeID":114,"Type":"Perception","PrefabName":"PerceptionPrefab_SensorCTCT","Event":"\u6E29\u6E7F\u5EA6\u4F20\u611F\u5668","HeartInfo":null,"Args":[{"Content":"\u5DE6\u4FA7","WordsStyle":null},{"Content":"\u6E29\u5EA6","WordsStyle":null}],"InputInits":null,"Colors":null,"Pictures":null,"Appendent":null,"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"137.4917","Y":"-467.6902"},"TasksOther":[{"NodeID":115,"Type":"Perception","PrefabName":"PerceptionPrefab_SensorAPIContext","Event":"API\u63A5\u53E3\u8FD4\u56DE\u6570\u636E\uFF1A\u6027\u522B","HeartInfo":null,"Args":[{"Content":"\u5973","WordsStyle":null}],"InputInits":null,"Colors":null,"Pictures":null,"Appendent":null,"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"1223.893","Y":"-1972.565"},"TasksOther":[{"NodeID":116,"Type":"Mind","PrefabName":"MindPrefab_AtoBRandomInteger","Event":"Mind_\u968F\u673A\u6574\u6570","HeartInfo":null,"Args":null,"InputInits":[{"Number":"1"},{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]},{"HeadNodePos":{"X":"1345.612","Y":"-1958.156"},"TasksOther":[{"NodeID":117,"Type":"Mind","PrefabName":"MindPrefab_AcalculateBRemainder","Event":"Mind_\u4F59\u6570","HeartInfo":null,"Args":null,"InputInits":[{"Number":"1"},{"Number":"1"}],"Colors":null,"Pictures":null,"Appendent":{"Append_A":null,"Append_B":null,"Append_C":null},"Actions":null,"Switch":null}]}],"SensorDatas":[{"TypeName":"TemperatureHumidity_Temper_Left","totalCount":1}]}
 *
 * 难点： 参数节点 应该返回什么数据类型？？
 *
 */

public class VisualEditActivity extends Activity {
    private static final int NEXT = 1;
    private static final String TAG = VisualEditActivity.class.getSimpleName();
    private BluToothMgr bluToothMgr;
    private LinkedList<VpJsonBean.NodeDataBase> rootNodeLists = new LinkedList<>();
    private LinkNode currentFatherLinkNode;
    private LinkNode currentExeLinkNode; //当前正在执行的node,这是为了消息分发能够收到
    private ExeHandler exeHandler;
    private int rootNodeListYIndex = 0;  // y 方向的 链表索引

    private static class ExeHandler extends Handler {
        WeakReference<VisualEditActivity> weakReference;

        public ExeHandler(VisualEditActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            VisualEditActivity visualEditActivity = weakReference.get();
            if (visualEditActivity != null && !visualEditActivity.isDestroyed()) {
                switch (msg.what) {
                    case NEXT:
                        visualEditActivity.exeNextNode();
                        break;
                }
            }
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exeHandler = new ExeHandler(this);
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
                case TaskJsonType.MSG_TYPE_BASE_TASK:
                    handlerVpTaskThread(vpJsonBean.Tasks);
                    break;
                case TaskJsonType.MSG_TYPE_STOP:
                    clean();
                    Log.e(TAG, "parseNodeDataThread: clean task");
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
            clean();
            rootNodeLists.addAll(tasks);
            Log.e(TAG, "handlerVpTaskThread: fatherNodeList size=== " + rootNodeLists.size());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    VpJsonBean.NodeDataBase nodeDataBase = rootNodeLists.get(rootNodeListYIndex++);
                    contachLinkNode(new LinkNode(nodeDataBase));
                }
            });
        }
    }

    private void exeNextNode() {
        if (currentFatherLinkNode != null) {
            boolean hasNext = currentFatherLinkNode.hasNextChildNode();
            if (hasNext) {
                Log.e(TAG, "exeNextNode: >>>>>>>>>>>>>>>> next child.......... ");
                VpJsonBean.NodeDataBase nextData = currentFatherLinkNode.getNextChildNode();
                contachLinkNode(new LinkNode(nextData));
            } else {
                LinkNode prevFatherNode = currentFatherLinkNode.getFatherNode();
                if (prevFatherNode != null) {
                    currentFatherLinkNode = prevFatherNode;
                    exeNextNode();
                } else {
                    exeNextRootNode();
                }
            }
        } else {
            exeNextRootNode();
        }
    }

    private void exeNextRootNode() {
        if (rootNodeListYIndex < rootNodeLists.size()) {
            VpJsonBean.NodeDataBase nodeDataBase = rootNodeLists.get(rootNodeListYIndex++);
            contachLinkNode(new LinkNode(nodeDataBase));
        } else {
            Log.e(TAG, "exeNextNode: **********************end");
        }
    }

    private void contachLinkNode(LinkNode linkNode) {
        if (linkNode.isContainerNode()) { //如果是容器节点,看是不是 条件型容器节点
            if (currentFatherLinkNode == null) {
                currentFatherLinkNode = linkNode;
            } else {
                currentFatherLinkNode.setChildContainerNode(linkNode);
                linkNode.setFatherNode(currentFatherLinkNode);
                currentFatherLinkNode = linkNode;
            }
            Log.e(TAG, "contachLinkNode: father node event=="+linkNode.getEvent() );
            VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
            contachLinkNode(new LinkNode(nextChildNode));
        } else { //如果是非容器型节点，直接执行
            exeLinkNode(linkNode);
        }
    }

    private void exeLinkNode(LinkNode node) {
        currentExeLinkNode = node;
        VpJsonBean.NodeDataBase nodeDataBase = node.getNextChildNode();
        dispatchNode(nodeDataBase);
        mockNext();
    }

    private void dispatchRobotMsg(RobotMsgType robotMsgType, Bundle bundle) {
        if (currentFatherLinkNode != null) {
            //消息一次往下传递，如果处理了，返回true ，
            boolean result = currentFatherLinkNode.handlerMsg(robotMsgType, bundle);
            if (!result) {
                Log.e(TAG, "dispatchRobotMsg: msg no handler.......................");
            }
        }
    }

    private void mockNext() {
        //模拟下一个节点执行的条件到了, 具体什么时候执行下一个？？？
        exeHandler.sendEmptyMessageDelayed(NEXT, 2000);
    }

    /**
     * 节点分发
     */
    private void dispatchNode(VpJsonBean.NodeDataBase node) {
        String type = node.Type;
        Log.e(TAG, "dispatchNode: node type ===" + node.Event);
        switch (type) {
            case NodeJsonType.BASIC:
                exeBasicNode(node);
                break;
            case NodeJsonType.COMBINEACTION:
                exeCombineActionNode(node);
                break;
            case NodeJsonType.EARS:
                exeEarNode(node);
                break;
            case NodeJsonType.EYES:
                exeEyesNode(node);
                break;
            case NodeJsonType.LANGUAGE:
                exeLanguageNode(node);
                break;
            case NodeJsonType.LOGIC:
                exeLogicNode(node);
                break;
            case NodeJsonType.MIND:
                break;
            case NodeJsonType.PERCEPTION:
                break;
        }
    }

    private void clean() {
        if (currentFatherLinkNode != null) {
            currentFatherLinkNode.stop();
            currentFatherLinkNode = null;
        }
        rootNodeListYIndex = 0;
        rootNodeLists.clear();
        exeHandler.removeCallbacksAndMessages(null);
    }


    private void exeLogicNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeJsonType.Logic.LogicPrefab_:
                break;
            case NodeJsonType.Logic.LogicPrefab_CallFunction:
                break;
            case NodeJsonType.Logic.LogicPrefab_RepeatCycle:
                break;
            case NodeJsonType.Logic.LogicPrefab_RepeatUntil:
                break;
            case NodeJsonType.Logic.LogicPrefab_If:
                break;
            case NodeJsonType.Logic.LogicPrefab_IfElse:
                break;
            case NodeJsonType.Logic.LogicPrefab_IfElseFace:
                break;
            case NodeJsonType.Logic.LogicPrefab_MakeFunction:
                break;
            case NodeJsonType.Logic.LogicPrefab_RepeatCount:
                break;
            case NodeJsonType.Logic.LogicPrefab_WaitSecond:
                break;
        }
    }

    private void exeEarNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeJsonType.Ears.EarsPrefab_: //要去云端获取答案然后才能执行下一个节点
                EarBean earBean = EarBean.getBean(nodeData);
                earBean.exeNode();
                break;
            case NodeJsonType.Ears.EarsPrefab_Hear:
                break;
        }
    }

    private void exeEyesNode(VpJsonBean.NodeDataBase nodeData) {
        switch (nodeData.PrefabName) {
            case NodeJsonType.Eyes.EyesPrefab_:
            case NodeJsonType.Eyes.EyesPrefab_LookAngle:
                EyeLookAngle eyeLookAngle = EyeLookAngle.getBean(nodeData);
                eyeLookAngle.exeNode();
                break;
            case NodeJsonType.Eyes.EyesPrefab_Feelings:
                EyeFeelingBean eyeFeelingBean = EyeFeelingBean.getBean(nodeData);
                eyeFeelingBean.exeNode();
                break;
        }
    }

    private void exeLanguageNode(VpJsonBean.NodeDataBase node) {
        switch (node.PrefabName) {
            case NodeJsonType.Language.LanguagePrefab_Speak:// tts
                TtsBean bean = TtsBean.getBean(node);
                bean.exeNode();
                break;
            case NodeJsonType.Language.LanguagePrefab_:// music
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
            case NodeJsonType.Basic.BasicActionPrefab_GoSpeedSeconed:
                SpeedTimeBean bean = SpeedTimeBean.getBean(nodeData);
                bean.exeNode();
                break;
            case NodeJsonType.Basic.BasicActionPrefab_BasicJoint:
                JointBean bean1 = JointBean.getBean(nodeData);
                bean1.exeNode();
                break;
            case NodeJsonType.Basic.BasicActionPrefab_TrunAngle:
                TurnAngleBean bean2 = TurnAngleBean.getBean(nodeData);
                bean2.exeNode();
                break;
            case NodeJsonType.Basic.BasicActionPrefab_Second:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluToothMgr.destroy();
    }
}
