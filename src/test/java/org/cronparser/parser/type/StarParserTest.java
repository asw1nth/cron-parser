package org.cronparser.parser.type;

import org.cronparser.model.CronFieldType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StarParserTest {

    StarParser parser = new StarParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(CronFieldType.MINUTE, "*", getListOfIntegers(0,59)),
                Arguments.of(CronFieldType.HOUR, "*", getListOfIntegers(0,23)),
                Arguments.of(CronFieldType.DAY_OF_MONTH, "*", getListOfIntegers(1,31)),
                Arguments.of(CronFieldType.MONTH, "*", getListOfIntegers(1,12)),
                Arguments.of(CronFieldType.DAY_OF_WEEK, "*", getListOfIntegers(1,7))
        );
    }

    private static List<Integer> getListOfIntegers(int start, int end) {
        List<Integer> result = new ArrayList<>();
        while(start <= end) {
            result.add(start);
            start += 1;
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfStarParserThrowsExceptionForInvalidInput(CronFieldType cronFieldType, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(cronFieldType, cronExpression);
        assertEquals(actualList, expectedList);
    }

}