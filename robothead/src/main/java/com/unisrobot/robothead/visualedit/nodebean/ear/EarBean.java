package com.unisrobot.robothead.visualedit.nodebean.ear;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.VpJsonBean;

/**
 * Created by WEI on 2018/6/27.
 */

public class EarBean {
    private String content;

    public static EarBean getBean(VpJsonBean.NodeDataBase nodeData) {
        EarBean earBean = new EarBean();
        earBean.content = nodeData.Args.get(0).Content;
        return earBean;
    }

    public void exeNode() {
        if (!TextUtils.isEmpty(content)) {
//            MsgSendUtils.sendStringMsg(MsgType.SEND_RECOGNITION_RESULT, content);
        }
    }
}
