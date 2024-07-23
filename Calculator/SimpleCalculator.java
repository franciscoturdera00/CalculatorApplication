package Calculator;

import Calculator.Operations.Operation;

import java.util.Map;

import Calculator.Operations.IntegerOperation;
import Util.Utils;

/**
 * A calculator that implements the {@link ISimpleCalculator} interface. All operations are evaluated as defined in {@link ACalculator}.
 */
public class SimpleCalculator extends ACalculator<Integer> implements ISimpleCalculator<Integer> {

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
    }

    /**
     * Initialize an  empty calculator, without register keys.
     * Any unregistered key is given the value 0 if requested.
     */
    public SimpleCalculator() {
        super();
    }

    @Override
    public void add(String key, String value) {
        // Defines addition in the context of integer addition
        Operation<Integer> adder = new IntegerOperation() {

            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 + x2;
            }
        };
        this.performOperation(key, value, adder);
    }

    @Override
    public void subtract(String key, String value) {
        // Defines subtraction in the context of integer subtraction
        Operation<Integer> subtracter = new IntegerOperation() {

            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 - x2;
            }
        };
        this.performOperation(key, value, subtracter);
    }

    @Override
    public void multiply(String key, String value) {
        // Defines multiplication in the context of integer multiplication
        Operation<Integer> multiplier = new IntegerOperation() {

            @Override
            public Integer apply(Integer x1, Integer x2) {
                return x1 * x2;
            } 
        };
        this.performOperation(key, value, multiplier);
    }

    @Override
    public Integer evaluate(String key) {
        return this.evaluate(key, 0);
    }

    private void performOperation(String key, String value, Operation<Integer> operation) {
        Integer intValue = Utils.integerValue(value);
        this.performOperation(key, value, intValue, operation);
    }
}
