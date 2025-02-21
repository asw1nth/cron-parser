package org.cronparser.parser.type;

import org.cronparser.exception.InvalidCronExpression;
import org.cronparser.model.CronFieldType;
import org.cronparser.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class WeekNamedParser extends Parser {

    @Override
    public List<Integer> getTimings(CronFieldType cronFieldType, String cronExpression) {
        List<Integer> result = new ArrayList<>();
        if (!cronFieldType.getName().equals(CronFieldType.DAY_OF_WEEK.getName())) {
            throw new InvalidCronExpression(cronFieldType, cronExpression, "MON-SUN Not supported except for day of week");
        }
        String dayOfWeek = cronExpression;
        result.add(getDayNum(dayOfWeek));
        return result;
    }

    @Override
    public String getRegex() {
        return "^(MON|TUE|WED|THU|FRI|SAT|SUN)(-+MON|TUE|WED|THU|FRI|SAT|SUN)?$";
    }

    // MON // (MON-FRI)

    private Integer getDayNum(String expression) {
        int res = 1;
        if (expression.equals("MON")) res = 1;
        else if (expression.equals("TUE")) res = 2;
        else if (expression.equals("WED")) res = 3;
        else if (expression.equals("THU")) res = 4;
        else if (expression.equals("FRI")) res = 5;
        else if (expression.equals("SAT")) res = 6;
        else if (expression.equals("SUN")) res = 7;
        return res;
    }
}
