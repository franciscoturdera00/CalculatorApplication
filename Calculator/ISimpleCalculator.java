package Calculator;

/**
 * A Simple Calculator interface.
 * This calculator supports 3 operations, namely adding, subtracting, and multiplying.
 * Each operation has the option to take in a value from a number or a variable holding a value.
 * 
 * Type T: The resulting numeric type of the evaluation.
 */
public interface ISimpleCalculator<T extends Number> extends IBasicCalculator<T> {

    /**
     * Adds value to the variable key and stores as new variable key
     * @param key Variable name that holds a Number
     * @param value Number or variable name that holds a Number
     */
    void add(String key, String value);

    /**
     * Subtracts value from the variable key and stores as new variable key
     * @param key Variable name that holds a Number
     * @param value Number or variable name that holds a Number
     */
    void subtract(String key, String value);

    /**
     * Multiplies variable key by value and stores as new variable key
     * @param key Variable name that holds a Number
     * @param value Number or variable name that holds a Number
     */
    void multiply(String key, String value);

}
