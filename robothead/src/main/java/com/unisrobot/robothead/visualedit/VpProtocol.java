package com.unisrobot.robothead.visualedit;

/**
 * 发送可视化蓝牙消息类
 */
public class VpProtocol {
    public int nodeID;
    public int pN;
    public String args;

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getPN() {
        return pN;
    }

    public void setPN(int pN) {
        this.pN = pN;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "VpProtocol [NodeID=" + nodeID + ", PN=" + pN
                + ", Args=" + args + "]";
    }

}