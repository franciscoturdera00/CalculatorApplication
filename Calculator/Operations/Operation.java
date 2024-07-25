package Calculator.Operations;

/**
 * A representation of the basic elements necessary to define a mathematical operation
 */
public interface Operation<T extends Number> {

    /**
     * Applies binary operation between two elements
     * @param x1 An element of type T available to operate with
     * @param x2 An element of type T available to operate with
     * @return The result after applying the binary operation between two elements
     */
    T apply(T x1, T x2);

    /**
     * The value that represents lack of value in the chosen mathematical representation
     * @return the value representing a lack of value
     */
    T zero();
}