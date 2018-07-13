package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unisrobot.robothead.R;
import com.unisrobot.robothead.visualedit.bluetooth.BluToothMgr;
import com.unisrobot.robothead.visualedit.bluetooth.IBluetoothLisenter;
import com.unisrobot.robothead.visualedit.interfaces.IMsgCanHandler;
import com.unisrobot.robothead.visualedit.model.LinkNode;
import com.unisrobot.robothead.visualedit.nodebean.basic.JointBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.SpeedTimeBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.StopMoveBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.TurnAngleBean;
import com.unisrobot.robothead.visualedit.nodebean.combineaction.CombineBean;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeParams;
import com.unisrobot.robothead.visualedit.nodebean.common.SensorMgr;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.ear.EarBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeFeelingBean;
import com.unisrobot.robothead.visualedit.nodebean.eye.EyeLookAngle;
import com.unisrobot.robothead.visualedit.nodebean.language.MusicBean;
import com.unisrobot.robothead.visualedit.nodebean.language.TtsBean;
import com.unisrobot.robothead.visualedit.nodebean.logic.FaceRecogBean;
import com.unisrobot.robothead.visualedit.nodebean.logic.LogicWaitSecondBean;
import com.unisrobot.robothead.visualedit.nodebean.logic.MapBean;
import com.unisrobot.robothead.visualedit.nodebean.mind.HeartChangeBean;
import com.unisrobot.robothead.visualedit.nodebean.mind.HeartSetBean;
import com.unisrobot.robothead.visualedit.nodebean.percetion.PercetionNormalBean;
import com.unisrobot.robothead.visualedit.nodebean.percetion.WaitPhoneBean;
import com.unisrobot.robothead.visualedit.nodebean.percetion.WaitTouchBean;
import com.unisrobot.robothead.visualedit.type.NodeJsonType;
import com.unisrobot.robothead.visualedit.type.NodeRunType;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;
import com.unisrobot.robothead.visualedit.type.TaskJsonType;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
 * 难点： 参数节点 应该返回什么数据类型？？
 */

public class VisualEditActivity extends Activity implements IMsgCanHandler, Observer {
        private static final int NEXT = 1;
        private static final int Timer = 2;
        private static final String TAG = VisualEditActivity.class.getSimpleName();
        private BluToothMgr bluToothMgr;
        private LinkedList<VpJsonBean.NodeDataBase> rootNodeLists = new LinkedList<>();
        private LinkNode currentFatherLinkNode;
        private LinkNode firstFatherNode;
        private ExeHandler exeHandler;
        private int rootNodeListYIndex = 0;  // y 方向的 链表索引
        private LinkNode currentExeLinkNode;
        private SensorMgr sensorMgr;

