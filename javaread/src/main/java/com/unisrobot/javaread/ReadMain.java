package com.unisrobot.javaread;

import com.unisrobot.javaread.compare.FileString;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/4/28.
 */

public class ReadMain {
        public static final byte EVENT_CSB_RESPONSE = (byte) 0x96;

        public static void main(String[] args) {
                test3();

                //                test2();
        }

        private static void test3() {
                List<FileString> list = Arrays.asList(
                        new FileString("a1.png"),
                        new FileString("a2.png"),
                        new FileString("a10.png"),
                        new FileString("a11.png"),
                        new FileString("a21.png")
                );
                Collections.sort(list);
                System.out.println(list);
        }

        private static void test2() {
                int aa = (byte) 0x96;
                System.out.println("aa===" + aa);
                if (EVENT_CSB_RESPONSE == aa) {
                        System.out.println("------------------1");
                } else {
                        System.out.println("--------------0");
                }
                test();
        }


        private static void test() {
                ExecutorService executorService = Executors.newFixedThreadPool(2);
                executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                                while (true) {
                                        System.out.println("thread alice");
                                        try {
                                                Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                                System.out.println("interutped");
                                        }
                                }
                        }
                });
                TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                                executorService.shutdownNow();
                        }
                };
                new Timer().schedule(timerTask, 5000);
        }


        private static void read() {
                String path = "F:\\svn\\策划\\dqq\\U05E教育机器人\\00-U05E情绪情感系统\\U05E本体-动画合集\\开心";
                String pathTo = "F:\\rename\\开心";
                File file = new File(path);
                File[] files = file.listFiles();
                int count = 0;
                if (file.exists()) {
                        for (File file1 : files
                                ) {
                                file1.renameTo(new File(pathTo, "us_" + (count++) + ".png"));

                                System.out.println("exit========" + file1.getName());
                        }
                }
        }
}
