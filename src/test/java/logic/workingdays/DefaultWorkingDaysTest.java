package test.java.logic.workingdays;

import org.junit.Before;
import org.junit.Test;

import main.java.logic.workingdays.DefaultWorkingDays;
import main.java.logic.workingdays.IWorkingDays;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DefaultWorkingDaysTest {

    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = DefaultWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Monday() throws Exception {
        final LocalDate aMonday = LocalDate.of(2017, 8, 21);

        // should return the same, since Monday is a working by default
        assertEquals(aMonday, workingDays.findFirstWorkingDate(aMonday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2017, 8, 25);

        // should return the same, since Friday is a working by default
        assertEquals(aFriday, workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2017, 8, 26);

        // should return the first Monday (28/8/2017), since Saturday is not a working day
        assertEquals(LocalDate.of(2017, 8, 28), workingDays.findFirstWorkingDate(aSaturday));
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2017, 8, 27);

        // should return the first Monday (28/8/2017), since Sunday is not a working day
        assertEquals(LocalDate.of(2017, 8, 28), workingDays.findFirstWorkingDate(aSunday));
    }
}