package com.unisrobot.javaread.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WEI on 2018/5/20.
 */

public class LocalThreadSimp {
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("construct...........");
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date get(String source) throws ParseException {
        return threadLocal.get().parse(source);
    }

}
