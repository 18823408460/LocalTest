package com.unisrobot.javaread;

import java.io.File;

/**
 * Created by Administrator on 2018/4/28.
 */

public class ReadMain {

        public static void main(String[] args) {
                String path = "F:\\svn\\策划\\dqq\\U05E教育机器人\\00-U05E情绪情感系统\\U05E本体-动画合集\\心碎";
                String pathTo = "F:\\rename\\ai";
                File file = new File(path);
                File[] files = file.listFiles();
                int count = 0 ;
                if (file.exists()) {
                        for (File file1 : files
                                ) {
                                if (count<10){
                                        file1.renameTo(new File(pathTo,"comp_0000"+(count++)+".png"));
                                }else {
                                        file1.renameTo(new File(pathTo,"comp_000"+(count++)+".png"));
                                }

                                System.out.println("exit========" + file1.getName());
                        }
                }
        }
}