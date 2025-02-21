package org.cronparser.exception;

import org.cronparser.model.CronFieldType;

public class InvalidCronExpression extends RuntimeException {

    public InvalidCronExpression(String message) {
        super(message);
    }

    public InvalidCronExpression(CronFieldType cronFieldType, String cronExpression, String message) {
        String errorMessage = "The expression " + cronExpression + " passed for cron field " +
                cronFieldType.getName() + " is invalid. \n" +
                "Accepted range for cron field " + cronFieldType.getName() +
                " is [" + cronFieldType.getStartRange() + "-" + cronFieldType.getEndRange() + "]. \n" +
                "Error message is : " + message;

        throw new InvalidCronExpression(errorMessage);
    }
}
