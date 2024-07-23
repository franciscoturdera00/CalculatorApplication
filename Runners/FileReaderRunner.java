package Runners;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class FileReaderRunner {

    public FileReaderRunner(String path) {
        IInputHandler inputHandler = new InputHandler();
        this.handleInput(path, inputHandler);
    }

    private void handleInput(String path, IInputHandler handler) {
        try {
            // Open the file as a FileInputStream
            FileInputStream fileInputStream = new FileInputStream(path);

            // Wrap the FileInputStream in an InputStreamReader
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            // Wrap the InputStreamReader in a BufferedReader
            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;
                InputHandler inputHandler = new InputHandler();
                while ((line = bufferedReader.readLine()) != null) {
                    inputHandler.handleInputCalculations(line);
                }
            }
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File could not be found");
            System.exit(0);
        }
    }
}
