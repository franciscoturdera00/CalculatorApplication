package Calculator;

/**
 * A bare bones calculator interface that only evaluates values.
 */
public interface IBasicCalculator<T extends Number> {

    /**
     * Evaluates any remaining calculations and returns the <T> value in variable key or Zero, defined by T
     * @param key Variable name that holds a Number
     * @return A Number or stronger type given by the value of the given variable key or Zero, defined by T
     */
    T evaluate(String key);

}
