package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        if (date.contains(".") && date.contains(":"))
        {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.M.yyyy HH:mm:ss");
            DateTime dt = formatter.parseDateTime(date);
            DateTime minYearDate = dt.dayOfYear().withMinimumValue();
            DateTime minMonthDate = dt.dayOfMonth().withMinimumValue();
            int weekDis = (minYearDate.getWeekyear() == dt.getYear()) ? 0 : 1;
            int weekOfYear = dt.getWeekOfWeekyear() + weekDis;
            if (weekOfYear >= 53)
                weekOfYear = 1;
            int weekOfMonth = dt.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
            if (minMonthDate.getWeekOfWeekyear() >= dt.getWeekOfWeekyear())
                weekOfMonth = dt.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
            if (dt.getMonthOfYear() == 1)
                weekOfMonth = weekOfYear;
            System.out.format("День: %d%n" +
                    "День недели: %d%n" +
                    "День месяца: %d%n" +
                    "День года: %d%n" +
                    "Неделя месяца: %d%n" +
                    "Неделя года: %d%n" +
                    "Месяц: %d%n" +
                    "Год: %d%n" +
                    "Эра: %d%n" +
                    "AM или PM: %s%n" +
                    "Часы: %d%n" +
                    "Часы дня: %d%n" +
                    "Минуты: %d%n" +
                    "Секунды: %d%n",
                    dt.getDayOfMonth(),
                    (dt.getDayOfWeek() % 7) + 1,
                    dt.getDayOfMonth(),
                    dt.getDayOfYear(),
                    weekOfMonth,
                    weekOfYear,
                    dt.getMonthOfYear() - 1,
                    dt.getYear(),
                    dt.getEra(),
                    dt.getHourOfDay() >= 12 ? 1 : 0,
                    dt.getHourOfDay() > 12 ? dt.getHourOfDay() - 12 : dt.getHourOfDay(),
                    dt.getHourOfDay(),
                    dt.getMinuteOfHour(),
                    dt.getSecondOfMinute());
        } else if (date.contains(".")) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.M.yyyy");
            DateTime dt = formatter.parseDateTime(date);
            DateTime minYearDate = dt.dayOfYear().withMinimumValue();
            DateTime minMonthDate = dt.dayOfMonth().withMinimumValue();
            int weekDis = (minYearDate.getWeekyear() == dt.getYear()) ? 0 : 1;
            int weekOfYear = dt.getWeekOfWeekyear() + weekDis;
            if (weekOfYear >= 53)
                weekOfYear = 1;
            int weekOfMonth = dt.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
            if (minMonthDate.getWeekOfWeekyear() >= dt.getWeekOfWeekyear())
                weekOfMonth = dt.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
            if (dt.getMonthOfYear() == 1)
                weekOfMonth = weekOfYear;
            System.out.format("День: %d%n" +
                    "День недели: %d%n" +
                    "День месяца: %d%n" +
                    "День года: %d%n" +
                    "Неделя месяца: %d%n" +
                    "Неделя года: %d%n" +
                    "Месяц: %d%n" +
                    "Год: %d%n" +
                    "Эра: %d%n",
                    dt.getDayOfMonth(),
                    (dt.getDayOfWeek() % 7) + 1,
                    dt.getDayOfMonth(),
                    dt.getDayOfYear(),
                    weekOfMonth,
                    weekOfYear,
                    dt.getMonthOfYear() - 1,
                    dt.getYear(),
                    dt.getEra());
        } else if (date.contains(":")) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
            DateTime dt = formatter.parseDateTime(date);
            System.out.format("AM или PM: %s%n" +
                            "Часы: %d%n" +
                            "Часы дня: %d%n" +
                            "Минуты: %d%n" +
                            "Секунды: %d%n",
                            dt.getHourOfDay() >= 12 ? 1 : 0,
                            dt.getHourOfDay() > 12 ? dt.getHourOfDay() - 12 : dt.getHourOfDay(),
                            dt.getHourOfDay(),
                            dt.getMinuteOfHour(),
                            dt.getSecondOfMinute());
        }
    }
}
