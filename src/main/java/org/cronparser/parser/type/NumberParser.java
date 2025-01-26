package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class NumberParser extends Parser {
    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        String[] fixedTimings = cronExpression.split(",");
        List<Integer> result = new ArrayList<>();

        for(String fixedTime : fixedTimings) {
            Integer time = Integer.valueOf(fixedTime);
            if(isValid(time, cronFieldType)) {
                result.add(time);
            } else {
                throw new InvalidCronExpression(cronFieldType, cronExpression, "Values passed are not in give range");
            }
        }

        return result;
    }

    @Override
    public String getRegex() {
        return "^\\d+(,\\d+)*$";
    }

    private boolean isValid(Integer value, CronFieldType cronFieldType) {
        return value >= cronFieldType.getStartRange() && value <= cronFieldType.getEndRange();
    }
}
