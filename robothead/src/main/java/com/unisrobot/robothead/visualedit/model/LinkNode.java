package com.unisrobot.robothead.visualedit.model;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.interfaces.IRobotMsgHandler;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendCData;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.NodeRunType;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;
import com.unisrobot.robothead.visualedit.type.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class LinkNode implements IRobotMsgHandler{
    private static final String TAG = LinkNode.class.getSimpleName();
    private int currentChildYIndex = 0;  // y 方向的 链表索引
    private NodeRunType nodeType;
    private List<VpJsonBean.NodeDataBase> nodeDataBaseList;// 当前LinkNode中包含的 包含型Node
    private List<VpJsonBean.NodeDataBase> nodeDataBaseListElse; //暂存，判断条件后，付给nodeDataBaseList
    private AppendCData appendCData;
    private boolean ContainerNode = false;
    private String Event;
    private LinkNode fatherNode; //前一个node(父 node),这是为了在嵌套多层是时，回退使用
    private LinkNode childContainerNode; //当前容器节点 的 子节点，这是为了收到消息时，消息一次派发用, 消息之派发到容器节点和
    // 当前正在执行的节点..

    public LinkNode(VpJsonBean.NodeDataBase nodeDataBase) {
        Event = nodeDataBase.Event;
        List<VpJsonBean.NodeDataBase> list = new ArrayList<>();
        list.add(nodeDataBase);
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
            appendCData = AppendUtil.getAppendCData(nodeDataBase);
        }
    }

    public boolean hasNextChildNode() { // 这里不仅仅只判断 索引，还要优先根据nodeType来判断
        if (currentChildYIndex < nodeDataBaseList.size()) {
            return true;
        }
        return false;
    }

    public VpJsonBean.NodeDataBase getNextChildNode() {
        VpJsonBean.NodeDataBase nodeDataBase = nodeDataBaseList.get(currentChildYIndex);
        currentChildYIndex += 1;
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

    public LinkNode getChildContainerNode() {
        return childContainerNode;
    }

    public boolean isContainerNode() {
        return ContainerNode;
    }

    public void startExe() {
    }

    public void stop() {
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "currentChildYIndex=" + currentChildYIndex +
                ", nodeType=" + nodeType +
                ", event=" + Event +
                '}';
    }

    @Override
    public boolean handlerMsg(RobotMsgType robotMsgType, Bundle bundle) {
        return false;
    }
}
