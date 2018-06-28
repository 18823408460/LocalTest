package com.unisrobot.robothead.visualedit.nodebean.basic;

import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeParams;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.bean.Joint;

/**
 * Created by Administrator on 2018/6/27.
 */

public class JointBean {
    private Joint[] joints = null;
    private long time;

    public void exeNode() {
        if (joints != null) {
            for (Joint joint : joints) {
                //SerialPortMgr.getInstance().sendSingleJointCmd((byte) joint.getIndex(), joint.getRotate(), joint.getTime());
            }
        }
    }

    public static JointBean getBean(VpJsonBean.NodeDataBase nodeData) {
        String joint = nodeData.Args.get(0).Content;
        String oration = nodeData.Args.get(1).Content;
        String angle = nodeData.Args.get(2).Content;
        JointBean jointBean = new JointBean();
        Joint[] joints = null;
        switch (nodeData.Event) {
            case NodeEvent.Basic.SET_JIAN_JOINT:
                joints = getShoulderJoints(joint, oration, angle);
                break;
            case NodeEvent.Basic.SET_ZHOU_JOINT:
                joints = getElbowJoints(joint, oration, angle);
                break;
            case NodeEvent.Basic.SET_WAN_JOINT:
                joints = getWristJoints(joint, oration, angle);
                break;
        }
        jointBean.joints = joints;
        long time = 1000;
        if (joints != null) {
            time += 1000;
        }
        jointBean.time = time;
        return jointBean;
    }

    private static Joint[] getWristJoints(String content, String oration, String angle) {
        Joint[] joints = null;
        if (NodeParams.Basic.GO_ALL.equals(content)) {
            joints = new Joint[2];
            joints[0] = new Joint();
            joints[1] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(6).setRotate((short) integer).setTime((short) 20000);
                    joints[1].setIndex(11).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(6).setRotate((short) -integer).setTime((short) 20000);
                    joints[1].setIndex(11).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        } else if ("左腕".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(6).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(6).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        } else if ("右腕".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(11).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(11).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        }
        return joints;
    }

    private static Joint[] getElbowJoints(String content, String oration, String angle) {
        Joint[] joints = null;
        if (NodeParams.Basic.GO_ALL.equals(content)) {
            joints = new Joint[2];
            joints[0] = new Joint();
            joints[1] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(4).setRotate((short) integer).setTime((short) 20000);
                    joints[1].setIndex(9).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(4).setRotate((short) -integer).setTime((short) 20000);
                    joints[1].setIndex(9).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(5).setRotate((short) integer).setTime((short) 20000);
                    joints[1].setIndex(10).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(5).setRotate((short) -integer).setTime((short) 20000);
                    joints[1].setIndex(10).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        } else if ("左肘".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(4).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(4).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(5).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(5).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        } else if ("右肘".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向左":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(9).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向右":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(9).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(10).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(10).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        }
        return joints;
    }


    private static Joint[] getShoulderJoints(String content, String oration, String angle) {
        Joint[] joints = null;
        if (NodeParams.Basic.GO_ALL.equals(content)) {
            joints = new Joint[2];
            joints[0] = new Joint();
            joints[1] = new Joint();
            switch (oration) {
                case "向前":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(2).setRotate((short) integer).setTime((short) 20000);
                    joints[1].setIndex(7).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向后":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(2).setRotate((short) -integer).setTime((short) 20000);
                    joints[1].setIndex(7).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(3).setRotate((short) integer).setTime((short) 20000);
                    joints[1].setIndex(8).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(3).setRotate((short) -integer).setTime((short) 20000);
                    joints[1].setIndex(8).setRotate((short) -integer).setTime((short) 20000);

                    break;
            }
        } else if ("左肩".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向前":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(2).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向后":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(2).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(3).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(3).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        } else if ("右肩".equals(content)) {
            joints = new Joint[1];
            joints[0] = new Joint();
            switch (oration) {
                case "向前":
                    int integer = Integer.valueOf(angle);
                    joints[0].setIndex(7).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向后":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(7).setRotate((short) -integer).setTime((short) 20000);
                    break;
                case "向上":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(8).setRotate((short) integer).setTime((short) 20000);
                    break;
                case "向下":
                    integer = Integer.valueOf(angle);
                    joints[0].setIndex(8).setRotate((short) -integer).setTime((short) 20000);
                    break;
            }
        }
        return joints;
    }
}
