package org.cronparser.parser.type;

import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.List;

public class StarParser extends Parser {

    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        return getCronTimings(cronFieldType, 1);
    }

    @Override
    public String getRegex() {
        return "^\\*$";
    }
}
