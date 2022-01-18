import java.util.Scanner;

public class ParserBase {

    private final Scanner scanner = new Scanner(System.in);
    private final String request;

    public ParserBase(String request) {
        this.request = request;
    }

    protected boolean checkInput(String input) {
        return true;
    }

    public String getInput() {
        String input;
        do {
            System.out.println(request + ": ");
            input = scanner.nextLine();
        } while (!this.checkInput(input));
        return input;
    }
}