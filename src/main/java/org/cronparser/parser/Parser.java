package org.cronparser.parser;

import org.cronparser.model.CronFieldType;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {

    public abstract List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression);

    public abstract String getRegex();

    protected List<Integer> getCronTimings(CronFieldType cronFieldType, Integer increment) {
        return getCronTimings(cronFieldType.getStartRange(), cronFieldType.getEndRange(), increment);
    }

    protected List<Integer> getCronTimings(Integer startRange, Integer endRange, Integer increment) {
        List<Integer> result = new ArrayList<>();

        while(startRange <= endRange) {
            result.add(startRange);
            startRange = startRange + increment;
        }

        return result;
    }
}
