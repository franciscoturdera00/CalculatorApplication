package Runners;

import java.util.Scanner;

/**
 * 
 */
public final class InputStreamRunner {

    public InputStreamRunner() {
        IInputHandler inputHandler = new InputHandler();
        this.handleInput(inputHandler);
    }

    private void handleInput(IInputHandler handler) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Begin calculations:");
            while (true) { // Does not terminate until user terminates session
                String input = scanner.nextLine();
                handler.handleInputCalculations(input);
            }
        }
    }

}
