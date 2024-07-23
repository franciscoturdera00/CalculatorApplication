package Calculator.Operations;

/**
 * A more strongly defined application of Operation in relation to Integers
 */
public interface IntegerOperation extends Operation<Integer> {

    @Override
    default Integer zero() {
        return 0;
    }

}
