package com.unisrobot.localtest.threadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2018/4/12.
 */
//当线程池中的资源已经全部使用，添加新线程被拒绝时,会调用下面这个方法
public class DefaultRejectExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
}
