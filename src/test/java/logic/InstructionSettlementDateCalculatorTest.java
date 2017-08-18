package test.java.logic;

import main.java.logic.InstructionSettlementDateCalculator;
import main.java.model.instruction.Instruction;
import main.java.model.instruction.InstructionDetails;
import main.java.model.instruction.TradeFlag;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionSettlementDateCalculatorTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 25); // Its a Friday

        final Instruction Inst = new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(Inst);

        // should be the same
        assertEquals(initialSettlementDate, Inst.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 27); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the first monday (28/8/2017)
        assertEquals(LocalDate.of(2017, 8, 28), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 25); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("AED"), // Its Arabia (AED)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the first Sunday (27/8/2017)
        assertEquals(LocalDate.of(2017, 8, 27), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 27); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SAR"), // Its Arabia (SAR)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }
}