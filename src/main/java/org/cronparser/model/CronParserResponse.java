package org.cronparser.model;

import java.util.List;

import static java.lang.String.format;

public class CronParserResponse {
    private List<Integer> minutes;
    private List<Integer> hours;
    private List<Integer> daysOfMonth;
    private List<Integer> month;
    private List<Integer> daysOfWeek;
    private String command;


    public List<Integer> getMinutes() {
        return minutes;
    }

    public void setMinutes(List<Integer> minutes) {
        this.minutes = minutes;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }

    public List<Integer> getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(List<Integer> daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public List<Integer> getMonth() {
        return month;
    }

    public void setMonth(List<Integer> month) {
        this.month = month;
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String toString() {
        return format("%-14s%s\n", CronFieldType.MINUTE.getName(), printList(minutes)) +
                format("%-14s%s\n", CronFieldType.HOUR.getName(), printList(hours)) +
                format("%-14s%s\n", CronFieldType.DAY_OF_MONTH.getName(), printList(daysOfMonth)) +
                format("%-14s%s\n", CronFieldType.MONTH.getName(), printList(month)) +
                format("%-14s%s\n", CronFieldType.DAY_OF_WEEK.getName(), printList(daysOfWeek)) +
                format("%-14s%s\n", "command", command);
    }

    private String printList(List<Integer> integers) {
        StringBuilder result = new StringBuilder();
        for (Integer t : integers) {
            result.append(Integer.toString(t));
            result.append(" ");
        }
        return result.toString();
    }
}
