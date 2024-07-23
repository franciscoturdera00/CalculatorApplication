package Runners;

/**
 * Language interpreter for a calculator
 */
public interface IInputHandler {

    /**
     * Parses and performs calculations as appropiate
     * @param originalInput A single line of input to parse and perform appropiate calculations
     */
    void handleInputCalculations(String originalInput);

}
