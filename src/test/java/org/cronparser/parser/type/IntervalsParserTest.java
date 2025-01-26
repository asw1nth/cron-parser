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

class IntervalsParserTest {

    IntervalsParser parser = new IntervalsParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(CronFieldType.HOUR, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(CronFieldType.MONTH, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1-2,3-5", Arrays.asList(1, 2, 3, 4, 5))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "3-2"),
                Arguments.of(CronFieldType.MINUTE, "10-61"),
                Arguments.of(CronFieldType.HOUR, "3-2"),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "3-2"),
                Arguments.of(CronFieldType.MONTH, "3-2"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "3-2"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1-2,3-2")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfBoundIntervalsParserIsWorkingAsExpected(CronFieldType cronFieldType, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(cronFieldType, cronExpression);
        assertEquals(actualList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfBoundIntervalsThrowsExceptionForInvalidInput(CronFieldType cronFieldType, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(cronFieldType, cronExpression);
        });
    }

}