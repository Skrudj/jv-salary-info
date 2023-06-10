package core.basesyntax.util;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate parseToDate(String date) {
        return LocalDate.of(
                Integer.parseInt(date.substring(6)),
                Integer.parseInt(date.substring(3, 5)),
                Integer.parseInt(date.substring(0, 2)));
    }

    public static boolean isBetween(LocalDate given, LocalDate from, LocalDate to) {
        return (given.isAfter(from) && given.isBefore(to))
                || given.isEqual(from) || given.isEqual(to);
    }
}
