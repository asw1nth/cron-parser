package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StepValueIntervalParserTest {

    StepValueIntervalParser parser = new StepValueIntervalParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "0/15", Arrays.asList(0, 15, 30, 45)),
                Arguments.of(CronFieldType.HOUR,   "0/12", Arrays.asList(0,12)),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "1/10", Arrays.asList(1,11,21,31)),
                Arguments.of(CronFieldType.MONTH,"1/2", Arrays.asList(1,3,5,7,9,11)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1/1", Arrays.asList(1,2,3,4,5,6,7))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "0/70"),
                Arguments.of(CronFieldType.HOUR,   "0/25"),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "1/45"),
                Arguments.of(CronFieldType.MONTH,"1/22"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1/10")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testStepValueIntervalParser(CronFieldType cronFieldType, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(cronFieldType, cronExpression);
        assertEquals(actualList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testStepValueIntervalParserThrowsException(CronFieldType cronFieldType, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(cronFieldType, cronExpression);
        });
    }

}