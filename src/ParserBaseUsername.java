public class ParserBaseUsername extends ParserBase {
    private Class<? extends Client> userType;
    private Shop shop;
    public ParserBaseUsername(String request, Shop shop, Class<? extends Client> userType) {
        super(request);
        this.userType = userType;
        this.shop = shop;
    }


    protected boolean checkInput(String input) {
        boolean existUserName = false;
        DynamicArray<Client> userDynamicArray = this.shop.getUsers();
        for (int i = 0; i < this.shop.getUsers().length(); i++) {
            if (userDynamicArray.getItem(i).getClass().equals(userType)) {
                if (userDynamicArray.getItem(i).getUsername().equals(input)) {
                    existUserName = true;
                    break;
                }
            }
        }
        return !existUserName;
    }

}
