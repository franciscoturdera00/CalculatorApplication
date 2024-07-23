package Runners;

import Calculator.ISimpleCalculator;
import Calculator.SimpleCalculator;
import Util.Utils;

public final class InputHandler implements IInputHandler {

    // By not specifying the type, we gain the ability to be more
    // general when requesting an evaluation
    @SuppressWarnings("rawtypes")
    private ISimpleCalculator calculator;

    public InputHandler() {
        // Can be swapped with any ISimpleCalculator, regardless of type T
        this.calculator = new SimpleCalculator();
    }

    public void handleInputCalculations(String originalInput) {
        if (originalInput == null) {
            this.printNotValid("");
            return;
        }
        // Ensures register name and input is case insensitive
        String input = originalInput.toLowerCase();
        String[] breakdown = input.split(" ");
        if (breakdown.length == 1) {
            this.handleQuit(breakdown[0], originalInput);
            return;
        }
        if (breakdown.length == 2) {
            this.handlePrint(breakdown, originalInput);
            return;
        }
        if (breakdown.length == 3) {
            this.handleOperation(breakdown, originalInput);
            return;
        }
        // If it reaches here, invalid input
        this.printNotValid(originalInput);
    }


    private void handleOperation(String[] breakdown, String originalInput) {
        String registerName = breakdown[0];
        String operation = breakdown[1];
        String value = breakdown[2];
        if (Utils.isAlphanumeric(registerName)) { // check register name is alphanumeric
            if (Utils.integerValue(registerName) == null) { // Check register name is valid
                if (operation.toLowerCase().equals("add")) {
                    this.calculator.add(registerName, value);
                } else if (operation.toLowerCase().equals("subtract")) {
                    this.calculator.subtract(registerName, value);
                } else if (operation.toLowerCase().equals("multiply")) {
                    this.calculator.multiply(registerName, value);
                } else {
                    this.printNotValid(originalInput);
                }
            } else {
                System.out.print("\"" + registerName + "\": Register names must contain at least one letter\n");
            }
        } else {
            System.out.print("\"" + registerName + "\": Register names must be alphanumeric and contain at least one letter\n");
        }
        
    }

    private void handlePrint(String[] breakdown, String originalInput) {
        String registerName = breakdown[1];
        if (!breakdown[0].equals("print")) {
            this.printNotValid(originalInput); // All two word inputs must start with "print"
            return;
        } else {
            if (Utils.isAlphanumeric(registerName)) { // check register name is alphanumeric
                if (Utils.integerValue(registerName) == null) { // Check register name is valid
                    Number result = this.calculator.evaluate(registerName);
                    System.out.print(result + "\n");
                    return;
                } else {
                    System.out.print("\"" + registerName + "\": Register names must contain at least one letter\n");
                    return;
                }
            } else {
                System.out.print("\"" + registerName + "\": Register names must be alphanumeric and contain at least one letter\n");
                return;
            }
        }
    }

    private void handleQuit(String keyWord, String originalInput) {
        if (keyWord.equals("quit")) { // Keyword to quit program
            System.exit(0);
        } else {
            this.printNotValid(originalInput); // No other single word inputs are valid
            return;
        }
    }

    private void printNotValid(String input) {
        System.out.print("input \"" + input + "\" is not valid\n");
    }
}
