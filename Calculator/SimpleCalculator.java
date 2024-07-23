package Calculator;

import Calculator.Operations.Operation;

import java.util.Map;

import Calculator.Operations.IntegerOperation;
import Util.Utils;

/**
 * A calculator that implements the {@link ISimpleCalculator} interface. All operations are evaluated as defined in {@link ACalculator}.
 */
public class SimpleCalculator extends ACalculator<Integer> implements ISimpleCalculator<Integer> {

    private Operation<Integer> adder;
    private Operation<Integer> subtracter;
    private Operation<Integer> multiplier;

    /**
     * Initiate the calculator with existing register values.
     * Any unregistered key is given the value 0 if requested.
     * @param startingRegister Initial register values
     */
    public SimpleCalculator(Map<String, Integer> startingRegister) {
        super();
        if (startingRegister != null) {
            this.register.putAll(startingRegister);
        }
        defineOperations();
    }

    /**
     * Initialize an  empty calculator, without register keys.
     * Any unregistered key is given the value 0 if requested.
     */
    public SimpleCalculator() {
        super();
        defineOperations();
    }

    @Override
    public void add(String key, String value) {
        this.performOperation(key, value, this.adder);
    }

    @Override
    public void subtract(String key, String value) {
        this.performOperation(key, value, this.subtracter);
    }

    @Override
    public void multiply(String key, String value) {
        this.performOperation(key, value, this.multiplier);
    }

    @Override
    public Integer evaluate(String key) {
        return this.evaluate(key, 0);
    }

    private void performOperation(String key, String value, Operation<Integer> operation) {
        Integer intValue = Utils.integerValue(value);
        this.performOperation(key, value, intValue, operation);
    }

    // Defines addition in the context of integer addition
    private Operation<Integer> defineAddition() {
        Operation<Integer> adder = new IntegerOperation() {
            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 + x2;
            }
        };
        return adder;
    }

    // Defines subtraction in the context of integer subtraction
    private Operation<Integer> defineSubtraction() {
        Operation<Integer> subtracter = new IntegerOperation() {
            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 - x2;
            }
        };
        return subtracter;
    }

    // Defines multiplication in the context of integer multiplication
    private Operation<Integer> defineMultiplication() {
        Operation<Integer> multiplier = new IntegerOperation() {
            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 * x2;
            } 
        };
        return multiplier;
    }

    // Iitializes operations with their definitions
    private void defineOperations() {
        this.adder = defineAddition();
        this.subtracter = defineSubtraction();
        this.multiplier = defineMultiplication();
    }
}
