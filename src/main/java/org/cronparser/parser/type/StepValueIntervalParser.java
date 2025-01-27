package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class StepValueIntervalParser extends Parser {
    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        String[] interval = cronExpression.split("/");
        if (interval.length > 2) {
            throw new InvalidCronExpression(cronFieldType, cronExpression, "Multiple step values are not allowed");
        }
        String startInterval = interval[0];
        String intervalString = interval[1];
        int start, end = cronFieldType.getEndRange();
        if (startInterval.equals("*")) start = 0;
        else if (startInterval.contains("-")) {
            String []vals = startInterval.split("-");
            start = Integer.parseInt(vals[0]);
            end = Integer.parseInt(vals[1]);
        }
        else start = Integer.parseInt(startInterval);
        int increment = Integer.parseInt(intervalString);
        List<Integer> result = new ArrayList<>(List.of());

        if(isValid(start, increment, cronFieldType)){
            result.addAll(getCronTimings(start, end, increment));
        } else {
            throw new InvalidCronExpression(cronFieldType, cronExpression, "Values passed are not in given range");
        }

        return result;
    }

    @Override
    public String getRegex() {
        return "^(\\d+-\\d+|[\\d*]+)\\/\\d+$";
    }

    private boolean isValid(Integer startInterval, Integer increment, CronFieldType cronFieldType) {
        return startInterval >= cronFieldType.getStartRange()
                && startInterval <= cronFieldType.getEndRange()
                && increment+startInterval >= cronFieldType.getStartRange()
                && increment+startInterval <= cronFieldType.getEndRange();
    }
}
