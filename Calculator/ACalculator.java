package Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Calculator.Operations.Operation;

/**
 * An abstract implementation of a calculator. This class serves as an generalization of the necessary computations.
 * Any unregistered key is given the value 0 if requested.
 * This calculator evaluates in two different ways:
 * 1. Operations where a register key is mutated by a final value are performed at call time.
 * 2. Operations where a register key is mutated by another register key are performed when an evaluation is requested (lazy evaluation).
 */
public abstract class ACalculator<T extends Number> {

    // Register to current value map. A register name must be alphanumeric and contain at least one letter.
    protected Map<String, T> register;

    // Stack of executable operations
    protected Stack<Runnable> lazyStack;

    protected ACalculator() {
        this.register = new HashMap<>();
        this.lazyStack = new Stack<>();
    }

    /**
     * Performs an operation. This operation is performed as described in the class definition.
     * @param key Register key that is being mutated
     * @param valueKey Register key that is performing the mutation, if any
     * @param value Value mutating register key, either a final Number or null
     * @param operation The operation to be performed on the register key
     */
    protected void performOperation(String key, String valueKey, T value, Operation<T> operation) {
        if (value == null) { // null -> register key
            Runnable eval = () -> {
                // Need to perform these operations inside the runnable to evaluate lazily
                T registerValueOfKey = this.register.getOrDefault(key, operation.zero());
                T registerValueOfValue = this.register.getOrDefault(valueKey, operation.zero());
                T newKeyValue = operation.apply(registerValueOfKey, registerValueOfValue);
                this.register.put(key, newKeyValue);
            };
            lazyStack.push(eval);
        } else {
            T registerValueOfKey = this.register.getOrDefault(key, operation.zero());
            T newKeyValue = operation.apply(registerValueOfKey, value);
            this.register.put(key, newKeyValue);
        }
    }

    /**
     * Performs all operations in the lazy stack and returns value of the requested register key or a default value if key does not exist
     * @param key Register key
     * @param defaultValue Value to be returned if key does not exist
     * @return The value of the register key or the default value
     */
    protected T evaluate(String key, T defaultValue) {
        while (!this.lazyStack.isEmpty()) {
            this.lazyStack.pop().run();
        }
        return this.register.getOrDefault(key, defaultValue);
    }

}
