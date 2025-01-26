package org.cronparser;

import java.util.Arrays;

import static org.cronparser.CronParser.computeTimings;

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
