package com.kapil.java10;

import com.kapil.records.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ImmutableMapSample {

    public static void main(String[] args) {
        List<Employee> employeeList = fetchEmployees();
        System.out.println("Employee List is " + employeeList);
        Map<Long, Employee> employeeMap = employeeList.stream().collect(Collectors.toMap(Employee::id, Function.identity()));
        System.out.println("Employee Map is " + employeeMap);

        // we want to create an immutable static cache of map of employees
        employeeMap.put(3L, new Employee(3L, "Raj" , "Kumar"));
        System.out.println("Employee Map after modification is " + employeeMap);

        Map<Long, Employee> employeeMapOption01  = getImmutableMapOption1(employeeMap);
        Map<Long, Employee> employeeMapOption02  = getImmutableMapOption2(employeeMap);
        Map<Long, Employee> employeeMapOption03  = getImmutableMapOption3();
        Map<Long, Employee> employeeMapOption04  = getImmutableMapOption4();

        // we want to create an immutable static cache of map of employees
        //employeeMapOption01.put(4L, new Employee(3L, "Naresh" , "Kumar"));
        System.out.println("employeeMapOption01 is " + employeeMapOption01);
        System.out.println("employeeMapOption02 is " + employeeMapOption02);

        // we want to create an immutable static cache of map of employees
        employeeMap.put(5L, new Employee(5L, "Vikram" , "Kumar"));
        System.out.println("employeeMapOption01 is " + employeeMapOption01);
        System.out.println("employeeMapOption02 is " + employeeMapOption02);
        System.out.println("employeeMapOption03 is " + employeeMapOption03);
        System.out.println("employeeMapOption04 is " + employeeMapOption04);

        //employeeMapOption03.put(4L, new Employee(3L, "Naresh" , "Kumar"));
        //employeeMapOption04.put(4L, new Employee(3L, "Naresh" , "Kumar"));
    }

    // fetch the list of unique employees
    private static List<Employee> fetchEmployees() {
        Employee employee1 = new Employee(1L, "Ankit" , "P");
        Employee employee2 = new Employee(2L, "Amit" , "S");

        return List.of(employee1, employee2);
    }

    // Option 01 using Collections.unModifiableMap (since java 5)
    private static Map<Long, Employee> getImmutableMapOption1(Map<Long, Employee> inputMap) {
        return Collections.unmodifiableMap(inputMap);
    }

    // Option 02 using Map.copyOf (since java 10)
    private static Map<Long, Employee> getImmutableMapOption2(Map<Long, Employee> inputMap) {
        return Map.copyOf(inputMap);
    }

    // Option 03 using Map.of (since java 9)
    private static Map<Long, Employee> getImmutableMapOption3() {
        return Map.of(7L, new Employee(7L, "Ram" , "Kumar"),
                8L, new Employee(8L, "Vijay" , "Kumar"));
    }

    // Option 04 using Map.ofEntries (since java 9)
    private static Map<Long, Employee> getImmutableMapOption4() {
        return Map.ofEntries(Map.entry(9l, new Employee(9L, "Jeet" , "Kumar")),
                Map.entry(10l, new Employee(10L, "Rajesh" , "Kumar")));
    }
}
