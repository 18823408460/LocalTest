package com.unisrobot.robothead.visualedit.model;

import android.util.Log;

import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendCData;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.type.NodeRunType;
import com.unisrobot.robothead.visualedit.type.RunTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class LinkNode {
    private static final String TAG = LinkNode.class.getSimpleName();
    //每次执行一个nodeDataBaseList中的一个节点后，currentIndex++ ;
    private int currentYIndex = 0;  // y 方向的 链表索引
    private NodeRunType nodeType;
    private List<VpJsonBean.NodeDataBase> nodeDataBaseList;// 可执行的List
    private List<VpJsonBean.NodeDataBase> nodeDataBaseListElse; //暂存，判断条件后，付给nodeDataBaseList
    private AppendCData appendCData;
    private boolean ContanirNode = false;

    public LinkNode(VpJsonBean.NodeDataBase nodeDataBase) {
        List<VpJsonBean.NodeDataBase> list = new ArrayList<>();
        list.add(nodeDataBase);
        addData(list);
    }

    public LinkNode(List<VpJsonBean.NodeDataBase> list) {
        addData(list);
    }

    private void addData(List<VpJsonBean.NodeDataBase> list) {
        VpJsonBean.NodeDataBase nodeDataBase = list.get(currentYIndex);
        nodeType = RunTypeUtil.getRunType(nodeDataBase);
        Log.e(TAG, "LinkNode: " + nodeDataBase.PrefabName + "   nodeType=" + nodeType);
        boolean isViewGroupNode = RunTypeUtil.IsViewGroupNode(nodeDataBase);
        if (isViewGroupNode) {
            Log.e(TAG, "LinkNode: isViewGroupNode");
            this.ContanirNode = true;
            List<VpJsonBean.NodeDataBase> actions = nodeDataBase.Actions;
            if (actions != null && actions.size() > 0) {
                nodeDataBaseList = actions;
            }
            VpJsonBean.SwitchBean aSwitch = nodeDataBase.Switch;
            if (aSwitch != null) {
                nodeDataBaseList = aSwitch.Do;  // if
                nodeDataBaseListElse = aSwitch.Else; //else
            }

        } else {
            Log.e(TAG, "LinkNode: not   ViewGroupNode");
            this.ContanirNode = false;
            this.nodeDataBaseList = list;
        }
        if (NodeRunType.CONDITION.equals(nodeType)) {
            appendCData = AppendUtil.getAppendCData(nodeDataBase);
        }
    }

    public boolean hasNext() { // 这里不仅仅只判断 索引，还要优先根据nodeType来判断
        if (currentYIndex < nodeDataBaseList.size()) {
            return true;
        }
        return false;
    }

    public VpJsonBean.NodeDataBase getNextData() {
        VpJsonBean.NodeDataBase nodeDataBase = nodeDataBaseList.get(currentYIndex);
        currentYIndex++;
        return nodeDataBase;
    }

    public AppendCData getAppendCData() {
        return appendCData;
    }

    public boolean isContanirNode() {
        return ContanirNode;
    }

    public List<VpJsonBean.NodeDataBase> getNodeDataBaseList() {
        return nodeDataBaseList;
    }

    public void updateNodeDataBaseList(List<VpJsonBean.NodeDataBase> dataBases) {
        this.nodeDataBaseList = dataBases;
    }

    public List<VpJsonBean.NodeDataBase> getNodeDataBaseListElse() {
        return nodeDataBaseListElse;
    }

    public void startExe() {
    }

    public void stop() {

    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "currentYIndex=" + currentYIndex +
                ", nodeType=" + nodeType +
                ", nodeDataBaseList=" + nodeDataBaseList +
                ", nodeDataBaseListElse=" + nodeDataBaseListElse +
                ", appendCData=" + appendCData +
                ", ContanirNode=" + ContanirNode +
                '}';
    }
}
