package com.unisrobot.javaread.java8;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by WEI on 2018/5/20.
 */

public class LocalDateMain {

    @Test
    public void test6() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd  hh:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println("data====   " + format);

    }

    @Test
    public void test5() {
        Instant now = Instant.now(); // UTC 时区 美国时区
        System.out.println("instant======" + now);

        LocalDateTime now1 = LocalDateTime.now(); //本地时区
        System.out.println("LocalDataTime=" + now1);

        LocalDate now2 = LocalDate.now();
        System.out.println("localdate====" + now2);

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime===" + localTime);
    }


    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> callable = new Callable<LocalDate>() { // java8 不会有错。。
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20180520", formatter);
            }
        };
        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<LocalDate> submit = executorService.submit(callable);
            list.add(submit);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get());
        }

        // 执行完毕，一定要执行  关闭。。。。。。
        executorService.shutdown();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return LocalThreadSimp.get("20180520"); // 通过 LocalThread 就不会报错了。。。
            }
        };
        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> submit = executorService.submit(callable);
            list.add(submit);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get());
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return simpleDateFormat.parse("20180520");
            }
        };
        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //  多线程 这里会报错。。。。。。
            Future<Date> submit = executorService.submit(callable);
            list.add(submit);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get());
        }
    }


    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(9);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return simpleDateFormat.format(new Date());
            }
        };
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> submit = executorService.submit(callable);
            list.add(submit);
        }

        list.stream().forEach(
                x -> {
                    try {
                        System.out.println("x===" + x.get().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}

