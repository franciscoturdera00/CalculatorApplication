import Runners.FileReaderRunner;
import Runners.InputStreamRunner;

public final class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            new FileReaderRunner(args[0]);
        } else if (args.length == 0) {
            new InputStreamRunner();
        } else {
            System.out.println("Error: Please provide a file path or no arguments");
        }
        
    }
}
