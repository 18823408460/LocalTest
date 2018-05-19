package com.unisrobot.javaread.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/5/19.
 */

public class Lambda2 {


        @Test
        public void test2() {
                Concruct concruct = (x) -> new Employee(x);
                Employee employee = concruct.get("hello");
                System.out.println(employee);

                //
                Concruct concruct1 = Employee::new;
                Employee employee1 = concruct1.get("11");
                System.out.println(employee1);

                Supplier<Employee> supplier = Employee::new;
                Employee employee2 = supplier.get();
                System.out.println(employee2);


        }


        @Test
        public void test1() {
                List<Employee> employees = new ArrayList<>();
                employees.add(new Employee("一", 1));
                employees.add(new Employee("er", 1));
                employees.add(new Employee("san", 1));
                employees.add(new Employee("si", 1));

                // 1
                Stream<Employee> stream = employees.stream();
                PrintStream out = System.out;
                stream.map(Employee::getName).forEach(out::println);

                //2
                Stream<String> stream1 = Arrays.stream(new String[]{"a", "b", "c", "d"});
                stream1.forEach(out::println);

                // 3
                Stream<String> stringStream = Stream.of("dd", "ccc", "bbb");
                stringStream.forEach(out::println);

                // 4
                Stream<Double> generate = Stream.generate(() -> Math.random());
                generate.limit(4)
                        .forEach(out::println);

                // 5
                Stream<Integer> iterate = Stream.iterate(1, (x) -> x + 2);
                iterate.limit(20)
                        .skip(3)
                        .distinct() // 通过hashcode 的 equals 来判断的
                        .forEach(out::println);
        }
}
