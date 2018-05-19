package com.unisrobot.javaread.java8;

/**
 * Created by Administrator on 2018/5/17.
 */

public class Employee {
        private String name ;
        private int age    ;

        public Employee() {
                System.out.println("-----null-------------");
        }

        public Employee(String name, int age) {

                this.name = name;
                this.age = age;
        }

        public Employee(String name) {
                this.name = name;
        }

        public Employee(int age) {
                this.age = age;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public String getName() {

                return name;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Employee employee = (Employee) o;

                if (age != employee.age) return false;
                return name != null ? name.equals(employee.name) : employee.name == null;
        }

        @Override
        public int hashCode() {
                int result = name != null ? name.hashCode() : 0;
                result = 31 * result + age;
                return result;
        }

        @Override
        public String toString() {
                return "Employee{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
        }

        public void setName(String name) {
                this.name = name;
        }
}
