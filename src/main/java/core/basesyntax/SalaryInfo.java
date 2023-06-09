package core.basesyntax;

import java.time.LocalDate;
import java.util.HashMap;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] datas, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate localDateFrom = parse(dateFrom);
        LocalDate localDateTo = parse(dateTo);
        HashMap<String, Integer> salaries = new HashMap<>();
        for (String name: names) {
            salaries.put(name, 0);
        }
        for (String piece: datas) {
            String[] data = piece.split(" ");
            if (compare(parse(data[DATE_INDEX]), localDateFrom, localDateTo)) {
                String name = data[NAME_INDEX];
                salaries.put(name, salaries.get(name)
                        + Integer.parseInt(data[SALARY_INDEX])
                        * Integer.parseInt(data[HOURS_INDEX]));
            }
        }
        for (String name: names) {
            report.append(name)
                    .append(" - ")
                    .append(salaries.get(name))
                    .append(System.lineSeparator());
        }
        report.deleteCharAt(report.length() - 1);
        report.deleteCharAt(report.length() - 1);
        return report.toString();
    }

    private LocalDate parse(String date) {
        return LocalDate.of(
                Integer.parseInt(date.substring(6)),
                Integer.parseInt(date.substring(3, 5)),
                Integer.parseInt(date.substring(0, 2)));
    }

    public boolean compare(LocalDate given, LocalDate from, LocalDate to) {
        return (given.isAfter(from) && given.isBefore(to))
                || given.isEqual(from) || given.isEqual(to);
    }
}
