package org.cronparser.parser.manager;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserManagerImpl implements ParserManager {

    private Set<Parser> parserList = new HashSet<>();

    @Override
    public void registerParser(Parser parser) {
        parserList.add(parser);
    }

    @Override
    public List<Integer> getAllTimings(CronFieldType cronFieldType, String cronExpression) {
        Parser parser;
        try {
            parser = getParser(cronExpression);
        } catch (InvalidCronExpression e) {
            throw new InvalidCronExpression(cronFieldType, cronExpression, e.getMessage());
        }
        return parser.getTimings(cronFieldType, cronExpression);
    }

    private Parser getParser(String expression) {
        for (Parser parser : parserList) {
//            System.out.println(expression);
            if (expression.matches(parser.getRegex())) {
//                System.out.println("Parser regex: " + parser.getRegex());
                return parser;
            }
        }

        throw new InvalidCronExpression("Invalid cron expression passed. Unable to parse the expression");
    }

}
