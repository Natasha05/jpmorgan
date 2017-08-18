package main.java.logic.reports;

import main.java.model.instruction.Instruction;

import java.util.Set;

public interface IReportGenerator {
    String generateInstructionsReport(Set<Instruction> instructions);
}
