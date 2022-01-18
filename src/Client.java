import java.util.Date;

public class Client extends User {
    private boolean isVIP;
    private DynamicArray<ShoppingCart> shoppingCarts;

    public Client(String firstName, String lastName, String username, String password, boolean isVIP) {
        super(firstName, lastName, username, password);
        this.shoppingCarts = new DynamicArray<>(ShoppingCart.class);
        this.isVIP = isVIP;
    }

    public String getTypeVIP() {
        return isVIP ? "(VIP)!" : "";
    }

    public String typeClass() {
        return "[Client]";
    }

    public String login() {
        return "Hi, " + this.getFullName() + " " + this.getTypeVIP();
    }

    public int getAmountOfPurchases() {
        return this.shoppingCarts.length();
    }

    public boolean isVIP() {
        return isVIP;
    }

    public double getSpent() {
        double spent = 0;
        for (int i = 0; i < shoppingCarts.length(); i++) {
            spent += shoppingCarts.getItem(i).getPrice();
        }
        return spent;
    }

    public String getTheLastDate() {
        if (shoppingCarts.length() == 0) {
            return "no shopping has been made";
        }
        Date date = shoppingCarts.getItem(shoppingCarts.length() - 1).getDate();
        return date.toString();
    }

    public void addShoppingToArr(ShoppingCart shoppingCart) {
        this.shoppingCarts.addItem(shoppingCart);
    }

    public String toString() {
        StringBuilder output = new StringBuilder(super.toString()).append(" ").append(getTypeVIP());
        output.append("\n(").append(typeClass()).append(")\n");
        output.append("Number of Purchases: ").append(this.getAmountOfPurchases()).append("\nSpent: ")
                .append(this.getSpent());
        output.append("\nLast shop: ").append(this.getTheLastDate());
        return output.toString();
    }

    public void justLoggedIn(Shop shop) {
        shop.startShopping(this);
    }
}