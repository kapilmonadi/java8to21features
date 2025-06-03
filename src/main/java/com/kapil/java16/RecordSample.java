package com.kapil.java16;

import com.kapil.records.Employee;

/**
 * This class showcases examples of creating classes using Records that was introduced as a preview in Java 14
 * and finalized as a feature in Java 16
 */
public class RecordSample {
    public static void main(String[] args) {
        Employee employee1 = new Employee(1L, "Ankit" , "P");
        Employee employee2 = new Employee(2L, "Amit" , "S");
        System.out.println("Emp1 " + employee1);
        System.out.println("Emp2 "  + employee2);
    }
}
