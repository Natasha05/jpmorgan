package main.java.utils;

import main.java.model.instruction.Instruction;
import main.java.model.instruction.InstructionDetails;
import main.java.model.instruction.TradeFlag;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class InstructionsGenerator {
    public static Set<Instruction> getInstructions() {
        return new HashSet<>(Arrays.asList(

            new Instruction(
                "E1",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 21),
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25))),

            new Instruction(
                "E2",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 20),
                new InstructionDetails(
                        Currency.getInstance("AED"),
                        BigDecimal.valueOf(0.22),
                        450,
                        BigDecimal.valueOf(150.5))),

            new Instruction(
                "E3",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 19),
                new InstructionDetails(
                        Currency.getInstance("SAR"),
                        BigDecimal.valueOf(0.27),
                        150,
                        BigDecimal.valueOf(400.8))),

            new Instruction(
                "E4",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 22),
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.34),
                        50,
                        BigDecimal.valueOf(500.6))),

            new Instruction(
                "E5",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 22),
                new InstructionDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new Instruction(
                "E6",
                TradeFlag.BUY,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 22),
                new InstructionDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new Instruction(
                "E7",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 22),
                new InstructionDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                    1000,
                        BigDecimal.valueOf(160.6))),

            new Instruction(
                "E8",
                TradeFlag.SELL,
                LocalDate.of(2017, 8, 11),
                LocalDate.of(2017, 8, 22),
                    new InstructionDetails(
                            Currency.getInstance("EUR"),
                            BigDecimal.valueOf(0.34),
                        120,
                            BigDecimal.valueOf(500.6)))
        ));
    }
}
