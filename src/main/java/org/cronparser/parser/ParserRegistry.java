package org.cronparser.parser;

import org.cronparser.parser.manager.ParserManager;
import org.cronparser.parser.type.*;

public class ParserRegistry {

    public static void registerParsers(ParserManager parserManager) {
        Parser starParser = new StarParser();
        parserManager.registerParser(starParser);

        Parser intervalsparser = new IntervalsParser();
        parserManager.registerParser(intervalsparser);

        Parser numberParser = new NumberParser();
        parserManager.registerParser(numberParser);

        Parser stepIntervalParser = new StepIntervalParser();
        parserManager.registerParser(stepIntervalParser);

        Parser stepValueIntervalParser = new StepValueIntervalParser();
        parserManager.registerParser(stepValueIntervalParser);

        Parser weekNamedParser = new WeekNamedParser();
        parserManager.registerParser(weekNamedParser);
    }
}
