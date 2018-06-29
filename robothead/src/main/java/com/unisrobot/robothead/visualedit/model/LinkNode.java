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
        private int currentChildYIndex = 0;  // y 方向的 链表索引
        private int currentFatherYIndex = 0;  // y 方向的 链表索引
        private NodeRunType nodeType;
        private List<VpJsonBean.NodeDataBase> nodeDataBaseList;// 当前LinkNode中包含的 包含型Node
        private List<VpJsonBean.NodeDataBase> nodeDataBaseFatherList;// 当前LinkNode中包含的所有的node
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
                VpJsonBean.NodeDataBase nodeDataBase = list.get(currentChildYIndex);
                nodeType = RunTypeUtil.getRunType(nodeDataBase);
                Log.e(TAG, "LinkNode: " + nodeDataBase.PrefabName + "   nodeType=" + nodeType);
                boolean isViewGroupNode = RunTypeUtil.IsViewGroupNode(nodeDataBase);
                if (isViewGroupNode) { // 这里会导致节点丢失
                        Log.e(TAG, "LinkNode: isViewGroupNode");
                        this.ContanirNode = true;
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
                        Log.e(TAG, "LinkNode: not   ViewGroupNode");
                        this.ContanirNode = false;
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

        public boolean hasNextFatherNode() { // 这里不仅仅只判断 索引，还要优先根据nodeType来判断
                if (isContanirNode()){
                        if (currentFatherYIndex < nodeDataBaseFatherList.size()) {
                                return true;
                        }
                }
                return false;
        }

        public VpJsonBean.NodeDataBase getNextChildNode() {
                VpJsonBean.NodeDataBase nodeDataBase = nodeDataBaseList.get(currentChildYIndex);
                currentChildYIndex += 1;
                return nodeDataBase;
        }

        public VpJsonBean.NodeDataBase getNextFatherNode() {
                Log.e(TAG, "getNextFatherNode: currentFatherYIndex=" + currentFatherYIndex );
                VpJsonBean.NodeDataBase nodeDataBase = nodeDataBaseFatherList.get(currentFatherYIndex);
                currentFatherYIndex += 1;
                Log.e(TAG, "getNextFatherNode: currentFatherYIndex="+
                        currentFatherYIndex + "   data"+ nodeDataBase);
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
                        "currentChildYIndex=" + currentChildYIndex +
                        ", nodeType=" + nodeType +
                        ", nodeDataBaseList=" + nodeDataBaseList +
                        ", nodeDataBaseListElse=" + nodeDataBaseListElse +
                        ", appendCData=" + appendCData +
                        ", ContanirNode=" + ContanirNode +
                        '}';
        }
}
