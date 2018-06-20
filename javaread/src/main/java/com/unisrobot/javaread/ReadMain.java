package com.unisrobot.javaread;

import android.text.TextUtils;

import com.unisrobot.javaread.compare.FileString;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/4/28.
 */
//com.dl7.mvp.adapter
public class ReadMain {
        public static final byte EVENT_CSB_RESPONSE = (byte) 0x96;

        public static void main(String[] args) {
                testBurrfer();

                //                test2();
        }

        private static void testBurrfer() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("1");
                stringBuffer.append("2");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("3");
                stringBuffer.append("4");
                System.out.println(stringBuffer.capacity());
                System.out.println(stringBuffer.length());
                String data = stringBuffer.toString();
                System.out.println("data=" + data);
                stringBuffer.delete(0, stringBuffer.length() );
                System.out.println(stringBuffer.capacity()+ "  "+stringBuffer.toString()+"  len="+stringBuffer.length());
        }

        private static void testList() {
                LinkedList<String> linkedList = new LinkedList<>();
                linkedList.add("0");
                linkedList.add("1");
                linkedList.add("2");
                linkedList.add("3");

                String aa = null;
                for (String e :
                        linkedList) {
                        if (e.equals("2")) {
                                aa = e;
                                linkedList.remove(e);
                        }
                }
                if (aa != null)
                        linkedList.addFirst(aa);
                System.out.println(linkedList);
        }

        private static void test4() {
                String info = "[# ]";
                String[] splitInfo = info.split("\n");
                List<String> infoList = new ArrayList<>();

                System.out.println("=== " + splitInfo.length);
                System.out.println("=== " + infoList);
        }

        private static String delNewlineCharacter(String s) {
                return s.replace("\n", "").replace(" ", "").replace("\t", "").replace("\r", "");
        }

        private static void test3() {
                List<FileString> list = Arrays.asList(
                        new FileString("a1.png"),
                        new FileString("a2.png"),
                        new FileString("a10.png"),
                        new FileString("a11.png"),
                        new FileString("a21.pnga21.pnga21.png")
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
