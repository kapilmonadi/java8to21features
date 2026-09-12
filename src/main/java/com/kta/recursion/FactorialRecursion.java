package com.kta.recursion;

public class FactorialRecursion {

    public static long calculateFactorial(long inputNumber) {
        if(inputNumber == 0 || inputNumber == 1) {
            return 1;
        }
        else {
            return inputNumber * calculateFactorial(inputNumber - 1);
        }
    }

    public static long calculateFactorialTailRec(long inputNumber) {
        long accumulator = 1;
        return calculateFactorialTailRec(inputNumber, accumulator);
    }

    private static long calculateFactorialTailRec(long inputNumber, long accumulator) {
        if(inputNumber == 0 || inputNumber == 1) {
            return accumulator;
        }
        else {
            return calculateFactorialTailRec(inputNumber - 1 , inputNumber * accumulator);
        }
    }
}
