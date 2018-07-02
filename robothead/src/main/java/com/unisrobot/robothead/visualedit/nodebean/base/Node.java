package com.unisrobot.robothead.visualedit.nodebean.base;

import android.os.Bundle;
import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.type.RobotMsgType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/6/26.
 */

public abstract class Node<R> {
        public static String Bundle_SensorName = "SensorName";
        //有可能可以接受多个消息，比如播放tts，可以是播放完毕，可以是播放超时
        protected List<RobotMsgType> robotMsgTypeList;
        protected Bundle bundle;

        public Bundle getBundle() {
                return bundle;
        }

        public abstract R exeNode();

        public List<RobotMsgType> getRobotMsgTypeList() {
                return robotMsgTypeList;
        }

        public void setRobotMsgType(RobotMsgType robotMsgType) {
                if (robotMsgTypeList == null) {
                        robotMsgTypeList = new ArrayList<>();
                }
                robotMsgTypeList.add(robotMsgType);
        }

        public void setBundle(Bundle bundle) {
                this.bundle = bundle;
        }

        protected static long getTime(String time) {
                long xTime = 1000;
                if (!TextUtils.isEmpty(time)) {
                        xTime = (long) (Double.parseDouble(time) * 1000);
                }
                return xTime;
        }

        protected static long getTime(int time) {
                long xTime = 1000;
                if (time > 0) {
                        xTime = time * 1000;
                }
                return xTime;
        }
}
