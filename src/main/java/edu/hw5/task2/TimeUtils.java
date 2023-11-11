package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class TimeUtils {

    private static final int THIRTEEN = 13;

    private TimeUtils() {
    }

    public static List<LocalDate> findAllFridaysThe13th(int year) {
        if (isNotValid(year)) {
            throw new IllegalArgumentException();
        }

        List<LocalDate> fridaysThe13th = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, THIRTEEN);

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThe13th.add(date);
            }
            date = date.plusMonths(1);
        }

        return fridaysThe13th;
    }

    public static LocalDate findNextFridayThe13th(LocalDate currentDate) {

        TemporalAdjuster nextFriday13th = TemporalAdjusters.ofDateAdjuster(
            temporal -> {
                LocalDate date
                    = temporal.withDayOfMonth(THIRTEEN)
                    .plusMonths(1);

                while (
                    date.getDayOfWeek() != DayOfWeek.FRIDAY
                        || date.getDayOfMonth() != THIRTEEN) {

                    date = date.plusMonths(1);
                }

            return date;
        });

        return currentDate.with(nextFriday13th);

    }

    private static boolean isNotValid(int year) {
        return year <= 0;
    }
}
