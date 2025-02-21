package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class IntervalsParser extends Parser {
    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        String[] boundIntervals = cronExpression.split(",");
        List<Integer> result = new ArrayList<>();

        for(String boundInterval : boundIntervals) {
            String[] intervals = boundInterval.split("-");
            Integer startInterval = Integer.valueOf(intervals[0]);
            Integer endInterval = Integer.valueOf(intervals[1]);

            if(isValid(startInterval, endInterval, cronFieldType)) {
                result.addAll(getCronTimings(startInterval, endInterval, 1));
            } else {
                throw new InvalidCronExpression(cronFieldType, cronExpression, "Values passed are not in give range");
            }
        }
        return result;
    }

    @Override
    public String getRegex() {
        return "^\\d+-\\d+(,\\d+-\\d+)*$";
    }

    private boolean isValid(Integer startInterval, Integer endInterval, CronFieldType cronFieldType) {
        return startInterval >= cronFieldType.getStartRange()
                && startInterval <= cronFieldType.getEndRange()
                && endInterval >= cronFieldType.getStartRange()
                && endInterval <= cronFieldType.getEndRange()
                && startInterval <= endInterval;
    }
}
