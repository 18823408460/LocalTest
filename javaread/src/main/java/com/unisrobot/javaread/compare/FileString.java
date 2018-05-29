package com.unisrobot.javaread.compare;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileString implements Comparable<FileString> {
        private final String fileName;
        private final int prefix_num;
        Pattern number = Pattern.compile("(\\d+)\\..*");

        public FileString(String fileName) {
                this.fileName = fileName;
                Matcher matcher = number.matcher(fileName);
                if (matcher.find()) {
                        String group = matcher.group(); //返回的是匹配串   的所有字符长度
                        System.out.println("=========="+matcher.groupCount());
                        System.out.println("========="+matcher.group());
                        System.out.println("========="+matcher.group().length());
                        System.out.println();
                        prefix_num = Integer.parseInt(matcher.group(1));
                } else {
                        prefix_num = 0;
                }
        }

        @Override
        public int compareTo(FileString o) {
                return o.prefix_num > prefix_num ? -1 : o.prefix_num == prefix_num ? 0 : 1;
        }

        @Override
        public String toString() {
                return fileName;
        }
}