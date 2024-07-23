package SimpleCalculatorApp.Tests.Input;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SimpleCalculatorApp.Runners.IInputHandler;
import SimpleCalculatorApp.Runners.InputHandler;

public class InputHandlerTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    IInputHandler handler;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        handler = new InputHandler();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private String inputNotValid(String input) {
        return "input \"" + input + "\" is not valid\n";
    }

    @Test
    public void testHandleInputCalculationsNull() {
        handler.handleInputCalculations(null);
        assertEquals(this.inputNotValid(""), outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsAverageCaseNoOutput() {
        handler.handleInputCalculations("a add 4");
        handler.handleInputCalculations("b add a");
        handler.handleInputCalculations("r subtract 4");
        assertEquals("", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsAverageCaseWithOutput() {
        handler.handleInputCalculations("a add 4");
        handler.handleInputCalculations("b add a");
        handler.handleInputCalculations("r subtract 4");
        handler.handleInputCalculations("print b");
        assertEquals("4\n", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsInvalidQuit() {
        handler.handleInputCalculations("foobar");
        assertEquals(this.inputNotValid("foobar"), outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsPrint() {
        handler.handleInputCalculations("print a");
        handler.handleInputCalculations("a add 3");
        handler.handleInputCalculations("print a");
        assertEquals("0\n3\n", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsPrintKeyHasOnlyNumbers() {
        String num = "217958";
        handler.handleInputCalculations("print " + num);
        assertEquals("\"" + num + "\": Register names must contain at least one letter\n", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsPrintNonAlphanumericKey() {
        String key = "key;;!";
        handler.handleInputCalculations("print " + key);
        assertEquals("\"" + key + "\": Register names must be alphanumeric and contain at least one letter\n", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsOperationKeyHasOnlyNumbers() {
        String key = "217958";
        handler.handleInputCalculations(key + " add 17");
        assertEquals("\"" + key + "\": Register names must contain at least one letter\n", outContent.toString());
    }

    @Test
    public void testHandleInputCalculationsOperationNonAlphanumericKey() {
        String key = "key;;!";
        handler.handleInputCalculations(key + " add 124");
        assertEquals("\"" + key + "\": Register names must be alphanumeric and contain at least one letter\n", outContent.toString());
    }

}
