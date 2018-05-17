package com.unisrobot.javaread.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.reactivex.Observer;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2018/5/17.
 */

public class Lambda {

        @Test
        public void test1() {
                Runnable runnable = () -> System.out.println("hello");
                runnable.run();
        }

        @Test
        public void test2() {
                List<Employee> list = Arrays.asList(
                        new Employee("张三", 23),
                        new Employee("张四", 24),
                        new Employee("张五e", 25),
                        new Employee("张六d", 26)
                );

                Employee employee = new Employee("张六d", 26);


                // 流式Api只是针对 collection 的
                list.stream()
                        .map(Employee::getName)
                        .forEach(System.out::println);
                list.stream()
                        .map(x -> x.getName())
                        .filter(x -> x.length() > 2)
                        .forEach(x -> System.out.println(x));

                list.stream().map(new Function<Employee, String>() {
                        @Override
                        public String apply(Employee employee) {
                                return employee.getName();
                        }
                }).flatMap(new Function<String, Stream<String>>() {
                        @Override
                        public Stream<String> apply(String s) {
                                return Arrays.stream(new String[]{"ss=" + s});
                        }
                }).forEach(System.out::println);


                happy(100, x -> System.out.println("consume====" + x
                ));

                String s = funciotn1(100d, x -> String.valueOf(x) + "   apply");
                System.out.println(s);

                String supply = supply(100, () -> {
                        return "捡到100元";
                });

                assertEquals(2, 2);
                System.out.println(supply);

        }


        private void happy(double monney, Consumer<Double> consumer) {
                consumer.accept(monney);
        }

        private String funciotn1(Double money, Function<Double, String> function) {
                return function.apply(money);
        }

        private String supply(double money, Supplier<String> supplier) {
                String aDouble = supplier.get();
                return aDouble;
        }

        // 断言型---- Boolean
        private boolean predict(double money, Predicate<Double> predicate) {
                return predicate.test(money);
        }
}