        @Override
        public void update(Observable o, Object arg) {

        }

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
                                        case Timer:
                                                visualEditActivity.dispatchRobotMsg(RobotMsgType.Timer, null);
                                                break;
                                }
                        }
                }
        }

        public void playEnd(View view) {
                dispatchRobotMsg(RobotMsgType.PlayEnd, null);
        }

        public void sensor(View view) {
                dispatchRobotMsg(RobotMsgType.SensorTouch, null);
        }

        public void timer(View view) {
                dispatchRobotMsg(RobotMsgType.Timer, null);
        }

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_visualedit);
                sensorMgr = SensorMgr.getSensorMgr();
                sensorMgr.addObserver(this);
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
                                        parseFatherNode(new LinkNode(nodeDataBase, VisualEditActivity.this));
                                }
                        });
                }
        }

        private void exeNextNode() {
                if (currentFatherLinkNode != null) {
                        boolean hasNext = currentFatherLinkNode.hasNextChildNode();
                        if (hasNext) {
                                VpJsonBean.NodeDataBase nextData = currentFatherLinkNode.getNextChildNode();
                                parseFatherNode(new LinkNode(nextData, this));
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
                currentFatherLinkNode = null;
                if (firstFatherNode != null) {
                        firstFatherNode.setChildContainerNode(null);
                }
                if (rootNodeListYIndex < rootNodeLists.size()) {
                        VpJsonBean.NodeDataBase nodeDataBase = rootNodeLists.get(rootNodeListYIndex++);
                        parseFatherNode(new LinkNode(nodeDataBase, this));
                } else {
                        clean();
                        Log.e(TAG, "exeNextNode: **********************end");
                }
        }

        private void parseFatherNode(LinkNode linkNode) {
                if (linkNode.isContainerNode()) { //如果是容器节点,看是不是 条件型容器节点
                        if (currentFatherLinkNode == null) {
                                firstFatherNode = currentFatherLinkNode = linkNode;
                        } else {
                                currentFatherLinkNode.setChildContainerNode(linkNode);
                                linkNode.setFatherNode(currentFatherLinkNode);
                                currentFatherLinkNode = linkNode;
                        }
                        Log.e(TAG, "parseFatherNode: father node event==" + linkNode.getEvent());
                        if (linkNode.hasNextChildNode()) { // 如果里面为空，直接跳过，避免报错
                                exeFatherNode(linkNode);
                        } else {
                                exeNextNode();
                        }
                } else { //如果是非容器型节点，直接执行
                        exeChildNode(linkNode);
                }
        }

        private void exeFatherNode(final LinkNode linkNode) {
                // 首先判断 ViewGroupNode 是否可以执行.
                NodeRunType nodeType = linkNode.getNodeType();
                switch (nodeType) {
                        case REPEAT_UNIT: // 重复直到触摸,重复执行直到
                                break;
                        case REPEAT_COUNT: // 重复X次, 调用function(1次)
                                break;
                        case REPEAT_CYCLE: // 一直重复
                                break;
                        case CONDITION: //如果那么, 如果那么否则
                                break;
                        case FACE: //如果人脸
                                exeHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                                mockFaceInfo(linkNode);
                                        }
                                }, 2000);
                                return;
                }
                VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
                parseFatherNode(new LinkNode(nextChildNode, this));
        }

        private void mockFaceInfo(LinkNode linkNode) {
                String temp[] = {"已注册", "未注册"};
                int index = (int) (Math.random() * temp.length);
                if (NodeParams.Logic.REGISTER.equals(linkNode.getFaceArgs())) {// if
                        if (NodeParams.Logic.REGISTER.equals(temp[index])) {
                                VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
                                parseFatherNode(new LinkNode(nextChildNode, this));
                        } else {
                                linkNode.updateNodeDataBaseListFromElse();
                                VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
                                parseFatherNode(new LinkNode(nextChildNode, this));
                        }
                } else { //else
                        if (NodeParams.Logic.UNREGISTER.equals(temp[index])) {
                                VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
                                parseFatherNode(new LinkNode(nextChildNode, this));
                        } else {
                                linkNode.updateNodeDataBaseListFromElse();
                                VpJsonBean.NodeDataBase nextChildNode = linkNode.getNextChildNode();
                                parseFatherNode(new LinkNode(nextChildNode, this));
                        }
                }
        }

        private void exeChildNode(LinkNode node) {
                currentExeLinkNode = node;
                VpJsonBean.NodeDataBase nodeDataBase = node.getNextChildNode();
                bluToothMgr.sendMsgToPad(TaskJsonType.Sender.NODE_MOTION_REQUEST, nodeDataBase.NodeID, nodeDataBase.Event);
                dispatchNode(nodeDataBase);
                mockNext();
        }

        @Override
        public void haveHandler(boolean isFatherNode, LinkNode linkNode) {
                exeHandler.removeCallbacksAndMessages(null);
                Log.e(TAG, "haveHandler: =========handler by isFatherNode = " + isFatherNode);
                if (isFatherNode) {
                        currentFatherLinkNode = linkNode;
                        exeNextNode();
                } else {
                        exeNextNode();
                }
        }

        private void dispatchRobotMsg(RobotMsgType robotMsgType, Bundle bundle) {
                Log.e(TAG, "dispatchRobotMsg: ========== robotMsgType=" + robotMsgType);
                if (firstFatherNode != null) {
                        boolean result = firstFatherNode.handlerMsg(robotMsgType, null);
                        if (result) { //如果被处理了，就会回调

                        } else {
                                if (currentExeLinkNode != null) {
                                        currentExeLinkNode.handlerMsg(robotMsgType, null);
                                }
                        }
                } else {
                        if (currentExeLinkNode != null) {
                                currentExeLinkNode.handlerMsg(robotMsgType, null);
                        }
                }
        }

        private void mockNext() {
                //模拟下一个节点执行的条件到了, 具体什么时候执行下一个？？？
                // exeHandler.sendEmptyMessageDelayed(NEXT, 2000);
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
                                exeMindNode(node);
                                break;
                        case NodeJsonType.PERCEPTION:
                                exePerceptionNode(node);
                                break;
                }
        }

        private HeartSetBean heartSetBean;

        private void exeMindNode(VpJsonBean.NodeDataBase node) {
                switch (node.PrefabName) {
                        case NodeJsonType.Mind.MindPrefab_Set:
                                heartSetBean = HeartSetBean.getBean(node);
                                Long timer = heartSetBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(heartSetBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Mind.MindPrefab_UseChange:
                                HeartChangeBean heartChangeBean = HeartChangeBean.getBean(node);
                                heartChangeBean.setHeartSetBean(heartSetBean);
                                timer = heartChangeBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(heartChangeBean.getRobotMsgTypeList());
                                break;
                }
        }

        private void exePerceptionNode(VpJsonBean.NodeDataBase node) {
                switch (node.PrefabName) {
                        case NodeJsonType.Perception.PerceptionPrefab_LookAt:
                        case NodeJsonType.Perception.PerceptionPrefab_WaitTouch:
                                String event = node.Event;
                                if (NodeEvent.Perception.WAIT_PHONE.equals(event)) {
                                        WaitPhoneBean waitPhoneBean = WaitPhoneBean.getBean(node);
                                        waitPhoneBean.exeNode();
                                        currentExeLinkNode.setRobotMsgType(waitPhoneBean.getRobotMsgTypeList());

                                } else if (NodeEvent.Perception.WAIT_TOUCH.equals(event)) {
                                        WaitTouchBean waitTouchBean = WaitTouchBean.getBean(node);
                                        waitTouchBean.exeNode();
                                        currentExeLinkNode.setRobotMsgType(waitTouchBean.getRobotMsgTypeList());
                                } else {
                                        PercetionNormalBean percetionNormalBean = PercetionNormalBean.getBean(node);
                                        long timer = percetionNormalBean.exeNode();

                                        exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                        currentExeLinkNode.setRobotMsgType(percetionNormalBean.getRobotMsgTypeList());
                                }
                                break;
                        case NodeJsonType.Perception.PerceptionPrefab_When:

                                break;
                }
        }

        private void clean() {
                if (currentFatherLinkNode != null) {
                        currentFatherLinkNode = null;
                }
                if (firstFatherNode != null) {
                        firstFatherNode.cleanCurrentAndChild();
                }
                heartSetBean = null;
                rootNodeListYIndex = 0;
                rootNodeLists.clear();
                bluToothMgr.sendTaskExeEndMsg();
                firstFatherNode = null;
                currentExeLinkNode = null;
                exeHandler.removeCallbacksAndMessages(null);
        }


        private void exeLogicNode(VpJsonBean.NodeDataBase nodeData) {
                switch (nodeData.PrefabName) {
                        case NodeJsonType.Logic.LogicPrefab_CallFunction:
                                if (NodeEvent.Logic.GO_TO_MAP.equals(nodeData.Event)) {
                                        MapBean mapBean = MapBean.getBean(nodeData);
                                        mapBean.exeNode();
                                        currentExeLinkNode.setRobotMsgType(mapBean.getRobotMsgTypeList());

                                } else if (NodeEvent.Logic.CREATE_FUNCTION.equals(nodeData.Event)) {// 创建函数

                                }
                                break;
                        case NodeJsonType.Logic.LogicPrefab_MakeFunction://调用函数
                                break;
                        case NodeJsonType.Logic.LogicPrefab_WaitSecond://等待几秒
                                LogicWaitSecondBean logicWaitSecondBean = LogicWaitSecondBean.getBean(nodeData);
                                Long timer = logicWaitSecondBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(logicWaitSecondBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Logic.LogicPrefab_://打开盛开互动人脸识别
                                FaceRecogBean faceRecogBean = FaceRecogBean.getBean(nodeData);
                                timer = faceRecogBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(faceRecogBean.getRobotMsgTypeList());
                                break;
                }
        }

        private void exeEarNode(VpJsonBean.NodeDataBase nodeData) {
                switch (nodeData.PrefabName) {
                        case NodeJsonType.Ears.EarsPrefab_: //要去云端获取答案然后才能执行下一个节点
                                EarBean earBean = EarBean.getBean(nodeData);
                                long timer = earBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(earBean.getRobotMsgTypeList());
                                break;
                }
        }

        private void exeEyesNode(VpJsonBean.NodeDataBase nodeData) {
                switch (nodeData.PrefabName) {
                        case NodeJsonType.Eyes.EyesPrefab_: // 看前方
                        case NodeJsonType.Eyes.EyesPrefab_LookAngle: //向左看 , 角度
                                EyeLookAngle eyeLookAngle = EyeLookAngle.getBean(nodeData);
                                eyeLookAngle.exeNode();
                                long timer = eyeLookAngle.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(eyeLookAngle.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Eyes.EyesPrefab_Feelings:
                                EyeFeelingBean eyeFeelingBean = EyeFeelingBean.getBean(nodeData);
                                eyeFeelingBean.exeNode();
                                timer = eyeFeelingBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(eyeFeelingBean.getRobotMsgTypeList());
                                break;
                }
        }

        private void exeLanguageNode(VpJsonBean.NodeDataBase node) {
                switch (node.PrefabName) {
                        case NodeJsonType.Language.LanguagePrefab_Speak:// tts
                                TtsBean ttsBean = TtsBean.getBean(node);
                                long timer = ttsBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(ttsBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Language.LanguagePrefab_:// music
                                MusicBean musicBean = MusicBean.getBean(node);
                                timer = musicBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(musicBean.getRobotMsgTypeList());
                                break;
                }
        }

        private void exeCombineActionNode(VpJsonBean.NodeDataBase node) {
                CombineBean combineBean = CombineBean.getBean(node);
                long timer = combineBean.exeNode();
                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                currentExeLinkNode.setRobotMsgType(combineBean.getRobotMsgTypeList());
        }

        /**
         * basic 节点的执行
         *
         * @param nodeData
         */
        private void exeBasicNode(VpJsonBean.NodeDataBase nodeData) {
                switch (nodeData.PrefabName) {
                        case NodeJsonType.Basic.BasicActionPrefab_GoSpeedSeconed:
                                SpeedTimeBean speedTimeBean = SpeedTimeBean.getBean(nodeData);
                                long timer = speedTimeBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(speedTimeBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Basic.BasicActionPrefab_BasicJoint:
                                JointBean jointBean = JointBean.getBean(nodeData);
                                timer = jointBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(jointBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Basic.BasicActionPrefab_TrunAngle:
                                TurnAngleBean turnAngleBean = TurnAngleBean.getBean(nodeData);
                                timer = turnAngleBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(turnAngleBean.getRobotMsgTypeList());
                                break;
                        case NodeJsonType.Basic.BasicActionPrefab_Second:
                                StopMoveBean stopMoveBean = StopMoveBean.getBean(nodeData);
                                timer = stopMoveBean.exeNode();
                                exeHandler.sendEmptyMessageDelayed(Timer, timer);
                                currentExeLinkNode.setRobotMsgType(stopMoveBean.getRobotMsgTypeList());
                                break;
                }
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                sensorMgr.deleteObserver(this);
                bluToothMgr.destroy();
        }
}
