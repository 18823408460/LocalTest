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
import com.unisrobot.robothead.visualedit.nodebean.AppendCData;
import com.unisrobot.robothead.visualedit.nodebean.basic.JointBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.SpeedTimeBean;
import com.unisrobot.robothead.visualedit.nodebean.basic.TurnAngleBean;
import com.unisrobot.robothead.visualedit.nodebean.combineaction.CombineBean;
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
    private LinkedList<LinkNode> linkNodeLinkedListX = new LinkedList<>();
    private LinkNode currentLinkNode;
    private LinkNode linkFatherNode;
    private ExeHandler exeHandler;
    private int linkNodeListXIndex = -1;  // y 方向的 链表索引

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

    private void exeNextNode() {
        boolean hasNext = currentLinkNode.hasNext();
        if (hasNext) {
            LinkNode linkNode = new LinkNode(currentLinkNode.getNextData());
            contachLinkNode(linkNode);
        } else { // 同一个Node中非包含型 Node 的执行
            //子节点个数默认是0（-1是为了和list索引保持一致）,如果是1个，不需要回退，剩下的 交给 linkFatherNode处理
            // 如果 2个以上，则需要回退..
            if (linkNodeListXIndex > 0) { // 如果 Y 方向上 还有Node，往下执行  XXXXXXX这里回退有问题？？
                linkNodeListXIndex--;
                currentLinkNode = linkNodeLinkedListX.get(linkNodeListXIndex);
                hasNext = currentLinkNode.hasNext();
                if (hasNext) {
                    VpJsonBean.NodeDataBase nodeDataBase = currentLinkNode.getNextData();
                    LinkNode linkNode = new LinkNode(nodeDataBase);
                    contachLinkNode(linkNode);
                } else { // 继续回退。往前执行上一个节点 剩下的
                    exeNextNode();
                }
            } else { // FatherNode Y 方向上的执行
                linkNodeListXIndex = -1;
                hasNext = linkFatherNode.hasNext();
                if (hasNext) {
                    VpJsonBean.NodeDataBase nodeDataBase = linkFatherNode.getNextData();
                    LinkNode linkNode = new LinkNode(nodeDataBase);
                    contachLinkNode(linkNode);
                } else {
                    Log.e(TAG, "exeNextNode: **********************end");
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
            linkFatherNode = new LinkNode(tasks);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    VpJsonBean.NodeDataBase nextData = linkFatherNode.getNextData();
                    currentLinkNode = new LinkNode(nextData);
                    contachLinkNode(currentLinkNode);
                }
            });
        }
    }

    private void contachLinkNode(LinkNode linkNode) {
        if (linkNode.isContanirNode()) { //如果是容器节点
            AppendCData appendCData = linkNode.getAppendCData();
            if (appendCData != null) { //如果是条件型容器节点
                Log.e(TAG, "contachLinkNode: is conditon view group node");
                if (appendCData.logic) { //如果条件满足,执行if==============================容器节点判断位置
                    contachLinkNode(getLinkNode(linkNode, true));
                } else {
                    contachLinkNode(getLinkNode(linkNode, false));
                }
            } else { // 如果直接是顺序执行的容器节点
                Log.e(TAG, "contachLinkNode: is  view group node");
                contachLinkNode(getLinkNode(linkNode, true));
            }
        } else {
            exeLinkNode();
        }
    }

    @NonNull
    private LinkNode getLinkNode(LinkNode fatherNode, boolean isIf) {
        List<VpJsonBean.NodeDataBase> nodeDataBaseList;
        if (isIf) {
            nodeDataBaseList = fatherNode.getNodeDataBaseList();
        } else {
            nodeDataBaseList = fatherNode.getNodeDataBaseListElse();
        }
        fatherNode.updateNodeDataBaseList(nodeDataBaseList);
        LinkNode childNode = new LinkNode(nodeDataBaseList);
        linkNodeLinkedListX.addLast(childNode);
        currentLinkNode = childNode;
        linkNodeListXIndex++;
        return childNode;
    }

    private void exeLinkNode() {
        VpJsonBean.NodeDataBase nodeDataBase = currentLinkNode.getNextData();
        dispatchNode(nodeDataBase);
        exeHandler.sendEmptyMessageDelayed(NEXT, 1000);
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
        if (currentLinkNode != null) {
            currentLinkNode.stop();
            currentLinkNode = null;
        }
        linkNodeListXIndex = -1;
        linkNodeLinkedListX.clear();
        linkFatherNode = null;
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
