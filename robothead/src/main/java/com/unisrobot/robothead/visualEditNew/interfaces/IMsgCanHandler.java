package com.unisrobot.robothead.visualEditNew.interfaces;

import com.unisrobot.robothead.visualEditNew.ChildNode;
import com.unisrobot.robothead.visualEditNew.FatherNode;

/**
 * Created by WEI on 2018/7/1.
 */

public interface IMsgCanHandler {
    /**
     * @param //如果是容器类节点处理,true,同时加上后面参数，如果子节点false
     * @param
     */
    void fatherHandler(FatherNode fatherNode);

    void childHandler(ChildNode fatherNode);
}
