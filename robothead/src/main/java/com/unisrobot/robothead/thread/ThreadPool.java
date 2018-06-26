package com.unisrobot.robothead.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/6/26.
 */

public class ThreadPool {
        private volatile static ThreadPool mThreadPool = null;
        private ExecutorService executorService;

        private ThreadPool() {
                executorService = Executors.newCachedThreadPool();
        }

        public static ThreadPool getInstance() {
                if (mThreadPool == null) {
                        synchronized (ThreadPool.class) {
                                if (mThreadPool == null) {
                                        mThreadPool = new ThreadPool();
                                }
                        }
                }

                return mThreadPool;
        }

        public void runTask(Runnable runnable) {
                executorService.execute(runnable);
        }

        public void shutDown() {
                executorService.shutdownNow();
        }
}
