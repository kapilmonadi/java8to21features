package com.kapil;

/**
 * This class showcases examples of performing the instance of test without Pattern Matching
 * and with Pattern Matching that was introduced in Java 17
 */
public class InstanceOfPatternMatchingSample {
    public static void main(String[] args) {
        String mySampleStr = "Hello Kaps !";
        testInstanceOfOldWay(mySampleStr);
        testInstanceOfNewWay(mySampleStr);
    }

    private static void testInstanceOfOldWay(Object inputObj) {
        if(inputObj instanceof String) {
            String inputStr = ((String) inputObj);
            System.out.println(inputStr.toUpperCase());
        }
    }

    private static void testInstanceOfNewWay(Object inputObj) {
        if(inputObj instanceof String inputStr) {
            System.out.println(inputStr.toUpperCase());
        }
    }
}
