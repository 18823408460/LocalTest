package com.unisrobot.robothead.visualedit.nodebean.mind;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.NumberUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2. 心形bean
 */

public class HeartChangeBean extends Node<Long> {
        private long time;
        private HeartSetBean heartSetBean;
        private String operate;
        private String newColor;
        private String newDiff;

        @Override
        public Long exeNode() {
                changeValue();
                return time;
        }


        private void changeValue() {
                if (heartSetBean != null) {
                        String oldColor = heartSetBean.getColor();
                        String oldValue = heartSetBean.getValue();
                        if (!TextUtils.isEmpty(oldColor)) {
                                if (oldColor.equals(this.newColor)) {
                                        String newData = NumberUtil.calculateTwoNum(operate, oldValue, newDiff);
                                        heartSetBean.setValue(newData);
                                }
                        }
                }
        }

        public void setHeartSetBean(HeartSetBean heartSetBean) {
                this.heartSetBean = heartSetBean;
        }

        public static HeartChangeBean getBean(VpJsonBean.NodeDataBase nodeData) {
                HeartChangeBean heartBean = new HeartChangeBean();
                heartBean.time = getTime(1);
                heartBean.operate = nodeData.Pictures.get(0).Picture;
                heartBean.newColor = nodeData.Pictures.get(1).Picture;
                heartBean.newDiff = AppendUtil.getNumberParams(nodeData, 0);
                heartBean.setRobotMsgType(RobotMsgType.Timer);
                return heartBean;
        }
}
