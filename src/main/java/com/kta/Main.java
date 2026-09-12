package com.kta;

import static com.kta.recursion.FactorialRecursion.calculateFactorial;
import static com.kta.recursion.FactorialRecursion.calculateFactorialTailRec;

public class Main {
    public static void main(String[] args) {
        System.out.println(calculateFactorial(5));
        System.out.println(calculateFactorialTailRec(5));
    }
}
