package com.unisrobot.robothead.visualedit.nodebean.percetion;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class WaitPhoneBean extends Node {
        private String state;


        @Override
        public Object exeNode() {
                return null;
        }

        public static WaitPhoneBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
                WaitPhoneBean waitTouchBean = new WaitPhoneBean();
                waitTouchBean.state = nodeDataBase.Args.get(0).Content;
                waitTouchBean.setRobotMsgType(RobotMsgType.WaitForPhone);
                Bundle bundle = new Bundle();
                bundle.putString(Bundle_PhoneState, waitTouchBean.state);
                waitTouchBean.setBundle(bundle);
                return waitTouchBean;
        }
}
