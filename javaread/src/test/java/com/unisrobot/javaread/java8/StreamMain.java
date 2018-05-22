package com.unisrobot.javaread.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/5/21.
 */

public class StreamMain {
        List<Employee> list = Arrays.asList(
                new Employee("张三", 10),
                new Employee("李四", 15),
                new Employee("王五", 20),
                new Employee("王五", 20),
                new Employee("赵六", 30),
                new Employee("李七", 50),
                new Employee("王八", 55)
        );

        @Test
        public void test4() {
                Stream<Employee> stream = list.stream();
                stream.filter((x) -> x.getAge() > 20)
                        .mapToInt((x) -> x.getAge())
                        .forEach(System.out::println);

        }


        @Test
        public void test1() {
                Stream<Employee> stream = list.stream();
                stream.sorted((x, y) ->
                        -Integer.compare(x.getAge(), y.getAge())
                ).distinct()
                        .forEach(System.out::println);

        }

        @Test
        public void test2() {
                List<String> Strings = Arrays.asList(
                        "adbc", "efghi", "gkp"
                );
                Stream<String> stream = Strings.stream();
                getChars(Strings.get(0)).stream().forEach(System.out::println);


                stream.map(new Function<String, List<Character>>() {
                        @Override
                        public List<Character> apply(String s) {
                                return getChars(s);
                        }
                }).map(new Function<List<Character>, Stream<Character>>() {

                        @Override
                        public Stream<Character> apply(List<Character> characters) {
                                return characters.stream();
                        }
                }).forEach(System.out::println);
        }


        @Test
        public void test3() {
                List<String> Strings = Arrays.asList(
                        "adbc", "efghi", "gkp"
                );
                Stream<String> stream = Strings.stream();
                Stream<String> stream1 = Strings.stream();

                // 对数据 多两次处理。。。
                stream.flatMap(new Function<String, Stream<Character>>() {
                        @Override
                        public Stream<Character> apply(String s) {
                                return getChars(s).stream();
                        }
                }).forEach(System.out::println);

                System.out.println("-------------------------------");

                stream1.flatMap((x) ->
                        getChars(x).stream()
                ).forEach(System.out::println);
        }


        private List<Character> getChars(String s) {
                List<Character> chars = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                        chars.add(s.charAt(i));
                }
                return chars;
        }

}
