package com.unisrobot.javaread;

import android.text.TextUtils;
import android.util.Log;

import com.unisrobot.javaread.compare.FileString;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/4/28.
 */
//com.dl7.mvp.adapter
public class ReadMain {
        public static final byte EVENT_CSB_RESPONSE = (byte) 0x96;

        public static void main(String[] args) {
                //                testBurrfer();

                //                test2();
                List<String> strings = new ArrayList<>();
                strings.add("111");
                strings.add("222");
                strings.add("333");

                strings.add(1,"helllo1");
                strings.add(2,"helllo2");
                strings.add(3,"helllo3");

                for (String list: strings
                     ) {
                        System.out.println("======="+list);
                }
        }

        private static void getdata() {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < 500; i++) {
                        stringBuffer.append("A");
                }
                endCodeTouChuanDataHead(1, stringBuffer.toString());
        }

        private static void endCodeTouChuanDataHead(int msgType, String data) {
                byte[] str = data.getBytes();
                int len = str.length + 1 + 2;
                byte buffer[] = new byte[len + 7];
                int index = 0;
                buffer[index++] = 0x44;
                buffer[index++] = 0x4A;
                buffer[index++] = (byte) (len >> 8);
                buffer[index++] = (byte) (len & 0xff);

                buffer[index++] = (byte) 0xdb;

                buffer[index++] = (byte) (msgType >> 8);
                buffer[index++] = (byte) (msgType & 0xff);

                byte sum = (byte) (buffer[4] + buffer[5] + buffer[6]);

                for (int i = 0; i < str.length; i++) {
                        buffer[index++] = str[i];
                        sum += str[i];
                }

                buffer[index++] = sum;
                buffer[index++] = 0x0d;
                buffer[index++] = 0x0a;
                System.out.println("==" + bytesToHexString(buffer));
        }

        private static void endCodeTouChuanData(int msgType, String data) {
                byte[] str = data.getBytes();
                int len = str.length + 1 + 2;
                byte buffer[] = new byte[len + 7];
                int index = 0;
                buffer[index++] = 0x08;
                buffer[index++] = 0x06;
                buffer[index++] = (byte) ((len >> 8) & 0xff);
                buffer[index++] = (byte) (len & 0xff);


                buffer[index++] = 0x13;
                buffer[index++] = (byte) (msgType >> 8);
                buffer[index++] = (byte) (msgType & 0xff);

                byte sum = (byte) (0x13 + (byte) (msgType >> 8) + (byte) (msgType & 0xff));
                for (int i = 0; i < (len - 3); i++) {
                        buffer[index++] = str[i];
                        sum += str[i];
                }
                buffer[index++] = sum;

                buffer[index++] = 0x0d;
                buffer[index++] = 0x0a;
                System.out.println("==" + bytesToHexString(buffer));
        }

        public static String bytesToHexString(byte[] src) {
                StringBuilder stringBuilder = new StringBuilder("");
                if (src == null || src.length <= 0) {
                        return null;
                }
                for (int i = 0; i < src.length; i++) {
                        int v = src[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        stringBuilder.append("0x");
                        if (hv.length() < 2) {
                                stringBuilder.append(0);
                        }
                        stringBuilder.append(hv);
                        stringBuilder.append(", ");
                }
                return stringBuilder.toString();
        }

        private static void testHashmap() {
                HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

                Integer integer = stringIntegerHashMap.get("hello");
                System.out.println("int======= " + integer);
                integer = new Integer(0);
                stringIntegerHashMap.put("hello", ++integer);

                Integer integer1 = stringIntegerHashMap.get("hello");
                System.out.println("int1======= " + integer1);

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
                stringBuffer.delete(0, stringBuffer.length());
                System.out.println(stringBuffer.capacity() + "  " + stringBuffer.toString() + "  len=" + stringBuffer.length());
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
