package org.cronparser;

import org.cronparser.model.CronParserResponse;
import org.cronparser.parser.manager.ParserManager;
import org.cronparser.parser.manager.ParserManagerImpl;

import static org.cronparser.parser.ParserRegistry.registerParsers;

public class CronParser {

    public static void computeTimings(String [] expressions) {
        ParserManager parserManager = new ParserManagerImpl();

        registerParsers(parserManager);

        CronExpression cronExpression = new CronExpression(parserManager);
        CronParserResponse cronParserResponse = cronExpression.parseExpression(expressions);

        System.out.println(cronParserResponse.toString());

    }
}
