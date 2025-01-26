package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.List;

public class StepIntervalParser extends Parser {

    private static final String STEP_INTERVAL_FORMAT = "*/";
    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        String intervalString = cronExpression.substring(STEP_INTERVAL_FORMAT.length());
        int interval = Integer.parseInt(intervalString);

        if(interval < cronFieldType.getStartRange() || interval > cronFieldType.getEndRange()) {
            throw new InvalidCronExpression(cronFieldType, cronExpression, "Values passed are not in give range");
        }

        return getCronTimings(cronFieldType, interval);
    }

    @Override
    public String getRegex() {
        return "^\\*/\\d+$";
    }
}
