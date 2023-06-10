package core.basesyntax;

import core.basesyntax.util.DateUtil;
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
        LocalDate localDateFrom = DateUtil.parseToDate(dateFrom);
        LocalDate localDateTo = DateUtil.parseToDate(dateTo);
        HashMap<String, Integer> salaries = new HashMap<>();
        for (String name: names) {
            salaries.put(name, 0);
        }
        for (String piece: datas) {
            String[] data = piece.split(" ");
            if (DateUtil.isBetween(DateUtil.parseToDate(data[DATE_INDEX]),
                    localDateFrom, localDateTo)) {
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
}
