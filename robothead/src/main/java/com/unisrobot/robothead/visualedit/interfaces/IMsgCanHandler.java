package com.unisrobot.robothead.visualedit.interfaces;

import com.unisrobot.robothead.visualedit.model.LinkNode;

/**
 * Created by WEI on 2018/7/1.
 */

public interface IMsgCanHandler {
    /**
     * @param isFatherNode ,如果是容器类节点处理,true,同时加上后面参数，如果子节点false
     * @param linkNode
     */
    void haveHandler(boolean isFatherNode, LinkNode linkNode);
}
