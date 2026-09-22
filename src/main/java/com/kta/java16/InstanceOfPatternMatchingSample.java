package com.kta.java16;

/**
 * This class showcases examples of performing the instance of test without Pattern Matching
 * and with Pattern Matching that was introduced in Java 16
 * Type checking and Type casting combined into a single operation reducing boilerplate code
 */
public class InstanceOfPatternMatchingSample {
    public static void main(String[] args) {
        String mySampleStr = "Hello Kaps ";
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
        else if (inputObj instanceof Integer number) {
            System.out.println("Integer: " + (number * 2));

        } else {
            System.out.println("Unknown type");
        }
        // the scope is confined to the block in which the variable is declared
        // below code won't compile
        //System.out.println(inputStr);

        // the pattern matching variable can be used with short circuit && for further comparison
        if (inputObj instanceof String inputStr && inputStr.length() > 5) {
            System.out.println(inputStr.concat(" !!!!!!!!!!!!!"));
        }

        // null handling
        inputObj = null;
        System.out.println("inputObj is : "+ inputObj);

        // the below will evaluate to false and there will not be an  exception raised
        if (inputObj instanceof String inputStr) {
            System.out.println("It's null !!!!");
        }
    }
}
