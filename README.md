# Simple Calculator App

This calculator performs as a register. It stores values and returns them as prompted. Calculations are performed one of two ways:
1. Lazy Operations:
    - Whenever an operation between registers happen, the program will delay this operation until retrieval of a register value is requested.
2. Real Time Operations:
    - Whenver an operation is set onto a register value, this will happen immediately and the register's value will be updated.

## Usage

Run the following commands from the application directory.

To re-compile the application:

```
javac Main.java
```

This application can be used with ongoing input, or by providing a file with pre-written commands. To run the application:

```
java Main
OR
java Main <path-to-file>
```

SimpleCalculator has 3 verbs (case insensitive), namely:
- ADD
- SUBTRACT
- MULTIPLY

Register names must contain at least one letter.

Example Usage:
```
register1 ADD 6
register2 SUBTRACT 7
register1 ADD register2
register3 ADD 5
register1 MULTIPLY register3
print register1
quit
```
Result: 23

## Design Choices

- Lazy Evaluation: Lazy evaluation can happen in one of four ways:  

    1. Priority: Defining a metric or heruistic that dictates which evaluations must happen first. This approach can be expanded to ONLY evaluate what is needed by composing a dependency map that tracks what register values affect one another.
    2. Random: Pick which evaluation happens first at random
    3. Stack: Last in, first out
    4. Queue: First in, first out

    Given the examples provided for the desired behavior, there were only two viable options: Priority and Stack. After assessing the time frame, complexity, and readability of the options, it was decided that Stack would be the right approach for this solution. Given more time, it would be interesting to explore a Priority approach to determine what evaluations need not be evaluated. Although an interesting solution, the memory and time complexity of a Priority solution might exceed that of simply evaluating all computations, like the Stack does.
 
- Scalability

    This application is designed to be scalable. Thanks to the generalization of [ACalculator](Calculator/ACalculator.java), any defined calculator can be processed through it. To create a new calculator, one just needs to create a class that extends [ACalculator](Calculator/ACalculator.java) and implements [IBasicCalculator](Calculator/IBasicCalculator.java). Then, simply follow the pattern shown in [SimpleCalculator](Calculator/SimpleCalculator.java) and you can create any kind of calculator, with any numberic type (namely Byte, Double, Integer, Long, and Short). 
    
    It is recommended the user follows the convention of declaring an interface that extends [IBasicCalculator](Calculator/IBasicCalculator.java), as shown in [ISimpleCalculator](Calculator/ISimpleCalculator.java), with the desired operations, which is then implemented into the new calculator class, but not strictly necessary. 
    
    A new [IInputHandler](Runners/IInputHandler.java) will need to be implemented if the language associated with the new calculator changes. If the language does not change, you may consider implementing [ISimpleCalculator](Calculator/ISimpleCalculator.java) in your new calculator and updating the variable definition inside [InputHandler](Runners/InputHandler.java) to point to your new calculator.

- Zero

    When a register key is requested but does not exist, the register will return the value zero, as defined by the [Operation](Calculator/Operations/Operation.java) used in the calculator. Equivalently, all valid register keys exist, and are initiated at value zero.