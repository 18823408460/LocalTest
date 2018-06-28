package com.unisrobot.robothead.visualedit.model;

import android.util.Log;

import com.unisrobot.robothead.visualedit.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.AppendCData;
import com.unisrobot.robothead.visualedit.nodebean.AppendUtil;
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
        private List<VpJsonBean.NodeDataBase> nodeDataBaseList;
        private List<VpJsonBean.NodeDataBase> nodeDataBaseListElse;
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

        public AppendCData getAppendCData() {
                return appendCData;
        }

        public boolean isContanirNode() {
                return ContanirNode;
        }


        public NodeRunType getNodeType() {
                return nodeType;
        }

        public List<VpJsonBean.NodeDataBase> getNodeDataBaseList() {
                return nodeDataBaseList;
        }

        public List<VpJsonBean.NodeDataBase> getNodeDataBaseListElse() {
                return nodeDataBaseListElse;
        }

        public void setCurrentYIndex(int currentYIndex) {
                this.currentYIndex = currentYIndex;
        }

        public int getCurrentYIndex() {
                return currentYIndex;
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
