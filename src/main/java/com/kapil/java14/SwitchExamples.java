package com.kapil.java14;
import java.time.DayOfWeek;

/**
 * Class showcasing switch expression introduced as part of Java 14
 */
public class SwitchExamples {

    public static void main(String[] args) {
        System.out.println(getMessageWithSwitchExpression("SUNDAY"));
    }

    public static String getMessage(String dayOfWeek) {
        String message;

        if (dayOfWeek.equals("MONDAY") || dayOfWeek.equals("TUESDAY") || dayOfWeek.equals("WEDNESDAY")) {
            message = "Work from Office";
        } else if (dayOfWeek.equals("THURSDAY") || dayOfWeek.equals("FRIDAY")) {
            message = "Work from Home";
        } else {
            message = "Go outing";
        }
        return message;
    }

    public static String getMessageWithSwitch(String dayOfWeek) {
        String message;

        switch (dayOfWeek) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY": {
                message = "Work from Office";
                break;
            }
            case "THURSDAY":
            case "FRIDAY": {
                message = "Work from Home";
                break;
            }
            default: {
                message = "Go outing";
            }
        }
        return message;
    }

    public static String getMessageWithSwitchExpression(String dayOfWeek) {
        String message = switch (dayOfWeek) {
            case "MONDAY", "TUESDAY", "WEDNESDAY" -> "Work from Office";
            case "THURSDAY", "FRIDAY" -> "Work from Home";
            default -> {
                System.out.println("Hey its weekend. Time to go outing !!");
                yield "Go outing";
            }
        };
        return message;
    }

    public static String getMessageWithSwitchExpressionEnum(DayOfWeek dayOfWeek) {
        String message = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY -> "Work from Office";
            case THURSDAY, FRIDAY -> "Work from Home";
            case SATURDAY, SUNDAY -> "Go outing";
        };
        return message;
    }
}
