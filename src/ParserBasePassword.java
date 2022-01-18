public class ParserBasePassword extends ParserBase {
    public static final int MIN_SIZE_LENGTH=6;

    public ParserBasePassword(String request) {
        super(request);
    }
    @Override
    protected boolean checkInput(String password) {
        return password.length() >= MIN_SIZE_LENGTH;
    }
}

