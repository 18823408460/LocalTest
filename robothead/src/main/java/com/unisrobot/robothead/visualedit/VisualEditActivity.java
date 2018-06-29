package com.unisrobot.robothead.visualedit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unisrobot.robothead.bluetooth.BluToothMgr;
import com.unisrobot.robothead.bluetooth.IBluetoothLisenter;
import com.unisrobot.robothead.visualedit.model.LinkNode;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendCData;
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
 */

public class VisualEditActivity extends Activity {
        private static final int NEXT = 1;
        private static final String TAG = VisualEditActivity.class.getSimpleName();
        private BluToothMgr bluToothMgr;
        private LinkedList<VpJsonBean.NodeDataBase> rootNodeLists = new LinkedList<>();
        private LinkNode currentFatherLinkNode;
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
                if (currentFatherLinkNode != null){
                        boolean hasNext = currentFatherLinkNode.hasNextChildNode();
                        if (hasNext) {
                                Log.e(TAG, "exeNextNode: >>>>>>>>>>>>>>>> next child.......... ");
                                VpJsonBean.NodeDataBase nextData = currentFatherLinkNode.getNextChildNode();
                                contachLinkNode(new LinkNode(nextData));
                        }else {
                                LinkNode prevFatherNode = currentFatherLinkNode.getFatherNode();
                                if (prevFatherNode != null){
                                        currentFatherLinkNode = prevFatherNode ;
                                        exeNextNode();
                                }else {
                                        exeNextRootNode();
                                }
                        }
                }else {
                        exeNextRootNode();
                }
        }

        private void exeNextRootNode() {
                if (rootNodeListYIndex < rootNodeLists.size()) {
                        VpJsonBean.NodeDataBase nodeDataBase = rootNodeLists.get(rootNodeListYIndex++);
                        contachLinkNode( new LinkNode(nodeDataBase));
                } else {
                        Log.e(TAG, "exeNextNode: **********************end");
                }
        }

        private void contachLinkNode(LinkNode linkNode) {
                if (linkNode.isContainerNode()) { //如果是容器节点,看是不是 条件型容器节点
                        if (currentFatherLinkNode == null){
                                currentFatherLinkNode = linkNode ;
                        }else {
                                linkNode.setFatherNode(currentFatherLinkNode);
                                currentFatherLinkNode = linkNode ;
                        }
                        AppendCData appendCData = linkNode.getAppendCData();
                        if (appendCData != null) { //如果是条件型容器节点
                                Log.e(TAG, "contachLinkNode: is conditon view group node=");
                                if (appendCData.logic) { //如果条件满足,执行if==============================容器节点判断位置
                                        contachLinkNode(getContainerFirstNode(linkNode, true));
                                } else {
                                        contachLinkNode(getContainerFirstNode(linkNode, false));
                                }
                        } else { // 如果直接是顺序执行的容器节点
                                Log.e(TAG, "contachLinkNode: is  view group node");
                                contachLinkNode(getContainerFirstNode(linkNode, true));
                        }
                } else { //如果是非容器型节点，直接执行
                        exeLinkNode(linkNode);
                }
        }

        @NonNull
        private LinkNode getContainerFirstNode(LinkNode fatherNode, boolean isIf) {
                if (!isIf) {
                        fatherNode.updateNodeDataBaseList(fatherNode.getNodeDataBaseListElse());
                }
                VpJsonBean.NodeDataBase nextChildNode = fatherNode.getNextChildNode();
                return new LinkNode(nextChildNode);
        }

        private void exeLinkNode(LinkNode node) {
                VpJsonBean.NodeDataBase nodeDataBase = node.getNextChildNode();
                dispatchNode(nodeDataBase);
                mockNext();
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
