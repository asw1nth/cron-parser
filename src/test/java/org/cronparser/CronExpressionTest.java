package org.cronparser;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronParserResponse;
import org.cronparser.parser.manager.ParserManager;
import org.cronparser.parser.manager.ParserManagerImpl;
import org.cronparser.parser.type.IntervalsParser;
import org.cronparser.parser.type.NumberParser;
import org.cronparser.parser.type.StarParser;
import org.cronparser.parser.type.StepIntervalParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CronExpressionTest {

    private static CronExpression parser;

    @BeforeAll
    public static void init() {
        ParserManager manager = new ParserManagerImpl();
        manager.registerParser(new StarParser());
        manager.registerParser(new StepIntervalParser());
        manager.registerParser(new NumberParser());
        manager.registerParser(new IntervalsParser());
        parser = new CronExpression(manager);
    }

    @Test
    public void testIfCronExpressionIsWorkingAsExpected() {
        init();
        String expected =
                "minute        0 15 30 45\n" +
                        "hour          0\n" +
                        "day of month  1 15\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   1 2 3 4 5\n" +
                        "command       /usr/bin/find\n";

        String cronExpression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        CronParserResponse response = parser.parseExpression(cronExpression.split(" "));
        assertEquals(expected, response.toString());
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of("**/15 0 1,15 * 1-5 /usr/bin/find"),
                Arguments.of("*/67 0 1,15 * 1-5 /usr/bin/find"),
                Arguments.of("* 0 1,15 * 10 /usr/bin/find"),
                Arguments.of("* 0 1,* * 1-5 /usr/bin/find")
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    public void testIfCronExpressionParserThrowsException(String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.parseExpression(cronExpression.split(" "));
        });
    }

}