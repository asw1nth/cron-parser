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

class NumberParserTest {

    NumberParser parser = new NumberParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "1,2", Arrays.asList(1,2)),
                Arguments.of(CronFieldType.HOUR,   "1,2", Arrays.asList(1,2)),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "1", List.of(1)),
                Arguments.of(CronFieldType.MONTH,"1,2", Arrays.asList(1,2)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1,2", Arrays.asList(1,2)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1,2,5", Arrays.asList(1,2,5))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "10,70"),
                Arguments.of(CronFieldType.MINUTE, "-1"),
                Arguments.of(CronFieldType.HOUR, "25"),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "35"),
                Arguments.of(CronFieldType.MONTH, "15"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "8"),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "1,8")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfFixedTimingsParserIsWorkingAsExpected(CronFieldType cronFieldType, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(cronFieldType, cronExpression);
        assertEquals(actualList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfFixedTimingsParserThrowsExceptionForInvalidInput(CronFieldType cronFieldType, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(cronFieldType, cronExpression);
        });
    }

}