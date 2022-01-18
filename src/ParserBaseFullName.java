public class ParserBaseFullName extends ParserBase{
    public ParserBaseFullName(String request){
        super(request);
    }
    protected boolean checkInput(String input){
        boolean formatUserName = true;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isAlphabetic(input.charAt(i))) {
                formatUserName = false;
                break;
            }
        }
        return formatUserName;
    }
}
