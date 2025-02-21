package org.cronparser.parser.manager;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.*;

public class ParserManagerImpl implements ParserManager {

    private final Set<Parser> parserList = new HashSet<>();

    @Override
    public void registerParser(Parser parser) {
        parserList.add(parser);
    }

    @Override
    public List<Integer> getAllTimings(CronFieldType cronFieldType, String cronExpression) {
        Parser parser;
        Set<Integer> result = new HashSet<>();
        try {
            String []expressions = cronExpression.split(",");
            for (String expression : expressions) {
                parser = getParser(expression);

                result.addAll(parser.getTimings(cronFieldType, expression));
            }
        } catch (InvalidCronExpression e) {
            throw new InvalidCronExpression(e.getMessage());
        }
        List<Integer> timings = new ArrayList<>(result);
        Collections.sort(timings);
        return timings;
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
