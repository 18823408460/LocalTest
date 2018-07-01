package com.unisrobot.robothead.visualedit.model;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.interfaces.IRobotMsgHandler;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendCdata;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.NodeRunType;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;
import com.unisrobot.robothead.visualedit.type.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 * <p>
 * 要知道 节点类型的 作用是：
 * 1. 对于普通节点：是要知道它什么执行结束
 * 2. 对于容器节点:
 * > 如果是条件单次型，需要根据类型去 执行if 还是 else
 * > 如果是条件多次型， 需要根据类型对应的条件，知道它什么时候该结束
 */

public class LinkNode implements IRobotMsgHandler {
    private static final String TAG = LinkNode.class.getSimpleName();
    private int currentChildYIndex = 0;  // y 方向的 链表索引
    private NodeRunType nodeType;
    private List<VpJsonBean.NodeDataBase> nodeDataBaseList;// 当前LinkNode中包含的 包含型Node
    private List<VpJsonBean.NodeDataBase> nodeDataBaseListElse; //暂存，判断条件后，付给nodeDataBaseList
    private AppendCdata appendCData;
    private String event;
    private boolean ContainerNode = false;
    private LinkNode fatherNode; //前一个node(父 node),这是为了在嵌套多层是时，回退使用
    private LinkNode childContainerNode; //当前容器节点 的 子节点，这是为了收到消息时，消息一次派发用, 消息之派发到容器节点和
    //如果是条件节点,或是重复性判断的节点，需要保存这个值，每次去进行判断；
    private VpJsonBean.NodeDataBase nodeDataBaseCondition;

    // 当前正在执行的节点..

    public LinkNode(VpJsonBean.NodeDataBase nodeDataBase) {
        List<VpJsonBean.NodeDataBase> list = new ArrayList<>();
        list.add(nodeDataBase);
        event = nodeDataBase.Event;
        addData(list);
    }

    private void addData(List<VpJsonBean.NodeDataBase> list) {
        VpJsonBean.NodeDataBase nodeDataBase = list.get(currentChildYIndex);
        nodeType = TypeUtil.getRunType(nodeDataBase);
        boolean isViewGroupNode = TypeUtil.IsViewGroupNode(nodeDataBase);
        if (isViewGroupNode) { // 这里会导致节点丢失
            this.ContainerNode = true;
            List<VpJsonBean.NodeDataBase> actions = nodeDataBase.Actions;
            if (actions != null && actions.size() > 0) { //这个是包含的内容(和 ifelse里面包含的区分)
                this.nodeDataBaseList = actions;
            }
            VpJsonBean.SwitchBean aSwitch = nodeDataBase.Switch;
            if (aSwitch != null) {
                nodeDataBaseList = aSwitch.Do;  // if
                nodeDataBaseListElse = aSwitch.Else; //else
            }
        } else {
            this.ContainerNode = false;
            this.nodeDataBaseList = list;
        }
        if (NodeRunType.CONDITION.equals(nodeType)) {
            nodeDataBaseCondition = AppendUtil.getAppendCData(nodeDataBase);
        }
    }

    public boolean hasNextChildNode() { // 这里不仅仅只判断 索引，还要优先根据nodeType来判断
        if (currentChildYIndex < nodeDataBaseList.size()) {
            return true;
        }
        return false;
    }

    public VpJsonBean.NodeDataBase getNextChildNode() {
        VpJsonBean.NodeDataBase nodeDataBase = nodeDataBaseList.get(currentChildYIndex++);
        return nodeDataBase;
    }


    public LinkNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(LinkNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    public void setChildContainerNode(LinkNode childContainerNode) {
        this.childContainerNode = childContainerNode;
    }

    public boolean isContainerNode() {
        return ContainerNode;
    }

    public String getEvent() {
        return event;
    }

    public void startExe() {

    }

    public void stop() {
    }

    @Override
    public boolean handlerMsg(RobotMsgType robotMsgType, Bundle bundle) {
        return false;
    }
}
