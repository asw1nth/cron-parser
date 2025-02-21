package org.cronparser;

import java.util.Arrays;

import static org.cronparser.CronParser.computeTimings;

/**
 * Example : "*\/15 0 1,15 * 1-5 /usr/bin/find"
 * All of these below are supported
 * Star, Numbers, Ranges, Multiple numbers & Ranges, Step Intervals, combination of all above, duplicates
 * Step Intervals - *\/15 , 2/15, 0-55/15
 * Limitations:
 * @daily , @weekly, @yearly, Some months have < 31 days, MON-FRI, MON, JAN, JAN-APR, ?, seconds & years,
 * (1-7)L , (1-7)W, 2#2
 *
 *
 * MON-FRI, MON, JAN, JAN-APR
 * ***/


public class Main {

    private static final int EXPRESSION_LENGTH = 6;
    public static void main(String []args) {
        try {
            String[] expressions = preprocess(args);

            System.out.println(Arrays.toString(expressions));

            computeTimings(expressions);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String[] preprocess(String[] args) {
        if (args.length != 1) {
            printExpectedFormat(args);
            throw new IllegalArgumentException("Invalid Input arguments");
        }

        String cronExpression = args[0];
        String[] expressions = cronExpression.split(" ");

        if (expressions.length != EXPRESSION_LENGTH) {
            printExpectedFormat(args);
            throw new IllegalArgumentException("Invalid Input arguments");
        }
        return expressions;
    }

    public static void printExpectedFormat(String[] args) {
        System.out.println("Expected format:");
        System.out.println("[minute] [hour] [day of month] [month] [day of week] [command]");
        System.out.println("Example: */15 0 1,15 * 1-5 /usr/bin/find");

        System.out.println();
        System.out.println("Received format:");
        System.out.println(Arrays.toString(args));
    }
}
