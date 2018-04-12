package com.unisrobot.localtest.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/4/11.
 */

/**
 * I----- Executor(i)
 * |
 * ExecutorService (i)
 * |
 * AbstractExecutorService(abstract)
 * |
 * ThreadPoolExecutor(im)   ForkJoinPool(im)
 * <p>
 * 我们常用的是 ThreadPoolExcutor，这个类有4个构造，可以创建不同类型的 threadPool，
 * 但是我们不直接调用构造，而是通过 工具类： Executors里面的静态方法来创建需要的Pool
 * <p>
 * <p>
 * ..... 不推荐使用 Executors，而是用ThreadPoolExcutor，这样更容易了解线程池的运行规则，规避资源耗尽的风险；
 */
public class ThreadPoolMgr {

        private static ThreadPoolMgr threadPoolMgr;

        private ExecutorService executorService;

        private ThreadPoolExecutor threadPoolExecutor;

        public static ThreadPoolMgr getInstance() {
                if (threadPoolMgr == null) {
                        synchronized (ThreadPoolMgr.class) {
                                if (threadPoolMgr == null) {
                                        threadPoolMgr = new ThreadPoolMgr();
                                }
                        }
                }
                return threadPoolMgr;
        }

        private ThreadPoolMgr() {


                executorService = Executors.newFixedThreadPool(5);
        }


        public void excute(Runnable runnable) {
                executorService.submit(runnable);
        }

        private void customThreadPool() {
                int num_of_cores = Runtime.getRuntime().availableProcessors(); // 核心线程数
                int num_of_coresOther = num_of_cores * 2; // 非核心线程数，线程池所能容纳的最大线程数
                int keep_alive_time = 1; // 非核心线程的闲置超时时间，超过这个时间就会被回收
                TimeUnit timeUiit = TimeUnit.SECONDS;
                LinkedBlockingQueue<Runnable> tasksQueue = new LinkedBlockingQueue<>();
                threadPoolExecutor = new ThreadPoolExecutor(num_of_cores, num_of_coresOther, keep_alive_time,
                        timeUiit, tasksQueue, new DefaultThreadFactory(), new DefaultRejectExecutionHandler());
        }

        public void excuteCustom(Runnable runnable) {
                threadPoolExecutor.execute(runnable);
        }

}
