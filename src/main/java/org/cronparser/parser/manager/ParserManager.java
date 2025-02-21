package org.cronparser.parser.manager;

import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.List;

public interface ParserManager {

    void registerParser(Parser parser);

    List<Integer> getAllTimings(CronFieldType cronFieldType, String cronExpression);

}
