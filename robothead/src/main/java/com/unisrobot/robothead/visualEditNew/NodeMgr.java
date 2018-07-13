package com.unisrobot.robothead.visualEditNew;

import com.unisrobot.robothead.visualedit.interfaces.IMsgCanHandler;
import com.unisrobot.robothead.visualedit.model.LinkNode;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by WEI on 2018/7/3.
 */

public class NodeMgr implements IMsgCanHandler {
    private int rootNodeListYIndex = 0;  // y 方向的 链表索引
    private LinkedList<VpJsonBean.NodeDataBase> rootNodeLists = new LinkedList<>();
    private FatherNode headFatherNode;
    private FatherNode currentFatherNode;

    /**
     * 执行可视化编程 Task
     *
     * @param tasks
     */
    private void handlerVpTaskThread(final List<VpJsonBean.NodeDataBase> tasks) {
        if (tasks != null && tasks.size() > 0) {
            clean();
            rootNodeLists.addAll(tasks);
            exeNextRootNode();
        }
    }

    private void exeNextRootNode() {
        if (rootNodeLists != null && rootNodeLists.size() > 0) {
            if (rootNodeListYIndex < rootNodeLists.size()) {
                VpJsonBean.NodeDataBase nodeDataBase = rootNodeLists.get(rootNodeListYIndex++);
                parseNodeDataBase(nodeDataBase);
            }
        }
    }

    private void parseNodeDataBase(VpJsonBean.NodeDataBase nodeDataBase) {
        if (isFatherNode(nodeDataBase)) {
            FatherNode fatherNode = getFatherNode(nodeDataBase, this);
            if (headFatherNode == null) {
                headFatherNode = currentFatherNode = fatherNode;
            } else {
                currentFatherNode.NextFatherNode = fatherNode;
                fatherNode.PrevFathNode = currentFatherNode;
                currentFatherNode = fatherNode;
            }
            fatherNode.startExe();
        } else {
            ChildNode childNode = getChildNode(nodeDataBase, this);
            childNode.startExe();
        }
    }

    private ChildNode getChildNode(VpJsonBean.NodeDataBase nodeDataBase, IMsgCanHandler iMsgCanHandler) {
        return null;
    }

    private FatherNode getFatherNode(VpJsonBean.NodeDataBase nodeDataBase, IMsgCanHandler iMsgCanHandler) {
        return null;
    }

    private boolean isFatherNode(VpJsonBean.NodeDataBase nodeDataBase) {
        return false;
    }

    private void clean() {

    }

    @Override
    public void haveHandler(boolean isFatherNode, LinkNode linkNode) {

    }
}
