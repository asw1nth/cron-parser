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

class StepIntervalParserTest {

    StepIntervalParser parser = new StepIntervalParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "*/15", Arrays.asList(0, 15, 30, 45)),
                Arguments.of(CronFieldType.HOUR,   "*/12", Arrays.asList(0,12)),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "*/10", Arrays.asList(1,11,21,31)),
                Arguments.of(CronFieldType.MONTH,"*/2", Arrays.asList(1,3,5,7,9,11)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "*/1", Arrays.asList(1,2,3,4,5,6,7))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "*/70"),
                Arguments.of(CronFieldType.HOUR,   "*/25"),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "*/45"),
                Arguments.of(CronFieldType.MONTH,"*/22"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "*/10")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfNthIntervalParserIsWorkingAsExpected(CronFieldType cronFieldType, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(cronFieldType, cronExpression);
        assertEquals(actualList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfNthIntervalParserThrowsExceptionForInvalidInput(CronFieldType cronFieldType, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(cronFieldType, cronExpression);
        });
    }

}