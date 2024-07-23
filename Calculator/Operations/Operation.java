package Calculator.Operations;

/**
 * A representation of the basic elements necessary to define a mathematical operation
 */
public interface Operation<T extends Number> {
    T apply(T x1, T x2);

    T zero();
}