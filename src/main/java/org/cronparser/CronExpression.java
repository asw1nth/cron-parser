package org.cronparser;

import org.cronparser.model.CronFieldType;
import org.cronparser.model.CronParserResponse;
import org.cronparser.parser.manager.ParserManager;

import java.util.List;

public class CronExpression {

    private final ParserManager parserManager;

    public CronExpression(ParserManager parserManager) {
        this.parserManager = parserManager;
    }

    public CronParserResponse parseExpression(String [] expressions) {
        CronParserResponse cronParserResponse = new CronParserResponse();

        List<Integer> minutes = parserManager.getAllTimings(CronFieldType.MINUTE, expressions[0]);
        cronParserResponse.setMinutes(minutes);

        List<Integer> hours = parserManager.getAllTimings(CronFieldType.HOUR, expressions[1]);
        cronParserResponse.setHours(hours);

        List<Integer> dayOfMonth = parserManager.getAllTimings(CronFieldType.DAY_OF_MONTH, expressions[2]);
        cronParserResponse.setDaysOfMonth(dayOfMonth);

        List<Integer> month = parserManager.getAllTimings(CronFieldType.MONTH, expressions[3]);
        cronParserResponse.setMonth(month);

        List<Integer> dayOfWeek = parserManager.getAllTimings(CronFieldType.DAY_OF_WEEK, expressions[4]);
        cronParserResponse.setDaysOfWeek(dayOfWeek);

        cronParserResponse.setCommand(expressions[5]);

        return cronParserResponse;

    }
}
