package Tests.Calculator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Calculator.ISimpleCalculator;
import Calculator.SimpleCalculator;

public class SimpleCalculatorTest {

    private ISimpleCalculator<Integer> calcWithKeyFoo(int value) {
        Map<String, Integer> reg = new HashMap<>();
        reg.put("foo", value);
        return new SimpleCalculator(reg);
    }

    @Test
    public void testAdd() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.add("baz", "3");
        assertEquals(3, (int)calc.evaluate("baz"));
    }

    @Test
    public void testMultiply() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.multiply("baz", "3");
        assertEquals(0, (int)calc.evaluate("baz"));
    }

    @Test
    public void testMultiplyWithValue() {
        ISimpleCalculator<Integer> calc = calcWithKeyFoo(3);
        calc.multiply("foo", "3");
        assertEquals(9, (int)calc.evaluate("foo"));
    }

    @Test
    public void testSubtract() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.subtract("baz", "3");
        assertEquals(-3, (int)calc.evaluate("baz"));
    }

    @Test
    public void testEvaluateNonExistentKey() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        // All valid keys "exist" and are initialized at zero
        assertEquals(0, (int)calc.evaluate("foo"));
    }

    @Test
    public void testEvaluateRetrieveExistingKey() {
        ISimpleCalculator<Integer> calc = calcWithKeyFoo(3);
        assertEquals(3, (int)calc.evaluate("foo"));
    }

    @Test
    public void givenExample1() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.add("A", "2");
        calc.add("A", "3");
        assertEquals(5, (int)calc.evaluate("A"));
        calc.add("B", "5");
        calc.subtract("B", "2");
        assertEquals(3, (int)calc.evaluate("B"));
        calc.add("A", "1");
        assertEquals(6, (int)calc.evaluate("A"));
    }

    @Test
    public void givenExample2() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.add("a", "10");
        calc.add("b", "a");
        calc.add("b", "1");
        assertEquals(11, (int)calc.evaluate("b"));
    }

    @Test
    public void givenExample3() {
        ISimpleCalculator<Integer> calc = new SimpleCalculator();
        calc.add("result", "revenue");
        calc.subtract("result", "costs");
        calc.add("revenue", "200");
        calc.add("costs", "salaries");
        calc.add("salaries", "20");
        calc.multiply("salaries", "5");
        calc.add("costs", "10");
        assertEquals(90, (int)calc.evaluate("result"));
    }
}
