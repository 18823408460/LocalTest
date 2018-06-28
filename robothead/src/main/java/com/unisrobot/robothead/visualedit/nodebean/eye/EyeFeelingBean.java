package com.unisrobot.robothead.visualedit.nodebean.eye;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

/**
 * Created by WEI on 2018/6/27.
 */

public class EyeFeelingBean {
    private String event;
    private String content;
    private long time;

    public static EyeFeelingBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
        EyeFeelingBean eyeFeeling = new EyeFeelingBean();
        eyeFeeling.content = nodeDataBase.Args.get(0).Content;
        eyeFeeling.event = nodeDataBase.Event;
        return eyeFeeling;
    }

    private String getEyesId(String param1) {
        String id = "-1";
        switch (param1) {
            case "伤心":
                id = "2";
                break;

            case "傲慢":
                id = "3";
                break;
            case "困了":
                id = "4";
                break;
            default:
                break;
        }
        return id;
    }

    public void exeNode() {
        if (!TextUtils.isEmpty(event)) {
            // 一种是控制眼睛动画
            // 一种是控制胸口pad 动画
        }
    }
}
