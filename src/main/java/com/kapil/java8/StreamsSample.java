package com.kapil.java8;

import com.kapil.records.Employee;

import java.util.List;
import java.util.stream.IntStream;

public class StreamsSample {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(new Employee(1L, "Kaps", "K"),
                new Employee(2L, "Sam", "R"));

        printEmployeeWithoutStream(employeeList);
        printEmployeeWithStream(employeeList);
        generateIntStream(6);
    }

    private static void printEmployeeWithStream(List<Employee> employeeList) {
        employeeList.stream().forEach(System.out::println);
    }

    private static void printEmployeeWithoutStream(List<Employee> employees){
        // print using enhanced for loop
        for(Employee employee : employees){
            System.out.println(employee);
        }
    }

    private static void generateIntStream(int limit){
        IntStream intStream = IntStream.range(1, limit);
        intStream.forEach(System.out::println);
    }

}
