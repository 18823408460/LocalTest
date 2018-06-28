package com.unisrobot.robothead.visualedit.model;

import android.util.Log;

import com.unisrobot.robothead.visualedit.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.AppendCData;
import com.unisrobot.robothead.visualedit.nodebean.AppendUtil;
import com.unisrobot.robothead.visualedit.type.NodeJsonType;
import com.unisrobot.robothead.visualedit.type.NodeRunType;
import com.unisrobot.robothead.visualedit.type.RunTypeUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class LinkNode {
        private static final String TAG = LinkNode.class.getSimpleName();
        //每次执行一个nodeDataBaseList中的一个节点后，currentIndex++ ;
        private int currentIndex = 0;
        private NodeRunType nodeType;
        private List<VpJsonBean.NodeDataBase> nodeDataBaseList;
        private List<VpJsonBean.NodeDataBase> nodeDataBaseListElse;
        private AppendCData appendCData;
        private boolean ContanirNode = false;

        public LinkNode(List<VpJsonBean.NodeDataBase> list) {
                VpJsonBean.NodeDataBase nodeDataBase = list.get(currentIndex);
                nodeType = RunTypeUtil.getRunType(nodeDataBase);
                Log.e(TAG, "LinkNode: " + nodeDataBase.PrefabName + "   nodeType=" + nodeType);
                boolean isViewGroupNode = RunTypeUtil.IsViewGroupNode(nodeDataBase);
                if (isViewGroupNode) {
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


        public int getCurrentIndex() {
                return currentIndex;
        }

        public void setCurrentIndex(int currentIndex) {
                this.currentIndex = currentIndex;
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

        @Override
        public String toString() {
                return "LinkNode{" +
                        "currentIndex=" + currentIndex +
                        ", nodeType=" + nodeType +
                        '}';
        }

        public void startExe() {
        }

        public void stop() {

        }
}
