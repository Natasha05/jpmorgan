package main.java.logic.workingdays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class WorkingDays implements IWorkingDays {
    protected Map<DayOfWeek, Boolean> isWorkingDayMap = new HashMap<>();

    protected abstract void setupWorkingDays();

    public WorkingDays() {
        setupWorkingDays();
    }

    public LocalDate findFirstWorkingDate(LocalDate date) {

        if (isWorkingDayMap.values().stream().noneMatch(b -> b)) {
            return null;
        }
        return findFirstWorkingDateRec(date);
    }

    private LocalDate findFirstWorkingDateRec(LocalDate date) {
        final DayOfWeek inputDay = date.getDayOfWeek();

        // if the given date is working day, just return date
        if (isWorkingDayMap.get(inputDay)) {
            return date;
        } else {
            // else find the next working day
            return findFirstWorkingDateRec(date.plusDays(1));
        }
    }
}
