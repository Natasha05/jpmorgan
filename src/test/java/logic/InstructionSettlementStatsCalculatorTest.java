package test.java.logic;

import test.java.model.*;
import test.java.model.instruction.*;
import org.junit.Test;

import main.java.logic.InstructionSettlementDateCalculator;
import main.java.logic.InstructionSettlementStatsCalculator;
import main.java.logic.rank.Rank;
import main.java.model.instruction.Instruction;
import main.java.model.instruction.InstructionDetails;
import main.java.model.instruction.TradeFlag;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class InstructionSettlementStatsCalculatorTest {

    private static final LocalDate MONDAY    = LocalDate.of(2017, 8, 21);
    private static final LocalDate TUESDAY   = LocalDate.of(2017, 8, 22);
    private static final LocalDate WEDNESDAY = LocalDate.of(2017, 8, 23);
    private static final LocalDate SATURDAY  = LocalDate.of(2017, 8, 19);
    private static final LocalDate SUNDAY    = LocalDate.of(2017, 8, 20);

    private static Set<Instruction> getSetOfInstructions() {
        final Set<Instruction> instructions = new HashSet<>();

        // *************************************************************
        // Should be under the same settlement date (21/8/2017)
        // *************************************************************
        instructions.add(new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                MONDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        100,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E2",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                MONDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E3",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                SATURDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        300,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E4",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                SUNDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        // *************************************************************
        // Should be under the same settlement date (21/8/2017)
        // *************************************************************
        instructions.add(new Instruction(
                "E5",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                TUESDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        400,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E6",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                TUESDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        1000,
                        BigDecimal.valueOf(1))));

        // *****************************************************
        // Should be under the same settlement date (21/8/2017)
        // *****************************************************
        instructions.add(new Instruction(
                "E7",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                WEDNESDAY,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        7000,
                        BigDecimal.valueOf(1))));

        InstructionSettlementDateCalculator.calculateSettlementDates(instructions);

        return instructions;
    }

    @Test
    public void testDailyIncomingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyIncomingAmount =
                InstructionSettlementStatsCalculator.calculateDailyIncomingAmount(getSetOfInstructions());

        assertEquals(2, dailyIncomingAmount.size());
        assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(1000.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyOutgoingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                InstructionSettlementStatsCalculator.calculateDailyOutgoingAmount(getSetOfInstructions());

        assertEquals(3, dailyOutgoingAmount.size());
        assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(400.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));

    }

    @Test
    public void testDailyIncomingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                InstructionSettlementStatsCalculator.calculateDailyIncomingRanking(getSetOfInstructions());
        System.out.println(dailyIncomingRanking);

        assertEquals(2, dailyIncomingRanking.size());
        System.out.println(dailyIncomingRanking.get(MONDAY).size());

        assertEquals(1, dailyIncomingRanking.get(MONDAY).size());
        assertEquals(1, dailyIncomingRanking.get(TUESDAY).size());

        assertTrue(dailyIncomingRanking.get(MONDAY).contains(new Rank(1, "E4", MONDAY)));
        assertTrue(dailyIncomingRanking.get(TUESDAY).contains(new Rank(1, "E6", TUESDAY)));

    }

    @Test
    public void testDailyOutgoingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                InstructionSettlementStatsCalculator.calculateDailyOutgoingRanking(getSetOfInstructions());

        assertEquals(3, dailyOutgoingRanking.size());

        assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(1, "E3", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(2, "E2", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(3, "E1", MONDAY)));

        assertTrue(dailyOutgoingRanking.get(TUESDAY).contains(new Rank(1, "E5", TUESDAY)));

        assertTrue(dailyOutgoingRanking.get(WEDNESDAY).contains(new Rank(1, "E7", WEDNESDAY)));
    }
}