package main.java;

import main.java.logic.reports.*;
import main.java.model.instruction.Instruction;
import main.java.utils.InstructionsGenerator;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Instruction> instructions = InstructionsGenerator.getInstructions();
        final IReportGenerator reportGenerator = new ReportGenerator();

        System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}
